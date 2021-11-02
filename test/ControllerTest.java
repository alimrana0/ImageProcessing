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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null),outBuffer);
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
  public void controllerLoadTest() throws IOException {
    String inputString = "load\nkoala.ppm\nkoala\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null),outBuffer);
    ImageProcessingSession session = new ImageProcessingSession();

    ImageController controller = new ImageControllerImpl(session, view,input);
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
            + "Image Loaded";
    assertEquals(expectedOutput, actualOutput);
  }

}