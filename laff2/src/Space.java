/*
 * Represents the position, using coordinates, within a particular dimension
 */

public class Space extends Dimension {

    private Space parent;
    private Space remainder; //free space available

    //position coordinates in 3-d space for 3-d mapping
    private double x; // width
    private double y; // length
    private double z; // height

    //constructors
    //default constructor
    public Space() { }

    //clone constructor
    public Space(Space clone) {
        this.parent = clone.parent;
        this.x = clone.x;
        this.y = clone.y;
        this.z = clone.z;

        this.width = clone.width;
        this.length = clone.length;
        this.height = clone.height;
    }

    public Space(Space parent, String name, double w, double d, double h, double x, double y, double z) {
        super(name, w, d, h);

        this.parent = parent;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Space(double w, double d, double h, double x, double y, double z) {
        this(null, null, w, d, h, x, y, z);
    }

    //getters and setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Space getParent() {
        return parent;
    }

    public void setParent(Space parent) {
        this.parent = parent;
    }

    public void setRemainder(Space dual) {
        this.remainder = dual;
    }

    public Space getRemainder() {
        return remainder;
    }

    /*
     * checks if the particular position is between the start and end coordinates in a plane
     */
    protected static boolean isBetween(double start, double pos, double end) {
        return start <= pos && pos <= end;
    }

    /*
     * Checks if the position lies between the start and end or within the constraint of a distance
     * Used to check if the position of a particular box's footprint intersects with an already placed one
     */
    protected boolean intersects(double start, double end, double pos, double distance) {
        return isBetween(start, pos, end) || isBetween(start, pos + distance, end) || (pos < start && end < pos + distance);
    }

    public boolean intersects(Space space) {
        return intersectsX(space) && intersectsY(space) && intersectsZ(space);
    }

    public boolean intersectsX(Space space) {
        double startX = space.getX();
        double endX = startX + space.getWidth() - 1;

        return intersects(startX, endX, x, width);
    }

    public boolean intersectsY(Space space) {
        double startY = space.getY();
        double endY = startY + space.getLength() - 1;

        return intersects(startY, endY, y, length);
    }

    public boolean intersectsZ(Space space) {
        double startZ = space.getZ();
        double endZ = startZ + space.getHeight() - 1;

        return intersects(startZ, endZ, z, height);
    }

    public boolean intersects(Placement placement) {
        return intersectsX(placement) && intersectsY(placement) && intersectsZ(placement);
    }

    public boolean intersectsX(Placement placement) {
        double startX = placement.getSpace().getX();
        double endX = startX + placement.getBox().getWidth() - 1;
        return intersects(startX, endX, x, width);
    }

    public boolean intersectsY(Placement placement) {
        double startY = placement.getSpace().getY();
        double endY = startY + placement.getBox().getLength() - 1;
        return intersects(startY, endY, y, length);
    }

    public boolean intersectsZ(Placement placement) {
        double startZ = placement.getSpace().getZ();
        double endZ = startZ + placement.getBox().getHeight() - 1;
        return intersects(startZ, endZ, z, height);
    }

    /*
     * To calculate the remainder space in a particular plane
     * Recalculate volume (of the free space) as it would have changed
     */
    public void subtractX(Placement placement) {
        double endX = placement.getSpace().getX() + placement.getBox().getWidth();

        if(endX > x) {
            width -= endX - x;

            x = endX;

            calculateVolume();
        }
    }

    public void subtractY(Placement placement) {
        double endY = placement.getSpace().getY() + placement.getBox().getLength();

        if(endY > y) {
            length -= endY - y;

            y = endY;

            calculateVolume();
        }
    }

    public void subtractZ(Placement placement) {
        double endZ = placement.getSpace().getZ() + placement.getBox().getHeight();

        if(endZ > z) {
            height -= endZ - z;

            z = endZ;

            calculateVolume();
        }
    }

    @Override
    public String toString() {
        return "Space [name=" + name + ", " + x + "x" + y + "x" + z + ", width=" + width + ", length=" + length + ", height="
                + height + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Space other = (Space) obj;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (remainder == null) {
            if (other.remainder != null)
                return false;
        } else if (!remainder.equals(other.remainder))
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
            return false;
        return true;
    }


}