package com.calllog.main;

import java.time.LocalDateTime;
import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        CallLogManager manager = new CallLogManager();

        // Adding calls with slight delay to show different times
        manager.addCall(new Call("9876543210", 120, CallType.INCOMING, LocalDateTime.now()));
        Thread.sleep(1000);

        manager.addCall(new Call("9123456780", 60, CallType.OUTGOING, LocalDateTime.now()));
        Thread.sleep(1000);

        manager.addCall(new Call("9988776655", 0, CallType.MISSED, LocalDateTime.now()));

        System.out.println("ALL CALLS:");
        manager.displayAllCalls();

        System.out.println("\nINCOMING CALLS:");
        manager.displayByType(CallType.INCOMING);
    }
}