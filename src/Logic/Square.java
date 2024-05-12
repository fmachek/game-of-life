package Logic;

import java.util.HashSet;
import java.util.Objects;

// The Logic.Square class represents one square on the grid
public class Square {

    private int rowsAndColumns;
    private int xCoordinate;
    private int yCoordinate;
    private int livingNeighbors;
    private Boolean oldState = false;
    private Boolean newState = false;
    public int getXCoordinate(){
        return xCoordinate;
    }
    public int getYCoordinate(){
        return yCoordinate;
    }
    public Boolean isAlive(){
        return oldState;
    }

    public Square(int x, int y, int rowsAndColumns){
        this.rowsAndColumns = rowsAndColumns;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    public void setOldState(Boolean state){
        this.oldState = state;
    }
    public void setNewState(Boolean state){
        this.newState = state;
    }
    public Boolean getNewState(){
        return this.newState;
    }

    public void getNeighbors(SquareGrid grid, HashSet<Square> neighbors, Boolean checkingAlive){ // Gets all neighbors and checks their state if needed

        findNeighbor(this.xCoordinate - 1, this.yCoordinate + 1, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate, this.yCoordinate + 1, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate + 1, this.yCoordinate + 1, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate - 1, this.yCoordinate, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate + 1, this.yCoordinate, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate - 1, this.yCoordinate - 1, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate, this.yCoordinate - 1, grid, neighbors, checkingAlive);
        findNeighbor(this.xCoordinate + 1, this.yCoordinate - 1, grid, neighbors, checkingAlive);

    }

    public void findNeighbor(int x, int y, SquareGrid grid, HashSet<Square> neighbors, Boolean checkingLiving){ // Finds a neighbor and if its looking for living ones, it adds it to livingNeighbors
        Square[][] gridArray = grid.getGrid();
        if(x >= 0 && x < rowsAndColumns && y >= 0 && y < rowsAndColumns){
            if(checkingLiving && gridArray[x][y].isAlive()){
                this.livingNeighbors++;
            }
            if(!checkingLiving){ // If it's not checking the living neighbors, it means we're just finding the neighbors, not calculating the logic, so we add the neighbor to our hashset
                neighbors.add(gridArray[x][y]);
            }
        }
    }

    public void compute(SquareGrid grid, HashSet<Square> neighbors){ // Computes what happens to this square
        livingNeighbors = 0;
        this.getNeighbors(grid, neighbors, true); // gets all neighbors and counts the living ones

        if(livingNeighbors < 2){ // Logic
            this.newState = false;
        } else if((livingNeighbors == 2 || livingNeighbors == 3) && this.oldState == true){
            this.newState = true;
        } else if (livingNeighbors > 3){
            this.newState = false;
        } else if(livingNeighbors == 3 && this.oldState == false){
            this.newState = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return xCoordinate == square.xCoordinate && yCoordinate == square.yCoordinate && livingNeighbors == square.livingNeighbors && Objects.equals(oldState, square.oldState) && Objects.equals(newState, square.newState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate, livingNeighbors, oldState, newState);
    }

}
