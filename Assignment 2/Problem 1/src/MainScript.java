import java.nio.file.*;
import java.io.IOException;
import java.util.HashMap;

public class MainScript {
	public static void main(String[] args) throws IOException {
		String encrypted = Files.readString(Paths.get("02-1.txt"));
		String[] decomposition;
		HashMap<Character,Double> freqTable;
		
		int keylength = 0;
		double topvalue = 0, temp;
		for(int i = 1; i <= 10; i++) {
			decomposition = StringTools.offsetDecompose(encrypted, i);
			freqTable = FrequencyTable.computeFrequencyTable(decomposition[0]);
			temp = FrequencyTable.getNorm(freqTable);
			if(temp > topvalue) {
				keylength = i;
				topvalue = temp;
			}
		}
		System.out.println("Guess for keylength: " + keylength);
		System.out.print("Guess for key: ");
		decomposition = StringTools.offsetDecompose(encrypted, keylength);
		HashMap<Character,Double> englishTable = FrequencyTable.getEnglishFrequencyTable();
		int offsetGuess;
		
		for(int k = 0; k < keylength; k++) {
			freqTable = FrequencyTable.computeFrequencyTable(decomposition[k]);
			offsetGuess = 0;
			topvalue = 0;
			for(int i = 0; i < 26; i++) {
				temp = FrequencyTable.getOffsetNorm(freqTable, englishTable, i);
				if(temp > topvalue) {
					offsetGuess = i;
					topvalue = temp;
				}
			}
			System.out.print((char)('A'+offsetGuess));
		}
		System.out.print("\n");
	}
}
