public class Compulsory {

    public static void main(String[] args) {

        City c1 = new City("bucharest", 8, 8, 2000000);
        City c2 = new City("iasi", 15, 30, 300000);
        Airport a1 = new Airport("otopeni", 12, 16, 4);
        Road r1 = new Road(RoadType.highway, c1, c2, 120);
        Problem problem = new Problem();
        problem.addLocation(c1);
        problem.addLocation(c2);
        problem.addLocation(a1);
        problem.addRoad(r1);

        System.out.println("can reach: " + problem.canReach(c1, c2));
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(a1);
        System.out.println(r1);

        System.out.println("valid: " + problem.isValid());
    }
}