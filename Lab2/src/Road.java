public class Road {

    private String type;
    private double length;
    private double speedLim;
    private Location start;
    private Location end;

    public Road(String type, Location start, Location end, double speedLim) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.speedLim = speedLim;

        double dx = start.getX() - end.getX();
        double dy = start.getY() - end.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        this.length = distance;
    }
    public String getType() {
        return type; }
    public double getLength() {
        return length; }
    public double getSpeedLim() {
        return speedLim; }
    public Location getStart() {
        return start; }
    public Location getEnd() {
        return end; }

    public void setType(String type) {
        this.type = type; }
    public void setSpeedLim(double speedLim) {
        this.speedLim = speedLim; }

    @Override
    public String toString() {
        return "Road {type='" + type + "', start=" + start.getName() +
                ", end =" + end.getName() + ", length =" + length +
                ", speed limit =" + speedLim + "}";
    }
}