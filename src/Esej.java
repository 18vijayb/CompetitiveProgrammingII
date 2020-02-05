import java.util.*;
import java.io.*;
public class Esej {
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer str = new StringTokenizer(br.readLine());
            int minWords = Integer.parseInt(str.nextToken());
            int maxWords = Integer.parseInt(str.nextToken());
            int numUnique = maxWords/2;
            char[] letters = new char[26];
            for (int i=97; i<123; i++)
            {
                letters[i-97]=(char)i;
            }
            int count=0;
            List<String> uniqueWords = new ArrayList<>();
            for (int i=1; i<numUnique+1; i++)
            {
                String word = "";
                for (int j=0; j<26; j++)
                {
                    if ((i & (1<<j))!=0)
                        word+=letters[j];
                }
                uniqueWords.add(word);
            }
            String lastWord = uniqueWords.get(uniqueWords.size()-1);
            if (numUnique<minWords)
            {
                while (uniqueWords.size()<minWords)
                {
                    uniqueWords.add(lastWord);
                }
            }
            for (int i=0; i<uniqueWords.size(); i++)
            {
                System.out.print(uniqueWords.get(i)+" ");
            }

        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }
}
