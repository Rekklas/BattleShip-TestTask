import java.util.ArrayList;
import java.util.List;

/**
 * Model ship object
 *
 * This class contains ship's characteristics
 */
public class Ship {
    /**
     * Value of how many decks the ship has
     */
    private int length;
    /**
     * Value of ship's orientation
     */
    private Orientation orientation;
    /**
     * List of cells where ships is situated
     */
    private List<Cell> cellsOccupied = new ArrayList<>();

    public Ship(int length) {
        this.length = length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Cell> getCellsOccupied() {
        return cellsOccupied;
    }

    /**
     * Method that add cell to the ship's list of occupied cells
     *
     * @param cell empty cell where deck of the ship can be placed
     */
    public void addCellOccupied(Cell cell) {
        cell.setContent(Content.deck);
        cellsOccupied.add(cell);
    }
}
