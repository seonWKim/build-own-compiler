#include "value.h"
#include "memory.h"

void initValueArray(ValueArray *array) {
    array->capacity = 0;
    array->count = 0;
    array->values = NULL;
}

void writeValueArray(ValueArray *array, Value value) {
    if (array->capacity < array->count + 1) {
        int oldCapacity = array->count;
        array->capacity = GROW_CAPACITY(oldCapacity);
        GROW_ARRAY(Value, array, oldCapacity, array->capacity);
    }

    array->values[array->count] = value;
    array->count += 1;
}

void freeArray(ValueArray *array) {
    FREE_ARRAY(Value, array->values, array->capacity);
    initValueArray(array);
}