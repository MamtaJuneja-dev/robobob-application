package com.robobob.service;


import com.robobob.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class RoboBobService {

    Logger logger = LoggerFactory.getLogger(RoboBobService.class);

    @Autowired
    private final QuestionRepository questionRepository;
    //CacheLoader cacheLoader = new CacheLoader(2);

    public RoboBobService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        logger.info("Loading Question on Service Initialization");
        questionRepository.loadQuestions();

    }

    @Cacheable(value = "questionCache", key = "#question")
    public String handleQuestion(String question) {
       logger.info("Checking for predefined Question");
        String answer = questionRepository.getAnswer(question);
        if (answer != null) {
            return answer;  // Will be automatically cached due to @Cacheable
        }

        logger.info("The question asked is arithematic");
         try {
        return evaluateArithmeticExpression(question);
       } catch (Exception e) {
             logger.error("The question asked is unclear" + e.getMessage() + question);
        return "Sorry, I don't understand that. Please ask a different question.";
        }
    }

    @Cacheable(value = "arithmeticCache", key = "#expression")
    private String evaluateArithmeticExpression(String expression)  {
        try {
            Expression e = new ExpressionBuilder(expression).build();
            double result = e.evaluate();
            return String.valueOf(result);
        } catch (Exception ex) {
            logger.error("There was error evaluating the question " + ex.getMessage());
            return "Error evaluating the expression: " + ex.getMessage();
        }



    }
}


