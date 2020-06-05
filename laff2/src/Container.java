import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * This class represents the box used to store all the items
 * specified by the user in the most optimal way
 */

public class Container extends Box {

    protected double stackHeight = 0;
    protected ArrayList<Level> levels = new ArrayList<>();

    //constructors
    public Container(Container container) {
        super(container.getName(), container.getWidth(), container.getLength(), container.getHeight());
    }

    public Container(Dimension dimension) {
        super(dimension.getName(), dimension.getWidth(), dimension.getLength(), dimension.getHeight());
    }

    public Container(double w, double l, double h) {
        super(w, l, h);
    }

    public Container(String name, double w, double l, double h) {
        super(name, w, l, h);
    }

    public Container clone() {
        return new Container(this);
    }

    /*
     * rotations on boxes but returning containers
     */
    public Container rotate3D() {
        return (Container)super.rotate3D();
    }

    public Container rotate2D() {
        return (Container)super.rotate2D();
    }

    public Container rotate2DIn3D() {
        return (Container)super.rotate2DIn3D();
    }

    /*
     * The 6 different possible rotations. If two of the sides are equal, there are only 3 possible orientations.
     * If all sides is equal, there is only 1 possible orientation.
     * since it has a better chance to find a packaging than with a single container.
     * returns list of containers in all 6 rotations.
     *
     * Found from online source on stackoverflow. Moodified very slightly to fit our classes
     */
    public List<Container> rotations(){
        return rotationsStream().collect(Collectors.toList());
    }

    Stream<Container> rotationsStream() {
        List<Container> result = new ArrayList<>(6);
        Container box = clone();
        boolean square0 = box.isSquare2D();

        result.add(box);

        if(!box.isSquare3D()) {

            box = box.clone().rotate3D();
            boolean square1 = box.isSquare2D();

            result.add(box);

            box = box.clone().rotate3D();
            boolean square2 = box.isSquare2D();

            result.add(box);

            if(!square0 && !square1 && !square2) {
                box = box.clone().rotate2DIn3D();
                result.add(box);

                box = box.clone().rotate3D();
                result.add(box);

                box = box.clone().rotate3D();
                result.add(box);
            }
        }
        return result.stream();
    }

    public boolean add(Level element) {
        if(!levels.isEmpty()) {
            stackHeight += currentLevelStackHeight();
        }

        return levels.add(element);
    }

    public Level currentLevel() {
        if(!levels.isEmpty()) {
            return levels.get(levels.size() - 1);
        }
        return null;
    }

    public double getStackHeight() {
        return stackHeight + currentLevelStackHeight();
    }


    private double currentLevelStackHeight() {
        if(levels.isEmpty()) {
            return 0;
        }
        return levels.get(levels.size() - 1).getHeight();
    }


    public void add(Placement placement) {
        levels.get(levels.size() - 1).add(placement);
    }

    public Level addLevel() {
        Level level = new Level();
        add(level);
        return level;
    }

    /*
     * Get the free level space, i.e. container height with height of all levels subtracted.
     *
     * returns a dimension with the free height and box dimension
     */
    public Dimension getFreeLevelSpace() {
        double remainder = height - getStackHeight();
        if(remainder < 0) {
            throw new IllegalArgumentException("Remaining free space is negative at " + remainder + " for " + this);
        }
        return new Dimension(width, length, remainder);
    }


    public List<Level> getLevels() {
        return levels;
    }

    public Placement get(int level, int placement) {
        return levels.get(level).get(placement);
    }

    // keep method for tests
    public void validateCurrentLevel() {
        levels.get(levels.size() - 1).validate();
    }

    public void clear() {
        levels.clear();
        stackHeight = 0;
    }

    /*
     * TODO: get the used space in the main container
     */


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Container other = (Container) obj;
        if (levels == null) {
            if (other.levels != null)
                return false;
        } else if (!levels.equals(other.levels))
            return false;
        if (stackHeight != other.stackHeight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Container [stackHeight=" + stackHeight + ", levels=" + levels
                + ", width=" + width + ", length=" + length + ", height=" + height + ", volume="
                + volume + ", name=" + name + "]";
    }



}