package front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public TelaLogin() {
        setTitle("Login - Sistema de Agenda");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel labelTitulo = new JLabel("Login");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setBounds(140, 20, 100, 30);
        add(labelTitulo);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(40, 70, 80, 25);
        add(labelUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 70, 180, 25);
        add(txtUsuario);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(40, 110, 80, 25);
        add(labelSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 110, 180, 25);
        add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(120, 160, 100, 30);
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        add(btnEntrar);
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        // Hardcoded credentials as requested
        if ("admin".equals(usuario) && "admin".equals(senha)) {
            new TelaPrincipal().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}
