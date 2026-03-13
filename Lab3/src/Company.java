import java.util.HashMap;
import java.util.Map;

public class Company implements Profile {

    private String name;
    private String id;
    private String industry;

    private Map<Profile, String> relationships = new HashMap<>();

    public Company(String name, String id, String industry) {
        this.name = name;
        this.id = id;
        this.industry = industry;
    }
    public void addRelationship(Profile p, String relation) {
        relationships.put(p, relation);
    }
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
        return "Company{name='" + name + "', industry='" + industry + "'}";
    }
}