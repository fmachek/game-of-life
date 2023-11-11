import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The ResetButton class allows the player to reset the grid
public class ResetButton extends JButton implements ActionListener {

    private GUI gui;
    private Game game;

    public ResetButton(GUI gui){
        this.gui = gui;

        this.setBounds(530,100,120,60);
        this.setEnabled(true);
        this.setVisible(true);
        this.setText("Reset");
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.addActionListener(this);

        this.game = gui.getGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setPlaying(false);
        game.getGrid().resetGrid();
        gui.getGrid().resetGUIGrid();
        gui.getStart().setText("Start");
    }
}
