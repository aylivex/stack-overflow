import java.awt.Font;
import java.awt.Toolkit;

public class DefaultFontGUI {
    public static void main(String[] args) {
        Font guiFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
        int guiSize = guiFont.getSize();
        Font iconFont = (Font) Toolkit.getDefaultToolkit().getDesktopProperty("win.icon.font");
        System.out.println("gui default: " + guiFont + "\nicon default: " + iconFont);
    }
}
