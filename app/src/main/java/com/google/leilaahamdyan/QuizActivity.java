package com.google.leilaahamdyan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView btnOne, btnTwo, btnThree, btnFour, btnNext;
    TextView tvQuestion, tvCorrectAnswer;

    QuestionItem currentQuestion;

    int currentPosition = 0;

    QuizActivity       activity      = this;
    List<QuestionItem> questionItems = new ArrayList<>();
    SessionManager     sessionManager;
    int                correctAnswer = 0, unCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle("سوالات ارشد");
        sessionManager = new SessionManager(this);

        SessionManager.setIsReview(false);

        btnOne          = findViewById(R.id.btnOne);
        btnTwo          = findViewById(R.id.btnTwo);
        btnThree        = findViewById(R.id.btnThree);
        tvQuestion      = findViewById(R.id.tvQuestion);
        tvCorrectAnswer = findViewById(R.id.tvCorrectAnswer);
        btnNext         = findViewById(R.id.btnNext);
        btnFour         = findViewById(R.id.btnFour);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        questionItems = dataBaseHelper.getAllQuestions();

        currentQuestion = questionItems.get(currentPosition);

        setQuestion(currentQuestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                questionResult(btnOne);
                break;

            case R.id.btnTwo:
                questionResult(btnTwo);
                break;

            case R.id.btnThree:
                questionResult(btnThree);
                break;

            case R.id.btnFour:
                questionResult(btnFour);
                break;
        }
    }

    private void questionResult(TextView textView) {
        String currentAnswer = textView.getText().toString();
        String ans           = "";
        switch (Integer.parseInt(currentQuestion.getAnswer())) {
            case 1:
                ans = currentQuestion.getAnswer1();
                break;
            case 2:
                ans = currentQuestion.getAnswer2();
                break;
            case 3:
                ans = currentQuestion.getAnswer3();
                break;
            case 4:
                ans = currentQuestion.getAnswer4();
                break;
        }
        if (currentAnswer.equals(ans)) {
            correctAnswer++;
        } else {
            unCorrectAnswer++;
        }
        if (currentPosition >= questionItems.size())
            finishQuizDialog();
        else {
            currentQuestion = questionItems.get(currentPosition);
            setQuestion(currentQuestion);
        }
    }

    public void nextQuestion(View view) {
        if (currentPosition >= questionItems.size())
            finishQuizDialog();
        else {
            currentQuestion = questionItems.get(currentPosition);
            setQuestion(currentQuestion);
        }
    }

    private void finishQuizDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder
                .setTitle("Finish Quiz")
                .setMessage("Correct Answer : " + correctAnswer + "\n" + "Un Correct Answer : " + unCorrectAnswer)
                .setCancelable(false)
                .setPositiveButton("New Quiz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recreate();
                    }
                })
                .setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                })
                .setNeutralButton("Review", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        currentPosition = 0;
                        SessionManager.setIsReview(true);
                        currentQuestion = questionItems.get(currentPosition);
                        setQuestion(currentQuestion);
                    }
                });
        alertDialogBuilder.show();

    }

    private void setQuestion(QuestionItem questionItem) {
        tvCorrectAnswer.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        tvQuestion.setText((currentPosition + 1) + ") " + questionItem.getQuestion());
        btnOne.setText(questionItem.getAnswer1());
        btnTwo.setText(questionItem.getAnswer2());
        btnThree.setText(questionItem.getAnswer3());
        btnFour.setText(questionItem.getAnswer4());
        String ans = "";
        switch (Integer.parseInt(questionItem.getAnswer().trim())) {
            case 1:
                ans = questionItem.getAnswer1();
                break;
            case 2:
                ans = questionItem.getAnswer2();
                break;
            case 3:
                ans = questionItem.getAnswer3();
                break;
            case 4:
                ans = questionItem.getAnswer4();
                break;
        }

        String desc = "جواب : " + ans + "\n\n" + questionItem.getAnswerDesc();
        tvCorrectAnswer.setText(desc);
        if (SessionManager.getIsReview()) {
            tvCorrectAnswer.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnOne.setEnabled(false);
            btnTwo.setEnabled(false);
            btnThree.setEnabled(false);
            btnFour.setEnabled(false);
        }
        currentPosition++;
    }

}
