package front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox; // Import adicionado
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    // Componentes promovidos a atributos da classe para serem modificados dinamicamente
    private JLabel labelTitulo;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JComboBox<String> cbIdioma; // Novo componente para seleção de idioma

    public TelaLogin() {
        setTitle("Login - Sistema de Agenda");
        setSize(350, 300); // Aumentei um pouco a altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // --- Configuração do Seletor de Idioma ---
        String[] idiomas = {"Português", "English"};
        cbIdioma = new JComboBox<>(idiomas);
        cbIdioma.setBounds(220, 10, 100, 25); // Posicionado no canto superior direito
        cbIdioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarIdioma(); // Chama a função quando troca o item da lista
            }
        });
        add(cbIdioma);

        // --- Título ---
        labelTitulo = new JLabel("Login");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setBounds(140, 40, 100, 30);
        add(labelTitulo);

        // --- Usuário ---
        labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(40, 90, 80, 25);
        add(labelUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 90, 180, 25);
        add(txtUsuario);

        // --- Senha ---
        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(40, 130, 80, 25);
        add(labelSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 130, 180, 25);
        add(txtSenha);

        // --- Botão ---
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(120, 180, 100, 30);
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        add(btnEntrar);
    }

    // Método responsável por traduzir a interface
    private void atualizarIdioma() {
        String idiomaSelecionado = (String) cbIdioma.getSelectedItem();

        if ("English".equals(idiomaSelecionado)) {
            setTitle("Login - Agenda System");
            labelTitulo.setText("Login");
            labelUsuario.setText("Username:");
            labelSenha.setText("Password:");
            btnEntrar.setText("Login");
        } else {
            setTitle("Login - Sistema de Agenda");
            labelTitulo.setText("Login");
            labelUsuario.setText("Usuário:");
            labelSenha.setText("Senha:");
            btnEntrar.setText("Entrar");
        }
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        String idiomaSelecionado = (String) cbIdioma.getSelectedItem();

        if ("admin".equals(usuario) && "admin".equals(senha)) {
            // Supondo que a classe TelaPrincipal exista no seu projeto
            new TelaPrincipal().setVisible(true);
            this.dispose();
        } else {
            // Define a mensagem de erro baseada no idioma
            String msgErro;
            String tituloErro;
            
            if ("English".equals(idiomaSelecionado)) {
                msgErro = "Invalid username or password!";
                tituloErro = "Error";
            } else {
                msgErro = "Usuário ou senha inválidos!";
                tituloErro = "Erro";
            }
            
            JOptionPane.showMessageDialog(this, msgErro, tituloErro, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}