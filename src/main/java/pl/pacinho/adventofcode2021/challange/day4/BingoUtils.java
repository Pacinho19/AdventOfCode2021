package pl.pacinho.adventofcode2021.challange.day4;

import java.util.ArrayList;
import java.util.List;

public class BingoUtils {

    private static final int ROW_COUNT = 5;

    private static final int COL_COUNT =5;
    public static boolean checkWin(List<Cell> board) {
        //Check rows
        for (int i = 0; i < ROW_COUNT; i++) {
            int finalI = i;
            boolean win = board.stream().filter(c -> c.getRow() == finalI && c.isSelected()).count() == COL_COUNT;
            if (win) return true;
        }

        //Check column
        for (int i = 0; i < COL_COUNT; i++) {
            int finalI = i;
            boolean win = board.stream().filter(c -> c.getCol() == finalI && c.isSelected()).count() == ROW_COUNT;
            if (win) return true;
        }

        return false;
    }

    public static Bingo parseBoard(String s) {
        List<Cell> out = new ArrayList<>();
        String[] split = s.split("\n");

        for (int i = 0; i < split.length; i++) {
            int col = 0;
            String[] s1 = split[i].split(" ");
            for (int j = 0; j < s1.length; j++) {
                String numberS = s1[j];
                Integer numberI;
                try {
                    numberI = Integer.parseInt(numberS);
                } catch (Exception ex) {
                    continue;
                }
                out.add(new Cell(numberI, col, i));
                col++;
            }
        }
        return new Bingo(out);
    }
}