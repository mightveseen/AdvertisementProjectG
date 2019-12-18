package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Served;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

import java.util.Comparator;

public class OrderService implements IOrderService {
    private final IOrderDao orderDao;
    private final IRoomService roomService;
    private final IGuestService guestService;
    private final IAttendanceService attendanceService;
    private final Comparator<Order> SORT_BY_DATE = Comparator.comparing(Order::getEndDate);

    public OrderService(IOrderDao orderDao, IRoomService roomService, IGuestService guestService, IAttendanceService attendanceService) {
        this.orderDao = orderDao;
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
    }

    @Override
    public void add(Order order) {
        if (roomService.getStatus(order.getRoomIndex()) instanceof Served || roomService.getStatus(order.getRoomIndex()) instanceof Rented) {
            System.err.println("Room now is not available");
            return;
        }
        orderDao.create(order);
        orderDao.read(orderDao.readAll().size()).setPrice(roomService.getPrice(order.getRoomIndex()));
        roomService.changeStatus(order.getRoomIndex(), new Rented(order.getEndDate()));
    }

    @Override
    public void delete(int index) {
        if(orderDao.readAll().size() < index) {
            System.err.println("Order with index: " + index + " dont exists.");
            return;
        }
        orderDao.delete(index);
        roomService.changeStatus(index, new Free());
    }

    @Override
    public void show() {
        for (int i = 1; i <= orderDao.readAll().size(); i++) {
            System.out.println(guestService.getGuest(orderDao.read(i).getGuestIndex()) + "\t" + roomService.getRoom(orderDao.read(i).getRoomIndex())
                    + "\nTotal amount: " + orderDao.read(i).getPrice());
        }
    }

    @Override
    public void show(MyList<Order> myList) {
        for (int i = 1; i <= myList.size(); i++) {
            System.out.println(guestService.getGuest(myList.get(i - 1).getGuestIndex()) + "\t" + roomService.getRoom(myList.get(i - 1).getRoomIndex())
                    + "\nTotal amount: " + myList.get(i - 1).getPrice());
        }
    }

    @Override
    public void showGuestRoom(int index) {
        int counter = 0;
        for (int i = 1; i <= orderDao.readAll().size(); i++) {
            if (orderDao.read(i).getGuestIndex() == index) {
                System.out.println(guestService.getGuest(orderDao.read(i).getGuestIndex()) + "\t" + roomService.getRoom(orderDao.read(i).getRoomIndex()));
                counter++;
            }
            if (counter == 3) {
                return;
            }
        }
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        Order order = new Order(orderDao.read(orderIndex));
        MyList<Integer> myList = new MyList<>();
        System.arraycopy(myList.get(), 0, order.getAttendanceIndex().get(), 0, order.getAttendanceIndex().size());
        myList.add(attendanceIndex);
        order.setAttendanceIndex(myList);
        double price = order.getPrice() + attendanceService.getPrice(attendanceIndex);
        order.setPrice(price);
        orderDao.update(order);
    }

    @Override
    public MyList<Order> sort(String parameter) {
        MyList<Order> myList = new MyList<>();
        switch (parameter) {
            case "date":
                sortByDate(myList);
                return myList;
            case "alphabet":
                sortByAlphabet(myList);
                return myList;
        }
        return null;
    }

    private void createBufList(MyList<Order> myList) {
        for (int i = 1; i <= orderDao.readAll().size(); i++) {
            myList.add(orderDao.read(i));
        }
    }

    private void sortByDate(MyList<Order> myList) {
        createBufList(myList);
        myList.sort(SORT_BY_DATE);
    }

    private void sortByAlphabet(MyList<Order> myList) {
        int[] guestIndex = guestService.sortByAlphabet();
        for(int index : guestIndex) {
            myList.add(orderDao.read(index));
        }
    }

    public void showAttendance(int orderIndex) {
        try{
            Object[] attendanceIndex = orderDao.read(orderIndex).getAttendanceIndex().get();
            System.out.print(guestService.getGuest(orderDao.read(orderIndex).getGuestIndex()));
            for(Object index : attendanceIndex) {
                if(index == null) {
                    break;
                }
                System.out.print(attendanceService.get((int)index));
            }
        }catch (NullPointerException e) {
            System.out.println("This guest didn't have attendance's");
        }

    }
}
