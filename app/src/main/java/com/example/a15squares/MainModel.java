package com.example.a15squares;
//model class
public class MainModel {
    //variables that will be called by either controller or view
    public int boardSize = 4;
    public int maxBoardSize = 10;
    public int minBoardSize = 2;
    public int outerSquareSize = 150;
    public int totalBoxes = boardSize*boardSize;
    public Numbers[][] numberArray = new Numbers[boardSize][boardSize];
    public float pressX = 0;
    public float pressY = 0;
    public int totalBoardSize = outerSquareSize*(boardSize+1);
    public boolean error = false;
    public boolean win = false;

}
