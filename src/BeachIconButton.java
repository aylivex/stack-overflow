import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BeachIconButton {
    private static final String IMAGE_URL =
            "https://tabler-icons.io/static/tabler-icons/icons-png/beach.png";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BeachIconButton::new);
    }

    private BeachIconButton() {
        JPanel panel = new JPanel();
        ImageIcon icon = getIcon();
        JButton button = new JButton("Test button", icon);
        panel.add(button);

        JFrame frame = new JFrame("Beach Icon Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static ImageIcon getIcon() {
        try {
            return new ImageIcon(Toolkit.getDefaultToolkit()
                                        .getImage(new URL(IMAGE_URL))) {
                @Override
                public int getIconWidth() {
                    // return super.getIconWidth();
                    return 18;
                }

                @Override
                public int getIconHeight() {
                    // return super.getIconHeight();
                    return 18;
                }

                @Override
                public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
                    // super.paintIcon(c, g, x, y);
                    g.drawImage(getImage(), x, y, 18, 18, null);
                }
            };
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
