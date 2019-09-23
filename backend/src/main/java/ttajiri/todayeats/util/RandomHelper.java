package ttajiri.todayeats.util;

import org.springframework.stereotype.*;

import java.security.*;

@Component
public class RandomHelper {
    private SecureRandom random;

    public RandomHelper() throws NoSuchAlgorithmException {
        this.random = SecureRandom.getInstanceStrong();
    }

    public int nextInt() { return Math.abs(random.nextInt()); }

    public int nextInt(int maxValue) {
        return random.nextInt(maxValue);
    }
}
