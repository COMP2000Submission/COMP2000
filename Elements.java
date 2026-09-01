public class Elements {
    boolean moveable;
    boolean canBurn;
    String color;
    boolean ReverseGravity;

    public Elements(boolean moveable, boolean canBurn, String color, boolean ReverseGravity) {
        this.moveable = moveable;
        this.canBurn = false;
        this.color = color;
        this.ReverseGravity = false;
    }

    class Sand extends Elements {
        public Sand() {
            super(true, false, "yellow", false);
        }
    }

    class Water extends Elements {
        public Water() {
            super(true, false, "blue", false);
        }
    }

    class Fire extends Elements {
        public Fire() {
            super(false, true, "red", false);
        }
    }
    class Gas extends Elements {
        public Gas() {
            super(false, false, "clear", false);
        }
    }
}


