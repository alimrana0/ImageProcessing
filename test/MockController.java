import controller.ImageProcessorControllerImpl;
import controller.ImageProcessorGUIController;
import controller.filereading.IMultiLayerReader;
import controller.filereading.ImageIOFileReader;
import controller.filereading.MultiLayerFileReader;
import controller.filereading.PPMFileReader;
import controller.filewriting.IImageFileWriter;
import controller.filewriting.JPEGImageIOWriter;
import controller.filewriting.MultiLayerImageWriter;
import controller.filewriting.PNGImageIOWriter;
import controller.filewriting.PPMFileWriter;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.IImageProcessingSession;
import model.imaging.ImageOfPixel;
import view.IImageProcessingView;
import view.ImageProcessorTextView;

public class MockController extends ImageProcessorGUIController {

   StringBuilder log;


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