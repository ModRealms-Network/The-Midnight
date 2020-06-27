package midnight.gradle;

import groovy.lang.Closure;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.tasks.Copy;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ConstantInjectionTask extends Copy {
    private Type annotation;
    private String annotArgument = "value";
    private Map<Object, Object> constants = new HashMap<>();

    public ConstantInjectionTask() {

    }

    @Override
    protected CopyAction createCopyAction() {
        File destinationDir = getDestinationDir();
        if (destinationDir == null) {
            throw new InvalidUserDataException("No copy destination directory has been specified, use 'into' to specify a target directory.");
        } else {
            return new ASMCopyAction(getFileLookup().getFileResolver(destinationDir), this::transform);
        }
    }

    public void constant(Object val, Object constant) {
        constants.put(val, constant);
    }

    public void annotation(String annotation) {
        this.annotation = Type.getObjectType(annotation.replace('.', '/'));
    }

    public void argument(String argument) {
        this.annotArgument = argument;
    }

    private ClassNode transform(ClassNode cls) {
        if (annotation != null) {
            for (FieldNode field : cls.fields) {
                if ((field.access & Opcodes.ACC_STATIC) != 0 && (field.access & Opcodes.ACC_FINAL) != 0 && field.value != null) {
                    AnnotationNode annot = getAnnotation(field);
                    if (annot != null) {
                        Object value = getValue(annot);
                        Object fv = constants.get(value);
                        if (fv instanceof Supplier<?>) {
                            fv = ((Supplier<?>) fv).get();
                        }
                        if (fv instanceof Closure<?>) {
                            Closure<?> cl = (Closure<?>) fv;
                            fv = cl.call();
                        }
                        if (fv != null) {
                            if (fv instanceof Integer || fv instanceof Float || fv instanceof Long || fv instanceof Double || fv instanceof String) {
                                field.value = fv;
                            } else if (fv instanceof Boolean) {
                                field.value = (boolean) fv ? 1 : 0;
                            } else if (fv instanceof Number) {
                                field.value = ((Number) fv).intValue();
                            } else {
                                field.value = fv.toString();
                            }
                        }
                    }
                }
            }
        }
        return cls;
    }

    private AnnotationNode getAnnotation(FieldNode field) {
        AnnotationNode invisible = getAnnotation(field.invisibleAnnotations);
        if (invisible != null) return invisible;
        return getAnnotation(field.visibleAnnotations);
    }

    private AnnotationNode getAnnotation(List<AnnotationNode> annots) {
        if (annots == null) return null;
        for (AnnotationNode node : annots) {
            if (Type.getType(node.desc).equals(annotation)) {
                return node;
            }
        }
        return null;
    }

    private Object getValue(AnnotationNode node) {
        if (node.values == null) return null;
        for (int i = 0, l = node.values.size(); i < l; i += 2) {
            String name = (String) node.values.get(i);
            if (name.equals(annotArgument)) {
                return node.values.get(i + 1);
            }
        }
        return null;
    }
}
