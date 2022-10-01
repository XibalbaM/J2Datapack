package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.Item;
import io.github.xibalbaM.j2datapack.PackFormat;
import io.github.xibalbaM.j2datapack.Selector;

public class ClearCommand implements ICommand {

    private Selector target = Selector.of();
    private String item;
    private int count = -1;
    private String nbt;

    private ClearCommand with(Selector target) {

        this.target = target;
        return this;
    }

    public ClearCommand with(Item item) {

        this.item = item.getId();
        this.nbt = item.getNbt();
        return this;
    }

    public ClearCommand with(String item) {

        this.item = item;
        return this;
    }

    public ClearCommand withNbt(String nbt) {

        this.nbt = nbt;
        return this;
    }

    public ClearCommand with(int count) {

        this.count = count;
        return this;
    }

    @Override
    public String getCommand() {

        StringBuilder command = new StringBuilder("clear ").append(target.getSelector());

        if (item != null) {
            command.append(" ").append(item);

            if (nbt != null) {
                command.append(nbt);
            }

            if (count != -1) {
                command.append(" ").append(count);
            }
        }

        return command.toString();
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return true;
    }
}
