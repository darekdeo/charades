# Product Requirements Document (PRD): Charades App Migration & Completion

## 1. Executive Summary
**Project Name:** Charades Expo Migration
**Objective:** Migrate the existing native Android "Charades" application to a cross-platform React Native solution using the Expo framework. The project also aims to complete the application by implementing missing core features, specifically the active gameplay loop (Game Mode) which is currently absent.

## 2. Current Status Analysis
The existing Android codebase contains:
-   **Architecture:** MVP (Model-View-Presenter) with Clean Architecture layers (Coordinators, Interactors).
-   **Features:**
    -   **Categories List:** View all categories.
    -   **Category Management:** Create/Edit categories.
    -   **Charade Management:** Add/Remove words (charades) within a category.
    -   **Persistence:** SQLite (Room Database).
-   **Missing Features:**
    -   **Game Loop:** The actual "Play" mode is missing.
    -   **Settings:** No configuration for round duration or difficulty.
    -   **Sensors:** No accelerometer/gyroscope integration for gesture-based scoring.

## 3. Technology Stack
-   **Framework:** React Native with Expo (Managed Workflow).
-   **Language:** TypeScript.
-   **Navigation:** React Navigation (Stack Navigator).
-   **State Management:** React Context API or Zustand.
-   **Persistence:** `expo-sqlite` (to maintain relational structure of Categories -> Charades).
-   **Sensors:** `expo-sensors` (Accelerometer/Gyroscope) for gameplay interactions.
-   **UI Library:** `react-native-paper` or standard `StyleSheet` with vector icons (`@expo/vector-icons`).

## 4. Functional Requirements

### 4.1. Phase 1: Migration & Core Data (CRUD)
**Goal:** Replicate existing functionality in the new framework.

#### 4.1.1. Home Screen (Categories List)
-   **UI:** Display a list of available categories.
-   **Actions:**
    -   Tap a category to select it (opens Game Setup or Details).
    -   "Edit" button on a category to open Category Editor.
    -   "Add New Category" floating action button (FAB).
-   **Data:** Fetch all categories from local DB.

#### 4.1.2. Category Editor
-   **UI:** Form to edit Category Name and Description.
-   **Charades List:** List of words associated with this category.
-   **Actions:**
    -   Add new word to category.
    -   Remove word from category.
    -   Save changes.
    -   Delete Category.

#### 4.1.3. Database & Seeding
-   **Schema:**
    -   `Category`: `id` (INT, PK), `name` (TEXT), `description` (TEXT).
    -   `Charade`: `id` (INT, PK), `name` (TEXT), `category_id` (INT, FK).
-   **Seeding:** On first launch, populate DB with default categories (e.g., "Movies", "Animals", "Science", "Actions").

### 4.2. Phase 2: Gameplay Implementation (New Features)
**Goal:** Implement the "Play" logic.

#### 4.2.1. Game Setup Screen
-   **Input:**
    -   Selected Category (passed from Home).
    -   Round Duration (e.g., 60s, 90s, 120s - Default: 60s).
-   **Action:** "Start Game" button.

#### 4.2.2. Game Screen (The "Charades" Mode)
-   **Orientation:** Force Landscape (preferred for forehead placement) or allow Portrait if UI supports it.
-   **States:**
    1.  **Get Ready:** 3-second countdown before timer starts.
    2.  **Active:**
        -   Display current word (Large Text).
        -   Display remaining time.
        -   **Sensor Interaction:**
            -   **Tilt Down (Forehead -> Floor):** "Correct" (Green background flash + Positive Sound).
            -   **Tilt Up (Forehead -> Ceiling):** "Pass" (Yellow/Red background flash + Negative Sound).
            -   **Neutral Position:** Ready for next tilt.
        -   **Touch Interaction (Accessibility):** Tap left/right or buttons for Correct/Pass.
    3.  **Time's Up:** Sound effect, vibration, transition to Results.

#### 4.2.3. Results Screen
-   **UI:**
    -   Total Score (Number of Correct guesses).
    -   List of words:
        -   Green checkmark for "Correct".
        -   Red dash/cross for "Passed".
-   **Actions:**
    -   "Play Again" (Restart same category).
    -   "Back to Home".

## 5. Non-Functional Requirements
-   **Performance:** Smooth 60fps animations for timer and tilt feedback.
-   **Offline:** App must work 100% offline.
-   **Permissions:** Request Motion Sensors permission on iOS (if required by OS version).

## 6. Quality Assurance & CI/CD
**Goal:** Ensure code stability and prevent regressions.

-   **Testing Strategy:**
    -   **Unit Tests:** Use `Jest` for business logic and utility functions.
    -   **Component Tests:** Use `react-test-renderer` or `@testing-library/react-native` for UI components.
    -   **Integration Tests:** Ensure Database and Navigation flows work as expected.
-   **CI/CD Pipeline:**
    -   Configure GitHub Actions to automatically run tests (`npm test`) on every Push and Pull Request.
    -   Ensure the build passes before merging changes.

## 7. Workflow Constraints
-   **Human-in-the-Loop:** The AI Agent is **strictly prohibited** from automatically committing code changes to the repository.
-   **Review Process:** All code generation, modification, or deletion must be presented to the user for review.
-   **Commit Policy:** Commits can only be executed after explicit user approval/confirmation of the changes.

## 8. UI/UX Guidelines
-   **Style:** Modern, vibrant, high contrast.
-   **Typography:** Large, legible fonts for the Game Screen (must be readable from 3-5 feet away).
-   **Feedback:** Haptic feedback (vibration) on Correct/Pass events.

## 9. Migration Plan (Step-by-Step)
1.  **Initialize Expo Project:** Setup TypeScript, Navigation, SQLite.
2.  **Database Layer:** Create DB service, tables, and seeding logic.
3.  **Screens - CRUD:** Implement Home and Edit screens.
4.  **Game Logic:** Implement Game Setup and Sensor logic.
5.  **Quality Assurance:** Set up Jest, write tests, and configure CI/CD.
6.  **Polishing:** Add assets (sounds, icons), transition animations, and final review.
