package com.j2ee.server.vo;

import com.j2ee.server.entity.Offers;

/**
 * @Author: 许杨
 * @Description:
 * @Date: 2019/2/28
 */
public class OfferInfoVO {
    private String id;
    private String description ;        // 描述
    private String start;            // 开始时间
    private String end;              // 结束时间

    public OfferInfoVO() {
    }

    public OfferInfoVO(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public OfferInfoVO(String id, String description, String start, String end) {
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public OfferInfoVO(Offers offer) {
        this.id = offer.getId();
        this.description = offer.getDescription();
        this.start = offer.getStart().toString();
        this.end = offer.getEnd().toString();
    }

    public void print() {
        System.out.println("OfferInfoVO:");
        System.out.println("id: "+id);
        System.out.println("description: "+description);
        System.out.println("start: "+start);
        System.out.println("end: "+end);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
