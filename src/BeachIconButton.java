import java.awt.Image;
import java.awt.image.BaseMultiResolutionImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BeachIconButton {
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
        return new ImageIcon(
               new BaseMultiResolutionImage(
               Arrays.stream(new String[] { BEACH_100, BEACH_150, BEACH_200})
                     .map(BeachIconButton::loadImage)
                     .toArray(Image[]::new)));
    }

    private static Image loadImage(String base64) {
        try {
            return ImageIO.read(new ByteArrayInputStream(
                                Base64.getMimeDecoder().decode(base64)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final String BEACH_100 = """
            iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAA7DAAAO
            wwHHb6hkAAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAAXxJ
            REFUSInl1E9LVVEUBfAfVJBIIoWGIDiRGtagN7Yg+gTZXPwICU4FyUGDahBRCTrK
            qUVNIxrkJHjWJMKZikIjSyHoYd0GZ186vOef7n2zWnB4e591WXudt/c5/G8Ywiw+
            4gd28QGLuNCt+C18RXHI+onJuuLjIVBgGWOYyPb2syI3q4qfzZw/ir1raMXeNBay
            k+zgfNUC25nTBWxGPhfffI78XfzOVD1FP+5lrgusoweXIv+C6xE3qxYocRFrWZHv
            eBPxA5yJeK9ugRJPdU7QGPoi3+1GfFBqZIFn+BVxC88jft9NgdL9q8gH8MSfUa3V
            5BKXQ6gl9aOdey1N2GBV4VO4ik/h8CWG67rMcQ53Hf5ErOBGXfEGtjKxVSxhHi/w
            LeMe4mQV8ROZ67fSZWrHadyW7kKBqaonuC89Bcc5uyKNZ+M4wTvS9d/AY/RmXK80
            iht/wa+HVgfKB61cTYzGaups8FH89kEFhjBSUzDnR0LrSFT5Sw7i/yH8BmQ0mnmX
            f2wqAAAAAElFTkSuQmCC""";

    private static final String BEACH_150 = """
            iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAYAAADhAJiYAAAACXBIWXMAABYlAAAW
            JQFJUiTwAAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAAoVJ
            REFUWIXt1kuoTVEYB/DfdZWiq7gykZBHHokyw0QGREpKLgMDGSojQ6+JARFi4JFn
            ySPyyoCJR8zcIUVJiPLopiMhLoO1d3ftc+7Z95xrnYHyr1V7feu/1/ff3/etby/+
            4x9DW8K9ZmE9FmTP7fiBp3iE03iS0F9dTMQN/G5gvMLMVopZhi8NisnHD6xohZiV
            2ea5o584h+WYgAclor5jaUoxE9ATOXiOednaUMUU9mKPkK5YVCXbJwkuKkZmYbS2
            t8rxpsz+QW2krqYSdF5tCnZjCX5F9h0Zf25k66nizEghaBSOV22cRyt/vhnxd0b2
            E7gQzXelEJRjnv6L96dQ9IR+9CxaWymcsnx+L6UgQnPtUizyfDwRaiiff8JwjIts
            71MLytGBy0I9xaLitG7LuCMi2+d4kyEJBVWwWkhPfz568UaI6Mho/XtCDTXYqO/L
            v+GF2jQ+wNZofrdVYjrwNnK0XYjOYSEt9bp20lMWY0/k5LVQJzlGCn2qur56Mb0V
            YiYLKcodddXhTcX1iJesU1cj/nc9NPA9axE2Cw02GUYITe6UYgoOCv+29pTOyjAH
            VxRT1N/4iP0Y2yohHTij9v810Khgiwavy43eqSfhGmZHtt/oxm2h4fUI0Zim75IW
            4wI24GuDPutiCF4q1sklAx/XJXisGK2zfysGhum7N3/F2ibebce+SNCdFIJgMQ4J
            F63BYBUOYEqzL87HLaEeKrivPCLrMk5lEPyezNf8MkHv9H9STioegDbF/vM3/Hdl
            grpLnBxFJ8bgWAmvWX53maBOId/jMRpHGnB8JOM2yx+f+eosE1SNNiH89TY/oTY1
            zfAHjS7hMv45G3exJiH/P/5d/AHE21JDZYKOHAAAAABJRU5ErkJggg==""";

    private static final String BEACH_200 = """
            iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAACXBIWXMAAB2HAAAd
            hwGP5fFlAAAAGXRFWHRTb2Z0d2FyZQB3d3cuaW5rc2NhcGUub3Jnm+48GgAAA5FJ
            REFUaIHt2ctrXFUcB/BPYoWGVGOwBZtYQQoqhUp9IFEoulC04spGExQEpYoiVBBc
            qVAVwYX/gEWqooKboItqN4oV0frWPgR3XRSl9mHT2GjaJh0X5w6ZOXPuzO08roL5
            wlnM+T3O93cev3vOb1jCEv7f6CtpnBswjg1Yh2HM4wx+wh58jC9K4lMIfXgIB1Ap
            2H7FC7jwX+Bbh2uwW3HiqUDWlE26io040YJgkXYKd5TM3Z34K0HmNKYwgavxgLD/
            WwUxi1vKIr8GfyRIfIAravTuxtmE3g+Jvkrm86pek+/XuOfP4elIbz1mIr15bBUm
            IG8lPtHjjLkpMeg+jNboDOKXSOcsJjP5bU0CqODBsgOoHsRnsRyvJ+RP1vh4PpId
            in7v7WUA/diRE0QFhxN92yMfeyL5MxoP+nW9DAJuxY85QdS2AxiosVsrnJmqfAGX
            4/3I7uVeBwAX4DEcaRLAFFbV2LwSyXdn/ZNR/86es6/BCmyTTpnVWX4Tl2pMv49k
            PtZF/QfLIl+L9fhN/mrEK3XE4vYajGQzZRKPcTuOyw+k2rbV2FwSyY7GTvt7yTjC
            UQwV0FspEKf+nMBcVxmdJz7VeEVYkF6F43gKD0f9H5bOOsN9Gkluws04lpBV21z0
            +7myiRO+wgcjInE6HE/opNpYOZTrEV8RzghX6hjLhWvHKWny35RBNsZogtCrLWxG
            8JrG8zHRO5r5eCci8bvFDNMKY/hMeNRsF77wpWJM/f2mgke7PciyLvtbKby4NuJe
            9Y+QGdyEv7FLSJX/CfThfqGmM691Jqlkep8L6bWs2lQSd+F7xUjnte+EYkCpGMQb
            HRKP247M73mhneVbhY9wY0J2TpjRnUKV4bBQIxrGalyPe4RSY+oe9q1who61wasQ
            +vC1xtlbwFu4sqCftXhb+i70lR6ei+HEgPtxbZv+NuDnhM/hjpnmoE+YoepAu7Sx
            byOsELZkWyvQzlINCTWaGbwnpMROsUx4/16Md3GyE2cDQqXsS0wLRPfhJfWFqjyM
            Zrr78WcX7KczLlvVVzJyMSU/1U1brKSlMJnp9Mp+qkgAs00cVL+imxN244p9iTux
            ny0SQLMVqLY5PCHs2SGhRHi6gF2n9oVWYABbhOLUZbgoGyx+3rUi+Hhm26n96ozL
            FgXPQB4262yLdGrfFUxo/vfRCc1fTp3adwUjeFEod5/M2l7h38WREuyXsIQl1OAf
            9zFZ1uiy3BkAAAAASUVORK5CYII=""";
}
