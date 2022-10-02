package io.github.xibalbaM.j2datapack;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j(topic = "j2datapack")
public class Datapack {

    public static final String DEFAULT_MINECRAFT_SAVE_PATH = System.getProperty("user.home") + "/AppData/Roaming/.minecraft/saves/";

    private final String name, description;
    private final int packFormat;
    private final List<Namespace> namespaces = new ArrayList<>();
    //TODO filter

    public Datapack(String name, String description, @NotNull PackFormat packFormat) {

        this.name = name;
        this.description = description;
        this.packFormat = packFormat.getFormat();
        this.namespaces.add(Namespace.getMinecraft());
    }

    public Datapack withNamespace(Namespace namespace) {

        namespaces.add(namespace);
        return this;
    }

    public List<Namespace> getNamespaces() {

        return namespaces;
    }

    public void generate() throws IOException {

        generate(Paths.get("/datapacks"));
    }


    public void generate(Path datapackDir) throws IOException {

        Path baseDir = datapackDir.resolve(name);
        if (baseDir.toFile().exists()) {

            log.info("Cleaning datapack directory...");
            deleteDirectory(baseDir.toFile());
            log.info("Done");
        }
        baseDir.toFile().mkdirs();

        Path packMcMeta = baseDir.resolve("pack.mcmeta");
        Path contentDir = baseDir.resolve("data");

        log.info("Generating pack.mcmeta...");
        writeToFile(packMcMeta, generatePackMcMeta());
        log.info("Done");

        log.info("Generating data folder...");
        contentDir.toFile().mkdirs();
        Namespace minecraft = null;
        for (Namespace namespace : namespaces) {

            if (namespace.getName().equals("minecraft")) {

                minecraft = namespace;
                continue;
            }

            log.info("  Generating namespace " + namespace.getName() + "...");
            namespace.generate(contentDir);
            log.info("  Done");
        }
        if (minecraft != null) {

            log.info("  Generating namespace " + minecraft.getName() + "...");
            minecraft.generate(contentDir);
            log.info("  Done");
        }
        log.info("Done");
    }

    @Contract(pure = true)
    private @NotNull String generatePackMcMeta() {

        return "{\n" +
                "  \"pack\": {\n" +
                "    \"pack_format\": " + packFormat + ",\n" +
                "    \"description\": \"" + description + "\"\n" +
                "  }\n" +
                "}";
    }

    static void writeToFile(@NotNull Path path, String content) throws IOException {

        if (!path.toFile().getParentFile().exists()) {

            path.toFile().getParentFile().mkdirs();
        }
        path.toFile().createNewFile();
        @Cleanup
        FileWriter fileWriter = new FileWriter(path.toFile());
        fileWriter.write(content);
    }

    private static boolean deleteDirectory(@NotNull File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}