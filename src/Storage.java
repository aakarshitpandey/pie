import java.util.ArrayList;

public class Storage extends Box{
    //the height of the stack when all the items are stacked in levels in the storage box
    private double itemsHeight = 0;
    //arraylist of levels
    private ArrayList<Level> levels = new ArrayList<>();

    //constructor
    public Storage(double length, double width, double height) {
        super(length, width, height);
    }

    public void addLevel() {
        Level lev = new Level();
        if (!levels.isEmpty()) {
            itemsHeight += getCurrLevelHeight();
            levels.add(lev);
        }
    }

    public double getCurrLevelHeight() {
        if (levels.isEmpty()) {
            return 0;
        }
        return levels.get(levels.size() - 1).getMaxHeight();
    }

    public double getItemsHeight() {
        return itemsHeight + getCurrLevelHeight();
    }

    public double getFreeheight() {
        double rem = getHeight() - getItemsHeight();
        if (rem < 0) {
            //TODO: exception has to be returned due to overflow
        }
        return rem;
    }

}
