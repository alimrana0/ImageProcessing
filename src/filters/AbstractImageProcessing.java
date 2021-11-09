package filters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

/**
 * A class to represent a filter on an image. The filter is applied using the given kernel.
 * kernel must be square with odd dimensions. 1x1, 3x3, 5x5, etc. Uses a kernel to apply a
 * filter to an image.
 */
public abstract class AbstractImageProcessing implements IFilter {

  protected final IKernel kernel;

  /**
   * Creates and instance of the abstract filter with the given kernel.
   *
   * @param kernel kernel to be associated with the filter.
   * @throws IllegalArgumentException If th kernel is null, it does not have odd dimensions, or it
   *                                  is not square.
   */
  protected AbstractImageProcessing(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (kernel.getHeight() % 2 == 0 || kernel.getWidth() % 2 == 0) {
      throw new IllegalArgumentException("Dimensions of kernel must be odd.");
    }
    this.kernel = kernel;
  }

  @Override
  public ImageOfPixel transform(ImageOfPixel image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();

    return new Image(filtered(imagePixels, image));
  }

  /**
   * Returns the pixels of the image after the filter has been applied.
   *
   * @param pixels Pixels of the image to be filtered.
   * @param image  Image to be filtered.
   * @return The filtered pixels.
   */
  protected List<ArrayList<IPixel>> filtered(List<List<IPixel>> pixels,
                                             ImageOfPixel image) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        r.add(filter(pixels.get(i).get(j), image));
      }
      newPixels.add(r);
    }

    return newPixels;

  }

  /**
   * Applies the filter to the given pixel using the filter's kernel. RGB values are clamped at a
   * max of 255 and a min of 0.
   *
   * @param pixel Pixel to be filtered.
   * @param image Original image.
   * @return The filtered pixel
   */
  protected IPixel filter(IPixel pixel, ImageOfPixel image) {

    List<List<IPixel>> imagePixels = image.getPixels();

    int peripherals = kernel.getHeight() / 2;

    int r = 0;
    int g = 0;
    int b = 0;

    for (int i = peripherals * -1; i <= peripherals; i++) {
      for (int j = peripherals * -1; j <= peripherals; j++) {
        try {
          double kVal = (double) Array
                  .get(Array.get(kernel.getValues(), i + peripherals), j + peripherals);

          int red = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getRed();
          int green = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getGreen();
          int blue = imagePixels.get(pixel.getPosn().getY() + i)
                  .get(pixel.getPosn().getX() + j).getColor().getBlue();

          red *= kVal;
          green *= kVal;
          blue *= kVal;

          r += red;
          g += green;
          b += blue;

        } catch (IndexOutOfBoundsException ignore) {
          r += 0;
          g += 0;
          b += 0;
        }
      }
    }

    r = FilterClamp.clamp(r);
    g = FilterClamp.clamp(g);
    b = FilterClamp.clamp(b);

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(r, g, b));


  }


}