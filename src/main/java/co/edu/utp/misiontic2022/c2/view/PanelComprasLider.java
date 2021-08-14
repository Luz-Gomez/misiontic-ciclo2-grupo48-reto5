package co.edu.utp.misiontic2022.c2.view;
// Implementacion de vista para consulta de Compras de lideres
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.controller.ConsultasController;
import co.edu.utp.misiontic2022.c2.model.vo.ComprasDeLiderVo;

public class PanelComprasLider extends JPanel{
    private ConsultasController controller;
    private JTable tabla;

    public PanelComprasLider() {
        controller = new ConsultasController();

        setLayout(new BorderLayout());
        var panelVariable = new JPanel();
    
        var btnConsulta = new JButton("Consultar 10 Lideres con las Menores Compras");
        btnConsulta.addActionListener(e -> consultarComprasLider());
        panelVariable.add(btnConsulta);
        add(panelVariable, BorderLayout.PAGE_START);
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void consultarComprasLider(){
        try {
            var lista = controller.listaComprasDeLider();
            var tableModel = new ProyectoTableModel();
            tableModel.setData(lista);
            tabla.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getName(), 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private class ProyectoTableModel extends AbstractTableModel {
        private List<ComprasDeLiderVo> data;
    
        public void setData(List<ComprasDeLiderVo> data) {
            this.data = data;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "LIDER";
                case 1:
                    return "VALOR TOTAL COMPRAS";
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var proyecto = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return proyecto.getLider();
                case 1:
                    return proyecto.getValor();
            }
            return null;
        }
    }
}