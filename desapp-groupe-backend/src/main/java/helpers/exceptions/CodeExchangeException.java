package helpers.exceptions;

public class CodeExchangeException extends GetCredentialsException {

/**
 * Construct a CodeExchangeException.
 *
 * @param authorizationUrl The authorization URL to redirect the user to.
 */
public CodeExchangeException(String authorizationUrl) {
        super(authorizationUrl);
        }

        }