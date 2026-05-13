import java.util.Random;

class DivideOperation {
    public MathProblem createProblem() {
    	Random random = new Random();
    	int a;
    	int b;
    	
    	do {
    		a = random.nextInt(100) + 1;
    		b = random.nextInt(10) + 1;    		
    	} while (a < b || a % b > 0);
    	
    	return new MathProblem(a + " ÷ " + b, a / b);
    }
}