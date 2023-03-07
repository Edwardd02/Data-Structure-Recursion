
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ChessBoard extends JPanel{
    private int rows;
    private int cols;
    private Cell[][] maze;
    private boolean isCapture(int row, int col)
    {
        //check for NS sides, WE sides are not needed to check because we are adding queens row by row
        for(int i = 1; i < 8; i++)
        {
            int rowCheck = row + i;
            if(rowCheck > 7)
            {
                rowCheck = rowCheck - 8;
            }
            if(maze[rowCheck][col].getBackground() == Color.BLACK)
            {
                return true;
            }
        }
        //check for NE, NW, SE, SW sides
        for(int i = 1; (row - i) > -1 && (col + i) < 8; i++)
        {
            if(maze[row - i][col + i].getBackground() == Color.BLACK)
            {
                return true;
            }
        }
        for(int i = 1; (col - i) > -1 && (row + i) < 8; i++)
        {
            if(maze[col - i][row + i].getBackground() == Color.BLACK)
            {
                return true;
            }
        }
        for(int i = 1; (row - i) > -1 && (col - i) > -1; i++)
        {
            if(maze[row - i][col - i].getBackground() == Color.BLACK)
            {
                return true;
            }
        }
        for(int i = 1; (row + i) < 8 && (col + i) < 8; i++)
        {
            if(maze[row + i][col + i].getBackground() == Color.BLACK)
            {
                return true;
            }
        }
        return false;
    }
    public int queensProblem(int row)
    {
        if(row == 0)
        {
            //set a random col of first row to 0, Black means there is a queen
            int col = 4;//(int) (Math.random() * 8);
            maze[row][col].setBackground(Color.BLACK);
            return queensProblem(row + 1);

        }
        else if(row < 8)
        {
            int i;
            for(i = 0; i < 8; i++)
            {
                //if there is a position that works
                if(!isCapture(row, i) && maze[row][i].getBackground() != Color.gray)
                {
                    maze[row][i].setBackground(Color.BLACK);
                    break;
                }
            }
            //if all position doesn't work
            if(i == 8)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(maze[row - 1][j].getBackground() == Color.black)
                    {
                        maze[row - 1][j].setBackground(Color.gray);
                    }
                    maze[row][j].setBackground(Color.white);

                }
                return queensProblem(row - 1);
            }
            return queensProblem(row + 1);
        }
        else
        {
            //set all didn't work block to normal and quit from the recursion
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(maze[i][j].getBackground() == Color.gray)
                    {
                        maze[i][j].setBackground(Color.white);
                    }
                }
            }
            return -1;
        }
    }

    public void drawChessBoard() {

        for(int row = 0; row  < rows; row++) {
            for(int col = 0; col < cols; col++) {

                maze[row][col].repaint();

            }
        }
        this.repaint();

    }
    public ChessBoard(int rows, int cols) {
        this.setPreferredSize(new Dimension(800,800));
        this.rows = rows;
        this.cols = cols;
        this.setLayout(new GridLayout(rows,cols));
        this.maze =  new Cell[rows][cols];
        for(int row = 0 ; row  < rows ; row++) {
            for(int col = 0; col < cols; col++) {

                maze[row][col] = new Cell(row,col);

                this.add(maze[row][col]);
            }

        }
        queensProblem(0);
        drawChessBoard();
    }




}
