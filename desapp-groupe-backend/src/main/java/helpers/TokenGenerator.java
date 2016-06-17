package helpers;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {


    private SecureRandom secureRandom;

    public TokenGenerator(SecureRandom secureRandom) {
        this.setSecureRandom(secureRandom);
    }

    public String generate() {
        return new BigInteger(130, this.getSecureRandom()).toString(32);
    }

    public static TokenGenerator defaultGenerator() {
        return new TokenGenerator(new SecureRandom());
    }

    private SecureRandom getSecureRandom() {
        return secureRandom;
    }

    private void setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }
}