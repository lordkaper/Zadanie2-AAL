package AAL;

public class Pair {

    Integer first;
    Integer second;

    Pair(Integer first, Integer second){
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "AAL.Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

}
