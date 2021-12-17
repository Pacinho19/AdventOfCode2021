package pl.pacinho.adventofcode2021.challange.day17;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;

public class Day17Part1 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day17Part1().calculate("day17\\input.txt"));
    }

    private int targetXMin;
    private int targetXMax;
    private int targetYMin;
    private int targetYMax;

    private int maxY = Integer.MIN_VALUE;

    @Override
    public long calculate(String filePath) {
        String info = FileUtils.readAsText(new File(filePath));
        targetXMin = Integer.parseInt(info.split("x=")[1].split("\\.\\.")[0]);
        targetXMax = Integer.parseInt(info.split("\\.\\.")[1].split(", y=")[0]);
        targetYMin = Integer.parseInt(info.split("y=")[1].split("\\.\\.")[0]);
        targetYMax = Integer.parseInt(info.split("y=.*\\.\\.")[1]);


//        Integer localMax = checkTrajectory(0, 9, 0, 9, Integer.MIN_VALUE);

        int row = 0;
        int col = 0;
        while (checkOutOfY(row, col)) {
            col = 0;
            while (checkOutOfX(row, col)) {
                checkTrajectory(row, col, row, col, 0);
                col++;
            }
            row++;
        }
        return maxY;
    }

    private Integer checkTrajectory(int row, int col, int prevRow, int prevCol, int localMax) {
        if (row > localMax) localMax = row;
        System.out.println(col + "," + row);
        if (checkArea(row, col)) {
            if (localMax > maxY) maxY = localMax;
            return null;
        }
        if (checkOutOfArea(row, col) || checkOutOfX(row, col) || checkOutOfY(row, col)) return null;


        int rowChange = prevRow - 1;
        int colChange = prevCol == 0 ? 0 : prevCol - 1;
        checkTrajectory((row + rowChange), (col + colChange), rowChange, colChange, localMax);

        return localMax;
    }

    private boolean checkArea(int row, int col) {
        return (row >= targetYMin && row <= targetYMax
                && col >= targetXMin && col <= targetXMax);
    }

    private boolean checkOutOfArea(int row, int col) {
        return !(row >= targetYMax && col <= targetXMax);
    }

    private boolean checkOutOfX(int row, int col) {
        return row < targetYMax && col < targetXMin;
    }

    private boolean checkOutOfY(int row, int col) {
        return row > targetYMin && col > targetXMax;
    }
}