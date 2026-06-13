package dao;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReporteDAO {

    public void mostrarReporte() {

        try {

            Connection cn = Conexion.getConexion();

            // ✔ CARGA DIRECTA DEL JRXML (SIN PROBLEMAS DE RUTA)
            InputStream report = getClass()
                    .getResourceAsStream("/reportes/reporte_productos.jrxml");

            if (report == null) {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el archivo JRXML en /reportes/");
                return;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(report);

            JasperPrint print = JasperFillManager.fillReport(
                    jasperReport,
                    new HashMap<>(),
                    cn
            );

            JasperViewer.viewReport(print, false);

            cn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar reporte:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void exportarPDF(String rutaDestino) {

        try {

            Connection cn = Conexion.getConexion();

            InputStream report = getClass()
                    .getResourceAsStream("/reportes/reporte_productos.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(report);

            JasperPrint print = JasperFillManager.fillReport(
                    jasperReport,
                    new HashMap<>(),
                    cn
            );

            JasperExportManager.exportReportToPdfFile(print, rutaDestino);

            JOptionPane.showMessageDialog(null,
                    "PDF exportado correctamente");

            cn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error PDF:\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}