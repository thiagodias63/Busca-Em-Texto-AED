package javaapplication3;

import javax.swing.JOptionPane;

public class ForcaBruta {
    public static int ultimaPosicao;
    
    public static int forcaBruta(String p, String t) {
        ForcaBruta.ultimaPosicao = -1;
        int i, j, aux;
        int m = p.length();
        int n = t.length();
        for (i = 0; i < n; i++) {
            aux = i;
            for (j = 0; j < m && aux < n; j++) {
                int t1 = t.charAt(aux);
                int p1 = p.charAt(j);
                if (t1 != p1) {
                    break;
                }
                aux++;
            }
            if (j == m) {
                // ForcaBruta.ultimaPosicao = i;
                return i;
            }
        }
        // ForcaBruta.ultimaPosicao = i;
        return -1;
    }

    public static void main(String args[]) {
        
            String t = "texto de teste";
            String p = "teste";
            int pos = forcaBruta(p, t);
            if (pos == -1)
                    JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadrão: "
                                    + p + "\nPadrão nao encontrado!");
            else
                    JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadrão: "
                                    + p + "\nPadrão encontrado na posição: " + pos + ".");
            System.exit(0);
            
    }
}
