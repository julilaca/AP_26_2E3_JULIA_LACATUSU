package lab8;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class MazeApp extends JFrame {
    private MazePanel mazePanel;
    private JTextField rowsField;
    private JTextField colsField;

    public MazeApp() {
        super("Maze App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mazePanel = new MazePanel();

        createConfigPanel();
        add(mazePanel, BorderLayout.CENTER);
        createControlPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createConfigPanel() {
        JPanel configPanel = new JPanel();

        configPanel.add(new JLabel("Rows:"));
        rowsField = new JTextField("10", 5);
        configPanel.add(rowsField);

        configPanel.add(new JLabel("Cols:"));
        colsField = new JTextField("10", 5);
        configPanel.add(colsField);

        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            mazePanel.setMazeSize(rows, cols);
            pack();
        });
        configPanel.add(drawButton);

        add(configPanel, BorderLayout.NORTH);
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel();

        JButton createButton = new JButton("Create");
        JButton resetButton = new JButton("Reset");
        JButton validateButton = new JButton("Validate");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exportButton = new JButton("Export");
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(e -> mazePanel.randomRemoveWalls());
        resetButton.addActionListener(e -> mazePanel.resetMaze());

        validateButton.addActionListener(e -> {
            if (mazePanel.isTraversable()) {
                JOptionPane.showMessageDialog(this, "traversable");
            } else {
                JOptionPane.showMessageDialog(this, "No path found");
            }
        });


        saveButton.addActionListener(e -> mazePanel.saveMaze("maze.dat"));
        loadButton.addActionListener(e -> mazePanel.loadMaze("maze.dat"));


        exportButton.addActionListener(e -> mazePanel.exportToPNG("maze.png"));

        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.add(createButton);
        controlPanel.add(resetButton);
        controlPanel.add(validateButton);
        controlPanel.add(saveButton);
        controlPanel.add(loadButton);
        controlPanel.add(exportButton);
        controlPanel.add(exitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeApp::new);
    }
}


