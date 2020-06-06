import java.util.List;

/*
 * Class where the all the boxes are arranged in the container
 * Largest Area Fit First Algorithm is implemented here
 */

public class laff {
    public int bestfitHeight(List<Box> boxes, Container container, Dim free) {
        int index = -1;
        boolean fullLev = false;
        //iterating through all the boxes and getting the best fit with respect to SA
        for (int i = 0; i < boxes.size(); i++) {
            Box b = boxes.get(i);
            boolean fits = b.rotateLargestSA3D(free);

            if(fits && b.getVolume() <= container.getVolume()) {
                if(index == -1) {
                    fullLev = (b.getHeight() == free.getHeight());
                    index = i;
                } else {
                    if(fullLev) {
                        if(b.getHeight() == free.getHeight()) {
                            if(boxes.get(index).currentSurfaceArea() < b.currentSurfaceArea()) {
                                index = i;
                            }
                        }
                    } else {
                        if(b.getHeight() == free.getHeight()) {
                            fullLev = true;
                            index = i;
                        } else {
                            if (boxes.get(index).currentSurfaceArea() < b.currentSurfaceArea()) {
                                index = i;
                            } else if (boxes.get(index).currentSurfaceArea() == b.currentSurfaceArea() &&
                                    boxes.get(index).getHeight() < b.getHeight()) {
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
