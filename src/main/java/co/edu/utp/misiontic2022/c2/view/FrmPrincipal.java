package co.edu.utp.misiontic2022.c2.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

// Frame principal que va a contener las carpetas de las consultas 

public class FrmPrincipal extends JFrame{
    PanelComprasLider panelComprasLider;
    PanelPagoPorProyecto panelPagoPorProyecto;
    PanelProyectoPorBanco panelProyectoPorBanco;
    JTabbedPane carpetas;

    public FrmPrincipal() {
        setTitle("Misión TIC 2022 UTP - Ciclo 2 Grupo 48 - Reto 5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        carpetas = new JTabbedPane();
        panelProyectoPorBanco = new PanelProyectoPorBanco();
        panelPagoPorProyecto = new PanelPagoPorProyecto();
        panelComprasLider = new PanelComprasLider();

        carpetas.add("Listado de Proyectos por Banco", panelProyectoPorBanco);
        carpetas.add("Total Pagado por Proyecto", panelPagoPorProyecto);
        carpetas.add("Los 10 Lideres Menos Compradores", panelComprasLider);

        add(carpetas);

        var mensaje = "MisionTIC UTP Ciclo 2 Grupo 48 - Profesor Cesar Diaz - " +
                        "Autora Luz Aracely Gomez Torrente";

        JOptionPane.showMessageDialog (this, mensaje);
    }
}
