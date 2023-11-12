import java.util.HashSet;

// The Game class handles the generations and creation of all the other elements
public class Game extends Thread {
    private int rowsAndColumns = 50; // Change this to change the grid size, 500 must be divisible by this number because the grid is 500x500 pixels
    private int squareSize;
    private int ms = 50; // The time between generations in milliseconds
    private GUI gui;
    private SquareGrid grid = new SquareGrid(rowsAndColumns);
    public HashSet<Square> pack = new HashSet<>();
    public HashSet<Square> livingSquares = new HashSet<>();
    private Boolean playing = false;

    // Getters and setters
    public SquareGrid getGrid() {
        return grid;
    }
    public HashSet<Square> getLivingSquares(){
        return livingSquares;
    }
    public void setMs(int ms){
        this.ms = ms;
    }
    public int getMs(){
        return this.ms;
    }
    public void setPlaying(Boolean state){
        this.playing = state;
    }
    public Boolean getPlaying(){
        return this.playing;
    }
    //

    public void run(){

        if(500 % rowsAndColumns != 0){ // Checks if the grid size is valid and if not, resets it to 50
            System.out.println("Cannot set the grid to " + rowsAndColumns + "x" + rowsAndColumns + ". Resetting to 50.");
            rowsAndColumns = 50;
        }
        squareSize = 500/rowsAndColumns;

        this.gui = new GUI(this, rowsAndColumns, squareSize);

        while(true){
                try{
                    if(playing) { // Computes the generations if the player is playing the game
                        pack.clear();

                        for(Square square : livingSquares){ // adds all the living squares to the "pack" HashSet
                            pack.add(square);
                            square.getNeighbors(grid, pack, false);
                        }

                        for(Square square : pack){ // Computes the logic for all the squares in pack
                            square.compute(grid, pack);
                        }

                        for(Square square : pack){ // Reloads the state of the square
                            square.setOldState(square.getNewState());
                        }

                        livingSquares.clear(); // Clears livingSquares so we can calculate which ones are alive again

                        for(Square square : pack){ // Adds the living squares into livingSquares or removes the ones that died and changes their color to the new correct one

                            GUI.Grid grid = gui.getGrid();

                            if(square.isAlive()){
                                livingSquares.add(square);
                                grid.paintSquareAlive(square.getXCoordinate() * squareSize, square.getYCoordinate() * squareSize);
                            } else {
                                livingSquares.remove(square);
                                grid.paintSquareDead(square.getXCoordinate() * squareSize, square.getYCoordinate() * squareSize);
                            }

                        }
                    }

                    Thread.sleep(ms);

                } catch (InterruptedException e) {

                }
            }
        }

}
