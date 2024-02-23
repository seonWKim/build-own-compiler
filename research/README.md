# Overview of How to build own compiler 
1. Define the grammar of the language 
2. Lexical analysis: read the source code and break it down into tokens 
3. Parsing: parse the tokens and analyze the structure of the programming language 
4. Semantic analysis: analyze the meaning of the program
5. Intermediate code generation: generate intermediate code
6. Code optimization: optimize the intermediate code
7. Code generation: generate the target code

# Selecting target language 
- [ ] Assembly language: hard, portability issue 
- [ ] C/C++: good performance, well integrated  
- [X] LLVM(Low-Level Virtual Machine): provides a versatile and efficient platform for compiling languages, allows for efficient optimization and support for multiple target architectures

# LLVM 
- LLVM is the name of the project, examples of sub-projects are 
  - LLVM Core libraries 
    - provide a modern source and target independent optimizer 
    - code generation support for popular CPUs
    - easy to invent your own langauge using LLVM as an optimizer and code generator 
  - Clang 
    - LLVM native C/C++/Objective-C compiler
  - LLDB 
    - native debugger for LLVM
