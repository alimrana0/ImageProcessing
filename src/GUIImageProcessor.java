import controller.GUIImageController;
import controller.IFeatures;
import java.io.IOException;
import model.ImageProcessingSession;
import view.IGUI;
import view.JFrameView;

public class GUIImageProcessor {

  public static void main(String[] args) throws IOException {
    ImageProcessingSession session = new ImageProcessingSession();
    IGUI view = new JFrameView();
    IFeatures controller= new GUIImageController(session, view);

  }
  }
