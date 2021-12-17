package pl.pacinho.adventofcode2021.challange.day16;

import pl.pacinho.adventofcode2021.challange.CalculateI;
import pl.pacinho.adventofcode2021.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day16Part2 implements CalculateI {

    private static HashMap<String, String> bitMap = new HashMap<String, String>() {
        {
            put("0", "0000");
            put("1", "0001");
            put("2", "0010");
            put("3", "0011");
            put("4", "0100");
            put("5", "0101");
            put("6", "0110");
            put("7", "0111");
            put("8", "1000");
            put("9", "1001");
            put("A", "1010");
            put("B", "1011");
            put("C", "1100");
            put("D", "1101");
            put("E", "1110");
            put("F", "1111");
        }
    };

    private String bits;
    private Packet main;

    //    320250684237 - to Low!
    //    9395190683173 - to High!
    public static void main(String[] args) {
        System.out.println(new Day16Part2().calculate("day16\\input.txt"));
    }

    @Override
    public long calculate(String filePath) {
        bits = Arrays.stream(FileUtils.readAsText(new File(filePath))
                        .split(""))
                .map(bitMap::get)
                .collect(Collectors.joining(""));

        checkBits();
        return transmissions(main);
    }

    private long transmissions(Packet checkBits) {
        switch (checkBits.getId()) {
            case 0:
                return checkBits.getSubPacket()
                        .stream()
                        .map(this::transmissions)
                        .reduce(0L, Long::sum);
            case 1:
                return checkBits.getSubPacket()
                        .stream()
                        .map(this::transmissions)
                        .reduce(1L, (a, b) -> a * b);
            case 2:
                return checkBits.getSubPacket()
                        .stream()
                        .map(this::transmissions)
                        .min(Long::compareTo)
                        .get();
            case 3:
                return checkBits.getSubPacket()
                        .stream()
                        .map(this::transmissions)
                        .max(Long::compareTo)
                        .get();
            case 4:
                return checkBits.getNumber();
            case 5:
                return transmissions(checkBits.getSubPacket().get(0)) > transmissions(checkBits.getSubPacket().get(1)) ? 1 : 0;
            case 6:
                return transmissions(checkBits.getSubPacket().get(0)) < transmissions(checkBits.getSubPacket().get(1)) ? 1 : 0;
            case 7:
                return transmissions(checkBits.getSubPacket().get(0)) == transmissions(checkBits.getSubPacket().get(1)) ? 1 : 0;
        }
        return 0;
    }

    private Packet checkBits() {
        if (bits.length() < 11) return main;

        Long version = getDecimal(bits.substring(0, 3));
        Long typeId = getDecimal(bits.substring(3, 6));

        Packet p = new Packet();
        if (main == null) {
            main = new Packet();
            p = main;
        }
        p.setId(typeId.intValue());
        p.setVersion(version.intValue());

        bits = bits.substring(6);
        if (typeId == 4) return checkTypeId4(p);
        else return checkTypeOtherId(p);
    }

    private Packet checkTypeOtherId(Packet packet) {
        Long digitAtPos = getDigitAtPos(bits, 0);
        if (digitAtPos == 1) {
            Long count = getDecimal(bits.substring(1, 12));
            bits = bits.substring(12);
            for (int i = 0; i < count; i++) {
                Packet packet1 = checkBits();
                if (packet1 != null) packet.addSubPacket(packet1);
            }
        } else {
            Long length = getDecimal(bits.substring(1, 16));
            bits = bits.substring(16);
            String bits2 = bits;
            while (bits2.length() - bits.length() < length) {
                packet.addSubPacket(checkBits());
            }
        }
        return packet;
    }

    private Packet checkTypeId4(Packet packet) {
        boolean last = false;
        int pos = 0;
        StringBuilder number = new StringBuilder();
        while (!last) {
            Long digitAtPos = getDigitAtPos(bits, pos);
            number.append(bits, pos + 1, pos + 5);
            pos += 5;
            last = digitAtPos == 0;
        }
        packet.setNumber(getDecimal(number.toString()));
        bits = bits.substring(pos);
        return packet;
    }

    private Long getDecimal(String binary) {
        Long sum = 0L;
        int pos = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            sum += getDigitAtPos(binary, i) * (long) Math.pow(2, pos);
            pos++;
        }
        return sum;
    }

    private Long getDigitAtPos(String bits, int pos) {
        return Long.parseLong(String.valueOf(bits.charAt(pos)));
    }

}