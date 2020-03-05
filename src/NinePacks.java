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
            for (int i=0; i<numHotDogs; i++)
            {
                hotDogsPerPack[i]=Integer.parseInt(currentLine.nextToken());
            }

            currentLine = new StringTokenizer(br.readLine());
            int numBuns = Integer.parseInt(currentLine.nextToken());
            int[] bunsPerPack = new int[numBuns];
            for (int i=0; i<numBuns; i++)
            {
                bunsPerPack[i] = Integer.parseInt(currentLine.nextToken());
            }

            int[][] bunDP = new int[100][1000];
            int[][] hotDogDP = new int[100][1000];
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
