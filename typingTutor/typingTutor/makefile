#Makefile 
#Lukhanyo Vena

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=WordDictionary.class FallingWord.class \
		Score.class ScoreUpdater.class \
		CatchWord.class WordMover.class \
		HungryWordMover.class GamePanel.class\
		TypingTutorApp.class \

CLASS_FILES =$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class


# wm -> numbers of total words to fall
# nw -> number of words to fall at a time on the screen
# dit -> dictionary file of words to fall
App: $(CLASS_FILES)
	java -cp $(BINDIR) TypingTutorApp $(tw) $(nw) $(dict)
