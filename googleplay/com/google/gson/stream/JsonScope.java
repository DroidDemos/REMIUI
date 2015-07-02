package com.google.gson.stream;

enum JsonScope {
    EMPTY_ARRAY,
    NONEMPTY_ARRAY,
    EMPTY_OBJECT,
    DANGLING_NAME,
    NONEMPTY_OBJECT,
    EMPTY_DOCUMENT,
    NONEMPTY_DOCUMENT,
    CLOSED
}
