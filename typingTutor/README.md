# Assignment PCP2 2022
## Typing Tutor Game with a Hungry Word

<br>

### Running TypingTutorApp in command-line
In the src directory, compile all java files:
```console
javac *.java
```

To run the TypingTutorApp:
```console
java -cp ./ TypingTutorApp
```
To run the TypingTutorApp with aruguements:
<br>
```console
java -cp ./ TypingTutorApp <totalWordsToFall> <noWordsToFall> <textFileName>
```

Example:
```console
java -cp ./ TypingTutorApp 35 5 words.txt
```
<br>

### Running TypingTutorApp using Makefile
Inside the typingTutor folder execute:
```console
make
```

To run the TypingTutoraApp:
```console
make App
```
To run the TypingTutoraApp with arguements: <br>
- tw = total numbers of words to fall in the game
- nw = number of words to fall at a given time
- dict = the text file of words<br>
<br>
Example:
```console
make App tw=35 nw=5 dict=words.txt
```