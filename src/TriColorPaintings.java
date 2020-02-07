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
            for (int i=0; i<K; i++)
            {
                currentLine = new StringTokenizer(br.readLine());
                if (currentLine.nextToken().equals("S"))
                {
                    int stripe1 = Integer.parseInt(currentLine.nextToken())-1;
                    int stripe2 = Integer.parseInt(currentLine.nextToken())-1;
                    if (! (same.containsKey(stripe1)))
                    {
                        same.put(stripe1,new ArrayList<>());
                    }
                    same.get(stripe1).add(stripe2);
                    if (! (same.containsKey(stripe2)))
                    {
                        same.put(stripe2,new ArrayList<>());
                    }
                    same.get(stripe2).add(stripe1);
                }
                else
                {
                    int stripe1 = Integer.parseInt(currentLine.nextToken())-1;
                    int stripe2 = Integer.parseInt(currentLine.nextToken())-1;
                    if (! (different.containsKey(stripe1)))
                    {
                        different.put(stripe1,new ArrayList<>());
                    }
                    different.get(stripe1).add(stripe2);
                    if (! (different.containsKey(stripe2)))
                    {
                        different.put(stripe2,new ArrayList<>());
                    }
                    different.get(stripe2).add(stripe1);
                }
            }
            int[] numCombos = new int[N];
            for (int i=0; i<N; i++)
            {
                numCombos[i]=3;
            }
            for (int i=0; i<N; i++)
            {
                if (same.containsKey(i))
                {
                    List<Integer> sameColor = same.get(i);
                    for (int j: sameColor)
                    {
                        if (i<j)
                            numCombos[j]=1;
                    }
                }
                if (different.containsKey(i))
                {
                    List<Integer> differentColor = different.get(i);
                    for (int j: differentColor)
                    {
                        if (i<j && numCombos[j]>1)
                            numCombos[j]--;
                        else if (i<j && numCombos[j]==1)
                                if (numCombos[i]>1)
                                    numCombos[i]--;
                    }
                }
            }
            int runningMult = 1;
            for (int i: numCombos)
                runningMult*=i;
            //System.out.println(Arrays.toString(numCombos));
            System.out.println(runningMult);
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }
}
