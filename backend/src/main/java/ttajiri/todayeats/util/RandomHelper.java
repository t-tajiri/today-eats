package ttajiri.todayeats.util;

import org.springframework.stereotype.*;

import java.security.*;

@Component
public class RandomHelper {
    private SecureRandom random;

    public RandomHelper() throws NoSuchAlgorithmException {
        this.random = SecureRandom.getInstanceStrong();
    }

    public int nextInt() { return random.nextInt(Integer.MAX_VALUE) + 1; }

    public int nextInt(int maxValue) {
        return random.nextInt(maxValue);
    }
}
