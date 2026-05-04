package com.calllog.ui;

import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller Class - Manages business logic for UI
 * Follows MVC design pattern
 */
public class CallLogController {

    private CallLogManager manager;

    public CallLogController(CallLogManager manager) {
        this.manager = manager;
    }

    /**
     * Add a new call
     */
    public void addCall(String phoneNumber, int duration, CallType type) {
        Call newCall = new Call(phoneNumber, duration, type, LocalDateTime.now());
        manager.addCall(newCall);
    }

    /**
     * Get all calls
     */
    public List<Call> getAllCalls() {
        return getCallListFromManager();
    }

    /**
     * Get calls by type
     */
    public List<Call> getCallsByType(CallType type) {
        List<Call> filteredCalls = new ArrayList<>();
        for (Call call : getCallListFromManager()) {
            if (call.getType() == type) {
                filteredCalls.add(call);
            }
        }
        return filteredCalls;
    }

    /**
     * Get statistics
     */
    public CallStatistics getStatistics() {
        List<Call> allCalls = getCallListFromManager();
        int total = allCalls.size();
        int incoming = 0, outgoing = 0, missed = 0;

        for (Call call : allCalls) {
            if (call.getType() == CallType.INCOMING)
                incoming++;
            else if (call.getType() == CallType.OUTGOING)
                outgoing++;
            else if (call.getType() == CallType.MISSED)
                missed++;
        }

        return new CallStatistics(total, incoming, outgoing, missed);
    }

    /**
     * Delete a specific call
     */
    public void deleteCall(int index) {
        List<Call> calls = getCallListFromManager();
        if (index >= 0 && index < calls.size()) {
            calls.remove(index);
        }
    }

    /**
     * Clear all calls
     */
    public void clearAllCalls() {
        getCallListFromManager().clear();
    }

    /**
     * Helper method to extract call list from manager
     */
    private List<Call> getCallListFromManager() {
        try {
            java.lang.reflect.Field field = CallLogManager.class.getDeclaredField("callList");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ArrayList<Call> callList = (ArrayList<Call>) field.get(manager);
            return callList;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Inner class to hold statistics
     */
    public static class CallStatistics {
        public int total;
        public int incoming;
        public int outgoing;
        public int missed;

        public CallStatistics(int total, int incoming, int outgoing, int missed) {
            this.total = total;
            this.incoming = incoming;
            this.outgoing = outgoing;
            this.missed = missed;
        }
    }
}
