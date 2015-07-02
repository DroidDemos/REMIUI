package com.xiaomi.kenai.jbosh;

interface BodyParser {
    BodyParserResults parse(String str) throws BOSHException;
}
