import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
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
}
