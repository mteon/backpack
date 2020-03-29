public class Main {
    public static void main(String[] args) {
        String filename = "./files/sac1.txt";
        solveWithBackTrack(filename);
    }

    public static void solveWithBackTrack(String filename) {
        BackTrackingSolver backTrackingSolver = new BackTrackingSolver(filename);
        System.out.println(backTrackingSolver.solve());
        System.out.println(backTrackingSolver.getWeightOfObjects(backTrackingSolver.getBetterSolution()));
        System.out.println(backTrackingSolver.getValueOfObjects(backTrackingSolver.getBetterSolution()));
    }

}
