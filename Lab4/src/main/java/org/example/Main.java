package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Intersection> intersectionList = IntStream.range(0, 10)
                .mapToObj(i -> new Intersection("Intersection " + i))
                .collect(Collectors.toList());
        Set<Intersection> intersectionSet = new HashSet<>(intersectionList);
        intersectionSet.add(intersectionList.get(0));

        List<Street> streets = new LinkedList<>();
        streets.add(new Street("s1", 12, intersectionList.get(0), intersectionList.get(1)));
        streets.add(new Street("s2", 5, intersectionList.get(1), intersectionList.get(2)));
        streets.add(new Street("s3", 8, intersectionList.get(2), intersectionList.get(3)));
        streets.add(new Street("s4", 20, intersectionList.get(3), intersectionList.get(4)));

        streets.sort(Comparator.comparingInt(Street::getLength));

        streets.forEach(System.out::println);
    }
}
