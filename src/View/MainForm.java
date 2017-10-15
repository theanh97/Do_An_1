/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Algorithm.DataOfTwoPointForOneStep;
import Algorithm.Dijkstra;
import Draw.DrawDoThi;
import Model.MaTran;
import Model.MaTran.Mode;
import Model.MPoint;
import Presenter.MainFormPresenter;
import Utils.Const;
import Utils.MaTranUtils;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javafx.util.Pair;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 *
 * @author DELL
 */
public class MainForm extends javax.swing.JFrame
        implements IMainForm,
        ActionListener {

    MainFormPresenter mPresenter;

    private MaTran mMaTran;
    private DefaultTableModel mTableModelMaTran;
    private DefaultTableModel mTableModelThuatToan;
    private ArrayList<Object[][]> mListDoThiCoSan;
    private ArrayList<Point[]> mListDoThiCoSanCoordinate;
    private ArrayList<DataOfTwoPointForOneStep> mListDataOfTwoPointForOneStep;
    private DrawDoThi mDrawDoThi;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        mPresenter = new MainFormPresenter(this);
        setEventListener();
        initData();
        
        mDrawDoThi = new DrawDoThi(mMaTran.getListPoints(),mMaTran.isMode().equals(Mode.CoHuong));
        mDrawDoThi.setBounds(334, 27, 884, 384);
        add(mDrawDoThi);
        
        prepareUI();


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        btnGroupPhuongThucNhapDoThi = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        rdbCoHuong = new javax.swing.JRadioButton();
        rdbVoHuong = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        rdbTuVe = new javax.swing.JRadioButton();
        rdbDoThiCoSan = new javax.swing.JRadioButton();
        cmbDoThiCoSan = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        cmbDiemXuatPhat = new javax.swing.JComboBox<>();
        cmbDiemCuoi = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        btnChayMotLan = new javax.swing.JButton();
        btnChayTungBuoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuatToan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblBieuDienThuatToan = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMaTran = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TÌM ĐƯỜNG ĐI NGẮN NHẤT ");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kiểu đồ thị", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setToolTipText("Đồ thị");

        btnGroup.add(rdbCoHuong);
        rdbCoHuong.setText("Có hướng ");
        rdbCoHuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbCoHuongActionPerformed(evt);
            }
        });

        btnGroup.add(rdbVoHuong);
        rdbVoHuong.setText("Vô hướng");
        rdbVoHuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbVoHuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(rdbCoHuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdbVoHuong)
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbCoHuong)
                    .addComponent(rdbVoHuong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn đồ thị\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btnGroupPhuongThucNhapDoThi.add(rdbTuVe);
        rdbTuVe.setText("Tự Vẽ");
        rdbTuVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbTuVeActionPerformed(evt);
            }
        });

        btnGroupPhuongThucNhapDoThi.add(rdbDoThiCoSan);
        rdbDoThiCoSan.setText("Đồ thị có sẵn");

        cmbDoThiCoSan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(rdbTuVe)
                .addGap(18, 18, 18)
                .addComponent(rdbDoThiCoSan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDoThiCoSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbTuVe)
                    .addComponent(rdbDoThiCoSan)
                    .addComponent(cmbDoThiCoSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đường Đi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        cmbDiemXuatPhat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDiemXuatPhat.setToolTipText("Điểm xuất phát");

        cmbDiemCuoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDiemXuatPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbDiemCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDiemXuatPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDiemCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cách chạy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btnChayMotLan.setText("Chạy 1 lần");

        btnChayTungBuoc.setText("Chạy từng bước");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChayMotLan)
                .addGap(39, 39, 39)
                .addComponent(btnChayTungBuoc)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChayMotLan, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnChayTungBuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tblThuatToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblThuatToan);

        lblBieuDienThuatToan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBieuDienThuatToan.setText("Hiển thị Đường Đi và Giá Trị thuật toán");
        jScrollPane2.setViewportView(lblBieuDienThuatToan);

        tblMaTran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblMaTran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rdbCoHuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbCoHuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbCoHuongActionPerformed

    private void rdbVoHuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbVoHuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbVoHuongActionPerformed

    private void rdbTuVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbTuVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbTuVeActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChayMotLan;
    private javax.swing.JButton btnChayTungBuoc;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.ButtonGroup btnGroupPhuongThucNhapDoThi;
    private javax.swing.JComboBox<String> cmbDiemCuoi;
    private javax.swing.JComboBox<String> cmbDiemXuatPhat;
    private javax.swing.JComboBox<String> cmbDoThiCoSan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBieuDienThuatToan;
    private javax.swing.JRadioButton rdbCoHuong;
    private javax.swing.JRadioButton rdbDoThiCoSan;
    private javax.swing.JRadioButton rdbTuVe;
    private javax.swing.JRadioButton rdbVoHuong;
    private javax.swing.JTable tblMaTran;
    private javax.swing.JTable tblThuatToan;
    // End of variables declaration//GEN-END:variables

    @Override
    public void prepareUI() {
        // combobox Do thi co san 
        cmbDoThiCoSan.removeAllItems();
        cmbDoThiCoSan.addItem("Đồ thị 1");
        cmbDoThiCoSan.addItem("Đồ thị 2");

        // combobox MPoint ( Start - End ) 
        cmbDiemXuatPhat.removeAllItems();
        cmbDiemCuoi.removeAllItems();
        for (int i = 0; i < mMaTran.getListPoints().size(); i++) {
            int point = mMaTran.getListPoints().get(i).getIndicator();
            cmbDiemXuatPhat.addItem("" + point);
            cmbDiemCuoi.addItem("" + point);
        }

        // Co Huong && Chon Do thi co san 1 
        rdbCoHuong.setSelected(true);
        rdbDoThiCoSan.setSelected(true);

        // load MaTran vao table
        mTableModelMaTran = new DefaultTableModel(mMaTran.getListVariable(), mMaTran.getColumnsName());
        tblMaTran.setModel(mTableModelMaTran);

        // Table Thuat Toan
        mTableModelThuatToan = new DefaultTableModel(new Object[][]{}, mMaTran.getColumnsName());
        tblThuatToan.setModel(mTableModelThuatToan);
    }

    @Override
    public void initData() {
        // list Do Thi Co San
        mListDoThiCoSan = new ArrayList<Object[][]>();
        mListDoThiCoSan.add(Const.DO_THI_1);
        mListDoThiCoSan.add(Const.DO_THI_2);

        // list toạ độ Point của Đồ thị có sẵn 
        mListDoThiCoSanCoordinate = new ArrayList<Point[]>(); 
        mListDoThiCoSanCoordinate.add(Const.DO_THI_1_COORDINATE);
        mListDoThiCoSanCoordinate.add(Const.DO_THI_2_COORDINATE);
        
        // MaTran 
        mMaTran = new MaTran(
                Mode.CoHuong, 
                mListDoThiCoSan.get(0),
                mListDoThiCoSanCoordinate.get(0));

    }

    @Override
    public void setEventListener() {
        // Radion button 
        rdbCoHuong.setActionCommand("CoHuong");
        rdbCoHuong.addActionListener(this);

        rdbVoHuong.setActionCommand("VoHuong");
        rdbVoHuong.addActionListener(this);

        rdbTuVe.setActionCommand("TuVe");
        rdbTuVe.addActionListener(this);

        rdbDoThiCoSan.setActionCommand("DoThiCoSan");
        rdbDoThiCoSan.addActionListener(this);

        // Combobox 
        cmbDoThiCoSan.setActionCommand("SelectDoThi");
        cmbDoThiCoSan.addActionListener(this);

        // Button 
        btnChayMotLan.setActionCommand("ChayMotLan");
        btnChayMotLan.addActionListener(this);

        // Button 
        btnChayTungBuoc.setActionCommand("ChayTungBuoc");
        btnChayTungBuoc.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CoHuong":
                mPresenter.onSelectedDoThiCoHuong();
                System.out.println("CoHuong");
                break;
            case "VoHuong":
                mPresenter.onSelectedDoThiVoHuong();
                System.out.println("VoHuong");
                break;
            case "TuVe":
                mPresenter.onSelectedDoThiTuVe();
                System.out.println("TuVe");
                break;
            case "DoThiCoSan":
                mPresenter.onSelectedDoThiCoSan();
                System.out.println("DoThiCoSan");
                break;
            case "SelectDoThi":
                mPresenter.onSelectedDoThiCoSanPosition(cmbDoThiCoSan.getSelectedIndex());
                System.err.println("Index : " + cmbDoThiCoSan.getSelectedIndex());
                break;
            case "ChayMotLan":
                mPresenter.onSelectedChayMotLan();
                System.err.println("Chạy 1 lần");
                break;
            case "ChayTungBuoc":
                mPresenter.onSelectedChayTungBuoc();
                System.err.println("Chạy Từng bước");
                break;
            default:
                break;
        }
    }

    @Override
    public void updateDoThiCoHuong() {
        mMaTran.setMode(Mode.CoHuong);
        mTableModelMaTran = new DefaultTableModel(mMaTran.getListVariable(), mMaTran.getColumnsName());
        tblMaTran.setModel(mTableModelMaTran);
    }

    @Override
    public void updateDoThiVoHuong() {
        // mode 
        mMaTran.setMode(Mode.VoHuong);
        // Table Data 
        mTableModelMaTran = new DefaultTableModel(mMaTran.getListVariable(), mMaTran.getColumnsName());
        tblMaTran.setModel(mTableModelMaTran);
    }

    @Override
    public void updateDoThiTuVe() {
        // MaTran = null 
        // disable combobox DoThiCoSan
        cmbDoThiCoSan.setSelectedIndex(0);
        cmbDoThiCoSan.setEnabled(false);
        // Table data = null 
        mTableModelMaTran = new DefaultTableModel(new Object[][]{}, new Object[]{});
        tblMaTran.setModel(mTableModelMaTran);
    }

    @Override
    public void updateDoThiCoSan(Mode mode) {
        // MaTran == null 
        mMaTran = new MaTran(mode, mListDoThiCoSan.get(0),mListDoThiCoSanCoordinate.get(0));

        // Table Ma Tran
        mTableModelMaTran = new DefaultTableModel(mMaTran.getListVariable(), mMaTran.getColumnsName());
        tblMaTran.setModel(mTableModelMaTran);

        // Table Thuat Toan
        mTableModelThuatToan = new DefaultTableModel(new Object[][]{}, mMaTran.getColumnsName());
        tblThuatToan.setModel(mTableModelThuatToan);

        // udpate MPoint of combobox ( Start - End ) 
        cmbDiemXuatPhat.removeAllItems();
        cmbDiemCuoi.removeAllItems();
        for (int i = 0; i < mMaTran.getListPoints().size(); i++) {
            int point = mMaTran.getListPoints().get(i).getIndicator();
            cmbDiemXuatPhat.addItem("" + point);
            cmbDiemCuoi.addItem("" + point);
        }

        // disable combobox DoThiCoSan
        cmbDoThiCoSan.setEnabled(true);
    }

    @Override
    public void updateDoThiCoSanPosition(Mode mode, int position) {
        // MaTran == null 
        mMaTran = new MaTran(mode, mListDoThiCoSan.get(position),mListDoThiCoSanCoordinate.get(position));
        
        // vẽ lại đồ thị 
        mDrawDoThi.initShapeData(mMaTran.getListPoints(), mMaTran.isMode().equals(Mode.CoHuong));
        mDrawDoThi.repaint();

        // Table Ma Tran
        mTableModelMaTran = new DefaultTableModel(mMaTran.getListVariable(), mMaTran.getColumnsName());
        tblMaTran.setModel(mTableModelMaTran);

        // Table Thuat Toan
        mTableModelThuatToan = new DefaultTableModel(new Object[][]{}, mMaTran.getColumnsName());
        tblThuatToan.setModel(mTableModelThuatToan);

        // udpate MPoint of combobox ( Start - End ) 
        cmbDiemXuatPhat.removeAllItems();
        cmbDiemCuoi.removeAllItems();
        for (int i = 0; i < mMaTran.getListPoints().size(); i++) {
            int point = mMaTran.getListPoints().get(i).getIndicator();
            cmbDiemXuatPhat.addItem("" + point);
            cmbDiemCuoi.addItem("" + point);
        }
    }

    @Override
    public boolean checkValidBeforeExecute() {
        if (mMaTran.getListPoints() == null
                || mMaTran.getListPoints().size() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đồ thị hợp lệ !!!");
            return false;
        }

        if (cmbDiemXuatPhat.getSelectedIndex() == cmbDiemCuoi.getSelectedIndex()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn điểm xuất phát và điểm kết thúc khác nhau !!!");
            return false;
        }
        return true;
    }

    @Override
    public void executeOneTime() {
        System.out.println("Execute One Time ");
        int soDinh = mMaTran.getListPoints().size();
        int sPoint = Integer.parseInt(cmbDiemXuatPhat.getSelectedItem().toString());
        int fPoint = Integer.parseInt(cmbDiemCuoi.getSelectedItem().toString());
        // lấy list DataOfTwoPointForOneStep 
        mListDataOfTwoPointForOneStep = Dijkstra.dijkstra(
                MaTranUtils.convertListObjectToListInt(mMaTran.getListVariable()),
                soDinh,
                sPoint,
                fPoint);

        showDataWithStepIndicator(sPoint, fPoint,
                mListDataOfTwoPointForOneStep.size(), mListDataOfTwoPointForOneStep.size(), soDinh);
    }

    @Override
    public void showDataWithStepIndicator(int sPoint, int fPoint,
            int stepIndicator, int maxStep, int soDinh) {
        if (stepIndicator > maxStep || stepIndicator < 1) {
            return;
        }

        // Tạo Data cho vào table Thuật Toán 
        Object[][] listDataForTable = new Object[stepIndicator][soDinh];

        int currentLength = 0;
        int currentFinishPoint = -1;

        for (int step = 0; step < stepIndicator; step++) {
            DataOfTwoPointForOneStep item = mListDataOfTwoPointForOneStep.get(step);
            int finishPoint = item.getFinishPoint();
            int startPoint = item.getStartPoint();
            int currentValue = item.getCurrentValue();
            int beforeValue = item.getBeforeValue();
            ArrayList<Integer> listPointMarked = item.getListPointMarked();

            currentLength = item.getCurrentValue();
            currentFinishPoint = item.getFinishPoint();

            // đánh dấu những điểm đã đi qua 
            for (int i = 0; i < listPointMarked.size(); i++) {
                listDataForTable[step][listPointMarked.get(i) - 1] = "---";
            }
//            System.out.println("\n ---------- " + startPoint + "  ->  " + finishPoint + " -------------\n");
//
            for (Entry<Integer, Integer> item2 : item.getListValue().entrySet()) {
                int pointConnected = item2.getKey();
                int valueOfPointConnected = item2.getValue();
//                System.out.println("Điểm kết nối thử : " + pointConnected + " : " + valueOfPointConnected);

                // step > 0 => bước > 1 
                if (step != 0) {

                    // những điểm không thể kết nối được 
                    if (valueOfPointConnected <= beforeValue) {
//                        System.out.println("MPoint connected : " + (pointConnected - 1));
                        listDataForTable[step][pointConnected - 1] = "[ @ ," + startPoint + " ]";

                    }
                    // những điểm có thể kết nối nhưng không phải là điểm được chọn 
                    if (valueOfPointConnected > beforeValue
                            && pointConnected != finishPoint) {
                        listDataForTable[step][pointConnected - 1] = "[ " + valueOfPointConnected
                                + " , " + startPoint + " ]";
                    }

                    // Điểm được chọn  
                    if (valueOfPointConnected > beforeValue
                            && pointConnected == finishPoint) {
                        listDataForTable[step][pointConnected - 1] = " *** [ " + valueOfPointConnected
                                + " , " + startPoint + " ]";
                    }
                } // step == 0 => bước 1 => kết nối với chính điểm đầu tiên  
                else {
                    if (pointConnected != startPoint) {
//                        System.out.println("MPoint Connected : " + (pointConnected - 1));
                        listDataForTable[step][pointConnected - 1] = "[ @ ," + startPoint + " ]";
                    } else {
                        listDataForTable[step][pointConnected - 1] = " *** [ " + valueOfPointConnected
                                + " , " + startPoint + " ]";
                    }
                }
            }
        }

        // tạo TableModel 
        mTableModelThuatToan = new DefaultTableModel(listDataForTable, mMaTran.getColumnsName());
        tblThuatToan.setModel(mTableModelThuatToan);

        // hiển thị đường đi 
        // K <=> StartPoint ---- V <=> FinishPoint
        HashMap<Integer, Integer> listRoad = new HashMap<Integer, Integer>();

        int finishBefore = sPoint;
        for (int step = 0; step < stepIndicator; step++) {
            DataOfTwoPointForOneStep item = mListDataOfTwoPointForOneStep.get(step);
            int start = item.getStartPoint();
            int finish = item.getFinishPoint();
            int curValue = item.getCurrentValue();
            // gặp quay lui rẽ nhánh 
            if (finishBefore != start
                    && listRoad.size() > 0) {

//                // duyệt các điểm đã đi từ cuối lên đầu 
//                ArrayList<Integer> listKeysTemp = new ArrayList<Integer>(); 
//                for(Integer it : listRoad.keySet()){
//                    listKeysTemp.add(it); 
//                }
//                Collections.reverse(listKeysTemp); 
//               
//                
//                // xoá đường đi sai 
//                for (Integer it : listKeysTemp) {
//                    int s = it ;
//                    int f = listRoad.get(s);
//                    if (s != start || f != finish) {
//                        listRoad.remove(s);
//                    } else {
//                        break;
//                    }
//                }
            }
            listRoad.put(start, finish);
            finishBefore = finish;
        }

        StringBuilder result = new StringBuilder();
        result.append("Đường đi từ " + sPoint + " -> " + currentFinishPoint + " : " + sPoint + " -> ");
        for (Entry<Integer, Integer> temp : listRoad.entrySet()) {
            result.append(temp.getValue() + " -> ");
        }

        result.replace(result.length() - 3, result.length(), "");

        // kiểm tra có phải là chạy 1 lần || đi đến bước cuối cùng hay ko 
        if (stepIndicator == maxStep) {
            // không có đường 
            if (currentFinishPoint != fPoint) {
                lblBieuDienThuatToan.setText("Không có đường đi từ điểm " + sPoint + " -> " + fPoint);
                return;
            }
        }

        // kiểm tra xem có đường đi hay ko 
        result.append("\t\t\t\nTổng giá trị đường đi : " + currentLength);
        lblBieuDienThuatToan.setText(result.toString());
    }

    @Override
    public void executePerStep(int stepIndicator) {
        int soDinh = mMaTran.getListPoints().size();
        int sPoint = Integer.parseInt(cmbDiemXuatPhat.getSelectedItem().toString());
        int fPoint = Integer.parseInt(cmbDiemCuoi.getSelectedItem().toString());
        // lấy list DataOfTwoPointForOneStep 
        if (stepIndicator == 1) {
            mListDataOfTwoPointForOneStep = Dijkstra.dijkstra(
                    MaTranUtils.convertListObjectToListInt(mMaTran.getListVariable()),
                    soDinh,
                    sPoint,
                    fPoint);
        }
        showDataWithStepIndicator(sPoint, fPoint, stepIndicator, mListDataOfTwoPointForOneStep.size(), soDinh);
    }
}
