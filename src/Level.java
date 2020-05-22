import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Level {
    private ArrayList<Box> levelBox = new ArrayList<>();

    public double getMaxHeight(){
        Collections.sort(levelBox, new Comparator<Box>() {
            @Override
            public int compare(Box b1, Box b2) {
                Double b1h = b1.getHeight();
                Double b2H = b2.getHeight();
                return b1h.compareTo(b2H);
            }
        });

        return levelBox.get(levelBox.size() - 1).getHeight();
    }
}
