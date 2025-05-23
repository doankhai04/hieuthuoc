package gui.page.thongke;

import controller.ThongKeController;
import entities.ThongKeTheoNam;
import gui.barchart.ModelChart;
import gui.barchart.Chart;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utils.Formatter;
import utils.JTableExporter;
import utils.MessageDialog;
import utils.TableSorter;
import utils.Validation;

/**
 *
 * @author atuandev
 */
public class ThongKeDoanhThuTheoNamPage extends javax.swing.JPanel {
private Chart chart;
    private final int currentYear = LocalDate.now().getYear();
    private List<ThongKeTheoNam> listTK = new ThongKeController().getStatisticFromYearToYear(currentYear - 5, currentYear);

    private DefaultTableModel modal;

    public ThongKeDoanhThuTheoNamPage() {
        initComponents();
        this.chart = new gui.barchart.Chart(); // Khởi tạo đối tượng Chart của bạn
        jPanel2.setLayout(new java.awt.BorderLayout()); // Đảm bảo jPanel2 có layout
        jPanel2.removeAll(); // Xóa các thành phần cũ nếu có
        jPanel2.add(this.chart, java.awt.BorderLayout.CENTER); // Thêm chart vào jPanel2
        jPanel2.revalidate(); // Revalidate lại panel để đảm bảo layout được cập nhật
        jPanel2.repaint(); //
        chartLayout();
        tableLayout();
        loadDataset();
    }

    private void chartLayout() {
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Lợi nhuận", new Color(139, 225, 196));

        chart.start();
    }

    private void loadChart() {
        for (ThongKeTheoNam e : listTK) {
            chart.addData(new ModelChart("Năm " + e.getNam(), new double[]{e.getDoanhThu(), e.getChiPhi(), e.getLoiNhuan()}));
        }
    }

    private void tableLayout() {
        String[] header = new String[]{"Năm", "Doanh thu", "Chi phí", "Lợi nhuận"};
        modal = new DefaultTableModel();
        modal.setColumnIdentifiers(header);
        table.setModel(modal);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        table.setDefaultRenderer(Object.class, centerRenderer);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);

        sortTable();
    }

    private void sortTable() {
        table.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(table, 0, TableSorter.STRING_COMPARATOR);
    }

    private void loadTable() {
        modal.setRowCount(0);
        for (ThongKeTheoNam e : listTK) {
            modal.addRow(new Object[]{
                e.getNam() + "", Formatter.FormatVND(e.getDoanhThu()), Formatter.FormatVND(e.getChiPhi()), Formatter.FormatVND(e.getLoiNhuan())
            });
        }
    }

    private void loadDataset() {
        chart.clear();
        loadChart();
        loadTable();
        chart.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblChart = new javax.swing.JLabel();
        txtFromYear = new javax.swing.JTextField();
        lblChart1 = new javax.swing.JLabel();
        txtToYear = new javax.swing.JTextField();
        btnStatistic = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(230, 245, 245));
        setMinimumSize(new java.awt.Dimension(1130, 800));
        setPreferredSize(new java.awt.Dimension(1130, 800));
        setLayout(new java.awt.BorderLayout(0, 6));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 6, 6));
        jPanel1.setLayout(new java.awt.BorderLayout(4, 4));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(456, 300));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"123", "Anh Tuấn", "123123", null, null, null},
                {"13124", "czczxc", "zxc", null, null, null},
                {"14123", "zxczc", "zxc", null, null, null},
                {"124123", "zxczx", "zxc", null, null, null}
            },
            new String [] {
                "Mã", "Họ tên", "Số điện thoại", "Giới tính", "Năm sinh", "Ngày vào làm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.setRowHeight(40);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        jPanel5.setBackground(new java.awt.Color(247, 247, 247));
        jPanel5.setPreferredSize(new java.awt.Dimension(1188, 30));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 0));

        lblChart.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblChart.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChart.setText("Từ năm");
        lblChart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblChart.setPreferredSize(new java.awt.Dimension(60, 30));
        jPanel5.add(lblChart);
        jPanel5.add(txtFromYear);

        lblChart1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblChart1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChart1.setText("Đến năm");
        lblChart1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblChart1.setPreferredSize(new java.awt.Dimension(60, 30));
        jPanel5.add(lblChart1);
        jPanel5.add(txtToYear);

        btnStatistic.setBackground(new java.awt.Color(51, 153, 255));
        btnStatistic.setForeground(new java.awt.Color(204, 255, 255));
        btnStatistic.setText("Thống kê");
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });
        jPanel5.add(btnStatistic);

        btnReload.setText("Làm mới");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });
        jPanel5.add(btnReload);

        btnExport.setBackground(new java.awt.Color(0, 153, 102));
        btnExport.setForeground(new java.awt.Color(204, 255, 204));
        btnExport.setText("Xuất excel");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        jPanel5.add(btnExport);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);
        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidFilterFields() {
        if (Validation.isEmpty(txtFromYear.getText().trim())) {
            MessageDialog.warring(this, "Không được để trống!");
            Validation.resetTextfield(txtFromYear);
            return false;
        }
        if (Validation.isEmpty(txtToYear.getText().trim())) {
            MessageDialog.warring(this, "Không được để trống!");
            Validation.resetTextfield(txtToYear);
            return false;
        }

        int fromYear = Integer.parseInt(txtFromYear.getText());
        int toYear = Integer.parseInt(txtToYear.getText());

        try {
            if (fromYear <= 1900 || fromYear > currentYear
                    && toYear <= 1900 || toYear > currentYear) {
                MessageDialog.warring(this, "Số năm phải từ 1900 đến " + currentYear);
                return false;
            }
            if (toYear < fromYear) {
                MessageDialog.warring(this, "Số năm kết thúc phải >= năm bắt đầu!");
                Validation.selectAllTextfield(txtToYear);
                return false;
            }
            if (toYear - fromYear >= 10) {
                MessageDialog.warring(this, "Hai năm không cách nhau quá 10 năm");
                Validation.selectAllTextfield(txtFromYear);
                return false;
            }
        } catch (NumberFormatException e) {
            MessageDialog.warring(this, "Số không hợp lệ!");
            Validation.resetTextfield(txtFromYear);
            return false;
        }

        return true;
    }

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        if (isValidFilterFields()) {
            int fromYear = Integer.parseInt(txtFromYear.getText());
            int toYear = Integer.parseInt(txtToYear.getText());

            listTK = new ThongKeController().getStatisticFromYearToYear(fromYear, toYear);
            loadDataset();
        }
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        JTableExporter.exportJTableToExcel(table);
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        txtFromYear.setText("");
        txtToYear.setText("");

        listTK = new ThongKeController().getStatisticFromYearToYear(currentYear - 5, currentYear);
        loadDataset();
    }//GEN-LAST:event_btnReloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblChart1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtFromYear;
    private javax.swing.JTextField txtToYear;
    // End of variables declaration//GEN-END:variables
}
