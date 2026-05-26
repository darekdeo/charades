# Product Requirements Document (PRD): Charades App - Final Release

## 1. Executive Summary
**Project Name:** Charades Expo Migration
**Status:** ✅ **COMPLETED**
**Objective:** Migrate the existing native Android "Charades" application to a cross-platform React Native solution using the Expo framework. All core features have been successfully implemented, including the active gameplay loop, database persistence, and comprehensive testing. The project is now ready for release with enhanced graphics and polished UI/UX.

## 2. Current Status Analysis
The React Native application is **fully functional** with the following completed features:

### ✅ Implemented Features
- **Categories List (Home Screen):** Display all categories with edit/delete actions
- **Category Management:** Create/Edit categories with word management
- **Charade Management:** Add/Remove words within categories
- **Persistence:** SQLite database with proper seeding
- **Game Loop:** Complete gameplay experience
  - Game Setup with duration configuration
  - Active Game Screen with timer and word display
  - Results Screen with score summary
- **Testing:** Comprehensive test suite with Jest
- **CI/CD:** GitHub Actions pipeline configured

### ✅ Completed Components
- **Navigation:** React Navigation with Stack Navigator
- **Database:** `expo-sqlite` integration with proper schema
- **Screens:** All 5 screens implemented (Home, CategoryForm, GameSetup, Game, Results)
- **Tests:** Unit and component tests for all screens and database
- **Configuration:** Metro bundler, TypeScript, Jest setup

## 3. Technology Stack
- **Framework:** React Native with Expo (Managed Workflow)
- **Language:** TypeScript
- **Navigation:** React Navigation v6 (Stack Navigator)
- **State Management:** React Context API
- **Persistence:** `expo-sqlite`
- **UI Library:** `react-native-paper`
- **Icons:** `@expo/vector-icons`
- **Testing:** Jest + React Native Testing Library
- **CI/CD:** GitHub Actions

## 4. Functional Requirements (All Completed)

### 4.1. Home Screen (Categories List) ✅
- **UI:** Display a list of available categories with FAB
- **Actions:**
  - Tap a category to select it (opens Game Setup)
  - "Edit" button on a category to open Category Editor
  - "Add New Category" floating action button
- **Data:** Fetch all categories from local DB

### 4.2. Category Editor ✅
- **UI:** Form to edit Category Name and Description
- **Charades List:** List of words associated with this category
- **Actions:**
  - Add new word to category
  - Remove word from category
  - Save changes
  - Delete Category

### 4.3. Database & Seeding ✅
- **Schema:**
  - `Category`: `id` (INT, PK), `name` (TEXT), `description` (TEXT)
  - `Charade`: `id` (INT, PK), `name` (TEXT), `category_id` (INT, FK)
- **Seeding:** Default categories populated on first launch

### 4.4. Game Setup Screen ✅
- **Input:**
  - Selected Category (passed from Home)
  - Round Duration (60s, 90s, 120s - Default: 60s)
- **Action:** "Start Game" button

### 4.5. Game Screen ✅
- **Orientation:** Landscape preferred, Portrait supported
- **States:**
  1. **Get Ready:** 3-second countdown
  2. **Active:**
     - Display current word (Large Text)
     - Display remaining time
     - Touch interaction for Correct/Pass
  3. **Time's Up:** Transition to Results
- **Results:** Sound effects, vibration, transition to Results

### 4.6. Results Screen ✅
- **UI:**
  - Total Score (Number of Correct guesses)
  - List of words with checkmarks/crosses
- **Actions:**
  - "Play Again" (Restart same category)
  - "Back to Home"

## 5. UI/UX & Graphics Requirements (NEW)

### 5.1. Visual Design System
- **Color Palette:**
  - Primary: Vibrant purple (#6200EA)
  - Secondary: Teal (#00BFA5)
  - Success: Green (#00C853)
  - Error: Red (#D50000)
  - Background: Dark theme (#121212)
  - Surface: Dark gray (#1E1E1E)
- **Typography:**
  - Game Screen: Large, bold fonts (min 48px for word display)
  - Readable from 3-5 feet away
  - Sans-serif font family (Roboto/Inter)
- **Animations:**
  - Smooth 60fps transitions
  - Timer countdown animations
  - Word reveal animations
  - Feedback animations (Correct/Pass)

### 5.2. Graphics & Assets
- **Icons:** Custom vector icons for categories
- **Backgrounds:** Gradient backgrounds for game screen
- **Word Cards:** Card-based design with shadows and rounded corners
- **Feedback Visuals:**
  - Correct: Green flash with particle effects
  - Pass: Red/Yellow flash with shake animation
- **Loading States:** Skeleton screens for database queries
- **Error States:** Custom error illustrations

### 5.3. Accessibility
- **High Contrast:** Ensure text is readable in all lighting conditions
- **Touch Targets:** Minimum 48x48dp for all interactive elements
- **Screen Reader:** Proper ARIA labels and accessibility tree
- **Motion:** Respect reduced motion preferences

### 5.4. Performance Requirements
- **60fps:** Smooth animations on all supported devices
- **Memory:** Optimize database queries and component rendering
- **Battery:** Minimize sensor polling when not in active game
- **Startup:** Cold start under 3 seconds

## 6. Quality Assurance & CI/CD ✅

### 6.1. Testing Strategy
- **Unit Tests:** Jest for business logic and utility functions
- **Component Tests:** React Native Testing Library for UI components
- **Integration Tests:** Database and Navigation flows
- **Coverage:** Minimum 80% code coverage

### 6.2. CI/CD Pipeline
- **GitHub Actions:**
  - Run tests on every push and PR
  - Lint checks (ESLint, Prettier)
  - Build validation
  - Automated release preparation
- **Quality Gates:**
  - All tests must pass
  - No new warnings in ESLint
  - Code coverage threshold met

## 7. Migration Plan (COMPLETED)
1. ✅ **Initialize Expo Project:** Setup TypeScript, Navigation, SQLite
2. ✅ **Database Layer:** Create DB service, tables, and seeding logic
3. ✅ **Screens - CRUD:** Implement Home and Edit screens
4. ✅ **Game Logic:** Implement Game Setup and Gameplay logic
5. ✅ **Quality Assurance:** Set up Jest, write tests, and configure CI/CD
6. ✅ **Polishing:** Add assets, animations, and final graphics review

## 8. Release Checklist
- [x] All features implemented
- [x] All tests passing
- [x] CI/CD pipeline configured
- [x] Graphics and animations polished
- [x] Accessibility compliance verified
- [ ] Final user acceptance testing
- [ ] App Store submission preparation

## 9. Future Enhancements (Post-Release)
- [ ] Multiplayer mode
- [ ] Custom category sharing
- [ ] Cloud sync for categories
- [ ] Additional sensor integrations
- [ ] Sound customization
- [ ] Dark/Light theme toggle
