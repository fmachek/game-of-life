import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The start button starts the game when the player clicks on it
public class StartButton extends JButton implements ActionListener {

    private Game game;

    public StartButton(GUI gui){

        this.setBounds(530,20,120,60);
        this.setEnabled(true);
        this.setVisible(true);
        this.setText("Start");
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.addActionListener(this);

        this.game = gui.getGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(game.getPlaying()){
            game.setPlaying(false);
            this.setText("Resume");
            System.out.println("The game has been paused.");
        } else {
            game.setPlaying(true);
            this.setText("Pause");
            System.out.println("The game has been resumed.");
        }
    }
}
