package pl.pacinho.adventofcode2021.challange.day12;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12Part2 implements CalculateI {

    private Set<String> outPaths = new HashSet<>();
    private List<Path> paths;

    public static void main(String[] args) {
        System.out.println(new Day12Part2().calculate("day12\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        paths = FileUtils.readTxt(new File(filePath))
                .stream()
                .map(s -> new Path(s.split("-")[0], s.split("-")[1]))
                .collect(Collectors.toList());

        List<Path> reversePath = paths.stream()
                .filter(p -> !p.getStart().equals("start") && !p.getEnd().equals("end"))
                .collect(Collectors.toList());
        reversePath.forEach(p -> paths.add(new Path(p.getEnd(), p.getStart())));

        List<Path> startPaths = paths.stream()
                .filter(p -> p.getStart().equals("start"))
                .collect(Collectors.toList());

        startPaths.forEach(p -> analyzePath(p, ""));

        return outPaths.size();
    }

    private void analyzePath(Path path, String pathS) {
        pathS += path.toString();
        if (path.getEnd().equals("end")) {
//            System.out.println(pathS);
            outPaths.add(pathS);
            return;
        }

        List<String> lowerCaseLetters = getLowerCase(pathS);

        int maxLowerCaseCount = lowerCaseLetters.stream()
                .collect(Collectors.groupingBy(String::toString))
                .values().stream().filter(l -> l.size() == 2)
                .count() == 0 ? 2 : 1;

        List<Path> collect = paths.stream()
                .filter(p ->
                        !p.getStart().equals("start")
                                && lowerCaseLetters.stream().filter(s -> s.equals(p.getEnd())).count() < maxLowerCaseCount
                                && p.getStart().equals(path.getEnd()))
                .collect(Collectors.toList());

        for (Path p : collect) {
            analyzePath(p, pathS);
        }
    }

    private List<String> getLowerCase(String pathS) {
        return Arrays.stream(pathS.split("-"))
                .filter(s -> !s.isEmpty() && Character.isLowerCase(s.charAt(0)))
                .collect(Collectors.toList());
    }

}