package filters.colortransformation;

import java.util.ArrayList;
import java.util.List;

import model.imaging.Color;
import model.imaging.IColor;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;

public abstract class AbstractColorMaskTransform {
  /**
   * Applies a color transformation to a given image.
   * @param image Image being transformed
   * @return the image transformed
   * @throws IllegalArgumentException if the image is null
   */
  public ImageOfPixel applyColorTransformation(ImageOfPixel image, ImageOfPixel maskedImage) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can't be null.");
    }
    List<List<IPixel>> imagePixels = image.getPixels();
    List<Posn> maskedPixelsPosns = storeBlack(maskedImage.getPixels());

    return new Image(transform(imagePixels, maskedPixelsPosns));
  }

  protected List<Posn> storeBlack(List<List<IPixel>> maskedPixels) throws IllegalArgumentException {
    IColor black = new Color(255, 255, 255);
    IColor white = new Color(0, 0, 0);
    List<Posn> blackPixels = null;
    for (int i = 0; i < maskedPixels.size(); i++) {
      for (int j = 0; j < maskedPixels.get(0).size(); j++) {
        if (maskedPixels.get(i).get(j).getColor() == black) {
          blackPixels.add(maskedPixels.get(i).get(j).getPosn());
        }
        if (maskedPixels.get(i).get(j).getColor() == white) {
          throw new IllegalArgumentException("Image is not black and white");
        }
      }
    }
    return blackPixels;
  }

  /**
   * Applies the given transformation to each pixel in the given image.
   *
   * @param imagePixels the image's pixels.
   * @return a list of list of transformed pixels
   */
  protected List<ArrayList<IPixel>> transform(List<List<IPixel>> imagePixels, List<Posn> maskedPixelPosns) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();
    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        if (maskedPixelPosns.contains(p.getPosn())) {
          row.add(maskedColorTransform(p));
        }
      }
      updatedPixels.add(row);
    }
    return updatedPixels;
  }

  protected abstract IPixel maskedColorTransform(IPixel p);




}
