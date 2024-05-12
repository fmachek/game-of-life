package UserInterface.Buttons;

import UserInterface.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

// The UserInterface.Buttons.ResetButton class allows the player to reset the grid
public class ResetButton extends CustomButton {

    private GUI gui;

    public ResetButton(GUI gui){
        super("Reset", gui.getGame(), new Dimension(120,60), new Point(530, 100));
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPlaying(false);
        game.getGrid().resetGrid();
        gui.getGrid().resetGUIGrid();
        gui.getStart().setText("Start");
    }
}
