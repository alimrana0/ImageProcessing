package filters.FlippingTransformation;

import java.util.ArrayList;
import java.util.List;

import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.pixel.IPixel;

public class FlipVertical implements IFlipTransform{

  public FlipVertical() {}


  /**
   * Flips the image vertically, by splitting the image in half along width of the image and
   * reflecting the pixels along the middle. Results in an image that is
   *
   * @param image Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  @Override
  public ImageOfPixel flipTransform(ImageOfPixel image) {
    List<List<IPixel>> imagePixels = image.getPixels();
    List<ArrayList<IPixel>> newImagePixels = new ArrayList<>();

    for (int i = imagePixels.size() - 1; i >= 0; i--) {
      List<IPixel> oldPixels = newImagePixels.get(i);
      ArrayList<IPixel> newPixels = new ArrayList<>();
      for (int j = 0; j < oldPixels.size(); j++) {
        newPixels.add(oldPixels.get(j));
      }
      newImagePixels.add(newPixels);
    }
    return new Image(newImagePixels);
  }
}