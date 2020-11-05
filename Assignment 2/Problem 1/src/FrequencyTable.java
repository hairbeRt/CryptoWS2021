import java.util.HashMap;

public class FrequencyTable {
	
	public static HashMap<Character,Double> computeFrequencyTable(String text) {
		HashMap<Character,Double> freqMap = new HashMap<Character,Double>();
		//Initialize frequency
		for(char x = 'A'; x <= 'Z'; x++) {
			freqMap.put(x,0.);
		}
		//Counts actual amount of letters in string
		int lettercount = 0;
		
		char[] textCharArr = text.toCharArray();
		for(char c : textCharArr) {
			if(c >= 'A' && c <= 'Z') {
				lettercount++;
				freqMap.put(c,freqMap.get(c)+1.);
			}
		}
		for(char x = 'A'; x <= 'Z'; x++) {
			freqMap.put(x, freqMap.get(x)/lettercount);
		}
		return freqMap;
	}
	
	public static String frequencyTableToString(HashMap<Character,Double> freqTable) {
		StringBuilder str = new StringBuilder();
		str.append('{');
		for(char x = 'A'; x <= 'Z'; x++) {
			str.append('(');
			str.append(x + "->" + String.format("%.5f", freqTable.get(x)));
			str.append(")");
			if(x != 'Z') str.append(',');
		}
		str.append('}');
		return str.toString();
	}
	
	public static double getNorm(HashMap<Character,Double> freqTable) {
		double result = 0;
		for(char c = 'A'; c <= 'Z'; c++) {
			result += Math.pow(freqTable.get(c), 2);
		}
		return result;
	}
	
	public static HashMap<Character,Double> getEnglishFrequencyTable() {
		Character[] englishLettersSorted =   {'E','T','A','O','I','N','S','H','R','D','L','C','U','M','W','F','G','Y','P','B','V','K','J','X','Q','Z'};
		Double[] englishFreqSorted = {0.1202, 0.0910, 0.0812, 0.0768, 0.0731, 0.0695, 0.0628, 0.0602, 0.0592, 0.0432, 0.0398, 0.0288, 0.0271, 0.0261, 0.0230, 0.0211, 0.0209, 0.0203, 0.0182, 0.0149, 0.0111, 0.0069, 0.0017, 0.0011, 0.0010, 0.0007};
		HashMap<Character, Double> result = new HashMap<Character,Double>();
		for(int i = 0; i < 26; i++) {
			result.put(englishLettersSorted[i],englishFreqSorted[i]);
		}
		return result;
	}
	
	public static double getOffsetNorm(HashMap<Character,Double> encryptedFreqTable, HashMap<Character,Double> groundFreqTable, int offset) {
		ShiftCipher shift = new ShiftCipher(offset);
		double result = 0;
		for(char c = 'A'; c <= 'Z'; c++) {
			result += groundFreqTable.get(c)*encryptedFreqTable.get(shift.applyTo(c));
		}
		return result;
	}
}
