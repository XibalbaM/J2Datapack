package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.PackFormat;

public interface ICommand {

    public String getCommand();

    public boolean isAvailableFor(PackFormat version);
}
