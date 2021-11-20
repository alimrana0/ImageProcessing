import controller.GUIImageController;
import controller.IFeatures;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import model.ImageProcessingSession;
import view.IGUI;
import view.JFrameView;

public class GUIImageProcessor {

  public static void main(String[] args) throws IOException {
    ImageProcessingSession session = new ImageProcessingSession();
    JFrameView view = new JFrameView();
    IFeatures controller= new GUIImageController(session, view);
    view.addFeatures((ActionListener) controller);

  }
  }
