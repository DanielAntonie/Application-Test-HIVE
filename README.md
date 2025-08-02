# Habit Tracker

A simple Android app built with Kotlin and Retrofit to track habits using a mock API. Users can view a list of habits, add new habits, mark habits as done, and sync data with the server.

## Features
- **List Habits**: Displays habits with name, description, and last completed date in a RecyclerView.
- **Add Habits**: Allows users to add new habits with a name and description.
- **Mark as Done**: Updates the last completed date for a habit.
- **Sync Habits**: Refreshes the habit list from the server.

## Prerequisites
- **Android Studio**: Version 2024.1.1 or later.
- **JDK**: Version 11.
- **mockapi.io Account**: For the mock backend API.
- **Git**: To clone the repository.

## Setup Instructions

### 1. Clone the Repository
Clone the project to your local machine:

```bash
git clone <your-repository-url>
```

Replace `<your-repository-url>` with the GitHub repository URL.

### 2. Set Up mockapi.io
1. Sign up or log in at [mockapi.io](https://mockapi.io/).
2. Create a new project (e.g., "HabitTrackerAPI").
3. Add a `habits` resource with the following schema:
   ```json
   {
     "id": "number",
     "name": "string",
     "description": "string",
     "lastCompletedDate": "string|null"
   }
   ```
4. (Optional) Add sample data via the dashboard, e.g.:
   ```json
   {
     "id": 1,
     "name": "Drink Water",
     "description": "Drink 8 glasses daily",
     "lastCompletedDate": "2025-08-01"
   }
   ```
5. Copy the project URL (e.g., `https://123456789.mockapi.io/`).

### 3. Configure the Project
1. Open the project in **Android Studio**.
2. Update the `BASE_URL` in `app/src/main/java/com/nicholas/application_test_hive/network/RetrofitClient.kt` with your mockapi.io project URL:
   ```kotlin
   private const val BASE_URL = "https://<your-project-id>.mockapi.io/"
   ```
   Replace `<your-project-id>` with your mockapi.io project ID.
3. Sync the project:
   - Click **Sync Project with Gradle Files** in Android Studio.
   - Or run:
     ```bash
     ./gradlew sync
     ```

### 4. Build the Project
1. Clean and rebuild the project:
   - In Android Studio: **Build > Clean Project**, then **Build > Rebuild Project**.
   - Or run:
     ```bash
     ./gradlew clean build
     ```
2. Ensure no build errors occur. The `build.gradle.kts` includes all necessary dependencies (Retrofit, Gson, OkHttp, Material, CardView).

### 5. Run the App
1. Connect an Android device or start an emulator (API level 24 or higher).
2. Run the app:
   - Click **Run > Run 'app'** in Android Studio.
   - Or run:
     ```bash
     ./gradlew installDebug
     ```
3. Verify functionality:
   - **Main Screen**: Displays a list of habits from mockapi.io.
   - **Add Habit**: Tap "Add New Habit" to enter a name and description, then save.
   - **Mark as Done**: Tap "Mark as Done" on a habit to update its completion date.
   - **Sync**: Tap "Sync Habits" to refresh the list.

## Dependencies
- **Retrofit**: For API communication.
- **Gson**: For JSON parsing.
- **OkHttp**: For HTTP requests with logging.
- **Material Components**: For UI elements.
- **CardView**: For habit item layouts.

## Troubleshooting
- **Build Errors**: Ensure all dependencies are synced and JDK 11 is configured.
- **API Issues**: Verify the mockapi.io URL and test endpoints (e.g., `https://<your-project-id>.mockapi.io/habits`) in a browser or Postman.
- **No Data**: Add sample habits in mockapi.io or check Logcat for Retrofit errors (filter by `OkHttp`).
- **Contact**: For issues, refer to the project repository or contact the developer.

## Project Structure
- `models/`: Data class for `Habit`.
- `network/`: Retrofit setup and API interface.
- `repository/`: Handles API calls.
- `viewmodel/`: Manages UI data.
- `adapters/`: RecyclerView adapter for habits.
- `res/layout/`: XML layouts for activities and habit items.
