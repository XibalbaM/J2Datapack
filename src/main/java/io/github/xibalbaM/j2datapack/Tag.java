package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.tags.TagType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tag extends NamespacePart {

    private final TagType type;
    private final boolean replace, fromMinecraft;
    private final List<String> values = new ArrayList<>();

    public Tag(String name, TagType type, boolean replace, boolean fromMinecraft) {

        super(name);

        this.type = type;
        this.replace = replace;
        this.fromMinecraft = fromMinecraft;
    }

    @Contract("_, _ -> new")
    public static @NotNull Tag custom(String name, TagType type) {
        return new Tag(name, type, false, false);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Tag custom(String name, TagType type, boolean replace) {
        return new Tag(name, type, replace, false);
    }

    @Contract("_, _ -> new")
    public static @NotNull Tag minecraft(String name, TagType type) {
        return new Tag(name, type, false, true);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Tag minecraft(String name, TagType type, boolean replace) {
        return new Tag(name, type, replace, true);
    }

    public static @NotNull Tag minecraft(MinecraftTags tag, boolean replace) {
        return new Tag(tag.getName(), tag.getTagType(), replace, true);
    }

    public static @NotNull Tag minecraft(MinecraftTags tag) {
        return new Tag(tag.getName(), tag.getTagType(), false, true);
    }

    public Tag with(String value) {
        values.add("minecraft:" + value);
        return this;
    }

    public Tag with(String @NotNull ... values) {
        for (String value : values) {
            with(value);
        }
        return this;
    }

    public Tag with(String namespace, String value) {
        values.add(namespace + ":" + value);
        return this;
    }

    public Tag with(String namespace, String @NotNull ... values) {
        for (String value : values) {
            with(namespace, value);
        }
        return this;
    }

    public Tag with(NamespacePart part) {
        values.add(part.getNamespace().getName() + ":" + part.getName());
        return this;
    }

    public TagType getType() {

        return type;
    }

    @Override
    public String generateContent() {

        StringBuilder builder = new StringBuilder("{\n");

        builder.append("    \"replace\": ").append(replace ? "true" : "false").append(",\n");


        builder.append("    \"values\": [\n");
        for (String value : values) {
            builder.append("        \"").append(value).append("\",\n");
        }
        builder.delete(builder.length() - 2, builder.length() - 1);
        builder.append("    ]\n}");

        return builder.toString();
    }

    public boolean isFromMinecraft() {

        return fromMinecraft;
    }
}
