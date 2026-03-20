package org.example;

import java.util.*;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.Edge;
import org.graph4j.spanning.WeightedSpanningTreeIterator;


public class City {

    private String name;
    private Set<Intersection> intersections = new HashSet<>();
    private List<Street> streets = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void addStreet(Street street) {
        streets.add(street);
        intersections.add(street.getFrom());
        intersections.add(street.getTo());
    }

    public int connectingStreets(Street target) {
        int count = 0;
        for (Street s : this.streets) {
            if (s.equals(target)) continue;
            if (s.getFrom().equals(target.getFrom()) || s.getTo().equals(target.getFrom())
                    || s.getFrom().equals(target.getTo()) || s.getTo().equals(target.getTo())) {
                count++;
            }
        }
        return count;
    }

    public void morethan3() {
        this.streets.stream()
                .filter(street -> street.getLength() > 10)
                .filter(street -> this.connectingStreets(street) >= 3)
                .forEach(street -> System.out.println(street.getName()));
    }

    public void minimumCostSolution(int k) {
        Map<Intersection, Integer> intersectionId = new HashMap<>();
        int id = 0;
        for (Intersection inter : this.intersections) {
            intersectionId.put(inter, id++);
        }

        Graph cityGraph = GraphBuilder.numVertices(this.intersections.size()).buildGraph();

        for (Street street : this.streets) {
            int uID = intersectionId.get(street.getFrom());
            int vID = intersectionId.get(street.getTo());
            cityGraph.addEdge(uID, vID, (double) street.getLength());
        }

        WeightedSpanningTreeIterator iterator = new WeightedSpanningTreeIterator(cityGraph);

        int count = 1;
        while (iterator.hasNext() && count <= k) {
            Collection<Edge> spanningTree = iterator.next();
            double totalCost = 0;
            for (Edge edge : spanningTree) {
                totalCost += edge.weight();
            }
            System.out.println(totalCost);
            count++;
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", intersections=" + intersections +
                ", streets=" + streets +
                '}';
    }
}
