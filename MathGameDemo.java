import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * The game entry is here, it will open the MainPage.
 */
public class MathGameDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage());
    }
}