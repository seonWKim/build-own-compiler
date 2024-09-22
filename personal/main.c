#include <printf.h>
#include "common.h"
#include "chunk.h"
#include "debug.h"
#include "vm.h"

int main(int argc, const char* argv[]) {
    initVM();

    Chunk chunk;
    initChunk(&chunk);

#define WRITE_CONSTANT(chunk, value, line1, line2) \
    writeChunk(&chunk, OP_CONSTANT, line1);    \
    writeChunk(&chunk, addConstant(&chunk, value), line2);

    WRITE_CONSTANT(chunk, 1, 123, 123);
    WRITE_CONSTANT(chunk, 2, 124, 124);
    writeChunk(&chunk, OP_NEGATE, 124);

    WRITE_CONSTANT(chunk, 3, 125, 125);
    WRITE_CONSTANT(chunk, 4, 126, 126);
    writeChunk(&chunk, OP_ADD, 127);

    writeChunk(&chunk, OP_RETURN, 126);
    interpret(&chunk);

    freeVM();
    freeChunk(&chunk);
    return 0;
}
