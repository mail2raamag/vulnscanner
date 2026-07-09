import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;

public class FullScreenApp extends Frame {

    private Image backgroundImage;

    public FullScreenApp() {
        // 1. Remove window decorations (title bar, borders) to make it look clean
        setUndecorated(true);
        
        // 2. Prevent resizing
        setResizable(false);

        // 3. Load the background image
        try {
            // Replace with your actual image path
            backgroundImage = ImageIO.read(new File("your_image.jpg")); 
        } catch (Exception e) {
            System.out.println("Image could not be loaded. Defaulting to solid black shade.");
        }

        // 4. Get screen dimensions and set full screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        // 5. Add a key listener so you can easily exit full-screen mode (Press ESC)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the entire screen with a nice, deep black shade
        g2d.setColor(new Color(15, 15, 15));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the image with 60% transparency (40% opacity)
        if (backgroundImage != null) {
            // Save the original composite state
            Composite originalComposite = g2d.getComposite();

            // 0.4f opacity = 40% visible (60% transparent)
            float opacity = 0.4f; 
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Draw the image scaled to fill the entire window
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Restore original composite state
            g2d.setComposite(originalComposite);
        }

        // Optional: Add some placeholder text to confirm it's working
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
        g2d.drawString("Press 'ESC' to Exit Full Screen Mode", 50, 100);
    }

    public static void main(String[] args) {
        // Ensure standard AWT layout rules don't mess up the frame presentation
        EventQueue.invokeLater(() -> {
            FullScreenApp app = new FullScreenApp();
            app.setVisible(true);
        });
    }
}
