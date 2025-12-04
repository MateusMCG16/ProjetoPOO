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

    private JLabel labelTitulo;
    private JLabel labelNome;
    private JTextField txtNome;
    private JButton btnSalvar;

    public TelaCategoria() {
        setTitle("Cadastro de Categorias");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        labelTitulo = new JLabel("Nova Categoria");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(130, 20, 200, 30);
        add(labelTitulo);

        labelNome = new JLabel("Nome da Categoria:");
        labelNome.setBounds(30, 70, 150, 25);
        add(labelNome);

        txtNome = new JTextField();
        txtNome.setBounds(30, 95, 320, 25);
        add(txtNome);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(140, 150, 100, 30);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCategoria();
            }
        });
        add(btnSalvar);
    }

    private void salvarCategoria() {
        String nome = txtNome.getText();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome não pode ser vazio!");
            return;
        }

        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome(nome);

        CategoriaDAO dao = new CategoriaDAO();
        dao.salvar(novaCategoria);

        txtNome.setText("");
    }

    public static void main(String[] args) {
        new TelaCategoria().setVisible(true);
    }
}