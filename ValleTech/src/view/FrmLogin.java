package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class FrmLogin extends javax.swing.JFrame {

    public FrmLogin() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("ValleTech - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        new LoginController(this);
    }

    private void initComponents() {

        // 💗 fondo rosado elegante
        Color fondo = new Color(255, 235, 245);

        getContentPane().setBackground(fondo);
        setLayout(null);

        lblTitulo = new javax.swing.JLabel("VALLETECH");
        lblUsuario = new javax.swing.JLabel("Usuario:");
        lblPassword = new javax.swing.JLabel("Contraseña:");

        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton("Ingresar");

        // =========================
        // TÍTULO MÁS PRO
        // =========================
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(new Color(200, 80, 140));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setBounds(70, 20, 270, 60);

        // =========================
        // LABELS
        // =========================
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 15);

        lblUsuario.setFont(labelFont);
        lblPassword.setFont(labelFont);

        lblUsuario.setBounds(60, 110, 100, 25);
        lblPassword.setBounds(60, 160, 100, 25);

        // =========================
        // INPUTS
        // =========================
        txtUsuario.setBounds(150, 110, 200, 34);
        txtPassword.setBounds(150, 160, 200, 34);

        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(230, 170, 200)));
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(230, 170, 200)));

        // =========================
        // BOTÓN MODERNO
        // =========================
        btnIngresar.setBounds(150, 220, 200, 45);

        btnIngresar.setBackground(new Color(255, 120, 170));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // =========================
        // ✨ ANIMACIÓN HOVER SUAVE
        // =========================
        Color original = btnIngresar.getBackground();

        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(original.darker());
                btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 18));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(original);
                btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 17));
            }
        });

        // =========================
        // ADD
        // =========================
        add(lblTitulo);
        add(lblUsuario);
        add(lblPassword);
        add(txtUsuario);
        add(txtPassword);
        add(btnIngresar);

        setSize(420, 360);
    }

    // ✔ MVC PUBLIC
    public javax.swing.JTextField txtUsuario;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JButton btnIngresar;

    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblPassword;
}