import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(e -> mazePanel.randomRemoveWalls());
        resetButton.addActionListener(e -> mazePanel.resetMaze());
        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.add(createButton);
        controlPanel.add(resetButton);
        controlPanel.add(exitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeApp::new);
    }
}

class Cell {
    int row, col;
    boolean top = true;
    boolean right = true;
    boolean bottom = true;
    boolean left = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class MazePanel extends JPanel {
    private int rows = 10;
    private int cols = 10;
    private final int cellSize = 40;
    private Cell[][] cells;
    private Random random = new Random();

    public MazePanel() {
        setBackground(Color.WHITE);
        createCells();
    }

    public void setMazeSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        createCells();
        repaint();
    }

    public void resetMaze() {
        createCells();
        repaint();
    }

    private void createCells() {
        cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void randomRemoveWalls() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < cols - 1 && random.nextBoolean()) {
                    cells[i][j].right = false;
                    cells[i][j + 1].left = false;
                }
                if (i < rows - 1 && random.nextBoolean()) {
                    cells[i][j].bottom = false;
                    cells[i + 1][j].top = false;
                }
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cols * cellSize + 1, rows * cellSize + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                g.setColor(new Color(200, 230, 255));
                g.fillRect(x, y, cellSize, cellSize);

                g.setColor(Color.BLACK);

                if (cells[i][j].top) {
                    g.drawLine(x, y, x + cellSize, y);
                }
                if (cells[i][j].right) {
                    g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                }
                if (cells[i][j].bottom) {
                    g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                }
                if (cells[i][j].left) {
                    g.drawLine(x, y, x, y + cellSize);
                }
            }
        }
    }
}

