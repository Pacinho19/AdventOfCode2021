package pl.pacinho.adventofcode2021.challange.day5;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day5Part2().calculate("day5\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath));
        List<String> points = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" -> ");
            String a = parts[0];
            String b = parts[1];

            String[] splitA = a.split(",");
            String[] splitB = b.split(",");

            points.addAll(extendsPoints(splitA, splitB));
        }

        return (int) points.stream().collect(Collectors.groupingBy(String::toString))
                .values()
                .stream()
                .filter(l -> l.size() > 1)
                .count();
    }

    private List<String> extendsPoints(String[] splitA, String[] splitB) {
        List<String> out = new ArrayList<>();

        int aX = Integer.parseInt(splitA[0]);
        int aY = Integer.parseInt(splitA[1]);

        int bX = Integer.parseInt(splitB[0]);
        int bY = Integer.parseInt(splitB[1]);

        if (aX == bX) {
            int multiple = aY > bY ? -1 : 1;
            for (int i = 0; i <= (Math.abs(aY - bY)); i++) {
                out.add(aX + "," + (aY + (multiple * i)));
            }
        } else if (aY == bY) {
            int multiple = aX > bX ? -1 : 1;
            for (int i = 0; i <= (Math.abs(aX - bX)); i++) {
                out.add(aX + (multiple * i) + "," + aY);
            }
        } else if (aX < bX && aY < bY) {
            for (int i = 0; i <= (Math.abs(aX - bX)); i++) {
                out.add(aX + i + "," + (aY + i));
            }
        } else if (aX > bX && aY > bY) {
            for (int i = 0; i <= (Math.abs(aX - bX)); i++) {
                out.add(aX - i + "," + (aY - i));
            }
        } else if (aX < bX) {
            for (int i = 0; i <= (Math.abs(aX - bX)); i++) {
                out.add(aX + i + "," + (aY - i));
            }
        } else {
            for (int i = 0; i <= (Math.abs(aX - bX)); i++) {
                out.add(aX - i + "," + (aY + i));
            }
        }
        return out;
    }

}