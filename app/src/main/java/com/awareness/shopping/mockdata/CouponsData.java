package com.awareness.shopping.mockdata;

import com.awareness.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class CouponsData {
    private static List<Integer> couponList = new ArrayList<>();

    static {
        couponList.add(R.drawable.coupons_flower_en);
        couponList.add(R.drawable.coupons_coffee_en);
        couponList.add(R.drawable.coupons_cotton_en);
        couponList.add(R.drawable.coupons_hamburger_en);
    }

    public static List<Integer> getMockData() {
        return couponList;
    }
}
