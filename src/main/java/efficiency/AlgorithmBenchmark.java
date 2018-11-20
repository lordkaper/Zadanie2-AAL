package efficiency;

import AAL.Algorithm;
import AAL.FileFormatException;
import AAL.FileReader;
import AAL.Pair;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 30, time = 1)
@Measurement(iterations = 20, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class AlgorithmBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        Algorithm algorithm;

        @Param({"file"})
        String fileName;

        @Setup(Level.Trial)
        public void setUp(){

            FileReader fileReader = null;
            try {
                fileReader = new FileReader(fileName);
            } catch (FileFormatException e) {
                e.printStackTrace();
            }
            List<Pair> edges = fileReader.getPairs();
            algorithm = new Algorithm(fileReader.getFirstNumber(), edges);
            fileReader.close();

        }

    }

    @Benchmark
    public int firstBenchmark(MyState state){
        return state.algorithm.findMinValOfScoutsNeeded();
    }

    public void runBenchmark(String file) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AlgorithmBenchmark.class.getSimpleName())
                .forks(1)
                .param("fileName", file )
                .build();

        new Runner(opt).run();
    }


}
