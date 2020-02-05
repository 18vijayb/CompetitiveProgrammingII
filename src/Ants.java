import java.io.*;
import java.util.*;

public class Ants {

    public static void main(String[] args) {
        try
        {
            Scanner sc = new Scanner(System.in);
            int numCases = sc.nextInt();
            int[] earliestTimes = new int[numCases];
            int[] latestTimes = new int[numCases];
            for (int i=0; i<numCases; i++)
            {
                int poleLength = sc.nextInt();
                int numberOfAnts = sc.nextInt();
                int[] antPositions = new int[numberOfAnts];
                int max = 0;
                int min = Integer.MAX_VALUE;
                for (int j=0; j<numberOfAnts; j++)
                {
                    antPositions[j]=sc.nextInt();
                    if (antPositions[j]>max)
                        max=antPositions[j];
                    if (antPositions[j]<min)
                        min=antPositions[j];
                }
                int closestToCenter = 0;
                int center = poleLength % 2 == 1 ? (poleLength/2)+1 : (poleLength/2);
                for (int j=0; j<numberOfAnts; j++)
                {
                    int distanceFromEdge = 0;
                    if (antPositions[j]>=center)
                        if (poleLength%2==0 && antPositions[j]==center)
                            distanceFromEdge = antPositions[j];
                        else distanceFromEdge = poleLength-antPositions[j];
                    else distanceFromEdge = antPositions[j];
                    if (distanceFromEdge>closestToCenter)
                        closestToCenter=distanceFromEdge;
                }
                earliestTimes[i]=closestToCenter;
                latestTimes[i]=Math.max(poleLength-min,max);
            }
            for (int i=0; i<numCases; i++)
            {
                System.out.println(earliestTimes[i] + " " + latestTimes[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
