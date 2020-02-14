import java.io.*;
import java.util.*;
public class Centsavings {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(currentLine.nextToken());
            int D = Integer.parseInt(currentLine.nextToken())+1;
            int[] prices = new int[N];
            currentLine = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++)
                prices[i]=Integer.parseInt(currentLine.nextToken());
            int[][] dp = new int[D][N];
            for (int j=0; j<N; j++)
            {
                for (int i=0; i<D; i++)
                {
                    if (i<=j) //We have less dividers than we do items
                    {
                        if (i==0) //When we have 0 dividers to place
                        {
                            if (j==0)
                                dp[i][j] = prices[j];
                            else dp[i][j] = prices[j]+dp[i][j-1];
                        }
                        else
                        {
                            int addingDivider = floor(dp[i-1][j]-prices[j])+prices[j];
                            int priorSolution = dp[i][j-1]+prices[j];
                            if (dp[i][j-1]==-1) {
                                dp[i][j] = addingDivider;
                            }
                            else {
                                dp[i][j] = Math.min(addingDivider,priorSolution);
                            }
                        }
                    }
                    else
                    {
                        dp[i][j]=-1;
                    }
                }
            }
            int min = Integer.MAX_VALUE;
            for (int i=0; i<D; i++)
            {
                if (dp[i][N-1]!=-1)
                    min = Math.min(dp[i][N-1],min);
            }
//            for (int[] i: dp) {
//                for (int j : i)
//                    System.out.print(j+" ");
//                System.out.println();
//            }
            System.out.println(floor(min));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static int floor(int i)
    {
        if ((i%10)>=5)
            return (10-(i%10))+i;
        else return (i-(i%10));
    }
}
