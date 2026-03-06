import java.util.ArrayList;
import java.util.List;

public class Problem {

    private List<Location> locations = new ArrayList<>();
    private List<Road> roads = new ArrayList<>();

    public void addLocation(Location l) {
        if (!locations.contains(l))
            locations.add(l);
    }
    public void addRoad(Road r) {
        if (!roads.contains(r))
            roads.add(r);
    }

    public boolean isValid() {
        for (Road r : roads) {
            if (!locations.contains(r.getStart()) ||
                    !locations.contains(r.getEnd()))
                return false;
        }
        return true;
    }
    public boolean canReach(Location start, Location target) {

        java.util.ArrayList<Location> visited = new java.util.ArrayList<>();
        visited.add(start);

        for (int i = 0; i < visited.size(); i++) {
            Location current = visited.get(i);

            if (current.equals(target)) {
                return true;
            }
            for (Road r : roads) {
                if (r.getStart().equals(current)) {
                    Location next = r.getEnd();

                    if (!visited.contains(next)) {
                        visited.add(next);
                    }
                }
                if (r.getEnd().equals(current)) {
                    Location next = r.getStart();

                    if (!visited.contains(next)) {
                        visited.add(next);
                    }
                }
            }
        }
        return false;
    }
}
