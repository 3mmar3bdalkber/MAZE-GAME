<h1 align="center">Maze Game</h1>
<p align="center"> <strong>An interactive maze-solving game built with Java Swing featuring dynamic level progression, scoring system, and real-time gameplay.</strong> </p><p align="center"> <!-- Shields --> <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"> <img src="https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white" alt="Swing"> <img src="https://img.shields.io/badge/OOP-4A90E2?style=for-the-badge&logo=java&logoColor=white" alt="OOP"> <img src="https://img.shields.io/badge/Game%20Development-FF6B6B?style=for-the-badge&logo=unity&logoColor=white" alt="Game Dev"> <img src="https://img.shields.io/badge/License-MIT-2ea44f?style=for-the-badge" alt="License"> </p><p align="center"> <a href="#-features">Features</a> • <a href="#-gameplay">Gameplay</a> • <a href="#-installation">Installation</a> • <a href="#-project-structure">Structure</a> • <a href="#-how-to-play">How to Play</a> • <a href="#-development-team">Team</a> </p>

---

## Features
 Core Gameplay
- 3 Difficulty Levels (Easy, Medium, Hard)

- Dynamic Maze Generation using DFS algorithm

-Real-time Scoring System with time penalty

-Player Movement with arrow key controls

-Auto-solve feature with score penalty

---

## Game Mechanics

Score System: Starts at 1000, decreases by 10/second

Time Bonus: Faster completion = higher bonus

Level Progression: Maze size increases with level

Visual Path Display: Shows solution path

Win Condition: Reach the green flag goal

---

## UI/UX Features
Modern Dark Theme with purple accent

Rounded Window Corners (custom JFrame)

Draggable Window for easy positioning

Real-time Statistics Panel

Level Selection Dialog with preview

Interactive Tutorial Panel

 Technical Highlights
Object-Oriented Design with MVC pattern

Thread-safe Timers for score decrement

Memory-efficient Maze Generation

Collision Detection with wall boundaries

Custom Graphics Rendering

---

## Gameplay
Objective
Navigate your red player character through randomly generated mazes to reach the green goal flag before time runs out!

Controls
↑ Move Up

↓ Move Down

← Move Left

→ Move Right

Scoring
Starting Score: 1000 points

Time Penalty: -10 points/second

Movement Bonus: +1 point/move

Solve Penalty: -50 points

Level Bonus: +500 points × level

Time Bonus: Up to 10,000 points

Level System
Level	Difficulty	Maze Size	Description
1	Easy	12×12	Beginner friendly
2	Medium	16×16	Increased complexity
3	Hard	20×30	Expert challenge

---

## File Structure Setup

```bash
maze-adventure/
├── src/
│   ├── model/
│   │   ├── GameState.java
│   │   ├── Level.java
│   │   └── Player.java
│   ├── game/
│   │   ├── MazePanel.java
│   │   ├── RightSidePanel.java
│   │   ├── LevelSelection.java
│   │   ├── MazeGenerator.java
│   │   └── MazeSolver.java
│   ├── Main.java
│   ├── navPanel.java
│   └── maze2.java
└── assets/ (optional for custom images)

```
---

##  Contributors

**Team Name** : **MAZE**

**Ammar Abdalkber Abdelqader** 

**Ahmed Rabee Mohamed** 

**Ahmed Mohamed Khalaf**

**Mohamed Khalaf Apas**

**Mohamed Ali Mo3oud**

**John Hany**

<p align="center"> <strong>Enjoy navigating through the mazes! </strong><br/> </p><p align="center"> <a href="#top">Back to top ↑</a> </p>
