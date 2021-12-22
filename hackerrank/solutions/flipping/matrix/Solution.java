package hackerrank.solutions.flipping.matrix;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'flippingMatrix' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int flippingMatrix(List<List<Integer>> matrix) {
        // Write your code here
        int sum = 0;

        //Divide size by 2 to get quadrant size
        int quadSize = matrix.size()/2;


        //Now for each cell in the upper quadrant, get the max value that could be flipped into that cell
        //
        //Iterate all rows in quadrant
        for(int r = 0; r < quadSize; r++)
        {
            //Iterate all columns in quadrant
            for(int c = 0; c < quadSize; c++)
            {
                //Grab the 4 possible values that could wind up in this location of the upper quadrant
                int p1 = matrix.get(r).get((2*quadSize) - c - 1);
                int p2 = matrix.get(r).get(c);
                int p3 = matrix.get((2*quadSize) - r - 1).get(c);
                int p4 = matrix.get((2*quadSize) - r - 1).get((2*quadSize) - c - 1);

                //Get the highest value possible in this cell
                sum += Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            }
        }

        return sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> matrix = new ArrayList<>();

                IntStream.range(0, 2 * n).forEach(i -> {
                    try {
                        matrix.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.flippingMatrix(matrix);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

