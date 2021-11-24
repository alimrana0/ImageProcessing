
import controller.ImageProcessorGUIController;

import model.IImageProcessingSession;

/**
 * A fake controller used to check that the controller is receiving the correct input from the
 * view.
 */
public class MockController extends ImageProcessorGUIController {

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
  public void handleSaveLayerEvent(String filename, String filetype) {
    log.append("\nsave " + filename + " " + filetype);
  }


  @Override
  public void handleLoadLayerEvent(String filename, String filetype, String layerName) {
    log.append("\nsave " + filename + " " + filetype + " " + layerName);
  }

  @Override
  public void handleSaveAllLayerEvent(String fileName, String fileType)
      throws IllegalStateException {
    log.append("\nsaveALL " + fileName + " " + fileType);

  }

  @Override
  public void handleLoadAllLayerEvent(String filename) {
    log.append("\nhandle load all layer");
  }

  @Override
  public void handleBlurEvent() {
    log.append("\nblur");
  }

  @Override
  public void handleSharpenEvent() {

    log.append("\nsharpen");
  }

  @Override
  public void handleVerticalFlipEvent() {
    log.append("\nvertical");
  }

  @Override
  public void handleHorizontalFlipEvent() {
    log.append("\nhorizontal");
  }

  @Override
  public void handleGrayscaleEvent() {
    log.append("\ngrayscale");
  }

  @Override
  public void handleSepiaEvent() {
    log.append("\nsepia");
  }

  @Override
  public void handleRedEvent() {
    log.append("\nred");
  }

  @Override
  public void handleGreenEvent() {
    log.append("\ngreen");
  }

  @Override
  public void handleBlueEvent() {
    log.append("\nblue");
  }

  @Override
  public void handleValueEvent() {
    log.append("\nvalue");
  }

  @Override
  public void handleIntensityEvent() {

    log.append("\nintensity");
  }

  @Override
  public void handleDarkenEvent(int val) {
    log.append("\ndarken" + val);
  }

  @Override
  public void handleBrightenEvent(int val) {
    log.append("\nbrighten" + val);
  }

  @Override
  public void showEvent() {
    log.append("\nshowEvent");
  }

  @Override
  public void hideEvent() {
    log.append("\nhideEvent");
  }

  @Override
  public void removeLayerEvent() {
    log.append("\nremoveLayer");
  }

  @Override
  public void setCurrentLayerEvent(String layerID) {
    log.append("\nset layer" + layerID);
  }

  @Override
  public void runScriptEvent(String filename) {
    log.append("\nScript" + filename);
  }

}