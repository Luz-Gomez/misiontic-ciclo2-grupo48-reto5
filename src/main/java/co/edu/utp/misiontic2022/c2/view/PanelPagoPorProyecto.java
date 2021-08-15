package co.edu.utp.misiontic2022.c2.view;
// Implementacion de vista para consulta de Proyectos con limite inferior
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.controller.ConsultasController;
import co.edu.utp.misiontic2022.c2.model.vo.PagadoPorProyectoVo;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

public class PanelPagoPorProyecto extends JPanel{
    private ConsultasController controller;
    private JTable tabla;

    public PanelPagoPorProyecto() {
        controller = new ConsultasController();

        setLayout(new BorderLayout());
        var panelVariable = new JPanel();
        panelVariable.add(new JLabel("Digite Limite Inferior   "));
        var txtLimite = new JTextField(10);
        txtLimite.setHorizontalAlignment(JTextField.TRAILING);
        panelVariable.add(txtLimite);
        var btnConsulta = new JButton("Consultar");
        btnConsulta.addActionListener(e -> consutarPagoPorProyecto(
                    (Double.parseDouble(txtLimite.getText()))));
        panelVariable.add(btnConsulta);
        add(panelVariable, BorderLayout.PAGE_START);
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void consutarPagoPorProyecto(Double dato){
        try {
            var lista = controller.listaPagadoPorProyecto(dato);
            var tableModel = new ProyectoTableModel();
            tableModel.setData(lista);
            tabla.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getName(), 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private class ProyectoTableModel extends AbstractTableModel {
        private List<PagadoPorProyectoVo> data;
    
        public void setData(List<PagadoPorProyectoVo> data) {
            this.data = data;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                case 1:
                    return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID PROYECTO";
                case 1:
                    return "VALOR TOTAL PAGADO";
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
                    return proyecto.getId();
                case 1:
                    return proyecto.getValor();
            }
            return null;
        }
    }    
}