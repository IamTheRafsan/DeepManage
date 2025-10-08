package com.digitalrangersbd.DeepManage.Global;

import org.springframework.stereotype.Component;

@Component
public class GlobalVariables {

    private String ShopName = "DeepManage";

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }
}
