import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GoodMorning {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numInputs = Integer.parseInt(br.readLine());
            int[] answers = new int[numInputs];
            for (int i = 0; i < numInputs; i++) {
                answers[i]=solve(Integer.parseInt(br.readLine()), true, true);
            }
            for (int i=0; i<numInputs; i++)
                System.out.println(answers[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int solve(int num, boolean isInLess, boolean isInMore) {
        if (num >= 100 && num <= 105)
            return 100;
        if (num >= 106 && num <= 110)
            return 110;
        if (num == 200)
            return 200;
        if (num > 110)
            return 100 + solve(num % 100, true, true);
        List<Integer> accessibleNumbers = new ArrayList<>();
        switch (num / 10) {
            case 0:
            case 1:
                return num;
            case 2:
                accessibleNumbers = new ArrayList<>(Arrays.asList(0, 2, 3, 5, 6, 8, 9));
                break;
            case 3:
                accessibleNumbers = new ArrayList<>(Arrays.asList(3, 6, 9));
                break;
            case 4:
                accessibleNumbers = new ArrayList<>(Arrays.asList(0, 4, 5, 6, 7, 8, 9));
                break;
            case 5:
                accessibleNumbers = new ArrayList<>(Arrays.asList(0, 5, 6, 8, 9));
                break;
            case 6:
                accessibleNumbers = new ArrayList<>(Arrays.asList(6, 9));
                break;
            case 7:
                accessibleNumbers = new ArrayList<>(Arrays.asList(0, 7, 8, 9));
                break;
            case 8:
                accessibleNumbers = new ArrayList<>(Arrays.asList(0, 8, 9));
                break;
            case 9:
                accessibleNumbers = new ArrayList<>(Arrays.asList(9));
                break;
        }
        int tens = num / 10;
        int mod = num % 10;
        int searchResult = Collections.binarySearch(accessibleNumbers, mod);
        if (searchResult < 0) {
            int more=0;
            int less=0;
            if (isInLess) {
                less = solve(num - 1, true, false);
            }
            if (isInMore) {
                more = solve(num + 1, false, true);
            }
            if (!isInMore && isInLess)
                return less;
            if (isInMore && !isInLess)
                return more;
            if ((more-num)>(num-less))
                return less;
            else return more;
        }
        return (tens * 10) + accessibleNumbers.get(searchResult);
    }

}
