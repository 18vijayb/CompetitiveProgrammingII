import java.io.*;
import java.util.*;

public class DiningCourts {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numDiningCourts = Integer.parseInt(br.readLine());
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int[] diningCourtLines = new int[numDiningCourts];
            for (int i=0; i<numDiningCourts; i++)
            {
                diningCourtLines[i]=Integer.parseInt(currentLine.nextToken());
            }
            int richard = 0;
            while ((diningCourtLines[richard%numDiningCourts]-richard)<=0)
            {
                richard++;
            }
            System.out.println((richard%numDiningCourts)+1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
