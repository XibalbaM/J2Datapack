package io.github.xibalbaM.j2datapack.tags;

public enum TagType {

    BLOCKS,
    ITEMS,
    ENTITY_TYPES,
    FLUIDS,
    FUNCTIONS,
    GAME_EVENTS;

    public String getTagType() {
        return this.name().toLowerCase();
    }
}
