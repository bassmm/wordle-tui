import random

words = open("words.txt","r")

wordlist = []
for word in words: 
    wordlist.append(word[:-1])

answer = random.choice(wordlist)

answer_array = list(answer)

print(answer)
print(answer_array)
guess = ""

while guess != answer:
    guess = input("Guess: ")