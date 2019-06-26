package javaapplication3;

import javax.swing.JOptionPane;

public class BoyerMoore {

	static int skip[] = new int[256];

	public static void initSkip(String p) {
		int j, m = p.length();
		for (j = 0; j < 256; j++)
			skip[j] = m;
		for (j = 0; j < m; j++)
			skip[p.charAt(j)] = m - j - 1;
	}

	public static int BMSearch(String p, String t) {
		int i, j, a, m = p.length(), n = t.length();
		i = m - 1;
		j = m - 1;
		initSkip(p);
		while (j >= 0) {
			while (t.charAt(i) != p.charAt(j)) {
				a = skip[t.charAt(i)];
				i += (m - j > a) ? (m - j) : a;
				if (i >= n)
					return -1;
				j = m - 1;
			}
			i--;
			j--;
		}
		return i + 1;
	}

	public static void main(String args[]) {
		String t = "texto de teste";
		String p = "teste";

		int pos = BMSearch(p, t);

		if (pos == -1)
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o nao encontrado!");
		else
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o encontrado na posi��o: " + pos + ".");

		System.exit(0);
	}
}