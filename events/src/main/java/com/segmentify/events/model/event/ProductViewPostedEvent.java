package com.segmentify.events.model.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductViewPostedEvent extends BaseEvent {
    private String productId;
    private String title;
    private String inStock;
    private String url;
    private String mUrl;
    private String image;
    private String imageXS;
    private String imageS;
    private String imageM;
    private String imageL;
    private String imageXL;
    private String category;
    private String brand;
    private String price;
    private String oldPrice;
    private String currency;
    private String gender;
    private String colors;
    private String sizes;
    private String labels;
}
