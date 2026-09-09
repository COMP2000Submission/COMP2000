import java.awt.Color;

public class Elements {
    boolean moveable;
    boolean canBurn;
    Color color;
    boolean reverseGravity;

    public Elements(boolean moveable, boolean canBurn, Color color, boolean reverseGravity) {
        this.moveable = moveable;
        this.canBurn = canBurn;
        this.color = color;
        this.reverseGravity = reverseGravity;
    }

    public Color getColor() {
        return color;
    }

    public boolean fallsDown() {
        return false;
    }

    public boolean floatsUp() {
        return false;
    }

    public void step(Sandbox sandbox, int row, int col) {
        // Default: stay still (Fire).
    }

    protected void fallDown(Sandbox sandbox, int row, int col) {
        if (sandbox.grid[row + 1][col] == null) {
            try {
                sandbox.moveElement(row, col, row + 1, col);
            } catch (Exception e) {
                System.out.println("Gravity error: " + e.getMessage());
            }
        } else {
            sandbox.side_gravity(row, col);
        }
    }

    protected void floatUp(Sandbox sandbox, int row, int col) {
        if (Math.random() < 0.6) {
            try {
                sandbox.reverse_side_gravity(row, col);
            } catch (Exception e) {
                System.out.println("Reverse side gravity error: " + e.getMessage());
            }
        } else if (sandbox.grid[row - 1][col] == null) {
            try {
                sandbox.moveElement(row, col, row - 1, col);
            } catch (Exception e) {
                System.out.println("Reverse gravity error: " + e.getMessage());
            }
        }
    }
}

class Sand extends Elements {
    public Sand() {
        super(true, false, Color.YELLOW, false);
    }

    @Override
    public boolean fallsDown() {
        return true;
    }

    @Override
    public void step(Sandbox sandbox, int row, int col) {
        fallDown(sandbox, row, col);
    }
}

class Water extends Elements {
    public Water() {
        super(true, false, Color.BLUE, false);
    }

    @Override
    public boolean fallsDown() {
        return true;
    }

    @Override
    public void step(Sandbox sandbox, int row, int col) {
        fallDown(sandbox, row, col);
    }
}

class Fire extends Elements {
    public Fire() {
        super(false, true, Color.RED, false);
    }
}

class Gas extends Elements {
    public Gas() {
        super(false, true, Color.WHITE, true);
    }

    @Override
    public boolean floatsUp() {
        return true;
    }

    @Override
    public void step(Sandbox sandbox, int row, int col) {
        floatUp(sandbox, row, col);
    }
}
