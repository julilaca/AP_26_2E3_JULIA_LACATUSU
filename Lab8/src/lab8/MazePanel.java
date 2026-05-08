package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

class MazePanel extends JPanel {
    private int rows = 10;
    private int cols = 10;
    private final int cellSize = 40;
    private Cell[][] cells;
    private Random random = new Random();

    public MazePanel() {
        setBackground(Color.WHITE);
        createCells();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                toggleWallAt(e.getX(), e.getY());
            }
        });
    }
    private void toggleWallAt(int x, int y) {
        int col = x / cellSize;
        int row = y / cellSize;
        if (row >= rows || col >= cols) return;

        int offsetX = x % cellSize;
        int offsetY = y % cellSize;
        int threshold = 10;

        if (offsetY < threshold) {
            cells[row][col].top = !cells[row][col].top;
            if (row > 0) cells[row - 1][col].bottom = cells[row][col].top;
        } else if (offsetY > cellSize - threshold) {
            cells[row][col].bottom = !cells[row][col].bottom;
            if (row < rows - 1) cells[row + 1][col].top = cells[row][col].bottom;
        } else if (offsetX < threshold) {
            cells[row][col].left = !cells[row][col].left;
            if (col > 0) cells[row][col - 1].right = cells[row][col].left;
        } else if (offsetX > cellSize - threshold) {
            cells[row][col].right = !cells[row][col].right;
            if (col < cols - 1) cells[row][col + 1].left = cells[row][col].right;
        }
        repaint();
    }
    public void exportToPNG(String filename) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "png", new File(filename));
            JOptionPane.showMessageDialog(this, "Saved as " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveMaze(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(cells);
            out.writeInt(rows);
            out.writeInt(cols);
            JOptionPane.showMessageDialog(this, "saved");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void loadMaze(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            cells = (Cell[][]) in.readObject();
            rows = in.readInt();
            cols = in.readInt();
            repaint();
            JOptionPane.showMessageDialog(this, "loaded");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public boolean isTraversable() {
        boolean[][] visited = new boolean[rows][cols];
        return dfs(0, 0, visited);
    }

    private boolean dfs(int r, int c, boolean[][] visited) {
        if (r == rows - 1 && c == cols - 1) return true;
        visited[r][c] = true;

        if (!cells[r][c].top && r > 0 && !visited[r - 1][c] && dfs(r - 1, c, visited)) return true;
        if (!cells[r][c].bottom && r < rows - 1 && !visited[r + 1][c] && dfs(r + 1, c, visited)) return true;
        if (!cells[r][c].left && c > 0 && !visited[r][c - 1] && dfs(r, c - 1, visited)) return true;
        if (!cells[r][c].right && c < cols - 1 && !visited[r][c + 1] && dfs(r, c + 1, visited)) return true;

        return false;
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
                g.setColor(new Color(205, 230, 255));
                g.fillRect(x, y, cellSize, cellSize);
                g.setColor(Color.BLACK);
                if (cells[i][j].top) g.drawLine(x, y, x + cellSize, y);
                if (cells[i][j].right) g.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                if (cells[i][j].bottom) g.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cells[i][j].left) g.drawLine(x, y, x, y + cellSize);
            }
        }
    }
}
