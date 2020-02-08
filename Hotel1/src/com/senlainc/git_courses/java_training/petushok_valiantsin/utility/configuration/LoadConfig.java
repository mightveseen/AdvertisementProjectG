package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
public class LoadConfig {
    private static final Logger LOGGER = Logger.getLogger(LoadConfig.class.getName());
    @ConfigProperty(configName = "Load")
    private static String ATTENDANCE_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String GUEST_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String ROOM_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String ORDER_DAO_PATH;
    private static LoadConfig instance;

    private LoadConfig() {
        try {
            ConfigController.getInstance().letsRock(this);
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, "Could load program resources.properties from file: " + e.toString(), e);
        }
    }

    public static LoadConfig getInstance() {
        if (instance == null) {
            instance = new LoadConfig();
        }
        return instance;
    }

    public String getAttendanceDaoPath() {
        return ATTENDANCE_DAO_PATH;
    }

    public String getGuestDaoPath() {
        return GUEST_DAO_PATH;
    }

    public String getRoomDaoPath() {
        return ROOM_DAO_PATH;
    }

    public String getOrderDaoPath() {
        return ORDER_DAO_PATH;
    }
}
