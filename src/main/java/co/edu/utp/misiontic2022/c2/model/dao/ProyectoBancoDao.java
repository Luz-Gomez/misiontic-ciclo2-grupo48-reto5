package co.edu.utp.misiontic2022.c2.model.dao;
// Seleccion de datos desde la BD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class ProyectoBancoDao {
 
    public List<ProyectoBancoVo> consultaProyectoBanco(String banco) throws SQLException {
        List<ProyectoBancoVo> listado = new ArrayList<>();

        var conectar = JDBCUtilities.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            var consulta = "SELECT p.ID_Proyecto ID, p.Constructora, p.Ciudad, p.Clasificacion, t.Estrato,"
                       + " (l.Nombre || ' ' || l.Primer_Apellido || ' ' || l.Segundo_Apellido) AS LIDER "
                + "FROM Proyecto p "
                + "INNER JOIN Tipo t ON t.ID_Tipo = p.ID_Tipo "
                + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
                + "WHERE p.Banco_Vinculado = ? "
                + "ORDER BY p.Fecha_Inicio DESC, p.Ciudad ASC, p.Constructora";

                sentencia = conectar.prepareStatement(consulta);
                sentencia.setString(1, banco);
                resultado = sentencia.executeQuery();
                
                while (resultado.next()) {
                    var datosVo = new ProyectoBancoVo();
                    datosVo.setId(resultado.getInt("ID"));
                    datosVo.setConstructora(resultado.getString("CONSTRUCTORA"));
                    datosVo.setCiudad(resultado.getString("CIUDAD"));
                    datosVo.setClasificacion(resultado.getString("CLASIFICACION"));
                    datosVo.setEstrato(resultado.getInt("ESTRATO"));
                    datosVo.setLider(resultado.getString("LIDER"));
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
