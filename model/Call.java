package com.calllog.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.calllog.util.CallType;

public class Call {

    private String phoneNumber;
    private int duration;
    private CallType type;
    private LocalDateTime time;

    // Constructor
    public Call(String phoneNumber, int duration, CallType type, LocalDateTime time) {
        this.phoneNumber = phoneNumber;
        this.duration = duration;
        this.type = type;
        this.time = time;
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getDuration() {
        return duration;
    }

    public CallType getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    // Display method
    public void display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Number   : " + phoneNumber);
        System.out.println("Type     : " + type);
        System.out.println("Duration : " + duration + " sec");
        System.out.println("Time     : " + time.format(formatter));
        System.out.println("----------------------------");
    }
}