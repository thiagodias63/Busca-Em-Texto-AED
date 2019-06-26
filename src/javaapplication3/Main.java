package javaapplication3;

import com.sun.java.swing.plaf.motif.MotifMenuUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import javaapplication3.PesquisarGUI;
import javax.swing.text.StyleConstants;

class Main extends JFrame implements ActionListener {
    JTextPane texto; // componente que permite mudança de cores e outros atributos do texto
    JScrollPane painel; // painel com barra de rolagem

    JMenuBar menuBar = new JMenuBar();
    JMenu mArquivo = new JMenu("Arquivo"); 
    JMenuItem mNovo = new JMenuItem("Novo", KeyEvent.VK_N); 
    JMenuItem mAbrir = new JMenuItem("Abrir", KeyEvent.VK_A);
    JMenuItem mSair = new JMenuItem("Sair", KeyEvent.VK_I);

    /* adicione as demais opções do menu aqui... */
    JMenuItem mProcurarFb = new JMenuItem("Força Bruta", KeyEvent.VK_F);
    JMenuItem mProcurarBm = new JMenuItem("Boyer Moore", KeyEvent.VK_B);
    JMenuItem mProcurarKm = new JMenuItem("KMP", KeyEvent.VK_K);
    JMenuItem mProcurarRk = new JMenuItem("Rabin Karp", KeyEvent.VK_R);
    
    JMenu mPesquisar = new JMenu("Pesquisar");
    
    JMenu mAjuda = new JMenu("Ajuda");
    JMenuItem mSobre = new JMenuItem("Sobre", KeyEvent.VK_H);
    
    public Main() {
        super("Trabalho Prático - Busca em Texto 2019.1");

        mArquivo.setMnemonic(KeyEvent.VK_A);
        menuBar.add(mArquivo);

        mNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mNovo.addActionListener(this);
        mArquivo.add(mNovo);

        mAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        mAbrir.addActionListener(this);
        mArquivo.add(mAbrir);		
        // Força Bruta
        mProcurarFb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        mProcurarFb.addActionListener(this);
        mPesquisar.add(mProcurarFb);		
        // BoyerMoore
        mProcurarBm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        mProcurarBm.addActionListener(this);
        mPesquisar.add(mProcurarBm);
        // KMP
        mProcurarKm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        mProcurarKm.addActionListener(this);
        mPesquisar.add(mProcurarKm);
        // RabinKarp
        mProcurarRk.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        mProcurarRk.addActionListener(this);
        mPesquisar.add(mProcurarRk);
        
        mArquivo.add(mPesquisar);
                
        mArquivo.addSeparator(); // separador
        
        mSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        mSobre.addActionListener(this);
        
        mAjuda.add(mSobre);
        mArquivo.add(mAjuda);
        
        mArquivo.addSeparator(); // separador
        
        mSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        mSair.addActionListener(this);
        mArquivo.add(mSair);
        

        setJMenuBar(menuBar); // adiciona a barra de menus ao frame		

        texto = new JTextPane();
        painel = new JScrollPane(texto);

        getContentPane().add(painel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);		
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mSair) {
            System.exit(0);
        }

        else if(e.getSource() == mNovo) {
            texto.setText("");
        }

        else if(e.getSource() == mAbrir) {
            try {				
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(this);

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    // Para abrir RTF, consulte a documentação do componente JTextPane. 
                    
                    texto.setContentType("text/plain");
                    texto.read(new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsoluteFile())), "");
                    // texto.setText(texto.getText().replace("\r", "")); 
                    // substitui quebra de linha padrão "Windows" (\r\n) por somente (\n) 
                }
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir arquivo.\n" + ex);
            }
        }
        // Força Bruta
        else if(e.getSource() == mProcurarFb) {
            descolorirTexto();
            new PesquisarGUI(this, 1);
        }
        // BoyerMoore
        else if(e.getSource() == mProcurarBm) {
            descolorirTexto();
            new PesquisarGUI(this, 2);
        }
        // KMP
        else if(e.getSource() == mProcurarKm) {
            descolorirTexto();
            new PesquisarGUI(this, 3);
        }
        // Rabin Karp
        else if(e.getSource() == mProcurarRk) {
            descolorirTexto();
            new PesquisarGUI(this, 4);
        }
        else if(e.getSource() == mSobre) {
            new Sobre();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
    
    public void colorirTexto(int inicio, int tamanho) {
        StyleConstants.setBackground(texto.getInputAttributes(), Color.YELLOW);
        texto.getStyledDocument().setCharacterAttributes(inicio, tamanho, texto.getInputAttributes(), false);
    }
    
    public void descolorirTexto () {
        StyleConstants.setBackground(texto.getInputAttributes(), Color.WHITE);
        texto.getStyledDocument().setCharacterAttributes(0, texto.getText().length(), texto.getInputAttributes(), false);
    }
    
    public void zerarBackground() {
        StyleConstants.setBackground(texto.getInputAttributes(), Color.WHITE);
    }
}