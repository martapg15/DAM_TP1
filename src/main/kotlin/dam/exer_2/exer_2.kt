package org.example.dam.exer_2

fun main() {
    calculator()
}

fun calculator() {
    println("--- Initiating Console Calculator ---")
    println("Type 'exit' to leave the calculator at any time.")

    while(true) {
        try {
            val input1 = getValidInput("Enter the first value (Number or Boolean): ")

            if (input1 == "exit") {
                print("Goodbye!")
                break
            }

            val operator = getValidOperator("Enter an operator (+, -, *, /, &&, ||, !, shl, shr): ")

            // The NOT operation is handled separated from the rest, because it only needs 1 value
            if (operator == "!") {
                val result = !input1.toBooleanStrict()
                println("Result: !$input1 = $result")
                println("--- Calculation Finished ---")
                continue
            }

            if (operator == "exit") {
                print("Goodbye!")
                break
            }

            val input2 = getValidInput("Enter the second value: ")

            if (input2 == "exit") {
                print("Goodbye!")
                break
            }

            when (operator) {
                "+", "-", "*", "/" -> {
                    val num1 = input1.toDouble()
                    val num2 = input2.toDouble()

                    val result = when (operator) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> {
                            if (num2 == 0.0) throw ArithmeticException("Division by zero")
                            num1 / num2
                        }
                        else -> 0.0
                    }

                    println("Decimal result: %.2f".format(result))
                    println("Hexadecimal result: 0x${result.toInt().toString(16).uppercase()}")
                }

                "&&", "||" -> {
                    val b1 = input1.toBooleanStrict()
                    val b2 = input2.toBooleanStrict()
                    val result = if (operator == "&&") b1 && b2 else b1 || b2
                    println("Boolean result: $result")
                }

                "shl", "shr" -> {
                    val n1 = input1.toInt()
                    val n2 = input2.toInt()
                    val result = if (operator == "shl") n1 shl n2 else n1 shr n2

                    println("Decimal result: $result")
                    println("Hexadecimal result: 0x${result.toString(16).uppercase()}")
                }

                else -> println("Unknown operator: $operator")
            }
        } catch (e: Exception) {
            println("Error: Invalid input or calculation error! (${e.message})")
        }

        println("--- Calculation Finished ---")
    }
}

/**
 * This function will help to validate if the input given by the user is valid or not.
 * It was easier to do this to avoid having repeated code.
 */
fun getValidInput(prompt: String): String {
    while (true) {
        print(prompt)
        val input = readLine()?.trim() ?: ""

        if (input.lowercase() == "exit") {
            return "exit"
        }

        if (input.toDoubleOrNull() != null || input.lowercase() == "true" || input.lowercase() == "false") {
            return input
        } else {
            println("Error: '$input' is not a valid Number or Boolean. Please try again.")
        }
    }
}

/**
 * Similar to the previous one, this will check if the user is typing the correct operators. If not,
 * the program will ask the user to type in again the operator that it's desired.
 */
fun getValidOperator(prompt: String): String {
    val validOperators = listOf("+", "-", "*", "/", "&&", "||", "!", "shl", "shr")

    while (true) {
        print(prompt)
        val operator = readLine()?.trim()?.lowercase() ?: ""

        if (operator.lowercase() == "exit") {
            return "exit"
        }

        if (operator in validOperators) {
            return operator
        } else {
            println("Error: '$operator' is not a supported operator. Please try again.")
        }
    }
}