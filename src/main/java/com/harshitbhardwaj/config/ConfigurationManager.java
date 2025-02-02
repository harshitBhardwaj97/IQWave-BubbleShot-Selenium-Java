package com.harshitbhardwaj.config;

import org.aeonbits.owner.ConfigCache;

/**
 * @author Harshit Bhardwaj
 */
public class ConfigurationManager {

    private ConfigurationManager() {
        throw new AssertionError("Can't instantiate ConfigurationManager class");
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}