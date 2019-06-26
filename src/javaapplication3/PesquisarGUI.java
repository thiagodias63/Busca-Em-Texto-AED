package javaapplication3;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javaapplication3.Main;
import javax.swing.JOptionPane;

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
        if (txtPesq.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o texto para pesquisa.");
        }
        /* else if (chk1.isSelected() == true && txtSubs.getText().length() == 0) { // pode ser nulo
            JOptionPane.showMessageDialog(null, "Informe o texto para substituição.");
        }  */ 
        
        mainPublic.descolorirTexto();
        
        ArrayList<Integer> marcados = new ArrayList<Integer>();
        int posicao = -1;
        String pesquisa =  mainPublic.texto.getText();
        String texto = txtPesq.getText();
        String novoTexto = mainPublic.texto.getText();
        
        if (chk2.isSelected() != true) {
            pesquisa = pesquisa.toLowerCase();
            texto = texto.toLowerCase();
            novoTexto = novoTexto.toLowerCase();
        }
        
        if (this.metodo == 1) { 
            // Força Bruta
            posicao = ForcaBruta.forcaBruta(texto, pesquisa);
            if (posicao > -1) {
                marcados.add(posicao);
            }
            
            while (posicao > -1) {
                pesquisa = pesquisa.substring(posicao + 1);
                posicao = ForcaBruta.forcaBruta(texto, pesquisa);
                if (posicao > -1) {
                    marcados.add(posicao + marcados.get(marcados.size() - 1) + 1);    
                }
            }
        }
        else if (this.metodo == 2) {
            // Boyer Moore
            posicao = BoyerMoore.BMSearch(texto, pesquisa);
            if (posicao > -1) {
                marcados.add(posicao);
            }
            while (posicao > -1) {
                pesquisa = pesquisa.substring(posicao + 1);
                posicao = BoyerMoore.BMSearch(texto, pesquisa);
                if (posicao > -1) {
                    marcados.add(posicao + marcados.get(marcados.size() - 1) + 1);    
                }
            }
        }
        else if (this.metodo == 3) {
            // KMP 
            posicao = KMP.KMPSearch(texto, pesquisa);
            if (posicao > -1) {
                marcados.add(posicao);
            }
            while (posicao > -1) {
                pesquisa = pesquisa.substring(posicao + 1);
                posicao = KMP.KMPSearch(texto, pesquisa);
                if (posicao > -1) {
                    marcados.add(posicao + marcados.get(marcados.size() - 1) + 1);    
                }
            }
        }
        else if (this.metodo == 4) {
            // Rabin Karp
            posicao = RabinKarp.RKSearch(texto, pesquisa);
            if (posicao > -1) {
                marcados.add(posicao);
            }
            while (posicao > -1) {
                pesquisa = pesquisa.substring(posicao + 1);
                posicao = RabinKarp.RKSearch(texto, pesquisa);
                if (posicao > -1) {
                    marcados.add(posicao + marcados.get(marcados.size() - 1) + 1);    
                }
            }
        }
        
        
        if (chk1.isSelected() == false) { // Se não vai substituir
            for (int m : marcados) {
                // if (chk1.isSelected() != true) {
                    mainPublic.colorirTexto(m - buscaEspacos(m), texto.length());
                // } else {
                //     novoTexto = novoTexto.replace(texto, txtSubs.getText());
                // }
            }
        } else {
            // if (chk1.isSelected() == true) {
            if (metodo == 1 && pesquisaPossuiCoringa(texto)) {
                // for (int n = marcados.size(); n > -1; n--) {
                    texto = texto.substring(0, texto.length() - 1);
                    System.out.println(texto);
                    novoTexto = novoTexto.replaceAll(texto + "\\S", txtSubs.getText());
                    // novoTexto = substituirWildCard( txtSubs.getText())
                // }
                mainPublic.texto.setText(novoTexto);
            } else {
                // for (int m : marcados) {
                novoTexto = novoTexto.replace(texto, txtSubs.getText());
                // }
                mainPublic.texto.setText(novoTexto);                
            }
            // }
        
        }
        
        mainPublic.zerarBackground();
    }
    
    private int buscaEspacos(int posicao) { // String texto = ""
        int espaco = 0;
        for (int i = posicao; i > 0; i--) {
            if(mainPublic.texto.getText().charAt(i) == 10 || mainPublic.texto.getText().charAt(i) == 13 && espaco == 0) {
                espaco = espaco+1;
            }
        }
        // System.out.println(espaco); // Comentando impressão
        return espaco;
    }
    
    private boolean pesquisaPossuiCoringa(String texto) {
        for (int k = 0; k < texto.length(); k++) {
            int k1 = texto.charAt(k);
            if (k1 == 63) {
                return true;
            }
        }
        return false;
    }
    
    private String substituirWildCard(String texto, String padrao) {
        Pattern regex = Pattern.compile("\\?([^?]*)\\?");
        Matcher matcherReg = regex.matcher(texto);

        StringBuffer strBuf = new StringBuffer();
        while (matcherReg.find()) {
             // String something = matcherReg.group(1);
             // System.out.println(something);
             matcherReg.appendReplacement(strBuf, padrao);

        }
        matcherReg.appendTail(strBuf);
        return strBuf.toString(); // String replaced
    } 
}