import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * This class represents the box used to store all the items
 * specified by the user in the most optimal way
 */

public class Container extends Box {

    protected double levelHeight = 0;
    protected ArrayList<Level> levels = new ArrayList<>();

    //constructors
    public Container(Container container) {
        super(container.getName(), container.getWidth(), container.getLength(), container.getHeight());
    }

    public Container(Dim dim) {
        super(dim.getName(), dim.getWidth(), dim.getLength(), dim.getHeight());
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
            levelHeight += currentLevelHeight();
        }

        return levels.add(element);
    }

    public Level currentLevel() {
        if(!levels.isEmpty()) {
            return levels.get(levels.size() - 1);
        }
        return null;
    }

    public double getLevelHeight() {
        return levelHeight + currentLevelHeight();
    }


    private double currentLevelHeight() {
        if(levels.isEmpty()) {
            return 0;
        }
        return levels.get(levels.size() - 1).getHeight();
    }


    public void add(Position position) {
        levels.get(levels.size() - 1).add(position);
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
    public Dim getFreeLevSpace() {
        double remainder = height - getLevelHeight();
        if(remainder < 0) {
            throw new IllegalArgumentException("Remaining free space is negative at " + remainder + " for " + this);
        }
        return new Dim(width, length, remainder);
    }


    public List<Level> getLevels() {
        return levels;
    }

    public Position get(int level, int placement) {
        return levels.get(level).get(placement);
    }

    // keep method for tests
    public void checkCurrLevel() {
        levels.get(levels.size() - 1).validate();
    }

    public void clear() {
        levels.clear();
        levelHeight = 0;
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
        if (levelHeight != other.levelHeight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Container [levelHeight=" + levelHeight + ", levels=" + levels
                + ", width=" + width + ", length=" + length + ", height=" + height + ", volume="
                + volume + ", name=" + name + "]";
    }



}