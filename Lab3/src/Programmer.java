public class Programmer extends Person {

    private String language;

    public Programmer(String name, String id, String birthDate, String language) {
        super(name, id, birthDate);
        this.language = language; }

    public String getLanguage() {
        return language; }

    @Override
    public String toString() {
        return "Programmer{name='" + getName() + "', language='" + language + "'}";
    }
}
