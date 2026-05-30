import java.util.Random;

/**
 * It will create multiplication math problems.
 * Randomly generates two numbers from 1 to 10 and returns.
 * a MathProblem object with the question text and correct answer.
 */
class MultipleOperation {
    /**
     * Creates one multiplication problem.
     *
     * @return a MathProblem object containing
     * the multiplication question and answer.
     */
    public MathProblem createProblem() {
        Random random = new Random();
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;

        return new MathProblem(a + " × " + b, a * b);
    }
}