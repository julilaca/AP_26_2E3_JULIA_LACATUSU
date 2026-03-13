public class Designer extends Person {

    private String tool;

    public Designer(String name, String id, String birthDate, String tool) {
        super(name, id, birthDate);
        this.tool = tool;
    }
    public String getTool() {
        return tool; }

    @Override
    public String toString() {
        return "Designer{name='" + getName() + "', tool='" + tool + "'}";
    }
}