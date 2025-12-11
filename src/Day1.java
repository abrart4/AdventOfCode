import java.io.File;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("my day 1 input.txt"));
        int current = 50;
        // this actually really got me
        // i inputted 42 about once or twice and was dumbfounded
        int amountOfZero = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String directionString = line.substring(0, 1);
            int direction = directionString.equals("L") ? -1 : 1;
            String numberString = line.substring(1);
            int number = Integer.parseInt(numberString);
            for (int i = 0; i < number; i ++) {
                if (direction == 1) {
                    current ++;
                }
                else {
                    current --;
                }
                if (current % 100 == 0) {
                    amountOfZero ++;

                }
            }
            if (current < 0) {
                current = 100 + (current % 100);
            }
            else if (current > 99) {
                current = current % 100;
            }
            current = current % 100;
        }
        System.out.println(amountOfZero);

    }
}
