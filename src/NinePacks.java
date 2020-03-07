import java.io.*;
import java.util.*;
public class NinePacks {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int numHotDogs = Integer.parseInt(currentLine.nextToken());
            int[] hotDogsPerPack = new int[numHotDogs];
            int hotDogSum=0;
            for (int i=0; i<numHotDogs; i++)
            {
                hotDogsPerPack[i]=Integer.parseInt(currentLine.nextToken());
                hotDogSum+=hotDogsPerPack[i];
            }

            currentLine = new StringTokenizer(br.readLine());
            int numBuns = Integer.parseInt(currentLine.nextToken());
            int[] bunsPerPack = new int[numBuns];
            int bunSum = 0;
            for (int i=0; i<numBuns; i++)
            {
                bunsPerPack[i] = Integer.parseInt(currentLine.nextToken());
                bunSum+=bunsPerPack[i];
            }
            int[] bunDP = new int[bunSum+1];
            int[] hotDogDP = new int[hotDogSum+1];
            for (int i=0; i<bunDP.length; i++)
                bunDP[i]=1000000;
            for (int i=0; i<hotDogDP.length; i++)
                hotDogDP[i]=1000000;
            bunDP[0]=0;
            hotDogDP[0]=0;
            for (int hotDog: hotDogsPerPack)
                for (int j = hotDogSum; j>=hotDog; j--)
                    hotDogDP[j]=Math.min(hotDogDP[j],hotDogDP[j-hotDog]+1);
            for (int bun: bunsPerPack)
                for (int j = bunSum; j>=bun; j--)
                    bunDP[j]=Math.min(bunDP[j],bunDP[j-bun]+1);
            int len = bunSum<hotDogSum ? bunSum : hotDogSum;
            int minSum = Integer.MAX_VALUE;
            for (int i=1; i<len+1; i++)
            {
                if (bunDP[i] != 1000000 && hotDogDP[i] != 1000000)
                {
                    int tempSum = bunDP[i]+hotDogDP[i];
                    minSum=tempSum<minSum ? tempSum : minSum;
                }
            }
            if (minSum==Integer.MAX_VALUE)
                System.out.println("impossible");
            else System.out.println(minSum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
