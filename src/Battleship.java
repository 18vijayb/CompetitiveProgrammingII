import java.io.*;
import java.util.*;
public class Battleship {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numCases = Integer.parseInt(br.readLine());
            for (int i=0; i<numCases; i++)
            {
                StringTokenizer currentLine = new StringTokenizer(br.readLine());
                int width = Integer.parseInt(currentLine.nextToken());
                int height = Integer.parseInt(currentLine.nextToken());
                int numShots = Integer.parseInt(currentLine.nextToken());
                boolean[][] playerOne = new boolean[width][height];
                boolean[][] playerTwo = new boolean[width][height];
                int playerOneNumHashtags = 0;
                int playerTwoNumHashtags = 0;
                for (int j=0; j<height; j++)
                {
                    String shipLine = br.readLine();
                    for (int k=0; k<width; k++) {
                        playerOne[j][k]=shipLine.charAt(k)=='#';
                        playerOneNumHashtags++;
                    }
                }
                for (int j=0; j<height; j++) {
                    String shipLine = br.readLine();
                    for (int k = 0; k < width; k++) {
                        playerTwo[j][k] = shipLine.charAt(k) == '#';
                        playerTwoNumHashtags++;
                    }
                }
                for (int j=0; j<numShots; j++)
                {

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
