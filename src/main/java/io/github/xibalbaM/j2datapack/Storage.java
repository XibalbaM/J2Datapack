package io.github.xibalbaM.j2datapack;

import io.github.xibalbaM.j2datapack.commands.CustomCommand;
import io.github.xibalbaM.j2datapack.commands.ICommand;
import org.junit.Assert;

public record Storage(String name) {

    public static Storage of(String name) {

        return new Storage(name);
    }

    public ICommand dataGet(String path) {

        return new CustomCommand("data get storage " + name + " " + path);
    }

    public ICommand dataGet(String path, int scale) {

        return new CustomCommand("data get storage " + name + " " + path + " " + scale);
    }

    public ICommand dataRemove(String path) {

        return new CustomCommand("data remove storage " + name + " " + path);
    }

    public ICommand dataMerge(String path, String nbt) {

        return new CustomCommand("data merge storage " + name + " " + path + " " + nbt);
    }

    public ICommand dataMerge(String path, Nbt nbt) {

        return new CustomCommand("data merge storage " + name + " " + path + " " + nbt.getNbt());
    }

    public ICommand dataModifySetValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify storage " + name + " " + path + " set value " + data.getValue());
    }

    public ICommand dataModifySetValue(String path, String data) {

        return new CustomCommand("data modify storage " + name + " " + path + " set value " + data);
    }

    public ICommand dataModifySetFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify storage " + name + " " + path + " set from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " set from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifySetFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " set from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyAppendValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify storage " + name + " " + path + " append value " + data.getValue());
    }

    public ICommand dataModifyAppendValue(String path, String data) {

        return new CustomCommand("data modify storage " + name + " " + path + " append value " + data);
    }

    public ICommand dataModifyAppendFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify storage " + name + " " + path + " append from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " append from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyAppendFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " append from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyInsertValue(String path, int index, Nbt.Data data) {

        return new CustomCommand("data modify storage " + name + " " + path + " insert " + index + " value " + data.getValue());
    }

    public ICommand dataModifyInsertValue(String path, int index, String data) {

        return new CustomCommand("data modify storage " + name + " " + path + " insert " + index + " value " + data);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify storage " + name + " " + path + " insert " + index + " from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Position from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " insert " + index + " from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyInsertFrom(String path, int index, Storage from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " insert " + index + " from storage " + from.name() + " " + fromPath);
    }

    public ICommand dataModifyMergeValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify storage " + name + " " + path + " merge value " + data.getValue());
    }

    public ICommand dataModifyMergeValue(String path, String data) {

        return new CustomCommand("data modify storage " + name + " " + path + " merge value " + data);
    }

    public ICommand dataModifyMergeFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify storage " + name + " " + path + " merge from entity " + from.getSelector() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " merge from block " + from.getPosition() + " " + fromPath);
    }

    public ICommand dataModifyMergeFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " merge from storage " + from.name() + " " + fromPath);
    }
    
    public ICommand dataModifyPrependValue(String path, Nbt.Data data) {

        return new CustomCommand("data modify storage " + name + " " + path + " prepend value " + data.getValue());
    }
    
    public ICommand dataModifyPrependValue(String path, String data) {

        return new CustomCommand("data modify storage " + name + " " + path + " prepend value " + data);
    }
    
    public ICommand dataModifyPrependFrom(String path, Selector from, String fromPath) {

        Assert.assertTrue("Selector must be a sigle entity", from.isSingleEntity());

        return new CustomCommand("data modify storage " + name + " " + path + " prepend from entity " + from.getSelector() + " " + fromPath);
    }
    
    public ICommand dataModifyPrependFrom(String path, Position from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " prepend from block " + from.getPosition() + " " + fromPath);
    }
    
    public ICommand dataModifyPrependFrom(String path, Storage from, String fromPath) {

        return new CustomCommand("data modify storage " + name + " " + path + " prepend from storage " + from.name() + " " + fromPath);
    }
}
