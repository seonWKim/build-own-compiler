#include <printf.h>
#include "common.h"
#include "chunk.h"
#include "debug.h"

int main(int argc, const char* argv[]) {
    Chunk chunk;
    initChunk(&chunk);

    int constant = addConstant(&chunk, 1.2);
    writeChunk(&chunk, OP_CONSTANT, 123);
    writeChunk(&chunk, constant, 123);

    int c1 = addConstant(&chunk, 1.3);
    int c2 = addConstant(&chunk, 1.4);
    int c3 = addConstant(&chunk, 1.5);
    writeChunk(&chunk, OP_CONSTANT_LONG, 124);
    writeChunk(&chunk, c1, 124);
    writeChunk(&chunk, c2, 124);
    writeChunk(&chunk, c3, 124);

    writeChunk(&chunk, OP_RETURN, 125);

    disassembleChunk(&chunk, "test chunk");
    freeChunk(&chunk);

    return 0;
}

