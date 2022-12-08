/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.entity;

/**
 *
 * @author blackundo
 */
public class ModelData {
    private String month;
    private int amount;
    private int amountService;

    public ModelData() {
    }

    public ModelData(String month, int amount, int amountService) {
        this.month = month;
        this.amount = amount;
        this.amountService = amountService;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAmountService() {
        return amountService;
    }

    public void setAmountService(int amountService) {
        this.amountService = amountService;
    }
    
    
}
