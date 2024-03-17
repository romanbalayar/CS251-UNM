/**
 * Name: Roman Balayar
 * CS251L
 * Project 1
 * 01/28/2024
 */
public class FizzBuzz {
    /**
     * @param args
     * upplmt = upper limit
     * fizMul = Multiplication  gives fizz
     * buzMul = Multiplication that gives buzz
     */
    public static void main(String[] args) {
        // Checking if three command line arguments are provided
        if (args.length != 3) {
            System.out.println("Arguments not valid");
            return;
        }

        // Parse command line arguments
        int uppLmt = Integer.parseInt(args[0]);
        int fizMul = Integer.parseInt(args[1]);
        int buzMul = Integer.parseInt(args[2]);

        //logic and print the results
        for (int i = 1; i <= uppLmt; i++) {
            if (i % fizMul == 0 && i % buzMul == 0) {
                System.out.println("FizzBuzz");
            } else if (i % fizMul == 0) {
                System.out.println("Fizz");
            } else if (i % buzMul == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}

