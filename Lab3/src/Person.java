public class Person implements Profile, Comparable<Profile> {
    private String id;
    private String name;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
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
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}