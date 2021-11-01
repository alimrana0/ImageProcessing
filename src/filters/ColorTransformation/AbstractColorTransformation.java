package filters.ColorTransformation;

import java.util.ArrayList;
import java.util.List;

import model.Imaging.Image;
import model.Imaging.ImageOfPixel;
import model.Imaging.pixel.IPixel;

public abstract class AbstractColorTransformation implements IColorTransform {


  public ImageOfPixel applyColorTransformation(ImageOfPixel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    return new Image(transform(imagePixels));
  }

  /**
   * Applies the given transformation matrix to each pixel in the supplied image.
   *
   * @param imagePixels Pixels of the image.
   * @return The updated pixels with the transformation applied.
   */
  protected List<ArrayList<IPixel>> transform(List<List<IPixel>> imagePixels) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p));
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  protected abstract IPixel colorTransform(IPixel p);




}

