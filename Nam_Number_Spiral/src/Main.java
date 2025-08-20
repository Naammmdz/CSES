import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // ========= FAST SCANNER =========
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        private int skip() throws IOException {
            int c;
            while ((c = read()) <= ' ' && c != -1) { /* skip space */ }
            return c;
        }

        // ---- primitives
        int nextInt() throws IOException {
            int c = skip(), sign = 1, val = 0;
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c = skip(), sign = 1;
            long val = 0L;
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return sign == 1 ? val : -val;
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        // ---- tokens & lines
        String next() throws IOException {
            int c = skip();
            StringBuilder sb = new StringBuilder(16);
            while (c > ' ') { sb.append((char) c); c = read(); }
            return sb.toString();
        }

        String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder(64);
            int c;
            // consume possible LF/CR leftovers
            if ((c = read()) == '\r') { if (read() != '\n') ptr--; return ""; }
            while (c != -1 && c != '\n' && c != '\r') { sb.append((char) c); c = read(); }
            // handle CRLF
            if (c == '\r') { int n = read(); if (n != '\n') ptr--; }
            return sb.toString();
        }

        boolean hasNext() throws IOException {
            int c;
            while ((c = read()) != -1) {
                if (c > ' ') { ptr--; return true; }
            }
            return false;
        }

        // ---- helpers
        int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

    // ========= FAST WRITER =========
    static final class FastWriter {
        private final StringBuilder sb = new StringBuilder(1 << 20); // ~1MB
        private final OutputStream out;
        FastWriter(OutputStream os) { this.out = os; }
        void print(Object o) { sb.append(o); }
        void println(Object o) { sb.append(o).append('\n'); }
        void println() { sb.append('\n'); }
        void flush() throws IOException { out.write(sb.toString().getBytes()); sb.setLength(0); }
    }
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner(System.in);
        FastWriter fw = new FastWriter(System.out);

        int t = fs.nextInt(); // số lượng test case
        while (t-- > 0) {
            long y = fs.nextLong();
            long x = fs.nextLong();

            long ans;
            if (y >= x) {
                if (y % 2 == 0) {
                    ans = y * y - x + 1;
                } else {
                    ans = (y - 1) * (y - 1) + x;
                }
            } else {
                if (x % 2 == 1) {
                    ans = x * x - y + 1;
                } else {
                    ans = (x - 1) * (x - 1) + y;
                }
            }

            fw.println(ans);
        }

        fw.flush();
    }
}