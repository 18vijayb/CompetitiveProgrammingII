import java.io.*;
import java.util.*;
public class tenkindsofpeople {
    public static void main(String[] args) {
        try {
            FastReader in = new FastReader();
            int numRows = in.nextInt();
            int numCols = in.nextInt();
            int[][] matrix = new int[numRows][numCols];
            boolean[][] visited = new boolean[numRows][numCols];
            for (int i=0; i<numRows; i++)
            {
                String line = in.nextLine();
                for (int j=0; j<numCols; j++)
                {
                    if (line.charAt(j)=='0')
                        matrix[i][j]=-1;
                    else matrix[i][j]=1;
                }
            }
            int numCases = in.nextInt();
            int[][][] cases = new int[numCases][2][2];
            for (int i=0; i<numCases; i++)
            {
                cases[i][0][0]=in.nextInt();
                cases[i][0][1]=in.nextInt();
                cases[i][1][0]=in.nextInt();
                cases[i][1][1]=in.nextInt();
            }
            Stack<int[]> stack = new Stack<>();
            int currentNumber = 1;
            for (int i=0; i<numRows; i++)
            {
                for (int j=0; j<numCols; j++)
                {
                    if (! visited[i][j]) {
                        stack.push(new int[]{i, j});
                        visited[i][j]=true;
                        while (!stack.isEmpty()) {
                            int[] currentCoords = stack.pop();
                            int x = currentCoords[0];
                            int y = currentCoords[1];
                            matrix[x][y] *= currentNumber;
                            boolean sign = isPositive(matrix[x][y]);
                            if (x - 1 >= 0 && !visited[x - 1][y] && sign == isPositive(matrix[x - 1][y])) {
                                stack.push(new int[]{x - 1, y});
                                visited[x - 1][y] = true;
                            }
                            if (y - 1 >= 0 && !visited[x][y - 1] && sign == isPositive(matrix[x][y - 1])) {
                                stack.push(new int[]{x, y - 1});
                                visited[x][y - 1] = true;
                            }
                            if (x + 1 < numRows && !visited[x + 1][y] && sign == isPositive(matrix[x + 1][y])) {
                                stack.push(new int[]{x + 1, y});
                                visited[x+1][y] = true;
                            }
                            if (y + 1 < numCols && !visited[x][y + 1] && sign == isPositive(matrix[x][y + 1])) {
                                stack.push(new int[]{x, y + 1});
                                visited[x][y+1] = true;
                            }
                        }
                        currentNumber++;
                    }
                }
            }
            for (int i=0; i<numCases; i++)
            {
                int startx = cases[i][0][0]-1;
                int starty = cases[i][0][1]-1;
                int endx = cases[i][1][0]-1;
                int endy = cases[i][1][1]-1;
                if (matrix[startx][starty]==matrix[endx][endy])
                    if (isPositive(matrix[startx][starty]))
                        System.out.println("decimal");
                    else System.out.println("binary");
                else System.out.println("neither");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    static boolean isPositive(int a)
    {
         return a>0;
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
