import java.lang.StringBuilder;
/**
 * 
 * @author jcrem
 *
 */
public class StringTools {
	/**
	 * Decomposes a string into its offset substrings.
	 * Ignores all characters that are not uppercase letters.
	 * Example: offsetDecompose("HELLO WORLD!",2) = {"HLOOL","ELWRD"}
	 * @param s String to decompose
	 * @param k Offset value
	 * @return String decomposition in array form.
	 */
	public static String[] offsetDecompose(String s, int k) {
		StringBuilder[] result = new StringBuilder[k];
		for(int i = 0; i < k; i++) {
			result[i] = new StringBuilder();
		}
		int curroffset = 0;
		for(char c : s.toCharArray()) {
			if(c >= 'A' && c <= 'Z') {
				result[curroffset].append(c);
				curroffset = (curroffset+1)%k;
			}
		}
		String[] resultStrings = new String[k];
		for(int i = 0; i < k; i++) {
			resultStrings[i] = result[i].toString();
		}
		return resultStrings;
	}
}
