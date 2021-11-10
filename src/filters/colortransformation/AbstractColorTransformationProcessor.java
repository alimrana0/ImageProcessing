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
   * Constructs an AbstractColorTransformation using a given kernel.
   *
   * @param kernel kernel being applied to the image.
   * @throws IllegalArgumentException If the kernel is null or not 3x3.
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
    return new Image(applyChange(imagePix));
  }

  /**
   * Applies a color transformation to every pixel in a given image of pixels.
   *
   * @param imagePix the image's pixels.
   * @return the transformed 2D pixel array.
   */
  protected List<ArrayList<IPixel>> applyChange(List<List<IPixel>> imagePix) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (List<IPixel> c : imagePix) {
      ArrayList<IPixel> temp = new ArrayList<>();
      for (IPixel p : c) {
        temp.add(changeColor(p));
      }
      newPixels.add(temp);
    }
    return newPixels;
  }

  /**
   * Applies the color transformation to the given pixel. Values are clamped to 0 255.
   *
   * @param pixel Pixel being transformed.
   * @return The transformed pixel.
   */
  protected IPixel changeColor(IPixel pixel) {

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


