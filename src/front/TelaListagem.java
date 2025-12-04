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
}