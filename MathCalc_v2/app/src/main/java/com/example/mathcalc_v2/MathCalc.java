package com.example.mathcalc_v2;

public class MathCalc {
    public double calc (double leftNum, double rightNum, char op) {
        double result = 0;
        switch (op) {
            case '*':
                result = leftNum * rightNum;
                break;
            case '/':
                result = leftNum / rightNum;
                break;
            case '+':
                result = leftNum + rightNum;
                break;
            case '-':
                result = leftNum - rightNum;
                break;
            default:
                result = 0;
        } // end switch
        return result;
    } // end method calc
} // end class
