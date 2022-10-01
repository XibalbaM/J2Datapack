package io.github.xibalbaM.j2datapack.commands;

import io.github.xibalbaM.j2datapack.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteCommand implements ICommand {

    private final List<Argument> arguments = new ArrayList<>();

    public ExecuteCommand with(Argument argument) {

        arguments.add(argument);
        return this;
    }

    public static ExecuteCommand of() {

        return new ExecuteCommand();
    }

    public ExecuteCommand run(ICommand command) {

        return with(new Argument("run", command.getCommand()));
    }

    public ExecuteCommand align(String axis) {

        for (char c : axis.toCharArray()) {

            if (c != 'x' && c != 'y' && c != 'z') {
                throw new IllegalArgumentException("Invalid axis: " + c);
            }
        }

        return with(new Argument("align", axis));
    }

    public ExecuteCommand anchored(String anchor) {

        Assert.assertTrue("Anchor argument for anchored must be feet or eyes", Arrays.asList("feet", "eyes").contains(anchor));

        return with(new Argument("anchored", anchor));
    }

    public ExecuteCommand as(Selector selector) {

        return with(new Argument("as", selector.getSelector()));
    }

    public ExecuteCommand at(Selector selector) {

        return with(new Argument("at", selector.getSelector()));
    }

    public ExecuteCommand facing(Position position) {

        return with(new Argument("facing", position.toString()));
    }

    public ExecuteCommand facing(Selector selector, String anchor) {

        Assert.assertTrue("Anchor argument for facing must be feet or eyes", Arrays.asList("feet", "eyes").contains(anchor));

        return with(new Argument("facing", "entity", selector.getSelector(), anchor));
    }

    public ExecuteCommand ifBlock(Position position, Block block) {

        return with(new Argument("if", "block " + position.getPosition() + " " + block.getId()));
    }

    public ExecuteCommand ifBlock(Position position, Tag block) {

        return with(new Argument("if", "block " + position.getPosition() + " " + block.getId()));
    }

    public ExecuteCommand ifBlocks(Position start, Position end, Position destination, String mode) {

        Assert.assertTrue("Mode argument for ifBlocks must be all or masked", Arrays.asList("all", "masked").contains(mode));

        return with(new Argument("if", "blocks " + start.getPosition() + " " + end.getPosition() + " " + destination.getPosition() + " " + mode));
    }

    public ExecuteCommand ifData(Position position, String path) {

        return with(new Argument("if", "data blocks " + position.getPosition() + " " + path));
    }

    public ExecuteCommand ifData(Selector selector, String path) {

        Assert.assertTrue("Selector argument for ifData must be a single entity", selector.isSingleEntity());

        return with(new Argument("if", "data entity " + selector.getSelector() + " " + path));
    }

    public ExecuteCommand ifData(String storage, String path) {

        return with(new Argument("if", "data storage " + storage + " " + path));
    }

    public ExecuteCommand ifEntity(Selector selector) {

        return with(new Argument("if", "entity " + selector.getSelector()));
    }

    public ExecuteCommand ifPredicate(Predicate predicate) {

        return with(new Argument("if", "predicate " + predicate.getId()));
    }

    public ExecuteCommand ifScore(Selector selector, Scoreboard scoreboard, Scoreboard.Operation operation, Selector otherSelector, Scoreboard scoreboard2) {

        return with(new Argument("if", "score", selector.getSelector(), scoreboard.getId(), operation.getOperation(), otherSelector.getSelector(), scoreboard2.getId()));
    }

    public ExecuteCommand ifScore(Selector selector, String scoreboard, Scoreboard.Operation operation, Selector otherSelector, String scoreboard2) {

        return with(new Argument("if", "score", selector.getSelector(), scoreboard, operation.getOperation(), otherSelector.getSelector(), scoreboard2));
    }

    public ExecuteCommand ifScoreMatches(Selector selector, Scoreboard scoreboard, Range range) {

        return with(new Argument("if", "score", selector.getSelector(), scoreboard.getId(), "matches", range.getRange()));
    }

    public ExecuteCommand ifScoreMatches(Selector selector, String scoreboard, String range) {

        return with(new Argument("if", "score", selector.getSelector(), scoreboard, "matches", range));
    }

    public ExecuteCommand in(String dimension) {

        if (!dimension.contains(":")) {
            dimension = "minecraft:" + dimension;
        }

        return with(new Argument("in", dimension));
    }

    public ExecuteCommand in(Dimension dimension) {

        return with(new Argument("in", dimension.getId()));
    }

    public ExecuteCommand positioned(Position position) {

        return with(new Argument("positioned", position.toString()));
    }

    public ExecuteCommand positioned(Selector selector) {

        return with(new Argument("positioned", "entity", selector.getSelector()));
    }

    public ExecuteCommand rotated(Selector position) {

        return with(new Argument("rotated", "as", position.toString()));
    }

    public ExecuteCommand storeBlock(String type, Position position, String path, String dataType, String scale) {

        Assert.assertTrue("Type argument for storeBlock must be result or success", Arrays.asList("result", "success").contains(type));
        Assert.assertTrue("Data type argument for storeBlock must be byte, double, float, int, long or short", List.of("byte", "double", "float", "int", "long", "short").contains(dataType));

        return with(new Argument("store", type, "block " + position.getPosition(), path, dataType, scale));
    }

    public ExecuteCommand storeBossbar(String type, String bossbar, boolean max) {

        Assert.assertTrue("Type argument for storeBossbar must be result or success", Arrays.asList("result", "success").contains(type));

        return with(new Argument("store", type, "bossbar " + bossbar, max ? "max" : "value"));
    }

    public ExecuteCommand storeEntity(String type, Selector selector, String path, String dataType, String scale) {

        Assert.assertTrue("Type argument for storeEntity must be result or success", Arrays.asList("result", "success").contains(type));
        Assert.assertTrue("Data type argument for storeEntity must be byte, double, float, int, long or short", List.of("byte", "double", "float", "int", "long", "short").contains(dataType));
        Assert.assertTrue("Selector argument for storeEntity must be a single entity", selector.isSingleEntity());

        return with(new Argument("store", type, "entity " + selector.getSelector(), path, dataType, scale));
    }

    public ExecuteCommand storeScore(String type, Selector selector, Scoreboard scoreboard) {

        Assert.assertTrue("Type argument for storeScore must be result or success", Arrays.asList("result", "success").contains(type));

        return with(new Argument("store", type, "score " + selector.getSelector(), scoreboard.getId()));
    }

    public ExecuteCommand storeScore(String type, Selector selector, String scoreboard) {

        Assert.assertTrue("Type argument for storeScore must be result or success", Arrays.asList("result", "success").contains(type));

        return with(new Argument("store", type, "score " + selector.getSelector(), scoreboard));
    }

    public ExecuteCommand storeStorage(String type, Storage storage, String path, String dataType, String scale) {

        Assert.assertTrue("Type argument for storeStorage must be result or success", Arrays.asList("result", "success").contains(type));
        Assert.assertTrue("Data type argument for storeStorage must be byte, double, float, int, long or short", List.of("byte", "double", "float", "int", "long", "short").contains(dataType));

        return with(new Argument("store", type, "storage " + storage, path, dataType, scale));
    }

    public ExecuteCommand storeStorage(String type, String storage, String path, String dataType, String scale) {

        Assert.assertTrue("Type argument for storeStorage must be result or success", Arrays.asList("result", "success").contains(type));
        Assert.assertTrue("Data type argument for storeStorage must be byte, double, float, int, long or short", List.of("byte", "double", "float", "int", "long", "short").contains(dataType));

        return with(new Argument("store", type, "storage " + storage, path, dataType, scale));
    }

    public ExecuteCommand unlessBlock(Position position, Block block) {

        return with(new Argument("unless", "block " + position.getPosition() + " " + block.getId()));
    }

    public ExecuteCommand unlessBlock(Position position, Tag block) {

        return with(new Argument("unless", "block " + position.getPosition() + " " + block.getId()));
    }

    public ExecuteCommand unlessBlocks(Position start, Position end, Position destination, String mode) {

        Assert.assertTrue("Mode argument for unless blocks must be all or masked", Arrays.asList("all", "masked").contains(mode));

        return with(new Argument("unless", "blocks " + start.getPosition() + " " + end.getPosition() + " " + destination.getPosition() + " " + mode));
    }

    public ExecuteCommand unlessData(Position position, String path) {

        return with(new Argument("unless", "data blocks " + position.getPosition() + " " + path));
    }

    public ExecuteCommand unlessData(Selector selector, String path) {

        Assert.assertTrue("Selector argument for unless aata must be a single entity", selector.isSingleEntity());

        return with(new Argument("unless", "data entity " + selector.getSelector() + " " + path));
    }

    public ExecuteCommand unlessData(String storage, String path) {

        return with(new Argument("unless", "data storage " + storage + " " + path));
    }

    public ExecuteCommand unlessEntity(Selector selector) {

        return with(new Argument("unless", "entity " + selector.getSelector()));
    }

    public ExecuteCommand unlessPredicate(Predicate predicate) {

        return with(new Argument("unless", "predicate " + predicate.getId()));
    }

    public ExecuteCommand unlessScore(Selector selector, Scoreboard scoreboard, Scoreboard.Operation operation, Selector otherSelector, Scoreboard scoreboard2) {

        return with(new Argument("unless", "score", selector.getSelector(), scoreboard.getId(), operation.getOperation(), otherSelector.getSelector(), scoreboard2.getId()));
    }

    public ExecuteCommand unlessScore(Selector selector, String scoreboard, Scoreboard.Operation operation, Selector otherSelector, String scoreboard2) {

        return with(new Argument("unless", "score", selector.getSelector(), scoreboard, operation.getOperation(), otherSelector.getSelector(), scoreboard2));
    }

    public ExecuteCommand unlessScoreMatches(Selector selector, Scoreboard scoreboard, Range range) {

        return with(new Argument("unless", "score", selector.getSelector(), scoreboard.getId(), "matches", range.getRange()));
    }

    public ExecuteCommand unlessScoreMatches(Selector selector, String scoreboard, String range) {

        return with(new Argument("unless", "score", selector.getSelector(), scoreboard, "matches", range));
    }

    @Override
    public String getCommand() {

        StringBuilder builder = new StringBuilder();

        builder.append("execute");

        if (arguments.isEmpty()) {
            throw new IllegalStateException("Execute command must have at least one argument");
        }

        for (Argument argument : arguments) {
            builder.append(" ").append(argument.get());
        }

        return builder.toString();
    }

    @Override
    public boolean isAvailableFor(PackFormat version) {

        return false;
    }

    public static class Argument {

        private final String name;
        private final List<String> values = new ArrayList<>();

        public Argument(String name, String... values) {

            this.name = name;
            this.values.addAll(Arrays.asList(values));
        }

        public static Argument of(String name) {
            return new Argument(name);
        }

        public Argument with(String value) {
            values.add(value);
            return this;
        }

        public String getName() {
            return name;
        }

        public List<String> getValues() {
            return values;
        }

        public String get() {

            StringBuilder builder = new StringBuilder();

            builder.append(name);

            for (String value : values) {
                builder.append(" ").append(value);
            }

            return builder.toString();
        }
    }
}