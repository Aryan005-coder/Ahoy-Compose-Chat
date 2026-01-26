<div align="center">

# 🚀 Ahoy — Jetpack Compose Chat App

**A modern, scalable Android chat application built with Jetpack Compose  
focused on clean architecture, state-driven navigation, and real-world patterns.**

</div>

---

## ✨ Overview

**Ahoy** is not a demo or tutorial clone.  
It is a **production-focused chat app foundation** designed to scale — clean UI, predictable state management, and architecture that won’t collapse as features grow.

Built intentionally to learn **how real apps are structured**, not just how screens look.

---

## 🧠 Core Principles

- **State > Screens**
- **Navigation driven by state, not hacks**
- **Composable UI with minimal coupling**
- **Readable, scalable, production-first code**

---

## ✨ Features

### 📱 UI & Navigation
- Jetpack Compose with **Material 3**
- State-driven navigation using **Navigation Compose**
- Clear separation of **Screens, Routes, Navigation, and ViewModels**

### 🔐 Authentication
- Auth flow powered by **ViewModel-managed state**
- No UI-based navigation decisions
- Single source of truth for auth state

### 💬 Chat Experience
- Dashboard with chat list UI
- Chat screen with:
  - Message list
  - Input handling
  - Reactive updates via `StateFlow`

### 🧩 Architecture
- **MVVM + Repository**
- Reactive UI using **StateFlow**
- Designed for long-term scalability
- Avoids over-engineering while staying production-ready

---

## 🛠 Tech Stack

| Layer | Technology |
|------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose (Material 3) |
| Navigation | Navigation Compose |
| Architecture | MVVM |
| State | StateFlow |
| Backend | Firebase (Auth + Firestore) |
| Build System | Gradle (KTS) |

---

## 📂 Project Structure

```text
com.example.ahoy
│
├── authentication        # Auth screens, state & ViewModel
├── navigation            # AppNavigation & routes
├── screens               # Dashboard & Chat UI
├── data                  # Models & DTOs
├── repository            # Data repositories
├── ui.theme              # Material theme & styling
└── MainActivity.kt       # App entry point
