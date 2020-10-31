import java.util.HashMap;
import java.util.Map;
import java.lang.StringBuilder;

/**
 * Wrapper class for the data determining a monoalphabetic substitution,
 * with its inverse computed online.
 * @author Jonas
 *
 */
public class Substitution {
	private HashMap<Character,Character> forwardMap;
	private HashMap<Character,Character> inverseMap;
	
	/**
	 * Default constructor. Implements the identity substitution (i.e. the trivial cipher)
	 */
	public Substitution() {
		forwardMap = new HashMap<Character,Character>();
		inverseMap = new HashMap<Character,Character>();
		
		for(char x = 'A'; x <= 'Z'; x++) {
			forwardMap.put(x, x);
			inverseMap.put(x, x);
		}
	}
	
	/**
	 * Constructs the substitution giving by array data. Array length must be exactly 26.
	 * @param range The substitution as an array, range[0] == subst('A'), range[1] == subst('B') etc.
	 */
	public Substitution(char[] range) {
		if(range.length != 26) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int k = 0;
		for(char x = 'A'; x <= 'Z'; x++) {
			forwardMap.put(x, range[k]);
			inverseMap.put(range[k], x);
			k++;
		}
	}
	
	/**
	 * Constructs a Substitution object with parameter as its given data.
	 * @param data Substitution in forward mode. Must be a bijection [A:Z]->[A:Z] by contract!
	 */
	public Substitution(HashMap<Character,Character> data) {
		forwardMap = data;
		//Construct inverse map from data
		inverseMap = new HashMap<Character,Character>();
		for(Map.Entry<Character, Character> entry : data.entrySet()) {
			inverseMap.put(entry.getValue(), entry.getKey());
		}
	}
	
	/**
	 * Returns a string of this object, represented as a set with tuples as elements
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append('{');
		for(char x = 'A'; x <= 'Z'; x++) {
			str.append('(');
			str.append(x + "->" + forwardMap.get(x));
			str.append(")");
			if(x != 'Z') str.append(',');
		}
		str.append('}');
		return str.toString();
	}
	
	/**
	 * Applies the modeled substitution to the input character.
	 * @param x A letter to substitute
	 * @return The substitute letter of the input
	 */
	public char subst(char x) {
		return forwardMap.get(x);
	}
	
	/**
	 * Applies the inverse modeled substitution to the input character,
	 * i.e. a y, such that subst(y) corresponds to the input letter
	 * @param x
	 * @return A y, such that subst(y)=x
	 */
	public char inverse(char x) {
		return inverseMap.get(x);
	}
	
	/**
	 * Changes the substitution by applying the swap x1<->x2 behind the modeled substitution,
	 * e.g. if subst(a)=x1, subst(b)=x2, then applySwap(x1,x2) causes subst(a)=x2, subst(b)=x1
	 */
	public void applySwap(char x1, char x2) {
		char a = inverse(x1), b = inverse(x2);
		forwardMap.put(a, x2);
		forwardMap.put(b, x1);
		inverseMap.put(x2, a);
		inverseMap.put(x1, b);
	}
	
	/**
	 * Applies the modeled substitution to a given string, substituting every occuring letter by its substitute.
	 * @param text The text to apply the substitution to
	 * @return The substituted text
	 */
	public String applyTo(String text) {
		char[] textCharArr = text.toCharArray();
		char x;
		for(int k = 0; k < textCharArr.length; k++) {
			x = textCharArr[k];
			if(x >= 'A' && x <= 'Z') {
				textCharArr[k] = subst(x);
			}
		}
		return new String(textCharArr);
	}
}
