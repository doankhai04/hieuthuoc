package gui.page.thongke;

import controller.ThongKeController;
import entities.ThongKe;
import gui.barchart.ModelChart;
import gui.barchart.Chart;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utils.Formatter;
import utils.JTableExporter;
import utils.TableSorter;

/**
 *
 * @author atuandev
 */
public class ThongKeDoanhThuTungNgayTrongThangPage extends javax.swing.JPanel {
    private Chart chart;
    private final int currentMonth = LocalDate.now().getMonthValue();
    private final int currentYear = LocalDate.now().getYear();
    private List<ThongKe> listTK = new ThongKeController().getStatisticDaysByMonthYear(currentMonth, currentYear);

    private DefaultTableModel modal;

    public ThongKeDoanhThuTungNgayTrongThangPage() {
        initComponents();
        this.chart = new gui.barchart.Chart(); // Khởi tạo đối tượng Chart của bạn
        jPanel2.setLayout(new java.awt.BorderLayout()); // Đảm bảo jPanel2 có layout
        jPanel2.removeAll(); // Xóa các thành phần cũ nếu có
        jPanel2.add(this.chart, java.awt.BorderLayout.CENTER); // Thêm chart vào jPanel2
        jPanel2.revalidate(); // Revalidate lại panel để đảm bảo layout được cập nhật
        jPanel2.repaint();  // Repaint the panel
        chartLayout();
        tableLayout();
        loadDataset();
    }

    private void chartLayout() {
        txtMonth.setModel(new SpinnerNumberModel(currentMonth - 1, 1, 12, 1)); // Hoặc (currentMonth, 1, 12, 1)
        txtYear.setModel(new SpinnerNumberModel(currentYear, 1900, currentYear, 1));

        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Lợi nhuận", new Color(139, 225, 196));

        chart.start();
    }

    private void loadChart() {
        double sum_doanhthu = 0;
        double sum_chiphi = 0;
        double sum_loinhuan = 0;

        for (int day = 0; day < listTK.size(); day++) {
            sum_doanhthu += listTK.get(day).getDoanhThu();
            sum_chiphi += listTK.get(day).getChiPhi();
            sum_loinhuan += listTK.get(day).getLoiNhuan();
            if ((day + 1) % 3 == 0 || day == listTK.size() - 1) {
                int startDay = day - 2;
                if (startDay < 0) {
                    startDay = 0;
                }
                int endDay = day;
                chart.addData(new ModelChart("Ngày " + (startDay + 1) + " - " + (endDay + 1), new double[]{sum_doanhthu, sum_chiphi, sum_loinhuan}));
                sum_doanhthu = 0;
                sum_chiphi = 0;
                sum_loinhuan = 0;
            }
        }
    }

    private void tableLayout() {
        String[] header = new String[]{"Thời gian", "Doanh thu", "Chi phí", "Lợi nhuận"};
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
        for (ThongKe e : listTK) {
            modal.addRow(new Object[]{
                Formatter.FormatDate(e.getThoiGian()), Formatter.FormatVND(e.getDoanhThu()), Formatter.FormatVND(e.getChiPhi()), Formatter.FormatVND(e.getLoiNhuan())
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
        txtMonth = new javax.swing.JSpinner();
        lblChart1 = new javax.swing.JLabel();
        txtYear = new javax.swing.JSpinner();
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
        lblChart.setText("Tháng");
        lblChart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblChart.setPreferredSize(new java.awt.Dimension(60, 30));
        jPanel5.add(lblChart);
        jPanel5.add(txtMonth);

        lblChart1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        lblChart1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblChart1.setText("Năm");
        lblChart1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblChart1.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel5.add(lblChart1);
        jPanel5.add(txtYear);

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
        return true;
    }

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        if (isValidFilterFields()) {
            int mounth = (int)txtMonth.getValue();
            int year = (int)txtYear.getValue();

            listTK = new ThongKeController().getStatisticDaysByMonthYear(mounth, year);
            loadDataset();
        }
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        JTableExporter.exportJTableToExcel(table);
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        txtMonth.setValue(currentMonth - 1);
        txtMonth.setValue(currentYear);

        listTK = new ThongKeController().getStatisticDaysByMonthYear(currentMonth, currentYear);
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
    private javax.swing.JSpinner txtMonth;
    private javax.swing.JSpinner txtYear;
    // End of variables declaration//GEN-END:variables
}
