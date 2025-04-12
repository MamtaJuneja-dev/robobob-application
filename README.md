# RoboBob – Children's Mathematics Robot App

RoboBob is a friendly robot app that helps children with basic math expressions and answers a set of predefined questions in
The app supports both arithmetic evaluation and static Q&A responses using a single unified interface.

## Features
Single REST API endpoint for both arithmetic and general questions
Arithmetic expression evaluation
Predefined basic Q&A
In-memory caching of frequently asked questions.
Data loaded from questions.txt in future questions can be retrieved from DB

## Prerequisites
- Java 17 or higher
- Maven
- (Optional) IDE like IntelliJ IDEA or VS Code

## Run the app

# Clone the repo
git clone https://github.com/yourusername/robobob-app.git
cd robobob-app

# Build and run
./mvnw spring-boot:run

# Endpoint
# Example 1: Predefined Questions
 POST/askQuestions
 Body: What is your name
 Response: RoboBob

# Example 2: Arithematic questions
POST /askQuestions
Body: 2 * 10.5 + 1
Response: 22.0

# Caching Behaviour:

-First time a question is asked, it is loaded and cached.
-Repeated requests are served from the in-memory cache.

# Future Enhancements:

-Store Questions and Answers in a real database (e.g., PostgreSQL, MongoDB)
-Add front-end interface (React or Android/iOS)
-Use Distributed cache ie Redis






