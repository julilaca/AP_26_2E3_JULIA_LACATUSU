package org.example;

import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();
        City iasi = new City("Iasi");
        List<Intersection> intersectionList = IntStream.range(0, 10)
                .mapToObj(i -> new Intersection(faker.address().city()))
                .collect(Collectors.toList());

        List<Street> streets = new LinkedList<>();
        streets.add(new Street(faker.address().streetName(), 15, intersectionList.get(0), intersectionList.get(1)));
        streets.add(new Street(faker.address().streetName(), 20, intersectionList.get(0), intersectionList.get(2)));
        streets.add(new Street(faker.address().streetName(), 40, intersectionList.get(0), intersectionList.get(3)));
        streets.add(new Street(faker.address().streetName(), 50, intersectionList.get(1), intersectionList.get(4)));
        streets.add(new Street(faker.address().streetName(), 50, intersectionList.get(3), intersectionList.get(5)));

        for (Street s : streets) {
            iasi.addStreet(s);
        }
        System.out.println(iasi);

        iasi.morethan3();
        iasi.minimumCostSolution(3);
    }
}
