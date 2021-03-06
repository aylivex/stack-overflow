import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class FrameIconList {

    private static final Color[] colors = new Color[]{
            Color.GREEN,
            Color.RED,
            Color.YELLOW,
            Color.WHITE,
            Color.CYAN,
            Color.MAGENTA,
            Color.PINK,
            Color.ORANGE
    };

    private static final int[] sizes = new int[64];

    public static BufferedImage getImage(int size, Color color) {
        BufferedImage i = new BufferedImage(
                size, size, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = i.createGraphics();

        g.setColor(color);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.BLACK);
        int off = (size > 17 ? 3 : 1);
        if (off > 1) {
            g.drawRect(0, 0, size - 1, size - 1);
        }
        g.drawString("" + size, off, size - off);

        g.dispose();

        return i;
    }

    public static void main(String[] args) {
        for (int ii = 0; ii < sizes.length; ii++) {
            sizes[ii] = 16 + (ii * 2);
        }

        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(FrameIconList::createUI);
    }

    private static void createUI() {
        // the GUI as seen by the user (without frame)
        JPanel gui = new JPanel(new BorderLayout());
        gui.setBorder(new EmptyBorder(2, 3, 2, 3));
        gui.setBackground(Color.WHITE);

        ArrayList<BufferedImage> images = new ArrayList<>(sizes.length);
        Vector<ImageIcon> icons = new Vector<>(sizes.length);
        for (int ii = 0; ii < sizes.length; ii++) {
            BufferedImage bi = getImage(
                    sizes[ii],
                    colors[ii % colors.length]);
            images.add(bi);
            ImageIcon imi = new ImageIcon(bi);
            icons.add(imi);
        }
        JList<ImageIcon> list = new JList<>(icons);
        list.setVisibleRowCount(6);
        gui.add(new JScrollPane(list));

        JFrame f = new JFrame("Icon size usage");
        f.setIconImages(images);
        f.add(gui);
        // Ensures JVM closes after frame(s) closed and
        // all non-daemon threads are finished
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // See http://stackoverflow.com/a/7143398/418556 for demo.
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // should be done last, to avoid flickering, moving,
        // resizing artifacts.
        f.setVisible(true);
    }
}
