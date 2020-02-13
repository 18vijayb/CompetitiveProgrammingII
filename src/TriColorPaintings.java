import java.io.*;
import java.util.*;
public class TriColorPaintings {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(currentLine.nextToken());
            int K = Integer.parseInt(currentLine.nextToken());
            HashMap<Integer,List<Integer>> different = new HashMap<>();
            HashMap<Integer,List<Integer>> same= new HashMap<>();
            //ALWAYS MAP BIGGER STRIPES TO SMALLER STRIPES
            for (int i=0; i<K; i++)
            {
                currentLine = new StringTokenizer(br.readLine());
                if (currentLine.nextToken().equals("S"))
                {
                    int stripe1 = Integer.parseInt(currentLine.nextToken())-1;
                    int stripe2 = Integer.parseInt(currentLine.nextToken())-1;
                    if (stripe1>stripe2) {
                        if (!(same.containsKey(stripe1))) {
                            same.put(stripe1, new ArrayList<>());
                        }
                        same.get(stripe1).add(stripe2);
                    }
                    else {
                        if (!(same.containsKey(stripe2))) {
                            same.put(stripe2, new ArrayList<>());
                        }
                        same.get(stripe2).add(stripe1);
                    }
                }
                else
                {
                    int stripe1 = Integer.parseInt(currentLine.nextToken())-1;
                    int stripe2 = Integer.parseInt(currentLine.nextToken())-1;
                    if (stripe1>stripe2) {
                        if (!(different.containsKey(stripe1))) {
                            different.put(stripe1, new ArrayList<>());
                        }
                        different.get(stripe1).add(stripe2);
                    }
                    else {
                        if (!(different.containsKey(stripe2))) {
                            different.put(stripe2, new ArrayList<>());
                        }
                        different.get(stripe2).add(stripe1);
                    }
                }
            }
            int[] currentPainting = new int[N];
            int backTracking = rec(0,currentPainting, different, same);
            System.out.println(backTracking);
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
    public static int rec(int currentIndex, int[] currentPainting, HashMap<Integer,List<Integer>> different, HashMap<Integer,List<Integer>> same)
    {
        if (currentIndex==currentPainting.length) {
            //System.out.println("The final painting is " + Arrays.toString(currentPainting));
            return 1;
        }
        Set<Integer> availableGoodColors = new HashSet<>();
        if (same.containsKey(currentIndex))
        {
            List<Integer> sameList = same.get(currentIndex);
            availableGoodColors.add(currentPainting[sameList.get(sameList.size()-1)]);
        }
        else
        {
            availableGoodColors.add(1);
            availableGoodColors.add(2);
            availableGoodColors.add(3);
        }
        if (different.containsKey(currentIndex))
        {
            List<Integer> differentList = different.get(currentIndex);
            HashSet<Integer> cantBeTheseColors = new HashSet<>();
            for (int i: differentList)
                cantBeTheseColors.add(currentPainting[i]);
            if (cantBeTheseColors.contains(1))
                availableGoodColors.remove(1);

            if (cantBeTheseColors.contains(2))
                availableGoodColors.remove(2);
            if (cantBeTheseColors.contains(3))
                availableGoodColors.remove(3);
        }
        int numPaintings=0;
        for (int color: availableGoodColors)
        {
            currentPainting[currentIndex] = color;
            numPaintings+=rec(currentIndex+1,currentPainting,different,same);

        }
        return numPaintings;
    }
}
