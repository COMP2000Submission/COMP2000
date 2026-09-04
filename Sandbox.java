public class Sandbox {

    static int WIDTH = 1500; // Width and Height are changeable, not sure if this is too big.
    static int HEIGHT = 800;
    static int CELL_SIZE = 4;

    static int COLS = WIDTH / CELL_SIZE;
    static int ROWS = HEIGHT / CELL_SIZE;

    int[][] grid;

    public Sandbox() {
        grid = new int[ROWS][COLS];
        grid[20][40] = 1;
    }

    public void step() {
        for (int row = ROWS - 2; row >= 0; row--) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 1 && grid[row + 1][col] == 0) {
                    grid[row][col] = 0;
                    grid[row + 1][col] = 1;
                }
            }
        }
    }

public static void main(String[] args) {
        Sandbox sandbox = new Sandbox();
        UI frame = new UI(WIDTH, HEIGHT, sandbox);
    }
}

