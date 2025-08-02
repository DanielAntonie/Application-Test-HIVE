# 🧪 Developer Application Test: Habit Tracker Lite (Online Only)

Welcome! This is a short technical test to help us evaluate your skills in Kotlin, Retrofit, and general Android app structure. You are provided with a prebuilt UI and your task is to bring it to life by implementing the backend logic.

---

## 📱 Overview

You will build a small **Habit Tracker** app that allows users to:

- View a list of habits
- Add a new habit
- Mark a habit as "Done Today"
- Sync new or updated habits with a remote API

> ❗ This version of the test **does not** require offline capability or RoomDB caching. It is entirely online-only.

---

## 🧰 What’s Provided

The starter Android Studio project includes:

- Screens already created in XML:
  - **Habit List**
  - **Add Habit**
  - **Sync Button**
- Navigation already set up
- Empty classes/interfaces for:
  - `ViewModel`, `Repository`, `RetrofitService`, and `Data Models`
- Sample mock JSON file in `/assets/mock_habits.json` (optional)

---

## 🎯 Your Tasks

### 1. Retrofit API Integration
- Create a Retrofit interface to handle the following:
  - `GET /habits` – Fetch all habits
  - `POST /habits` – Add a new habit
  - `PUT /habits/{id}` – Mark a habit as done
- You may use any public mock API (e.g., https://reqres.in, https://mockapi.io) or simulate a local JSON source using an `Interceptor`.

---

### 2. Habit List Screen
- Display habits fetched from the API
- Each habit item should show:
  - Name
  - Description
  - Last Completed Date
  - A "Mark as Done" button

---

### 3. Add Habit Screen
- Implement logic to:
  - Capture name and description
  - Submit data using `POST /habits`
  - Return to the list and refresh it

---

### 4. Mark Habit as Done
- On button click:
  - Send a `PUT /habits/{id}` request to update the `lastCompletedDate` to today
  - Update the UI accordingly

---

## 🧪 Suggested Habit Model

```kotlin
data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val lastCompletedDate: String? // Format: yyyy-MM-dd
)

```
## 🚫 Not Required

You do **not** need to implement:

- RoomDB or offline data persistence  
- User authentication  
- Dark mode or advanced UI polish  
- Testing (though you're welcome to include it)

---

## 📦 Submission Instructions

- Submit your code as a **GitHub repository** 
- Include a short `README.md` explaining how to build and run the app

> 🕒 **Deadline: Tuesday @ 12:00 PM**

---

## ✅ Evaluation Criteria

| Area                         | Weight |
|------------------------------|--------|
| Retrofit API integration     | 40%    |
| List & form UI functionality | 30%    |
| Code structure & readability | 20%    |
| Extras / effort shown        | 10%    |

---

🎯 **Good luck, and have fun building!** 

