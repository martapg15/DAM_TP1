# Assignment TP1 - Kotlin Fundamentals and Virtual Library

**Course:** Mobile Application Development (DAM)  
**Student:** Marta Garcia (nº 51564)  
**Date:** 2026-03-08 <br>
**Repository URL:** https://github.com/martapg15/DAM_TP1

---

## 1. Introduction
This repository contains the Kotlin console-based exercises developed as part of TP1. The purpose of this assignment is to introduce the Kotlin programming language and reinforce core programming concepts required for Android development.

The exercises focus on learning Kotlin syntax, control flow structures, functional constructs, and Object-Oriented Programming (OOP). Students implement several small programs and a larger object-oriented system representing a Virtual Library Management System.

The main objectives of this assignment are:
- Become familiar with Kotlin language fundamentals.
- Practice working with arrays, ranges, and sequences.
- Implement console applications using control flow and exception handling.
- Apply object-oriented principles such as inheritance, encapsulation, and polymorphism.
- Design a small modular system that simulates a virtual library with different types of books and library operations.

## 2. System Overview
The Kotlin part of this project is organized as a set of independent console programs (each with its own `main`) plus a small object-oriented “Virtual Library” domain model.

### Exercises
- **Exercise 1 (Squares Array)** (`exer_1.kt`): generates and prints the squares of integers from 1 to 50 using different Kotlin approaches:
  - `IntArray` initializer
  - `map` over a range and conversion to `IntArray`
  - generic `Array`
- **Exercise 2 (Console Calculator)** (`exer_2.kt`): an interactive calculator that runs in a loop and supports:
  - arithmetic operators: `+`, `-`, `*`, `/` (with division-by-zero protection)
  - boolean operators: `&&`, `||`, unary `!`
  - bit shift operators: `shl`, `shr`
  - input validation and exception handling
  - an `exit` command to leave the program
- **Exercise 3 (Bouncing Ball Sequence)** (`exer_3.kt`): models successive bounce heights using `generateSequence`, printing bounce heights while they remain ≥ 1 meter (up to a maximum of 15 qualifying bounces).

### Virtual Library Management System
A small OOP system that models:
- **Books** with shared attributes (title, author, “era” classification, available copies)
- Two book types:
  - **DigitalBook** (includes file size and format)
  - **PhysicalBook** (includes weight and hardcover flag, with a secondary constructor for single-copy books)
- A **Library** that can:
  - add books
  - register members
  - borrow and return books
  - list its catalog
  - search books by author
- A **LibraryMember** record (implemented as a Kotlin `data class`) storing member identity and borrowed book titles.

A small demo program (`TestLib.kt`) creates a library, adds books, registers members, and exercises borrowing/returning and author search.

## 3. Architecture and Design
### Folder Structure
The Kotlin code is organized as a Maven/Kotlin project (as indicated by `pom.xml`) following the conventional layout:
- `src/main/kotlin/`
  - `org/example/dam/exer_1/` (Exercise 1)
  - `org/example/dam/exer_2/` (Exercise 2)
  - `org/example/dam/exer_3/` (Exercise 3)
  - `org/example/dam/exer_vl/` (Virtual Library)

### Design Choices
- **Separation by packages:** each exercise has its own package, keeping programs isolated and easy to run independently.
- **Virtual Library as an OO model:**
  - `Book` is an **abstract base class** defining common state and behavior and requiring a `getStorageInfo()` implementation from subclasses.
  - `DigitalBook` and `PhysicalBook` demonstrate **inheritance and polymorphism** via overridden methods.
  - Validation logic is centralized in `Book.availableCopies` via a **custom setter** (prevents negative inventory and warns when out of stock).
- **Data class for members:** `LibraryMember` is a `data class` for concise modeling of identity and borrowing state, and it automatically provides useful default functionality (`toString`, equality, etc.).

## 4. Implementation
### Exercise 1 - Squares generation (`exer_1.kt`)
The program demonstrates multiple Kotlin techniques to build arrays of computed values (squares from 1..50), highlighting Kotlin’s initializer lambdas and functional utilities.
```kotlin
val intArr = IntArray(50) { (it + 1) * (it + 1) }
val mapArr = (1..50).map { it * it }.toIntArray()
val arr = Array(50) { (it + 1) * (it + 1) }
```

### Exercise 2 - Console calculator (`exer_2.kt`)
The calculator is implemented as an interactive loop that:
- reads two operands (or one operand for unary `!`)
- validates input type (numeric or boolean)
- validates operator
- executes the operation using a `when` expression
- handles invalid input and runtime errors using `try/catch`

Key helpers:
- `getValidInput(prompt: String)`: ensures the user enters a number or boolean (or `exit`)
- `getValidOperator(prompt: String)`: ensures the operator is one of `+ - * / && || ! shl shr` (or `exit`)

The numeric operations format output both as:
- decimal (2 decimal places)
- hexadecimal (via `toInt().toString(16).uppercase()`)

### Exercise 3 - Bouncing ball (`exer_3.kt`)
A sequence is created with `generateSequence`, applying a constant bounce ratio (`* 0.6`). The pipeline then:
- drops the initial height
- takes bounces while ≥ 1 meter
- limits output to 15 bounces
```kotlin
val sequence = generateSequence(initialHeight) { previousHeight -> previousHeight * 0.6 }
val result = sequence.drop(1).takeWhile { it >= 1.0 }.take(15).toList()
```

### Virtual Library - domain model and operations (`exer_vl`)
- `Book`:
  - stores `title`, `author`, `_publicationYear`
  - exposes `publicationYear` as an **era label**: `"Classic"`, `"Modern"`, `"Contemporary"`
  - validates `availableCopies` via setter
  - defines abstract `getStorageInfo()`
- `DigitalBook` / `PhysicalBook`:
  - implement `getStorageInfo()` with format-specific details
  - override `toString()` to include storage info
- `Library`:
  - stores `books` and `members`
  - implements `addBook`, `registerMember`, `borrowBook`, `returnBook`, `searchByAuthor`
  - maintains a `companion object` counter (`totalBooksAdded`)
- `TestLib.kt`:
  - serves as a runnable demonstration and manual validation script for the Virtual Library behavior.

## 5. Testing and Validation
Testing for this Kotlin console assignment was primarily **manual**, by running each `main` function and validating output behavior.

- **Exercise 1:** verified that the arrays print 50 values corresponding to squares from `1²` to `50²`.
- **Exercise 2 (Calculator):**
  - verified correct results for arithmetic operations (`+`, `-`, `*`, `/`)
  - verified division-by-zero handling (throws and reports an error)
  - verified boolean operations (`&&`, `||`, `!`) with strict parsing via `toBooleanStrict()`
  - verified bit shifts (`shl`, `shr`) and hex formatting
  - verified input validation loops (rejects invalid input/operator) and correct handling of `exit`
- **Exercise 3:** verified that the bounce heights decrease by a factor of 0.6 and stop once values drop below 1 meter (or after 15 bounces).
- **Virtual Library (via `TestLib.kt`):**
  - verified borrowing decreases available copies and prevents borrowing when stock reaches 0
  - verified returning increases available copies
  - verified member borrowed list is updated
  - verified author search outputs matching titles

Known limitations:
- The calculator accepts “number or boolean” as input, but numeric operations assume values can be parsed as `Double`, and shift operations assume values can be parsed as `Int` (invalid combinations are handled via exceptions and retry).
- The library stores `borrowedBooks` as a list of **titles** (strings), not book IDs (fine for a small simulation, but ambiguous if duplicate titles exist).

## 6. Usage Instructions
### Requirements
- Kotlin/JVM environment (run from IntelliJ IDEA or via Maven project configuration).
- A JDK installed (e.g., JDK 17+ is commonly used for Kotlin/JVM projects).

### How to run
1. Clone the repository:
   - `git clone https://github.com/martapg15/DAM_TP1`
2. Open the project in IntelliJ IDEA.
3. Navigate to `src/main/kotlin/`.
4. Run any of the following entry points:
   - `org.example.dam.exer_1.main` (Exercise 1)
   - `org.example.dam.exer_2.main` (Exercise 2)
   - `org.example.dam.exer_3.main` (Exercise 3)
   - `org.example.dam.exer_vl.main` in `TestLib.kt` (Virtual Library demo)

Notes:
- For the calculator (Exercise 2), type `exit` at any prompt to terminate the program.

---

# Development Process

## 12. Version Control and Commit History

Version control was used throughout the development of this project to document the progression of the Kotlin exercises. The commit history begins with the initialization of the project structure and configuration, followed by incremental commits corresponding to the implementation of each exercise. A final commit includes additional refinements and improvements to the `Virtual Library` implementation.

## 13. Difficulties and Lessons Learned

- **Companion objects:** Initially I didn’t understand what a `companion object` was, but after using it in `Library` I learned it provides class-level variables/functions (similar to `static`), which was useful to track totals like `totalBooksAdded`.
- **Infinite sequences (Exercise 3):** My code seemed to freeze because the sequence was effectively infinite and my filtering approach didn’t stop evaluation. I fixed it by switching to `takeWhile`, which stops the sequence once the bounce height drops below the minimum.
- **Constructors in Kotlin:** I learned how to work with a **primary** and a **secondary constructor**, and how a secondary constructor can enforce alternative initialization rules (e.g., creating a `PhysicalBook` with a default of only one available copy).

## 14. Future Improvements

- Refactor the **calculator** to separate numeric vs boolean operations and add more operators.
- Improve the Virtual Library model by using **unique book identifiers** (instead of only titles) and validating returns/borrows more strictly.
- Add a simple **interactive menu** for the library (instead of only `TestLib.kt`) and enhance search options.
- Persist data by saving/loading the library catalog and members (e.g., JSON file).

## 15. AI Usage Disclosure (Mandatory)

In this part of the assignment, the use of AI tools for code generation was explicitly forbidden ([AC NO, AI NO]). Therefore, all code in this repository was implemented manually by the student.

However, AI tools were used only to assist in revising and improving the wording of the README report, as permitted by the assignment guidelines. I remain fully responsible for the accuracy and content of this documentation.
