/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * This test does not collect any kind of personal information!
 *
 * @author nick
 * @version 0.1
 */

import org.junit.Before;
import org.junit.Test;
import service.PwnedPasswordService;
import static org.junit.Assert.assertTrue;

public class TestService {

    private PwnedPasswordService pwnedPasswordService;

    @Before
    public void setUp() {
        pwnedPasswordService = new PwnedPasswordService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyPass() {
        pwnedPasswordService.getCountOfHashedPassword("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyPassFromSpaces() {
        pwnedPasswordService.getCountOfHashedPassword("  ");
    }

    @Test()
    public void checkPass() {
        assertTrue(pwnedPasswordService.getCountOfHashedPassword("zzz") != 0);
    }

}
