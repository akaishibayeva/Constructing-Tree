import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'treeConstruction' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER N
     *  2. LONG_INTEGER X
     */

    public static List<List<Integer>> treeConstruction(int N, long X) {
        // Write your code here
        List<List<Integer>> output = new ArrayList<>();

        //Base case:
        if ( N == 1 && X == 0) {
            List<Integer> arrOutput = new ArrayList<>();
            arrOutput.add(-1);
            arrOutput.add(-1);
            output.add(arrOutput);
            return output;
        }
        int currentParent = 1;
        int childCount = 0;
        int edge = 0;
        int score = 0;
        int currentScore = 1;
        int pow = 1;
        int nodeCounter = 0;

        for (int i = 2; i <= N; i++) {
            if (nodeCounter == Math.pow(2,pow)) {
                pow++;
                currentScore++;
                nodeCounter = 0;
            }
            if (score+currentScore > X)  {
                break;
            }
            score+=currentScore;

            if (childCount == 2) {
                childCount = 0;
                currentParent++;
            }
            List<Integer> arrOutput = new ArrayList<>();
            arrOutput.add(currentParent);
            arrOutput.add(i);
            output.add(arrOutput);
            childCount++;
            edge++;
        }
        if (edge != N-1) {
            output.clear();
            List<Integer> arrOutput = new ArrayList<>();
            arrOutput.add(-1);
            arrOutput.add(-1);
            output.add(arrOutput);
        }
        return output;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int N = Integer.parseInt(firstMultipleInput[0]);

                long X = Long.parseLong(firstMultipleInput[1]);

                List<List<Integer>> result = Result.treeConstruction(N, X);

                result.stream()
                        .map(
                                r -> r.stream()
                                        .map(Object::toString)
                                        .collect(joining(" "))
                        )
                        .map(r -> r + "\n")
                        .collect(toList())
                        .forEach(e -> {
                            try {
                                bufferedWriter.write(e);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}


