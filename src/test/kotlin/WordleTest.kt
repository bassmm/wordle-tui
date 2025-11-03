import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class WordleTest :
    StringSpec({
        "isValid only 5 letter words" {
            withClue("word='eleven'") { isValid("eleven") shouldBe false }
            withClue("word='sive'") { isValid("sive") shouldBe false }
            withClue("word='seven'") { isValid("seven") shouldBe true }
        }

        "readWordList actually turns text file into a list" {
            withClue("fileName='data/test1.txt'") { readWordList("data/test1.txt") shouldBe mutableListOf("HELLO") }
            withClue("fileName='data/test2.txt'") {
                readWordList("data/test2.txt") shouldBe mutableListOf("FLIPS", "HELLO", "FLOPS", "ADIEU", "GHOST")
            }
            withClue("fileName='data/test3.txt'") { readWordList("data/test3.txt") shouldBe mutableListOf<String>() }
        }
        "pickRandomWord picks a valid word" {
            { pickRandomWord(readWordList("data/test1.txt")) shouldBe "HELLO" }
            { isValid(pickRandomWord(readWordList("data/test1.txt"))) shouldBe true }
        }

        "Case 1: words are equal" {
            evaluateGuess("HELLO", "HELLO") shouldBe listOf(2, 2, 2, 2, 2)
        }
        "Case 2: words share no common letters" {
            evaluateGuess("ADIEU", "GHOST") shouldBe listOf(0, 0, 0, 0, 0)
        }
        "Case 3: words contain some equal words" {
            evaluateGuess("HELLO", "HEALS") shouldBe listOf(2, 2, 0, 2, 0)
        }
        "Case 4: words contain same letters but different positions " {
            evaluateGuess("LEAST", "STEAL") shouldBe listOf(1, 1, 1, 1, 1)
        }
        "Case 5: words are contain a mixture of same position and same letter" {
            evaluateGuess("FLASH", "FLORA") shouldBe listOf(2, 2, 1, 0, 0)
        }
        "Dupe Char Case 1: target and guess has 2 same letters and both in correct pos" {
            evaluateGuess("ARRAY", "AREAS") shouldBe listOf(2, 2, 0, 2, 0)
        }
        "Dupe Char Case 2: target and guess both have 2 of same letter but both wrong pos" {
            evaluateGuess("BALLS", "LLAMA") shouldBe listOf(0, 1, 1, 1, 0)
        }
        "Dupe Char Case 3: target and guess both have 2 of same letter but 1 correct pos and 1 wrong pos" {
            evaluateGuess("GAUGE", "LEGGY") shouldBe listOf(1, 0, 0, 2, 1)
        }

        "Dupe Char Case 4: target has 2 of same letter and guess has 1 of that letter in correct pos" {
            evaluateGuess("TIMED", "TIMID") shouldBe listOf(2, 2, 2, 0, 2)
        }
    })
