package com.example.ayushib.tictactoe.model;

/**
 * Created by Ayushi B on 10-Jan-18.
 */

public class Victory {

    public int row, col, lineType;
    public int winner;

    public Victory(int row, int col, int lineType, int winner) {
        this.row = row;
        this.col = col;
        this.lineType = lineType;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Victory{" +
                "row=" + row +
                ", col=" + col +
                ", lineType=" + lineType +
                ", winner=" + winner +
                '}';
    }
}
