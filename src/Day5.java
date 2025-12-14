import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class Day5 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static void part1() throws Exception {
        Scanner input = new Scanner(new File("my day 5 input.txt"));
        ArrayList<String> rangeStrings = new ArrayList<>();
        boolean foundBlankLine = false;
        int freshAmount = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) {
                foundBlankLine = true;
                continue;
            }
            if (!foundBlankLine) {
                rangeStrings.add(line);
            }
            else {
                long value = Long.parseLong(line);
                ArrayList<Long> alreadyFreshValues = new ArrayList<>();
                for (int i = 0; i < rangeStrings.size(); i ++) {
                    String rangeString = rangeStrings.get(i);
                    int indexOfDash = rangeString.indexOf("-");
                    String lowerBoundString = rangeString.substring(0, indexOfDash);
                    String upperBoundString = rangeString.substring(indexOfDash + 1);
                    long lowerBound = Long.parseLong(lowerBoundString);
                    long upperBound = Long.parseLong(upperBoundString);
                    if (lowerBound <= value && value <= upperBound && !alreadyFreshValues.contains(value)) {
                        freshAmount ++;
                        alreadyFreshValues.add(value);
                    }
                }
            }
        }
        System.out.println("Day 5 part 1: " + freshAmount);
    }

    private static void part2() throws Exception {
        Scanner input = new Scanner(new File("my day 5 input.txt"));
        // set
        Set<Long> acceptableValues = new HashSet<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("")) break;
            int indexOfDash = line.indexOf("-");
            String lowerBoundString = line.substring(0, indexOfDash);
            String upperBoundString = line.substring(indexOfDash + 1);
            long lowerBound = Long.parseLong(lowerBoundString);
            long upperBound = Long.parseLong(upperBoundString);
            for (long i = lowerBound; i <= upperBound; i ++) {
                acceptableValues.add(i);
            }
        }
        System.out.println("Day 5 part 2: " + acceptableValues.size());
    }
}
