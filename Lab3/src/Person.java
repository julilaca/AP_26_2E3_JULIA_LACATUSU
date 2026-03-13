import java.util.HashMap;
import java.util.Map;

public class Person implements Profile {

    private String name;
    private String id;
    private String birthDate;

    private Map<Profile, String> relationships = new HashMap<>();

    public Person(String name, String id, String birthDate) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;}

    public void addRelationship(Profile p, String relation) {
        relationships.put(p, relation);}

    public Map<Profile, String> getRelationships() {
        return relationships;}

    public String getBirthDate() {
        return birthDate;}

    @Override
    public int getImportance() {
        return relationships.size();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Profile other) {
        return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', birthDate='" + birthDate + "'}";
    }
}