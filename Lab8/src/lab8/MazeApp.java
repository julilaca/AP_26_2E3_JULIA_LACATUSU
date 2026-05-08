package lab8;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());

                mazePanel.setMazeSize(rows, cols);

                pack();
            }
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

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.randomRemoveWalls();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.resetMaze();
            }
        });

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mazePanel.isTraversable()) {
                    JOptionPane.showMessageDialog(null, "traversable");
                } else {
                    JOptionPane.showMessageDialog(null, "no path found");
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.saveMaze("maze.txt");
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.loadMaze("maze.txt");
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazePanel.exportToPNG("maze.png");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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
        new MazeApp();
    }
}


