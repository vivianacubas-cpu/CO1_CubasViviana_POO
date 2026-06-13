package main;

import view.FrmLogin;

public class Main {

    public static void main(String[] args) {

        // Compilar reporte automáticamente si no existe el .jasper
        compilarReporte();

        javax.swing.SwingUtilities.invokeLater(() -> {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
        });
    }

    private static void compilarReporte() {
        try {
            java.io.File jasper = new java.io.File("src/reportes/reporte_productos.jasper");
            if (!jasper.exists()) {
                net.sf.jasperreports.engine.JasperCompileManager.compileReportToFile(
                    "src/reportes/reporte_productos.jrxml",
                    "src/reportes/reporte_productos.jasper"
                );
                System.out.println("Reporte compilado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Aviso reporte: " + e.getMessage());
        }
    }
}
