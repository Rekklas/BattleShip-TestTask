import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for board creating
 */
public class BoardFactory {
    /**
     * This method creates gameboard and configurate it according to the settings
     *
     * @return created gameboard
     */
    public static GameBoard makeBoard() {
        GameBoard gameBoard = new GameBoard();

        gameBoard.setShips(getShipsFromSettings());
        gameBoard.setCells(getCellsFromSettings());

        return gameBoard;
    }

    /**
     * This method creates 2-dimensional array of cells that represents board
     * according to settings values of board's height and width
     *
     * @return 2-dimensional array of cells
     */
    private static Cell[][] getCellsFromSettings() {
        Cell[][] cells = new Cell[Settings.boardHeight][Settings.boardWidth];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(Content.empty, i, j);
            }
        }
        return cells;
    }

    /**
     * This method creates ships objects according to settings
     *
     * @return list of ships
     */
    private static List<Ship> getShipsFromSettings() {
        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < Settings.fourDeckersCount; i++) {
            ships.add(new Ship(4));
        }
        for (int i = 0; i < Settings.threeDeckersCount; i++) {
            ships.add(new Ship(3));
        }
        for (int i = 0; i < Settings.twoDeckersCount; i++) {
            ships.add(new Ship(2));
        }
        for (int i = 0; i < Settings.oneDeckersCount; i++) {
            ships.add(new Ship(1));
        }
        return ships;
    }
}
