import java.awt.*;
import java.awt.event.*;
class Simple{
    Simple(){
        Frame frame=new Frame("IV");
        frame.setBackground(new Color(150, 200, 100));
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
        Frame frame2=new Frame();
        frame2.setBackground(new Color(10, 200, 100));
        frame2.setSize(100,100);;
        frame2.setVisible(true);


    }
    public static void main(String[] args){
        new Simple();
    }

}