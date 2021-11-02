package filters.FlippingTransformation;

import java.util.ArrayList;
import java.util.List;

import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.pixel.IPixel;

public class FlipHorizontal implements IFlipTransform{
  /**
   * Applies some transformation on the intensity of the given image.
   *
   * @param image Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  @Override
  public ImageOfPixel flipTransform(ImageOfPixel image) throws IllegalArgumentException {
    List<List<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    List<ArrayList<IPixel>> newImagePixels = new ArrayList<>();

    for (int i = 0; i < imagePixels.size(); i++) {
      List<IPixel> oldPixels = imagePixels.get(i);
      ArrayList<IPixel> newPixels = new ArrayList<>();
      for (int j = oldPixels.size() - 1; j >= 0; j--) {
        newPixels.add(oldPixels.get(j));
      }
      newImagePixels.add(newPixels);
    }
    return new Image(newImagePixels);
  }

}