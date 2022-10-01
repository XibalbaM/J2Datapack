package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.CustomCommand;
import io.github.xibalbaM.j2datapack.commands.ICommand;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Selector {

    private Base base = Base.SELF;
    private final List<SelectorParam> params = new ArrayList<>();

    public Selector(Base base, SelectorParam... params) {

        this.base = base;
        this.params.addAll(Arrays.asList(params));
    }

    public static Selector of(Base base, SelectorParam... params) {

        return new Selector(base, params);
    }

    public static Selector of(SelectorParam... params) {

        return new Selector(Base.SELF, params);
    }

    public String getSelector() {

        StringBuilder builder = new StringBuilder(base.getSelector());
        if (!params.isEmpty()) {
            builder.append("[");
            for (int i = 0; i < params.size(); i++) {
                builder.append(params.get(i).get());
                if (i < params.size() - 1) {
                    builder.append(",");
                }
            }
            builder.append("]");
        }
        return builder.toString();
    }

    public boolean isSingleEntity() {

        return base == Base.SELF || base == Base.NEAREST_PLAYER || base == Base.RANDOM_PLAYER ||
               !params.stream().filter(p -> p.key == "limit" && p.value == "1").collect(Collectors.toList()).isEmpty();
    }
    
    
    public ICommand dataGet(String path, int scale) {

        return new CustomCommand("data get entity " + getSelector() + " " + path + " " + scale);
    }

    public ICommand dataRemove(String path) {

        return new CustomCommand("data remove entity " + getSelector() + " " + path);
    }

    public ICommand dataMerge(String path, String nbt) {

        return new CustomCommand("data merge entity " + getSelector() + " " + path + " " + nbt);
    }

    public ICommand dataMerge(String path, Nbt nbt) {

        return new CustomCommand("data merge entity " + getSelector() + " " + path + " " + nbt.getNbt());
    }

    public ICommand dataModifySetValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " set value " + data.getValue());
    }

    public ICommand dataModifySetValue(String path, String data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " set value " + data);
    }

    public ICommand dataModifySetFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " set from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " set from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " set from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyAppendValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " append value " + data.getValue());
    }

    public ICommand dataModifyAppendValue(String path, String data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " append value " + data);
    }

    public ICommand dataModifyAppendFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " append from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " append from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " append from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyInsertValue(String path, int index, Nbt.Data data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " insert " + index + " value " + data.getValue());
    }

    public ICommand dataModifyInsertValue(String path, int index, String data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " insert " + index + " value " + data);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " insert " + index + " from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Position from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " insert " + index + " from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Storage from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " insert " + index + " from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyMergeValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " merge value " + data.getValue());
    }

    public ICommand dataModifyMergeValue(String path, String data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " merge value " + data);
    }

    public ICommand dataModifyMergeFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " merge from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " merge from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " merge from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyPrependValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " prepend value " + data.getValue());
    }

    public ICommand dataModifyPrependValue(String path, String data) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " prepend value " + data);
    }

    public ICommand dataModifyPrependFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " prepend from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyPrependFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " prepend from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyPrependFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify entity " + getSelector() + " " + path + " prepend from storage " + from.name() + " " + fromPath);
    }

    public enum Base {
        SELF("@s"),
        NEAREST_PLAYER("@p"),
        RANDOM_PLAYER("@r"),
        ALL_PLAYERS("@a"),
        ALL_ENTITIES("@e");

        private final String selector;

        Base(String selector) {
            this.selector = selector;
        }

        public String getSelector() {
            return selector;
        }
    }

    public static class SelectorParam {

        private String key;
        private String value;

        public SelectorParam(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public SelectorParam(String key, SelectorParams value) {
            this.key = key;
            this.value = value.getKey();
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String get() {
            return key + "=" + value;
        }

        public static SelectorParam of(String key, String value) {

            return new SelectorParam(key, value);
        }
    }

    public enum SelectorParams {

        NAME("name"),
        TYPE("type"),
        TEAM("team"),
        GAMEMODE("gamemode"),
        PREDICATE("predicate"),
        DIMENSION("dimension"),
        LEVEL("level"),
        SORT("sort"),
        LIMIT("limit"),
        X_ROTATION("x_rotation"),
        Y_ROTATION("y_rotation"),
        X("x"),
        Y("y"),
        Z("z"),
        RADIUS("r"),
        RANGE("rm"),
        DX("dx"),
        DY("dy"),
        DZ("dz"),
        DISTANCE("distance"),
        NBT("nbt"),
        TAG("tag"),
        SCORE("score"),
        ADVANCEMENT("advancement"),
        EFFECT("effect");

        private final String key;
        private String value;

        SelectorParams(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void with(String value) {
            this.value = value;
        }
    }
}