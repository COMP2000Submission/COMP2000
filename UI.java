import java.awt.*;
import javax.swing.*;

public class UI extends JFrame {

    Sandbox sandbox;

    public UI(int width, int height, Sandbox sandbox) {
        this.sandbox = sandbox;

        // Frame Creation
        setTitle("COMP2000 Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Layout
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);

        add(mainPanel, BorderLayout.CENTER);

        // Left Area
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.GRAY);

        mainPanel.add(leftPanel, BorderLayout.CENTER);

        // Simulation Area - might need to change this later. !!!!!!
        JPanel simWrapper = new JPanel(new FlowLayout(
                FlowLayout.LEFT,
                0,
                0));

        simWrapper.setBackground(Color.GRAY);

        JPanel simPanel = new SimulationPanel(sandbox);

        simPanel.setPreferredSize(new Dimension(
                Sandbox.WIDTH,
                Sandbox.HEIGHT));

        simPanel.setBackground(Color.DARK_GRAY);

        simWrapper.add(simPanel);

        leftPanel.add(simWrapper, BorderLayout.NORTH);

        // Side Bar
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(150, 0));
        sidePanel.setBackground(Color.GRAY);

        mainPanel.add(sidePanel, BorderLayout.EAST);

        // Bottom Bar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);

        leftPanel.add(bottomPanel, BorderLayout.CENTER);

        setSize(
                Sandbox.WIDTH + 150,
                Sandbox.HEIGHT + 150);

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}