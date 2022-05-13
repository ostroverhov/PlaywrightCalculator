package browser;

import utils.Logger;

public class IllegalBrowserNameException extends IllegalArgumentException {

    private static final Logger logger = Logger.getInstance();

    public IllegalBrowserNameException() {
        logger.error("Wrong browser name. Select browser chrome / firefox");
    }
}
