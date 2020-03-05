import java.io.*;
import java.util.*;
public class Skeeball {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(currentLine.nextToken());
            int H = Integer.parseInt(currentLine.nextToken());
            currentLine = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(currentLine.nextToken());
            int b = Integer.parseInt(currentLine.nextToken());
            currentLine = new StringTokenizer(br.readLine());
            int[] points = new int[n];
            for (int i=0; i<n; i++)
            {
                points[i]=Integer.parseInt(currentLine.nextToken());
            }
            int[] dp = new int[10001];
            for (int i=0; i<dp.length; i++)
                dp[i]=Integer.MIN_VALUE;
            dp[0]=b;
            for (int i=0; i<dp.length; i++)
            {
                for (int point: points)
                {
                    if (i+point<dp.length) {
                        if (dp[i]>0 && dp[i]!=Integer.MIN_VALUE)
                            dp[i + point] = Math.max(dp[i + point], dp[i] - 1);
                    }
                }
            }
//            for (int i=0; i<dp.length; i++)
//                System.out.println(i + " " + dp[i]);
            int scoreNeeded = H-S;
            int hey = -1;
            for (int i=scoreNeeded+1; i<dp.length; i++)
            {
                if (dp[i]!=Integer.MIN_VALUE) {
                    hey=i;
                    break;
                }
            }
            if (hey==-1)
                System.out.println(hey);
            else System.out.println(hey+S);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
