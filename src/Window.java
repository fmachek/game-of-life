import javax.swing.*;

// The Window class represents the main window of the application
public class Window extends JFrame {

    public Window(int width, int height){

        this.setSize(width, height);
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

    }

}
