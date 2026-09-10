## Requirements
- users can post questions, answers and comments on both ques and ans
- users can upvote or downvote ques and ans, each user can vote only once per post
- poster can accept one answer as the solution to his question
- users can earn or lose reputation based on upvotes/downvotes or wheter their answre is accepted
- users can search for questions by keyword in the title or body and filter by tags
Out of scope - nested comments
             - auth
             - ui

## Core Entities
- User
- Question
- Answer
- Comment
- StackOverflow

## Class Design
enum VoteType:
    UPVOTE,
    DOWNVOTE

class User:
    id: String
    name: String
    reputation: AtomicInteger

    updateReputation(change: int) -> void

class Question:
    id: String
    title:
    body:
    author: User
    createdAt: DateTime
    answers: List<Answer>
    selectedAnswer: Answer
    votes: Vote
    tags: List<String>
    comments: List<Comment>

class Answer:
    id:
    body:
    quesId:
    author: User
    createdAt: DateTime
    votes: Vote
    comments: List<Comment>

class Comment:
    id:
    body:
    postId:
    author: String
    createdAt: DateTime

class Vote:
    voter: User
    type: VoteType

class StackOverflowService:
    void post
