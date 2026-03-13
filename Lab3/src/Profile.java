public interface Profile extends Comparable<Profile> {
    String getId();
    String getName();
    int getImportance();
}