package com.flink.automation.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonUtils {

    /**
     * Get index of least expensive product price
     * @param productPriceList list of product price
     * @return integer
     */
    public static int getLeastExpensiveProductIndex(List<String> productPriceList) {
        List<Integer> priceList = new ArrayList<>();
        for(String price : productPriceList) {
            priceList.add(Integer.parseInt(price.trim().replaceAll("[^\\d]", "").replace("\n", "").
                    replace("\r", "")));
        }

       return priceList.indexOf(Collections.min(priceList));
    }
}
