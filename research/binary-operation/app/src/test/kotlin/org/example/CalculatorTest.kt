package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ParsingTest {

    @Test
    fun parse_test() {
        val expr1 = "1 + 1"
        val parseResult = listOf(
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "1"),
        )
        assertEquals(parseResult, parse(expr1))

        val expr2 = "1 + 2 * 3"
        val parseResult2 = listOf(
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
        )
        assertEquals(parseResult2, parse(expr2))

        val expr3 = "(1 + 2) * 3"
        val parseResult3 = listOf(
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
        )
        assertEquals(parseResult3, parse(expr3))

        val expr4 = "10 + 200 + 300"
        val parseResult4 = listOf(
            Token(TokenType.NUMBER, "10"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "200"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "300"),
        )
        assertEquals(parseResult4, parse(expr4))
    }

    @Test
    fun test_basic_eval() {
        val tokens1 = listOf(
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "2")
        )
        assertEquals(3, eval(tokens1))

        val tokens2 = listOf(
            Token(TokenType.NUMBER, "20"),
            Token(TokenType.MINUS, "-"),
            Token(TokenType.NUMBER, "10"),
        )
        assertEquals(10, eval(tokens2))

        val tokens3 = listOf(
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
        )
        assertEquals(6, eval(tokens3))

        val tokens4 = listOf(
            Token(TokenType.NUMBER, "10"),
            Token(TokenType.DIV, "/"),
            Token(TokenType.NUMBER, "2"),
        )
        assertEquals(5, eval(tokens4))
    }

    @Test
    fun test_eval_different_priority() {
        val tokens1 = listOf(
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "4"),
        )
        assertEquals(14, eval(tokens1))

        val tokens2 = listOf(
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "4"),
        )
        assertEquals(10, eval(tokens2))

        val tokens3 = listOf(
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "4"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "5"),
        )
        assertEquals(26, eval(tokens3))

        val tokens4 = listOf(
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "10"),
            Token(TokenType.DIV, "/"),
            Token(TokenType.NUMBER, "2"),
        )
        assertEquals(8, eval(tokens4))

        val tokens5 = listOf(
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "10"),
            Token(TokenType.DIV, "/"),
            Token(TokenType.NUMBER, "5")
        )
        assertEquals(6, eval(tokens5))
    }

    @Test
    fun test_eval_with_parentheses() {
        // (1 + 2) * 3 
//        val tokens1 = listOf(
//            Token(TokenType.PARENTHESES_OPEN, "("),
//            Token(TokenType.NUMBER, "1"),
//            Token(TokenType.PLUS, "+"),
//            Token(TokenType.NUMBER, "2"),
//            Token(TokenType.PARENTHESES_CLOSE, ")"),
//            Token(TokenType.MUL, "*"),
//            Token(TokenType.NUMBER, "3"),
//        )
//        assertEquals(9, eval(tokens1))

//        val tokens2 = listOf(
//            Token(TokenType.NUMBER, "1"),
//            Token(TokenType.PLUS, "+"),
//            Token(TokenType.PARENTHESES_OPEN, "("),
//            Token(TokenType.NUMBER, "2"),
//            Token(TokenType.PLUS, "+"),
//            Token(TokenType.NUMBER, "3"),
//            Token(TokenType.PARENTHESES_CLOSE, ")"),
//        )
//        assertEquals(6, eval(tokens2))
//
//        val tokens3 = listOf(
//            Token(TokenType.PARENTHESES_OPEN, "("),
//            Token(TokenType.NUMBER, "1"),
//            Token(TokenType.PLUS, "+"),
//            Token(TokenType.NUMBER, "2"),
//            Token(TokenType.PARENTHESES_CLOSE, ")"),
//            Token(TokenType.MUL, "*"),
//            Token(TokenType.PARENTHESES_OPEN, "("),
//            Token(TokenType.NUMBER, "3"),
//            Token(TokenType.PLUS, "+"),
//            Token(TokenType.NUMBER, "4"),
//            Token(TokenType.PARENTHESES_CLOSE, ")"),
//        )
//        assertEquals(21, eval(tokens3))
    }
}
