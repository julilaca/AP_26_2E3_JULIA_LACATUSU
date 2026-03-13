import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SocialNetwork {

    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile p) {
        profiles.add(p);
    }
    public void printNetwork() {

        Collections.sort(profiles, new Comparator<Profile>() {
            @Override
            public int compare(Profile a, Profile b) {
                return Integer.compare(b.getImportance(), a.getImportance());
            }
        });
        for (Profile p : profiles) {
            System.out.println(p + " importance=" + p.getImportance());
        }
    }
}