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
        val tokens1 = listOf(
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "3"),
        )
        assertEquals(9, eval(tokens1))

        // (1 + 2) * (3 + 4)
        val tokens2 = listOf(
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "1"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "4"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
        )

        // (5 + 10) / (2 + 3)
        val tokens3 = listOf(
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "5"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "10"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.DIV, "/"),
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
        )

        // (2 * (2 + 3)) * 2
        val tokens4 = listOf(
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.PARENTHESES_OPEN, "("),
            Token(TokenType.NUMBER, "2"),
            Token(TokenType.PLUS, "+"),
            Token(TokenType.NUMBER, "3"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.PARENTHESES_CLOSE, ")"),
            Token(TokenType.MUL, "*"),
            Token(TokenType.NUMBER, "2"),
        )
        assertEquals(20, eval(tokens4))
    }

    @Test
    fun complete_test() {
        val expr1 = "1 + 1"
        assertEquals(2, eval(parse(expr1)))

        val expr2 = "1 + 2 * 3"
        assertEquals(7, eval(parse(expr2)))

        val expr3 = "(1 + 2) * 3"
        assertEquals(9, eval(parse(expr3)))

        val expr4 = "10 + 200 + 300"
        assertEquals(510, eval(parse(expr4)))

        val expr5 = "10 + 200 * 300"
        assertEquals(60010, eval(parse(expr5)))

        // division test
        val expr6 = "10 / 2"
        assertEquals(5, eval(parse(expr6)))

        val expr7 = "10 / 2 * 3"
        assertEquals(15, eval(parse(expr7)))

        val expr8 = "20 / (2 * 5)"
        assertEquals(2, eval(parse(expr8)))

        val expr9 = "20 / (2 * 5) * 2"
        assertEquals(4, eval(parse(expr9)))

        val expr10 = "20 / ((2 * 5) * 2)"
        assertEquals(1, eval(parse(expr10)))
    }
}
