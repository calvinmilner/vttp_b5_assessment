package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.*;

public class TicTacToe {
    private final int[] utility = { -1, 0, 1 };
    private final String[] legal = { "0 0", "1 0", "1, 2", "2 2" };
    private String textFile;
    private char[][] board = new char[3][3];
    // private int width = 3;
    // private int height = 3;
    private int posX = 0;
    private int posY = 0;

    public TicTacToe() {

    }

    public TicTacToe(String tf) {
        textFile = tf;
    }

    public void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(textFile));
        // printBoard(br);
        board = initializeBoard();
        populateBoard(br);
        return;

    }

    private char[][] initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '.';
            }
        }
        // Constants.EMPTY.substring(0, width).toCharArray();
        return board;
    }

    public void printBoard() {
        System.out.println("TicTacToe Board");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j]);
    }

    public void populateBoard(BufferedReader br) throws IOException {

        String line;
        int j = 0;
        while ((line = br.readLine()) != null) {
            char[] data = line.toCharArray();
            // System.out.println(data);
            for (int i = 0; i < data.length; i++) {
                board[j][i] = data[i];
                j++;
                System.out.printf("%s\n", String.valueOf(board[i][j]));
            }
        }
    }

    public int[] checkEmptyPos() {
        int[] coordinate = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    coordinate = coordinates(i, j);
                } else
                    continue;
            }
        }
        return coordinate;
    }

    public void place(char player, int[] pos) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == '.')
                    board[i][j] = player;
                else
                    continue;
            }
        }        
    }

    public int evaluate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == 'X') {
                    return utility[1];
                }
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == 'X') {
                    return utility[1];
                } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == 'X'
                        || board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == 'X') {
                    return utility[1];
                } else if (board[i][j] == '.' || board[i][j] == 'O') {
                    return utility[0];
                }
            }
        }
        return utility[1];

    }

    // public String coordinates(int x, int y) {
    //     String valueX = String.valueOf(x);
    //     String valueY = String.valueOf(y);
    //     String coordinates = valueX + " " + valueY;
    //     return coordinates;

    // }

    public int[] coordinates(int x, int y) {
        // posX.setPosX(x);
        // posY.setPosY(y);
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;

    }

    public String getTextFile() {
        return textFile;
    }

    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
