package UserInterface.Buttons;

import Logic.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class CustomButton extends JButton implements ActionListener {
    protected Game game;

    public CustomButton(String text, Game game, Dimension size, Point location) {
        super();
        this.game = game;

        setBounds((int)location.getX(), (int)location.getY(), (int)size.getWidth(), (int)size.getHeight());
        loadVisuals(text);

        addActionListener(this);

        setEnabled(true);
        setVisible(true);
    }

    private void loadVisuals(String text) {
        setText(text);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }
}
