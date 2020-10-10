package com.pravin.lede.gl.myapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pravin.lede.gl.myapplication.R;

public class QuestionAnsCustomView extends LinearLayout implements View.OnClickListener {
    private LinearLayout linearLayout;
    private TextView question;
    private TextView optionOne;
    private TextView optionTwo;

    private TextView optionThree;
    private TextView optionFour;

    public QuestionAnsCustomView(Context context) {
        super(context);
    }

    public QuestionAnsCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
        init(context, attrs);
    }

    private void initLayout(Context context) {
        linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.question_ans_custom_view, this, true);
        question = linearLayout.findViewById(R.id.question);
        optionOne = linearLayout.findViewById(R.id.opt1);
        optionTwo = linearLayout.findViewById(R.id.opt2);
        optionThree = linearLayout.findViewById(R.id.opt3);
        optionFour = linearLayout.findViewById(R.id.opt4);

        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QuestionAnsCustomView, 0, 0);

            for (int i = 0; i < a.getIndexCount(); i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.QuestionAnsCustomView_Question:
                        String question = a.getString(attr);
                        setQuestion(question);
                        break;
                    case R.styleable.QuestionAnsCustomView_OptionOne:
                        String opt1 = a.getString(attr);
                        setOptionOne(opt1);
                        break;
                    case R.styleable.QuestionAnsCustomView_OptionTwo:
                        String opt2 = a.getString(attr);
                        setOptionTwo(opt2);
                        break;
                    case R.styleable.QuestionAnsCustomView_OptionThree:
                        String opt3 = a.getString(attr);
                        setOptionThree(opt3);
                        break;
                    case R.styleable.QuestionAnsCustomView_OptionFour:
                        String opt4 = a.getString(attr);
                        setOptionFour(opt4);
                        break;
                    case R.styleable.QuestionAnsCustomView_OriginalAnswer:
                        String originalAns = a.getString(attr);
                        setOriginalAnswer(originalAns);
                        break;
                }
            }
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setOriginalAnswer(String originalAns) {

    }

    public void setOptionFour(String opt4) {
        optionFour.setText(opt4);
    }

    public void setOptionThree(String opt3) {
        optionThree.setText(opt3);
    }

    public void setOptionTwo(String opt2) {
        optionTwo.setText(opt2);
    }

    public void setOptionOne(String opt1) {
        optionOne.setText(opt1);
    }

    public void setQuestion(String que) {
        question.setText(que);
    }

    public String getQuestion() {
        return question.getText().toString();
    }

    public String getOptionOne() {
        return optionOne.getText().toString();
    }

    public String getOptionTwo() {
        return optionTwo.getText().toString();
    }

    public String getOptionThree() {
        return optionThree.getText().toString();
    }

    public String getOptionFour() {
        return optionFour.getText().toString();
    }

    private void setSelectedOptionsUnselect() {
        optionOne.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_black, 0, 0, 0);
        optionTwo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_black, 0, 0, 0);
        optionThree.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_black, 0, 0, 0);
        optionFour.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_black, 0, 0, 0);
    }

    @Override
    public void onClick(View v) {
        int clickedViewId = v.getId();
        switch (clickedViewId){
            case R.id.opt1:
                showMsg(getOptionOne());
                break;
            case R.id.opt2:
                showMsg(getOptionTwo());
                break;
            case R.id.opt3:
                showMsg(getOptionThree());
                break;
            case R.id.opt4:
                showMsg(getOptionFour());
                break;
        }
    }

    private void showMsg(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//        optionOne.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle_green, 0, 0, 0);

    }
}
