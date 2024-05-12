package UserInterface.Buttons;

import UserInterface.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

// The start button starts the game when the player clicks on it
public class StartButton extends CustomButton {

    public StartButton(GUI gui){
        super("Start", gui.getGame(), new Dimension(120,60), new Point(530,20));
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
