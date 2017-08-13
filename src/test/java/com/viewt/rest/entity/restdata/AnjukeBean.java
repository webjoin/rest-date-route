package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "anjuke")
public class AnjukeBean {
    @Id
    private Integer oid;

    private String reqlisturl;

    private String reqdetailurl;

    private Integer htotal;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String addr;

    private String enddate;

    private String lng;

    private String lat;

    private Double avg;

    private Double trend;

    private String wytype;

    private String wyfee;

    private String wyarea;

    private String houseqty;

    private String wybuildtime;

    private String parkingnum;

    private String wyparkingnum;

    private String volume;

    private String green;

    private String developer;

    private String wycompany;

    private Integer salenum;

    private Integer rentnum;

    private Integer page;

    private Integer totalpage;

    @Column(name = "house_geog")
    private Object houseGeog;

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
     * @return reqlisturl
     */
    public String getReqlisturl() {
        return reqlisturl;
    }

    /**
     * @param reqlisturl
     */
    public void setReqlisturl(String reqlisturl) {
        this.reqlisturl = reqlisturl == null ? null : reqlisturl.trim();
    }

    /**
     * @return reqdetailurl
     */
    public String getReqdetailurl() {
        return reqdetailurl;
    }

    /**
     * @param reqdetailurl
     */
    public void setReqdetailurl(String reqdetailurl) {
        this.reqdetailurl = reqdetailurl == null ? null : reqdetailurl.trim();
    }

    /**
     * @return htotal
     */
    public Integer getHtotal() {
        return htotal;
    }

    /**
     * @param htotal
     */
    public void setHtotal(Integer htotal) {
        this.htotal = htotal;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * @return enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * @param enddate
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
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
     * @return avg
     */
    public Double getAvg() {
        return avg;
    }

    /**
     * @param avg
     */
    public void setAvg(Double avg) {
        this.avg = avg;
    }

    /**
     * @return trend
     */
    public Double getTrend() {
        return trend;
    }

    /**
     * @param trend
     */
    public void setTrend(Double trend) {
        this.trend = trend;
    }

    /**
     * @return wytype
     */
    public String getWytype() {
        return wytype;
    }

    /**
     * @param wytype
     */
    public void setWytype(String wytype) {
        this.wytype = wytype == null ? null : wytype.trim();
    }

    /**
     * @return wyfee
     */
    public String getWyfee() {
        return wyfee;
    }

    /**
     * @param wyfee
     */
    public void setWyfee(String wyfee) {
        this.wyfee = wyfee == null ? null : wyfee.trim();
    }

    /**
     * @return wyarea
     */
    public String getWyarea() {
        return wyarea;
    }

    /**
     * @param wyarea
     */
    public void setWyarea(String wyarea) {
        this.wyarea = wyarea == null ? null : wyarea.trim();
    }

    /**
     * @return houseqty
     */
    public String getHouseqty() {
        return houseqty;
    }

    /**
     * @param houseqty
     */
    public void setHouseqty(String houseqty) {
        this.houseqty = houseqty == null ? null : houseqty.trim();
    }

    /**
     * @return wybuildtime
     */
    public String getWybuildtime() {
        return wybuildtime;
    }

    /**
     * @param wybuildtime
     */
    public void setWybuildtime(String wybuildtime) {
        this.wybuildtime = wybuildtime == null ? null : wybuildtime.trim();
    }

    /**
     * @return parkingnum
     */
    public String getParkingnum() {
        return parkingnum;
    }

    /**
     * @param parkingnum
     */
    public void setParkingnum(String parkingnum) {
        this.parkingnum = parkingnum == null ? null : parkingnum.trim();
    }

    /**
     * @return wyparkingnum
     */
    public String getWyparkingnum() {
        return wyparkingnum;
    }

    /**
     * @param wyparkingnum
     */
    public void setWyparkingnum(String wyparkingnum) {
        this.wyparkingnum = wyparkingnum == null ? null : wyparkingnum.trim();
    }

    /**
     * @return volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume
     */
    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    /**
     * @return green
     */
    public String getGreen() {
        return green;
    }

    /**
     * @param green
     */
    public void setGreen(String green) {
        this.green = green == null ? null : green.trim();
    }

    /**
     * @return developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * @param developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    /**
     * @return wycompany
     */
    public String getWycompany() {
        return wycompany;
    }

    /**
     * @param wycompany
     */
    public void setWycompany(String wycompany) {
        this.wycompany = wycompany == null ? null : wycompany.trim();
    }

    /**
     * @return salenum
     */
    public Integer getSalenum() {
        return salenum;
    }

    /**
     * @param salenum
     */
    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }

    /**
     * @return rentnum
     */
    public Integer getRentnum() {
        return rentnum;
    }

    /**
     * @param rentnum
     */
    public void setRentnum(Integer rentnum) {
        this.rentnum = rentnum;
    }

    /**
     * @return page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return totalpage
     */
    public Integer getTotalpage() {
        return totalpage;
    }

    /**
     * @param totalpage
     */
    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    /**
     * @return house_geog
     */
    public Object getHouseGeog() {
        return houseGeog;
    }

    /**
     * @param houseGeog
     */
    public void setHouseGeog(Object houseGeog) {
        this.houseGeog = houseGeog;
    }
}