package io.github.xibalbaM.j2datapack;

import java.util.HashMap;
import java.util.Map;

public class Block {

    private final String id;
    private String nbt = "{}";
    private final Map<String, String> states = new HashMap<>();

    public Block(String id) {

        if (id.contains(":")) {
            this.id = id;
        } else {
            this.id = "minecraft:" + id;
        }
    }

    public static Block of(String id) {
        return new Block(id);
    }

    public Block with(String nbt) {
        this.nbt = nbt;
        return this;
    }

    public Block with(Nbt nbt) {
        this.nbt = nbt.getNbt();
        return this;
    }

    public Block with(String state, String value) {
        states.put(state, value);
        return this;
    }

    public Block with(String state, Object value) {
        states.put(state, String.valueOf(value));
        return this;
    }

    public String getId() {

        return id;
    }

    public String getNbt() {

        return nbt;
    }

    public String getStates() {

        StringBuilder builder = new StringBuilder("[");
        for (Map.Entry<String, String> entry : states.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("]");
        return builder.toString();
    }
}