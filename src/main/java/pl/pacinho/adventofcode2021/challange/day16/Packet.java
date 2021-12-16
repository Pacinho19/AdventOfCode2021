package pl.pacinho.adventofcode2021.challange.day16;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Packet {

    private int version;
    private int id;
    private List<Packet> subPacket;
    private long number;

    public Packet() {
        subPacket = new ArrayList<>();
    }

    public void addSubPacket(Packet packet) {
        subPacket.add(packet);
    }
}