import org.junit.Test;
import com.zakharenko.RegexUtil;
import static org.junit.Assert.*;

public class RegexUtilTest {

    @Test
    public void testMatches_ValidRegexAndText() {
        assertTrue(RegexUtil.matches("a*b", "aaab"));
        assertFalse(RegexUtil.matches("a*b", "ac"));
    }

    @Test
    public void testMatches_EmptyRegexAndText() {
        assertTrue(RegexUtil.matches("", ""));
        assertFalse(RegexUtil.matches("", "text"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatches_NullRegex() {
        RegexUtil.matches(null, "text");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatches_NullText() {
        RegexUtil.matches("regex", null);
    }

    @Test
    public void testMatches_CachedPatterns() {
        assertTrue(RegexUtil.matches("a*b", "aaab"));
        assertTrue(RegexUtil.matches("a*b", "ab")); // Ensures caching works
    }
}
