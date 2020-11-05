import java.nio.file.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.io.*;

public class MainScript {
	public static void main(String[] args) {
		String encrypted = Files.readString(Paths.get("02-1.txt"));
		String[] decompose = StringTools.offsetDecompose(encrypted, 3);
		for(String s : decompose) {
			System.out.println(s);
		}
	}
}
