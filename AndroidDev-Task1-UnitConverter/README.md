# 📱 Task 1: Unit Converter Application

A versatile and user-friendly Android application designed to perform real-time conversions between common units of measurement across multiple categories. 

This project was developed as part of the **Oasis Infobyte Virtual Internship Program (OIBSIP)** under the **Android Development** track.

---

## 🛠️ Tech Stack & Prerequisites

* **Language:** Java
* **User Interface:** XML Layouts (Material Design elements)
* **Minimum SDK:** API Level 21 (Android 5.0 Lollipop) or higher
* **Target SDK:** API Level 34 / 35
* **Architecture Model:** Single Activity View Architecture

---

## ✨ Features & Checklist

- [x] **Category Selector:** Switch dynamically between **Length**, **Weight**, and **Temperature**.
- [x] **Dynamic Dropdowns:** Source and target unit dropdowns (`Spinner` widgets) automatically update based on the selected category.
- [x] **Numeric Input Field:** Dedicated `EditText` field configured for decimal numbers.
- [x] **Input Validation:** Error handling via `Toast` notifications when input fields are empty or formatted incorrectly.
- [x] **Conversion Engine:** Computes exact conversions instantly upon clicking the **Convert** button.
- [x] **Formatted Output Display:** Displays converted values rounded to two decimal places alongside unit labels.

---

## 📐 Supported Units & Conversion Formulas

| Category | Supported Units | Formula Base |
| :--- | :--- | :--- |
| **Length** | Centimeters, Meters, Inches, Feet | Normalized to Meters |
| **Weight** | Grams, Kilograms, Pounds | Normalized to Kilograms |
| **Temperature** | Celsius, Fahrenheit, Kelvin | Direct mathematical formulas |

---

## 📂 Project Structure

```text
OIBSIP/
└── AndroidDev-Task1-UnitConverter/
    ├── MainActivity.java         # Handles UI events, Spinner listeners, and conversion logic
    ├── activity_main.xml         # XML UI layout containing Spinners, EditText, and Buttons
    └── README.md                 # Detailed task overview and implementation documentation
    
