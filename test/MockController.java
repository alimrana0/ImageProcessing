
import controller.ImageProcessingControllerGUI;

import model.IImageProcessingSession;

/**
 * A fake controller used to check that the controller is receiving the correct input from the
 * view.
 */
public class MockController extends ImageProcessingControllerGUI {

  StringBuilder log;

  /**
   * Constructs a fake controller given a log to write to and a model.
   *
   * @param log   A stringbuilder that is used to write what method has been called.
   * @param model A model that is not used.
   */
  public MockController(StringBuilder log, IImageProcessingSession model) {
    super(model);
    this.log = log;
  }

  public StringBuilder getLog() {
    return this.log;
  }

  @Override
  public void saveLayerHandler(String filename, String filetype) {
    log.append("\nsave " + filename + " " + filetype);
  }


  @Override
  public void loadLayerHandler(String filename, String filetype, String layerName) {
    log.append("\nsave " + filename + " " + filetype + " " + layerName);
  }

  @Override
  public void saveAllHandler(String fileName, String fileType)
      throws IllegalStateException {
    log.append("\nsaveALL " + fileName + " " + fileType);

  }

  @Override
  public void loadAllHandler(String filename) {
    log.append("\nhandle load all layer");
  }

  @Override
  public void blurHandler() {
    log.append("\nblur");
  }

  @Override
  public void sharpenHandler() {

    log.append("\nsharpen");
  }

  @Override
  public void handleFlipVertical() {
    log.append("\nvertical");
  }

  @Override
  public void handleFlipHorizontal() {
    log.append("\nhorizontal");
  }

  @Override
  public void grayscaleHandler() {
    log.append("\ngrayscale");
  }

  @Override
  public void sepiaHandler() {
    log.append("\nsepia");
  }

  @Override
  public void redComponentHandler() {
    log.append("\nred");
  }

  @Override
  public void greenComponentHandler() {
    log.append("\ngreen");
  }

  @Override
  public void blueComponentHandler() {
    log.append("\nblue");
  }

  @Override
  public void valueHandler() {
    log.append("\nvalue");
  }

  @Override
  public void intensityHandler() {

    log.append("\nintensity");
  }

  @Override
  public void darkenHandler(int val) {
    log.append("\ndarken" + val);
  }

  @Override
  public void brightenHandler(int val) {
    log.append("\nbrighten" + val);
  }

  @Override
  public void show() {
    log.append("\nshowEvent");
  }

  @Override
  public void hide() {
    log.append("\nhideEvent");
  }

  @Override
  public void removeLayer() {
    log.append("\nremoveLayer");
  }

  @Override
  public void selectLayer(String id) {
    log.append("\nset layer" + id);
  }

  @Override
  public void useScript(String filename) {
    log.append("\nScript" + filename);
  }

}