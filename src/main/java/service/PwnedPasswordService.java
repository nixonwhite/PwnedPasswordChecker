/**
 * PwnedPassword checker
 * Uses third-party service https://haveibeenpwned.com/Passwords
 *
 * @author nick
 */

package service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class PwnedPasswordService {

    /**
     * Block of constants
     */
    private final int INDEX_START = 0;
    private final int INDEX_END = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(PwnedPasswordService.class);

    /**
     * Main method which rocks
     *
     * @param strPassToCheck contains password we want to check
     * @return count of times password was found
     */
    public int getCountOfHashedPassword(String strPassToCheck) {
        checkString(strPassToCheck);
        String strHash = getSHA1Hash(strPassToCheck);
        checkString(strHash);
        return getCountOfHashesFromBase(strHash);
    }

    /**
     * Method gets map and searches it with a given hash
     *
     * @param strHash contains hash
     * @return count of times hash was found
     */
    private int getCountOfHashesFromBase(String strHash) {
        HashMap<String, Integer> hashedPasswords = getMapOfSHA1HashesFromServer(strHash.substring(INDEX_START, INDEX_END));
        return hashedPasswords.getOrDefault(strHash.substring(INDEX_END, strHash.length()), 0);
    }

    /**
     * Method checks if str is empty or null
     *
     * @param str
     */
    private void checkString(String str) {
        if (StringUtils.isBlank(str)) {
            LOGGER.info("[!] Request cannot be empty!");
            throw new IllegalArgumentException("Request cannot be empty!");
        }
    }

    /**
     * Method convert string to SHA-1 representation
     *
     * @param str contains String value
     * @return String SHA-1 representation of given string
     */
    private String getSHA1Hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes(Charset.forName("UTF-8")));
            return new BigInteger(1, md.digest()).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("[!] " + e.getMessage());
        }
        return null;
    }

    /**
     * Method open connection and passes values to service then make a map with hashes and counts of them
     *
     * @param str contains must contain first 5 chars of SHA-1 hash
     * @return HashMap which contains hashes and counts of them from service
     */
    private HashMap<String, Integer> getMapOfSHA1HashesFromServer(String str) {
        HashMap<String, Integer> hashedPasswords = new HashMap<>();
        try {
            URL url = new URL("https://api.pwnedpasswords.com/range/" + str);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/64.0.3282.167 Chrome/64.0.3282.167 Safari/537.36");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] args = inputLine.split(":");
                if (args.length == 2) hashedPasswords.put(args[0].trim(), Integer.valueOf(args[1].trim()));
            }
            in.close();
        } catch (IOException e) {
            LOGGER.info("[!] " + e.getMessage());
        }
        return hashedPasswords;
    }
}
