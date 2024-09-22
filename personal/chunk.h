#ifndef clox_chunk_h
#define clox_chunk_h

#include "common.h"
#include "value.h"

/**
 * Opcodes
 *
 * - Opcodes has operands, and each opcodes relates to the instruction format
 */
typedef enum {
    OP_CONSTANT,
    OP_RETURN
} OpCode;


// Array of bytes, should be dynamic in nature
typedef struct {
    int count;
    int capacity;
    uint8_t* code;
    ValueArray constants;
} Chunk;

void initChunk(Chunk* chunk);
void writeChunk(Chunk* chunk, uint8_t byte);
int addConstant(Chunk* chunk, Value value);
void freeChunk(Chunk* chunk);

#endif