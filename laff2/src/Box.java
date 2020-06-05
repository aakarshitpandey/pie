/*
 * This class is used to represent all the objects that are put into the container
 * This is also an abstraction for the biggest container used to store all the objects
 */

public class Box extends Dimension {

    //constructor
    public Box(double w, double l, double h) {
        super(w, l, h);
    }

    //in case they want to name their objects for db + cacheing purposes
    public Box(String name, double w, double l, double h) {
        super(name, w, l, h);
    }

    public Box(final Dimension dimension) {
        this(dimension.width, dimension.length, dimension.height);
    }

    /*
     * rotate the box in a 3-d by reordering the w,d,h
     */
    public Box rotate3D() {
        double h = this.height;

        this.height = width;
        this.width = length;
        this.length = h;

        return this;
    }

    /*
     * rotates 2-d along the length and width
     */
    public Box rotate2D() {
        double l = this.length;

        this.length = width;
        this.width = l;

        return this;
    }

    /*
     * rotates along the length-height plane
     */
    public Box rotate2DIn3D() {
        double l = this.length;

        this.length = height;
        this.height = l;

        return this;
    }

    /*
     * checks if the new orientation fits the contraints
     */
    boolean fitRotate2D(double w, double d) {

        if (w >= width && d >= length) {
            return true;
        }
        if (d >= width && w >= length) {
            rotate2D();

            return true;
        }
        return false;
    }

    /*
     * returns the current surface area of the main face i.e l * w
     */
    double currentSurfaceArea() {
        return width * length;
    }

    public Box clone() {
        return new Box(name, width, length, height);
    }


    /*
     * rotate the box in 2-d and check whether it fits in a particular dimension's constraints
     */
    boolean fitRotate2D(Dimension dimension) {
        if (dimension.getHeight() < height) {
            return false;
        }
        return fitRotate2D(dimension.getWidth(), dimension.getLength());
    }

    /*
     * Set of functions to check whether the box fits within a particular orientation's constraints
     */
    private boolean fitsWidthAndLengthDown(double w, double l, double h) {

        if (h < height) {
            return false;
        }

        return (l >= width && w >= length) || (w >= width && l >= length);
    }

    private boolean fitsHeightAndLengthDown(double w, double l, double h) {

        if (h < width) {
            return false;
        }

        return (l >= height && w >= length) || (w >= height && l >= length);
    }

    private boolean fitsHeightAndWidthDown(double w, double l, double h) {

        if (h < length) {
            return false;
        }

        return (l >= height && w >= width) || (w >= height && l >= width);
    }

    /*
     * rotate the box to the orientation with the largest surface area in a given dimension.
     * Helpful when finidng the best box with largest height for inital level
     */
    boolean rotateLargestFootprint3D(Dimension dimension) {
        double w = dimension.getWidth();
        double l = dimension.getLength();
        double h = dimension.getHeight();

        double a = Integer.MIN_VALUE;
        if (fitsWidthAndLengthDown(w, l, h)) {
            a = width * length;
        }

        double b = Integer.MIN_VALUE;
        if (fitsHeightAndLengthDown(w, l, h)) {
            b = height * length;
        }

        double c = Integer.MIN_VALUE;
        if (fitsHeightAndWidthDown(w, l, h)) {
            c = width * height;
        }

        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE && c == Integer.MIN_VALUE) {
            return false;
        }

        if (a > b && a > c) {
            // no rotate
        } else if (b > c) {
            // rotate once
            rotate3D();
        } else {
            rotate3D();
            rotate3D();
        }

        if (h < height) {
            //TODO: or throw an exception
            return false;
        }

        if (width > w || length > l) {
            // use the other orientation as this one doesn't fit the constraints
            rotate2D();
        }

        if (width > w || length > l) {
            //TODO: or throw an exception
            return false;
        }

        return true;
    }

    /*
     * rotate the box to the orientation with the smallest surface area in a given dimension
     * Best for when fitting the smallest and most number of boxes within the free space in a level
     */
    boolean fitRotate3DSmallestFootprint(Dimension dimension) {
        //return fitRotate3DSmallestFootprint(space.getWidth(), space.getLength(), space.getHeight());
        double w = dimension.getWidth();
        double l = dimension.getLength();
        double h = dimension.getHeight();

        double a = Integer.MAX_VALUE;
        if (fitsWidthAndLengthDown(w, l, h)) {
            a = width * length;
        }

        double b = Integer.MAX_VALUE;
        if (fitsHeightAndLengthDown(w, l, h)) {
            b = height * length;
        }

        double c = Integer.MAX_VALUE;
        if (fitsHeightAndWidthDown(w, l, h)) {
            c = width * height;
        }

        if (a == Integer.MAX_VALUE && b == Integer.MAX_VALUE && c == Integer.MAX_VALUE) {
            return false;
        }

        if (a < b && a < c) {
            // no rotate
        } else if (b < c) {
            // rotate once
            rotate3D();
        } else {
            rotate3D();
            rotate3D();
        }

        if (h < height) {
            //TODO: or throw an exception
            return false;
        }

        if (width > w || length > l) {
            // use the other orientation as this one doesn't fit the constraints
            rotate2D();
        }

        if (width > w || length > l) {
            //TODO: or throw an exception
            return false;
        }

        return true;
    }


    @Override
    public String toString() {
        return "Box [width=" + width + ", length=" + length + ", height=" + height + ", volume="
                + volume + ", name=" + name + "]";
    }

}