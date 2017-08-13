package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "fivejob")
public class FivejobBean {
    @Id
    private Integer oid;

    private String address;

    private String companyname;

    private String companytype;

    private String fuli;

    private String gzjy;

    private String industry;

    private String jobname;

    private String lat;

    private String lng;

    private String money;

    private String peoplecount;

    private String publishdate;

    private String xueli;

    @Column(name = "compeny_geog")
    private Object compenyGeog;

    /**
     * @return oid
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * @param oid
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    /**
     * @return companytype
     */
    public String getCompanytype() {
        return companytype;
    }

    /**
     * @param companytype
     */
    public void setCompanytype(String companytype) {
        this.companytype = companytype == null ? null : companytype.trim();
    }

    /**
     * @return fuli
     */
    public String getFuli() {
        return fuli;
    }

    /**
     * @param fuli
     */
    public void setFuli(String fuli) {
        this.fuli = fuli == null ? null : fuli.trim();
    }

    /**
     * @return gzjy
     */
    public String getGzjy() {
        return gzjy;
    }

    /**
     * @param gzjy
     */
    public void setGzjy(String gzjy) {
        this.gzjy = gzjy == null ? null : gzjy.trim();
    }

    /**
     * @return industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * @return jobname
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * @param jobname
     */
    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    /**
     * @return lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * @return lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * @return money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    /**
     * @return peoplecount
     */
    public String getPeoplecount() {
        return peoplecount;
    }

    /**
     * @param peoplecount
     */
    public void setPeoplecount(String peoplecount) {
        this.peoplecount = peoplecount == null ? null : peoplecount.trim();
    }

    /**
     * @return publishdate
     */
    public String getPublishdate() {
        return publishdate;
    }

    /**
     * @param publishdate
     */
    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate == null ? null : publishdate.trim();
    }

    /**
     * @return xueli
     */
    public String getXueli() {
        return xueli;
    }

    /**
     * @param xueli
     */
    public void setXueli(String xueli) {
        this.xueli = xueli == null ? null : xueli.trim();
    }

    /**
     * @return compeny_geog
     */
    public Object getCompenyGeog() {
        return compenyGeog;
    }

    /**
     * @param compenyGeog
     */
    public void setCompenyGeog(Object compenyGeog) {
        this.compenyGeog = compenyGeog;
    }
}