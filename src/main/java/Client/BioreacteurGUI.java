package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BioreacteurGUI extends JFrame {
    private JButton buttonOxygen;
    private JButton buttonTemperature;
    private JButton buttonPH;
    private JPanel plotPanel;
    private ArrayList<String> xDates;
    private ArrayList<Float> ycoords;

    public BioreacteurGUI() {
        setTitle("Bioreacteur GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        buttonOxygen = new JButton("Oxygen");
        buttonTemperature = new JButton("Temperature");
        buttonPH = new JButton("pH");
        buttonOxygen.setBackground(new Color(59, 89, 182));
        buttonOxygen.setForeground(Color.WHITE);
        buttonOxygen.setFocusPainted(false);
        buttonOxygen.setFont(new Font("Tahoma", Font.BOLD, 12));//http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP

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
        xDates = new ArrayList<>();
        xDates.add("2024-01-01");
        xDates.add("2024-02-01");
        xDates.add("2024-03-01");
        xDates.add("2024-04-01");
        xDates.add("2024-05-01"); // Example dates
    }

    private void plotFunction(String selectedOption) {
        // Example: Assign y coordinates based on the selected option
        switch (selectedOption) {
            case "Oxygen":
                ycoords = new ArrayList<>();
                ycoords.add(2f);
                ycoords.add(4f);
                ycoords.add(6f);
                ycoords.add(8f);
                ycoords.add(10f); // Example y coordinates
                break;
            case "Temperature":
                ycoords = new ArrayList<>();
                ycoords.add(2f);
                ycoords.add(150f);
                ycoords.add(6f);
                ycoords.add(100f);
                ycoords.add(10f); // Example y coordinates
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
        if (xDates != null && ycoords != null && xDates.size() == ycoords.size()) {
            // Find maximum and minimum y values
            float maxY = ycoords.get(0);
            float minY = ycoords.get(0);
            for (int i = 1; i < ycoords.size(); i++) {
                maxY = Math.max(maxY, ycoords.get(i));
                minY = Math.min(minY, ycoords.get(i));
            }

            // Draw y-axis graduations
            int numGraduations = 10;
            float yRange = maxY - minY;
            float yInterval = yRange / numGraduations;
            for (int i = 0; i <= numGraduations; i++) {
                int y = plotPanel.getHeight() - 50 - (int) (i * (plotPanel.getHeight() - 100) / (float) numGraduations);
                g.drawLine(yAxisX - 5, y, yAxisX + 5, y); // Graduations
                g.drawString(String.format("%.1f", minY + i * yInterval), yAxisX - 40, y + 5); // Labels
            }

            // Draw x-axis graduations
            for (int i = 0; i < xDates.size(); i++) {
                int x = yAxisX + (int) (i * (plotPanel.getWidth() - 100) / (float) (xDates.size() - 1));
                g.drawLine(x, xAxisY - 5, x, xAxisY + 5); // Graduations
                g.drawString(xDates.get(i), x - 30, xAxisY + 20); // Labels
            }

            // Plot points and scale the y-axis
            g.setStroke(new BasicStroke(2)); // Change the width as needed
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setColor(Color.BLUE);
            for (int i = 0; i < xDates.size() - 1; i++) {
                int x1 = yAxisX + (int) (i * (plotPanel.getWidth() - 100) / (float) (xDates.size() - 1));
                int y1 = xAxisY - (int) ((ycoords.get(i) - minY) / (maxY - minY) * (plotPanel.getHeight() - 100));
                int x2 = yAxisX + (int) ((i + 1) * (plotPanel.getWidth() - 100) / (float) (xDates.size() - 1));
                int y2 = xAxisY - (int) ((ycoords.get(i + 1) - minY) / (maxY - minY) * (plotPanel.getHeight() - 100));
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BioreacteurGUI bioreacteurGUI = new BioreacteurGUI();
            bioreacteurGUI.setVisible(true);
        });
    }
}
