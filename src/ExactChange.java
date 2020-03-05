import java.io.*;
import java.util.*;

public class ExactChange {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numInputs = Integer.parseInt(br.readLine());
            int [][] answers = new int[numInputs][2];
            for (int n=0; n<numInputs; n++) {
                int cost = Integer.parseInt(br.readLine());
                int numCoins = Integer.parseInt(br.readLine());
                int[] dp = new int[10001];
                for (int i=0; i<dp.length; i++)
                {
                    dp[i]=10001;
                }
                dp[0]=0;
                for (int i = 0; i < numCoins; i++) {
                    int coin = Integer.parseInt(br.readLine());
                    for (int j=10000; j>=0; j--)
                    {
                        if (j-coin>=0)
                            dp[j]=Math.min(dp[j],dp[j-coin]+1);
                    }
                }
                for (int i=cost; i<dp.length; i++)
                {
                    if (dp[i]!=10001)
                    {
                        answers[n][0]=i;
                        answers[n][1]=dp[i];
                        break;
                    }
                }
            }
            for (int i=0; i<numInputs; i++)
            {
                System.out.println(answers[i][0]+ " " + answers[i][1]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
