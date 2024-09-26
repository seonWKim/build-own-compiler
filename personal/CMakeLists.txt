cmake_minimum_required(VERSION 3.10)
project(clox)

# Gather all .c and .h files in the current directory
file(GLOB SOURCES "*.c" "*.h")

add_executable(clox ${SOURCES}
        vm.c
        compiler.h
        compiler.c
        scanner.c)