package com.android.erlcarter.android_quickfit_master.data;

public class ShopItem {
    private int shopItemImg;
    private String shopItemTitle;
    private String shopItemSignature;
    private String shopItemRealPrice;
    private String shopItemOriginalPrice;

    public ShopItem() {}

    public ShopItem(int shopItemImg, String shopItemTitle, String shopItemSignature, String shopItemRealPrice, String shopItemOriginalPrice) {
        this.shopItemImg = shopItemImg;
        this.shopItemTitle = shopItemTitle;
        this.shopItemSignature = shopItemSignature;
        this.shopItemRealPrice = shopItemRealPrice;
        this.shopItemOriginalPrice = shopItemOriginalPrice;
    }

    public int getShopItemImg() {
        return shopItemImg;
    }

    public void setShopItemImg(int shopItemImg) {
        this.shopItemImg = shopItemImg;
    }

    public String getShopItemTitle() {
        return shopItemTitle;
    }

    public void setShopItemTitle(String shopItemTitle) {
        this.shopItemTitle = shopItemTitle;
    }

    public String getShopItemSignature() {
        return shopItemSignature;
    }

    public void setShopItemSignature(String shopItemSignature) {
        this.shopItemSignature = shopItemSignature;
    }

    public String getShopItemRealPrice() {
        return shopItemRealPrice;
    }

    public void setShopItemRealPrice(String shopItemRealPrice) {
        this.shopItemRealPrice = shopItemRealPrice;
    }

    public String getShopItemOriginalPrice() {
        return shopItemOriginalPrice;
    }

    public void setShopItemOriginalPrice(String shopItemOriginalPrice) {
        this.shopItemOriginalPrice = shopItemOriginalPrice;
    }
}
