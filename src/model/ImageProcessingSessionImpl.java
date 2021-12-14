package model;

import filters.BlurFilter;
import filters.DownscaleTransform;
import filters.SharpenFilter;
import filters.colortransformation.GreyscaleTransformation;
import filters.colortransformation.IntensityChange;
import filters.colortransformation.SepiaTransformation;
import filters.colortransformation.ValueChange;
import filters.colortransformation.greyscale.BlueGreyscale;
import filters.colortransformation.greyscale.GreenGreyscale;
import filters.colortransformation.greyscale.RedGreyscale;
import filters.flippingtransformation.FlipHorizontal;
import filters.flippingtransformation.FlipVertical;
import filters.intensitytransformation.BrightenTransformation;
import filters.intensitytransformation.DarkenTransformation;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import model.imaging.Image;
import model.imaging.ImageOfPixel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.imaging.pixel.IPixel;
import util.ImageUtil;

/**
 * Class to represent and image processor session model that is able to load several images at the
 * same time, as well as change their visibility. Each instance may only have one multilayer, which
 * may contain multiple images.
 */
public class ImageProcessingSessionImpl implements IImageProcessingSession {

  private final Map<String, ImageOfPixel> images;
  private final List<String> ids;
  private final List<String> invisible;

  /**
   * Constructs a multilayered image processor model.
   *
   * @param ids       list of image ids.
   * @param invisible list of ids corresponding to the current invisible images.
   * @throws IllegalArgumentException If given null arguments.
   */
  public ImageProcessingSessionImpl(List<String> ids,
                                    List<String> invisible) throws IllegalArgumentException {
    if (ids == null || invisible == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    this.images = new HashMap<>();
    this.ids = ids;
    this.invisible = invisible;
  }

  /**
   * Constructs a multilayered image processor model without any given information.
   */
  public ImageProcessingSessionImpl() {
    this.images = new HashMap<>();
    this.ids = new ArrayList<>();
    this.invisible = new ArrayList<>();

  }

  @Override
  public ImageOfPixel blur(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    return new BlurFilter().transform(this.getImage(id));
  }

  @Override
  public ImageOfPixel sharpen(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    return new SharpenFilter().transform(this.getImage(id));
  }

  @Override
  public ImageOfPixel redGrayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new RedGreyscale().applyColorTransformation(this.getImage(id));
  }

  @Override
  public ImageOfPixel greenGrayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new GreenGreyscale().applyColorTransformation(this.getImage(id));
  }

  @Override
  public ImageOfPixel blueGrayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new BlueGreyscale().applyColorTransformation(this.getImage(id));
  }

  @Override
  public ImageOfPixel valueComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new ValueChange().applyColorTransformation(this.getImage(id));
  }

  @Override
  public ImageOfPixel intensityComponent(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new IntensityChange().applyColorTransformation(this.getImage(id));
  }


  @Override
  public ImageOfPixel grayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new GreyscaleTransformation().transform(this.getImage(id));
  }

  @Override
  public ImageOfPixel sepia(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new SepiaTransformation().transform(this.getImage(id));
  }


  @Override
  public ImageOfPixel darken(String id, int val) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new DarkenTransformation().applyTransformation(this.getImage(id), val);
  }

  @Override
  public ImageOfPixel brighten(String id, int val) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new BrightenTransformation().applyTransformation(this.getImage(id), val);
  }

  @Override
  public ImageOfPixel horizontalFlip(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new FlipHorizontal().flipTransform(this.getImage(id));
  }

  @Override
  public ImageOfPixel verticalFlip(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image can't be null");
    }
    return new FlipVertical().flipTransform(this.getImage(id));
  }

  @Override
  public ImageOfPixel downscale(String id, int newWidth, int newHeight) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new DownscaleTransform().scale(this.getImage(id), newWidth, newHeight);
  }
  @Override
  public void addMultipleImages(Map<String, ImageOfPixel> images, List<String> invisible)
          throws IllegalArgumentException {
    if (images == null || invisible == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    for (String id : this.ids) {
      if (id == null) {
        throw new IllegalArgumentException("Arguments can't be null.");
      }
      if (!this.images.containsKey(id)) {
        throw new IllegalArgumentException("No such image is contained");
      }
      this.images.remove(id);
    }
    this.ids.clear();
    this.invisible.clear();
    for (Map.Entry<String, ImageOfPixel> image : images.entrySet()) {
      this.addImage(image.getKey(), image.getValue());
    }
    this.invisible.addAll(invisible);
  }

  @Override
  public void addImage(String id, ImageOfPixel image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    if (this.ids.contains(id)) {
      throw new IllegalArgumentException("This ID is already in use.");
    }
    this.ids.add(id);
    if (this.images.containsKey(id)) {
      throw new IllegalArgumentException("An image exists with this ID.");
    }

    this.images.putIfAbsent(id, image);
  }

  @Override
  public void removeImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("String can't be null.");
    }
    if (!this.ids.contains(id)) {
      throw new IllegalArgumentException("No such ID.");
    }
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No image is contained under this ID.");
    }
    this.images.remove(id);
    this.ids.remove(id);
    this.invisible.remove(id);
  }

  @Override
  public void replaceImage(String id, ImageOfPixel image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    if (!this.ids.contains(id)) {
      throw new IllegalArgumentException("This ID is not already in use.");
    }
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No image is contained under this ID.");
    }
    this.images.replace(id, image);
  }

  @Override
  public ImageOfPixel getImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No image is contained under this ID.");
    }
    return this.images.get(id);
  }

  @Override
  public Map<String, ImageOfPixel> getImages() throws IllegalArgumentException {
    Map<String, ImageOfPixel> layers = new HashMap<>();
    for (String id : this.ids) {
      layers.put(id, this.getImage(id));
    }
    return layers;
  }

  @Override
  public void showImage(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String can't be null.");
    }
    if (!this.invisible.contains(id)) {
      throw new IllegalArgumentException("Image with id is already visible.");
    }
    if (!this.ids.contains(id)) {
      throw new IllegalArgumentException("No such ID.");
    }
    this.invisible.remove(id);
  }

  @Override
  public void hideImage(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String can't be null.");
    }
    if (this.invisible.contains(id)) {
      throw new IllegalArgumentException("This image is already invisible.");
    }
    if (!this.ids.contains(id)) {
      throw new IllegalArgumentException("No such ID.");
    }
    this.invisible.add(id);
  }

  @Override
  public List<String> getInvisible() {
    return new ArrayList<>(this.invisible);
  }

  @Override
  public List<ArrayList<IPixel>> updatePreview (int horizontal, int vertical, String operation, BufferedImage image) {
    int imgH = image.getHeight();
    int imgW = image.getWidth();
    int h = horizontal;
    int v = vertical;

    Image toBeModified = new Image(ImageUtil.readBuffered(image));
    List<ArrayList<IPixel>> pixels = new ArrayList<>();

    List<ArrayList<IPixel>> copy = toBeModified.getPixelsArraylist();

    for (int i = 0; i < imgH; i++) {
      ArrayList<IPixel> temp = new ArrayList<>();
      for (int j = 0; j < imgW; j++) {
        IPixel pixel;
        if ((i < 0 + v + 200 && i > 0 + v) && (j < 0 + h + 200 && j > 0 + h)) {
          pixel = copy.get(i).get(j);
          temp.add(pixel);
        }
      }
      if ((i < 0 + v + 200 && i > 0 + v)) {
        pixels.add(temp);
      }
    }

    //modify 200x200
    ImageProcessingModel square = new ImageProcessingModel(new Image(pixels));
    ImageOfPixel modified;
    switch (operation) {
      case "Red Grayscale":
        modified = square.redComponent();
        break;
      case "Green Grayscale":
        modified = square.greenComponent();
        break;
      case "Blue Grayscale":
        modified = square.blueComponent();
        break;
      case "Grayscale":
        modified = square.greyscale();
        break;
      case "Value Component":
        modified = square.valueImage();
        break;
      case "Intensity Component":
        modified = square.intensity();
        break;
      case "Blur":
        modified = square.blur();
        break;
      case "Sharpen":
        modified = square.sharpen();
        break;
      case "Sepia":
        modified = square.sepia();
        break;
      case "Brighten":
        modified = square.brighten(25);
        break;
      case "Darken":
        modified = square.darken(25);
        break;
      default:
        modified = null;
        break;
    }

    Queue<IPixel> q = new LinkedList<IPixel>();
    for (ArrayList<IPixel> list : modified.getPixelsArraylist()) {
      for (IPixel temp : list) {
        q.add(temp);
      }
    }

    for (int i = 0; i < imgH; i++) {
      for (int j = 0; j < imgW; j++) {
        if ((i < 0 + v + 200 && i > 0 + v) && (j < 0 + h + 200 && j > 0 + h)) {
          //IPixel pixel = modified.get(i - v).get(j - h);
          if (!q.isEmpty()) {
            copy.get(i).set(j, q.remove());
          }
        }
      }
    }
    return copy;
  }

}
