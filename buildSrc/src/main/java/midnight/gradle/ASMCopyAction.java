package midnight.gradle;

import org.gradle.api.internal.file.CopyActionProcessingStreamAction;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.internal.file.copy.CopyActionProcessingStream;
import org.gradle.api.internal.file.copy.FileCopyDetailsInternal;
import org.gradle.api.tasks.WorkResult;
import org.gradle.api.tasks.WorkResults;
import org.gradle.internal.file.PathToFileResolver;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.*;
import java.util.function.Function;

public class ASMCopyAction implements CopyAction {
    private final PathToFileResolver fileResolver;
    private final Function<ClassNode, ClassNode> transformer;

    public ASMCopyAction(PathToFileResolver fileResolver, Function<ClassNode, ClassNode> transformer) {
        this.fileResolver = fileResolver;
        this.transformer = transformer;
    }

    @Override
    public WorkResult execute(CopyActionProcessingStream stream) {
        ASMCopyDetailsInternalAction action = new ASMCopyDetailsInternalAction();
        stream.process(action);
        return WorkResults.didWork(action.didWork);
    }

    private class ASMCopyDetailsInternalAction implements CopyActionProcessingStreamAction {
        private boolean didWork;

        private ASMCopyDetailsInternalAction() {
        }

        @Override
        public void processFile(FileCopyDetailsInternal details) {
            File target = fileResolver.resolve(details.getRelativePath().getPathString());

            if (details.getRelativePath().getPathString().endsWith(".class")) {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    details.copyTo(baos);

                    ClassReader reader = new ClassReader(baos.toByteArray());
                    ClassNode node = new ClassNode();
                    reader.accept(node, ClassReader.EXPAND_FRAMES);

                    node = transformer.apply(node);

                    ClassWriter out = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                    node.accept(out);

                    FileOutputStream fos = new FileOutputStream(target);
                    fos.write(out.toByteArray());
                    fos.close();

                    this.didWork = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new UncheckedIOException(e);
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}