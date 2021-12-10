package pl.pacinho.adventofcode2021.challange.day10;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Bracket {

    _ROUND_C(')', false, BracketType.ROUND),
    _ROUND_O('(', true, BracketType.ROUND),
    _DIAMOND_C('>', false, BracketType.DIAMOND),
    _DIAMOND_O('<', true, BracketType.DIAMOND),
    _SQUARE_C(']', false, BracketType.SQUARE),
    _SQUARE_O('[', true, BracketType.SQUARE),
    _BRACE_C('}', false, BracketType.BRACE),
    _BRACE_O('{', true, BracketType.BRACE);

    private final char sign;
    private final boolean open;
    private final BracketType bracketType;

    Bracket(char sign, boolean open, BracketType bracketType) {
        this.sign = sign;
        this.open = open;
        this.bracketType = bracketType;
    }

    public static Bracket findByChar(Character character) {
        return Arrays.stream(values())
                .filter(b -> b.getSign() == character)
                .findFirst()
                .orElse(null);
    }
}
