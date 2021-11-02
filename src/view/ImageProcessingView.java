package view;

import java.io.IOException;

import model.ImageProcessingModel;

/**
 * Class implementing IPhotoShopeView which allows the user to see messages and information about
 * the IPhotoShopLayerModel at the given Appendable.
 */

public class ImageProcessingView implements IImageProcessingView {

  private ImageProcessingModel model;
  private Appendable out;

  /**
   * Constructor that only takes in a model.
   *
   * @param model represents an IPhotoShopLayerModel that is used by the Controller
   */
  public ImageProcessingView(ImageProcessingModel model) {
    this(model, System.out);
  }

  /**
   * Constructor that takes in both a model and an output source.
   *
   * @param model an IPhotoShopLayerModel model
   * @param out   Output source
   */
  public ImageProcessingView(ImageProcessingModel model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    this.model = model;
    this.out = out;
  }



  public void renderMessage(String message) throws IOException {
    try {
      out.append(message);
    } catch (IOException io) {
      throw new IOException("Could not render Message");
    }
  }

  @Override
  public void showOptions() throws IOException {
    String options = "Commands:"
            + "\nload filepath name"
            + "\nsave saveLocation name"
            + "\nget-component component "
            + "\nhorizontal-flip"
            + "\nvertical-flip"
            + "\nbrighten"
            + "\ndarken"
            + "\nQ or q to quit";
    this.renderMessage(options);
  }
}