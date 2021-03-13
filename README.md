# BullsAndCows

Bulls and Cows command line game for guessing a number.

## How to Play
1. The computer will come up with a 4-digit hidden number.
2. The player guesses a number.
3. The computer will displays the amount of e's and p's the player recieved. However it will not display which number the player guessed correctly.
    * e signifies that the player guessed the correct digit in the correct spot
    * p signifies that the player guessed the correct digit but in the wrong spot
    * no e's or p's means that the guess was completely wrong
4. Player keeps guessing until he/she gets the correct number (4 e's).

## Example
1. The player generates a new game
2. Computer picks: 1234 as the 4 digit number
3. Players guesses 1465
4. Computer displays "e:1:p:1" 
  * e:1 because 1 is the correct number in the correct spot
  * p:1 because 4 is the correct number however it is not in the correct spot
5. Player guesses: 1856  (Notice how the player remove 4 since he/she did not know that was one of the correct number)
6. Computer displays "e:1:p:0"
7. Player guesses: 1234
8. Computer displays "e:4:p:0" signifying that he/she was completely correct.
