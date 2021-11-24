import controller.ImageProcessorGUIController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import model.IImageProcessingSession;
import model.ImageProcessingSessionImpl;
import model.graph.Histogram;
import model.graph.Line;
import model.graph.Position2D;
import model.imaging.IColor;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;
import org.junit.Test;
import view.IViewListener;
import view.ImageProcessorGUIViewImpl;

import static org.junit.Assert.assertEquals;


public class ControllerWindowTest {


  @Test
  public void test() {

    StringBuilder sB = new StringBuilder();
    IImageProcessingSession session = new ImageProcessingSessionImpl();
    MockController mockController = new MockController(sB,session);
    ImageProcessorGUIViewImpl mockView = new MockWindow(mockController);


    ActionEvent save = new ActionEvent(this, 1, "save");
    ActionEvent blur = new ActionEvent(this, 2, "Blur");
    ActionEvent sharpen = new ActionEvent(this, 3, "Sharpen");
    ActionEvent grayscale = new ActionEvent(this, 4, "Grayscale");
    ActionEvent sepia = new ActionEvent(this, 5, "Sepia");
    ActionEvent red = new ActionEvent(this, 6, "Red Component");
    ActionEvent blue = new ActionEvent(this, 7, "Blue Component");
    ActionEvent green = new ActionEvent(this, 8, "Green Component");
    ActionEvent value = new ActionEvent(this, 9, "Value Component");
    ActionEvent intensity = new ActionEvent(this, 10, "Intensity Component");
    ActionEvent darken = new ActionEvent(this, 11, "Darken");
    ActionEvent brighten = new ActionEvent(this, 12, "Brighten");
    ActionEvent vertical = new ActionEvent(this, 13, "Vertical Flip");
    ActionEvent horizontal = new ActionEvent(this, 14, "Horizontal Flip");
    ActionEvent add = new ActionEvent(this, 15, "Add Image");
    ActionEvent delete = new ActionEvent(this, 16, "Delete Image");
    mockView.actionPerformed(save);
    mockView.actionPerformed(blur);
    mockView.actionPerformed(sharpen);
    mockView.actionPerformed(grayscale);
    mockView.actionPerformed(sepia);
    mockView.actionPerformed(red);
    mockView.actionPerformed(blue);
    mockView.actionPerformed(save);
    mockView.actionPerformed(green);
    mockView.actionPerformed(value);
    mockView.actionPerformed(intensity);
    mockView.actionPerformed(darken);
    mockView.actionPerformed(brighten);
    mockView.actionPerformed(vertical);
    mockView.actionPerformed(horizontal);
    mockView.actionPerformed(add);
    mockView.actionPerformed(delete);

    String actualOutput = mockController.getLog().toString();
    String expectedOutput = "\n"
        + "blur\n"
        + "sharpen\n"
        + "grayscale\n"
        + "sepia\n"
        + "red\n"
        + "blue\n"
        + "green\n"
        + "value\n"
        + "intensity\n"
        + "darken5\n"
        + "brighten3\n"
        + "vertical\n"
        + "horizontal\n"
        + "save name type name\n"
        + "removeLayer";

    assertEquals(expectedOutput, actualOutput);
  }

}
