import java.io.*;
import java.util.*;

public class Brexit {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int numCountries = Integer.parseInt(currentLine.nextToken());
            int numPartnerships = Integer.parseInt(currentLine.nextToken());
            int homeCountry = Integer.parseInt(currentLine.nextToken())-1;
            int firstToLeave = Integer.parseInt(currentLine.nextToken())-1;
            Map<Integer, List> partnerships = new HashMap<>();
            int[] partnershipsForCountry = new int[numCountries];
            for (int i=0; i<numPartnerships; i++)
            {
                currentLine = new StringTokenizer(br.readLine());
                int country1 = Integer.parseInt(currentLine.nextToken())-1;
                int country2 = Integer.parseInt(currentLine.nextToken())-1;
                if (partnerships.get(country1)==null)
                    partnerships.put(country1,new ArrayList<Integer>());
                partnerships.get(country1).add(country2);
                if (partnerships.get(country2)==null)
                    partnerships.put(country2,new ArrayList<Integer>());
                partnerships.get(country2).add(country1);
                partnershipsForCountry[country1]+=1;
                partnershipsForCountry[country2]+=1;
            }
            boolean[] visited = new boolean[numCountries];
            Queue<Integer> leavingCountries = new LinkedList<>();
            leavingCountries.add(firstToLeave);
            while (!leavingCountries.isEmpty()) {
                int currentCountry = leavingCountries.remove();
                visited[currentCountry] = true;
                partnershipsForCountry[currentCountry] = 0;
                List<Integer> partnerCountries = partnerships.get(currentCountry);
                for (int i = 0; i < partnerCountries.size(); i++) {
                    int thisCountry = partnerCountries.get(i);
                    if (!visited[thisCountry]) {
                        partnershipsForCountry[thisCountry] -= 1;
                        if (partnershipsForCountry[thisCountry] <= ((partnerships.get(thisCountry)).size() / 2)) {
                            visited[thisCountry] = true;
                            leavingCountries.add(thisCountry);
                        }
                    }
                }
            }
            if (partnershipsForCountry[homeCountry]==0)
                System.out.println("leave");
            else System.out.println("stay");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
