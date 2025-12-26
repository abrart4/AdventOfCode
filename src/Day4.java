import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Day4 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static void part1() throws Exception {
        // sorry for everything i've done
        ArrayList<String[]> everything = new ArrayList<>();
        Scanner input = new Scanner(new File("my day 4 input.txt"));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            everything.add(line.split(""));
        }
        int everythingSize = everything.size();
        int amountOfValid = 0;
        for (int i = 0; i < everythingSize; i ++) {
            String[] line = everything.get(i);
            int lineLength = line.length;
            for (int j = 0; j < lineLength; j ++) {
                String chr = line[j];
                if (chr.equals("@")) {
                    String[] allAdjacent = new String[8];
                    boolean thereIsStuffAbove = i != 0;
                    boolean thereIsStuffToTheLeft = j != 0;
                    boolean thereIsStuffToTheRight = j != lineLength - 1;
                    boolean thereIsStuffBelow = i < everythingSize - 1;
                    if (thereIsStuffAbove) {
                        String[] previousLine = everything.get(i - 1);
                        if (thereIsStuffToTheLeft) {
                            String northWest = previousLine[j - 1];
                            allAdjacent[0] = northWest;
                        }
                        String north = previousLine[j];
                        allAdjacent[1] = north;
                        if (thereIsStuffToTheRight) {
                            String northEast = previousLine[j + 1];
                            allAdjacent[2] = northEast;
                        }
                    }
                    if (thereIsStuffToTheLeft) {
                        String west = line[j - 1];
                        allAdjacent[3] = west;
                    }
                    if (thereIsStuffToTheRight) {
                        String east = line[j + 1];
                        allAdjacent[4] = east;
                    }
                    if (thereIsStuffBelow) {
                        String[] nextLine = everything.get(i + 1);
                        if (thereIsStuffToTheLeft) {
                            String southWest = nextLine[j - 1];
                            allAdjacent[5] = southWest;
                        }
                        String south = nextLine[j];
                        allAdjacent[6] = south;
                        if (thereIsStuffToTheRight) {
                            String southEast = nextLine[j + 1];
                            allAdjacent[7] = southEast;
                        }
                    }
                    int amountOfAdjacentRolls = 0;

                    for (int k = 0; k < 8; k ++) {
                        String adjacent = allAdjacent[k];
                        if ("@".equals(adjacent)) {
                            amountOfAdjacentRolls ++;
                        }
                    }
                    if (amountOfAdjacentRolls < 4) {
                        amountOfValid ++;
                    }
                }
            }
        }
        System.out.println("Day 4 part 1: " + amountOfValid);
    }

    private static void part2() throws Exception {
        // sorry for everything i've done
        ArrayList<String[]> everything = new ArrayList<>();
        Scanner input = new Scanner(new File("my day 4 input.txt"));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            everything.add(line.split(""));
        }
        int everythingSize = everything.size();
        int amountOfValid = 0;
        int amountOfValidThisTime = 1; // not actually true but do it to enter the while loop
        while (amountOfValidThisTime > 0) {
            amountOfValidThisTime = 0;

            for (int KevinZhang = 0; KevinZhang < everythingSize; KevinZhang++) {
                String[] line = everything.get(KevinZhang);
                int lineLength = line.length;
                for (int j = 0; j < lineLength; j++) {
                    String chr = line[j];
                    if (chr.equals("@")) {
                        String[] allAdjacent = new String[8];
                        boolean thereIsStuffAbove = KevinZhang != 0;
                        boolean thereIsStuffToTheLeft = j != 0;
                        boolean thereIsStuffToTheRight = j != lineLength - 1;
                        boolean thereIsStuffBelow = KevinZhang < everythingSize - 1;
                        if (thereIsStuffAbove) {
                            String[] previousLine = everything.get(KevinZhang - 1);
                            if (thereIsStuffToTheLeft) {
                                String northWest = previousLine[j - 1];
                                allAdjacent[0] = northWest;
                            }
                            String north = previousLine[j];
                            allAdjacent[1] = north;
                            if (thereIsStuffToTheRight) {
                                String northEast = previousLine[j + 1];
                                allAdjacent[2] = northEast;
                            }
                        }
                        if (thereIsStuffToTheLeft) {
                            String west = line[j - 1];
                            allAdjacent[3] = west;
                        }
                        if (thereIsStuffToTheRight) {
                            String east = line[j + 1];
                            allAdjacent[4] = east;
                        }
                        if (thereIsStuffBelow) {
                            String[] nextLine = everything.get(KevinZhang + 1);
                            if (thereIsStuffToTheLeft) {
                                String southWest = nextLine[j - 1];
                                allAdjacent[5] = southWest;
                            }
                            String south = nextLine[j];
                            allAdjacent[6] = south;
                            if (thereIsStuffToTheRight) {
                                String southEast = nextLine[j + 1];
                                allAdjacent[7] = southEast;
                            }
                        }
                        int amountOfAdjacentRolls = 0;

                        for (int k = 0; k < 8; k++) {
                            String adjacent = allAdjacent[k];
                            if ("@".equals(adjacent)) {
                                amountOfAdjacentRolls++;
                            }
                        }
                        if (amountOfAdjacentRolls < 4) {
                            amountOfValid ++;
                            amountOfValidThisTime ++;
                            everything.get(KevinZhang)[j] = "x";
                        }
                    }
                }
            }
        }
        System.out.println("Day 4 part 2: " + amountOfValid);

    }
}
