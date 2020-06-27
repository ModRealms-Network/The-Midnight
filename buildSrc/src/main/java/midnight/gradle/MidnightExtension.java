package midnight.gradle;

import groovy.lang.GroovyObjectSupport;
import org.gradle.api.Project;
import org.gradle.internal.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MidnightExtension extends GroovyObjectSupport {
    protected final Project project;
    protected final ShadeRemapper shadeRemapper = new ShadeRemapper();
    protected final List<Pair<Object, Object>> constants = new ArrayList<>();

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

    public List<Pair<Object, Object>> getConstants() {
        return Collections.unmodifiableList(constants);
    }

    public void constant(Object name, Object value) {
        constants.add(Pair.of(name, value));
    }
}
