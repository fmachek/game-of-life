import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class allows the player to set the refresh rate to whatever they want as long as the value is between 0 and 9999ms
public class RefreshButton extends JTextField implements ActionListener {

    private Game game;

    public RefreshButton(GUI gui){

        this.game = gui.getGame();

        this.setBounds(600,180,50,60);
        this.setText(Integer.toString(game.getMs()));
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.addActionListener(this);
        this.setEnabled(true);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) { // Change the refresh rate when the user types in an amount of time in ms (between 0-9999)
        String text = this.getText();
        Pattern pattern = Pattern.compile("^([0-9]{1,4})$");
        Matcher matcher = pattern.matcher(text);

        if(matcher.find()){
            String numberString = matcher.group(1);
            int numberInt = Integer.parseInt(numberString);
            game.setMs(numberInt);
            System.out.println("Refresh rate set to " + numberInt + "ms.");
        }
    }
}
