/*
 * Represents the position of a barticular box within a defined space
 * The space usually refers to the free space within a particular level
 */

public class Placement {

    private Space space;
    private Box box;

    //constructors
    public Placement(Space space, Box box) {
        this.space = space;
        this.box = box;
    }

    public Placement(Space space) {
        this.space = space;
    }

    //getters and setters
    public Space getSpace() {
        return space;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    //getting the centers of a placement with respect to the box
    double getCenterX() {
        return (space.getX() + box.getWidth()) / 2;
    }

    double getCenterY() {
        return (space.getY() + box.getLength()) / 2;
    }

    double getCenterZ() {
        return (space.getZ() + box.getHeight()) / 2;
    }

    //checks for when the current placement intersects a specified placement. Same as with the space
    boolean intersects(Placement placement) {
        return intersectsX(placement) && intersectsY(placement) && intersectsZ(placement);
    }

    boolean isbetween(double start, double end, double pos) {
        if (start <= pos && pos <= end) {
            return true;
        }
        return false;
    }

    public boolean intersectsX(Placement placement) {

        double startX = space.getX();
        double endX = startX + box.getWidth() - 1;

        return isbetween(startX, endX, placement.getSpace().getX()) ||
                isbetween(startX, endX, (placement.getSpace().getX() + placement.getBox().getWidth() - 1));

    }

    public boolean intersectsY(Placement placement) {

        double startY = space.getY();
        double endY = startY + box.getLength() - 1;

        return isbetween(startY, endY, placement.getSpace().getY()) ||
                isbetween(startY, endY, (placement.getSpace().getY() + placement.getBox().getLength() - 1));
    }

    public boolean intersectsZ(Placement placement) {

        double startZ = space.getZ();
        double endZ = startZ + box.getHeight() - 1;

        return isbetween(startZ, endZ, placement.getSpace().getZ()) ||
                isbetween(startZ, endZ, (placement.getSpace().getZ() + placement.getBox().getHeight() - 1));
    }

    @Override
    public String toString() {
        return "Placement [" + box.getName() + " " + space.getX() + "x" + space.getY() + "x" + space.getZ() +
                ", width=" + box.getWidth() + ", depth=" + box.getLength() + ", height="
                + box.getHeight() + "]";
    }

}