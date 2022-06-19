package LeetCode;

import java.util.HashSet;

public class ValidSodoku {

    static char[][] board = {
                            {'5','3','X','X','7','X','X','X','X'},
                            {'6','X','X','1','9','5','X','X','X'},
                            {'X','9','8','X','X','X','X','6','X'},
                            {'8','X','X','X','6','X','X','X','3'},
                            {'4','X','X','8','X','3','X','X','1'},
                            {'7','X','X','X','2','X','X','X','6'},
                            {'X','6','X','X','X','X','2','8','X'},
                            {'X','X','X','4','1','9','X','X','5'},
                            {'X','X','X','X','8','X','X','7','9'}
                            };
    public static void hashSetSolution(char[][] board) {

        HashSet<String> row = new HashSet<>();
        HashSet<String> column = new HashSet<>();
        HashSet<String> threeByThreeBox = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
//                System.out.println(board[i][j]); ---> print every row
//                System.out.println(board[j][i]); ---> print every column
                if (board[i][j] != 'X') {
                   if(row.contains("row " + i + " - " + board[i][j]) ||
                           column.contains("column " + j + " - " + board[i][j]) ||
                           threeByThreeBox.contains("3 x 3 at: " + board[i][j] + " " +  i/3 + " " +  j/3)
                   ) {
                            System.out.println("Invalid bozo");
                   }
                   row.add("row " + i + " - " + board[i][j]);
                    column.add("column " + j + " - " + board[i][j]);
                    threeByThreeBox.add("3 x 3 at: " + board[i][j] + " " +  i/3 + " " +  j/3);

                }
            }
//            System.out.println(Arrays.toString(board[2]) + " ");
//            System.out.println(row);
//            System.out.println(" ");
//            System.out.println(column);
            System.out.println(threeByThreeBox);
            System.out.println(" ");
        }
    }

    public static void matrixTraversal(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.println(board[j][i]);
                if (j == 8) {
                    System.out.println(" ");
                }

            }
        }
    }

    public static void main(String[] args) {
//        hashSetSolution(board);
        matrixTraversal(board);
    }
}
