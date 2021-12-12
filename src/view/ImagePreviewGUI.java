package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePreviewGUI extends JPanel {
  BufferedImage imageToShow;


  ImagePreviewGUI(BufferedImage image) {
    this.imageToShow = image;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(imageToShow, 0, 0, this);

  }


  public void setImageToShow(BufferedImage imageToShow) {
    this.imageToShow = imageToShow;
  }
}
