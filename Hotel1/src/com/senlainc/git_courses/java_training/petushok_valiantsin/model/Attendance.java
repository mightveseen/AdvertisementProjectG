package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.AttendanceIndex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attendance")
public class Attendance {
    @XmlElement(name = "id")
    private final int id;
    @XmlElement(name = "name")
    private final String name;
    @XmlElement(name = "section")
    private final String section;
    @XmlElement(name = "price")
    private double price;

    public Attendance(Attendance attendance) {
        this.id = attendance.getId();
        this.name = attendance.getName();
        this.price = attendance.getPrice();
        this.section = attendance.getSection();
    }

    public Attendance(String name, String section, double price) {
        this.id = new AttendanceIndex().getIndex();
        this.name = name;
        this.section = section;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSection() {
        return this.section;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ")" + name + ", " + section + ", " + price;
    }
}
