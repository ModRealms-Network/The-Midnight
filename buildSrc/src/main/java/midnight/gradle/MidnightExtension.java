package midnight.gradle;

import groovy.lang.GroovyObjectSupport;
import org.gradle.api.Project;

public class MidnightExtension extends GroovyObjectSupport {
    protected final Project project;
    protected final ShadeRemapper shadeRemapper = new ShadeRemapper();

    public MidnightExtension(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void shade(String fromPackage, String toPackage) {
        shadeRemapper.addPackageRename(fromPackage.replace('.', '/'), toPackage.replace('.', '/'));
    }

    public ShadeRemapper getShadeRemapper() {
        return shadeRemapper;
    }
}
