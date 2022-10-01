package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.CustomCommand;
import io.github.xibalbaM.j2datapack.commands.FunctionCommand;
import io.github.xibalbaM.j2datapack.tags.TagType;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Log4j(topic = "j2datapack")
public class Namespace {

    private static Namespace currentNamespace;
    private static final List<Namespace> namespaces = new ArrayList<>();
    private static final Namespace minecraft = new Namespace("minecraft", null);

    private final String name;
    private final Datapack datapack;

    private final List<McFunction> functions = new ArrayList<>();
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<Tag> tags = new ArrayList<>();
    private final List<Scoreboard> scoreboards = new ArrayList<>();
    private final MultiValuedMap<CallOn, McFunction> calledFunctions = new ArrayListValuedHashMap<>();

    public Namespace(String name, Datapack datapack) {

        this.name = name;
        this.datapack = datapack;

        currentNamespace = this;
        namespaces.add(this);
    }

    public static Namespace of(String name, Datapack datapack) {

        return new Namespace(name, datapack);
    }

    public static Namespace getCurrentNamespace() {

        return currentNamespace;
    }

    public static Namespace getNamespace(String name) {

        currentNamespace = namespaces.stream().filter(namespace -> namespace.getName().equals(name)).findFirst().orElse(null);

        return currentNamespace;
    }

    public String getName() {

        return name;
    }

    public Namespace with(Recipe recipe) {

        recipe.namespace = this;
        recipes.add(recipe);

        currentNamespace = this;
        return this;
    }

    public Namespace with(McFunction function, CallOn... callOn) {

        function.namespace = this;
        functions.add(function);

        for (CallOn on : callOn) {

            calledFunctions.put(on, function);
        }

        currentNamespace = this;
        return this;
    }

    public Namespace with(Tag tag) {

        if (tag.isFromMinecraft() && !name.equals("minecraft")) {

            minecraft.with(tag);
            return this;
        }
        tag.namespace = this;
        tags.add(tag);

        currentNamespace = this;
        return this;
    }

    public Namespace with(Scoreboard scoreboard) {

        scoreboard.namespace = this;
        scoreboards.add(scoreboard);

        currentNamespace = this;
        return this;
    }

    public List<McFunction> getFunctions() {

        return functions;
    }

    public McFunction getFunction(String name) {

        return functions.stream().filter(function -> function.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Recipe> getRecipes() {

        return recipes;
    }

    public List<Tag> getTags() {

        return tags;
    }

    public List<Scoreboard> getScoreboards() {

        return scoreboards;
    }

    public Scoreboard getScoreboard(String name) {

        return scoreboards.stream().filter(scoreboard -> scoreboard.getName().equals(name)).findFirst().orElse(null);
    }

    public static Namespace getMinecraft() {

        return minecraft;
    }

    public void generate(Path dataDir) throws IOException {

        if (this != minecraft) {

            McFunction load = new McFunction("sys-load");
            for (Scoreboard scoreboard : scoreboards) {

                load.command(CustomCommand.of(scoreboard.generateContent()));
            }
            for (McFunction function : calledFunctions.get(CallOn.LOAD)) {

                load.command(FunctionCommand.of(function));
            }
            with(load);
            getMinecraft().with(Tag.minecraft(MinecraftTags.LOAD).with(getName(), load.getName()));

            McFunction uninstall = new McFunction("sys-uninstall");
            for (Scoreboard scoreboard : scoreboards) {

                uninstall.command(CustomCommand.of(scoreboard.generateUninstallContent()));

            }
            for (McFunction function : calledFunctions.get(CallOn.UNLOAD)) {

                uninstall.command(FunctionCommand.of(function));
            }
            with(uninstall);

            McFunction tick = new McFunction("sys-tick");
            for (McFunction function : calledFunctions.get(CallOn.TICK)) {

                tick.command(FunctionCommand.of(function));
            }
            with(tick);
            getMinecraft().with(Tag.minecraft(MinecraftTags.TICK).with(getName(), "sys-tick"));
        }



        dataDir = dataDir.resolve(name);
        Path functionsDir = dataDir.resolve("functions");
        Path advancementsDir = dataDir.resolve("advancements");
        Path recipesDir = dataDir.resolve("recipes");
        Path tagsDir = dataDir.resolve("tags");
        Path minecraftTagsDir = dataDir.resolve("tags");

        log.info("      Generating functions...");
        functionsDir.toFile().mkdirs();
        for (McFunction function : functions) {

            Path functionFile = functionsDir.resolve(function.getName() + ".mcfunction");
            log.info("         Generating " + functionFile.toAbsolutePath() + "...");
            Datapack.writeToFile(functionFile, function.generateContent());
            log.info("         Done");
        }
        log.info("      Done");

        log.info("      Generating recipes...");
        recipesDir.toFile().mkdirs();
        for (Recipe recipe : recipes) {

            Path recipeFile = recipesDir.resolve(recipe.getName() + ".json");
            log.info("         Generating " + recipeFile.toAbsolutePath() + "...");
            Datapack.writeToFile(recipeFile, recipe.generateContent());
            log.info("         Done");
        }
        log.info("      Done");

        log.info("      Generating tags...");
        tagsDir.toFile().mkdirs();
        for (TagType value : TagType.values()) {

            Path tagDir = tagsDir.resolve(value.getTagType());
            tagDir.toFile().mkdirs();
        }
        for (Tag tag : tags) {

            Path tagFile = tagsDir.resolve(tag.getType().getTagType() + "/" + tag.getName() + ".json");
            log.info("         Generating " + tagFile.toAbsolutePath() + "...");
            Datapack.writeToFile(tagFile, tag.generateContent());
            log.info("         Done");
        }
        log.info("      Done");
    }

    public Datapack getDatapack() {

        return datapack;
    }
}