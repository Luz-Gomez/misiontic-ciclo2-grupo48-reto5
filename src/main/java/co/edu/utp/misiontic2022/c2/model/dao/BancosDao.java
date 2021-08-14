package co.edu.utp.misiontic2022.c2.model.dao;
// Seleccion de datos desde la BD

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.vo.BancosVo;
import co.edu.utp.misiontic2022.c2.util.JDBCUtilities;

public class BancosDao {

    public List<BancosVo> consultaBancos() throws SQLException {
        List<BancosVo> listado = new ArrayList<>();
        var conectar = JDBCUtilities.getConnection();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            var consulta = "SELECT Banco_Vinculado BANCO FROM Proyecto "
            + "GROUP BY BANCO ORDER BY BANCO";

            sentencia = conectar.prepareStatement(consulta);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                var datosVo = new BancosVo();
                datosVo.setBanco(resultado.getString("BANCO"));
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
