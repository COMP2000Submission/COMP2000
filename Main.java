public class Main {

    public static void main(String[] args) {

        Sandbox sandbox = new Sandbox();

        UI frame = new UI(
                Sandbox.WIDTH,
                Sandbox.HEIGHT,
                sandbox);
    }
}