import java.io.*;
import java.util.*;

public class Paintings {
    static HashMap<String,Integer> colorToNumber=new HashMap<>();
    static HashMap<Integer,String> numberToColor=new HashMap<>();
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numInputs = Integer.parseInt(br.readLine());
            for (int i=0; i<numInputs; i++)
            {
                int numColors = Integer.parseInt(br.readLine());
                Set<Integer> colors = new HashSet<>();
                HashMap<Integer,List<Integer>> badPairs = new HashMap<>();
                StringTokenizer line = new StringTokenizer(br.readLine());
                for (int j=0; j<numColors; j++)
                {
                    String currentColor = line.nextToken();
                    colorToNumber.put(currentColor,j);
                    numberToColor.put(j,currentColor);
                    colors.add(j);
                }
                int numPairs = Integer.parseInt(br.readLine());
                for (int j=0; j<numPairs; j++)
                {
                    line = new StringTokenizer(br.readLine());
                    int firstColor = colorToNumber.get(line.nextToken());
                    int secondColor = colorToNumber.get(line.nextToken());
                    if (!(badPairs.containsKey(firstColor)))
                        badPairs.put(firstColor,new ArrayList<>());
                    badPairs.get(firstColor).add(secondColor);
                }
                int numPaintings = computePaintings(badPairs,colors,-1);
                System.out.println(badPairs);
                System.out.println(numPaintings);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int computePaintings(HashMap<Integer,List<Integer>> badPairs, Set<Integer> availableColors, int prevColor)
    {
        Set<Integer> availableGoodColors = new HashSet<>(availableColors);
        Set<Integer> availableColorsCopy = new HashSet<>(availableColors);
        int numPaintings=0;
        if (badPairs.containsKey(prevColor))
        {
            for (int i=0; i<badPairs.get(prevColor).size(); i++)
            {
                availableGoodColors.remove(badPairs.get(prevColor).get(i));
            }
        }
        for (int color: availableGoodColors)
        {
            System.out.println(availableColorsCopy);
            availableColorsCopy.remove(color);
            if (availableColorsCopy.size()!=0)
                numPaintings+=1+computePaintings(badPairs,availableColorsCopy,color);

        }
        return numPaintings;
    }
}
