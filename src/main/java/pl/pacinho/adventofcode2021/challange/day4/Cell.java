package pl.pacinho.adventofcode2021.challange.day4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class Cell {

    private int value;
    private int col;
    private int row;
    private boolean selected;

    public Cell(int value, int col, int row) {
        this.value = value;
        this.col = col;
        this.row = row;
    }

    public void setSelected(){
        this.selected=true;
    }
}