# MiniBank: Enterprise Console Banking Architecture

MiniBank is a semester-long, modular console banking application built incrementally across Practicals 1 to 14 of CEUE203 (Object-Oriented Programming).

## Architecture Overview
MiniBank adopts a clean 3-Tier Layered Architecture:
- **Presentation Tier**: Menu driven console CLI (`MiniBankApp`)
- **Service Tier**: Transaction boundary, business rules (`BankService`)
- **Repository Tier**: Type-safe generic storage (`Repository<T>`)
- **Domain Model**: Strongly encapsulated records, enums, abstract classes (`Account`, `SavingsAccount`, `CurrentAccount`, `FixedDepositAccount`, `Customer`)
- **Cross-Cutting Utilities**: Regex validation, reflection validators, exception hierarchies, NIO file persisters, ExecutorService concurrency engine.

## Repository
Footer Link: https://github.com/25csHenil/OOP
