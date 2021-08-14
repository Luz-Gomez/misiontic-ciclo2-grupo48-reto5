package co.edu.utp.misiontic2022.c2.controller;
// Controlador para las consultas generadas en DAO
import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.model.dao.PagadoPorProyectoDao;
import co.edu.utp.misiontic2022.c2.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;
import co.edu.utp.misiontic2022.c2.model.vo.ProyectoBancoVo;

public class ConsultasController {
    private ProyectoBancoDao proyectoBancoDao;
    private PagadoPorProyectoDao pagadoPorProyectoDao;
    private ComprasDeLiderDao comprasDeLiderDao;

    public ConsultasController() {
        proyectoBancoDao = new ProyectoBancoDao();
        pagadoPorProyectoDao = new PagadoPorProyectoDao();
        comprasDeLiderDao = new ComprasDeLiderDao();
    }

    public List<ProyectoBancoVo> listaProyectoBanco(String banco) throws SQLException {
        return proyectoBancoDao.consultaProyectoBanco(banco);
    }

    public List<PagadoPorProyectoVo> listaPagadoPorProyecto(Double limiteInferior) throws SQLException {
        return pagadoPorProyectoDao.consultaPagadoPorProyecto(limiteInferior);
    }

    public List<ComprasDeLiderVo> listaComprasDeLider() throws SQLException {
        return comprasDeLiderDao.consultaComprasDelLider();
    }    
}
