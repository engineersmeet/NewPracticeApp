package com.pravin.lede.gl.myapplication.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pravin.lede.gl.myapplication.R;
import com.pravin.lede.gl.myapplication.customView.CircularImageView;
import com.pravin.lede.gl.myapplication.customView.QuestionAnsCustomView;
import com.pravin.lede.gl.myapplication.models.QuestionDataModel;
import com.pravin.lede.gl.myapplication.utils.QuestionDb;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity {

    CircularImageView circularImageView;
    QuestionAnsCustomView questionAnsCustomView;

    TextView result;
    TextView nextTextView;
    TextView previousTextView;

    QuestionDb questionDb;
    ArrayList<QuestionDataModel> questionDataModels;
    String[] ansList;
    int nextQue = 0;
    int marks = 0;

    boolean isPreviewMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        init();
//        setOnClickListener();
//        checkAndInsertQuestionData();
//
//        questionDataModels = questionDb.getAllQuestion();
//        ansList = new String[questionDataModels.size()];
//        updateQuestion();

    }

    private void checkAndInsertQuestionData() {

        if (questionDb.getAllQuestion().size() == 0) {

            questionDb.insertQuestionData(new QuestionDataModel("Who is the PM of Maharashtra?",
                    "Devendra Fadanvis",
                    "Ajit Pawar",
                    "Uddhav Thakare",
                    "Rahul Gandhi",
                    "Uddhav Thakare"));

            questionDb.insertQuestionData(new QuestionDataModel("What is the capital of India?",
                    "Mumbai",
                    "Bhandara",
                    "Delhi",
                    "Goa",
                    "Delhi"));

            questionDb.insertQuestionData(new QuestionDataModel("How many states in India?",
                    "29",
                    "30",
                    "31",
                    "28",
                    "28"));

            questionDb.insertQuestionData(new QuestionDataModel("Nails made up of?",
                    "Calcium",
                    "Potassium",
                    "Protein",
                    "Zinc",
                    "Protein"));

            questionDb.insertQuestionData(new QuestionDataModel("Smiley emoji refers to?",
                    ":)",
                    ":(",
                    ":-|",
                    ":0",
                    ":)"));

        }
    }

    private void init() {
        questionDb = new QuestionDb(this, 1);

        result = findViewById(R.id.result);
        previousTextView = findViewById(R.id.previous);
        nextTextView = findViewById(R.id.next);

        circularImageView = findViewById(R.id.image);
        circularImageView.setImageResource(R.drawable.parrot);

        questionAnsCustomView = findViewById(R.id.qacv);

    }

    private void setOnClickListener() {

        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQue++;
                updateQuestion();
            }
        });

        previousTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQue--;
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        if (questionDataModels.size() > nextQue) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    QuestionDataModel model = questionDataModels.get(nextQue);

                    setNextQuestionDisable();
                    setPreviousQuestionDisable();
                }
            }, 1000);
        } else {
            nextQue--;
        }
    }

    private void setPreviousQuestionDisable() {
        if (nextQue == 0) {
            previousTextView.setEnabled(false);
        } else {
            previousTextView.setEnabled(true);
        }
    }

    private void setNextQuestionDisable() {
        if (nextQue == questionDataModels.size() - 1) {
            nextTextView.setEnabled(false);
            if (isPreviewMode) {
                showDialog("Would you like to turn off preview mode?");
            }
        } else {
            nextTextView.setEnabled(true);
        }
    }

    /**
     * Set text to textView
     */
    private void setTextToTextView(String text, String originalAns, TextView textView) {
        textView.setText(text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionDataModels.size() > nextQue && !isPreviewMode) {
                    if (text.equals(originalAns)) {
                        marks++;
                    }
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_green, 0, 0, 0);
                    ansList[nextQue] = text;
                    nextQue++;
                    if (nextQue == questionDataModels.size()) {
                        showDialog("Your marks : " + marks);
                    }
                    updateQuestion();
                }
            }
        });
        if (isPreviewMode && text.equals(ansList[nextQue])) {
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_green, 0, 0, 0);
        }
    }

    private void showDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result!!");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                isPreviewMode = false;
            }
        });
        if (!isPreviewMode) {
            builder.setNegativeButton("Preview", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isPreviewMode = true;
                }
            });
        }
        if (marks < 3) {
            builder.setNeutralButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    marks = 0;
                    nextQue = 0;
                    updateQuestion();
                    dialog.dismiss();
                }
            });
        }
        builder.setCancelable(false);
        builder.create().show();
    }
}
