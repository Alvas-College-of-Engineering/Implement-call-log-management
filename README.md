# 📱 Call Log Management System

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/UI-Swing-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Pattern-MVC-green?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge" />
</p>

> **A Professional Java Desktop Application**  
> An advanced implementation demonstrating modern GUI development, enterprise design patterns, and real-time data visualization.

<img width="980" height="592" alt="585838442-5164cb0a-b612-4666-a3e3-fb9b01940bb9" src="https://github.com/user-attachments/assets/b068ada7-a9f3-4d0e-8bd7-b35b70ac6096" />


## 🎯 Project Overview

The **Call Log Management System** is a feature-rich desktop application built with Java Swing. It allows users to manage, view, and analyze call history through a clean, intuitive interface. The project highlights the power of the **MVC (Model-View-Controller)** architecture and custom Java graphics.

### ✨ Key Highlights
*   🎨 **Professional GUI:** Custom-styled Swing components with responsive layouts.
*   📊 **Real-time Analytics:** Dynamic pie chart visualization using `Graphics2D`.
*   🔍 **Smart Filtering:** Live search and category-based filtering (Incoming/Outgoing/Missed).
*   🏗️ **Clean Architecture:** Strict separation of concerns for easy scalability.
*   🎯 **Advanced Java Tech:** Uses Reflection API, custom Cell Renderers, and Type-Safe Collections.

---

## 📁 Project Structure
```text
calllog/
├── main/
│   ├── Main.java                # Console-based entry point
│   └── UIMain.java              # Swing GUI entry point
├── model/
│   └── Call.java                # Data model (POJO)
├── service/
│   └── CallLogManager.java      # Call storage & management logic
├── ui/
│   ├── CallLogUI.java           # Main window & UI layout orchestration
│   ├── CallLogController.java   # Business logic & event handling
│   ├── CallStatisticsPanel.java # Graphics2D pie chart component
│   ├── SearchFilterPanel.java   # Real-time search UI
│   ├── CallTypeRenderer.java    # Custom table cell coloring
│   └── CallLogUIUtil.java       # UI helper functions
└── util/
    └── CallType.java            # Enum definitions

