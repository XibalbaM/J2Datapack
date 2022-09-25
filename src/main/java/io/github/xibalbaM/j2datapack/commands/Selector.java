package io.github.xibalbaM.j2datapack.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        SelectorParams(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}