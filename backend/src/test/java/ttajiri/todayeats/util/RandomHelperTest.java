package ttajiri.todayeats.util;

import org.junit.*;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class RandomHelperTest {
    private RandomHelper target;

    @Test
    public void 動作確認() throws Exception {
        target = new RandomHelper();
        assertThat(target.nextInt(100), allOf(greaterThan(0), lessThan(100)));
    }
}
