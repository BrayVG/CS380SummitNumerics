import java.util.Random;

class MultipleOperation {
    public MathProblem createProblem() {
    	Random random = new Random();
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;

        return new MathProblem(a + " × " + b, a * b);
    }
}