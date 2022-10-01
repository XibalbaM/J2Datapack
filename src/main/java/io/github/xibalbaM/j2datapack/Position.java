package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.CustomCommand;
import io.github.xibalbaM.j2datapack.commands.ICommand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

public class Position {

    private final Type type;
    private int x = 0, y = 0, z = 0;

    public Position(Type type) {
        this.type = type;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Position of(Type type) {
        return new Position(type);
    }

    public Position withX(int x) {
        this.x = x;
        return this;
    }

    public Position withY(int y) {
        this.y = y;
        return this;
    }

    public Position withZ(int z) {
        this.z = z;
        return this;
    }

    public Position with(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public String getPosition() {
        return type.getPrefix() + x + " " + type.getPrefix() + y + " " + type.getPrefix() + z;
    }public ICommand dataGet(String path) {

        return new CustomCommand("data get block " + getPosition() + " " + path);
    }


    public ICommand dataGet(String path, int scale) {

        return new CustomCommand("data get block " + getPosition() + " " + path + " " + scale);
    }

    public ICommand dataRemove(String path) {

        return new CustomCommand("data remove block " + getPosition() + " " + path);
    }

    public ICommand dataMerge(String path, String nbt) {

        return new CustomCommand("data merge block " + getPosition() + " " + path + " " + nbt);
    }

    public ICommand dataMerge(String path, Nbt nbt) {

        return new CustomCommand("data merge block " + getPosition() + " " + path + " " + nbt.getNbt());
    }

    public ICommand dataModifySetValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " set value " + data.getValue());
    }

    public ICommand dataModifySetValue(String path, String data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " set value " + data);
    }

    public ICommand dataModifySetFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify block " + getPosition() + " " + path + " set from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " set from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " set from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyAppendValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " append value " + data.getValue());
    }

    public ICommand dataModifyAppendValue(String path, String data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " append value " + data);
    }

    public ICommand dataModifyAppendFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify block " + getPosition() + " " + path + " append from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " append from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " append from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyInsertValue(String path, int index, Nbt.Data data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " insert " + index + " value " + data.getValue());
    }

    public ICommand dataModifyInsertValue(String path, int index, String data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " insert " + index + " value " + data);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify block " + getPosition() + " " + path + " insert " + index + " from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Position from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " insert " + index + " from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Storage from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " insert " + index + " from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyMergeValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " merge value " + data.getValue());
    }

    public ICommand dataModifyMergeValue(String path, String data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " merge value " + data);
    }

    public ICommand dataModifyMergeFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify block " + getPosition() + " " + path + " merge from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " merge from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " merge from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyPrependValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " prepend value " + data.getValue());
    }

    public ICommand dataModifyPrependValue(String path, String data) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " prepend value " + data);
    }

    public ICommand dataModifyPrependFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify block " + getPosition() + " " + path + " prepend from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyPrependFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " prepend from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyPrependFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify block " + getPosition() + " " + path + " prepend from storage " + from.name() + " " + fromPath);
    }

    public static enum Type {
        ABSOLUTE(""),
        RELATIVE("~"),
        LOCAL("^");

        private final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
