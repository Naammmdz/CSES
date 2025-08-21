import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        FastWriter fw = new FastWriter(System.out);

        long n = fs.nextLong();
        long sum = n * (n + 1) / 2;

        if ((sum & 1L) == 1L) {
            fw.println("NO");
        } else {
            fw.println("YES");
            ArrayList<Long> a = new ArrayList<>();
            ArrayList<Long> b = new ArrayList<>();

            if (n % 4 == 0) {
                long i = 1, j = n;
                while (i < j) {
                    a.add(i); a.add(j);      // (i, j) vào set1
                    i++; j--;
                    if (i < j) {             // tiếp cặp kế vào set2
                        b.add(i); b.add(j);
                        i++; j--;
                    }
                }
            } else { // n % 4 == 3
                a.add(1L); a.add(2L);
                b.add(3L);
                long i = 4, j = n;
                while (i < j) {
                    a.add(i); a.add(j);
                    i++; j--;
                    if (i < j) {
                        b.add(i); b.add(j);
                        i++; j--;
                    }
                }
            }

            // In đúng format, không dấu cách thừa, không in set rỗng "0" sai chỗ
            fw.println(a.size());
            for (int i = 0; i < a.size(); i++) { fw.print(a.get(i)); if (i + 1 < a.size()) fw.print(" "); }
            fw.println();

            fw.println(b.size());
            for (int i = 0; i < b.size(); i++) { fw.print(b.get(i)); if (i + 1 < b.size()) fw.print(" "); }
            fw.println();
        }
        fw.flush();
    }
}