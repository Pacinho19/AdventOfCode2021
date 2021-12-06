package pl.pacinho.adventofcode2021.challange.day6;

import lombok.AllArgsConstructor;
import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Day6Part2 implements CalculateI {

    private int daysLeft;

    public static void main(String[] args) {
        System.out.println(new Day6Part2(256).calculate("day6\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        Map<Integer, Long> states = Arrays.stream(FileUtils.readAsText(new File(filePath))
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Integer::intValue,
                        Collectors.counting()));

        while (daysLeft > 0) {
            Map<Integer, Long> newStates = new HashMap<>(states);
            List<Integer> sortedList = new ArrayList<>(states.keySet());
            Collections.sort(sortedList);
            for (Integer state : sortedList) {
                Integer newState = state - 1;
                addState(state, newState, newStates);
            }
            addState(-1, 6, newStates);
            addState(-1, 8, newStates);
            newStates.remove(new Integer(-1));
            states = new HashMap<>(newStates);
            daysLeft--;
        }

        return states
                .values()
                .stream().reduce(0L, Long::sum);
    }

    private void addState(int state, int newState, Map<Integer, Long> newStates) {
        Long stateCount = newStates.get(new Integer(state));
        if(stateCount== null && state==-1 && (newState==6 || newState==8)){
            return;
        }

        Long newStateCount = newStates.get(new Integer(newState));
        if (newStateCount == null) newStateCount = 0L;
        if (stateCount == null) stateCount = 0L;

        Long count = newStateCount + stateCount;
        newStates.put(newState, count== 0 ? 1L : count);

        if (newState != 8 && newState!=6)newStates.remove(new Integer(state));
        if(state==7 && newState==6) newStates.remove(new Integer(state));
    }

}