/*
 * Represents the position of a barticular box within a defined space
 * The space usually refers to the free space within a particular level
 */

public class Position {

    private Space space;
    private Box box;

    //constructors
    public Position(Space space, Box box) {
        this.space = space;
        this.box = box;
    }

    public Position(Space space) {
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

    //checks for when the current position intersects a specified position. Same as with the space
    boolean intersects(Position position) {
        return intersectsX(position) && intersectsY(position) && intersectsZ(position);
    }

    boolean isbetween(double start, double end, double pos) {
        if (start <= pos && pos <= end) {
            return true;
        }
        return false;
    }

    public boolean intersectsX(Position position) {

        double startX = space.getX();
        double endX = startX + box.getWidth() - 1;

        return isbetween(startX, endX, position.getSpace().getX()) ||
                isbetween(startX, endX, (position.getSpace().getX() + position.getBox().getWidth() - 1));

    }

    public boolean intersectsY(Position position) {

        double startY = space.getY();
        double endY = startY + box.getLength() - 1;

        return isbetween(startY, endY, position.getSpace().getY()) ||
                isbetween(startY, endY, (position.getSpace().getY() + position.getBox().getLength() - 1));
    }

    public boolean intersectsZ(Position position) {

        double startZ = space.getZ();
        double endZ = startZ + box.getHeight() - 1;

        return isbetween(startZ, endZ, position.getSpace().getZ()) ||
                isbetween(startZ, endZ, (position.getSpace().getZ() + position.getBox().getHeight() - 1));
    }

    @Override
    public String toString() {
        return "Position [" + box.getName() + " " + space.getX() + "x" + space.getY() + "x" + space.getZ() +
                ", width=" + box.getWidth() + ", depth=" + box.getLength() + ", height="
                + box.getHeight() + "]";
    }

}