import java.util.Comparator;
import java.util.Map;

	
	class ValueComparator implements Comparator<String> {
		Map<String, Integer> map;
	 
		public ValueComparator(Map<String, Integer> map) {
			this.map = map;
		}
	 
		public int compare(String k1, String k2) {
			if (k2.equals(k1)) { //if comparing the same string to itself, return 0 to avoid adding duplicates to the tree
				return 0;
			}
			Comparable<Integer> v1 = (Comparable<Integer>) map.get(k1);
			Comparable<Integer> v2 = (Comparable<Integer>) map.get(k2);
			int comparison = v2.compareTo((Integer) v1);		
			if (comparison > 0 ) { //if count of second word > count of first word, add second word and its count to tree
				return 1;
			} else { //otherwise, add first word and its count to tree
				return -1;
			}
		}
	}
