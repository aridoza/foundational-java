# Unit 1 Project

## Overview

We will put Java knowledge to use! Create a basic version of Rock-Paper-Scissors that allows users to play against the computer in the console. The game consists of a few main features:

- Play Rock Paper Scissors against a computer player
- Play Rock Paper Scissors against a human player

> Hint: Use a random [number generator](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html) to pick the computer's choice.

---

## Feature Requirements

Your game must:

- Have a main menu with options to enter "2 players" or "vs. Computer":
- If the user enters "2 players", they should be able to play Rock Paper Scissors against a human competitor
- If the user enters "vs. Computer", they should be able to play Rock Paper Scissors against the computer
- When the game is over, the winner should be declared

## Technical Requirements

- Use Classes to remove repetitive parts of code and abstract out a “Player” class used to manage the state of the player (if they have won or loss), how many points they have, what move they have made
- Handle invalid user input
- Handle incorrect capitalization of otherwise valid user input (rock, Rock, RoCk, ROCK, etc.)
- Each Class (including a Player class) should have methods associated with it and be instantiated as an object (as opposed to a singleton or a interface)
- Use `public`, `private`, and `static` `variables`, `methods` and `members` within each class appropriately. 
- Incorporate Exception Handling to make sure your game crashes gracefully if it receives bad input.
- Get Standard Input/Output with Java using a `Scanner` or use `Processing` to get mouse / keyboard / x input.
- Use Arrays or ArrayLists to store game history (if applicable)

## Bonus Ideas

- Write automated JUnit tests for your application.
- Use an Agile project management framework to architect your game
- If the user enters "history", the program should display previous game history (winner's name, game date, etc.)
- Use Java `packages` to modularize code. Place any “helper” tools in these packages, they could be related input, networking, or graphics.
- Use Maven to install external dependencies that your game might require.
- Use [Generics](https://docs.oracle.com/javase/tutorial/extra/generics/index.html) on Collections such as Arrays and ArrayLists to store different data that may compose of different types
- Do multitasking with multithreading to handle concurrent requests (like in multiplayer games)
- Incorporating Video, Text, Data, Networking, and Sound into your game via Processing

---


#### Code of Conduct

As always, your app must adhere to General Assembly's [student code of conduct guidelines](../../../resources/guidelines/code-of-conduct.md).

If you have questions about whether or not your work adheres to these guidelines, please speak with a member of your instructional team.

---

#### Necessary Deliverables

Submit a pull request with a Java program that meets the above requirements.

Below, you can see sample output:

```
Welcome to Rock Paper Scissors!

MAIN MENU
=====
1. Type 'play' to play
2. Type 'history' to view your game history
Type 'quit' to stop playing

play


Type in 'rock' 'paper' or 'scissors' to play.
 Type 'quit' to go back to the Main Menu

rock
Computer picks: scissors
User picks: rock
You win!

Welcome to Rock Paper Scissors!

MAIN MENU
=====
1. Type 'play' to play
2. Type 'history' to view your game history
Type 'quit' to stop playing

play


Type in 'rock' 'paper' or 'scissors' to play.
 Type 'quit' to go back to the Main Menu

paper
Computer picks: scissors
User picks: paper
You lose!

Welcome to Rock Paper Scissors!

MAIN MENU
=====
1. Type 'play' to play
2. Type 'history' to view your game history
Type 'quit' to stop playing

history
=== GAME HISTORY ===
WIN: Player-rock computer-scissors
LOSS: Player-paper computer-scissors

Welcome to Rock Paper Scissors!

MAIN MENU
=====
1. Type 'play' to play
2. Type 'history' to view your game history
Type 'quit' to stop playing

quit
```

---

#### Suggested Ways to Get Started

- Don’t hesitate to write throwaway code to solve short term problems
- Read the docs for whatever technologies you use. **Most of the time, there is a tutorial that you can follow, but not always. However, learning to read documentation is crucial to your success as a developer!**
- Write pseudocode before you write actual code.

---

### Useful Resources

- [Random number generator](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
- [Rules, history of Rock Paper Scissors](https://en.wikipedia.org/wiki/Rock-paper-scissors)

---

#### Project Feedback + Evaluation


Based on the requirements you can earn a maximum of 8 points on this project. Your instructors will score each of your technical requirements using the scale below:

    Score | Expectations
    ----- | ------------
    **0** | _Incomplete._
    **1** | _Does not meet expectations._
    **2** | _Meets expectations, good job!_
    **3** | _Exceeds expectations, you wonderful creature, you!_

 This will serve as a helpful overall gauge of whether you met the project goals, but __the more important scores are the individual ones__ above, which can help you identify where to focus your efforts for the next project!
