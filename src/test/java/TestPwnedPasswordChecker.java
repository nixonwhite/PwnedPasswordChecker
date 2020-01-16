/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * This test does not collect any kind of personal information!
 *
 * @author nick
 * @version 0.1
 */

import service.PwnedPasswordService;
import java.util.Scanner;

public class TestPwnedPasswordChecker {

    public static void main(String[] args) {

        System.out.print("Enter password to check: ");

        Scanner sc = new Scanner(System.in);

        String result, strPassToCheck = sc.nextLine().trim();

        PwnedPasswordService pwnedPasswordService = new PwnedPasswordService();

        int times = pwnedPasswordService.getCountOfHashedPassword(strPassToCheck);

        if (times != 0) {
            result = "Your password found " + times + " times! It's better to change it.";
        } else {
            result = "Looks like your password wasn't leaked!";
        }

        System.out.println(result);
    }
}
