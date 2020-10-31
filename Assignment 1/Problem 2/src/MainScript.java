import java.nio.file.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public class MainScript {
	
	public static void main(String[] args) throws IOException {
		char x1, x2;
		Scanner stdin = new Scanner(System.in);
		
		
		//Read and output encrypted text
		String encrypted = Files.readString(Paths.get("01-3.txt"));
		System.out.println(encrypted);
		System.out.println("-----------------------------------");
		
		//Compute frequency table of encrypted text
		HashMap<Character,Double> freqTable = computeFrequencyTable(encrypted);
		Character[] englishFreqSorted =   {'E','T','A','O','I','N','S','H','R','D','L','C','U','M','W','F','G','Y','P','B','V','K','J','X','Q','Z'};
		Character[] encryptedFreqSorted = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		Arrays.sort(encryptedFreqSorted, new FrequencyComparator(freqTable));
		
		//Calculate fitted substitution from encrypted to english
		HashMap<Character,Character> fittedSub = new HashMap<Character,Character>();
		for(int k = 0; k < 26; k++) {
			fittedSub.put(encryptedFreqSorted[k],englishFreqSorted[k]);
		}
		Substitution sub = new Substitution(fittedSub);
		String newText = sub.applyTo(encrypted);
		System.out.println(newText);
		System.out.println(sub);
		for(int k = 0; k < 26; k++) {
			System.out.print(encryptedFreqSorted[k]);
		}
		System.out.print("\n");
		System.out.println(frequencyTableToString(freqTable));
		while(true) {
			System.out.print("Substitute: ");
			//Ugly because there is no nextChar()-method smh
			x1 = stdin.next().charAt(0);
			x2 = stdin.next().charAt(0);
			sub.applySwap(x1, x2);
			newText = sub.applyTo(encrypted);
			System.out.println(newText);
			System.out.println(sub);
		}
	}
	
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
}
