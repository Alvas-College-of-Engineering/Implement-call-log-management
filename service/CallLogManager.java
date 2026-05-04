package com.calllog.service;

import java.util.ArrayList;
import com.calllog.model.Call;
import com.calllog.util.CallType;

public class CallLogManager {

    private ArrayList<Call> callList;

    // Constructor
    public CallLogManager() {
        callList = new ArrayList<>();
    }

    // Add call
    public void addCall(Call call) {
        callList.add(call);
    }

    // Display all calls
    public void displayAllCalls() {
        if (callList.isEmpty()) {
            System.out.println("No call history found.");
            return;
        }

        for (Call call : callList) {
            call.display();
        }
    }

    // Display calls by type
    public void displayByType(CallType type) {
        boolean found = false;

        for (Call call : callList) {
            if (call.getType() == type) {
                call.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No " + type + " calls found.");
        }
    }
}