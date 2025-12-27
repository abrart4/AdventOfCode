import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    private static void part1() throws FileNotFoundException {
        Scanner input = new Scanner(new File("my day 6 input.txt"));
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }
        int amountOfLines = lines.size();
        ArrayList<ArrayList<Integer>> eachLine = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();
        int shortestLength = Integer.MAX_VALUE;
        for (int o = 0; o < amountOfLines; o ++) {
            String line = lines.get(o);
            String[] all = line.split(" ");
            if (o + 1 != amountOfLines) {
                ArrayList<Integer> asNumbers = new ArrayList<>();
                for (String fufu : all) {
                    if (!fufu.equals("")) {
                        int asNumber = Integer.parseInt(fufu);
                        asNumbers.add(asNumber);
                    }
                }
                eachLine.add(asNumbers);
                shortestLength = Math.min(shortestLength, asNumbers.size());
            }
            else {
                for (String kareem: all) {
                    if (!kareem.equals("")) {
                        operators.add(kareem);
                    }
                }
            }
        }
        long total = 0;
        for (int i = 0; i < shortestLength; i ++) {
            // get 0 of everything
            ArrayList<Integer> operands = new ArrayList<>();
            for (ArrayList<Integer> line: eachLine) {
                int firstNumber = line.get(i);
                operands.add(firstNumber);
            }
            String operator = operators.get(i);
            boolean add = operator.equals("+");

            long accumulator = operands.get(0);
            for (int evan = 1; evan < operands.size(); evan ++) {
                int operand = operands.get(evan);
                if (add) {
                    accumulator += operand;
                }
                else {
                    accumulator *= operand;
                }
            }
            System.out.println("Operands " + operands + " and operator " + operator + " goes to " + accumulator);
            total += accumulator;
        }
        System.out.println(total);
    }

    private static void part2() throws FileNotFoundException {
        Scanner input = new Scanner(new File("my day 6 input.txt"));
        // this does say right to left, but left to right also works as it's just addition and multiplication
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }
        ArrayList<Integer> emptyColumnIndices = new ArrayList<>();
        int maxLength = -1;
        for (int i = 0; i < lines.size(); i ++) {
            maxLength = Math.max(maxLength, lines.get(i).length());
        }
        for (int i = 0; i < maxLength; i ++) {
            boolean emptyColumn = true;
            // check index 3 to see if lines.get(3) is empty
            for (String line: lines) {
                if (i >= line.length()) continue;
                if (line.charAt(i) != ' ') {
                    emptyColumn = false;
                }
            }
            if (emptyColumn) {
                emptyColumnIndices.add(i);
            }
        }
        emptyColumnIndices.add(lines.get(0).length());
        ArrayList<int[]> rangesOfGoodIndices = new ArrayList<>();
        rangesOfGoodIndices.add(new int[] {0, emptyColumnIndices.getFirst() - 1});
        for (int i = 0; i < emptyColumnIndices.size(); i ++) {
            int emptyColumnIndex = emptyColumnIndices.get(i);
            if (i + 1 != emptyColumnIndices.size()) {
                int next = emptyColumnIndices.get(i + 1);
                rangesOfGoodIndices.add(new int[] {emptyColumnIndex + 1, next - 1});
            }
        }
        long total = 0;
        for (int[] r: rangesOfGoodIndices) {
            int first = r[0];
            int last = r[1];
            boolean add = lines.getLast().charAt(first) == '+';
            String[] operands = new String[(last - first) + 1];
            for (int i = first; i <= last; i ++) {
                operands[i - first] = "";
                for (int j = 0; j < lines.size(); j ++) {
                    if (j + 1 == lines.size()) continue;
                    String line = lines.get(j);
                    operands[i - first] += line.charAt(i);
                }
            }
            int[] operandsAsNumbers = new int[operands.length];
            for (int i = 0; i < operands.length; i ++) {
                operandsAsNumbers[i] = Integer.parseInt(operands[i].trim());
            }
            long accumulator = operandsAsNumbers[0];
            for (int i = 1; i < operandsAsNumbers.length; i ++) {
                int operand = operandsAsNumbers[i];
                if (add) {
                    accumulator += operand;
                }
                else {
                    accumulator *= operand;
                }
            }
            total += accumulator;
        }
        System.out.println(total);
    }
}
