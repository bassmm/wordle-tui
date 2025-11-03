const val CLEAR: String = "\u001B[2J\u001B[H"
const val MAX_ATTEMPTS: Int = 6

fun main() {
    val wordList = readWordList("data/words.txt")

    val word = pickRandomWord(readWordList("data/answers.txt"))

    val guessList = mutableListOf<String>()

    var attempt = 0

    while (attempt < MAX_ATTEMPTS && word !in guessList) {
        attempt += 1
        println(CLEAR)
        guessList.forEachIndexed { pos, guess -> displayGuess(guess, evaluateGuess(guess, word)) }
        guessList.add(obtainGuess(attempt, wordList))
    }
    // win/lose event
    println(CLEAR)
    guessList.forEachIndexed { pos, guess -> displayGuess(guess, evaluateGuess(guess, word)) }
    if (word in guessList) println("\nCongrats!") else println("\nGood try... the word was $word")
}
