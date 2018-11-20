package AAL;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Tests {

    private FileReader fileReader;
    private Path path;
    private Algorithm algorithm;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initialize(){
        path = Paths.get("tests");
    }

    @Test
    public void withoutRelations() {
        try {
            fileReader = new FileReader(path + "/test1.txt");
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
        algorithm = new Algorithm(fileReader.getFirstNumber(), fileReader.getPairs());
        assertEquals(fileReader.getExpectedResult(), algorithm.findMinValOfScoutsNeeded());
    }

    @Test
    public void cycle() {
        try {
            fileReader = new FileReader(path + "/test2.txt");
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
        algorithm = new Algorithm(fileReader.getFirstNumber(), fileReader.getPairs());
        assertEquals(fileReader.getExpectedResult(), algorithm.findMinValOfScoutsNeeded());
    }

    @Test
    public void shouldThrowExceptionWhenTooManyData()throws FileFormatException{
        thrown.expect(AAL.FileFormatException.class);
        thrown.expectMessage("Zle sformatowane dane - pusta linia lub za duzo danych w linii!");
        fileReader = new FileReader(path + "/test3.txt");
    }

    @Test
    public void shouldThrowExceptionWhenNoEnoughData()throws FileFormatException{
        thrown.expect(FileFormatException.class);
        thrown.expectMessage("Zle sformatowane dane - za malo danych w linii!");
        fileReader = new FileReader(path + "/test4.txt");
    }

    @Test
    public void shouldThrowExceptionWhenFileIsEmpty()throws FileFormatException{
        thrown.expect(FileFormatException.class);
        thrown.expectMessage("Pusty plik!");
        fileReader = new FileReader(path + "/test5.txt");
    }

}
