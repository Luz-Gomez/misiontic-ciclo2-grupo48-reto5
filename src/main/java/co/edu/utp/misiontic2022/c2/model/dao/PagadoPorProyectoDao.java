package co.edu.utp.misiontic2022.c2.model.dao;
// Seleccion de datos desde la BD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class PagadoPorProyectoDao {
    
    public List<PagadoPorProyectoVo> consultaPagadoPorProyecto(Double limiteInferior) throws SQLException {
        List<PagadoPorProyectoVo> listado = new ArrayList<>();

        var conectar = JDBCUtilities.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            var consulta = "SELECT	p.ID_Proyecto ID, SUM(c.Cantidad*mc.Precio_Unidad) AS VALOR " 
                + "FROM Proyecto p "
                + "INNER JOIN Compra c ON c.ID_Proyecto = p.ID_Proyecto "
                + "INNER JOIN MaterialConstruccion mc ON mc.ID_MaterialConstruccion = " 
                + "c.ID_MaterialConstruccion "
                + "WHERE c.Pagado = 'Si' "
                + "GROUP BY p.ID_Proyecto "
                + "HAVING SUM(c.Cantidad * mc.Precio_Unidad) > ? "
                + "ORDER BY VALOR DESC";

                sentencia = conectar.prepareStatement(consulta);
                sentencia.setDouble(1, limiteInferior);
                resultado = sentencia.executeQuery();
                
                while (resultado.next()) {
                    var datosVo = new PagadoPorProyectoVo();
                    datosVo.setId(resultado.getInt("ID"));
                    datosVo.setValor(resultado.getDouble("VALOR"));
                    listado.add(datosVo);
                }
            } finally {
                if (resultado != null) {
                    resultado.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
                if (conectar != null) {
                    conectar.close();
                }
            }
        return listado;
    }    
}
