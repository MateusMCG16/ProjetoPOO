package front;

import dao.CategoriaDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Categoria;

public class TelaCategoria extends JFrame {

    // Componentes da tela
    private JLabel labelTitulo;
    private JLabel labelNome;
    private JTextField txtNome;
    private JButton btnSalvar;

    public TelaCategoria() {
        // Configurações da Janela
        setTitle("Cadastro de Categorias");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só a janela, não o programa todo
        setLayout(null); // Layout absoluto para posicionar manual (mais fácil de entender no início)
        setLocationRelativeTo(null); // Centraliza na tela

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // Título
        labelTitulo = new JLabel("Nova Categoria");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(130, 20, 200, 30);
        add(labelTitulo);

        // Label Nome
        labelNome = new JLabel("Nome da Categoria:");
        labelNome.setBounds(30, 70, 150, 25);
        add(labelNome);

        // Campo de Texto (Input)
        txtNome = new JTextField();
        txtNome.setBounds(30, 95, 320, 25);
        add(txtNome);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(140, 150, 100, 30);
        // Ação do Botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCategoria();
            }
        });
        add(btnSalvar);
    }

    private void salvarCategoria() {
        // 1. Pegar o texto da tela
        String nome = txtNome.getText();

        // Validação simples
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!");
            return;
        }

        // 2. Criar o objeto Modelo
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(nome);

        // 3. Chamar o DAO para salvar no banco
        CategoriaDAO dao = new CategoriaDAO();
        dao.salvar(novaCategoria);

        // 4. Limpar o campo após salvar
        txtNome.setText("");
    }

    // Método main apenas para testar essa tela agora
    public static void main(String[] args) {
        new TelaCategoria().setVisible(true);
    }
}