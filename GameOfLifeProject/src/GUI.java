import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// The GUI class handles the stuff that happens on the screen
public class GUI {

    Graphics graphics;

    // GUI values
    private final int width = 700;
    private final int height = 540;
    private int rowsAndColumns;
    private int spacing = 1;
    private int squareSize;
    //


    // Other values
    public int mouseX;
    public int mouseY;
    public int squareX = 0;
    public int squareY = 0;
    private int hoverX;
    private int hoverY;
    private Game game;
    private Window window;
    private Grid grid;
    private StartButton startButton;
    private ResetButton resetButton;
    private RefreshButton refreshButton;
    private JLabel refreshLabel;
    //

    public StartButton getStart(){
        return this.startButton;
    }
    public Game getGame(){
        return game;
    }
    public Grid getGrid(){
        return this.grid;
    }

    public GUI(Game game, int rowsAndColumns, int squareSize){

        this.rowsAndColumns = rowsAndColumns;
        this.squareSize = squareSize;

        if(rowsAndColumns > 50){ // if the grid size is big there will be no spacing between the squares
            this.spacing = 0;
        }

        this.window = new Window(width, height);
        this.grid = new Grid();
        this.game = game;

        this.window.add(grid);

        this.grid.addMouseMotionListener(new MouseMotion());
        this.grid.addMouseListener(new MouseClick());

        this.startButton = new StartButton(this);
        this.window.add(startButton);

        this.resetButton = new ResetButton(this);
        this.window.add(resetButton);

        this.refreshButton = new RefreshButton(this);
        this.window.add(refreshButton);

        this.refreshLabel = new JLabel();
        refreshLabel.setBounds(530,180,60,60);
        refreshLabel.setText("<html>Refresh<br>Rate (ms):</html>");
        refreshLabel.setBackground(Color.DARK_GRAY);
        refreshLabel.setForeground(Color.WHITE);
        refreshLabel.setOpaque(true);
        refreshLabel.setEnabled(true);
        refreshLabel.setVisible(true);
        window.add(refreshLabel);

        this.window.setVisible(true);

    }

    public class MouseMotion implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }
        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();

            squareX = Math.round(mouseX/squareSize) * squareSize; // Gets the square's coordinates based on its squareSize (it rounds the number down to a number divisible by squareSize)
            squareY = Math.round(mouseY/squareSize) * squareSize;

            grid.hover(squareX/squareSize, squareY/squareSize);
        }
    }

    public class MouseClick implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) { // Paints a square when clicked
            if(!game.getPlaying()){
                grid.paintSquare(squareX, squareY);
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class Grid extends JPanel {

        public Grid(){

            this.setBounds(0,0,500,500);
            this.setVisible(true);

        }

        public void hover(int x, int y){ // Indicates what would happen if a player clicked on a square they're hovering over

            Graphics g = this.getGraphics();
            Square[][] squareGrid = game.getGrid().getGrid();
            Square square = squareGrid[x][y];
            Square oldSquare = squareGrid[hoverX][hoverY];

            if (!(hoverX == x && hoverY == y)) { // Checks if the coords of the square hovered over are still the same, if not, it means we're hovering over a new square and it changes its color
                if (oldSquare.isAlive()) {
                    g.setColor(Color.WHITE);
                    g.fillRect(hoverX * squareSize + spacing, hoverY * squareSize + spacing, squareSize - spacing*2, squareSize - spacing*2);
                } else if (!oldSquare.isAlive()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(hoverX * squareSize + spacing, hoverY * squareSize + spacing, squareSize - spacing*2, squareSize - spacing*2);
                }
            }

            if(!game.getPlaying()) { // Displays a color that indicates if the square will turn black or white when clicked if the game isn't running
                if(square.isAlive()){
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x*squareSize+spacing,y*squareSize+spacing,squareSize - spacing*2,squareSize - spacing*2);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x*squareSize+spacing,y*squareSize+spacing,squareSize - spacing*2,squareSize - spacing*2);
                }
            }
                hoverX = x; // sets the previous square coords so we can check if we're looking at the same square later
                hoverY = y;
        }

        public void paintSquare(int x, int y){ // Paints a square based on its state

            Graphics g = this.getGraphics();

            Square[][] oldGrid = game.getGrid().getGrid();

            if(oldGrid[x/squareSize][y/squareSize].isAlive()){
                g.setColor(Color.GRAY);
                oldGrid[x/squareSize][y/squareSize].setOldState(false);
                game.getLivingSquares().remove(oldGrid[x/squareSize][y/squareSize]);
                g.fillRect(x,y,squareSize,squareSize);
                g.setColor(Color.BLACK);
                g.fillRect(x+spacing,y+spacing,squareSize-spacing*2,squareSize-spacing*2);
            } else {
                g.setColor(Color.GRAY);
                oldGrid[x/squareSize][y/squareSize].setOldState(true);
                game.getLivingSquares().add(oldGrid[x/squareSize][y/squareSize]);
                g.fillRect(x,y,squareSize,squareSize);
                g.setColor(Color.WHITE);
                g.fillRect(x+spacing,y+spacing,squareSize-spacing*2,squareSize-spacing*2);
            }

        }

        public void paintSquareAlive(int x, int y){

            Graphics g = this.getGraphics();

            g.setColor(Color.GRAY);
            g.fillRect(x,y,squareSize,squareSize);
            g.setColor(Color.WHITE);
            g.fillRect(x+spacing,y+spacing,squareSize-spacing*2,squareSize-spacing*2);

        }

        public void paintSquareDead(int x, int y){

            Graphics g = this.getGraphics();
            g.setColor(Color.GRAY);

            g.fillRect(x,y,squareSize,squareSize);
            g.setColor(Color.BLACK);
            g.fillRect(x+spacing,y+spacing,squareSize-spacing*2,squareSize-spacing*2);

        }

        public void resetGUIGrid(){ // This resets all squares

            for(int i = 0; i < rowsAndColumns; i++){
                for(int j = 0; j < rowsAndColumns; j++){
                    this.paintSquareDead(i*squareSize,j*squareSize);
                }
            }

            System.out.println("The grid has been reset.");
        }
        public void paintComponent(Graphics g){ // This paints the window

            graphics = g;
            g.setColor(Color.GRAY);
            g.fillRect(0,0,500,500);

            for(int i = 0; i < rowsAndColumns; i++){
                for(int j = 0; j < rowsAndColumns; j++){
                    g.setColor(Color.GRAY);
                    g.fillRect(i*squareSize,j*squareSize,squareSize,squareSize);
                    g.setColor(Color.BLACK);
                    g.fillRect(i*squareSize+spacing,j*squareSize+spacing,squareSize-spacing*2,squareSize-spacing*2);
                }
            }
        }
    }


}


