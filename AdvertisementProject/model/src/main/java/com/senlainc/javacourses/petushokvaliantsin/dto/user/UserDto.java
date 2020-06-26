package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCommentDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.ChatDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.chat.MessageDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {

    //TODO : Validation not working while add Advertisement
    @Null(groups = {Create.class})
    @Positive(groups = {Update.class, AdvertisementDto.Update.class, AdvertisementDto.Create.class,
            AdvertisementCommentDto.Create.class, AdvertisementCommentDto.Update.class,
            ChatDto.Create.class, ChatDto.Update.class, MessageDto.Create.class})
    @NotNull(groups = {Update.class, AdvertisementDto.Update.class, AdvertisementDto.Create.class,
            AdvertisementCommentDto.Create.class, AdvertisementCommentDto.Update.class,
            ChatDto.Create.class, ChatDto.Update.class, MessageDto.Create.class})
    private Long id;
    @NotNull(groups = {Create.class, Update.class})
    private String firstName;
    @NotNull(groups = {Create.class, Update.class})
    private String lastName;
    @NotNull(groups = {Create.class, Update.class})
    private String email;
    @NotNull(groups = {Create.class, Update.class})
    private Integer phone;
    @Null(groups = {Create.class, Update.class})
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
    @Null(groups = {Create.class, Update.class})
    private Float rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public interface Create {

    }

    public interface Update {

    }
}
