import java.io.*;
import java.util.*;
public class FruitBaskets
{
    public static long pow(int num, int power)
    {
        long ans=1;
        for (int i=0; i<power; i++)
        {
            ans*=num;
        }
        return ans;
    }
    public static void main(String args[])
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numWeights = Integer.parseInt(br.readLine());
            long[] weights = new long[numWeights];
            StringTokenizer str = new StringTokenizer(br.readLine());
            for (int i = 0; i < numWeights; i++) {
                weights[i] = Integer.parseInt(str.nextToken());
            }
            long totalWeight = 0;
            long numPossibilities = pow(2, numWeights - 1);
            for (int i = 0; i < numWeights; i++) {
                totalWeight += numPossibilities * weights[i];
            }
            List<Long> possibleArrangements=new ArrayList<>();
            long num=0L;
            for (int i=0; i<numWeights; i++)
            {
                for (int j=i+1; j<numWeights; j++)
                {
                    for (int k=j+1; k<numWeights; k++)
                    {
                        num = (1L<<(numWeights-1-i)) | (1L<<(numWeights-1-j)) | (1L<<(numWeights-1-k));
                        possibleArrangements.add(num);
                    }
                    num= ((1L<<(numWeights-1-i)) | (1L<<(numWeights-1-j)));
                    possibleArrangements.add(num);
                }
                num= (1L<<(numWeights-1-i));
                possibleArrangements.add(num);
            }

            long weightsLessThan200=0;
            for (long i : possibleArrangements) {
                int currentSum = 0;
                for (int j = 0; j < numWeights; j++) {
                    if ((i & (1L << j)) != 0) {
                        currentSum += weights[j];
                    }
                }
                if (currentSum < 200)
                    weightsLessThan200 += currentSum;
            }
            System.out.println(totalWeight-weightsLessThan200);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}