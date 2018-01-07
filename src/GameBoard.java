import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class contains information about gameboard
 */
public class GameBoard {
    /**
     * This field contains 2-dimensional array of cells which represents board
     */
    private Cell cells[][];
    /**
     * This field contains list of ships objects
     */
    private List<Ship> ships;

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    /**
     * This method arranges ships in the gameboard
     */
    public void arrangeShips() {

        List<Cell> possibleCells;
        Cell startPosition;
        boolean shipPlaced = false;

        for (Ship ship : ships) {
            generateRandomShipOrientation(ship);
            possibleCells = getPossibleCells(ship);
            while (!shipPlaced) {
                startPosition = getRandomCell(possibleCells);
                if (checkCanPlaceShip(ship, startPosition)) {
                    placeShip(ship, startPosition);
                    shipPlaced = true;
                } else {
                    possibleCells.remove(startPosition);
                }
            }
            possibleCells.clear();
            shipPlaced = false;
        }

    }

    /**
     * This method generate random orientation for the ship
     *
     * @param ship Ship object which needs to get random orientation
     */
    public void generateRandomShipOrientation(Ship ship) {
        ship.setOrientation(Orientation.values()
                [new Random().nextInt(Orientation.values().length)]);
    }

    /**
     * This method get the list of empty cells of the board
     *
     * @param ship ship object that should be placed into the board
     * @return list of empty cells
     */
    public List<Cell> getPossibleCells(Ship ship) {
        List<Cell> possibleCells = new ArrayList<>();

        switch (ship.getOrientation()) {
            case horizontal:
                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j <= cells.length - ship.getLength(); j++) {
                        if (cells[i][j].getContent().equals(Content.empty)) {
                            possibleCells.add(cells[i][j]);
                        }
                    }
                }
                break;
            case vertical:
                for (int i = 0; i <= cells.length - ship.getLength(); i++) {
                    for (int j = 0; j < cells.length; j++) {
                        if (cells[i][j].getContent().equals(Content.empty)) {
                            possibleCells.add(cells[i][j]);
                        }
                    }
                }
                break;
        }
        return possibleCells;
    }

    /**
     * This method get random cell from the list of empty cells
     *
     * @param possibleCells list of empty cells
     * @return one empty cell from the list
     */
    public Cell getRandomCell(List<Cell> possibleCells) {
        Random rand = new Random();
        System.out.println(possibleCells.size());
        return possibleCells.get(rand.nextInt(possibleCells.size()));
    }

    /**
     * This method checks if it is possible to place ship into board
     *
     * @param ship ship object that should be placed into the board
     * @param cell the start cell in which 1st deck of the ship should be placed
     * @return true if ship can be placed into board and false if not
     */
    public boolean checkCanPlaceShip(Ship ship, Cell cell) {

        boolean canPlaceShip = true;

        int x = cell.getPositionX();
        int y = cell.getPositionY();

        switch (ship.getOrientation()) {
            case horizontal:
                for (int i = 0; i < ship.getLength(); i++) {
                    if (checkCellIsOccupied(x,y+i)
                            || checkCellIsOccupied(x - 1, y + i - 1)
                            || checkCellIsOccupied(x - 1, y + i)
                            || checkCellIsOccupied(x - 1, y + i + 1)
                            || checkCellIsOccupied(x, y + i - 1)
                            || checkCellIsOccupied(x, y + i + 1)
                            || checkCellIsOccupied(x + 1, y + i - 1)
                            || checkCellIsOccupied(x + 1, y + i)
                            || checkCellIsOccupied(x + 1, y + i + 1)
                            ){
                        canPlaceShip = false;
                        break;
                    }
                }
                break;
            case vertical:
                for (int i = 0; i < ship.getLength(); i++) {
                    if (checkCellIsOccupied(x + i, y)
                            || checkCellIsOccupied(x + i - 1, y - 1)
                            || checkCellIsOccupied(x + i - 1, y)
                            || checkCellIsOccupied(x + i - 1, y + 1)
                            || checkCellIsOccupied(x + i, y - 1)
                            || checkCellIsOccupied(x + i, y + 1)
                            || checkCellIsOccupied(x + i + 1, y - 1)
                            || checkCellIsOccupied(x + i + 1, y)
                            || checkCellIsOccupied(x + i + 1, y + 1)
                            ) {
                        canPlaceShip = false;
                        break;
                    }
                    break;
                }
        }
        return canPlaceShip;
    }

    /**
     * This method checks if cell contains any ship's deck or it is empty
     *
     * @param x  cell x value from array of cells[x][y]
     * @param y cell y value from array of cells[x][y]
     * @return false if cell is empty and true if it is filled with any ship's deck
     */
    public boolean checkCellIsOccupied(int x, int y) {
        try {
            if (cells[x][y].getContent().equals(Content.deck)) {
                return true;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }

        return false;
    }

    /**
     * This method is responsible for placing ship into the board
     *
     * @param ship ship object that should be placed into the board
     * @param startPosition the start cell in which 1st deck of the ship should be placed
     */
    void placeShip(Ship ship, Cell startPosition) {
        switch (ship.getOrientation()) {
            case horizontal:
                for (int i = 0; i < ship.getLength(); i++) {
                    ship.addCellOccupied(
                            cells[startPosition.getPositionX()][startPosition.getPositionY() + i]);
                }
                break;
            case vertical:
                for (int i = 0; i < ship.getLength(); i++) {
                    ship.addCellOccupied(
                            cells[startPosition.getPositionX() + i][startPosition.getPositionY()]);
                }
                break;
        }
    }
}
