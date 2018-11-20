package AAL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithm {

    private List<Pair> relations;
    private Set<Integer> specialScouts;
    private int[] scouts;

    public Algorithm(int numberOfScouts, List<Pair> relations){
        this.relations = relations;
        this.scouts = new int[numberOfScouts+1];
        this.specialScouts = new HashSet<>();
    }

    public int findMinValOfScoutsNeeded(){
        int scoutsNotVisited = scouts.length;

        for (Pair relation : relations) {

            if(scouts[relation.first] == 0){
                --scoutsNotVisited;
                scouts[relation.first] = 1;
            }

            if(scouts[relation.second] == 0){
                --scoutsNotVisited;
                scouts[relation.second] = 1;
            }

            specialScouts.remove(relation.first);
            specialScouts.add(relation.second);

        }

        return scoutsNotVisited + specialScouts.size() - 1;
    }

}
