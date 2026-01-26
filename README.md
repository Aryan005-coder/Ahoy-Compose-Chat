Ahoy – Jetpack Compose Chat App 🚀

Ahoy is a modern Android chat application built using Jetpack Compose and Navigation Compose, focused on clean UI, state-driven navigation, and production-ready architecture.

This project is actively developed as a real-world chat app foundation, not a demo or tutorial clone.

✨ Features

📱 Modern UI built with Jetpack Compose (Material 3)

🧭 State-driven navigation using Navigation Compose

🔐 Authentication flow using ViewModel-managed auth state

🧩 Clean separation of Screens, Navigation, Routes, and ViewModels

🗂 Dashboard screen with chat list UI

💬 Chat screen with message list & input handling

🔁 Reactive UI using StateFlow

🧠 Architecture designed for scalability (MVVM + Repository)

🛠 Tech Stack

Language: Kotlin

UI: Jetpack Compose (Material 3)

Navigation: Navigation Compose

Architecture: MVVM

State Management: StateFlow

Build System: Gradle (KTS)

📂 Project Structure
com.example.ahoy
│
├── authentication   # Auth screen, state & ViewModel
├── navigation       # AppNavigation & routes
├── screens          # Dashboard & Chat UI
├── data             # Models
├── repository       # Data repositories
├── ui.theme         # App theme
└── MainActivity.kt  # App entry point

🔐 Firebase Setup (Required)

This project uses Firebase for authentication.

To run the app locally:

Create a Firebase project

Add an Android app with the same package name

Download google-services.json

Place it inside the app/ directory

⚠️ google-services.json is intentionally not committed for security reasons.

🚧 Work in Progress

 Auth flow with state-based navigation

 Dashboard & Chat UI

 Clean navigation architecture

 Dashboard ViewModel & repository hookup

 Real-time chat messages

 Firestore integration

 Message delivery states (sent / delivered / read)

🧠 Learning Goals

This project focuses on:

Building state-driven Compose UIs

Proper navigation without tight coupling

Clean separation of UI, logic, and state

Writing Android code that scales beyond MVPs

Avoiding over-engineering while staying production-ready

▶️ How to Run

Clone the repository

Open in Android Studio

Add google-services.json (see Firebase Setup)

Sync Gradle

Run on emulator or device (API 26+)

📌 Status

🚀 Core architecture complete
Actively developing real-time features and backend integration.

📄 License

This project is open-source and intended for learning and experimentation.
