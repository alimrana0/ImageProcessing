import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import jdk.jshell.execution.Util;
import model.ImageProcessingModel;
import model.ImageProcessingSession;
import util.ImageUtil;
import model.imaging.Image;
import model.imaging.ImageOfPixel;
import model.imaging.pixel.IPixel;
import org.junit.Test;
import view.IImageProcessingView;
import view.ImageProcessingView;

/**
 * Test for the ImageController class.
 */
public class ControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullModelConstructor() throws IllegalArgumentException {
    String inputString = "load\nstars.ppm\nstars\n";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    ImageProcessingSession session = new ImageProcessingSession();
    IImageProcessingView view = new MockImageProcessingView(new ImageProcessingModel(null));
    ImageController controller = new ImageControllerImpl(null, view, input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullViewConstructor() throws IllegalArgumentException {
    String inputString = "load\nstars.ppm\nstars\n";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    ImageProcessingSession session = new ImageProcessingSession();
    IImageProcessingView view = new MockImageProcessingView(new ImageProcessingModel(null));
    ImageController controller = new ImageControllerImpl(session, null, input);
  }


  @Test(expected = IllegalArgumentException.class)
  public void nullInputConstructor() throws IllegalArgumentException {
    String inputString = "load\nstars.ppm\nstars\n";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    ImageProcessingSession session = new ImageProcessingSession();
    IImageProcessingView view = new MockImageProcessingView(new ImageProcessingModel(null));
    ImageController controller = new ImageControllerImpl(session, view, null);
  }

  @Test
  public void controllerTest() throws IOException {
    String inputString = "Q";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);
    ImageProcessingSession session = new ImageProcessingSession();
    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit";

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void controllerLoadSaveTest() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "save\nstars-saved.ppm\nstars\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new MockImageProcessingSession(mockLog);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog = "\n"
        + "load stars.ppm with name stars\n"
        + "save image as stars-saved.ppm from stars";

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Image saved";
    assertEquals(expectedOutput, actualOutput);
    assertEquals(expectedLog, mockLog.toString());
  }

  @Test
  public void controllerFlipTest() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "vertical-flip\nstars\nstars-vertical\n"
        + "horizontal-flip\nstars-vertical\nstars-horizontal\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new MockImageProcessingSession(mockLog);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog =
        "\n"
            + "load stars.ppm with name stars\n"
            + "vertically flipping stars, store as stars-vertical\n"
            + "horizontally flipping stars-vertical, store as stars-horizontal";

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Vertical Image made\n"
        + "Horizontal Image made";
    assertEquals(expectedOutput, actualOutput);
    assertEquals(expectedLog, mockLog.toString());
  }

  @Test
  public void controllerComponentTest() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "get-component\nred\nstars\nstars-red\n"
        + "get-component\ngreen\nstars-red\nstars-green\n"
        + "get-component\nblue\nstars-green\nstars-blue\n"
        + "get-component\nvalue\nstars-blue\nstars-value\n"
        + "get-component\nluma\nstars-value\nstars-luma\n"
        + "get-component\nintensity\nstars-luma\nstars-intensity\n"
        + "horizontal-flip\nstars-intensity\nstars-flipped\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new MockImageProcessingSession(mockLog);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog =
        "\n"
            + "load stars.ppm with name stars\n"
            + "get component red from stars, store as stars-red\n"
            + "get component green from stars-red, store as stars-green\n"
            + "get component blue from stars-green, store as stars-blue\n"
            + "get component value from stars-blue, store as stars-value\n"
            + "get component luma from stars-value, store as stars-luma\n"
            + "get component intensity from stars-luma, store as stars-intensity\n"
            + "horizontally flipping stars-intensity, store as stars-flipped";

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Component Image made\n"
        + "Component Image made\n"
        + "Component Image made\n"
        + "Component Image made\n"
        + "Component Image made\n"
        + "Component Image made\n"
        + "Horizontal Image made";
    assertEquals(expectedOutput, actualOutput);
    assertEquals(expectedLog, mockLog.toString());
  }


  @Test
  public void controllerBrightenDarkenTest() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "darken\n4\nstars\nstars-dark\n"
        + "brighten\n4\nstars-dark\nstars-bright\n"
        + "horizontal-flip\nstars-bright\nstars\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new MockImageProcessingSession(mockLog);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog =
        "\n"
            + "load stars.ppm with name stars\n"
            + "darkening stars by 4, store as stars-dark\n"
            + "brightening stars-dark by 4, store as stars-bright\n"
            + "horizontally flipping stars-bright, store as stars";

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Darkened Image made\n"
        + "Brightened Image made\n"
        + "Horizontal Image made";
    assertEquals(expectedOutput, actualOutput);
    assertEquals(expectedLog, mockLog.toString());
  }


  @Test
  public void controllerInvalidEntries() throws IOException {
    String inputString = "not a commmand q";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();

    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Unknown Command Entered\n"
        + "Unknown Command Entered\n"
        + "Unknown Command Entered";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void controllerInvalidImageName() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "save\nstars-saved.ppm\nstarssssssssss\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Invalid model name";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void controllerInvalidLoad() throws IOException {
    String inputString = "load\nstarsx.ppm\nstars\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "File not Found";
    assertEquals(expectedOutput, actualOutput);
  }

  //TODO ADD ERROR CONTROL FOR INVALID STRING
  @Test
  public void controllerInvalidCommandInputs() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "get-component\nINVALID\nstars\nstars\n"
        + "get-component\nred\nNOIMAGE\nstars\n"
        + "brighten\nnotint\n"
        + "brighten\n0\nNOIMAGE\nstars\n"
        + "darken\nnotint\n"
        + "darken\n0\nNOIMAGE\nnewName\n"
        + "vertical-flip\nNOIMAGE\nnewName\n"
        + "horizontal-flip\nNOIMAGE\nnewName\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "invalid component\n"
        + "invalid inputs\n"
        + "Must enter an integer\n"
        + "invalid inputs\n"
        + "Must enter an integer\n"
        + "invalid inputs\n"
        + "invalid inputs\n"
        + "invalid inputs";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test(expected = IOException.class)
  public void badView() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new MockImageProcessingView(new ImageProcessingModel(null));
    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();
  }



  @Test
  public void controllerSaveAndLoadAsAllTypes() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "save\nstarsJPG.jpg\nstars\n"
        + "save\nstarsPNG.png\nstars\n"
        + "save\nstarsBMP.bmp\nstars\n"
        + "save\nstarsPPM.ppm\nstars\n"
        + "load\nstarsJPG.jpg\nstarsJPG\n"
        + "load\nstarsPNG.png\nstarsPNG\n"
        + "load\nstarsBMP.bmp\nstarsBMP\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "Commands:\n"
        + "load filepath name\n"
        + "save saveLocation name\n"
        + "get-component component \n"
        + "horizontal-flip\n"
        + "vertical-flip\n"
        + "brighten\n"
        + "darken\n"
        + "Q or q to quit\n"
        + "Image Loaded\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Image Loaded";
    assertEquals(expectedOutput, actualOutput);
  }

}
