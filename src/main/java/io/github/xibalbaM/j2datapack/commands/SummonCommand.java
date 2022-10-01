package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.Entity;
import io.github.xibalbaM.j2datapack.PackFormat;
import io.github.xibalbaM.j2datapack.Position;

public class SummonCommand implements ICommand {

    private Entity entity;
    private Position position = Position.of(Position.Type.RELATIVE);

    public SummonCommand(Entity entity) {
        this.entity = entity;
    }

    public static SummonCommand of(Entity entity) {
        return new SummonCommand(entity);
    }

    public SummonCommand with(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public String getCommand() {
        return "summon " + entity.getId() + " " + position.getPosition() + " " + entity.getNbt();
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {
        return true;
    }
}
