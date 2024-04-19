/**
 * Name: Roman Balayar
 * CS251L
 * Project 2
 * 01/28/2024
 */

import cs251.lab2.*;


import java.util.Random;

public class Gomoku implements GomokuInterface {
    /**
     * Here I'm using concepts of the interface. Concepts of the objects orientation.
     * In this Gomoku class. I'm implementing GomokuInterface, which class.
     */

    public static Square[][] board;
    public static boolean computerPlayer;
    public static Square curTn;

    public static Gomoku game;

    /**
     * Initializes the Gomoku game by creating a new instance and calling the initializeGame() method.
     */
    public Gomoku() {
        initializeGame();
    }

    /**
     * The main method of the Gomoku game. It creates a new instance of the game,
     * initializes the computer player if specified in the arguments, and displays the GUI.
     *
     * @param args Command-line arguments to specify the player type (e.g., "COMPUTER").
     */

    public static void main(String[] args) {
        game = new Gomoku();
        if (args.length > 0) {
            game.initComputerPlayer(args[0]);
        }
        GomokuGUI.showGUI(game);
    }

    /**
     * Retrieves the number of columns on the game board.
     *
     * @return The number of columns.
     */

    @Override
    public int getNumCols() {
        return DEFAULT_NUM_COLS;
    }

    /**
     * Retrieves the number of rows on the game board.
     *
     * @return The number of rows.
     */

    @Override
    public int getNumRows() {
        return DEFAULT_NUM_ROWS;
    }

    /**
     * Retrieves the number of consecutive squares required for a win.
     *
     * @return The number of squares required for a win.
     */
    @Override
    public int getNumInLineForWin() {
        return SQUARES_IN_LINE_FOR_WIN;
    }

    /**
     * Initializes the computer player based on the given argument.
     *
     * @param s A string representing the player type ("COMPUTER" or other).
     */
    @Override
    public void initComputerPlayer(String s) {
        computerPlayer = "COMPUTER".equalsIgnoreCase(s);

    }

    /**
     * Makes a move for the computer player.
     */

    public void makeComputerMove() {
        Random random = new Random();
        int row;
        int col;
        do {
            row = random.nextInt(getNumRows());
            col = random.nextInt(getNumCols());
        } while (board[row][col] != Square.EMPTY);
        board[row][col] = curTn;
    }

    /**
     * handleClickAt control overall of the game and check all the wins of
     * the Gomoku game. From method, I drive private methods that check
     * horizontal win, vertical win, and diagonals wins.
     *
     * @param row - number of the rows
     * @param col - number of the column
     * @return - return to data type
     */
    @Override
    public TurnResult handleClickAt(int row, int col) {
        if (board[row][col] == Square.EMPTY) {
            board[row][col] = curTn;
            if (computerPlayer) {
                curTn = (curTn == Square.CROSS) ? Square.RING : Square.CROSS;
                makeComputerMove();
            }
            if (checkWin(row, col)) {
                return (curTn == Square.CROSS) ? TurnResult.CROSS_WINS : TurnResult.RING_WINS;
            }
            if (!computerPlayer) {
                curTn = (curTn == Square.CROSS) ? Square.RING : Square.CROSS;
            }

        }
        return TurnResult.GAME_NOT_OVER;
    }


    /**
     * Checks for a win condition by evaluating horizontal, vertical, and diagonal sequences of squares.
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a win, false otherwise.
     */
    private boolean checkWin(int row, int col) {
        if (computerPlayer) {

            if (curTn == Square.CROSS) {
                curTn = Square.RING;
            } else {
                curTn = Square.CROSS;
            }
        }
        return horizontalWin(row, col) || verticalWin(row, col) || digRLUpward(row, col) ||
                digLRUpward(row, col) || digRLDownward(row, col) || digLRDownward(row, col);


    }

    /**
     * Checks for a horizontal win.
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a horizontal win, false otherwise.
     */
    private boolean horizontalWin(int row, int col) {
        int countWin = 0;
        for ( int i = 0; i < getNumCols(); i++) {
            if (board[row][i] == curTn) {
                countWin++;
                if (countWin >= getNumInLineForWin()) return true;
            } else {
                countWin = 0;
            }
        }
        return false;
    }

    /**
     * Checks for a vertical win.
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a vertical win, false otherwise.
     */
    private boolean verticalWin(int row, int col) {
        int countWin = 0;
        for ( int i = 0; i < getNumRows(); i++) {
            if (board[i][col] == curTn) {
                countWin++;
                if ( countWin >= getNumInLineForWin()) return true;
            } else {
                countWin = 0;
            }
        }
        return false;
    }

    /**
     * Checks for a diagonal win (right to left upward direction).
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a diagonal win, false otherwise.
     */
    private boolean digRLUpward(int row, int col) {
        int countWin = 0;
        for ( int i = row, j = col; i < getNumRows() && j < getNumCols(); i++, j++) {
            if ( board[i][j] != curTn) {
                countWin = 0;
            } else {
                countWin++;
                if ( countWin >= getNumInLineForWin())
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks for a diagonal win (left to right upward direction).
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a diagonal win, false otherwise.
     */
    private boolean digLRUpward(int row, int col) {
        int countWin = 0;
        for ( int i = row, j = col; i < getNumRows() && j >= 0; i++, j--) {
            if ( board[i][j] == curTn) {
                countWin++;
                if ( countWin >= getNumInLineForWin())
                    return true;
            } else {

                countWin = 0;
            }
        }
        return false;
    }


    /**
     * Checks for a diagonal win (right to left downward direction).
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a diagonal win, false otherwise.
     */
    private boolean digRLDownward(int row, int col) {
        int countWin = 0;
        for ( int i = row, j = col; i >= 0 && j < getNumCols(); i--, j++) {
            if ( board[i][j] != curTn) {
                countWin = 0;
            } else {
                countWin++;
                if ( countWin >= getNumInLineForWin())
                    return true;
            }
        }
        return false;

    }

    /**
     * Checks for a diagonal win (left to right downward direction).
     *
     * @param row The row where the latest move was made.
     * @param col The column where the latest move was made.
     * @return True if there's a diagonal win, false otherwise.
     */
    private boolean digLRDownward(int row, int col) {
        int countWin = 0;
        for ( int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if ( board[i][j] != curTn) {
                countWin = 0;
            } else {
                countWin++;
                if ( countWin >= getNumInLineForWin())
                    return true;
            }
        }
        return false;
    }


    /**
     * Here at this method, I initialize the game as the Gomoku as Empty Square box
     * Also I initialize the RING as the char when we first clicked in the board
     */
    @Override
    public void initializeGame() {
        board = new GomokuInterface.Square[getNumRows()][getNumCols()];
        for ( int i = 0; i < getNumRows(); ++i) {
            for ( int j = 0; j < getNumCols(); ++j) {
                board[i][j] = GomokuInterface.Square.EMPTY;
            }
        }
        curTn = Square.RING;
    }

    /**
     * Retrieves the current game board as a string representation.
     *
     * @return The current game board as a string.
     */

    @Override
    public String getCurrentBoardAsString() {
        StringBuilder sb = new StringBuilder();
        for (GomokuInterface.Square[] row : board) {
            for (GomokuInterface.Square square : row) {
                sb.append(square.toChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves the current turn.
     *
     * @return The current turn (RING or CROSS).
     */
    @Override
    public Square getCurrentTurn() {
        return curTn;
    }


}
