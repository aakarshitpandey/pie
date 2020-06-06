/*
 * This class is used to represent any 3-d object or space. It has a length, width and height
 * Also has a particular volume
 */

public class Dim {

    //using protected becuase want these variables to be accessible in subclasses
    protected double width; // x
    protected double length; // y
    protected double height; // z
    protected long volume;

    protected final String name;

    /*
     * constructors: default + parameters
     */
    public Dim() {
        this.name = null;
    }

    public Dim(String name, double w, double l, double h) {
        this.name = name;

        this.length = l;
        this.width = w;
        this.height = h;
        calculateVolume();
    }

    public Dim(double w, double l, double h) {
        this(null, w, l, h);
    }

    /*
     * create a new dimension with specified dimensions rather than a constructor with a nmae
     */
    public static Dim newInstance(double width, double depth, double height) {
        return new Dim(width, depth, height);
    }

    /*
     * calculates the volume and assigns it to the instance value volume
     */
    protected void calculateVolume() {
        this.volume = ((long)length) * ((long)width) * ((long)height);
    }

    /*
     * Getters and setters for the instance fields
     */
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public long getVolume() {
        return volume;
    }

    public void setLength(int depth) {
        this.length = depth;

        calculateVolume();
    }

    public void setHeight(int height) {
        this.height = height;

        calculateVolume();
    }

    public void setWidth(int width) {
        this.width = width;

        calculateVolume();
    }

    public boolean isSquare2D() {
        return width == length;
    }

    public boolean isSquare3D() {
        return width == length && width == height;
    }

    /*
     * returns the current footprint/surface area of the dimension
     */
    public double currentSurfaceArea() {
        return width * length;
    }

    /*
     * check if the box fits in the given dim without rotation
     */
    public boolean fitsIn3D(Dim dim) {
        return dim.getWidth() >= width && dim.getLength() >= length && dim.getHeight() >= height;
    }

    /*
     * check if the given dim fits in at least one 3-d orientation in this dim
     */
    public boolean canHold3D(Dim dim) {
        double w = dim.getWidth();
        double l = dim.getLength();
        double h = dim.getHeight();

        return (w <= width && h <= height && l <= length) ||
                (h <= width && l <= height && w <= length) ||
                (l <= width && w <= height && h <= length) ||
                (h <= width && w <= height && l <= length) ||
                (l <= width && h <= height && w <= length) ||
                (w <= width && l <= height && h <= length);
    }


    /*
     * check if the given dim fits in at least one 2-d orientation in this dim
     */
    public boolean canHold2D(Dim dim) {
        if(dim.getHeight() > height) {
            return false;
        }
        return (dim.getWidth() <= width && dim.getLength() <= length) ||
                (dim.getLength() <= width && dim.getWidth() <= length);
    }

    /*
     * check if the current box/dim fits in at least one orientation in a specified dim/free space
     */
    public boolean canFitIn3D(Dim dim) {
        return dim.canHold3D(this);
    }

    /*
     * check if the current box/dimesnsion can fit in the given free dim in one of the 2-d orientations
     */
    public boolean canFitIn2D(Dim dim) {
        return dim.canHold2D(this);
    }

    @Override
    public String toString() {
        return "Dim [width=" + width + ", length=" + length + ", height=" + height + ", volume=" + volume + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dim other = (Dim) obj;
        if (length != other.length)
            return false;
        if (height != other.height)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (volume != other.volume)
            return false;
        if (width != other.width)
            return false;
        return true;
    }


}