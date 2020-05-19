public class Box {
    private double length;
    private double width;
    private double height;

    //constructor
    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    //copy constructor for free space box representation
    public static Box newBox(Box b) {
        return new Box(b.length, b.width, b.height);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    //rotates the box object in 3D
    public Box rotate3D() {
        double l = this.length;
        this.length = this.height;
        this.height = this.width;
        this.width = l;

        return this;
    }

    //returns the volume of the box
    public double getVolume() {
        return length * width * height;
    }

    public double currArea() {
        return length * width;
    }

    //rotate along the largest surface area to check if that fits int he constraint
    public boolean getLargestSurfaceArea(double l, double w, double h) {
        double wl = Integer.MIN_VALUE;
        if (WL(l, w, h)) {
            wl = this.width * this.length;
        }

        double hl = Integer.MIN_VALUE;
        if (HL(l, w, h)) {
            hl = this.height * this.length;
        }

        double wh = Integer.MIN_VALUE;
        if (WH(l, w, h)) {
            wh = this.height * this.width;
        }

        //none of the dimensions can fit the largest surface area
        if (wl == Integer.MIN_VALUE && hl == Integer.MIN_VALUE && wh == Integer.MIN_VALUE) {
            return false;
        }

        //performing rotations to find the right orientation of the box
        //the wl orientation is the largest surface area
        if (wl > hl && wl > wh) {
            //no need to rotate as Wl is default
        } else if (hl > wh) {
            //rotate only once to make hl = wl
            rotate3D();
        } else {
            //roatae twice to make wh = wl
            rotate3D();
            rotate3D();
        }

        //TODO: check if 2D orientation needs to change
        return true;
    }

    //returns if the largest surface area is within the constraint of the box with respect to width and length
    public boolean WL(double l, double w, double h) {
        if (h < this.height) {
            return false;
        } else {
            //checks to see if the length and width are within the constraints of both
            return (l >= this.length && w >= this.width) || (l >= this.width && w >= this.length);
        }
    }

    //check if the largest surface area is  within the constraint of the box with respect to height and length
    public boolean HL(double l, double w, double h) {
        if (h < this.width) {
            return false;
        } else {
            //checks to see if the length and height are within the constraints of both
            return (l >= this.length && w >= this.height) || (l >= this.height && w >= this.length);
        }
    }

    //check if the largest surface area is  within the constraint of the box with respect to height and width
    public boolean WH(double l, double w, double h) {
        if (h < this.length) {
            return false;
        } else {
            //checks to see if the width and height are within the constraints of both
            return (l >= this.width && w >= this.height) || (l >= this.height && w >= this.width);
        }
    }
}
