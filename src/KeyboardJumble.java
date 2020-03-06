import java.io.*;
import java.util.*;
public class KeyboardJumble {
    static class Node {
        int start;
        int end;
        Node next;
        public Node(int start, int end)
        {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        try
        {
            FastReader in = new FastReader();
            String input = in.nextLine();
            List<Node> list = new ArrayList<>();
            List<String> inputs = new ArrayList<>();
            while (! input.equals("0"))
            {
                inputs.add(input);
                input = in.nextLine();
            }
            for (int i=0; i<inputs.size(); i++)
            {
                input = inputs.get(i);
                int atLocation = input.indexOf('@');
                int dollarLocation = input.indexOf('$');
                Node head = new Node(0,input.length());
                Node tail = head;
                Node current = head;
                while (!(dollarLocation == -1 && atLocation == -1)) {
                    if (dollarLocation==-1 || (atLocation != -1 && atLocation < dollarLocation)) {
                        Node temp = head;
                        head = new Node(atLocation+1,input.length());
                        current.end=atLocation;
                        head.next = temp;
                        current=head;
                    } else {
                        tail.next = new Node(dollarLocation+1, input.length());
                        current.end=dollarLocation;
                        tail = tail.next;
                        current=tail;
                    }
                    atLocation = input.indexOf('@',current.start);
                    dollarLocation = input.indexOf('$',current.start);
                }
                list.add(head);
            }
            PrintWriter out = new PrintWriter(System.out);
            for (int i=0; i<inputs.size(); i++) {
                Node current = list.get(i);
                String inp = inputs.get(i);
                while (current != null) {
                    out.print(inp.substring(current.start,current.end));
                    current = current.next;
                }
                out.println();
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