import java.io.*;
import java.util.*;

public class TriTiling {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            List<Integer> inputs = new ArrayList<>();
            int max = 0;
            int input;
            int[] tiles = new int[31];
            int[] tilesWithoutCorner = new int[31];
            tiles[0]=1;
            tiles[1]=0;
            tilesWithoutCorner[0]=0;
            tilesWithoutCorner[1]=1;
            while ((input = Integer.parseInt(br.readLine()))!=-1)
            {
                max = max > input ? max : input;
                inputs.add(input);
            }
            for (int i=2; i<=max; i++)
            {
                tiles[i]=tiles[i-2]+(2*tilesWithoutCorner[i-1]);
                tilesWithoutCorner[i]=tiles[i-1]+tilesWithoutCorner[i-2];
            }
            for (int i: inputs)
                System.out.println(tiles[i]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
