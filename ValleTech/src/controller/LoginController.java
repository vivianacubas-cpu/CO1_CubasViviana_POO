package controller;

import dao.UsuarioDAO;
import view.FrmDashboard;
import view.FrmLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private FrmLogin vista;
    private UsuarioDAO dao;

    public LoginController(FrmLogin vista) {
        this.vista = vista;
        this.dao = new UsuarioDAO();
        this.vista.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            login();
        }
    }

    private void login() {
        String usuario  = vista.txtUsuario.getText();
        String password = String.valueOf(vista.txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete usuario y contraseña.");
            return;
        }

        boolean acceso = dao.validarUsuario(usuario, password);

        if (acceso) {
            JOptionPane.showMessageDialog(null, "¡Acceso correcto!");
            FrmDashboard dashboard = new FrmDashboard();
            dashboard.setVisible(true);
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        }
    }
}
