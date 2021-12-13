package view;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImagePreviewGUI extends JLabel {
  BufferedImage imageToShow;
  JLabel imageLabel;


  ImagePreviewGUI(BufferedImage image) {
    this.imageToShow = image;
    this.imageLabel = new JLabel();
  }

  public void setImageToShow(BufferedImage imageToShow) {
    this.setIcon(new ImageIcon(imageToShow));
    this.imageToShow = imageToShow;

  }
}
