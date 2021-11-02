import controller.ImageController;
import controller.ImageControllerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.ImageProcessingModel;
import model.ImageProcessingSession;
import view.IImageProcessingView;
import view.ImageProcessingView;

public final class ImageProcessor {

  public static void main(String[] args) throws IOException {
    Readable rd = new InputStreamReader(System.in);

    IImageProcessingView view = new ImageProcessingView(new ImageProcessingModel(null), System.out);
    ImageProcessingSession session = new ImageProcessingSession();

    ImageController controller = new ImageControllerImpl(session, view, rd);
    controller.run();

  }

}