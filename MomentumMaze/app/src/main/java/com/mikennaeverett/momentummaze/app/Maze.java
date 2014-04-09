package com.mikennaeverett.momentummaze.app;

/**
 * Created by mikenna on 4/9/14.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Maze {
    public int mazeDimX, mazeDimY, gridDimX, gridDimY;
    public String mazeString;
    public Cell[][] cells; // 2d array of Cells
    public Cell startPoint, endPoint, currentPoint;
    public char[][] grid;

    public Maze(int levelNum) {
        if(levelNum == 1){
            mazeString = "start: (0,0)\nend: (4,4)\nXXXXXXXXXXXXXXXXXXXXX\nX           X       X\nX   XXXXX   X       X\nX                   X\nX   X           XXXXX\nX   X               X\nX   X   XXXXX       X\nX                   X\nX   XXXXX   X   XXXXX\nX           X       X\nXXXXXXXXXXXXXXXXXXXXX";
        }
        else if (levelNum == 2) {
            mazeString = "start: (2,2)\nend: (3,3)\nXXXXXXXXXXXXXXXXXXXXXXXXX\nX   X               X   X\nX   X   XXXXX       X   X\nX                       X\nX   X   X   X   XXXXX   X\nX   X   X   X           X\nX   X   XXXXXXXXX       X\nX           X   X       X\nXXXXX       X   X       X\nX                       X\nX       X   X       XXXXX\nX       X   X           X\nXXXXXXXXXXXXXXXXXXXXXXXXX";
        }
        else if (levelNum == 3) {
            mazeString = "start: (0,3)\nend: (5,2)\nXXXXXXXXXXXXXXXXXXXXXXXXX\nX           X           X\nX       XXXXX       XXXXX\nX                       X\nXXXXX   XXXXX       X   X\nX                   X   X\nX           X       XXXXX\nX           X           X\nX   X       XXXXX       X\nX   X                   X\nXXXXX   X       X   X   X\nX       X       X   X   X\nXXXXXXXXXXXXXXXXXXXXXXXXX";
        }
        else if (levelNum == 4) {
            mazeString = "start: (0,2)\nend: (6,4)\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\nX                   X       X\nX       X   XXXXX   X       X\nX       X                   X\nXXXXX   X   X   XXXXX   XXXXX\nX           X               X\nXXXXX       X       X       X\nX                   X       X\nX           X   XXXXX   XXXXX\nX           X               X\nX   XXXXX   X           XXXXX\nX       X                   X\nXXXXX   X   X       XXXXX   X\nX           X       X       X\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        }
        else if (levelNum == 5) {
            mazeString = "start: (4,1)\nend: (2,4)\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\nX   X           X           X\nX   X   XXXXX   X       XXXXX\nX                           X\nX           X           X   X\nX           X           X   X\nX   XXXXX   X   X   X   X   X\nX               X   X       X\nX           XXXXX   X   XXXXX\nX                           X\nXXXXX   X   X   XXXXX       X\nX       X   X               X\nX       XXXXX  X        X   X\nX              X        X   X\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        }
        else {
            mazeString = "start: (5,3)\nend: (3,5)\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\nX           X               X       X\nXXXXX   X   X   X   XXXXX   X   XXXXX\nX       X                       X   X\nX   XXXXX   X   XXXXX   X   X   X   X\nX                       X   X       X      \nX   X   X   X   X   X   X   X   X   X\nX       X           X               X\nXXXXX   X   XXXXX   XXXXX   X   XXXXX\nX                           X       X\nX   X   X   XXXXX   XXXXX   X   X   X                            \nX               X   X               X\nX   XXXXX   X   X   X   XXXXX   XXXXX\nX   X                               X\nX   X   XXXXX   XXXXX   X   X   X   X\nX                   X               X\nXXXXX   X   XXXXX   X   X   XXXXX   X\nX               X               X   X\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        }

        parseMazeFile();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String readMazeFromFile(FileReader fr) throws FileNotFoundException {
        String everything = "";

        BufferedReader br = new BufferedReader(fr);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return everything;
    }

    public void parseMazeFile() {
        String[] lines = mazeString.split(System.getProperty("line.separator"));
        // get start and end point from first two lines of the file
        Pattern nums = Pattern.compile("\\d+");
        Matcher matcher = nums.matcher(lines[0]);
        matcher.find();
        int startX = Integer.parseInt(matcher.group());
        matcher.find();
        int startY = Integer.parseInt(matcher.group());
        matcher = nums.matcher(lines[1]);
        matcher.find();
        int endX = Integer.parseInt(matcher.group());
        matcher.find();
        int endY = Integer.parseInt(matcher.group());


        // the rest of the file will be the maze, represented by a string
        mazeDimX = (lines[2].length() - 1) / 4;
        mazeDimY = (lines.length-2 - 1 ) / 2;
        gridDimX = lines[2].length();
        gridDimY = lines.length - 2;

        grid = new char[gridDimX][gridDimY];
        for (int y = 2; y < lines.length; y++) {
            for (int x = 0; x < lines[2].length(); x++) {
                grid[x][y-2] = lines[y].charAt(x);
            }
        }

        // initialize Cells
        cells = new Cell[mazeDimX][mazeDimY];
        for (int x = 0; x < mazeDimX; x++) {
            for (int y = 0; y < mazeDimY; y++) {
                cells[x][y] = new Cell(x, y, false); // create cell (see Cell constructor)
            }
        }

        // now, set neighbors for cells
        int cellX;
        int cellY;
        for (int y = 1; y < gridDimY; y+=2) {
            for (int x = 2; x < gridDimX; x+=4) {
                cellX = (x-2)/4;
                cellY = (y-1)/2;
                Cell curCell = getCell(cellX,cellY);
                if (grid[x-2][y] != 'X') {
                    curCell.addNeighbor(getCell(cellX-1,cellY));
                }
                if (grid[x+2][y] != 'X') {
                    curCell.addNeighbor(getCell(cellX+1,cellY));
                }
                if (grid[x][y-1] != 'X') {
                    curCell.addNeighbor(getCell(cellX,cellY-1));
                }
                if (grid[x][y+1] != 'X') {
                    curCell.addNeighbor(getCell(cellX,cellY+1));
                }
            }
        }

        startPoint = getCell(startX, startY);
        currentPoint = startPoint;
        endPoint = getCell(endX, endY);
    }

    public static class Cell {
        public int x, y; // coordinates
        // cells this cell is connected to
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        // impassable cell
        boolean wall = true;
        // construct Cell at x, y
        public Cell(int x, int y) {
            this(x, y, true);
        }
        // construct Cell at x, y and with whether it isWall
        public Cell(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.wall = isWall;
        }

        public boolean isNeighbor(Cell other) {
            if (this.neighbors.contains(other)) {
                return true;
            }
            return false;
        }

        // add a neighbor to this cell, and this cell as a neighbor to the other
        void addNeighbor(Cell other) {
            if (!this.neighbors.contains(other)) { // avoid duplicates
                this.neighbors.add(other);
            }
            if (!other.neighbors.contains(this)) { // avoid duplicates
                other.neighbors.add(this);
            }
        }
        // used in updateGrid()
        boolean isCellBelowNeighbor() {
            return this.neighbors.contains(new Cell(this.x, this.y + 1));
        }
        // used in updateGrid()
        boolean isCellRightNeighbor() {
            return this.neighbors.contains(new Cell(this.x + 1, this.y));
        }
        // useful Cell representation
        @Override
        public String toString() {
            return String.format("(%s, %s)", x, y);
        }
        // useful Cell equivalence
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Cell)) return false;
            Cell otherCell = (Cell) other;
            return (this.x == otherCell.x && this.y == otherCell.y);
        }
        // should be overridden with equals
        @Override
        public int hashCode() {
            // random hash code method designed to be usually unique
            return this.x + this.y * 256;
        }
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) { // catch out of bounds
            return null;
        }
    }

    public String toString() {
        String output = "";
        for (int y = 0; y < gridDimY; y++) {
            for (int x = 0; x < gridDimX; x++) {
                output += grid[x][y];
            }
            output += "\n";
        }
        return output;
    }

    public void moveRight() {
        // move current position right until it hits a wall
    }

    public void moveLeft() {
        // move current position left
    }

    public void moveUp() {
        // move current position up
    }

    public void moveDown() {
        // move current position down
    }
}