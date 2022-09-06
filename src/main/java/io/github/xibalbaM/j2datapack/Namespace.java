package io.github.xibalbaM.j2datapack;

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

    private final String name;
    private final Datapack datapack;

    private final List<Advancement> advancements = new ArrayList<>();
    private final List<McFunction> functions = new ArrayList<>();
    private final List<Recipe> recipes = new ArrayList<>();
    private final List<Tag> tags = new ArrayList<>();
    private final MultiValuedMap<CallOn, McFunction> calledFunctions = new ArrayListValuedHashMap<>();

    public Namespace(String name, Datapack datapack) {

        this.name = name;
        this.datapack = datapack;
    }

    public static Namespace of(String name, Datapack datapack) {

        return new Namespace(name, datapack);
    }

    public String getName() {

        return name;
    }

    public Namespace with(Advancement advancement) {

        advancement.namespace = this;
        advancements.add(advancement);
        return this;
    }

    public Namespace with(Recipe recipe) {

        recipe.namespace = this;
        recipes.add(recipe);
        return this;
    }

    public Namespace with(McFunction function, CallOn... callOn) {

        function.namespace = this;
        functions.add(function);

        for (CallOn on : callOn) {

            calledFunctions.put(on, function);
        }

        return this;
    }

    public Namespace with(Tag tag) {

        tag.namespace = this;
        tags.add(tag);
        return this;
    }

    public List<Advancement> getAdvancements() {

        return advancements;
    }

    public List<McFunction> getFunctions() {

        return functions;
    }

    public List<Recipe> getRecipes() {

        return recipes;
    }

    public List<Tag> getTags() {

        return tags;
    }

    public void generate(Path dataDir) throws IOException {

        Path functionsDir = dataDir.resolve("functions");
        Path advancementsDir = dataDir.resolve("advancements");
        Path recipesDir = dataDir.resolve("recipes");
        Path tagsDir = dataDir.resolve("tags");

        log.info("      Generating functions...");
        functionsDir.toFile().mkdirs();
        for (McFunction function : functions) {

            Path functionFile = functionsDir.resolve(function.getName() + ".mcfunction");
            log.info("         Generating " + functionFile.toAbsolutePath() + "...");
            datapack.writeToFile(functionFile, function.generateFileContent());
            log.info("         Done");
        }
        log.info("      Done");

        log.info("      Generating advancements...");
        advancementsDir.toFile().mkdirs();
        for (Advancement advancement : advancements) {

            Path advancementFile = advancementsDir.resolve(advancement.getName() + ".json");
            log.info("         Generating " + advancementFile.toAbsolutePath() + "...");
            datapack.writeToFile(advancementFile, advancement.generateFileContent());
            log.info("         Done");
        }
        log.info("      Done");

        log.info("      Generating recipes...");
        recipesDir.toFile().mkdirs();
        for (Recipe recipe : recipes) {

            Path recipeFile = recipesDir.resolve(recipe.getName() + ".json");
            log.info("         Generating " + recipeFile.toAbsolutePath() + "...");
            datapack.writeToFile(recipeFile, recipe.generateFileContent());
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
            datapack.writeToFile(tagFile, tag.generateFileContent());
            log.info("         Done");
        }
        log.info("      Done");
    }
}
