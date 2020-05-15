package com.awareness.shopping.data;

import java.util.ArrayList;
import java.util.List;

public class CouponInfo {
    private static List<Integer> couponList = new ArrayList<>();

    static {

    }

    public static List<Integer> getCouponList() {
        return couponList;
    }
}
