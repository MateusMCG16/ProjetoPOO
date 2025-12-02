package front;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Agenda de Contatos - SAC");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        criarMenu();
    }

    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuCadastros = new JMenu("Cadastros");
        
        JMenuItem itemCategoria = new JMenuItem("Categorias");
        JMenuItem itemContato = new JMenuItem("Contatos");
        JMenuItem itemSair = new JMenuItem("Sair");
        
        itemCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCategoria().setVisible(true);
            }
        });
        
        itemContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaContato().setVisible(true);
            }
        });
        
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        menuCadastros.add(itemCategoria);
        menuCadastros.add(itemContato);
        menuCadastros.addSeparator();
        menuCadastros.add(itemSair);
        
        JMenu menuConsultas = new JMenu("Consultas");
        JMenuItem itemPesquisa = new JMenuItem("Pesquisar / Listar Contatos");
        
        itemPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new TelaListagem().setVisible(true);
            }
        });
        
        menuConsultas.add(itemPesquisa);
        
        menuBar.add(menuCadastros);
        menuBar.add(menuConsultas);
        
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}