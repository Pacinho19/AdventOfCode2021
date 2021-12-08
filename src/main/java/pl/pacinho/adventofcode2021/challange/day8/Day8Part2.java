package pl.pacinho.adventofcode2021.challange.day8;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Day8Part2 implements CalculateI {

    public static void main(String[] args) {
        System.out.println(new Day8Part2().calculate("day8\\input.txt"));
    }

    private static String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }


    @Override
    public long calculate(String filePath) {
        List<String> collect = FileUtils.readTxt(new File(filePath));

        long summ = 0L;
        for (String line : collect) {
            HashMap<String, Integer> valuesMap = createValuesMap(line);
            summ += Long.parseLong(Arrays.stream(line.split("\\|")[1].split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(s1 -> String.valueOf(valuesMap.get(sortString(s1.trim()))))
                    .collect(Collectors.joining("")));
        }
        return summ;
    }

    private HashMap<String, Integer> createValuesMap(String line) {
        HashMap<String, Integer> valuesMap = new HashMap<>();
        List<String> values = Arrays.asList(line.split("\\|")[0].trim().split(" ")).stream().map(s -> sortString(s)).collect(Collectors.toList());
        valuesMap.put(values.stream().filter(s -> s.length() == 2).findFirst().get(), 1);

        valuesMap.put(sortString(values.stream().filter(s -> s.length() == 2).findFirst().get()), 1);
        valuesMap.put(sortString(values.stream().filter(s -> s.length() == 3).findFirst().get()), 7);
        valuesMap.put(sortString(values.stream().filter(s -> s.length() == 4).findFirst().get()), 4);
        valuesMap.put(sortString(values.stream().filter(s -> s.length() == 7).findFirst().get()), 8);

        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s)) && s.length() == 6 && !allMatch(valuesMap, 7, s)).findFirst().get()), 6);
        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s)) && s.length() == 5 && allMatch(valuesMap, 1, s)).findFirst().get()), 3);
        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s)) && s.length() == 5 && findUnique(s, fromValue(valuesMap, 6)).size() == 1).findFirst().get()), 5);
        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s)) && s.length() == 5).findFirst().get()), 2);
        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s)) && s.length() == 6 && allMatch(valuesMap, 3, s)).findFirst().get()), 9);
        valuesMap.put(sortString(values.stream().filter(s -> !valuesMap.containsKey((s))).findFirst().get()), 0);

        return valuesMap;
    }

    private boolean allMatch(HashMap<String, Integer> valuesMap, int toCheck, String number) {
        String toCheckS = fromValue(valuesMap, toCheck);
        List<Character> numList = convertToList(number);
        return convertToList(toCheckS)
                .stream()
                .allMatch(ch -> numList.contains(ch));
    }

    private String fromValue(HashMap<String, Integer> valuesMap, int toCheck) {
        String toCheckS = valuesMap.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), toCheck)).findFirst().map(Map.Entry::getKey).orElse(null);
        return toCheckS;
    }

    private static List<Character> findUnique(String value1, String value2) {
        List<Character> all = new ArrayList<>();
        all.addAll(convertToList(value1));
        all.addAll(convertToList(value2));

        return all.stream()
                .collect(Collectors.groupingBy(Character::charValue))
                .values()
                .stream()
                .filter(l -> l.size() == 1).map(l -> l.get(0))
                .collect(Collectors.toList());
    }

    private static List<Character> convertToList(String value) {
        return value.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

}