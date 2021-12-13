package pl.pacinho.adventofcode2021.challange.day13;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day13Part1 implements CalculateI {

    private Character[][] board;

    public static void main(String[] args) {
        System.out.println(new Day13Part1().calculate("day13\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        List<String> lines = FileUtils.readTxt(new File(filePath));

        String foldInstruction = lines.stream().filter(s -> s.startsWith("fold")).findFirst().get();

        List<int[]> dotCords = lines.stream()
                .filter(s -> !s.startsWith("fold") && !s.isEmpty())
                .map(s -> s.split(","))
                .map(arr -> Stream.of(arr).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList());

        board = new Character[getMaxAxis(dotCords, 1)][getMaxAxis(dotCords, 0)];
        fillEmptyArray(board);

        //placement dots on board
        dotCords.forEach(arr -> board[arr[1]][arr[0]] = '#');

        board = fold(foldInstruction);
        //printBoard();

        return Arrays.stream(board)
                .flatMap(Stream::of)
                .filter(ch -> ch == '#')
                .count();
    }

    private Character[][] fold(String foldInstruction) {
        String[] split = foldInstruction.split("fold along ")[1].split("=");
        String axis = split[0];
        int value = Integer.parseInt(split[1]);

        if (axis.equals("y")) return foldHorizontal(value);
        else return foldVertical(value);
    }

    private Character[][] foldVertical(int value) {
        Character[][] boardTemp = new Character[board.length][value];

        for (int row = 0; row < board.length; row++) {
            int backCol = 1;
            boolean backColB = false;
            for (int col = 0; col < board[row].length; col++) {
                if (col == value) continue;
                if (col < value)
                    boardTemp[row][col] = board[row][col];
                else {
                    if (!backColB) backColB = true;
                    if (board[row][col] == '#') boardTemp[row][boardTemp[row].length - backCol] = '#';
                }
                if (backColB) backCol++;
            }
        }
        return boardTemp;
    }

    private Character[][] foldHorizontal(int value) {
        Character[][] boardTemp = new Character[value][board[0].length];

        int backRow = 1;
        boolean backRowB = false;
        for (int row = 0; row < board.length; row++) {
            if (row == value) continue;
            for (int col = 0; col < board[row].length; col++) {
                if (row < value)
                    boardTemp[row][col] = board[row][col];
                else {
                    if (!backRowB) backRowB = true;
                    if (board[row][col] == '#') boardTemp[boardTemp.length - backRow][col] = '#';
                }
            }
            if (backRowB) backRow++;
        }
        return boardTemp;
    }

    private void fillEmptyArray(Character[][] board) {
        IntStream.range(0, board.length)
                .forEach(row ->
                        IntStream.range(0, board[row].length)
                                .forEach(col -> board[row][col] = '.'));
    }

    //idx 0 - X
    //idx 1 - Y
    private int getMaxAxis(List<int[]> list, int idx) {
        return list.stream()
                .map(arr -> arr[idx])
                .max(Integer::compareTo)
                .get()
                + 1; //add 0
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}