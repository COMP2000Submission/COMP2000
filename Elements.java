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

    private static final Color WHITE_HOT = new Color(255, 245, 200);
    private static final Color ORANGE = new Color(255, 140, 20);
    private static final Color EMBER = new Color(120, 20, 0);

    private final long bornAt = System.currentTimeMillis();
    private final long lifespanMs = 2800 + (long) (Math.random() * 400);

    public Fire() {
        super(false, true, Color.ORANGE, true);
    }

    @Override
    public Color getColor() {
        long age = System.currentTimeMillis() - bornAt;

        if (age >= lifespanMs) {
            return Color.DARK_GRAY;
        }

        float t = 1f - (float) age / lifespanMs;

        Color base = (t > 0.55f)
                ? lerp(ORANGE, WHITE_HOT, (t - 0.55f) / 0.45f)
                : lerp(EMBER, ORANGE, t / 0.55f);

        int flicker = (int) (Math.random() * 24) - 12;

        return new Color(
                clamp(base.getRed() + flicker),
                clamp(base.getGreen() + flicker),
                clamp(base.getBlue() + flicker / 2));
    }

    private Color lerp(Color a, Color b, float f) {
        f = Math.max(0, Math.min(1, f));
        return new Color(
                (int) (a.getRed() + f * (b.getRed() - a.getRed())),
                (int) (a.getGreen() + f * (b.getGreen() - a.getGreen())),
                (int) (a.getBlue() + f * (b.getBlue() - a.getBlue())));
    }

    private int clamp(int v) {
        return Math.max(0, Math.min(255, v));
    }

    @Override
    public Elements copy () {
        return new Fire();
    }
}


class Gas extends Elements {
    public Gas() {
        super(false, true, Color.WHITE, true);
    }
}