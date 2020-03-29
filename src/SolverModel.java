import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SolverModel {
    protected int bagCapacity;
    protected ArrayList<ObjectIntoBP> listOfObjects;
    protected ArrayList<ObjectIntoBP> bestSolution;

    public SolverModel(String filename) {
        this.bestSolution = new ArrayList<>();
        this.listOfObjects = new ArrayList<>();
        readFile(filename);
    }

    public abstract ArrayList<ObjectIntoBP> solve();

    public int getWeightOfObjects(List<ObjectIntoBP> list) {
        int weight = 0;
        for (ObjectIntoBP o : list) {
            weight += o.getWeight();
        }
        return weight;
    }

    public int getValueOfObjects(List<ObjectIntoBP> list) {
        int value = 0;
        for (ObjectIntoBP o : list) {
            value += o.getValue();
        }
        return value;
    }

    public ArrayList<ObjectIntoBP> getBetterSolution() {
        return bestSolution;
    }

    public void readFile(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            this.bagCapacity = Integer.parseInt(line);
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                listOfObjects.add(new ObjectIntoBP(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
            }
            Collections.sort(listOfObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

