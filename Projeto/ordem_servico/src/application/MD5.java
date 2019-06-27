package application;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author guigi
 */
public class MD5 {
    
    public String generate (String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
 
        return new BigInteger(1, m.digest()).toString(16);
    }
    
}
