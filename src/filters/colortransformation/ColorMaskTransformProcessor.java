package filters.colortransformation;

import java.util.ArrayList;
import java.util.List;

import filters.FilterClamp;
import filters.IKernel;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;

public class ColorMaskTransformProcessor {

  protected IKernel kernel;
  protected double[][] kernelValues;

  /**
   * Constructs an AbstractColorMaskTransformProcessor using a given kernel.
   *
   * @param kernel kernel being applied to the image.
   * @throws IllegalArgumentException If the kernel is null or not 3x3.
   */
  protected ColorMaskTransformProcessor(IKernel kernel) throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel can't be null");
    }
    if (kernel.getHeight() != 3 || kernel.getWidth() != 3) {
      throw new IllegalArgumentException("Kernel must be 3 x 3.");
    }
    this.kernel = kernel;
    this.kernelValues = kernel.getValues();

  }

  /**
   * Applies some transformation on the color of the given image based on the pixels in a masked
   * image.
   *
   * @param iop Image being transformed.
   * @param maskedImage black and white version of the iop
   * @return The transformed image.
   * @throws IllegalArgumentException If an image is null.
   */
  public ImageOfPixel transform(ImageOfPixel iop, ImageOfPixel maskedImage)
          throws IllegalArgumentException {
    if (iop == null || maskedImage == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePix = iop.getPixels();
    List<Posn> maskedPixelsPosns = storeBlack(maskedImage.getPixels());

    return new Image(applyTransform(imagePix, maskedPixelsPosns));
  }

  /**
   * Stores the black pixels of the mask image in a list.
   *
   * @param maskedPixels the 2D list of pixels of the mask image
   * @return a list of Posns for the black pixels in the mask
   */
  protected List<Posn> storeBlack(List<List<IPixel>> maskedPixels) {
    List<Posn> blackPixels = new ArrayList();
    for (int i = 0; i < maskedPixels.size(); i++) {
      for (int j = 0; j < maskedPixels.get(0).size(); j++) {
        if (maskedPixels.get(i).get(j).getColor().getRed() == 0
                && maskedPixels.get(i).get(j).getColor().getBlue() == 0
                && maskedPixels.get(i).get(j).getColor().getGreen() == 0) {
          blackPixels.add(maskedPixels.get(i).get(j).getPosn());
        } // Decided not to error handle a non-black-and-white image so that the black pixels of
        //any image can be transferred onto an image
//        else if ((maskedPixels.get(i).get(j).getColor().getRed() != 255)
//                || (maskedPixels.get(i).get(j).getColor().getGreen() != 255)
//        || (maskedPixels.get(i).get(j).getColor().getBlue() != 255)){
//          throw new IllegalArgumentException("Image is not black and white");
//        }
      }
    }
    return blackPixels;
  }

  /**
   * Applies the given transformation to each pixel in the given image under the condition of the
   * masked pixels corresponding to the image's pixel positions being contained in a list.
   *
   * @param imagePix the image's pixels.
   * @param maskedPixelPosns the list of pixel positions of the masked image's black pixels
   * @return the transformed 2D pixel array.
   */
  protected List<ArrayList<IPixel>> applyTransform(List<List<IPixel>> imagePix,
                                                   List<Posn> maskedPixelPosns) {
    List<ArrayList<IPixel>> newPixels = new ArrayList<>();
    for (List<IPixel> c : imagePix) {
      ArrayList<IPixel> r = new ArrayList<>();
      for (IPixel p : c) {
        r.add(colorTransform(p, maskedPixelPosns));
      }
      newPixels.add(r);
    }
    return newPixels;
  }

  /**
   * Applies the color transformation to the given pixel. Values are clamped to 0 255.
   *
   * @param pixel Pixel being transformed.
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

  /**
   * Method to transform a pixel's color based on the positions contained in the list of
   * masked pixel positions.
   * @param pixel the pixel being transformed
   * @param maskedPixelPosns the list of pixel positions of the black pixels of the mask image
   * @return the transformed pixel.
   */
  protected IPixel colorTransform(IPixel pixel, List<Posn> maskedPixelPosns) {
    if (maskedPixelPosns.contains(pixel.getPosn())) {
      return colorTransform(pixel);
    }
    return pixel;
  }

}
