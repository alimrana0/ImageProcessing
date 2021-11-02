import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import model.ImageProcessingModel;
import model.ImageProcessingSession;
import org.junit.Test;
import view.IImageProcessingView;
import view.ImageProcessingView;


public class ControllerTest {

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
    String inputString = "load\nkoala.ppm\nkoala\n"
        + "save\nkoala-saved.ppm\nkoala\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    StringBuilder mockLog = new StringBuilder();
    ImageProcessingSession session = new MockImageProcessingSession(mockLog);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog = "\n"
        + "load koala.ppm with name koala\n"
        + "save image as koala-saved.ppm from koala";

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
    String inputString = "load\nkoala.ppm\nkoala\n"
        + "vertical-flip\nkoala\nkoala-vertical\n"
        + "horizontal-flip\nkoala-vertical\nkoala-horizontal\nq";
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
            + "load koala.ppm with name koala\n"
            + "vertically flipping koala, store as koala-vertical\n"
            + "horizontally flipping koala-vertical, store as koala-horizontal";

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
    String inputString = "load\nkoala.ppm\nkoala\n"
        + "get-component\nred\nkoala\nkoala-red\n"
        + "get-component\ngreen\nkoala-red\nkoala-green\n"
        + "get-component\nblue\nkoala-green\nkoala-blue\n"
        + "get-component\nvalue\nkoala-blue\nkoala-value\n"
        + "get-component\nluma\nkoala-value\nkoala-luma\n"
        + "get-component\nintensity\nkoala-luma\nkoala-intensity\n"
        + "horizontal-flip\nkoala-intensity\nkoala-flipped\nq";
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
            + "load koala.ppm with name koala\n"
            + "get component red from koala, store as koala-red\n"
            + "get component green from koala-red, store as koala-green\n"
            + "get component blue from koala-green, store as koala-blue\n"
            + "get component value from koala-blue, store as koala-value\n"
            + "get component luma from koala-value, store as koala-luma\n"
            + "get component intensity from koala-luma, store as koala-intensity\n"
            + "horizontally flipping koala-intensity, store as koala-flipped";

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
    String inputString = "load\nkoala.ppm\nkoala\n"
        + "darken\n4\nkoala\nkoala-dark\n"
        + "brighten\n4\nkoala-dark\nkoala-bright\n"
        + "flip-horizontal\nkoala-bright\nkoala\nq";
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
            + "load koala.ppm with name koala\n"
            + "get component red from koala, store as koala-red\n"
            + "get component green from koala-red, store as koala-green\n"
            + "get component blue from koala-green, store as koala-blue\n"
            + "get component value from koala-blue, store as koala-value\n"
            + "get component luma from koala-value, store as koala-luma\n"
            + "get component intensity from koala-luma, store as koala-intensity\n"
            + "horizontally flipping koala-intensity, store as koala-flipped";

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

}
