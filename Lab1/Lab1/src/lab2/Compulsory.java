package lab2;

public class Compulsory {
    public static void main(String[] args) {

        Location l1 = new Location("bucharest", "city", 10, 15);
        Location l2 = new Location("iasi", "city", 20, 30);

        Road r1 = new Road("highway", l1, l2, 130);
        Road r2 = new Road("country",l1,l2, 90);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(r1);
        System.out.println(r2);
    }
}