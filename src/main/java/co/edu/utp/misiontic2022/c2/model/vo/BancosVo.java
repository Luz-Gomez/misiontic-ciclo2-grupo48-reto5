package co.edu.utp.misiontic2022.c2.model.vo;
// Consulta de Bancos para seleccion en ComboBox
public class BancosVo {
    private String banco;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Bancos [banco=" + banco + "]";
    }
}
