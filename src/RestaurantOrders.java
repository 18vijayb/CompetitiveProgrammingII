import java.io.*;
import java.util.*;
public class RestaurantOrders {
    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            int numItems = in.nextInt();
            int[] coins = new int[numItems];
            for (int i = 0; i < numItems; i++)
                coins[i] = in.nextInt();
            int numOrders = in.nextInt();
            int[] costs = new int[numOrders];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < numOrders; i++) {
                costs[i] = in.nextInt();
                if (max < costs[i])
                    max = costs[i];
            }
            List<List> dp = new ArrayList<>();
            int[] isPossible = new int[max + 1];
            boolean[] haveReached = new boolean[max + 1];
            for (int i = 0; i < isPossible.length; i++) {
                dp.add(new ArrayList<>());
                isPossible[i] = -1;
                haveReached[i] = false;
            }
            isPossible[0] = 1;
            for (int i = 0; i < numItems; i++) {
                for (int j = 0; j <= max - coins[i]; j++) {
                    if (isPossible[j] == 1 && isPossible[j + coins[i]] == -1)
                        isPossible[j + coins[i]] = 1;
                    else if (isPossible[j] == 1 && isPossible[j + coins[i]] == 1)
                        isPossible[j + coins[i]] = 0;
                    else if (isPossible[j] == 0)
                        isPossible[j + coins[i]] = 0;
                }
            }
            for (int j = 0; j <= max; j++) {
                for (int i = 0; i < numItems; i++) {
                    if (j + coins[i] <= max)
                        if (isPossible[j] == 1 && isPossible[j + coins[i]] == 1 && !haveReached[j + coins[i]]) {
                            dp.get(j + coins[i]).add(j);
                            dp.get(j + coins[i]).add(i + 1);
                            haveReached[j + coins[i]] = true;
                        }
                }
            }
            PrintWriter out = new PrintWriter(System.out);
            for (int i = 0; i < numOrders; i++) {
                int cost = costs[i];
                if (isPossible[cost] == -1)
                    out.println("Impossible");
                else if (isPossible[cost] == 0)
                    out.println("Ambiguous");
                else {
                    List<Integer> runningList = new ArrayList<>();
                    List<Integer> current = dp.get(cost);
                    while (current.size()>1)
                    {
                        runningList.add(current.get(1));
                        current=dp.get(current.get(0));
                    }
                    Collections.sort(runningList);
                    for (int j = 0; j < runningList.size() - 1; j++) {
                        out.print(runningList.get(j) + " ");
                    }
                    out.println(runningList.get(runningList.size() - 1));
                }
            }
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        final private int MAX_LINE_SIZE = 1 << 20;
        private DataInputStream din;
        private byte[] buffer, lineBuf;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public FastReader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public boolean hasNext() throws IOException {
            byte c;
            while ((c = read()) != -1) {
                if (c > ' ') {
                    bufferPointer--;
                    return true;
                }
            }
            return false;
        }
        public String nextLine() throws IOException {
            if (lineBuf == null)	lineBuf = new byte[MAX_LINE_SIZE];
            int ctr = 0;
            byte c;
            while ((c = read()) != -1) {
                if (c == '\r')        continue;
                if (c == '\n')        break;
                lineBuf[ctr++] = c;
            }
            return new String(lineBuf, 0, ctr);
        }
        public String next() throws IOException {
            if (lineBuf == null)	lineBuf = new byte[MAX_LINE_SIZE];
            int ctr = 0;
            byte c = read();
            while (c <= ' ')  	c = read();
            while (c > ' ') {
                lineBuf[ctr++] = c;
                c = read();
            }
            return new String(lineBuf, 0, ctr);
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')   c = read();
            boolean neg = (c == '-');
            if (neg)           c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)           return -ret;
            return ret;
        }
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')    c = read();
            boolean neg = (c == '-');
            if (neg)            c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)            return -ret;
            return ret;
        }
        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)  	c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)     return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)     fillBuffer();
            return buffer[bufferPointer++];
        }
        public void close() throws IOException {
            if (din == null) 	   return;
            din.close();
        }
    }
}
