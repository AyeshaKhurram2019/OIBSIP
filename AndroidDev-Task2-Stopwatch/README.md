# ⏱️ Task 2: Stopwatch App

A sleek, modern, and precise Android Stopwatch application built using Java and XML as part of the **Oasis Infobyte Android Development Internship**.

---

## 📱 Features
* ⏱️ **High-Precision Timer:** Displays elapsed time with millisecond precision (`HH:MM:SS.ms`).
* ▶️ **Start / Pause Controls:** Easily start timing or pause and resume without losing elapsed time.
* 🔄 **Reset:** Clears the timer back to `00:00:00.00` and clears recorded laps.
* 🚩 **Lap Recording:** Capture splits and lap times dynamically in a scrollable list view.
* 🎛️ **Dynamic Button States:** Buttons dynamically enable/disable (e.g., Start disables when running; Pause/Lap enable) to prevent invalid interactions.
* 🌙 **Modern Dark Theme:** Optimized high-contrast UI for screen clarity.

---

## 🛠️ Tech Stack & Architecture
* **Language:** Java
* **UI Layout:** XML (`LinearLayout`, `ScrollView`, `TextView`, `Button`)
* **Core Logic:** Android `Handler`, `Runnable`, and `SystemClock.uptimeMillis()`
* **Target SDK:** 34 | **Min SDK:** 21
* **Build Automation:** GitHub Actions CI/CD (Gradle 8.4)

---

## 🚀 How to Run / Install
1. Go to the **Actions** or **Releases** tab of this repository.
2. Download **`Stopwatch-APK`** (`app-debug.apk`).
3. Install the APK on any Android device running Android 5.0 (API 21) or higher.
4. 
