public class Dim {
    private double length;
    private double width;
    private double height;
    private double volume;

    public Dim(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;

        //set the volume of the object
        changeVol();
    }

    //constructor for an object using a Box's dimensions
    public Dim(Box b) {
        this.length = b.getLength();
        this.width = b.getWidth();
        this.height = b.getHeight();

        //set the volume
        changeVol();
    }

    public void changeVol() {
        this.volume = this.length * this.width * this.height;
    }

    //Getters and setters
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        changeVol();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        changeVol();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        changeVol();
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    //method to return the area of the current face
    public double currArea() {
        return length * width;
    }


}
