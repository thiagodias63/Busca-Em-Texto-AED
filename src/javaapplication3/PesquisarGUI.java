package javaapplication3;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javaapplication3.ForcaBruta.forcaBruta;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javaapplication3.Main;

class PesquisarGUI extends JFrame implements ActionListener {
    int metodo;
    Main mainPublic;
    
    JPanel p1 = new JPanel(new GridLayout(4, 2));
    JLabel lb1 = new JLabel("Pesquisar por:  ");
    JCheckBox chk1 = new JCheckBox("Substituir por:");
    JCheckBox chk2 = new JCheckBox("Case-sensitive");
    JTextField txtPesq = new JTextField();
    JTextField txtSubs = new JTextField();
    JButton btOk = new JButton("Ok");
    JButton btCancel = new JButton("Cancelar");

    public PesquisarGUI(Main mainPublic, int metodo){
        super("Pesquisar");		
        this.mainPublic = mainPublic;
        this.metodo = metodo;
        p1.add(lb1);
        lb1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        p1.add(txtPesq);
        p1.add(chk1);
        p1.add(txtSubs);
        p1.add(chk2);
        p1.add(new JLabel(""));
        p1.add(btOk);
        p1.add(btCancel);
        txtSubs.setEnabled(false);
        chk1.addActionListener(this);
        btOk.addActionListener(this);
        btCancel.addActionListener(this);
        getContentPane().add(p1, BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();		
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chk1) {
            txtSubs.setEnabled(chk1.isSelected());
            txtSubs.setText("");
        }
        if(e.getSource() == btCancel) {
            this.dispose();
        }		
        if(e.getSource() == btOk) {
            pesquisar();
            // mainPublic.texto.toString();
            // mainPublic.colorirTexto(0, 2);
        }		
    }
    
    private void pesquisar() {
        if (this.metodo == 1) { 
            // Força Bruta
            ArrayList<Integer> marcados = new ArrayList<Integer>();
            int posicao = -1; 
            String pesquisa =  mainPublic.texto.getText();
           
            posicao = ForcaBruta.forcaBruta(txtPesq.getText(), pesquisa);
            if (posicao > -1) {
                marcados.add(posicao);
            }
            
            while (posicao > -1) {
                pesquisa = pesquisa.substring(posicao + 1);
                posicao = ForcaBruta.forcaBruta(txtPesq.getText(), pesquisa);
                if (posicao > -1) {
                    marcados.add(posicao + marcados.get(marcados.size() - 1) + 1);    
                }
            }
            
            for (int m : marcados) {
                System.out.println(m);
                mainPublic.colorirTexto(m, txtPesq.getText().length());
            }
        }
        else if (this.metodo == 2) {
            // Boyer Moore
            int posicao = BoyerMoore.BMSearch(txtPesq.getText(), mainPublic.texto.getText());
            if (posicao > -1) {
                mainPublic.colorirTexto(posicao, txtPesq.getText().length());
            }
        }
        else if (this.metodo == 3) {
            // KMP 
            int posicao = KMP.KMPSearch(txtPesq.getText(), mainPublic.texto.getText());
            if (posicao > -1) {
                mainPublic.colorirTexto(posicao, txtPesq.getText().length());
            }
        }
        else if (this.metodo == 4) {
            // Rabin Karp
            int posicao = RabinKarp.RKSearch(txtPesq.getText(), mainPublic.texto.getText());
            if (posicao > -1) {
                mainPublic.colorirTexto(posicao, txtPesq.getText().length());
            }
        }
    }
    
    /* Pode ser removido 
    public static void main(String args[]) {
        new PesquisarGUI();
    } 
    */
    
    /*
    ForcaBruta fb = new ForcaBruta();

    System.out.print("Tamanho do texto pesquisado: ");
    System.out.println(txtPesq.getText().length());

    System.out.print("Texto do text main: ");
    System.out.println(mainPublic.texto.getText());

    System.out.print("Texto pesquisado: ");
    System.out.println(txtPesq.getText());

    // Tamanho do texto
    // System.out.println(txtPesq.getText().length());
    
    System.out.print("Posição encontrada: ");
    System.out.println(posicao);
            
            
    */
}