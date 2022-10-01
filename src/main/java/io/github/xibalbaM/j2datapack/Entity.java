package io.github.xibalbaM.j2datapack;

public class Entity {

    private final String id;
    private String nbt = "{}";

    public Entity(String id) {

        if (id.contains(":")) {
            this.id = id;
        } else {
            this.id = "minecraft:" + id;
        }
    }

    public static Entity of(String id) {
        return new Entity(id);
    }

    public Entity with(String nbt) {
        this.nbt = nbt;
        return this;
    }

    public Entity with(Nbt nbt) {
        this.nbt = nbt.getNbt();
        return this;
    }

    public String getId() {

        return id;
    }

    public String getNbt() {

        return nbt;
    }
}
