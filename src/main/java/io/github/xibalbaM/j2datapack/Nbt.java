package io.github.xibalbaM.j2datapack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nbt {

    Map<String, Data> nodes = new HashMap<>();

    public Nbt() {
    }

    public static Nbt of() {
        return new Nbt();
    }

    public Nbt with(String key, Object data) {
        nodes.put(key, new Data(data));
        return this;
    }

    public Nbt with(String key, Data data) {
        nodes.put(key, data);
        return this;
    }

    public String getNbt() {

        StringBuilder nbt = new StringBuilder("{");
        for (Map.Entry<String, Data> entry : nodes.entrySet()) {
            nbt.append(entry.getKey()).append(":").append(entry.getValue().getValue()).append(",");
        }
        if (nbt.toString().endsWith(",")) {
            nbt.deleteCharAt(nbt.length() - 1);
        }
        nbt.append("}");
        return nbt.toString();
    }

    public static class Data {

        private static final List<Class> acceptedTypes = List.of(
                byte.class,
                boolean.class,
                short.class,
                int.class,
                long.class,
                float.class,
                double.class,
                String.class,
                List.class,
                Nbt.class,
                byte[].class,
                int[].class,
                long[].class,
                Byte.class,
                Boolean.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class
        );

        private final Object data;

        public Data(Object value) {

            if (acceptedTypes.stream().anyMatch(type -> type.isInstance(value))) {

                if (!(value instanceof List) || ((List<?>) value).get(0) instanceof Data) {

                    this.data = value;
                } else {
                    throw new IllegalArgumentException("List must contain Data objects");
                }
            } else {
                throw new IllegalArgumentException(
                        "The type " + value.getClass().getName() + " is not a valid NBT type");
            }
        }

        public static Data of(Object value) {

            return new Data(value);
        }

        public Object getData() {
            return data;
        }

        public String getValue() {

            if (data instanceof List) {
                StringBuilder list = new StringBuilder("[");
                for (Object o : (List) data) {
                    list.append( ( (Data) o).getValue()).append(",");
                }
                list.deleteCharAt(list.length() - 1);
                list.append("]");
                return list.toString();
            } else if (data instanceof Nbt) {
                return ((Nbt) data).getNbt();
            } else if (data instanceof byte[]) {
                StringBuilder bytes = new StringBuilder("[B;");
                for (byte b : (byte[]) data) {
                    bytes.append(b).append(",");
                }
                bytes.deleteCharAt(bytes.length() - 1);
                bytes.append("]");
                return bytes.toString();
            } else if (data instanceof int[]) {
                StringBuilder ints = new StringBuilder("[I;");
                for (int i : (int[]) data) {
                    ints.append(i).append(",");
                }
                ints.deleteCharAt(ints.length() - 1);
                ints.append("]");
                return ints.toString();
            } else if (data instanceof long[]) {
                StringBuilder longs = new StringBuilder("[L;");
                for (long l : (long[]) data) {
                    longs.append(l).append(",");
                }
                longs.deleteCharAt(longs.length() - 1);
                longs.append("]");
                return longs.toString();
            } else if (data instanceof Integer) {
                return data + "i";
            } else if (data instanceof Long) {
                return data + "L";
            } else if (data instanceof Float) {
                return data + "f";
            } else if (data instanceof Double) {
                return data + "d";
            } else if (data instanceof String) {
                if (((String) data).startsWith("[") || ((String) data).startsWith("{")) {
                    return data.toString();
                } else {
                    return "\"" + data + "\"";
                }
            } else {
                return data.toString();
            }
        }
    }
}