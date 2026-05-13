interface Question {
    void generateQuestion();

    boolean answerCheck(String userInput);

    String getQuestionText();
}