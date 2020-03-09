package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.annotation.ConfigProperty;

@ConfigClass
public class RoomConfig {
    private static final boolean CHANGE_STATUS_BASIC_VALUE = true;
    @ConfigProperty(configName = "Room")
    private static boolean CHANGE_STATUS_VALUE;

    private RoomConfig() {
        ConfigController.getInstance().setConfig(RoomConfig.class);
    }

    public boolean getChangeStatus() {
        if (String.valueOf(CHANGE_STATUS_VALUE).equals("true") || String.valueOf(CHANGE_STATUS_VALUE).equals("false")) {
            return CHANGE_STATUS_VALUE;
        }
        return CHANGE_STATUS_BASIC_VALUE;
    }
}
