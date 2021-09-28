package com.example.a15squares;
//class made to hold all the values of a number
public class Numbers {
    //instance variables
    private int num;
    private int posX;
    private int posY;
    private int arrayX;
    private int arrayY;
    private String numString;
    //constructor to assign the number, X position, and Y position to an object
    public Numbers(int num, int x, int y, int i, int j){
        this.num = num;
        posX = x;
        posY = y;
        arrayY = i;
        arrayX = j;
        numString = String.valueOf(num);
    }
    //setter function for the number which updates the string
    public void setNum(int x){
        num = x;
        numString = String.valueOf(num);
    }
    //getter functions for each variable
    public int getNum() {
        return num;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getArrayX() {
        return arrayX;
    }

    public int getArrayY() {
        return arrayY;
    }

    public String getNumString() {
        return numString;
    }
}

