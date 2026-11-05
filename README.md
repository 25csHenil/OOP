# Object-Oriented Programming (CEUE203) - Lab Practicals & Mini Project

**Charotar University of Science and Technology (CHARUSAT)**  
**Faculty of Technology and Engineering (FTE)**  
**Department of Computer Science & Engineering**  
*Semester: 3rd, 2026-27 (ODD)*  
*Student Name:* **Henil**  
*Roll / ID:* **25CS108**  
*Repository Link:* [https://github.com/25csHenil/OOP](https://github.com/25csHenil/OOP)

---

## 📌 Repository Overview

This repository contains the complete implementation of all **14 Laboratory Practicals** and the semester-long **MiniBank Capstone Mini Project** for CEUE203 (Object-Oriented Programming).

Every practical includes:
1. Complete, working **Java source code** (`.java`)
2. Verified **execution outputs**
3. Comprehensive **technical Q&A and viva preparation**
4. Professional **Microsoft Word document (`.docx`)** with the repository link in the footer of every page.

---

## 📂 Directory Structure

```tree
OOP/
├── README.md                              <- Master project & practicals documentation
├── .gitignore
├── Word_Documents/                        <- Formatted Microsoft Word files (.docx)
│   ├── Practical_01.docx
│   ├── Practical_02.docx
│   ├── Practical_03.docx
│   ├── Practical_04.docx
│   ├── Practical_05.docx
│   ├── Practical_06.docx
│   ├── Practical_07.docx
│   ├── Practical_08.docx
│   ├── Practical_09.docx
│   ├── Practical_10.docx
│   ├── Practical_11.docx
│   ├── Practical_12.docx
│   ├── Practical_13.docx
│   ├── Practical_14.docx
│   └── Mini_Project_MiniBank.docx        <- Master Capstone Project Report
├── practicals/
│   ├── practical-01/                      <- Enums, Records, Switch Expressions & Interactive Shell
│   ├── practical-02/                      <- Constructors, Chaining & Encapsulation
│   ├── practical-03/                      <- Object Methods (toString, equals, hashCode, clone) & Nested Classes
│   ├── practical-04/                      <- Regex, Tokenizing, StringBuilder & Command Records
│   ├── practical-05/                      <- Inheritance, Abstract Classes & Dynamic Polymorphism
│   ├── practical-06/                      <- Interfaces, Default Methods, Lambdas & Modular Packaging
│   ├── practical-07/                      <- Annotations (@Id, @Positive) & Reflection Engine
│   ├── practical-08/                      <- Custom Exceptions & try-with-resources (Milestone 1)
│   ├── practical-09/                      <- Multithreading, Race Conditions & Synchronization
│   ├── practical-10/                      <- Thread Pools, Producer-Consumer & Deadlock Avoidance
│   ├── practical-11/                      <- Serialization & Java NIO Log Reporting
│   ├── practical-12/                      <- Collections Framework, Comparators & Concurrency (Milestone 2)
│   ├── practical-13/                      <- Generics, Bounded Types & Generic Repository<T> Pattern
│   └── practical-14/                      <- Layered Architecture, Automated Testing & Packaging (Milestone 3)
└── minibank-project/                      <- Production-Grade Modular MiniBank Project
    ├── README.md
    └── src/com/minibank/
        ├── model/                        <- Account, Savings, Current, FixedDeposit, Customer, Records
        ├── model/annotation/             <- Custom @Id, @Positive, @MaxLength
        ├── exception/                    <- BankException hierarchy
        ├── repository/                   <- Generic Repository<T> using Reflection
        ├── service/                      <- BankService transaction coordinator
        ├── util/                         <- Validator, StatementFormatter, StatePersister, etc.
        └── main/                         <- MiniBankApp CLI Entrypoint
```

---

## 📑 Practical Syllabus & Deliverables

| Practical | Title | Key Topics | CO / PO | Word Document |
|:---:|:---|:---|:---:|:---:|
| **01** | Modern Java Basics & Shell | Enums, Records, Switch Expressions with `yield`, Menu Shell | CO1 / PO1-PO3 | [Practical_01.docx](Word_Documents/Practical_01.docx) |
| **02** | Classes & Encapsulation | Encapsulated classes, Constructor Chaining (`this()`), Static members | CO1 / PO1-PO3 | [Practical_02.docx](Word_Documents/Practical_02.docx) |
| **03** | Object Methods & Nested Classes | Overriding `equals`, `hashCode`, `toString`, `clone()`, `instanceof` | CO1 / PO1-PO3 | [Practical_03.docx](Word_Documents/Practical_03.docx) |
| **04** | Strings & Regular Expressions | Compiled `Pattern` / `Matcher`, Tokenising, `StringBuilder`, Records | CO1 / PO1-PO3 | [Practical_04.docx](Word_Documents/Practical_04.docx) |
| **05** | Inheritance & Polymorphism | Abstract classes, dynamic method dispatch, pattern matching `instanceof` | CO1, CO2 / PO1-PO3 | [Practical_05.docx](Word_Documents/Practical_05.docx) |
| **06** | Interfaces & Lambdas | Default methods, functional interfaces, lambdas, marker interfaces | CO2 / PO1, PO3, PSO2 | [Practical_06.docx](Word_Documents/Practical_06.docx) |
| **07** | Annotations & Reflection | Custom annotations (`@Id`, `@Positive`), Runtime Reflection | CO1, CO2 / PO2, PO3, PSO2 | [Practical_07.docx](Word_Documents/Practical_07.docx) |
| **08** | Exception Handling | Custom checked exceptions (`InsufficientFundsException`), `try-with-resources` | CO3 / PO3-PO5 | [Practical_08.docx](Word_Documents/Practical_08.docx) |
| **09** | Multithreading & Sync | `Runnable`, thread lifecycle, race conditions, synchronized monitors | CO4 / PO5, PSO1 | [Practical_09.docx](Word_Documents/Practical_09.docx) |
| **10** | Advanced Concurrency | `ExecutorService` thread pools, Producer-Consumer `wait/notify`, Deadlock prevention | CO4 / PO5, PO11, PSO1 | [Practical_10.docx](Word_Documents/Practical_10.docx) |
| **11** | File I/O & Java NIO | Object serialization, `Path`/`Files`, append-only audit logs, `DirectoryStream` | CO5 / PO4, PO5, PSO1, PSO2 | [Practical_11.docx](Word_Documents/Practical_11.docx) |
| **12** | Collections Framework | `HashMap`, `ArrayList`, sorting with `Comparable` & `Comparator`, `ConcurrentHashMap` | CO6 / PO5, PSO1, PSO2 | [Practical_12.docx](Word_Documents/Practical_12.docx) |
| **13** | Generics & Repository | Generic classes, bounded types `<N extends Number>`, Generic `Repository<T>` | CO6 / PO4, PO5, PSO1, PSO2 | [Practical_13.docx](Word_Documents/Practical_13.docx) |
| **14** | Capstone System Integration | 3-tier layered architecture, unit testing, runnable JAR packaging | CO1-CO6 / PO3, PO5, PO11 | [Practical_14.docx](Word_Documents/Practical_14.docx) |

---

## 🏦 Mini Project: MiniBank Enterprise Core

**MiniBank** is a comprehensive, thread-safe console banking platform developed incrementally across the semester. It serves as the final capstone project demonstrating real-world software engineering practices.

### Features
- **3-Tier Architecture**: Clean boundary between Presentation, Service, and Repository tiers.
- **Account Hierarchies**: Supports `SavingsAccount`, `CurrentAccount` with overdraft protection, and `FixedDepositAccount`.
- **Fault Tolerance**: Custom checked exception hierarchy preventing system crashes.
- **Thread Safety**: Inter-account atomic transfers guarded against deadlocks through global lock ordering.
- **Generic Data Access**: Generic `Repository<T>` discovering primary keys dynamically via reflection on `@Id`.
- **Persistence**: File storage using binary object serialization and NIO log auditing.

### How to Run MiniBank

```bash
# Navigate to the minibank-project folder
cd minibank-project

# Compile all source files
javac -d bin src/com/minibank/model/annotation/*.java src/com/minibank/model/*.java src/com/minibank/exception/*.java src/com/minibank/repository/*.java src/com/minibank/util/*.java src/com/minibank/service/*.java src/com/minibank/main/*.java

# Run the MiniBank application
java -cp bin com.minibank.main.MiniBankApp
```

---

## 📄 Footer Note
*Repository:* [https://github.com/25csHenil/OOP](https://github.com/25csHenil/OOP)  
*All .docx reports contained in `Word_Documents/` include this repository link in the header/footer of each document.*
