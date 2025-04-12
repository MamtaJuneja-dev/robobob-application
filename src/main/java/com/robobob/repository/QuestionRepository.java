package com.robobob.repository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class QuestionRepository {
    private static final String FILE_PATH = "questions.txt";  // Fi

    // le to store questions
    private Map<String, String> questionAnswerMap = new HashMap<>();

    // Load predefined questions and answers from file
    public void loadQuestions() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    questionAnswerMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieve answer for a given question
    public String getAnswer(String question) {
        return questionAnswerMap.getOrDefault(question, null);
    }

    // Add a new question-answer pair to the file and the map
    public void addQuestion(String question, String answer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(question + ":" + answer + "\n");
            questionAnswerMap.put(question, answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
