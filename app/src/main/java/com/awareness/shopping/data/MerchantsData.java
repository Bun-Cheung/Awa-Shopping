package com.awareness.shopping.data;

import com.awareness.shopping.R;
import com.awareness.shopping.entity.Merchant;

import java.util.ArrayList;
import java.util.List;

public class MerchantsData {
    private static List<Merchant> merchants = new ArrayList<>();

    static {
        Merchant merchantCoffee = new Merchant("Kakaxi", R.drawable.ic_coffee, R.drawable.background_coffee);
        Merchant merchantCotton = new Merchant("Pure cotton home textile", R.drawable.ic_cotton, R.drawable.background_cotton);
        Merchant merchantFlower = new Merchant("Xunmeihe", R.drawable.ic_flowers, R.drawable.background_flower);
        Merchant merchantHamburger = new Merchant("Maikenken", R.drawable.ic_hamburger, R.drawable.background_hamburger);

        merchants.add(merchantCoffee);
        merchants.add(merchantCotton);
        merchants.add(merchantFlower);
        merchants.add(merchantHamburger);
    }

    public static List<Merchant> getData() {
        return merchants;
    }
}
