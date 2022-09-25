package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.PackFormat;

import java.util.Collections;
import java.util.List;

public class CustomCommand implements ICommand {

    private final String command;
    private final List<PackFormat> packFormats;

    public CustomCommand(String command, PackFormat... packFormats) {

        this.command = command;
        if (packFormats.length == 0) {

            this.packFormats = Collections.singletonList(PackFormat.ALL);
        } else {

            this.packFormats = List.of(packFormats);
        }
    }

    @Override
    public String getCommand() {

        return command;
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return packFormats.contains(PackFormat.ALL) || packFormats.contains(version);
    }
}
