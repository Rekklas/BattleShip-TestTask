/**
 * Model cell object
 *
 * This class contains information about cell
 */
public class Cell {

    /**
     * value that defines is cell empty or is filled with a deck
     */
    private Content content;
    /**
     * the x value from array of cells[x][y]
     */
    private int positionX;
    /**
     * the y value from array of cells[x][y]
     */
    private int positionY;

    public Cell(Content content, int positionX, int positionY) {
        this.content = content;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
