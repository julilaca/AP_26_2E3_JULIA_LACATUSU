public class Compulsory {

    public static void main(String[] args) {

        Programmer p1 = new Programmer("julia", "10", "2006", "Java");
        Designer d1 = new Designer("george", "11", "2005", "Photoshop");
        Person p2 = new Person("bia", "03", "2000");

        Company c1 = new Company("Amazon", "101", "Tech");
        Company c2 = new Company("Bitdefender", "100", "Security");

        p1.addRelationship(d1, "friend");
        p1.addRelationship(c1, "employee");
        d1.addRelationship(p1, "friend");
        c1.addRelationship(p1, "employee");
        c2.addRelationship(p2, "partner");

        SocialNetwork network = new SocialNetwork();

        network.addProfile(p1);
        network.addProfile(d1);
        network.addProfile(p2);
        network.addProfile(c1);
        network.addProfile(c2);

        network.printNetwork();
    }
}