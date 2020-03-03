package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.AttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.GuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.OrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.RoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.AttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.GuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.OrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.RoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

public class LoadData {
    private LoadData() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute() {
        /** Serialization initialization */
        DependencyController.getInstance().setDependency(Serialization.class);
        /** DAO initialization */
        DependencyController.getInstance().setDependency(AttendanceDao.class);
        DependencyController.getInstance().setDependency(GuestDao.class);
        DependencyController.getInstance().setDependency(RoomDao.class);
        DependencyController.getInstance().setDependency(OrderDao.class);
        /** Service's initialization */
        DependencyController.getInstance().setDependency(AttendanceService.class);
        DependencyController.getInstance().setDependency(RoomService.class);
        DependencyController.getInstance().setDependency(GuestService.class);
        DependencyController.getInstance().setDependency(OrderService.class);
        DependencyController.getInstance().setDependency(ConnectionManager.class);
        /** Controller initialization */
        DependencyController.getInstance().setDependency(Hotel.class);
        /** Load data from XML files */
    }
}
