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
}

class Sand extends Elements {
    public Sand() {
        super(true, false, Color.YELLOW, false);
    }
}

class Water extends Elements {
    public Water() {
        super(true, false, Color.BLUE, false);
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
}