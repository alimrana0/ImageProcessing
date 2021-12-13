package filters;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.pixel.IPixel;

/**
 * Class the replicate an image model to keep its information for a different model to use.
 */
public class Replicator {

  /**
   * Returns a copy of the image of the given model.
   * @param image the image being replicated.
   * @return a copy of the given image.
   * @throws IllegalArgumentException if the image is null.
   */
  public ImageOfPixel returnImage(ImageOfPixel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }

    List<List<IPixel>> imagePixels = image.getPixels();
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(p);
      }
      updatedPixels.add(row);
    }
    return new Image(updatedPixels);
  }
}
