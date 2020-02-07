import java.io.*;
import java.util.*;

public class CarnivalHopscotch {

    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(currentLine.nextToken());
            int D = Integer.parseInt(currentLine.nextToken());
            int[] points = new int[N];
            for (int i=0; i<N; i++)
                points[i]=Integer.parseInt(br.readLine());
            int leastPoints=0;
            int[] leastPointsSequence = new int[D];
            for (int i=0; i<N; i++)
            {
                leastPoints = Integer.MAX_VALUE;
                for (int j=0; j<D; j++)
                    if (i-j>=0)
                        leastPoints = Math.min(leastPoints,leastPointsSequence[(i-j)%D]);
                leastPointsSequence[i%D]=leastPoints+points[i];
            }
            leastPoints = Integer.MAX_VALUE;
            for (int i=0; i<D; i++)
                leastPoints = leastPoints<leastPointsSequence[i] ? leastPoints : leastPointsSequence[i];
            System.out.println(leastPoints);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}