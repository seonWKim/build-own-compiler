cmake_minimum_required(VERSION 3.10)

# set the project name
project(MyCompiler)

# specify the C++ standard
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_STANDARD_REQUIRED True)

# add the executable
add_executable(MyCompiler src/lexer.cpp
        ../../personal/main.c
        ../../personal/chunk.h
        ../../personal/chunk.c
        ../../personal/chunk.c
)