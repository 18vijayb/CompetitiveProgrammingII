import java.io.*;
import java.util.*;
public class TravelingMonk {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer line = new StringTokenizer(br.readLine());
            int ascent = Integer.parseInt(line.nextToken());
            int descent = Integer.parseInt(line.nextToken());
            int[][] ascentIntervals = new int[ascent][2];
            int[][] prefixSumAscent = new int[ascent+1][2];
            int[][] descentIntervals = new int[descent][2];
            int[][] prefixSumDescent = new int[descent+1][2];
            for (int i = 0; i < ascent; i++) {
                line = new StringTokenizer(br.readLine());
                ascentIntervals[i][0] = Integer.parseInt(line.nextToken());
                ascentIntervals[i][1] = Integer.parseInt(line.nextToken());
            }
            for (int i = 0; i < descent; i++) {
                line = new StringTokenizer(br.readLine());
                descentIntervals[i][0] = -Integer.parseInt(line.nextToken());
                descentIntervals[i][1] = Integer.parseInt(line.nextToken());
            }
            prefixSumAscent[0][0]=0;
            prefixSumAscent[0][1]=0;
            for (int i=1; i< ascent+1; i++)
            {
                prefixSumAscent[i][0]=prefixSumAscent[i-1][0]+ascentIntervals[i-1][0];
                prefixSumAscent[i][1]=prefixSumAscent[i-1][1]+ascentIntervals[i-1][1];
            }
            prefixSumDescent[0][0]=prefixSumAscent[ascent][0];
            prefixSumDescent[0][1]=0;
            for (int i=1; i<descent+1; i++)
            {
                prefixSumDescent[i][0]=prefixSumDescent[i-1][0]+descentIntervals[i-1][0];
                prefixSumDescent[i][1]=prefixSumDescent[i-1][1]+descentIntervals[i-1][1];
            }
            int endTime = prefixSumAscent[ascent][1];
            if (prefixSumDescent[descent][1]<prefixSumAscent[ascent][1])
                endTime=prefixSumDescent[descent][1];
            double answer = bisection(0,endTime,ascentIntervals,prefixSumAscent,descentIntervals,prefixSumDescent);
            //But what if the answer coincides with a break?
            double middle = 0;

            for (int i = 0; i < prefixSumAscent.length; i++) {
                if (prefixSumAscent[i][1] >= answer) {
                    middle=prefixSumAscent[i-1][1];
                    break;
                }
            }
            double ascentWithBreak = answer;
            double ascentLocation = modelAscentDescent(middle,ascentIntervals,prefixSumAscent);
            double descentLocation =modelAscentDescent(middle,descentIntervals,prefixSumDescent);
            //System.out.println(ascentLocation + " " + descentLocation);
            if (Math.abs(ascentLocation-descentLocation)<=0.00000001)
                ascentWithBreak=middle;
            for (int i = 0; i < prefixSumDescent.length; i++) {
                if (prefixSumDescent[i][1] >= answer) {
                    middle=prefixSumDescent[i-1][1];
                    break;
                }
            }
            ascentLocation = modelAscentDescent(middle,ascentIntervals,prefixSumAscent);
            descentLocation =modelAscentDescent(middle,descentIntervals,prefixSumDescent);
            double descentWithBreak = answer;
            if (Math.abs(ascentLocation-descentLocation)<=0.00000001)
                descentWithBreak=middle;
            answer = descentWithBreak>ascentWithBreak ? ascentWithBreak : descentWithBreak;
            System.out.println(answer);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static double modelAscentDescent(double time, int[][] arr, int[][] arrPrefix) {
        int index = 0;
        for (int i = 0; i < arrPrefix.length; i++) {
            if (arrPrefix[i][1] > time) {
                index = i;
                break;
            }
        }
        //System.out.println(time);
        //System.out.println(index);
        return arrPrefix[index][0] + ((time - arrPrefix[index][1]) * ((arr[index-1][0] * 1.0) / arr[index-1][1]));
    }

    public static double bisection(double startTime, double endTime, int[][] ascent, int[][] ascentPrefix, int[][] descent, int[][] descentPrefix)
    {
        double middle = (startTime+endTime)/2;
        double ascentLocation = modelAscentDescent(middle,ascent,ascentPrefix);
        double descentLocation =modelAscentDescent(middle,descent,descentPrefix);
        if (Math.abs(ascentLocation-descentLocation)<=0.00000001)
            return middle;
        else if (ascentLocation>descentLocation)
            return bisection(startTime,middle,ascent,ascentPrefix,descent,descentPrefix);
        else return bisection(middle,endTime,ascent,ascentPrefix,descent,descentPrefix);
    }
}
