package controller;

import dao.ProductoDAO;
import model.Producto;
import view.FrmProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController implements ActionListener {

    private FrmProductos vista;
    private ProductoDAO dao;

    public ProductoController(FrmProductos vista) {
        this.vista = vista;
        this.dao = new ProductoDAO();

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);

        // Clic en tabla → carga datos en campos
        this.vista.tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarDesdeTabla();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar)   guardar();
        if (e.getSource() == vista.btnModificar) modificar();
        if (e.getSource() == vista.btnEliminar)  eliminar();
        if (e.getSource() == vista.btnBuscar)    buscar();
        if (e.getSource() == vista.btnListar)    listar();
    }

    private void guardar() {
        try {
            Producto p = new Producto();
            p.setNombre(vista.txtNombre.getText());
            p.setCategoria(vista.txtCategoria.getText());
            p.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            p.setStock(Integer.parseInt(vista.txtStock.getText()));

            if (dao.insertar(p)) {
                JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar producto.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Precio y Stock deben ser numéricos.");
        }
    }

    private void modificar() {
        try {
            if (vista.txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla.");
                return;
            }
            Producto p = new Producto();
            p.setId(Integer.parseInt(vista.txtId.getText()));
            p.setNombre(vista.txtNombre.getText());
            p.setCategoria(vista.txtCategoria.getText());
            p.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            p.setStock(Integer.parseInt(vista.txtStock.getText()));

            if (dao.actualizar(p)) {
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Precio y Stock deben ser numéricos.");
        }
    }

    private void eliminar() {
        if (vista.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecciona un producto de la tabla.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
            "¿Eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(vista.txtId.getText());
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado.");
                limpiarCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar.");
            }
        }
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(vista.txtId.getText());
            Producto p = dao.buscar(id);
            if (p != null) {
                vista.txtNombre.setText(p.getNombre());
                vista.txtCategoria.setText(p.getCategoria());
                vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
                vista.txtStock.setText(String.valueOf(p.getStock()));
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingresa un ID válido.");
        }
    }

    private void listar() {
        List<Producto> lista = dao.listar();
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();
        modelo.setRowCount(0);
        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getCategoria(),
                p.getPrecio(), p.getStock()
            });
        }
    }

    private void cargarDesdeTabla() {
        int fila = vista.tblProductos.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();
            vista.txtId.setText(modelo.getValueAt(fila, 0).toString());
            vista.txtNombre.setText(modelo.getValueAt(fila, 1).toString());
            vista.txtCategoria.setText(modelo.getValueAt(fila, 2).toString());
            vista.txtPrecio.setText(modelo.getValueAt(fila, 3).toString());
            vista.txtStock.setText(modelo.getValueAt(fila, 4).toString());
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtCategoria.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
    }
}
