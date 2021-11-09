package filters.colortransformation;

import java.util.ArrayList;
import java.util.List;

import filters.FilterClamp;
import filters.IFilter;
import filters.IKernel;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;


public abstract class AbstractColorTransformationProcessor implements IFilter {

  protected IKernel kernel;

  /**
   * Constructs an AbstractColorTransformation using a given kernel.
   *
   * @param kernel kernel to be applied to each pixel in an image.
   * @throws IllegalArgumentException If the given kernel is null or not 3 x 3.
   */
  protected AbstractColorTransformationProcessor(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    if (kernel.getWidth() != 3 || kernel.getHeight() != 3) {
      throw new IllegalArgumentException("kernel must be 3 x 3.");
    }
    this.kernel = kernel;
  }


  /**
   * Applies a transformation on an image's color.
   *
   * @param iop Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  public ImageOfPixel transform(ImageOfPixel iop) throws IllegalArgumentException {
    if (iop == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<List<IPixel>> imagePixels = iop.getPixels();
    return new Image(applyTransform(imagePixels));
  }

   /**
   * Applies the correct transformation to an image and its pixels.
   *
   * @param imagePixels Pixels of the image.
   * @return The updated pixels with the transformation applied.
   */
  protected List<ArrayList<IPixel>> applyTransform(List<List<IPixel>> imagePixels) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (IPixel p : l) {
        r.add(colorTransform(p));
      }
      newPixels.add(r);
    }
    return newPixels;
  }

  /**
   * Applies the given transformation to the given pixel by updating its rgb values. Any out of
   * range rgb value is clamped to the minimum value of 0 or the maximum value of 255.
   *
   * @param pixel Pixel to transform.
   * @return The transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel) {

    int changedRed = (int) (pixel.getColor().getRed() * this.kernel.getValueAt(0,0)
            + pixel.getColor().getGreen() * this.kernel.getValueAt(0,1)
            + pixel.getColor().getBlue() * this.kernel.getValueAt(0,2));
    int changedGreen = (int) (pixel.getColor().getRed() * this.kernel.getValueAt(1,0)
            + pixel.getColor().getGreen() * this.kernel.getValueAt(1,1)
            + pixel.getColor().getBlue() * this.kernel.getValueAt(1,2));
    int changedBlue = (int) (pixel.getColor().getRed() * this.kernel.getValueAt(2,0)
            + pixel.getColor().getGreen() * this.kernel.getValueAt(2,1)
            + pixel.getColor().getBlue() * this.kernel.getValueAt(2,2));

    changedRed = FilterClamp.clamp(changedRed);
    changedGreen = FilterClamp.clamp(changedGreen);
    changedBlue = FilterClamp.clamp(changedBlue);

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            changedGreen, changedBlue));

  }



}


