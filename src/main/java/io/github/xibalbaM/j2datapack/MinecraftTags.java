package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.tags.TagType;

public enum MinecraftTags {

    TICK(TagType.FUNCTIONS, "tick"),
    LOAD(TagType.FUNCTIONS, "load"),
    MINEABLE_AXE(TagType.BLOCKS, "mineable/axe"),
    MINEABLE_PICKAXE(TagType.BLOCKS, "mineable/pickaxe"),
    MINEABLE_SHOVEL(TagType.BLOCKS, "mineable/shovel"),
    MINEABLE_HOE(TagType.BLOCKS, "mineable/hoe");

    private final TagType tagType;
    private final String name;

    MinecraftTags(TagType type, String name) {
        this.tagType = type;
        this.name = name;
    }

    public TagType getTagType() {

        return tagType;
    }

    public String getName() {

        return name;
    }
}