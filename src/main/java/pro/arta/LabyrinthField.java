package pro.arta;

public class LabyrinthField {
    private int[][] field;
    private int height;
    private int width;
    final private int BLANK = 0;
    private int finishX = -1;
    private int finishY = -1;
    private int[] dx = {0, 1, 0, -1};        // neighboring cells
    private int[] dy = {-1, 0, 1, 0};        // up, right, down, left

    public LabyrinthField(int[][] labyrinth) {
        field = labyrinth;
        if (field != null) {
            height = field.length;
            width = field[0].length;
        } else {
            height = -1;
            width = -1;
        }
    }

    public int[][] getField() {
        return field;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void SearchShortWay () {
        int step = 1;
        int neighborX, neighborY;
        boolean stop;
        boolean isExit;

        do {
            stop = false;
            isExit = false;
            out: for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (field[y][x] == step) {
                        isExit = true;
                        if (y == 0 || y == height - 1 ||
                                x == 0 || x == width -1 ) {
                            /* Finish point is found */
                            finishY = y;
                            finishX = x;
                            stop = true;
                            break out;
                        }

                        for (int k = 0; k < 4; k++) {
                            neighborY = y + dy[k];
                            neighborX = x + dx[k];
                            if (neighborY >= 0 && neighborY < height &&
                                    neighborX >= 0 && neighborX < width &&
                                    field[neighborY][neighborX] == BLANK) {
                                field[neighborY][neighborX] = step + 1;
                            }
                        }
                    }
                }
            }
            step++;
        } while (!stop && isExit);

    }

    public boolean getShortWay (String[][] labyrinth) {
        this.SearchShortWay ();
        int neighborX, neighborY;

        /* Build the shortest way */
        if (finishY != -1 && finishX != -1) {
            labyrinth[finishY][finishX] = field[finishY][finishX] + "";
            int step = field[finishY][finishX];
            int y = finishY, x = finishX;
            do {
                for (int k = 0; k < 4; k++) {
                    neighborY = y + dy[k];
                    neighborX = x + dx[k];
                    if (neighborY >= 0 && neighborY < height &&
                            neighborX >= 0 && neighborX < width &&
                            field[neighborY][neighborX] == step - 1) {
                        labyrinth[neighborY][neighborX] = step - 1 + "";
                        y = neighborY;
                        x = neighborX;
                    }
                }
                step--;
            } while (step != 1);

            return true;

        }

        return false;
    }

}
