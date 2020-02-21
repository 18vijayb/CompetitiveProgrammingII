import java.io.*;
import java.util.*;
public class NumbersOnATree {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer line = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(line.nextToken());
            int numNodes = (1 << (height+1))-1;
            if (line.countTokens()==0)
            {
                System.out.println(numNodes);
            }
            else {
                String path = line.nextToken();
                int numFloorNodes = 1 << path.length();
                int numNodesInvolved = (1 << (path.length() + 1)) - 1;
                int startingNumber = (numNodes - numNodesInvolved) + 1;
                int endingNumber = (startingNumber + numFloorNodes) - 1;
                int start=0;
                int end = numFloorNodes;
                for (int i=0; i<path.length(); i++)
                    if (path.charAt(i)=='L')
                        end=(start+end)/2;
                    else start=(start+end)/2;;
                System.out.println(endingNumber-((start+end)/2));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
