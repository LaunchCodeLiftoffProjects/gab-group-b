package com.liftoff.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Link extends AbstractEntity{


    @ManyToOne
    @JoinColumn (name = "wantTo_id")
    private WantTo wantTo;

    @ManyToOne
    @JoinColumn (name = "faq_id")
    private Faq faq;

    private String name;
    private String url;
    private String guideStarUrl;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(columnDefinition = "TINYINT")
    private Boolean hidden;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(columnDefinition = "TINYINT")
    private Boolean displayOnWelcomePage;

    public Link(){}

    public Link(WantTo wantTo, String name, String url, Faq faq) {
        this.wantTo = wantTo;
        this.name = name;
        this.url = url;
        this.faq = faq;
    }

    public Link(String name, String url, Faq faq) {
        this.name = name;
        this.url = url;
        this.faq = faq;
    }

    public Link(WantTo wantTo, String name, String url) {
        this.wantTo = wantTo;
        this.name = name;
        this.url = url;
    }

    public WantTo getWantTo() {
        return wantTo;
    }

    public void setWantTo(WantTo wantTo) {
        this.wantTo = wantTo;
    }

    public Faq getFaq() {
        return faq;
    }

    public void setFaq(Faq faq) {
        this.faq = faq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGuideStarUrl() {
        return guideStarUrl;
    }

    public void setGuideStarUrl(String guideStarUrl) {
        this.guideStarUrl = guideStarUrl;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getDisplayOnWelcomePage() {
        return displayOnWelcomePage;
    }

    public void setDisplayOnWelcomePage(Boolean displayOnWelcomePage) {
        this.displayOnWelcomePage = displayOnWelcomePage;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
