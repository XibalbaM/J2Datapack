package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.Item;
import io.github.xibalbaM.j2datapack.PackFormat;
import io.github.xibalbaM.j2datapack.Selector;

public class GiveCommand implements ICommand {

    private Selector target = Selector.of();
    private Item item = Item.of("stone");
    private int count = 1;

    private GiveCommand with(Selector target) {

        this.target = target;
        return this;
    }

    public GiveCommand with(Item item) {

        this.item = item;
        return this;
    }

    public GiveCommand with(int count) {

        this.count = count;
        return this;
    }

    public static GiveCommand of(Item item) {

        return new GiveCommand().with(item);
    }

    public static GiveCommand of(Item item, int count) {

        return new GiveCommand().with(item).with(count);
    }

    public static GiveCommand to(Selector target, Item item) {

        return new GiveCommand().with(target).with(item);
    }

    public static GiveCommand to(Selector target, Item item, int count) {

        return new GiveCommand().with(target).with(item).with(count);
    }

    @Override
    public String getCommand() {

        return "give " + target.getSelector() + " " + item.getId() + item.getNbt() + " " + count;
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return false;
    }
}
