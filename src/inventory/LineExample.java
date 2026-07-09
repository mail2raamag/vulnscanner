import java.awt.*;
import java.awt.event.*;

public class LineExample extends Frame {

    public LineExample() {
        // Set Frame properties
        setTitle("AWT Line Drawing Example");
        setSize(400, 300);
        setVisible(true);

        // Add window listener to handle closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Override the paint method to perform custom drawing
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Always call super class paint

        // Optional: Change the line color
        g.setColor(Color.BLUE);

        // Draw a line from (x1, y1) to (x2, y2)
        // This draws a diagonal line from coordinate (50, 50) to (350, 250)
        g.drawLine(50, 50, 350, 250);
    }

    public static void main(String[] args) {
        new LineExample();
    }
}
