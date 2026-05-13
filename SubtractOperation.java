import java.util.Random;

class SubtractOperation {
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