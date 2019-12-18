package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.AttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

import java.util.Comparator;

public class AttendanceService implements IAttendanceService {
    private final IAttendanceDao attendanceDao;
    private final Comparator<Attendance> SORT_BY_SECTION = Comparator.comparing(Attendance::getSection);
    private final Comparator<Attendance> SORT_BY_PRICE = Comparator.comparing(firstAttendance -> String.valueOf(firstAttendance.getPrice()));

    public AttendanceService(IAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    public void add(Attendance attendance) {
        attendanceDao.create(attendance);
    }

    @Override
    public void delete(int index) {
        if(attendanceDao.readAll().size() < index) {
            System.err.println("Attendance with index: " + index + " dont exists.");
            return;
        }
        attendanceDao.delete(index);
    }

    @Override
    public double getPrice(int index) {
        return attendanceDao.read(index).getPrice();
    }

    @Override
    public Attendance get(int index) {
        return attendanceDao.read(index);
    }

    @Override
    public void changePrice(int index, double price) {
        Attendance attendance = new Attendance(attendanceDao.read(index));
        attendance.setPrice(price);
        attendanceDao.update(attendance);
    }

    @Override
    public void sort(String parameter) {
        switch (parameter) {
            case "section":
                sortBySection();
                break;
            case "price":
                sortByPrice();
                break;
        }
    }

    private void sortBySection() {
        MyList<Attendance> myList = new MyList<>();
        for (int i = 0; i < attendanceDao.readAll().size(); i++) {
            myList.add(attendanceDao.read(i));
        }
        myList.sort(SORT_BY_SECTION);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i));
        }
    }

    private void sortByPrice() {
        MyList<Attendance> myList = new MyList<>();
        for (int i = 0; i < attendanceDao.readAll().size(); i++) {
            myList.add(attendanceDao.read(i));
        }
        myList.sort(SORT_BY_PRICE);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i));
        }
    }
}
