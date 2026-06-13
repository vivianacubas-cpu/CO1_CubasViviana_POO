package view;

import controller.ProductoController;
import javax.swing.*;
import java.awt.*;

public class FrmProductos extends JFrame {

    public FrmProductos() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestión de Productos - ValleTech");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        new ProductoController(this);
    }

    private void initComponents() {

        // 💗 fondo rosado suave
        getContentPane().setBackground(new Color(255, 240, 248));
        setLayout(null);

        // =========================
        // COMPONENTES
        // =========================
        lblTitulo = new JLabel("GESTIÓN DE PRODUCTOS");

        lblId = new JLabel("ID:");
        lblNombre = new JLabel("Nombre:");
        lblCategoria = new JLabel("Categoría:");
        lblPrecio = new JLabel("Precio:");
        lblStock = new JLabel("Stock:");

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtCategoria = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnListar = new JButton("Listar");

        tblProductos = new JTable();
        jScrollPane1 = new JScrollPane(tblProductos);

        // =========================
        // TÍTULO
        // =========================
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(180, 90, 140));
        lblTitulo.setBounds(160, 10, 500, 40);

        // =========================
        // LABELS
        // =========================
        Font f = new Font("Segoe UI", Font.PLAIN, 14);

        lblId.setBounds(40, 70, 100, 25);
        lblNombre.setBounds(40, 110, 100, 25);
        lblCategoria.setBounds(40, 150, 100, 25);
        lblPrecio.setBounds(40, 190, 100, 25);
        lblStock.setBounds(40, 230, 100, 25);

        lblId.setFont(f);
        lblNombre.setFont(f);
        lblCategoria.setFont(f);
        lblPrecio.setFont(f);
        lblStock.setFont(f);

        // =========================
        // TEXTFIELDS
        // =========================
        txtId.setBounds(140, 70, 220, 30);
        txtNombre.setBounds(140, 110, 220, 30);
        txtCategoria.setBounds(140, 150, 220, 30);
        txtPrecio.setBounds(140, 190, 220, 30);
        txtStock.setBounds(140, 230, 220, 30);

        txtId.setEditable(false);
        txtId.setBackground(new Color(240, 240, 240));

        // =========================
        // BOTONES
        // =========================
        JButton[] botones = {btnGuardar, btnModificar, btnEliminar, btnBuscar, btnListar};

        Color[] colores = {
                new Color(255, 160, 190),
                new Color(255, 140, 180),
                new Color(255, 120, 170),
                new Color(255, 150, 200),
                new Color(255, 180, 210)
        };

        int x = 40;

        for (int i = 0; i < botones.length; i++) {
            JButton b = botones[i];

            b.setBounds(x, 280, 100, 38);
            b.setBackground(colores[i]);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            b.setFocusPainted(false);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));

            x += 110;
        }

        // =========================
        // TABLA
        // =========================
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Categoría", "Precio", "Stock"}
        ));

        tblProductos.setRowHeight(22);

        jScrollPane1.setBounds(40, 340, 620, 180);

        // =========================
        // ADD
        // =========================
        add(lblTitulo);

        add(lblId);
        add(lblNombre);
        add(lblCategoria);
        add(lblPrecio);
        add(lblStock);

        add(txtId);
        add(txtNombre);
        add(txtCategoria);
        add(txtPrecio);
        add(txtStock);

        add(btnGuardar);
        add(btnModificar);
        add(btnEliminar);
        add(btnBuscar);
        add(btnListar);

        add(jScrollPane1);

        setSize(720, 600);
    }

    // PUBLIC MVC
    public JTextField txtId, txtNombre, txtCategoria, txtPrecio, txtStock;

    public JButton btnGuardar, btnModificar, btnEliminar, btnBuscar, btnListar;

    public JTable tblProductos;

    private JLabel lblTitulo, lblId, lblNombre, lblCategoria, lblPrecio, lblStock;

    private JScrollPane jScrollPane1;
}