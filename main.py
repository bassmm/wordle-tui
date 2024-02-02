import random


def import_words_to_array(filepath: str) -> list:
    words = open(filepath, "r")
    wordlist = []
    for word in words:
        wordlist.append(word[:-1])
    return wordlist


def guessing(valid_guesses: list[str], answer: str) -> bool:
    green = '\x1b[1;32;40m'
    yellow = '\x1b[1;33;40m'
    color_end = '\x1b[0m'
    guess_count = 0
    guesses = []
    guess = ''
    while guess != answer and guess_count <= 5:
        for guess_letters in guesses:
            print(' '.join(guess_letters))
        guess = input('Guess: ')
        while guess not in valid_guesses:  # Check 1: Word is valid english 5-letter word refers to list in words.txt
            guess = input('Invalid Word, Try again: ')
        guess_letters = list(guess)
        for x in range(0, 5):
            if guess_letters[x] == (list(answer))[x]:  # Check 2: if letter in guess same pos as in answer, green
                guess_letters[x] = f'{green}{guess_letters[x]}{color_end}'
            elif guess_letters[x] in list(answer):  # Check 3: if letter in guess even exists in answer, yellow
                guess_letters[x] = f'{yellow}{guess_letters[x]}{color_end}'

        guesses.append(guess_letters)
        guess_count += 1
    if guess == answer:
        return True
    return False


def main():
    words = import_words_to_array("words.txt")
    answers = import_words_to_array("answers.txt")

    answer = (random.choice(answers))
    print(f'answer(for debugging) = {answer}')
    win = guessing(words, answer)
    if win:
        print(f'Congrats! The answer as indeed: {answer} :)')
    else:
        print(f'Unlucky! The answer was actually: {answer} :(')


main()
