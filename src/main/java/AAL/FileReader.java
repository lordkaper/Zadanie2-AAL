package AAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private int firstNumber;
    private List<Pair> pairs;
    private int expectedResult;
    private Scanner input;


    public FileReader(String filename) throws FileFormatException{
        File file = new File(filename);
        try {
            this.input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.pairs = new LinkedList<>();
        this.firstNumber = -1;
        this.expectedResult = -1;

        getData();
    }

    private void getData() throws FileFormatException {
        int counter = 0;
        while(input.hasNext()){
            if(counter == 0){
                firstNumber = Integer.valueOf(input.nextLine());
                counter++;
            } else {
                String[]ints = input.nextLine().split(" ");
                if(ints.length == 1 && input.hasNext()){
                    throw new FileFormatException("Zle sformatowane dane - za malo danych w linii!");
                } else if (ints.length == 1){
                    expectedResult = Integer.valueOf(ints[0]);
                } else if(ints.length == 2){
                    pairs.add(new Pair(Integer.valueOf(ints[0]), Integer.valueOf(ints[1])));
                } else {
                    throw new FileFormatException("Zle sformatowane dane - pusta linia lub za duzo danych w linii!");
                }
            }
        }
        if(counter == 0){
            throw new FileFormatException("Pusty plik!");
        }

    }

    public void close(){
        input.close();
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    int getExpectedResult() {
        return expectedResult;
    }

}
