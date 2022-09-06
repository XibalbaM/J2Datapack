package io.github.xibalbaM.j2datapack;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j(topic = "j2datapack")
public class Datapack {


    private final String name, description;
    private final int packFormat;
    private final List<Namespace> namespaces = new ArrayList<>();
    //TODO filter

    public Datapack(String name, String description, PackFormat packFormat) {

        this.name = name;
        this.description = description;
        this.packFormat = packFormat.getFormat();
    }

    public void withNamespace(Namespace namespace) {

        namespaces.add(namespace);
    }

    public List<Namespace> getNamespaces() {

        return namespaces;
    }

    public void generate() throws IOException {

        Path baseDir = Paths.get("datapacks/" + this.name);
        if (baseDir.toFile().exists()) {

            log.info("Cleaning datapack directory...");
            baseDir.toFile().delete();
            log.info("Done");
        }
        baseDir.toFile().mkdirs();

        Path packMcMeta = baseDir.resolve("pack.mcmeta");
        Path contentDir = baseDir.resolve("data/" + this.name);

        log.info("Generating pack.mcmeta...");
        writeToFile(packMcMeta, generatePackMcMeta());
        log.info("Done");

        log.info("Generating data folder...");
        contentDir.toFile().mkdirs();
        for (Namespace namespace : namespaces) {

            log.info("  Generating namespace " + namespace.getName() + "...");
            namespace.generate(contentDir);
            log.info("  Done");
        }
        log.info("Done");
    }

    private String generatePackMcMeta() {

        return "{\n" +
                "  \"pack\": {\n" +
                "    \"pack_format\": " + packFormat + ",\n" +
                "    \"description\": \"" + description + "\"\n" +
                "  }\n" +
                "}";
    }

    void writeToFile(Path path, String content) throws IOException {

        path.toFile().createNewFile();
        @Cleanup
        FileWriter fileWriter = new FileWriter(path.toFile());
        fileWriter.write(content);
    }
}