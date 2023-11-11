
// The SquareGrid class contains an array of all the squares
public class SquareGrid {

    private Square[][] grid;
    private int rowsAndColumns;

    public void createGrid(int rowsAndColumns){

        grid = new Square[rowsAndColumns][rowsAndColumns];
        this.rowsAndColumns = rowsAndColumns;

        for(int i = 0; i< rowsAndColumns; i++){
            for(int j = 0; j < rowsAndColumns; j++){
                grid[i][j] = new Square(i,j, rowsAndColumns);
            }
        }

    }

    public Square[][] getGrid(){
        return grid;
    }

    public SquareGrid(int rowsAndColumns){
        this.createGrid(rowsAndColumns);
    }

    public void resetGrid(){ // Resets the grid

        for(int i = 0; i< this.rowsAndColumns; i++){
            for(int j = 0; j < this.rowsAndColumns; j++){
                grid[i][j].setOldState(false);
                grid[i][j].setNewState(false);
            }
        }

        System.out.println("The square states have been reset.");

    }

}
