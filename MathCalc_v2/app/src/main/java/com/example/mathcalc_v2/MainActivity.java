package com.example.mathcalc_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.*;
import android.view.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // create views, or controls (step 1)
    Button btnRemDig, btnClear, btnPosNeg, btnDecimal, btnEquals, btnZero, btnOne, btnTwo, btnThree,
            btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnAdd, btnSub, btnMult, btnDiv;
    TextView tvResult, tvWorking;
    private double leftNum, rightNum, temporary;
    private char op, interim;
    private boolean isLeftNumSet, isResultSet, isDecimal;
    public double result;
    MathCalc calcObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views, or instantiate (Step 2)
        calcObj = new MathCalc();
        btnRemDig = findViewById(R.id.btnRemDig);
        btnClear = findViewById(R.id.btnClear);
        btnPosNeg = findViewById(R.id.btnPosNeg);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnEquals = findViewById(R.id.btnEquals);
        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);
        tvWorking = findViewById(R.id.tvWorking);


        // Set all events to same listener class
        btnRemDig.setOnClickListener(onButtonClicked);
        btnClear.setOnClickListener(onButtonClicked);
        btnPosNeg.setOnClickListener(onButtonClicked);
        btnDecimal.setOnClickListener(onButtonClicked);
        btnEquals.setOnClickListener(onButtonClicked);
        btnZero.setOnClickListener(onButtonClicked);
        btnOne.setOnClickListener(onButtonClicked);
        btnTwo.setOnClickListener(onButtonClicked);
        btnThree.setOnClickListener(onButtonClicked);
        btnFour.setOnClickListener(onButtonClicked);
        btnFive.setOnClickListener(onButtonClicked);
        btnSix.setOnClickListener(onButtonClicked);
        btnSeven.setOnClickListener(onButtonClicked);
        btnEight.setOnClickListener(onButtonClicked);
        btnNine.setOnClickListener(onButtonClicked);
        btnAdd.setOnClickListener(onButtonClicked);
        btnSub.setOnClickListener(onButtonClicked);
        btnMult.setOnClickListener(onButtonClicked);
        btnDiv.setOnClickListener(onButtonClicked);
    } // end onCreate

    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // Code to remove digit
                case R.id.btnRemDig:
                    if (!isResultSet) {
                        StringBuilder dataToCalc = new StringBuilder(tvResult.getText().toString());
                        if (dataToCalc.length() > 0) {
                            dataToCalc.deleteCharAt(dataToCalc.length() - 1);
                            tvResult.setText(dataToCalc);
                        }
                    }
                    break;
                // Code to Clear everything, TextViews and variables
                case R.id.btnClear:
                    tvWorking.setText("");
                    tvResult.setText("0");
                    leftNum = 0;
                    rightNum = 0;
                    temporary = 0;
                    result = 0;
                    op = '\0';
                    interim = '\0';
                    isLeftNumSet = false;
                    isResultSet = false;
                    isDecimal = false;
                    break;
                // Code to change a number from positive to negative
                case R.id.btnPosNeg:
                    if (!isResultSet) {
                        temporary = Double.parseDouble(String.valueOf(tvResult.getText()));
                        temporary *= -1;
                        tvResult.setText(String.valueOf(temporary));
                    }
                    break;
                // Code to type decimal numbers
                case R.id.btnDecimal:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("0");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                        result = 0;
                        tvResult.append(".");
                        isDecimal = true;
                    } else{
                        if(!isDecimal) {
                            tvResult.append(".");
                            isDecimal = true;
                        }
                    }
                    break;
                // Code to calculate the result (equal button)
                case R.id.btnEquals:
                    if (!isResultSet) {
                        if (tvResult == null) {
                            tvResult.setText("");
                            isResultSet = true;
                        } else {
                            if (!isLeftNumSet) {
                                tvResult.setText(tvResult.getText());
                                isDecimal = false;
                            } else {
                                rightNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                tvWorking.setText(null);
                                if (op == '/' && rightNum == 0) {
                                    tvResult.setText("NaN");
                                    isResultSet = true;
                                } else {
                                    result = calcObj.calc(leftNum, rightNum, op);
                                    tvResult.setText(String.valueOf(result));
                                    isResultSet = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                // Code for buttons 0 to 9
                case R.id.btnZero:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("0");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("0");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnOne:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("1");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("1");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnTwo:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("2");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("2");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnThree:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("3");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("3");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnFour:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("4");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("4");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnFive:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("5");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("5");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnSix:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("6");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("6");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnSeven:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("7");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("7");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnEight:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("8");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("8");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                case R.id.btnNine:
                    if (isResultSet) {
                        tvWorking.setText("");
                        tvResult.setText("9");
                        leftNum = 0;
                        rightNum = 0;
                        temporary = 0;
                        result = 0;
                        op = '\0';
                        interim = '\0';
                        isLeftNumSet = false;
                        isResultSet = false;
                        isDecimal = false;
                    } else{
                        tvResult.append("9");
                        if (isLeftNumSet) {
                            op = interim;
                        }
                    }
                    break;
                // Code for the 4 operations (buttons +, -, *, /)
                case R.id.btnAdd:
                    interim = '+';
                    if (tvResult == null) {
                        tvResult.setText("");
                        isResultSet = true;
                    } else {
                        if (isResultSet) {
                            rightNum = 0;
                            temporary = 0;
                            result = 0;
                            op = '\0';
                            isResultSet = false;
                            leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                            isLeftNumSet = true;
                            tvWorking.setText(tvResult.getText() + "+");
                            tvResult.setText("0");
                            isDecimal = false;
                        } else {
                            if (!isLeftNumSet) {
                                leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                isLeftNumSet = true;
                                tvWorking.setText(tvResult.getText() + "+");
                                tvResult.setText("0");
                                isDecimal = false;
                            } else {
                                rightNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                tvWorking.setText(null);
                                if (op == '/' && rightNum == 0) {
                                    tvResult.setText("NaN");
                                    isResultSet = true;
                                } else {
                                    result = calcObj.calc(leftNum, rightNum, op);
                                    tvResult.setText(String.valueOf(result));
                                    isResultSet = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case R.id.btnSub:
                    interim = '-';
                    if (tvResult == null) {
                        tvResult.setText("");
                        isResultSet = true;
                    } else {
                        if (isResultSet) {
                            rightNum = 0;
                            temporary = 0;
                            result = 0;
                            op = '\0';
                            isResultSet = false;
                            leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                            isLeftNumSet = true;
                            tvWorking.setText(tvResult.getText() + "-");
                            tvResult.setText("0");
                            isDecimal = false;
                        } else {
                            if (!isLeftNumSet) {
                                leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                isLeftNumSet = true;
                                tvWorking.setText(tvResult.getText() + "-");
                                tvResult.setText("0");
                                isDecimal = false;
                            } else {
                                rightNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                tvWorking.setText(null);
                                if (op == '/' && rightNum == 0) {
                                    tvResult.setText("NaN");
                                    isResultSet = true;
                                } else {
                                    result = calcObj.calc(leftNum, rightNum, op);
                                    tvResult.setText(String.valueOf(result));
                                    isResultSet = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case R.id.btnMult:
                    interim = '*';
                    if (tvResult == null) {
                        tvResult.setText("");
                        isResultSet = true;
                    } else {
                        if (isResultSet) {
                            rightNum = 0;
                            temporary = 0;
                            result = 0;
                            op = '\0';
                            isResultSet = false;
                            leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                            isLeftNumSet = true;
                            tvWorking.setText(tvResult.getText() + "*");
                            tvResult.setText("0");
                            isDecimal = false;
                        } else {
                            if (!isLeftNumSet) {
                                leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                isLeftNumSet = true;
                                tvWorking.setText(tvResult.getText() + "*");
                                tvResult.setText("0");
                                isDecimal = false;
                            } else {
                                rightNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                tvWorking.setText(null);
                                if (op == '/' && rightNum == 0) {
                                    tvResult.setText("NaN");
                                    isResultSet = true;
                                } else {
                                    result = calcObj.calc(leftNum, rightNum, op);
                                    tvResult.setText(String.valueOf(result));
                                    isResultSet = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case R.id.btnDiv:
                    interim = '/';
                    if (tvResult == null) {
                        tvResult.setText("");
                        isResultSet = true;
                    } else {
                        if (isResultSet) {
                            rightNum = 0;
                            temporary = 0;
                            result = 0;
                            op = '\0';
                            isResultSet = false;
                            leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                            isLeftNumSet = true;
                            tvWorking.setText(tvResult.getText() + "/");
                            tvResult.setText("0");
                            isDecimal = false;
                        } else {
                            if (!isLeftNumSet) {
                                leftNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                isLeftNumSet = true;
                                tvWorking.setText(tvResult.getText() + "/");
                                tvResult.setText("0");
                                isDecimal = false;
                            } else {
                                rightNum = Double.parseDouble(String.valueOf(tvResult.getText()));
                                tvWorking.setText(null);
                                if (op == '/' && rightNum == 0) {
                                    tvResult.setText("NaN");
                                    isResultSet = true;
                                } else {
                                    result = calcObj.calc(leftNum, rightNum, op);
                                    tvResult.setText(String.valueOf(result));
                                    isResultSet = true;
                                    break;
                                }
                            }
                        }
                    }
                    break;
            } // end Switch
        } // end Method onClick
    }; // end inner class
} // end class