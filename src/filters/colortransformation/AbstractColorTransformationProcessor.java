package filters.colortransformation;

import filters.FilterClamp;
import filters.IFilter;
import filters.IKernel;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractColorTransformationProcessor implements IFilter {

  protected IKernel kernel;
  protected double[][] kernelValues;

  /**
   * Creates an AbstractColorTransformation using a provided kernel.
   *
   * @param kernel kernel to be applied to each pixel in an image.
   * @throws IllegalArgumentException If the supplied kernel is not 3 x 3 or is null.
   */
  protected AbstractColorTransformationProcessor(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel can't be null");
    }
    if (kernel.getHeight() != 3 || kernel.getWidth()!= 3) {
      throw new IllegalArgumentException("Kernel must be 3 x 3.");
    }
    this.kernel = kernel;
    this.kernelValues = kernel.getValues();

  }


  /**
   * Applies some transformation on the color of the given image.
   *
   * @param iop Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
  public ImageOfPixel transform(ImageOfPixel iop) throws IllegalArgumentException {
    if (iop == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePix = iop.getPixels();
    return new Image(applyTransform(imagePix));
  }

  /**
   * Applies the correct transformation to an image and its pixels.
   *
   * @param imagePix Pixels of the image.
   * @return The updated pixels with the transformation applied.
   */
  protected List<ArrayList<IPixel>> applyTransform( List<List<IPixel>> imagePix) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (List<IPixel> c : imagePix) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (IPixel p : c) {
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

    int changedRed = (int) (pixel.getColor().getRed() * this.kernelValues[0][0] 
            + pixel.getColor().getGreen() * this.kernelValues[0][1]
            + pixel.getColor().getBlue() * this.kernelValues[0][2]);
    int changedGreen = (int) (pixel.getColor().getRed() * this.kernelValues[1][0] 
            + pixel.getColor().getGreen() * this.kernelValues[1][1]
            + pixel.getColor().getBlue() * this.kernelValues[1][2]);
    int changedBlue = (int) (pixel.getColor().getRed() * this.kernelValues[2][0] 
            + pixel.getColor().getGreen() * this.kernelValues[2][1]
            + pixel.getColor().getBlue() * this.kernelValues[2][2]);

    changedRed = FilterClamp.clamp(changedRed);
    changedGreen = FilterClamp.clamp(changedGreen);
    changedBlue = FilterClamp.clamp(changedBlue);

    return new Pixel(new Posn(pixel.getPosn().getX(), pixel.getPosn().getY()), new Color(changedRed,
            changedGreen, changedBlue));

  }


}


