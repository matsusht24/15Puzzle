package com.example.a15squares;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

//controller class
public class MainController implements View.OnClickListener, View.OnTouchListener{
    private MainView myView;
    private MainModel myModel;
    private Random rand;
    public MainController(MainView tempView){
        myView = tempView;
        myModel = myView.getMyModel();
        rand = new Random();


    }
    //this function will randomize the numbers and assign them position values.
    public void randomizer(){
        //creates an array of the possible numbers
        int[] posNum = new int[myModel.totalBoxes];
        //assign the variables to the array
        for(int i = 0; i < myModel.totalBoxes; i++){
            posNum[i]= i + 1;
        }
        for(int i = 1; i < myModel.boardSize +1; i++){
            for(int j = 1; j < myModel.boardSize + 1;j++){
                //assigns the position variable with a random number from 0 to total boxes
                int pos = rand.nextInt(myModel.totalBoxes);
                //if the number has already been grabbed then it will find a different index
                while(posNum[pos] == 0){
                    pos = rand.nextInt(myModel.totalBoxes);

                }
                Numbers temp = new Numbers(posNum[pos], (j* myModel.outerSquareSize + 12), ((i+1)* myModel.outerSquareSize - 25 ), i-1, j-1);
                myModel.numberArray[i-1][j-1] = temp;
                posNum[pos] = 0;
            }

        }
        myView.invalidate();
    }

    //helper function to change the value of the number array
    //only if the player clicked in a box located around the blank box.
    public void checkTouch (){
        //checks if the person clicked outside the board
        if((myModel.pressX < myModel.outerSquareSize || myModel.pressX > myModel.totalBoardSize) || (myModel.pressY < myModel.outerSquareSize || myModel.pressY > myModel.totalBoardSize)){
            myModel.error = true;
            myView.invalidate();
        } else{
            int posX = (int) myModel.pressX/ myModel.outerSquareSize - 1;
            int posY = (int) myModel.pressY/ myModel.outerSquareSize - 1;
            if(myModel.numberArray[posY][posX] == checkSur(posX, posY)){
                myModel.error = true;
                myView.invalidate();
            } else {
                //else it wil swap the two number values
                Numbers found = checkSur(posX,posY);
                Numbers orig = myModel.numberArray[posY][posX];
                myModel.numberArray[found.getArrayY()][found.getArrayX()].setNum(orig.getNum());
                myModel.numberArray[posY][posX].setNum(myModel.totalBoxes);
                checkWin();
                myView.invalidate();
            }

        }
    }

    //helper function to check surroundings for empty square
    public Numbers checkSur(int x, int y){
        //checks the top box for an empty box
        if(y > 0){
            if(myModel.numberArray[y -1][x].getNum() == myModel.totalBoxes){
                return myModel.numberArray[y -1][x];
            }
        }
        //checks the left box for an empty box
        if(x > 0){
            if(myModel.numberArray[y][x - 1].getNum() == myModel.totalBoxes){
                return myModel.numberArray[y][x-1];
            }
        }
        //checks the bottom box for an empty box
        if(y < myModel.boardSize - 1){
            if(myModel.numberArray[y + 1][x].getNum() == myModel.totalBoxes){
                return myModel.numberArray[y + 1][x];
            }
        }

        //checks the right box for an empty box
        if(x < myModel.boardSize - 1){
            if(myModel.numberArray[y][x + 1].getNum() == myModel.totalBoxes){
                return myModel.numberArray[y][x + 1];
            }
        }
        return myModel.numberArray[y][x];
    }

    //helper method that will check if it is sorted correctly
    public void checkWin(){
        //does this by creating a counter
        int counter = 1;
        //checks each Numbers object in the array to see if it matches the counter.
        for (int i = 0; i < myModel.boardSize; i++) {
            for (int j = 0; j < myModel.boardSize; j++) {
                if(myModel.numberArray[i][j].getNum() != counter){
                   return ;
                }
                counter +=1;
            }
        }
        //if it does then it will make the variable win = true
        myModel.win = true;

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        myModel.pressX = motionEvent.getX();
        myModel.pressY = motionEvent.getY();
        checkTouch();
        return false;
    }

    @Override

    public void onClick(View view) {
        //when reset button is pressed, it will randomize the board and reset game.
        if(view.getId() == R.id.resetButton){
            myModel.win = false;
            randomizer();
        //when Size+ button is pressed, it will increase boardSize and reset game;
        } else if(view.getId() == R.id.increaseButton && myModel.boardSize < myModel.maxBoardSize){
            myModel.boardSize++;
            updateModel();

        //when Size- button is pressed, it will decrease boardSize and reset game;
        } else if(view.getId() == R.id.decreaseButton && myModel.boardSize > myModel.minBoardSize){
            myModel.boardSize--;
            updateModel();
        }


    }

    //helper method to update the model
    public void updateModel(){
        myModel.win = false;
        myModel.totalBoxes = myModel.boardSize*myModel.boardSize;
        myModel.totalBoardSize = myModel.outerSquareSize*(myModel.boardSize+1);
        myModel.numberArray = new Numbers[myModel.boardSize][myModel.boardSize];
        randomizer();
    }
}
