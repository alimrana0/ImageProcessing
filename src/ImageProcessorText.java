import controller.ImageController;
import controller.ImageControllerImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.ImageProcessingModel;
import model.ImageProcessingSession;
import model.imaging.Color;
import model.imaging.Image;
import model.imaging.Posn;
import model.imaging.pixel.IPixel;
import model.imaging.pixel.Pixel;
import view.IImageProcessingView;
import view.ImageProcessingView;

/**
 * Class representing the main processing unit that handles the MVC Image Processor.
 */
public final class ImageProcessorText {

  /**
   * The main function that runs the controller for an ImageProcessor text-based.
   *
   * @param args the client's given inputs
   * @throws IOException if the client gives inputs invalid to the ImageProcessor.
   */
  public static void main(String[] args) throws IOException {
    Readable rd = new InputStreamReader(System.in);

    ArrayList<IPixel> pixelArray = new ArrayList<>();
    pixelArray.add(new Pixel(new Posn(0, 0), new Color(0, 0, 0)));
    List<ArrayList<IPixel>> basicArray = new ArrayList<>();
    basicArray.add(pixelArray);
    Image emptyImage = new Image(basicArray);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(emptyImage),
            System.out);
    ImageProcessingSession session = new ImageProcessingSession();

    ImageController controller = new ImageControllerImpl(session, view, rd);
    controller.run();

  }

}