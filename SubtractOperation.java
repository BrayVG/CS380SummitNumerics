import java.util.Random;

/**
 * It will create subtraction math problems.
 * Randomly generates two numbers from 1 to 20 and returns.
 * If number a is bigger than b, question will be a - b, else
 * it will be b - a.
 */
class SubtractOperation {
    /**
     * Creates one subtraction problem.
     *
     * @return a MathProblem object containing the subtraction question and answer
     */
    public MathProblem createProblem() {
        Random random = new Random();
        int a = random.nextInt(20) + 1;
        int b = random.nextInt(20) + 1;

        if (a > b) {
            return new MathProblem(a + " - " + b, a - b);
        } else {
            return new MathProblem(b + " - " + a, b - a);
        }
    }
}