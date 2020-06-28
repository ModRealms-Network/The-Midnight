package midnight.gradle;

import groovy.lang.Closure;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.tasks.Copy;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ConstantInjectionTask extends Copy {
    private String annotation;
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
            return new RoasterCopyAction(getFileLookup().getFileResolver(destinationDir), this::modify);
        }
    }

    public void constant(Object val, Object constant) {
        constants.put(val, constant);
    }

    public void annotation(String annotation) {
        this.annotation = annotation;
    }

    public void argument(String argument) {
        this.annotArgument = argument;
    }

    private JavaClassSource modify(JavaClassSource src) {
        src.getFields()
           .stream()
           .filter(field -> field.isStatic() && field.isFinal() && field.getLiteralInitializer() != null)
           .forEach(
                   field -> {
                       Optional<AnnotationSource<JavaClassSource>> annotation
                               = field.getAnnotations()
                                      .stream()
                                      .filter(annot -> annot.getQualifiedName().equals(this.annotation))
                                      .findFirst();

                       annotation.ifPresent(annot -> {
                           String value = annot.getStringValue(annotArgument);
                           Object fv = constants.get(value);
                           if (fv instanceof Supplier<?>) {
                               fv = ((Supplier<?>) fv).get();
                           }
                           if (fv instanceof Closure<?>) {
                               Closure<?> cl = (Closure<?>) fv;
                               fv = cl.call();
                           }
                           if (fv instanceof String)
                               field.setStringInitializer((String) fv);
                           else {
                               if (fv instanceof Integer) {
                                   field.setLiteralInitializer(fv + "");
                               } else if (fv instanceof Long) {
                                   field.setLiteralInitializer(fv + "L");
                               } else if (fv instanceof Float) {
                                   field.setLiteralInitializer(fv + "F");
                               } else if (fv instanceof Double) {
                                   field.setLiteralInitializer(fv + "D");
                               }
                           }
                       });
                   }
           );
        return src;
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
