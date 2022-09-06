package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.FunctionCommand;
import io.github.xibalbaM.j2datapack.commands.SayCommand;
import io.github.xibalbaM.j2datapack.tags.TagType;
import org.junit.Test;

import java.io.IOException;

public class DatapackTest {

    @Test
    public void test() throws IOException {

        //TODO : more commands, default tags, default functions, recipes, advancements, loot tables, etc.

        Datapack datapack = new Datapack("test", "test", PackFormat.FORMAT_1_19_to_1_19_2);
        datapack.withNamespace(Namespace.of("test", datapack)
                .with(McFunction.of("test")
                        .command(SayCommand.message("test"))
                )
                .with(McFunction.of("test")
                        .command(FunctionCommand.of(McFunction.of("test")))
                )
                .with(
                        Tag.custom("test", TagType.BLOCKS)
                                .with("test")
                                .with("test", "test")
                )
        );
        datapack.generate();
    }
}
