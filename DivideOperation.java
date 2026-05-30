import java.util.Random;

/**
 * It will create division math problems.
 * Randomly generates two numbers and make sure the first number
 * can be divided by the second number with no remainder.
 */
class DivideOperation {
    /**
     * Creates one division problem.
     * The method keeps generating numbers
     * until the division result is a whole number.
     *
     * @return a MathProblem object containing the division question and answer
     */

    public MathProblem createProblem() {
        Random random = new Random();
        int a;
        int b;
        // Keep generating numbers until a is greater than or equal to b
        // and a can be divided by b evenly.
        do {
            a = random.nextInt(100) + 1;
            b = random.nextInt(10) + 1;
        } while (a < b || a % b > 0);

        return new MathProblem(a + " ÷ " + b, a / b);
    }
}