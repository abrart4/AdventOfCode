import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;



public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
    }

    private static void part1() throws FileNotFoundException {
        Scanner input = new Scanner(new File("my day 7 input.txt"));
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            lines.add(line);
        }
        int height = lines.size();
        int width = lines.getFirst().length();
        String[][] yx = new String[height][width];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] characters = line.split("");
            for (int j = 0; j < characters.length; j++) {
                yx[i][j] = lines.get(i).substring(j, j + 1);
            }
        }
        // too inconvenient to do proper logic
        CopyOnWriteArrayList<Beam> beams = new CopyOnWriteArrayList<>();
        int firstBeamX = -1;
        for (int i = 0; i < yx[0].length; i++) {
            String character = yx[0][i];
            if (character.equals("S")) {
                firstBeamX = i;
            }
        }
        beams.add(new Beam(0, firstBeamX));

        int relevantSplits = 0;
        for (int y = 1; y < height; y++) {
            ArrayList<Integer> occupiedX = new ArrayList<>();
            String[] characters = yx[y];
            for (int x = 0; x < width; x++) {
                for (Beam beam : beams) {
                    int bx = beam.getX();
                    if (bx == x) {
                        beam.down();
                        if (characters[x].equals("^")) {
                            beams.remove(beam);
                            occupiedX.remove(Integer.valueOf(x));
                            int leftX = x - 1;
                            int rightX = x + 1;
                            boolean anySplitIsRelevant = false;
                            if (!occupiedX.contains(leftX)) {
                                beams.add(new Beam(y, leftX));
                                occupiedX.add(leftX);
                                anySplitIsRelevant = true;
                            }
                            if (!occupiedX.contains(rightX)) {
                                beams.add(new Beam(y, rightX));
                                occupiedX.add(rightX);
                                anySplitIsRelevant = true;
                            }
                            if (anySplitIsRelevant) {
                                relevantSplits++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(relevantSplits);
    }

    private static void part2() throws FileNotFoundException {

    }

    private static class Beam {
        // private is supposed to make the variables actually private but we can access it for some reason
        // so i make the variables bad so i cant easily directly access them
        // (0, 0) is top left
        private int why;
        private int ecks;

        public Beam(int y, int x) {
            this.why = y;
            this.ecks = x;
        }

        private void down() {
            this.why++;
        }

        public int getY() {
            return why;
        }

        public int getX() {
            return ecks;
        }
    }
}
