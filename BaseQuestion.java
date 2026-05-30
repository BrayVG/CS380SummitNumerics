import java.util.Random;

/**
 * It will create random math questions.
 * It randomly selects one operation( addition, subtraction, multiplication
 * ,or division) and stores the generated problem.
 * It can also check the user's answer and return the question text.
 */
class BaseQuestion implements Question {

    private MathProblem problem;

    /**
     * Randomly generate one question,it could be addition,
     * subtraction, multiplication, or division.
     */

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

    /**
     * Checks if the user's answer is correct.
     * If the input is not a number, it returns false.
     *
     * @param userInput the answer entered by the user
     * @return true if the answer is correct, otherwise false
     */

    public boolean answerCheck(String userInput) {
        try {
            int userAnswer = Integer.parseInt(userInput.trim());
            return userAnswer == problem.getCorrectAnswer();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the question text from the current math problem.
     *
     * @return the question text
     */

    public String getQuestionText() {
        return problem.getQuestionText();
    }
}