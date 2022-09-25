package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.McFunction;
import io.github.xibalbaM.j2datapack.PackFormat;
import org.jetbrains.annotations.NotNull;

public class FunctionCommand implements ICommand {

    private final String function;

    public FunctionCommand(@NotNull McFunction function) {
        this.function = function.getNamespace().getName() + ":" + function.getName();
    }

    public static FunctionCommand of(@NotNull McFunction function) {
        return new FunctionCommand(function);
    }

    @Override
    public String getCommand() {
        return "function " + function;
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return true;
    }
}
