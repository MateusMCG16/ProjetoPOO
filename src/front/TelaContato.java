package front;

import controller.CategoriaController;
import controller.ContatoController;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelo.Categoria;
import modelo.Contato;

public class TelaContato extends JFrame {

    private JLabel labelTitulo;
    private JLabel labelNome;
    private JTextField txtNome;
    private JLabel labelEmail;
    private JTextField txtEmail;
    private JLabel labelTelefone;
    private JTextField txtTelefone;
    private JLabel labelCategoria;
    private JComboBox<Categoria> cbCategoria;
    private JButton btnSalvar;
    
    private Contato contatoEdicao = null;

    public TelaContato() {
        setTitle("Gerenciamento de Contatos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        iniciarComponentes();
    }
    
    public TelaContato(Contato contato, String nomeCategoria) {
        this(); // Chama o construtor padrão para iniciar componentes
        this.contatoEdicao = contato;
        
        labelTitulo.setText("Editar Contato");
        btnSalvar.setText("Atualizar");
        
        txtNome.setText(contato.getNome());
        txtEmail.setText(contato.getEmail());
        txtTelefone.setText(contato.getTelefone());
        
        // Selecionar a categoria no ComboBox
        if (nomeCategoria != null) {
            for (int i = 0; i < cbCategoria.getItemCount(); i++) {
                Categoria c = cbCategoria.getItemAt(i);
                if (c.getNome().equals(nomeCategoria)) {
                    cbCategoria.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void iniciarComponentes() {
        labelTitulo = new JLabel("Novo Contato");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(180, 20, 200, 30);
        add(labelTitulo);

        labelNome = new JLabel("Nome:");
        labelNome.setBounds(40, 70, 100, 25);
        add(labelNome);
        txtNome = new JTextField();
        txtNome.setBounds(40, 95, 400, 25);
        add(txtNome);

        labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(40, 130, 100, 25);
        add(labelEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(40, 155, 400, 25);
        add(txtEmail);

        labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(40, 190, 100, 25);
        add(labelTelefone);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(40, 215, 180, 25);
        add(txtTelefone);

        labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(240, 190, 100, 25);
        add(labelCategoria);
        
        cbCategoria = new JComboBox<>();
        cbCategoria.setBounds(240, 215, 200, 25);
        
        // Chama método para preencher (agora via controller)
        preencherCategorias();
        
        add(cbCategoria);

        btnSalvar = new JButton("Salvar Contato");
        btnSalvar.setBounds(150, 280, 180, 40);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarContato();
            }
        });
        add(btnSalvar);
    }

    private void preencherCategorias() {
        CategoriaController controller = new CategoriaController();
        List<Categoria> lista = controller.listar();
        
        cbCategoria.removeAllItems();
        
        for (Categoria c : lista) {
            cbCategoria.addItem(c); 
        }
    }

    private void salvarContato() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        Categoria categoriaSelecionada = (Categoria) cbCategoria.getSelectedItem();

        ContatoController controller = new ContatoController();
        
        int id = 0;
        if (contatoEdicao != null) {
            id = contatoEdicao.getIdContato();
        }
        
        boolean sucesso = controller.salvar(id, nome, email, telefone, categoriaSelecionada);

        if (sucesso) {
            if (contatoEdicao == null) {
                txtNome.setText("");
                txtEmail.setText("");
                txtTelefone.setText("");
            } else {
                dispose(); // Fecha a janela se for edição
            }
        }
    }

    public static void main(String[] args) {
        new TelaContato().setVisible(true);
    }
}