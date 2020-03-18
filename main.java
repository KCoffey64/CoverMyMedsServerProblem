import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * 
 * @author Kimberly (Kacey) Coffey
 *
 */
public class main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "inputText.txt"; //file name containing words to count
		File inputFile = new File(fileName);
		Scanner fileScanner = new Scanner(inputFile);
		Map<String, Integer> m = findUniqueWords(fileScanner);
		TreeMap<String, Integer> mSorted = sortMapByValues(m);
		printWordsAndCounts(mSorted);
		fileScanner.close();
	}
	
/**
 * Given a Scanner for a text file, finds each unique word and its count of occurrences in text file.
 * @param s Scanner for input text file
 * @return a Map containing <word, count> pairs of all unique words from input file
 */
	public static Map<String, Integer> findUniqueWords(Scanner s) {
		Map<String, Integer> m = new HashMap<String, Integer>(); //contain <word, count> pairs
		while (s.hasNext()) {
			String currentWord = s.next();
			currentWord = formatWord(currentWord);
			if (m.containsKey(currentWord)) { //if word is already in map, increase its count by 1
				int count = (Integer) m.get(currentWord);
				count++;
				m.replace(currentWord, count);
			} else { //otherwise, word needs to be added to map with a count of 1
				m.put(currentWord, 1);
			}
		}
		return m;
	}
	
	/**
	 * Given a string, removes any leading or trailing non-alphanumeric characters from string and converts it to lower-case.
	 * @param s String to format
	 * @return s as a lower-case String after having removed any leading or trailing non-alphanumeric characters
	 */
	public static String formatWord(String s) {
		int beginningIndex = 0;
		while (!Character.isLetterOrDigit(s.charAt(beginningIndex))) { //remove any leading punctuation marks
			beginningIndex++;
		}
		int endingIndex = s.length();
		while (!Character.isLetterOrDigit(s.charAt(endingIndex - 1))) { //remove any trailing punctuation marks
			endingIndex--;
		}
		s = s.substring(beginningIndex, endingIndex);
		return s.toLowerCase(); //convert all words to lower case
	}
	
	/**
	 * Given a map of <String, Integer> pairs, sorts the map by descending value values.
	 * @param m Map containing all <word, count> pairs from text file
	 * @return a TreeMap containing all pairs from m sorted by descending values
	 */
	public static TreeMap<String, Integer> sortMapByValues(Map<String, Integer> m) {
		Comparator<String> comparator = new ValueComparator(m);
		TreeMap<String, Integer> t = new TreeMap<String, Integer>(comparator);
		t.putAll(m);
		return t;
		
	}
	
	/**
	 * Given a sorted map, prints each pair in given order
	 * @param m sorted map
	 */
	public static void printWordsAndCounts(TreeMap<String, Integer> m) {
		System.out.println("Word              Count");
		System.out.println("-----------------------");
		while (!m.isEmpty()) {
			Map.Entry<String, Integer> pair = m.firstEntry();
			String word = pair.getKey();
			int count = pair.getValue();
			System.out.print(word);
			for (int i = word.length(); i < 18; i++) {
				System.out.print(" ");
			}
			System.out.println(count);
			
			m.remove(word);
		}
	}

}
