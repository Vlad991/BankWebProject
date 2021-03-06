package com.bank.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({"day", "month", "year"})
public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        this(1, 1, 1900);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        String[] dateParse = date.split("/");
        this.day = Integer.parseInt(dateParse[0]);
        this.month = Integer.parseInt(dateParse[1]);
        this.year = Integer.parseInt(dateParse[2]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        String day;
        String month;
        String year;

        day = Integer.toString(this.day);
        month = Integer.toString(this.month);
        year = Integer.toString(this.year);

        if (this.day < 10) {
            day = "0" + day;
        }
        if (this.month < 10) {
            month = "0" + month;
        }

        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
