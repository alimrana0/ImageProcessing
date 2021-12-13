package filters.colortransformation;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;

public abstract class AbstractColorMaskTransform {

  /**
   * Applies a color transformation to a given image.
   * @param image Image being transformed
   * @param maskedImage the mask of the image
   * @return the image transformed based on the mask's pixels
   * @throws IllegalArgumentException if an image is null
   */
  public ImageOfPixel applyColorTransformation(ImageOfPixel image, ImageOfPixel maskedImage)
          throws IllegalArgumentException {
    if (image == null || maskedImage == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    List<Posn> maskedPixelsPosns = storeBlack(maskedImage.getPixels());

    return new Image(transform(imagePixels, maskedPixelsPosns));
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
   * Applies the given transformation to each pixel in the given image under the condition of the
   * masked pixels corresponding to the image's pixel positions being contained in a list.
   *
   * @param imagePixels the image's pixels.
   * @param maskedPixelPosns the list of positions of the masked image's black pixels
   * @return the transformed image pixels
   */
  protected List<ArrayList<IPixel>> transform(List<List<IPixel>> imagePixels,
                                              List<Posn> maskedPixelPosns) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(colorTransform(p,maskedPixelPosns));
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  /**
   * Abstract method to transform a pixel's color based on the positions contained in the list of
   * masked pixel positions.
   * @param p the pixel being transformed
   * @param maskedPixelPosns the list of pixel positions of the black pixels of the mask image
   * @return the transformed pixel.
   */
  protected abstract IPixel colorTransform(IPixel p, List<Posn> maskedPixelPosns);




}
