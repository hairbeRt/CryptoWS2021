import java.lang.StringBuilder;
/**
 * Implements a shift (caesar) cipher. Applicable to chars and strings.
 * @author jonas
 *
 */
public class ShiftCipher {
	private int shift;
	
	public ShiftCipher(int shift) {
		this.shift = shift;
	}
	
	public char applyTo(char c) {
		char result = (char)(c + shift);
		if(result > 'Z') return (char)(result - 26);
		return result;
	}
	
	public String applyTo(String s) {
		StringBuilder result = new StringBuilder();
		for(char c : s.toCharArray()) {
			if(c >= 'A' && c <= 'Z') {
				result.append(this.applyTo(c));
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}
}
