import java.util.Random;

class BaseQuestion implements Question {
	private MathProblem problem;

	public void generateQuestion() {
		Random random = new Random();
		int randomNum = random.nextInt(4);

		switch (randomNum) {
		case 0:
			// addition
			AddOperation addOperation = new AddOperation();
			problem = addOperation.createProblem();
			break;
		case 1:
			// subtraction
			SubtractOperation subtractOperation = new SubtractOperation();
			problem = subtractOperation.createProblem();
			break;
		case 2:
			// multiplication
			MultipleOperation multipleOperation = new MultipleOperation();
			problem = multipleOperation.createProblem();
			break;
		case 3:
			// division
			DivideOperation divideOperation = new DivideOperation();
			problem = divideOperation.createProblem();
			break;
		}
	}

	public boolean answerCheck(String userInput) {
		try {
			int userAnswer = Integer.parseInt(userInput.trim());
			return userAnswer == problem.getCorrectAnswer();
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String getQuestionText() {
		return problem.getQuestionText();
	}
}