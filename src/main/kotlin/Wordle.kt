import java.io.File
import kotlin.random.Random

const val RESET: String = "\u001B[0m"
const val BOLD: String = "\u001B[1m"
const val GREEN: String = "\u001B[32m"
const val YELLOW: String = "\u001B[33m"
const val MAX_CHARACTER: Int = 5

fun isValid(word: String): Boolean = word.count() == MAX_CHARACTER

fun readWordList(fileName: String): MutableList<String> = File(fileName).useLines { it.toMutableList() }

fun pickRandomWord(words: MutableList<String>): String = words[Random.nextInt(0, words.size - 1)]

fun obtainGuess(
    attempt: Int,
    wordList: MutableList<String>,
): String {
    print("$attempt: ")
    var guess = readln().trim().uppercase()
    while (!isValid(guess) || guess !in wordList) {
        print("!: ")
        guess = readln().trim().uppercase()
    }
    return guess
}

fun evaluateGuess(
    guess: String,
    target: String,
): List<Int> {
    // 0 -> not in word
    // 1 -> in word, wrong pos
    // 2 -> in word, correct pos

    val targetCharList = target.toMutableList()
    val eval = mutableListOf(0, 0, 0, 0, 0)
    // pass 1: mark all 2's
    guess.forEachIndexed { pos, char ->
        if (char == targetCharList[pos]) {
            eval[pos] = 2
            targetCharList[pos] = '\u0000' // this will help with the second pass
        }
    }
    // pass 2: mark all 1's
    guess.forEachIndexed { pos, char ->
        if (char in targetCharList && eval[pos] != 2) {
            eval[pos] = 1
            targetCharList[targetCharList.indexOf(char)] = '\u0000'
        }
    }
    return eval.toList()
}

fun displayGuess(
    guess: String,
    matches: List<Int>,
) {
    guess.forEachIndexed { pos, char ->
        when (matches[pos]) {
            0 -> print("$BOLD$char$RESET ")
            1 -> print("$BOLD$YELLOW$char$RESET ")
            2 -> print("$BOLD$GREEN$char$RESET ")
        }
    }
    println(" ")
}
