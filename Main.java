class SudokuSolver {
    public static final int BOARD_SIZE = 9;
    public static void main(String[] args) {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        if(solveBoard(board)) {
            System.out.println("Solved Successfully");
            printBoard(board);
        }
        else
            System.out.println("Unsolvable Board");
    }
    private static void printBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }
    private static boolean numInRow(int [][] board, int num, int row)
    {
        for(int i = 0;i < BOARD_SIZE;i++)
        {
            if(board[row][i]== num)
                return true;
        }
        return false;
    }
    private static boolean numInColumn(int [][] board, int num, int col)
    {
        for(int i = 0;i < BOARD_SIZE;i++)
        {
            if(board[i][col] == num)
                return true;
        }
        return false;
    }
    private static boolean numInBox(int [][] board, int num, int col, int row)
    {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;
        for(int i = localBoxRow;i < localBoxRow + 3;i++)
        {
            for(int j = localBoxCol; j < localBoxCol + 3;j++)
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int board [][], int num, int col, int row)
    {
        return !numInRow(board, num, row) && !numInColumn(board, num, col) && numInBox(board, num, col, row);
    }
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) 
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                if (board[row][col] == 0) 
                {
                    for (int numTry = 1; numTry <= BOARD_SIZE; numTry++) 
                    {
                        if (isValidPlacement(board, numTry, row, col))
                        {
                            board[row][col] = numTry;

                            if (solveBoard(board)) 
                            {
                                return true;
                            }
                            else 
                            {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
 