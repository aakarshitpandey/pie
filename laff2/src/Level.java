import java.util.ArrayList;

/*
 * This class is a representation of a level within a container
 * It contains all the boxes within the arraylist of placements i.e positions of the boxes within the level
 */

public class Level extends ArrayList<Placement> {


    private static final long serialVersionUID = 1L;

    /*
     * returns max height of a box in the level
     */
    public double getHeight() {
        double height = 0;

        for(Placement placement : this) {
            Box box = placement.getBox();
            if(box.getHeight() > height) {
                height = box.getHeight();
            }
        }

        return height;
    }

    /*
     * Check whether placement is valid, i.e. no overlaps using coordinate geometry
     */
    public void validate() throws IllegalArgumentException {
        for(int i = 0; i < size(); i++) {
            for(int j = 0; j < size(); j++) {
                if(j == i) {
                    if(!get(i).intersects(get(j))) {
                        throw new IllegalArgumentException();
                    }
                } else {
                    if(get(i).intersects(get(j))) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
    }

}
