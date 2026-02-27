package lab1;

public class Homework {

    public void doHomework(String[] args) {

        int n = Integer.parseInt(args[0]);
        String shape = args[1];
        int[][] m = new int[n][n];

        if (n >= 4 && n < 1000) {

            if (shape.equals("rectangle")) {
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
                            m[i][j] = 0;
                        else
                            m[i][j] = 255;
                    }
                }
            }

            if (shape.equals("circle")) {
                int center = m.length / 2;
                int radius = m.length / 2;

                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        int dx = i - center;
                        int dy = j - center;
                        if (dx * dx + dy * dy <= radius * radius)
                            m[i][j] = 255;
                        else
                            m[i][j] = 0;
                    }
                }
            }

            String picture = buildString(m);
            System.out.println(picture);
        }
        else if (n >= 100) {

            long t1 = System.currentTimeMillis();

            if (shape.equals("rectangle")) {
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
                            m[i][j] = 0;
                        else
                            m[i][j] = 255;
                    }
                }
            }

            if (shape.equals("circle")) {
                int center = m.length / 2;
                int radius = m.length / 2;

                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        int dx = i - center;
                        int dy = j - center;
                        if (dx * dx + dy * dy <= radius * radius)
                            m[i][j] = 255;
                        else
                            m[i][j] = 0;
                    }
                }
            }

            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
        }
    }

    public String buildString(int[][] m) {
        String result = "";

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 255)
                    result += "\u2591";
                else
                    result += "\u2588";
            }
            result += "\n";
        }

        return result;
    }
}