package com.example.mange.space_shooter;

import com.example.mange.space_shooter.Characters.Blue;
import com.example.mange.space_shooter.Characters.Character;
import com.example.mange.space_shooter.Characters.Green;
import com.example.mange.space_shooter.Characters.Pink;
import com.example.mange.space_shooter.Characters.Player;

public class BoardConfiguration {
    Character[][] board = new Character[10][10];
    public BoardConfiguration() {//Default configuration
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++){
                if (row == 0 && col > 0 && col < 9)
                    board[row][col] = new Green();
                else if (row == 1 && col < 8)
                    board[row][col] = new Blue();
                else if (row == 2 && col > 0 && col < 8)
                    board[row][col] = new Pink();
        }}
        board[9][4] = new Player();
    }
    public BoardConfiguration(int level) {//Default configuration for levels
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
                if (row == 0 && col > 0 && col < 1)
                    board[row][col] = new Green(level);
                else if (row == 1 && col < 2)
                    board[row][col] = new Blue(level);
                else if (row == 2 && col > 0 && col < 1)
                    board[row][col] = new Pink(level);
        board[9][4] = new Player();
    }
}
