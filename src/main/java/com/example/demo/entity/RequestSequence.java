package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "request_sequence")
public class RequestSequence {
    @Id
    private Integer year;
    @Version
    private Long version;
    @Column(nullable = false)
    private Integer nextRunningNumber;
    public Integer getYear(){return year;} public void setYear(Integer year){this.year=year;}
    public Integer getNextRunningNumber(){return nextRunningNumber;} public void setNextRunningNumber(Integer nextRunningNumber){this.nextRunningNumber=nextRunningNumber;}
}
