package input

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InputReaderTest {
    private val inputReader = InputReader()

    private val testDay = Day.from(100)

    @Test
    @DisplayName("Given a valid input text file, when reading input as string, then it should return a list of strings")
    fun readAsString() {
        val input = inputReader.readInputString(testDay)
        assertThat(input.values).isEqualTo(arrayListOf("1", "2", "3", "4", "5"))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as int, then it should return a list of integers")
    fun readAsInt() {
        val input = inputReader.readInputInteger(testDay)
        assertThat(input.values).isEqualTo(arrayListOf(1, 2, 3, 4, 5))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as int, then it should return a list of integers")
    fun readAsDouble() {
        val input = inputReader.readInputDouble(testDay)
        assertThat(input.values).isEqualTo(arrayListOf(1.0, 2.0, 3.0, 4.0, 5.0))
    }

    @Test
    @DisplayName("Given a valid input text file, when reading input as a single string, then it should return a string of all values")
    fun readAsSingleString() {
        val input = inputReader.readInputAsSingleString(testDay)
        assertThat(input).isEqualTo("1")
    }

    @Test
    fun readAsSingleStringFromFilePath() {
        val input = inputReader.readInputAsSingleString("/day100/input.txt")
        assertThat(input).isEqualTo("1")
    }
}