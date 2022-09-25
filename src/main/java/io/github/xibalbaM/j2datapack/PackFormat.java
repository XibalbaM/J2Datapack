package io.github.xibalbaM.j2datapack;

public enum PackFormat {

    FORMAT_1_13_to_1_14_4(4),
    FORMAT_1_15_to_1_16_1(5),
    FORMAT_1_16_2_to_1_16_5(6),
    FORMAT_1_17_to_1_17_1(7),
    FORMAT_1_18_to_1_18_1(8),
    FORMAT_1_18_2(9),
    FORMAT_1_19_to_1_19_2(10),
    ALL(-1);

    private final int format;

    PackFormat(int format) {
        this.format = format;
    }

    public int getFormat() {
        return format;
    }
}
