package filters.intensitytransformation;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;

abstract public class AbstractIntensityMaskTransform {

  /**
   * Empty constructor for an abstract intensity transformation.
   */
  protected AbstractIntensityMaskTransform() {
    //Doesn't need any initializations.
  }

  /**
   * Applies the intensity transformation to the given pixel. If the RGB value is out of range
   * 0-255, it will be clamped to the value.
   *
   * @param pixel the pixel being transformed
   * @return the transformed pixel
   */
  protected abstract IPixel intensityTransform(IPixel pixel, int val, List<Posn> maskedImagePosns);
  /**
   * Applies some transformation on the intensity of a given image.
   *
   * @param image image being transformed.
   * @return The transformed image.
   * @throws IllegalArgumentException If the image is null.
   */
  public ImageOfPixel applyTransformation(ImageOfPixel image, int val, ImageOfPixel maskedImage)
          throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't' be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    List<List<IPixel>> maskedPixels = maskedImage.getPixels();
    List<Posn> maskedPixelsPosns = storeBlack(maskedPixels);

    return new Image(transform(imagePixels, val, maskedPixelsPosns, maskedImage));
  }

  /**
   * Stores the black pixels of the mask image in a list.
   * @param maskedPixels the 2D list of pixels of the mask image
   * @return a list of Posns for the black pixels in the mask
   */
  protected List<Posn> storeBlack(List<List<IPixel>> maskedPixels)  {
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
   * Applies the intensity transformation on a given image.
   *
   * @param imageOfPixels the image's pixels.
   * @param val           the value that the image is being intensified by.
   * @return the list of transformed pixels.
   */
  protected List transform(List<List<IPixel>> imageOfPixels,
                           int val, List<Posn> maskedPixelPosns, ImageOfPixel maskedImage) {
    List<ArrayList<IPixel>> updated = new ArrayList<>();
    for (List<IPixel> r : imageOfPixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : r) {
        row.add(intensityTransform(p, val, maskedPixelPosns));
      }
      updated.add(row);
    }
    return updated;
  }
}
