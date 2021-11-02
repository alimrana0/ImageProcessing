package filters.FlippingTransformation;

import java.util.ArrayList;
import java.util.List;

import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.pixel.IPixel;

/**
 * Class to represent an image being flipped vertically down the lateral middle.
 */
public class FlipVertical implements IFlipTransform{

  /**
   * Empty constructor for a FlipVertical filter.
   */
  public FlipVertical() {}


  /**
   * Applies a vertical flip on the image by rearranging its pixels.
   *
   * @param image Image being flipped.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  @Override
  public ImageOfPixel flipTransform(ImageOfPixel image) throws IllegalArgumentException {
    if(image == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    List<ArrayList<IPixel>> newImagePixels = new ArrayList<>();

    for (int i = imagePixels.size() - 1; i >= 0; i--) {
      List<IPixel> pixels = imagePixels.get(i);
      ArrayList<IPixel> newPixels = new ArrayList<>();
      for (int j = 0; j < pixels.size(); j++) {
        newPixels.add(pixels.get(j));
      }
      newImagePixels.add(newPixels);
    }
    return new Image(newImagePixels);
  }
}