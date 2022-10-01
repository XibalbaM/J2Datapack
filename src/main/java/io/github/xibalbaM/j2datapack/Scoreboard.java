package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.CustomCommand;
import io.github.xibalbaM.j2datapack.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard extends NamespacePart {

    private String type = Type.DUMMY.getType();
    private String displayName = "";
    private final List<String> fakePlayers = new ArrayList<>();

    public Scoreboard(String name) {

        super(name);
    }

    public static Scoreboard of(String name) {

        return new Scoreboard(name);
    }

    public static Scoreboard of(String name, Type type) {

        return of(name).with(type);
    }

    public Scoreboard with(Type type) {
        this.type = type.getType();
        return this;
    }

    public Scoreboard withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Scoreboard withType(String type) {
        this.type = type;
        return this;
    }

    public Scoreboard with(String name) {
        fakePlayers.add(name);
        return this;
    }

    public Scoreboard with(String... names) {
        for (String name : names) {
            fakePlayers.add(name);
        }
        return this;
    }

    public ICommand set(String target, int value) {
        return new CustomCommand("scoreboard players set " + target + " " + getName() + " " + value);
    }

    public ICommand add(String target, int value) {
        return new CustomCommand("scoreboard players add " + target + " " + getName() + " " + value);
    }

    public ICommand remove(String target, int value) {
        return new CustomCommand("scoreboard players remove " + target + " " + getName() + " " + value);
    }

    public ICommand reset(String target) {
        return new CustomCommand("scoreboard players reset " + target + " " + getName());
    }

    public ICommand operation(String target, String source, String sourceObjective, Operation operation) {
        return new CustomCommand("scoreboard players operation " + target + " " + getName() + " " + operation.getOperation() + " " + source + " " + sourceObjective);
    }

    public ICommand enable(String target) {

        return new CustomCommand("scoreboard players enable " + target + " " + getName());
    }

    public List<String> getFakePlayers() {
        return fakePlayers;
    }

    public String getType() {
        return type;
    }

    @Override
    public String generateContent() {

        StringBuilder builder = new StringBuilder();

        builder.append("scoreboard objectives add ").append(getName()).append(" ").append(type);
        if (!displayName.isEmpty()) {
            builder.append(" ").append(displayName);
        }
        builder.append("\n");

        for (String fakePlayer : fakePlayers) {
            builder.append("scoreboard players add ").append(fakePlayer).append(" ").append(getName()).append(" 0\n");
        }

        return builder.toString();
    }

    public String generateUninstallContent() {

        StringBuilder builder = new StringBuilder();

        builder.append("scoreboard objectives remove ").append(getName());
        if (!displayName.isEmpty()) {
            builder.append(" ").append(displayName);
        }
        builder.append("\n");

        return builder.toString();
    }

    public enum Type {

        DUMMY("dummy"),
        TRIGGER("trigger"),
        DEATH_COUNTER("deathCount"),
        PLAYER_KILL_COUNTER("playerKillCount"),
        TOTAL_KILL_COUNTER("totalKillCount"),
        HEALTH("health"),
        EXPERIENCE("xp"),
        LEVEL("level"),
        FOOD("food"),
        AIR("air"),
        ARMOR("armor");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum Operation {

        ADD("+="),
        SUBTRACT("-="),
        MULTIPLY("*="),
        DIVIDE("/="),
        MODULO("%="),
        ASSIGN("="),
        MIN("<"),
        MAX(">"),
        SWAP("><");

        private final String operation;

        Operation(String operation) {
            this.operation = operation;
        }

        public String getOperation() {
            return operation;
        }
    }
}
