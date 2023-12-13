package login;
import halamanAdmin.pnlPengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import sambungan.koneksi;

public class riwayatLogin extends javax.swing.JInternalFrame {
    koneksi koneksi = new koneksi();
    private DefaultTableModel model;
    PreparedStatement ps;
    ResultSet rs;
    
    private void loadData(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from riwayat_login";
            ResultSet r = s.executeQuery(sql);
            
//            model = new DefaultTableModel();
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            
            jTable1.setModel(model);

            model.addColumn("Username");
            model.addColumn("Login Pada");
            model.addColumn("Logout Pada");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while (r.next()) {
                Object[] o = new Object[3];
                o [0] = r.getString("username");
                o [1] = r.getString("waktulogin");
                o [2] = r.getString("waktu_logout");
                
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void loadDataNow(){
        btn_caritgl.setVisible(false);
        txtDate.setVisible(false);
        lbl_lognow.setText("Riwayat Login Hari Ini");
        
        try {
            LocalDate now = LocalDate.now();
            String nowString = now.toString();
            
            String sql = "select * from riwayat_login where date(waktulogin) = ?";
            ps = koneksi.getKoneksi().prepareStatement(sql);
            ps.setString(1, nowString);
            rs = ps.executeQuery();
            
//            model = new DefaultTableModel();
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
            jTable1.setModel(model);

            model.addColumn("Username");
            model.addColumn("Login Pada");
            model.addColumn("Logout Pada");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while (rs.next()) {
                Object[] o = new Object[3];
                o [0] = rs.getString("username");
                o [1] = rs.getString("waktulogin");
                o [2] = rs.getString("waktu_logout");
                
                model.addRow(o);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public riwayatLogin() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI pnl = (BasicInternalFrameUI)this.getUI();
        pnl.setNorthPane(null);
//        model = new DefaultTableModel();
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        
        jTable1.setModel(model);
        
        model.addColumn("Username");
        model.addColumn("Login Pada");
        model.addColumn("Logout Pada");
        loadDataNow();
        
        lbl_lognow.setText("Riwayat Login Hari Ini");
        btn_caritgl.setVisible(false);
        txtDate.setVisible(false);
        btn_alllogin.setVisible(true);
        btn_riwayatNow.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        bgRiwayatLogin = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btn_caritgl = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        lbl_lognow = new javax.swing.JLabel();
        btn_alllogin = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        btn_riwayatNow = new javax.swing.JButton();

        dateChooser1.setTextRefernce(txtDate);

        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgRiwayatLogin.setPreferredSize(new java.awt.Dimension(1540, 870));
        bgRiwayatLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgRiwayatLoginMouseClicked(evt);
            }
        });
        bgRiwayatLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(37, 150, 190));
        jScrollPane1.setViewportView(jTable1);

        bgRiwayatLogin.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 910, 490));

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("RIWAYAT LOGIN");

        javax.swing.GroupLayout panelcustom3Layout = new javax.swing.GroupLayout(panelcustom3);
        panelcustom3.setLayout(panelcustom3Layout);
        panelcustom3Layout.setHorizontalGroup(
            panelcustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelcustom3Layout.setVerticalGroup(
            panelcustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        bgRiwayatLogin.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        txtDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDate.setFocusable(false);
        bgRiwayatLogin.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, 109, 29));

        btn_caritgl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_date 20px.png"))); // NOI18N
        btn_caritgl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_caritgl.setPreferredSize(new java.awt.Dimension(29, 29));
        btn_caritgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caritglActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_caritgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 120, -1, -1));

        btn_kembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back 30px.png"))); // NOI18N
        btn_kembali.setText("Kembali");
        btn_kembali.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 760, -1, -1));

        btn_delete.setBackground(new java.awt.Color(141, 16, 26));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash 30px.png"))); // NOI18N
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_delete.setFocusable(false);
        btn_delete.setPreferredSize(new java.awt.Dimension(35, 35));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 610, -1, -1));

        lbl_lognow.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbl_lognow.setText("jLabel1");
        bgRiwayatLogin.add(lbl_lognow, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        btn_alllogin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_alllogin.setText("semua riwayat");
        btn_alllogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_alllogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_allloginActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_alllogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 660, -1, -1));

        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btn_refresh.setText("REFRESH");
        btn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_refresh.setPreferredSize(new java.awt.Dimension(43, 43));
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 160, 120, 43));

        btn_riwayatNow.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_riwayatNow.setText("riwayat sekarang");
        btn_riwayatNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_riwayatNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_riwayatNowActionPerformed(evt);
            }
        });
        bgRiwayatLogin.add(btn_riwayatNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 660, -1, -1));

        getContentPane().add(bgRiwayatLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        this.setVisible(false);
        pnlPengguna us = new pnlPengguna();
        halamanAdmin.mainAdmin.jDesktopPane1.add(us).setVisible(true);
        halamanAdmin.mainAdmin.judulMenu.setText("/ Pengguna");
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        int pernyataan = JOptionPane.showConfirmDialog(null, "Data riwayat login akan dihapus semua,\ntetap lanjutkan hapus?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
               Connection c = koneksi.getKoneksi();
               String sql = "delete from riwayat_login";
               PreparedStatement p = c.prepareStatement(sql);
               p.executeUpdate();
               p.close();
               JOptionPane.showMessageDialog(null, "Data Riwayat Login Terhapus");
           } catch (Exception e) {
               System.out.println(e);
           }
        }
        if (pernyataan == JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_allloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allloginActionPerformed
        loadData();
        btn_riwayatNow.setVisible(true);
        btn_alllogin.setVisible(false);
        lbl_lognow.setText("Semua Riwayat Login");
        btn_caritgl.setVisible(true);
        txtDate.setVisible(true);
        txtDate.requestFocus(true);
        txtDate.setText("lihat tanggal");
        
    }//GEN-LAST:event_btn_allloginActionPerformed

    private void btn_caritglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caritglActionPerformed
        model.setRowCount(0);
        try {
            String tanggal = txtDate.getText();
            String sql = "select * from riwayat_login where date(waktulogin) = ?";
            ps = koneksi.getKoneksi().prepareStatement(sql);
            ps.setString(1, tanggal);
            rs = ps.executeQuery();

            while (rs.next()){
               model.addRow(new Object[]  {
                  rs.getString("username"),
                  rs.getString("waktulogin"),
                  rs.getString("waktu_logout"),
                });
            }
            
            rs.close();
            ps.close();
            lbl_lognow.setText("Semua riwayat tanggal: " + tanggal);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            btn_riwayatNow.setVisible(false);
            btn_alllogin.setVisible(true);
        }
    }//GEN-LAST:event_btn_caritglActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        loadDataNow();
        btn_riwayatNow.setVisible(false);
        btn_alllogin.setVisible(true);
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_riwayatNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_riwayatNowActionPerformed
        loadDataNow();
        btn_riwayatNow.setVisible(false);
        btn_alllogin.setVisible(true);
    }//GEN-LAST:event_btn_riwayatNowActionPerformed

    private void bgRiwayatLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgRiwayatLoginMouseClicked
        jTable1.clearSelection();
    }//GEN-LAST:event_bgRiwayatLoginMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgRiwayatLogin;
    private javax.swing.JButton btn_alllogin;
    private javax.swing.JButton btn_caritgl;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_riwayatNow;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_lognow;
    private custom.panelcustom panelcustom3;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables
}