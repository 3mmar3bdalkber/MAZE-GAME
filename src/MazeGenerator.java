import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private final int rows;
    private final int cols;

    public boolean[][] wallsTop;
    public boolean[][] wallsRight;
    public boolean[][] wallsBottom;
    public boolean[][] wallsLeft;

    private final boolean[][] visited;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        wallsTop = new boolean[rows][cols];
        wallsRight = new boolean[rows][cols];
        wallsBottom = new boolean[rows][cols];
        wallsLeft = new boolean[rows][cols];
        visited = new boolean[rows][cols];
    }

    public void generateMaze() {
        // Initialize walls
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                visited[r][c] = false;
                wallsTop[r][c] = true;
                wallsRight[r][c] = true;
                wallsBottom[r][c] = true;
                wallsLeft[r][c] = true;
            }

        dfs(0, 0);
        wallsTop[0][0] = false;
    }

    private void dfs(int r, int c) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r, c});
        visited[r][c] = true;
        Random rand = new Random();

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int row = cell[0], col = cell[1];

            int[][] directions = {
                    {row-1, col, 0}, {row+1, col, 1}, {row, col-1, 2}, {row, col+1, 3}
            };

            for (int i = 0; i < directions.length; i++) {
                int rIndex = rand.nextInt(directions.length);
                int[] temp = directions[i];
                directions[i] = directions[rIndex];
                directions[rIndex] = temp;
            }

            for (int[] d : directions) {
                int nr = d[0], nc = d[1], dir = d[2];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                if (visited[nr][nc]) continue;

                switch (dir) {
                    case 0 -> { wallsTop[row][col]=false; wallsBottom[nr][nc]=false; }
                    case 1 -> { wallsBottom[row][col]=false; wallsTop[nr][nc]=false; }
                    case 2 -> { wallsLeft[row][col]=false; wallsRight[nr][nc]=false; }
                    case 3 -> { wallsRight[row][col]=false; wallsLeft[nr][nc]=false; }
                }

                visited[nr][nc] = true;
                stack.push(new int[]{row, col});
                stack.push(new int[]{nr, nc});
                break;
            }
        }
    }
}
