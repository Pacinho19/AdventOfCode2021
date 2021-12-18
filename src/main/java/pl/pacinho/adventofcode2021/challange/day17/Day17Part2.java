package pl.pacinho.adventofcode2021.challange.day17;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;

public class Day17Part2 implements CalculateI {

    private int targetXMin;
    private int targetXMax;
    private int targetYMin;
    private int targetYMax;

    private int hitCount;

    public static void main(String[] args) {
        System.out.println(new Day17Part2().calculate("day17\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        String info = FileUtils.readAsText(new File(filePath));
        targetXMin = Integer.parseInt(info.split("x=")[1].split("\\.\\.")[0]);
        targetXMax = Integer.parseInt(info.split("\\.\\.")[1].split(", y=")[0]);
        targetYMin = Integer.parseInt(info.split("y=")[1].split("\\.\\.")[0]);
        targetYMax = Integer.parseInt(info.split("y=.*\\.\\.")[1]);

        for (int col = 1; col <= targetXMax; col++) {
            for (int row = targetYMin; row <= Math.abs(targetYMin); row++) {
                checkTrajectory(row, col, row, col);
            }
        }
        return hitCount;
    }

    private Integer checkTrajectory(int row, int col, int prevRow, int prevCol) {
        if (checkArea(row, col)) {
            hitCount++;
            return null;
        }

        if (checkOutOfArea(row, col)) return null;

        int rowChange = prevRow - 1;
        int colChange = prevCol == 0 ? 0 : (prevCol < 0 ? prevCol + 1 : prevCol - 1);
        return checkTrajectory((row + rowChange), (col + colChange), rowChange, colChange);
    }

    private boolean checkArea(int row, int col) {
        return (row >= targetYMin && row <= targetYMax
                && col >= targetXMin && col <= targetXMax);
    }

    private boolean checkOutOfArea(int row, int col) {
        if (col > targetXMax) return true;
        return row < targetYMin;
    }
}