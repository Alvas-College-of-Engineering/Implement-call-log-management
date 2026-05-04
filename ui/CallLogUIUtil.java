package com.calllog.ui;

import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Call Log UI
 * Provides helper methods for data manipulation and filtering
 */
public class CallLogUIUtil {

    /**
     * Get the call list from CallLogManager using Reflection
     */
    public static List<Call> extractCallList(CallLogManager manager) {
        try {
            java.lang.reflect.Field field = CallLogManager.class.getDeclaredField("callList");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ArrayList<Call> callList = (ArrayList<Call>) field.get(manager);
            return new ArrayList<>(callList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Filter calls by type
     */
    public static List<Call> filterByType(List<Call> calls, CallType type) {
        List<Call> filtered = new ArrayList<>();
        for (Call call : calls) {
            if (call.getType() == type) {
                filtered.add(call);
            }
        }
        return filtered;
    }

    /**
     * Filter calls by phone number
     */
    public static List<Call> filterByPhoneNumber(List<Call> calls, String phoneNumber) {
        List<Call> filtered = new ArrayList<>();
        for (Call call : calls) {
            if (call.getPhoneNumber().contains(phoneNumber)) {
                filtered.add(call);
            }
        }
        return filtered;
    }

    /**
     * Calculate total duration of calls
     */
    public static int calculateTotalDuration(List<Call> calls) {
        int total = 0;
        for (Call call : calls) {
            total += call.getDuration();
        }
        return total;
    }

    /**
     * Get average call duration
     */
    public static double getAverageCallDuration(List<Call> calls) {
        if (calls.isEmpty())
            return 0;
        return (double) calculateTotalDuration(calls) / calls.size();
    }

    /**
     * Sort calls by date (oldest first)
     */
    public static List<Call> sortByDate(List<Call> calls) {
        List<Call> sorted = new ArrayList<>(calls);
        sorted.sort((c1, c2) -> c1.getTime().compareTo(c2.getTime()));
        return sorted;
    }

    /**
     * Sort calls by duration (descending)
     */
    public static List<Call> sortByDuration(List<Call> calls) {
        List<Call> sorted = new ArrayList<>(calls);
        sorted.sort((c1, c2) -> Integer.compare(c2.getDuration(), c1.getDuration()));
        return sorted;
    }
}
