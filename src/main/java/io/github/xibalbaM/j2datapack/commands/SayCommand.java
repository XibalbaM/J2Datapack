package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.PackFormat;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SayCommand implements ICommand {

    private String message;

    public static SayCommand message(String message) {

        return new SayCommand(message);
    }

    @Override
    public String getCommand() {
        return "say " + message;
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return true;
    }
}
