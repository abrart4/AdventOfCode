import java.io.File;
import java.util.*;



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
                for (int i = 0; i < rangeStrings.size(); i++) {
                    String rangeString = rangeStrings.get(i);
                    int indexOfDash = rangeString.indexOf("-");
                    String lowerBoundString = rangeString.substring(0, indexOfDash);
                    String upperBoundString = rangeString.substring(indexOfDash + 1);
                    long lowerBound = Long.parseLong(lowerBoundString);
                    long upperBound = Long.parseLong(upperBoundString);
                    if (lowerBound <= value && value <= upperBound && !alreadyFreshValues.contains(value)) {
                        freshAmount++;
                        alreadyFreshValues.add(value);
                    }
                }
            }
        }
        System.out.println("Day 5 part 1: " + freshAmount);
    }

    private static void part2() throws Exception {
        Scanner input = new Scanner(new File("my day 5 input.txt"));
        ArrayList<long[]> distinctRanges = new ArrayList<>();
        while (input.hasNextLine()) {
            String nextLine = input.nextLine();
            int indexOfDash = nextLine.indexOf("-");
            if (indexOfDash != -1) {
                String lowerBoundString = nextLine.substring(0, indexOfDash);
                String upperBoundString = nextLine.substring(indexOfDash + 1);
                long lowerBound = Long.parseLong(lowerBoundString);
                long upperBound = Long.parseLong(upperBoundString);
                distinctRanges.add(new long[] {lowerBound, upperBound});
            }
        }
        if (false) {
            Set<Long> lk = new HashSet<>();
            for (long[] dr : distinctRanges) {
                for (long h = dr[0]; h <= dr[1]; h++) {
                    lk.add(h);
                }
            }
            System.out.println(lk.size());
        }
        else {


            String x = "";
            x += "(";
            for (long[] range : distinctRanges) {
                x += range[0];
                x += "-";
                x += range[1];
                x += ", ";
            }
            x += ")";
            System.out.println(x);
            long acceptableValues = 0;
            for (int i = 0; i < distinctRanges.size(); i++) {
                long[] distinctRange = distinctRanges.get(i);
                long lower = distinctRange[0];
                long upper = distinctRange[1];
                // if 5 to 10 then that's 5 6 7 8 9 10 or 6 values
                // (upper - lower) + 1
                acceptableValues += Math.abs(upper - lower) + 1;

            }
            System.out.println("Day 5 part 2: " + acceptableValues);
        }
    }


    private static boolean eq(ArrayList<long[]> first, ArrayList<long[]> second) {
        int firstSize = first.size();
        int secondSize = second.size();
        if (firstSize != secondSize) {
            return false;
        }
        boolean isAllTheSame = true;
        for (int i = 0; i < firstSize; i++) {
            if (!Arrays.equals(first.get(i), second.get(i))) {
                isAllTheSame = false;
            }
        }
        return isAllTheSame;
    }
}
