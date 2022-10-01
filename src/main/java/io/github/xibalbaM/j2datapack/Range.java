package io.github.xibalbaM.j2datapack;

public class Range {

    private final int min;
    private final int max;

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static Range of(int min, int max) {
        return new Range(min, max);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getRange() {
        return min + ".." + max;
    }
}
