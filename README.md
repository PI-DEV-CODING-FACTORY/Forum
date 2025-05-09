# 💬 Discussion Forum Microservice

This microservice is dedicated to managing the discussion forum feature of the academic platform. It enables students to share knowledge, ask questions, and collaborate on technical and academic topics in a centralized environment.

# 🚀 Core Features

1. 🧠 Questions & Answers System
Ask a Question: Students can post questions with optional image uploads and categorize them by topic.

Answer Questions: Users can respond to questions using text or images.

Voice-to-Text Posting: Allows students to dictate questions/answers and convert speech to text.

Best Answer Marking: Mark one response as the "best solution" to highlight resolved questions.

AI-Generated Suggestions: When no suitable answer is found, the system can generate a potential solution using AI.

2. 🧾 Intelligent Code Detection
Code Recognition: Automatically detects and highlights code snippets in questions.

AI-Powered Parsing: Enhances question understanding and response generation by identifying technical code context.

3. 📈 Participation & Activity Tracking
User Statistics: Dashboard to track user engagement (questions posted, answers given, votes, etc.).

User Post Management: Full CRUD operations on forum posts and replies.

Post Reporting: Users can report inappropriate or off-topic content for moderation.

4. 🤖 Chatbot Assistant
Provides contextual help, suggestions, and guidance using AI for improved student support.

5. 📰 Tech News Integration
Trending Topics: A dedicated interface to display trending news and updates in the field of computer science and technology.

6. 🔔 Notifications System
Replies: Notify users when their questions receive replies.

Mentions: Alert users when they are mentioned by others in a post.




# 🛠️ Tech Stack & Integration

Spring Boot – Backend service framework

Spring Data JPA – For data persistence

Spring Security + JWT – Secure access to endpoints

Feign Client – For inter-service communication

Eureka Server – Service discovery

API Gateway – Routing and request filtering

Docker – Containerized deployment

AI Integration – For natural language understanding and code detection
