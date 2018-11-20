package AAL;

import efficiency.AlgorithmBenchmark;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openjdk.jmh.runner.RunnerException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws Exception {

        if (args.length == 0) {
            JUnitCore junit = new JUnitCore();
            Result res = junit.run(Tests.class);
            if (res.wasSuccessful()) {
                System.out.println("All tests successful");
            } else {
                res.getFailures().forEach(System.out::println);
            }
        } else if (args.length == 1) {

            System.out.println("1 - find minimum number of scouts for file " + args[0]);
            System.out.println("2 - run benchmark test for file " + args[0]);
            System.out.println("q - quit");

            Scanner sc = new Scanner(System.in);
            String value;
            value = sc.nextLine();

            switch (value) {
                case "1":
                    useAlgorithm(args[0]);
                    break;
                case "2":
                    useBenchmark(args[0]);
                    break;
                case "q":
                    return;
                default:
                    break;
            }
        }
        else {
            throw new Exception("Bledna ilosc danych wejsciowych! Wzor: nazwa.jar plik.txt");
        }

    }

    private static void useAlgorithm(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            List<Pair> edges = fileReader.getPairs();
            Algorithm algorithm = new Algorithm(fileReader.getFirstNumber(), edges);

            System.out.println(algorithm.findMinValOfScoutsNeeded());

            fileReader.close();
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }

    private static void useBenchmark(String file) {
        AlgorithmBenchmark algorithmBenchmark = new AlgorithmBenchmark();
        try {
            algorithmBenchmark.runBenchmark(file);
        } catch (RunnerException e) {
            e.printStackTrace();
        }

    }


}
