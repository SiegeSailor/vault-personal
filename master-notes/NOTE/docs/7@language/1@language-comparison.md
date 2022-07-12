---
sidebar_position: 1
---

# Language Comparison

The difference between programming languages.

## Type

Category of programming languages. A language is static/dynamic means when the information is acquired, if the code is interpreted immediately then it is dynamic, vice versa. A strong typed programming language means it rarely or not allow automatic implicit type conversion while a weak typed one allows implicit type conversion easily.

| Language   | Static | Dynamic | Strong | Weak | Special Note                   |
| ---------- | ------ | ------- | ------ | ---- | ------------------------------ |
| JavaScript |        | Yes     |        | Yes  |                                |
| TypeScript | Yes    |         | Yes    |      | The output is JavaScript code. |
| C++        | Yes    |         |        | Yes  |                                |
| Java       | Yes    |         | Yes    |      |                                |
| Python     |        | Yes     | Yes    |      |                                |

## Compilation

The following is the difference of the executing flow for different programming languages.

### JavaScript

JavaScript program is compiled **Just-In-Time**, which means it doesn't need to be compiled beforehand. In order to execute JavaScript programs, we need to use a JavaScript engine which is already embedded in every modern browser. There are engines like V8 for Chrome, Nitro for Safari, and SpiderMonkey for Mozilla, however, only V8 is used for JavaScript to execute without a browser due to modules such as `fs` and `Net` rely on the V8 interface between **C++** and **JavaScript**, which is called **Node.js**.

Since JavaScript and Node.js are both written in C/C++, technologies such as ASM.js and WASM is available to run C/C++ in JavaScript/Node.js.

```mermaid
flowchart TB
JP[\JavaScript Program/] --> Parser
DJ[Data for JavaScript Program] --> CM
Parser --> AST[Abstract Syntax Tree]
AST --> Interpreter[Interpreter/Ignition]
Interpreter --> |Immediately interpret| Bytecode
AST --> Profiler
Profiler --> Compiler[Compiler/TurboFan]
Compiler --> OC[Optimized Machine Code]
OC --> |Gradually replace| MI[Machine-Languages Instructions]
Bytecode --> MI
MI --> CM([Computer Execution of Machine-Language Instructions])
CM --> OJ[/Output of JavaScript Program\]
```

### C++

C++ programs are CPU-specified. Use `clang++ simple.cpp -o simple.out` to compile code in `simple.cpp` to a machine-language file `simple.out`. Since `simple.out` is a machine-language file, the outcome for using `./simple.out` to execute may be not supported or crashed if the user's CPU is different from the machine compiled the program.

```mermaid
flowchart TB
C[\C++ Program/] --> CP[C++ Preprocessor]
DC[Data for C++ Program] --> CM
CP --> CC[C++ Compiler]
CC --> AS[Assembler]
AS --> LI[Linker for Object Code for Library functions]
LI --> CM([Computer Execution of Machine-Language Instructions])
CM --> OJ[/Output of C++ Program\]
```

### Java

Use `javac Simple.java` to compile Java program into bytecode, which is not platform specified, so we can send/use it through internet. This is called **portability**. Then use `java Simple` to use the Java Virtual Machine, **JVM**, to compile the bytecode into machine-language only as needed and execute it (Just-In-Time). Compiled code will be stored for next use.

```mermaid
flowchart TB
JP[\Java Program/] --> JC[Java Compiler]
DJ[Data for Java Program] --> CM
JC --> BP[Bytecode Program]
BP --> BI[Bytecode Interpreter/JVM]
BI --> MI[Machine-Languages Instructions]
MI --> CM([Computer Execution of Machine-Language Instructions])
CM --> OJ[/Output of Java Program\]
```
