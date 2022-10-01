package io.github.xibalbaM.j2datapack;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

public class DatapackTest {

    @Test
    public void test() throws IOException {

        //TODO: Predicates, dimensions
        //TODO : more commands, default functions, recipes, advancements, loot tables, Setblock facing position, etc.

        Datapack datapack = new Datapack("test", "Test datapack", PackFormat.FORMAT_1_19_to_1_19_2);

        Namespace namespace = new Namespace("test", datapack);
        datapack.withNamespace(namespace);

        namespace.with(
                new McFunction("test").command(Position.of(Position.Type.RELATIVE).dataRemove("Items"))
        );

        datapack.generate(Path.of(Datapack.DEFAULT_MINECRAFT_SAVE_PATH + "/J2Datapack/datapacks"));
    }
}