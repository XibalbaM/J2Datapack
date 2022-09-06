package io.github.xibalbaM.j2datapack;

public abstract class NamespacePart {

    protected Namespace namespace;
    protected String name;

    public NamespacePart(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Namespace getNamespace() {

        return namespace;
    }

    public abstract String generateFileContent();
}
