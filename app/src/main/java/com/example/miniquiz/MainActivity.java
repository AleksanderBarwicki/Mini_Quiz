package com.example.miniquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private Button btnStart, btnReset, btnOptA, btnOptB, btnOptC;
    private LinearLayout quizSection;

    private List<Question> allQuestions = new ArrayList<>();
    private List<Question> selectedQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja widoków
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnOptA = findViewById(R.id.btnOptA);
        btnOptB = findViewById(R.id.btnOptB);
        btnOptC = findViewById(R.id.btnOptC);
        quizSection = findViewById(R.id.quizSection);

        initQuestions();

        btnStart.setOnClickListener(v -> startQuiz());
        btnReset.setOnClickListener(v -> resetQuiz());

        View.OnClickListener answerListener = v -> {
            Button clickedButton = (Button) v;
            checkAnswer(clickedButton.getText().toString());
        };

        btnOptA.setOnClickListener(answerListener);
        btnOptB.setOnClickListener(answerListener);
        btnOptC.setOnClickListener(answerListener);
    }

    private void initQuestions() {
        allQuestions.add(new Question("Stolica Włoch to:", "Rzym", "Paryż", "Madryt", "Rzym"));
        allQuestions.add(new Question("Ile to 2 + 2?", "3", "4", "5", "4"));
        allQuestions.add(new Question("Największa planeta Układu Słonecznego:", "Mars", "Ziemia", "Jowisz", "Jowisz"));
        allQuestions.add(new Question("Który pierwiastek ma symbol O?", "Złoto", "Tlen", "Wodór", "Tlen"));
        allQuestions.add(new Question("W którym roku wybuchła II WŚ?", "1914", "1939", "1945", "1939"));
        allQuestions.add(new Question("Stolica Polski to:", "Kraków", "Gdańsk", "Warszawa", "Warszawa"));
        allQuestions.add(new Question("Jakie zwierzę mówi 'miau'?", "Pies", "Kot", "Koń", "Kot"));
    }

    private void startQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");

        // Losowanie 5 pytań
        Collections.shuffle(allQuestions);
        selectedQuestions = new ArrayList<>(allQuestions.subList(0, 5));

        btnStart.setVisibility(View.GONE);
        quizSection.setVisibility(View.VISIBLE);
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < selectedQuestions.size()) {
            Question q = selectedQuestions.get(currentQuestionIndex);
            tvQuestion.setText(q.getQuestionText());
            btnOptA.setText(q.getOptionA());
            btnOptB.setText(q.getOptionB());
            btnOptC.setText(q.getOptionC());
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer(String answer) {
        if (answer.equals(selectedQuestions.get(currentQuestionIndex).getCorrectAnswer())) {
            score++;
            tvScore.setText("Wynik: " + score);
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishQuiz() {
        quizSection.setVisibility(View.GONE);
        Toast.makeText(this, "Koniec quizu! Twój wynik: " + score + " / 5", Toast.LENGTH_LONG).show();
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Wynik: 0");
        quizSection.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
    }
}