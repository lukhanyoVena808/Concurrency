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


App: $(CLASS_FILES)
	java -cp $(BINDIR) TypingTutorApp $(nw) $(tw) $(dict)
