package com.awareness.shopping.mockdata;

import com.awareness.shopping.entity.ShoppingMall;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMallData {
    private static final List<ShoppingMall> shoppingMallList = new ArrayList<>();

    static {
        ShoppingMall shoppingMall_A = new ShoppingMall("shoppingMall A",
                22.4943, 113.7436, 200);
        shoppingMallList.add(shoppingMall_A);
    }

    public static List<ShoppingMall> getMockData() {
        return shoppingMallList;
    }
}
