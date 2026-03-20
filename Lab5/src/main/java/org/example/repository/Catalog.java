package org.example.repository;

import org.example.model.Resource;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private final List<Resource> items = new ArrayList<>();

    public void add(Resource item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "items=" + items +
                '}';
    }
}
