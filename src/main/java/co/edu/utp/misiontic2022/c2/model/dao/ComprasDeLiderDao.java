package co.edu.utp.misiontic2022.c2.model.dao;
// Seleccion de datos desde la BD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class ComprasDeLiderDao {
    
    public List<ComprasDeLiderVo> consultaComprasDelLider() throws SQLException {
        List<ComprasDeLiderVo> listado = new ArrayList<>();
        var conectar = JDBCUtilities.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            var consulta = "SELECT (l.Nombre || ' ' || l.Primer_Apellido || ' ' || "
                + "l.Segundo_Apellido) AS LIDER, SUM(c.Cantidad*mc.Precio_Unidad) AS VALOR "
                + "FROM Proyecto p "
                + "INNER JOIN Compra c ON c.ID_Proyecto = p.ID_Proyecto "
                + "INNER JOIN MaterialConstruccion mc ON mc.ID_MaterialConstruccion = "
                + "c.ID_MaterialConstruccion "
                + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
                + "GROUP BY LIDER "
                + "ORDER BY VALOR "
                + "LIMIT 10";

            sentencia = conectar.prepareStatement(consulta);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                var datosVo = new ComprasDeLiderVo();
                datosVo.setLider(resultado.getString("LIDER"));
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
