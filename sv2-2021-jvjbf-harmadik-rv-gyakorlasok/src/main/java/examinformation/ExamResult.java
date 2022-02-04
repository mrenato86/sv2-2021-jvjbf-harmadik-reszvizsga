package examinformation;

public class ExamResult {

    private static final int PASS_PERCENT = 51;

    private final int theory;
    private final int practice;

    public ExamResult(int theory, int practice) {
        this.theory = theory;
        this.practice = practice;
    }

    public int getPractice() {
        return practice;
    }

    public int getTheory() {
        return theory;
    }

    public int getSumOfPoints() {
        return theory + practice;
    }

    public boolean isTheoryPassed(int maxPoint) {
        return theory >= maxPoint * PASS_PERCENT / 100;
    }

    public boolean isPracticePassed(int maxPoint) {
        return practice >= maxPoint * PASS_PERCENT / 100;
    }
}
