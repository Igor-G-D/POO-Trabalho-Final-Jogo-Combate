package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundJPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundJPanel() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/jogocombate.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(new GridLayout(5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // scaling the image to fit inside the panel
        Image scaledImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, 0, 0, null);
    }
}