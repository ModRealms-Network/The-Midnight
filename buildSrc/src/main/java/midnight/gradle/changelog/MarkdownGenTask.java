package midnight.gradle.changelog;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class MarkdownGenTask extends DefaultTask {
    private ChangelogInfo info;
    private File markdownOut;

    public void setInfo(ChangelogInfo info) {
        this.info = info;
    }

    public void setMarkdownOut(File markdownOut) {
        this.markdownOut = markdownOut;
    }

    public ChangelogInfo getInfo() {
        return info;
    }

    public File getMarkdownOut() {
        return markdownOut;
    }

    public void markdown(File out) {
        setMarkdownOut(out);
    }

    public void changelog(ChangelogInfo info) {
        setInfo(info);
    }

    @TaskAction
    private void invoke() {
        MarkdownChangelogGenerator gen = new MarkdownChangelogGenerator(info, markdownOut);
        try {
            gen.generate();
        } catch (IOException exc) {
            exc.printStackTrace();
            throw new UncheckedIOException(exc);
        }
    }
}
