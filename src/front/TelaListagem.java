package front;

import controller.ContatoController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Contato;

public class TelaListagem extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField txtPesquisa;
    private JButton btnPesquisar;
    private JButton btnAtualizar;

    public TelaListagem() {
        setTitle("Listagem de Contatos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        iniciarComponentes();
        carregarDados("");
    }

    private void iniciarComponentes() {
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        painelTopo.add(new JLabel("Pesquisar por Nome:"));
        
        txtPesquisa = new JTextField(20);
        painelTopo.add(txtPesquisa);
        
        btnPesquisar = new JButton("Buscar");
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados(txtPesquisa.getText());
            }
        });
        painelTopo.add(btnPesquisar);
        
        btnAtualizar = new JButton("Recarregar Todos");
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPesquisa.setText("");
                carregarDados("");
            }
        });
        painelTopo.add(btnAtualizar);
        
        add(painelTopo, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "E-mail", "Telefone", "Categoria"};
        
        modeloTabela = new DefaultTableModel(colunas, 0);
        
        tabela = new JTable(modeloTabela);
        
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarContato();
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirContato();
            }
        });
        
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void carregarDados(String termo) {
        modeloTabela.setRowCount(0);
        
        ContatoController controller = new ContatoController();
        List<Contato> lista;
        
        if (termo != null && !termo.isEmpty()) {
            lista = controller.pesquisar(termo);
        } else {
            lista = controller.listar();
        }

        for (Contato c : lista) {
            Object[] linha = {
                c.getIdContato(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getCategoria().getNome()
            };
            modeloTabela.addRow(linha);
        }
    }
    
    private void editarContato() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para editar!");
            return;
        }
        
        int idContato = (int) tabela.getValueAt(linhaSelecionada, 0);
        String nome = (String) tabela.getValueAt(linhaSelecionada, 1);
        String email = (String) tabela.getValueAt(linhaSelecionada, 2);
        String telefone = (String) tabela.getValueAt(linhaSelecionada, 3);
        String nomeCategoria = (String) tabela.getValueAt(linhaSelecionada, 4);
        
        Contato contato = new Contato();
        contato.setIdContato(idContato);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        
        new TelaContato(contato, nomeCategoria).setVisible(true);
    }

    private void excluirContato() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para excluir!");
            return;
        }
        
        int idContato = (int) tabela.getValueAt(linhaSelecionada, 0);
        
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            ContatoController controller = new ContatoController();
            if (controller.excluir(idContato)) {
                carregarDados("");
            }
        }
    }
}