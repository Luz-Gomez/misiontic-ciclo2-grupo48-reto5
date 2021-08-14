package co.edu.utp.misiontic2022.c2;

import co.edu.utp.misiontic2022.c2.view.FrmPrincipal;

// Ajuste de la App para ejecutar a partir de las vistas

public class App 
{
    public static void main( String[] args )
    {
        var vista = new FrmPrincipal();
        vista.setVisible(true);
    }
}