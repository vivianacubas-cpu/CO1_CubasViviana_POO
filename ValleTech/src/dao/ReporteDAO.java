package dao;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReporteDAO {

    private String getRutaJasper() {
        // Busca el .jasper en varias ubicaciones posibles
        String[] rutas = {
            "src/reportes/reporte_productos.jasper",
            "reportes/reporte_productos.jasper",
            "out/production/ValleTech/reportes/reporte_productos.jasper"
        };
        for (String ruta : rutas) {
            if (new File(ruta).exists()) return ruta;
        }
        // Como recurso del classpath
        java.net.URL url = getClass().getResource("/reportes/reporte_productos.jasper");
        if (url != null) return url.getPath();
        return null;
    }

    public void mostrarReporte() {
        try {
            // Compilar si solo existe el .jrxml
            asegurarJasper();

            String ruta = getRutaJasper();
            if (ruta == null) {
                JOptionPane.showMessageDialog(null,
                    "No se encontró el archivo reporte_productos.jasper\n" +
                    "Verifica que esté en src/reportes/");
                return;
            }

            Connection cn = Conexion.getConexion();
            Map<String, Object> params = new HashMap<>();
            params.put("TITULO", "Reporte de Productos - ValleTech");

            JasperPrint print = JasperFillManager.fillReport(ruta, params, cn);
            JasperViewer.viewReport(print, false);
            cn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar reporte:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void exportarPDF(String rutaDestino) {
        try {
            asegurarJasper();

            String ruta = getRutaJasper();
            if (ruta == null) {
                JOptionPane.showMessageDialog(null, "No se encontró reporte_productos.jasper");
                return;
            }

            Connection cn = Conexion.getConexion();
            Map<String, Object> params = new HashMap<>();
            params.put("TITULO", "Reporte de Productos - ValleTech");

            JasperPrint print = JasperFillManager.fillReport(ruta, params, cn);
            JasperExportManager.exportReportToPdfFile(print, rutaDestino);
            cn.close();

            JOptionPane.showMessageDialog(null, "PDF exportado en:\n" + rutaDestino);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar PDF:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void asegurarJasper() {
        String[] jrxmlRutas = {
            "src/reportes/reporte_productos.jrxml",
            "reportes/reporte_productos.jrxml"
        };
        for (String jrxml : jrxmlRutas) {
            File f = new File(jrxml);
            if (f.exists()) {
                String jasper = jrxml.replace(".jrxml", ".jasper");
                if (!new File(jasper).exists()) {
                    try {
                        JasperCompileManager.compileReportToFile(jrxml, jasper);
                        System.out.println("Reporte compilado: " + jasper);
                    } catch (Exception e) {
                        System.out.println("Error compilando reporte: " + e.getMessage());
                    }
                }
                break;
            }
        }
    }
}
