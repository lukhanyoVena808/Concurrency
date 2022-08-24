

public class WordDictionary {
	int size;
	static String [] theDict= {"litchi","banana","apple","mango","pear","orange","strawberry",
		"cherry","lemon","apricot","peach","guava","grape","kiwi","quince","plum","prune",
		"cranberry","blueberry","rhubarb","fruit","grapefruit","kumquat","tomato","berry",
		"boysenberry","loquat","avocado"}; //default dictionary

	static String [] theHungryDict= {"Bacon","Beef","Buffalo","Dusk","Goose","Liver","Mutton","Partridge","Pheasant","Pork","Quail","Rabbit","Veal","Chicken","Turkey","Cornish","Caviar","Clam","Conch","Crab","Eel","Flounder","Sole","Haddock","Halibut","Herring","Lobster","Lox"}; // same size as default dictionary
	// "Mussels","Octopus","Oysters","Scallop","Shrimp"
	
	WordDictionary(String [] tmp) {
		size = tmp.length;
		theDict = new String[size];
		for (int i=0;i<size;i++) {
			theDict[i] = tmp[i];
		}
		
	}
	
	WordDictionary() {
		size=theDict.length;
	}
	
	public synchronized String getNewWord() {
		int wdPos= (int)(Math.random() * size);
		return theDict[wdPos];
	}

	public synchronized String getNewHungryWord() {
		int wdPos= (int)(Math.random() * size);
		return theHungryDict[wdPos].toLowerCase();
	}
}