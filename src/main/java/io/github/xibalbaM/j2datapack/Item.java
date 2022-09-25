package io.github.xibalbaM.j2datapack;

public class Item {

    private final String id;
    private String nbt = "{}";

    public Item(String id) {

        if (id.contains(":")) {
            this.id = id;
        } else {
            this.id = "minecraft:" + id;
        }
    }

    public static Item of(String id) {
        return new Item(id);
    }

    public Item withNbt(String nbt) {
        this.nbt = nbt;
        return this;
    }

    public String getId() {

        return id;
    }

    public String getNbt() {

        return nbt;
    }
}