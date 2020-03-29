public class ObjectIntoBP implements Comparable {
    private int weight;
    private int value;

    public ObjectIntoBP (int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public double ratio () {
        return (double) this.value/this.weight;
    }

    @Override
    public int compareTo(Object o) {
        ObjectIntoBP objectIntoBP = (ObjectIntoBP) o;
        double result = this.ratio() - objectIntoBP.ratio();
        if (result > 0) return -1;
        else if (result < 0) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "weight: " + weight +
                ", value: " + value + " " + "ratio: " + ratio();
    }
}
