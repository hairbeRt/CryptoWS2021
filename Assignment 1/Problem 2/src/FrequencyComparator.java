import java.util.Comparator;
import java.util.HashMap;
import java.util.Arrays;

public class FrequencyComparator implements Comparator<Character> {
	HashMap<Character,Double> frequencyTable;
	
	public FrequencyComparator(HashMap<Character,Double> frequencyTable) {
		this.frequencyTable = frequencyTable;
	}
	
	@Override
	public int compare(Character o1, Character o2) {
		if(o1 == o2) return 0;
		return frequencyTable.get(o1)>frequencyTable.get(o2)?-1:1;
	}
}