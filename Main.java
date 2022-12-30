import java.util.Scanner;
class SudokuSolver {
    public static final int BOARD_SIZE = 9;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        String puzzle = "";
        System.out.println("Type or paste the desired 9x9 board to be solved with spaces in between numbers, 0's for blanks, and a new line for each row")
        for(int i = 0; i < BOARD_SIZE;i++)
        {
            puzzle += scan.nextLine();
            puzzle += "\n";
        }
        Scanner puz = new Scanner(puzzle);

        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                String temp = puz.next();
                int ans = Integer.parseInt(temp);
                board[row][col] = ans;
            }
        }
            printBoard(board);
        if(solveBoard(board)) {
            System.out.println("Solved Successfully");
        } else
            System.out.println("Unsolvable Board");
        printBoard(board);
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
            if(board[row][i] == num)
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
                if(board[i][j] == num)
                    return true;
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int board [][], int num, int col, int row)
    {
        return !numInRow(board, num, row) && !numInColumn(board, num, col) && !numInBox(board, num, col, row);
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
                        if (isValidPlacement(board, numTry, col, row))
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
 
