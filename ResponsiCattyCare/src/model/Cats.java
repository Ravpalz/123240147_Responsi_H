/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Cats extends Catssssss {
    private int days;
    private int fee;
    
    /**
     *
     * @param name
     * @param owner
     * @param number
     * @param days
     * @param fee
     */
    public Cats(String name, String owner, int number, int days, int fee){
    super(name, owner, number);
        this.days = days;
        CalculateFee();
    }
    public String getName() { return name; }
    public String getOwner() { return owner; }
    public int getNumber() { return number; }
    public int getDays() { return days; }
    public int getFee() { return fee; }
    
    private void CalculateFee(){
    if(days <= 2){
    this.fee = (40000 * this.days);
    }
    else{
    this.fee = (80000 + (30000 * (this.days - 2)));
    }
    }
}
