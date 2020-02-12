import java.io.*;
import java.util.*;
public class KeyboardConcert {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer currentLine = new StringTokenizer(br.readLine());

            int numInstruments = Integer.parseInt(currentLine.nextToken());

            int numNotes = Integer.parseInt(currentLine.nextToken());
            int[][] numSwitches = new int[numInstruments][numNotes];
            int[] noteSequence = new int[numNotes];
            Set<Integer>[] instruments = new Set[numInstruments];
            for (int i=0; i<numInstruments; i++)
            {
                currentLine = new StringTokenizer(br.readLine());
                int numNotesInstrumentCanPlay = Integer.parseInt(currentLine.nextToken());
                instruments[i]=new HashSet<>();
                for (int j=0; j<numNotesInstrumentCanPlay; j++)
                    instruments[i].add(Integer.parseInt(currentLine.nextToken()));
            }
            currentLine = new StringTokenizer(br.readLine());
            for (int i=0; i<numNotes; i++)
                noteSequence[i]=Integer.parseInt(currentLine.nextToken());
            int minSwitches = 0;
            for (int i=0; i<numInstruments; i++)
                if (instruments[i].contains(noteSequence[0]))
                    numSwitches[i][0]=0;
                else numSwitches[i][0]=-1;
            for (int i=1; i<numNotes; i++)
            {
                for (int j=0; j<numInstruments; j++)
                {
                    if (instruments[j].contains(noteSequence[i]))
                        if (numSwitches[j][i-1]!=-1)
                            numSwitches[j][i]=numSwitches[j][i-1];
                        else numSwitches[j][i]=minSwitches+1;
                    else
                        numSwitches[j][i]=-1;
                }
                minSwitches = Integer.MAX_VALUE;
                for (int j=0; j<numInstruments; j++)
                    if (numSwitches[j][i]!=-1)
                        if (minSwitches>numSwitches[j][i])
                            minSwitches=numSwitches[j][i];
            }
//            for (int i=0; i<numInstruments; i++)
//            {
//                for (int j=0; j<numNotes; j++)
//                {
//                    System.out.printf("%02d ",numSwitches[i][j]);
//                }
//                System.out.println();
//            }
            System.out.println(minSwitches);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
