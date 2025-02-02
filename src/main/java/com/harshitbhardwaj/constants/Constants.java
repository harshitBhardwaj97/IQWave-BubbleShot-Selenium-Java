package com.harshitbhardwaj.constants;

/**
 * @author Harshit Bhardwaj
 */
public final class Constants {

    private Constants() {
        throw new AssertionError("Can't instantiate Constants class");
    }

    public static class Common {
        public static final String BASE_URL = "https://focus-check-lkm-001.web.app/";
        public static final int EXPLICIT_LONG_WAIT = 10;
        public static final int EXPLICIT_SHORT_WAIT = 5;

        private Common() {
            throw new AssertionError("Can't instantiate Constants.Common class");
        }
    }
}
