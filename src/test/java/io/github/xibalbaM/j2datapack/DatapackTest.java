package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.SayCommand;
import org.junit.Test;

import java.io.IOException;

public class DatapackTest {

    @Test
    public void test() throws IOException {

        //TODO : more commands, default functions, recipes, advancements, loot tables, etc.

        Datapack datapack = new Datapack("test", "1.16.5", PackFormat.FORMAT_1_19_to_1_19_2);

        datapack.withNamespace(Namespace.of("test", datapack)
                .with(McFunction.of("test")
                        .command(SayCommand.message("Hello world!"))
                )
        );

        datapack.generate();
    }
}