
import java.math.BigInteger;

public class BlumBlumShubPseudorandomNumberGenerator {
    private long x;
    private long m;
    private final long p = 1301081L;
    private final long q = 1299721L;

    public BlumBlumShubPseudorandomNumberGenerator() {
        m = p * q;
        x0();
    }

    public boolean flip() {
        return blumBlumShub();
    }

    private boolean blumBlumShub() {
        x = (x * x) % m;
        return parity(x);
    }

    private boolean parity(long n) {
        BigInteger b = BigInteger.valueOf(n);
        byte c = 0;
        for (byte i = 0; i < 16; i++) {
            if (b.testBit(i)) {
                c++;
            }
        }
        return c % 2 == 0;
    }

    private void x0() {
        x = System.currentTimeMillis();
        while (x % m == 0 || x < 2) {
            x++;
        }
    }

    public static void main(String[] args) {
        BlumBlumShubPseudorandomNumberGenerator b = new BlumBlumShubPseudorandomNumberGenerator();
        System.out.println(b.flip());
    }
}
