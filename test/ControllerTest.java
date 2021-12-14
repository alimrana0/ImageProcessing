import static org.junit.Assert.assertEquals;

import controller.ImageController;
import controller.ImageControllerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import model.ImageProcessingModel;
import model.ImageProcessingSession;
import model.imaging.Color;
import model.imaging.Posn;
import model.imaging.pixel.Pixel;
import org.junit.Before;
import model.imaging.Image;
import model.imaging.pixel.IPixel;
import org.junit.Test;
import view.IImageProcessingView;
import view.ImageProcessingView;

/**
 * Test for the ImageController class.
 */
public class ControllerTest {

  //private Pixel emptyPixel = new Pixel(new Posn(0,0), new Color(0,0,0));
  private Image emptyImage;

  @Before
  public void setUp() {
    ArrayList<IPixel> pixelArray = new ArrayList<>();
    pixelArray.add(new Pixel(new Posn(0, 0), new Color(0, 0, 0)));
    List<ArrayList<IPixel>> basicArray = new ArrayList<>();
    basicArray.add(pixelArray);

    emptyImage = new Image(basicArray);
  }

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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);
    ImageProcessingSession session = new ImageProcessingSession();
    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();

    String expectedOutput = "";

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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog = "\n"
        + "load stars.ppm with name stars\n"
        + "save image as stars-saved.ppm from stars";

    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog =
        "\n"
            + "load stars.ppm with name stars\n"
            + "vertically flipping stars, store as stars-vertical\n"
            + "horizontally flipping stars-vertical, store as stars-horizontal";

    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

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

    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedLog =
        "\n"
            + "load stars.ppm with name stars\n"
            + "darkening stars by 4, store as stars-dark\n"
            + "brightening stars-dark by 4, store as stars-bright\n"
            + "horizontally flipping stars-bright, store as stars";

    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();

    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
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

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
        + "File not Found";
    assertEquals(expectedOutput, actualOutput);
  }

  //TODO ADD ERROR CONTROL FOR INVALID STRING
  @Test
  public void controllerInvalidCommandInputs() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "get-component\nINVALID\nstars\nstarsx\n"
        + "get-component\nred\nNOIMAGE\nnewImage\n"
        + "brighten\nnotint\n"
        + "brighten\n0\nNOIMAGE\nnewName\n"
        + "darken\nnotint\n"
        + "darken\n0\nNOIMAGE\nnewName\n"
        + "vertical-flip\nNOIMAGE\nnewName\n"
        + "horizontal-flip\nNOIMAGE\nnewName\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
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

    IImageProcessingView view = new MockImageProcessingView(new ImageProcessingModel(emptyImage));
    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();
  }


  @Test
  public void controllerSaveAndLoadAsAllTypes() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "save\nstars.jpg\nstars\n"
        + "save\nstarsPNG.png\nstars\n"
        + "save\nstarsBMP.bmp\nstars\n"
        + "save\nstarsPPM.ppm\nstars\n"
        + "load\nstars.jpg\nstarsJPG\n"
        + "load\nstarsPNG.png\nstarsPNG\n"
        + "load\nstarsBMP.bmp\nstarsBMP\n"
        + "load square.jpg mask\n"
        + "get-component red stars mask red-stars-mask\n"
        + "save\nstarsMask.jpg\nred-stars-mask\n"
        + "q";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
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

  @Test
  public void controllerGreySepiaSharpenBlurTest() throws IOException {
    String inputString = "load\nstars.ppm\nstars\n"
        + "greyscale\nstars\nstarsGreyscale\n"
        + "save\nstarsGreyscale.jpg\nstarsGreyscale\n"

        + "blur\nstars\nstarsBlur\n"
        + "save\nstarsBlur.jpg\nstarsBlur\n"

        + "sharpen\nstars\nstarsSharpen\n"
        + "save\nstarsSharpen.jpg\nstarsSharpen\n"


        + "sepia\nstars\nstarsSepia\n"
        + "save\nstarsSepia.jpg\nstarsSepia\nq";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
        + "Image Loaded\n"
        + "Greyscaled Image Made\n"
        + "Image saved\n"
        + "Blurred Image Made\n"
        + "Image saved\n"
        + "Sharpened Image Made\n"
        + "Image saved\n"
        + "Sepia Image Made\n"
        + "Image saved";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void scriptTest() throws IOException {
    String inputString = "-file script.txt";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
        + "Image Loaded\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Brightened Image made\n"
        + "Image saved\n"
        + "Darkened Image made\n"
        + "Image saved\n"
        + "Vertical Image made\n"
        + "Image saved\n"
        + "Horizontal Image made\n"
        + "Image saved\n"
        + "Greyscaled Image Made\n"
        + "Image saved\n"
        + "Sepia Image Made\n"
        + "Image saved\n"
        + "Blurred Image Made\n"
        + "Image saved\n"
        + "Sharpened Image Made\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Image Loaded";
    assertEquals(expectedOutput, actualOutput);
  }



  @Test
  public void scriptMaskTest() throws IOException {
    String inputString = "-file scriptMask.txt";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
        + "Image Loaded\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Brightened Image made\n"
        + "Image saved\n"
        + "Darkened Image made\n"
        + "Image saved\n"
        + "Vertical Image made\n"
        + "Image saved\n"
        + "Horizontal Image made\n"
        + "Image saved\n"
        + "Greyscaled Image Made\n"
        + "Image saved\n"
        + "Sepia Image Made\n"
        + "Image saved\n"
        + "Blurred Image Made\n"
        + "Image saved\n"
        + "Sharpened Image Made\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image saved\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Image Loaded\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Component Image made\n"
        + "Image saved\n"
        + "Brightened Image from mask made\n"
        + "Image saved\n"
        + "Darkened Image from mask made\n"
        + "Image saved\n"
        + "Greyscaled Mask Image Made\n"
        + "Image saved\n"
        + "Sepia Image Mask Made\n"
        + "Image saved\n"
        + "Blurred Image from mask made\n"
        + "Image saved\n"
        + "Sharpened Image from mask made\n"
        + "Image saved";
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void scriptDownscaleTest() throws IOException {
    String inputString = "-file scriptDownscale.txt";
    BufferedReader input = new BufferedReader(new StringReader(inputString));
    StringBuffer outBuffer = new StringBuffer();

    ImageProcessingSession session = new ImageProcessingSession();

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
        outBuffer);

    ImageController controller = new ImageControllerImpl(session, view, input);
    controller.run();

    String actualOutput = outBuffer.toString();
    String expectedOutput = "\n"
        + "Image Loaded\n"
        + "Image made from downscale\n"
        + "Image saved\n"
        + "Image made from downscale\n"
        + "Image saved";
    assertEquals(expectedOutput, actualOutput);
  }
}
