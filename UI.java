import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JFrame {

    Sandbox sandbox;

    public UI(int width, int height, Sandbox sandbox) {
        this.sandbox = sandbox;

        //Frame Creation
        setTitle("COMP2000 Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main Layout
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);

        add(mainPanel, BorderLayout.CENTER);

        //Left Area
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.GRAY);

        mainPanel.add(leftPanel, BorderLayout.CENTER);

        //Simulation Area - might need to change this later.                                                                     !!!!!!
        JPanel simWrapper = new JPanel(new FlowLayout(
                FlowLayout.LEFT,
                0,
                0));

        simWrapper.setBackground(Color.GRAY);

        JPanel simPanel = new SimPanel();

        simPanel.setPreferredSize(new Dimension(
                Sandbox.WIDTH,
                Sandbox.HEIGHT));

        simPanel.setBackground(Color.DARK_GRAY);

        simWrapper.add(simPanel);

        leftPanel.add(simWrapper, BorderLayout.NORTH);

        //Side Bar
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(150, 0));
        sidePanel.setBackground(Color.GRAY);

        mainPanel.add(sidePanel, BorderLayout.EAST);

        //Bottom Bar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);

        leftPanel.add(bottomPanel, BorderLayout.CENTER);

        setSize(
                Sandbox.WIDTH + 150,
                Sandbox.HEIGHT + 60);

        setLocationRelativeTo(null);
        setVisible(true);

        //Simulation Timer

        Timer timer = new Timer(16, e -> {
            sandbox.step();
            simPanel.repaint();
        });
        timer.start();
    }

    // draws or creates the sandbox and handles clicks within the simultaed area
    class SimPanel extends JPanel {
        SimPanel() {
            // Converting mous to pixels and allows us to spawn our material there
            MouseAdapter mouse = new MouseAdapter() {
                void place(MouseEvent e) {
                    int col = e.getX() / Sandbox.CELL_SIZE;
                    int row = e.getY() / Sandbox.CELL_SIZE;
                    // Collision detection to ensure that we cant click outside of the simulated enviroment/grid
                    if (row >= 0 && row < Sandbox.ROWS && col >= 0 && col < Sandbox.COLS) {
                        sandbox.grid[row][col] = new Sand();
                        repaint();
                    }
                }

                // mous events for click and clicks+drag
                @Override
                public void mousePressed(MouseEvent e) {
                    place(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    place(e);
                }
            };
            addMouseListener(mouse);
            addMouseMotionListener(mouse);
        }

        // called whenever the panel needs to redraw
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int s = Sandbox.CELL_SIZE;
            
            for (int row = 0; row < Sandbox.ROWS; row++) {
                for (int col = 0; col < Sandbox.COLS; col++) {
                    
                    Elements element = sandbox.grid[row][col];
                    
                    if (element != null) {
                        
                        g.setColor(element.getColor());
                        
                        g.fillRect(col * s, row * s, s, s);
                    }
                }
            }
        }
    }
}