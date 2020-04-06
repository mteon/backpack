import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class SolverDynamic extends Solver {

    private int[][] table;
    private int[] values;
    private int[] weights;
    private int number;

    public SolverDynamic(String filename) {
        super(filename);
        this.valuesTable = constructValueTable();
        this.weightTable = constructWeightTable();
        this.objectNumber = objectArrayList.size();
        this.dynamicTable = computeSubProblems(valuesTable, weightTable, objectNumber, bagCapacity);
    }

    public ArrayList<ObjectIntoBP> solve() {
        bestSolution = constructSolution(objectNumber-1, bagCapacity, new ArrayList<>());
        return bestSolution;
    }

    public int[] constructValueTable() {
        return constructTable(ObjectIntoBP::getValue);
    }

    public int[] constructWeightTable() {
        return constructTable(ObjectIntoBP::getWeight);
    }

    public int[] constructTable(Function<ObjectIntoBP, Integer> function) {
        int[] table = new int[objectArrayList.size()];
        for (int i = 0; i < objectArrayList.size(); i++) {
            MyObject o = objectArrayList.get(i);
            table[i] = function.apply(o);
        }
        return t;
    }

    public int[][] computeSubProblems(int[] values, int[] P, int number, int max) {
        int [][] A = new int[n][max+1];
        for (int p = 0; p <= max; p++) {
            if (P[0] > p) {
                A[0][p] = 0;
            }
            else {
                A[0][p] = values[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int p = 0; p <= Pmax; p++) {
                if (P[i] > p) {
                    A[i][p] = A[i - 1][p];
                }
                else {
                    A[i][p] = Math.max(A[i - 1][p], A[i - 1][p - P[i]] + values[i]);
                }
            }
        }
        return A;
    }

    private ArrayList<ObjectIntoBP> constructSolution(int i, int p, ArrayList<ObjectIntoBP> currentConfig) {
        if (i == 0) {
            if (weightTable[i] <= p) {
                currentConfig.add(objectArrayList.get(i));
            }
            return currentConfig;
        }
        if (dynamicTable[i - 1][p] == dynamicTable[i][p]) return constructSolution(i - 1,  p, currentConfig);
        currentConfig.add(objectArrayList.get(i));
        return constructSolution(i - 1 , p - objectArrayList.get(i).getWeight(), currentConfig);
    }
}