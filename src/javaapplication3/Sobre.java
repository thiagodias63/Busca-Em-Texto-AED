package javaapplication3;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Sobre extends JFrame implements ActionListener {
    
    JPanel p1 = new JPanel(new GridLayout(3, 4));
    JLabel lb1 = new JLabel("Autor: Thiago Dias Bittencourt");
    JLabel lb12 = new JLabel("Curso: Sistemas de informação");
    JButton btSair = new JButton("Sair");
    
    public Sobre(){
        super("Sobre");		
        p1.add(lb1);
        p1.add(lb12);
        p1.add(btSair);
        btSair.addActionListener(this);
        lb1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getContentPane().add(p1, BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();		
        
        p1.setLocation(100, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSair) {
            this.dispose();
        }		
    }
}