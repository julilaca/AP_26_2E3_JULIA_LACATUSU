import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compulsory {
    public static void main(String[] args){
        List<Profile> network = new ArrayList<>();

        Person julia = new Person ("julia", "01");
        Person ana = new Person ("ana", "02");
        Person bia = new Person ("bia", "003");
        Company bitdef = new Company("Bitdefender", "001");
        Company amazon = new Company("Amazon", "002");

        network.add(julia);
        network.add(ana);
        network.add(bia);
        network.add(bitdef);
        network.add(amazon);

        Collections.sort(network);

        for(Profile profiles : network){
            System.out.println(profiles);
        }
    }
}