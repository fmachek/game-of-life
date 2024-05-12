package UserInterface.Buttons;

import UserInterface.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RandomButton extends CustomButton {
    private GUI gui;

    public RandomButton(GUI gui){
        super("Generate", gui.getGame(), new Dimension(120,60), new Point(530,260));
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPlaying(false);
        game.getGrid().generateRandom();
        gui.getGrid().resetGuiGrid();
        gui.getStart().setText("Start");
    }
}
