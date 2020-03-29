import java.util.ArrayList;
import java.util.List;

public class BackTrackingSolver extends SolverModel {
    @Override
    public ArrayList<ObjectIntoBP> solve() {
        backtrack(new ArrayList<>());
        return bestSolution;
    }

    public BackTrackingSolver(String filename) {
        super(filename);
    }
    public void backtrack(List<ObjectIntoBP> list) {
//        System.out.println(a);
        if (isSolution(list)) {
            processSolution(list);
        }
        ArrayList<ObjectIntoBP> candidates = constructCandidates(list, listOfObjects);
        for (ObjectIntoBP candidate : candidates) {
            if (getWeightOfObjects(list) + candidate.getWeight() <= bagCapacity) {
                list.add(list.size(), candidate);
                backtrack(list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isSolution(List<ObjectIntoBP> list) {
        return getWeightOfObjects(list) <= bagCapacity;
    }

    private void processSolution(List<ObjectIntoBP> a) {
        if (getValueOfObjects(a) > getValueOfObjects(this.bestSolution)) {
            this.bestSolution.clear();
            this.bestSolution.addAll(a);
        }
    }

    public ArrayList<ObjectIntoBP> constructCandidates(List<ObjectIntoBP> parameterList, ArrayList<ObjectIntoBP> input) {
        ArrayList<ObjectIntoBP> list = new ArrayList<>();
        int beginning = 0;
        if (!parameterList.isEmpty())
            beginning = input.indexOf(parameterList.get(parameterList.size() - 1)) + 1;
        for (int i = beginning; i < input.size(); i++) {
            list.add(input.get(i));
        }
        return list;
    }
}