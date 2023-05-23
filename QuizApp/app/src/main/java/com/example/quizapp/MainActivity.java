package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.*;
import android.view.*;
import android.content.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // create views
    TextView tvDefinition, tvIsAnswer;
    Button btTermOne, btTermTwo, btTermThree, btTermFour, btNextQuestion;
    ArrayList<String> definitions = new ArrayList<String>();
    ArrayList<String> terms = new ArrayList<String>();
    ArrayList<String> choices = new ArrayList<String>();
    Map<String, String> map = new HashMap<String, String>();

    // create variables
    String term;
    String definition;
    int finalScore = 0;
    String correctAnswer;
    String greeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views
        tvDefinition = findViewById(R.id.tvDefinition);
        tvIsAnswer = findViewById(R.id.tvIsAnswer);
        btTermOne = findViewById(R.id.btTermOne);
        btTermTwo = findViewById(R.id.btTermTwo);
        btTermThree = findViewById(R.id.btTermThree);
        btTermFour = findViewById(R.id.btTermFour);
        btNextQuestion = findViewById(R.id.btNextQuestion);

        // Set all events to same listener class
        btTermOne.setOnClickListener(onButtonClicked);
        btTermTwo.setOnClickListener(onButtonClicked);
        btTermThree.setOnClickListener(onButtonClicked);
        btTermFour.setOnClickListener(onButtonClicked);
        btNextQuestion.setOnClickListener(onButtonClicked);

        String line = null;
        BufferedReader br = null;

        // load the .txt file, add to ArrayLists and do Hashmap
        try {
            InputStream is = getResources().openRawResource(R.raw.terms_definitions);
            br = new BufferedReader(new InputStreamReader(is));
            System.out.println("File in RAW is open");

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    StringTokenizer tokens = new StringTokenizer(line, "$$$");
                    definition = tokens.nextToken();
                    term = tokens.nextToken();
                    definitions.add((definition));
                    terms.add(term);
                    map.put(definition, term);
                    System.out.println(terms);
                }
            }
            is.close();
            System.out.println("File in RAW is closed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        displayQuestion();

    } // end onCreate

    // create onClickListener and switch / case statements for each button
    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btTermOne:
                    checkAnswer(1);
                    break;

                case R.id.btTermTwo:
                    checkAnswer(2);
                    break;

                case R.id.btTermThree:
                    checkAnswer(3);
                    break;

                case R.id.btTermFour:
                    checkAnswer(4);
                    break;
                case R.id.btNextQuestion:
                    displayQuestion();
            }
        }
    }; // end onClickListener

    // display definition and terms
    public void displayQuestion () {
        if (! definitions.isEmpty()) {
            Collections.shuffle(definitions); // shuffle definitions ArrayList
            Collections.shuffle(terms); // shuffle terms ArrayList
            tvDefinition.setText(definitions.get(0)); // send Definition to textView
            correctAnswer = map.get(definitions.get(0)); // Parse to correctAnswer variable the correct definition
            choices.clear();
            choices.add(correctAnswer); // add correct answer to choices ArrayList
            definitions.remove(definitions.get(0)); // delete chosen definition
            for (int i = 0; i < 3; i++) {
                choices.add(terms.get(i)); // add the other 3 terms
                if (Objects.equals(choices.get(0), choices.get(i + 1))) { // check if it's duplicated
                    choices.remove(choices.get(i + 1)); // remove the duplicated
                    choices.add(terms.get(4)); // add another term
                }
            }
            Collections.shuffle(choices); // shuffle choices ArrayList

            // clean tvIsAnswer
            tvIsAnswer.setText("");

            // send terms to buttons
            btTermOne.setText(choices.get(0));
            btTermTwo.setText(choices.get(1));
            btTermThree.setText(choices.get(2));
            btTermFour.setText(choices.get(3));

            // enable term buttons
            btTermOne.setEnabled(true);
            btTermTwo.setEnabled(true);
            btTermThree.setEnabled(true);
            btTermFour.setEnabled(true);

            //disable Next question until a term button is clicked
            btNextQuestion.setEnabled(false);
        } else {
            submitQuiz();

        }
    } // end displayQuestion

    // check if selected button is the correct answer and disable button terms until next question
    public void checkAnswer (int selectedNumber) {
        String selectedAnswer = choices.get(selectedNumber - 1);
        if (Objects.equals(correctAnswer, selectedAnswer)) {
            finalScore ++;
            tvIsAnswer.setText("It's Correct");
            btTermOne.setEnabled(false);
            btTermTwo.setEnabled(false);
            btTermThree.setEnabled(false);
            btTermFour.setEnabled(false);
            btNextQuestion.setEnabled(true);
        } else {
            tvIsAnswer.setText("It's Wrong, correct answer is: " + correctAnswer);
            btTermOne.setEnabled(false);
            btTermTwo.setEnabled(false);
            btTermThree.setEnabled(false);
            btTermFour.setEnabled(false);
            btNextQuestion.setEnabled(true);
        }
    } // end checkAnswer

    // intent to submit quiz to next screen
    public void submitQuiz () {
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("FinalScore", finalScore);
        startActivity(intent);
    } // end submitQuiz

} // end of Main class