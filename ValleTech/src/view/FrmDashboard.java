package view;

import dao.ReporteDAO;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FrmDashboard extends JFrame {

    public FrmDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("ValleTech - Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {

        // 💗 fondo rosado elegante
        Color fondo = new Color(255, 235, 245);

        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout());

        // =========================
        // TÍTULO PRINCIPAL
        // =========================
        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(190, 80, 140));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(35, 0, 25, 0));

        // =========================
        // BOTONES ROSA PASTEL
        // =========================
        JButton btnProductos = crearBoton("🛍 Gestión de Productos", new Color(255, 170, 200));
        JButton btnReportes  = crearBoton("📊 Ver Reporte",         new Color(255, 150, 190));
        JButton btnExportar  = crearBoton("💖 Exportar PDF",        new Color(255, 130, 180));
        JButton btnCerrar    = crearBoton("🚪 Cerrar Sesión",       new Color(255, 110, 170));

        // =========================
        // ACCIONES
        // =========================
        btnProductos.addActionListener(e -> new FrmProductos().setVisible(true));

        btnReportes.addActionListener(e -> new ReporteDAO().mostrarReporte());

        btnExportar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Guardar PDF");
            chooser.setSelectedFile(new File("reporte_productos.pdf"));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String ruta = chooser.getSelectedFile().getAbsolutePath();
                if (!ruta.endsWith(".pdf")) ruta += ".pdf";
                new ReporteDAO().exportarPDF(ruta);
            }
        });

        btnCerrar.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            dispose();
        });

        // =========================
        // PANEL PRINCIPAL
        // =========================
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(fondo);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 140, 40, 140));

        Dimension tam = new Dimension(340, 60);

        for (JButton btn : new JButton[]{btnProductos, btnReportes, btnExportar, btnCerrar}) {

            btn.setMaximumSize(tam);
            btn.setPreferredSize(tam);
            btn.setAlignmentX(CENTER_ALIGNMENT);

            // ✨ hover animación suave
            Color original = btn.getBackground();

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(original.darker());
                    btn.setFont(new Font("Segoe UI", Font.BOLD, 17));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(original);
                    btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
                }
            });

            panelBotones.add(btn);
            panelBotones.add(Box.createVerticalStrut(18));
        }

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        setSize(580, 470);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        return btn;
    }
}