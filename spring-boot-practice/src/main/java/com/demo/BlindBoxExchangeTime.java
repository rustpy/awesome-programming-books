package com.demo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created In Ctrip.
 *
 * @author: z_yong
 * @date: Create at: 2021年2月24日上午0:00:00
 */
public class BlindBoxExchangeTime {

    private Integer ruleid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Integer id;

    /**
     * 日期类型 0:盲盒兑换日期    1:盲盒活动日期
     */
    private Integer dateType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
