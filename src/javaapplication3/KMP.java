package javaapplication3;

import javax.swing.JOptionPane;

public class KMP {
	static int next[] = new int[1000];

	public static void initNext(String p) {
		int i = 0, j = -1, m = p.length();
		next[0] = -1;
		while (i < m) {
			while (j >= 0 && p.charAt(i) != p.charAt(j))
				j = next[j];
			i++;
			j++;
			next[i] = j;
		}
	}

	public static int KMPSearch(String p, String t) {
		int i = 0, j = 0, m = p.length(), n = t.length();
		initNext(p);
		while (j < m && i < n) {
			while (j >= 0 && t.charAt(i) != p.charAt(j)) {

				j = next[j];
			}
			i++;
			j++;
		}
		if (j == m)
			return i - m;
		else
			return -1;
	}

	public static void main(String args[]) {
		String t = "abracadababra";
		String p = "abracadabra";

		initNext(p);

		int pos = KMPSearch(p, t);

		if (pos == -1)
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o nao encontrado!");
		else
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o encontrado na posi��o: " + pos + ".");

		System.exit(0);
	}
}