package org.example;

public class Street implements Comparable<Street> {
    private String name;
    private int length;
    private Intersection from;
    private Intersection to;

    public Street(String name, int length, Intersection from, Intersection to) {
        this.name = name;
        this.length = length;
        this.from = from;
        this.to = to;
    }
    @Override
    public int compareTo(Street other) {
        return Integer.compare(this.length, other.length);
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    public int getLength() { return length; }

    public String getName() {
        return name; }
    public Intersection getFrom() {
        return from; }
    public Intersection getTo() {
        return to;}
}
