package Client;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BioreacteurGUI extends JFrame {
    private JButton buttonOxygen;
    private JButton buttonTemperature;
    private JButton buttonPH;
    private JPanel plotPanel;
    private String[] xDates;
    private float[] ycoords;

    public BioreacteurGUI() {
        setTitle("Bioreacteur GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        buttonOxygen = new JButton("Oxygen");
        buttonTemperature = new JButton("Temperature");
        buttonPH = new JButton("pH");

        buttonOxygen.addActionListener(e -> plotFunction("Oxygen"));
        buttonTemperature.addActionListener(e -> plotFunction("Temperature"));
        buttonPH.addActionListener(e -> plotFunction("pH"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(buttonOxygen);
        buttonPanel.add(buttonTemperature);
        buttonPanel.add(buttonPH);

        // Create plot panel
        plotPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                plotGraph((Graphics2D) g);
            }
        };

        // Add components to frame
        add(plotPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);

        // Set x-axis dates (assuming they are always the same)
        xDates = new String[]{"2024-01-01", "2024-02-01", "2024-03-01", "2024-04-01", "2024-05-01"}; // Example dates
    }

    private void plotFunction(String selectedOption) {
        // Example: Assign y coordinates based on the selected option
        switch (selectedOption) {
            case "Oxygen":
                ycoords = new float[]{2, 4, 6, 8, 10}; // Example y coordinates
                break;
            case "Temperature":
                ycoords = new float[]{2, 150, 6, 100, 10}; // Example y coordinates
                break;
            case "pH":
                // Assign y coordinates for pH
                break;
            default:
                break;
        }

        // Redraw the plot panel
        plotPanel.repaint();
    }

    private void plotGraph(Graphics2D g) {
        // Clear previous drawings
        g.clearRect(0, 0, plotPanel.getWidth(), plotPanel.getHeight());

        // Draw x and y axes
        g.setColor(Color.BLACK);
        int xAxisY = plotPanel.getHeight() - 50;
        int yAxisX = 50;
        g.drawLine(yAxisX, xAxisY, plotPanel.getWidth() - 50, xAxisY); // x-axis
        g.drawLine(yAxisX, 50, yAxisX, xAxisY); // y-axis

        // Plot function
        if (xDates != null && ycoords != null && xDates.length == ycoords.length) {
            // Find maximum and minimum y values
            float maxY = ycoords[0];
            float minY = ycoords[0];
            for (int i = 1; i < ycoords.length; i++) {
                maxY = Math.max(maxY, ycoords[i]);
                minY = Math.min(minY, ycoords[i]);
            }

            // Draw x-axis graduations
            int numGraduations = xDates.length;
            for (int i = 0; i < numGraduations; i++) {
                int x = 50 + (int) (i * (plotPanel.getWidth() - 100) / (float) numGraduations);
                g.drawLine(x, xAxisY - 5, x, xAxisY + 5); // Graduations
                g.drawString(xDates[i], x, xAxisY + 20); // Labels
            }

            // Plot points and scale the y-axis
            g.setColor(Color.BLUE);
            for (int i = 0; i < xDates.length - 1; i++) {
                int x1 = yAxisX + (int) (i * (plotPanel.getWidth() - 100) / (float) numGraduations);
                int y1 = xAxisY - (int) ((ycoords[i] - minY) / (maxY - minY) * (plotPanel.getHeight() - 100));
                int x2 = yAxisX + (int) ((i + 1) * (plotPanel.getWidth() - 100) / (float) numGraduations);
                int y2 = xAxisY - (int) ((ycoords[i + 1] - minY) / (maxY - minY) * (plotPanel.getHeight() - 100));
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BioreacteurGUI plotter = new BioreacteurGUI();
            plotter.setVisible(true);
        });
    }
}
