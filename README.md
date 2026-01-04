# Charades Expo

![CI Status](https://github.com/darekdeo/charades/actions/workflows/ci.yml/badge.svg)
![Platform](https://img.shields.io/badge/platform-android%20%7C%20ios%20%7C%20web-blue)
![License](https://img.shields.io/badge/license-MIT-green)

A modern, cross-platform Charades application built with React Native and Expo. Challenge your friends to guess the word on the card that’s on your head from their clues before the timer runs out!

## 🚀 Features

-   **Multi-Platform Support:** Runs seamlessly on Android, iOS, and Web.
-   **Game Mode:**
    -   **Tilt Controls:** Tilt down for "Correct", tilt up for "Pass" (Mobile only).
    -   **Touch Controls:** Accessible on-screen buttons for all platforms.
    -   **Countdown & Timer:** Configurable round durations (60s, 90s, 120s).
-   **Category Management:**
    -   Create custom categories.
    -   Edit and manage your own word lists.
    -   Pre-seeded with popular categories: "Movies", "Animals", and "Actions".
-   **Rich Feedback:** Haptic feedback (vibrations) and sound effects for gameplay events.
-   **Offline First:** Fully functional without an internet connection using local SQLite storage.

## 🛠 Tech Stack

-   **Framework:** [Expo](https://expo.dev/) (React Native)
-   **Language:** TypeScript
-   **Navigation:** React Navigation v7
-   **UI Library:** React Native Paper (Material Design)
-   **Persistence:** Expo SQLite
-   **Sensors:** Expo Sensors (Accelerometer)

## 📦 Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/darekdeo/charades.git
    cd charades
    ```

2.  **Install dependencies:**
    ```bash
    npm install
    ```

3.  **Start the application:**
    ```bash
    npm start
    ```
    -   Press `a` for Android (requires emulator or device).
    -   Press `i` for iOS (requires simulator or device).
    -   Press `w` for Web.

## 🧪 Testing

The project uses **Jest** and **React Native Testing Library** for unit and component testing.

Run the test suite:
```bash
npm test
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/amazing-feature`).
3.  Commit your changes (`git commit -m 'feat: Add some amazing feature'`).
4.  Push to the branch (`git push origin feature/amazing-feature`).
5.  Open a Pull Request.

**Note:** All changes are verified via GitHub Actions CI.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
