/**
 * This class is responsible for game creation,
 * it gets gameboard, prepare and render it
 * and starts game in the console
 */
public class Game {
    /**
     * This field contains gameboard object
     */
    private GameBoard gameBoard;

    /**
     * This method retrieves gameboard object
     */
    private void getBoard() {
        gameBoard = BoardFactory.makeBoard();
    }

    /**
     * This method invokes method for arranging ships in the gameboard
     */
    private void prepareBoard(){
        gameBoard.arrangeShips();
    }

    /**
     * This method creates view of the board and outputs it into console
     */
    private void renderBoard() {
        Cell[][] cells = gameBoard.getCells();
        StringBuilder battleField = new StringBuilder("\tA B C D E F G H I J \n");

        for (int i = 0; i < cells.length; i++) {
            battleField.append(i+1).append("\t");
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getContent().equals(Content.deck)) {
                    battleField.append("O ");
                } else {
                    battleField.append("~ ");
                }
            }
            battleField.append("\n");
        }

        System.out.println(battleField.toString());
    }

    /**
     * This method invokes list of methods for getting,
     * preparing and rendering board for the game
     */
    public void start(){
        getBoard();
        prepareBoard();
        renderBoard();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

}
