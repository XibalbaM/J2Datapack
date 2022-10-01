package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.Block;
import io.github.xibalbaM.j2datapack.PackFormat;
import io.github.xibalbaM.j2datapack.Position;

public class SetblockCommand implements ICommand {

    private Position position = Position.of(Position.Type.RELATIVE);
    private Block block;
    private String mode = "replace";

    public SetblockCommand(Block block) {

        this.block = block;
    }

    public static SetblockCommand of(Block block) {
        return new SetblockCommand(block);
    }

    public SetblockCommand with(Position position) {
        this.position = position;
        return this;
    }

    public SetblockCommand with(String mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public String getCommand() {

        return "setblock " + position.getPosition() + " " + block.getId() + block.getStates() + block.getNbt() + " " + mode;
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return true;
    }
}
