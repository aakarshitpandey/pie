import java.util.List;

public class laff {

    public void dolaff(Storage main, List<Box> boxes) {
        //creating the free space object
        Dim free = new Dim(main);

        while (!boxes.isEmpty()) {
            //get the box with the best height
            int index = bestfit(boxes, main, free);

            //best fit doesn't exist or level is full
            if (index == -1) {
                break;
            }

            Box currBox = boxes.get(index);

            //once the height and length and width of a level's space have been fixed, find the best boxes for that
            //TODO: fit within a level space

            //add a new level
            main.addLevel();

            //change the available free space
            free = new Dim(free.getLength(), free.getWidth(), main.getFreeheight());
        }
    }

    //returns the index of the box with the best fit
    public int bestfit(List<Box> boxes, Storage main, Dim free) {
        int index = -1;
        boolean fullLev = false;
        //iterating through all the boxes and getting the best fit with respect to SA
        for (int i = 0; i < boxes.size(); i++) {
            Box b = boxes.get(i);
            boolean fit = b.getLargestSurfaceArea(free.getLength(), free.getWidth(), free.getHeight());

            if (fit) { //TODO: add the part for remaining volume comparison
                //setting first index
                if (index == -1) {
                    index = i;
                    //checking if the level is full
                    fullLev = (b.getHeight() == free.getHeight()) ? true : false;
                } else {
                    //level is full
                    if (fullLev) {
                        //check if the height of the object is equal to the height of the free space in the box
                        if (b.getHeight() == free.getHeight()) {
                            //check if the current SA fits the constraints of the free space
                            if (boxes.get(index).currArea() < b.currArea()) {
                                //the box fits and can be added unless there is a better fit
                                index = i;
                            }
                        }
                    } else {
                        //checking if the box's height completely fills free space's height
                        if (b.getHeight() == free.getHeight()) {
                            fullLev = true;
                            index = i;
                        } else {
                            //setting the to-be-added box to a new box as it is a better fit height-wise
                            if (boxes.get(index).getHeight() < b.getHeight()) {
                                index = i;
                            } else if (boxes.get(index).getHeight() == b.getHeight() &&
                                        boxes.get(index).currArea() < b.currArea()) {
                                //setting the to-be-added box to a new box as it is a better fit SA-wise given the height
                                index = i;
                            }
                        }
                    }
                }
            }
        }
        return index;
    }
}
