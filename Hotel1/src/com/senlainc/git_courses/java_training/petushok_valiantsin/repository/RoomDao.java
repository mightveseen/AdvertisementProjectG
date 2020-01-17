package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDao implements IRoomDao {
    private final List<Room> roomList = new ArrayList<>();

    @Override
    public void create(Room room) {
        roomList.add(room);
    }

    @Override
    public void delete(int index) {
        roomList.remove(roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new));
    }

    @Override
    public void update(Room room) {
        roomList.set(roomList.indexOf(room), room);
    }

    @Override
    public List<Room> readAll() {
        return roomList;
    }

    @Override
    public Room read(int index) {
        return roomList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new);
    }
}
