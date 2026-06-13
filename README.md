# wordle-tui

## Overview

![Wordle](https://github.com/user-attachments/assets/35b67fab-6a9c-43e3-a36a-8fdb923a468d)

A simple terminal-based Wordle game, where you can guess a hidden word within a limited number of attempts.


## How to Play
### Download release & extract:
```bash 
# 1. Run the game:
java -jar wordle.jar
```
#### Alternatively:
##### Build from source (Requires Java 25):
```bash 
# 1. Clone the repository to your local machine:
git clone https://github.com/bassmm/wordle-tui.git

# 2. Run the game:
./gradlew run
```
 Type a valid english 5 letter word to guess. You have a limited number of attempts.

## Features

- Terminal-based gameplay.
- Randomly generated hidden words for replayability.
- 6 attempts to guess the word.

### Requirements

- Java Runtime 17+

### Installation

No additional libraries or dependencies are required. Just make sure you have JRE installed on your machine.
Contributing

This project is licensed under the MIT License - see the LICENSE.md file for details.

### Acknowledgments

Inspired by the classic NYTimes Wordle game.

#### To-Do

- [x] Add protable jar file to releases
- [ ] Display available keys left
