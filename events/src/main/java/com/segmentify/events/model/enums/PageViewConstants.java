package com.segmentify.events.model.enums;

public enum PageViewConstants {
    HOME_PAGE("Home Page"),
    PRODUCT_PAGE("Product Page"),
    CATEGORY_PAGE("Category Page"),
    BASKET_PAGE("Basket Page"),
    CHECKOUT_PAGE("Checkout Page"),
    SEARCH_PAGE("Search Page"),
    PAGE_NOT_FOUND("404 Page");

    private String resultCode;

    PageViewConstants(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }
}
