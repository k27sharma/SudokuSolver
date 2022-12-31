package Soduku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SodukuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        // Create the main window
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9, 9));
        JTextField[][] cells = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                inputPanel.add(cells[i][j]);
            }
        }

        // Create the solve button
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read the input from the text fields
                int[][] board = new int[9][9];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        board[i][j] = Integer.parseInt(cells[i][j].getText().trim());
                    }
                }

                // Solve the Sudoku puzzle
                solveBoard(board);

                // Update the text fields with the solution
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cells[i][j].setText(Integer.toString(board[i][j]));
                    }
                }
            }
        });

        // Add the input panel and solve button to the main window
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);

        // Set the size of the window and make it visible
        frame.setSize(500, 500);
        frame.setVisible(true);
    }



//        char[][] board = {
//                {'7', '.', '2', '.', '5', '.', '6', '.', '.'},
//                {'.', '.', '.', '.', '.', '3', '.', '.', '.'},
//                {'1', '.', '.', '.', '.', '.', '5', '.', '.'},
//                {'8', '.', '.', '.', '.', '.', '.', '9', '.'},
//                {'.', '4', '3', '.', '.', '.', '7', '5', '.'},
//                {'.', '9', '.', '.', '.', '.', '.', '.', '8'},
//                {'.', '.', '9', '7', '.', '.', '.', '.', '5'},
//                {'.', '.', '.', '2', '.', '.', '.', '.', '.'},
//                {'.', '.', '7', '.', '4', '.', '2', '.', '3'}
//        };
//        printboard(board);

//        if(solveBoard(board)){
//            System.out.println("Solved Sucessfully! :)");
//        }
//        else{
//            System.out.println("Oops! Sorry , This board is unsolvable :(");
//        }
//
//        printboard(board);



    private static void printboard(int[][] board) {

        System.out.println("***************************");
        for(int row= 0; row<GRID_SIZE; row++){
            if(row%3==0 && row!= 0){
                System.out.println("++=======================++");
            }
            for(int col = 0; col<GRID_SIZE; col++){
                if(col==0) System.out.print("#" + " ");
                if(col%3==0 && col!= 0) {
                    System.out.print("||" + " ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println("#");
//            System.out.println();
        }

        System.out.println("***************************");
        System.out.println();
    }



    private static boolean solveBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j] == 0) {

                    for (int k = 1; k <= 9; k++) {

                        if (check(board, i, j, k)) {

                             board[i][j] = k;

                            if (solveBoard(board) == true) return true;
                            else board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean check(int[][] board, int row, int col, int num) {

        for (int i = 0; i < GRID_SIZE; i++) {

            //checking for row
            if (board[row][i] == num) return false;
            //checking for column
            if (board[i][col] == num) return false;
            // checking every 3*3 grid
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) return false;
        }

        return true;
    }

}





