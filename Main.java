import java.util.Scanner;
class SudokuSolver {
    public static final int BOARD_SIZE = 9;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] board = new int[9][9]; // initializes board to correct size
        String puzzle = "";
        System.out.println("Type or paste the desired 9x9 board to be solved with spaces in between numbers," +
                " making a new line for each row, and a 0 as a placeholder for blanks");
        // user pastes in puzzle to solve, the board is stored in a string
        for(int i = 0; i < BOARD_SIZE;i++)
        {
            puzzle += scan.nextLine();
            puzzle += "\n";
        }
        Scanner puz = new Scanner(puzzle);
        //Transforms each number in the string to an int and adds it to the appropriate coordinate in the board array.
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                String temp = puz.next();
                int ans = Integer.parseInt(temp);
                board[row][col] = ans;
            }
        }
        //Calls algorithm to solve it and display either the puzzle solved or an error message if it can't be solved
        boardOutput(board);
        if(solveBoard(board)) {
            System.out.println("Solved Successfully");
        } else
            System.out.println("Unsolvable Board");
        boardOutput(board);
    }

    /** this method prints the board array with lines in between every "box" to make the board appear more like a real
     * sudoku board
     * @param board is the 2D array containing the current puzzle that needs to be formatted and printed
     */
    private static void boardOutput(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    /** This method checks if the number being checked is in one of the 9 3x3 boxes in the given sudoku board by finding
     * the top left corner of the current box and iterating through the whole box based on that location to check
     * @param board is the 2D array being checked
     * @param num is the number being tried in the box
     * @param col is the column of the top left corner box of the box being checked
     * @param row is the row of the top left corner box of the box being checked
     * @return True if the number is not in the box, false otherwise
     */
    private static boolean boxCheck(int [][] board, int num, int col, int row)
    {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for(int i = boxRow;i < boxRow + 3;i++)
        {
            for(int j = boxCol; j < boxCol + 3;j++)
            {
                if(board[i][j] == num)
                    return true;
            }
        }
        return false;
    }

    /** Checks if a given number is in a given row of the sudoku board
     *
     * @param board the 2D array that is being checked
     * @param num the number that is being tried to be put in the row
     * @param row the row of numbers that the number is being tried to be put into
     * @return true if the number isn't in the row, false otherwise
     */
    private static boolean rowCheck(int [][] board, int num, int row)
    {
        for(int i = 0;i < BOARD_SIZE; i++)
        {
            if(board[row][i] == num)
                return true;
        }
        return false;
    }
    /** Checks if a given number is in a given row of the sudoku board
     *
     * @param board the 2D array that is being checked
     * @param num the number that is being tried to be put in the row
     * @param col the column of numbers that the number is being tried to be put into
     * @return true if the number isn't in the column, false otherwise
     */
    private static boolean colCheck(int [][] board, int num, int col)
    {
        for(int i = 0;i < BOARD_SIZE;i++)
        {
            if(board[i][col] == num)
                return true;
        }
        return false;
    }

    /** Checks if the number is valid to place by calling the 3 methods that check the 3 conditions needed in a normal
     * game of sudoku
     * @param board the 2D array that is being checked
     * @param num the number that is testing whether its placement is valid or not
     * @param col the column where the number is being placed
     * @param row the row where the number is being placed
     * @return True if all 3 conditions are met, false otherwise
     */
    private static boolean canPlace(int board [][], int num, int col, int row)
    {
        return !boxCheck(board, num, col, row) && !rowCheck(board, num, row) && !colCheck(board, num, col);
    }

    /** Checks the board to see where the blank spaces are, denoted by a 0. Starting at 1, it checks whether all numbers
     * [1,9] are valid, placing the first valid number in the current location in the array. It calls the method again,
     * and if the whole board cannot be solved with that number, it changes it back to 0 and repeats this for all 0's
     * until it reaches the end or determines the board could not be solved to begin with
     * @param board 2D array containing board to be solved.
     * @return True if the board can be solved, false otherwise.
     */
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) 
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                if (board[row][col] == 0) 
                {
                    for (int numTry = 1; numTry <= BOARD_SIZE; numTry++) 
                    {
                        if (canPlace(board, numTry, col, row))
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
 
