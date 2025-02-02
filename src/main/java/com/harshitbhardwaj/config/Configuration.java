package com.harshitbhardwaj.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;


/**
 * @author Harshit Bhardwaj
 */
@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties", "classpath:general.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();

    @Key("headless")
    @DefaultValue("false")
    Boolean headless();
}
