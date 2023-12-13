package halamanAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import sambungan.koneksi;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class pnlDaftarmenu extends javax.swing.JInternalFrame {

    koneksi koneksi = new koneksi();
    private DefaultTableModel model;

    private void autonumber(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "select * from menu order by id_menu desc";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String idmenu = r.getString("id_menu").substring(2);
                String BR = "" +(Integer.parseInt(idmenu)+1);
                String Nol = "";
                
                if (BR.length()==1) 
                    {Nol = "00";}
                else if(BR.length()==2)
                    {Nol = "0";}
                else if(BR.length()==3)
                    {Nol = "";}
                
                txtidmenu.setText("BR" + Nol + BR);  
            }else{
                txtidmenu.setText("BR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void clear(){
        txtnamamenu.setText("");
        txtharga.setText("");
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from menu";
            ResultSet r = s.executeQuery(sql);

                model.setRowCount(0);
                
            while (r.next()) {
                Object[] o = new Object[3];
                o [0] = r.getString("id_menu");
                o [1] = r.getString("nama_menu");
                o [2] = r.getString("harga_menu");
                
                model.addRow(o);
                jTable1.setModel(model);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan");
        }
    }
    
    public void cariData(){
//        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        model.addColumn("Id Menu");
        model.addColumn("Nama Menu");
        model.addColumn("Harga Menu");

        model.setRowCount(0); // Mengganti model.getDataVector().removeAllElements()
        String cari = txtcari.getText();
        try {
            String sql = "select * from menu "
                    + "where id_menu like '%" + cari + "%' or nama_menu like '%" + cari + "%' or harga_menu like '%" + cari + "%'";
            java.sql.Connection conn = (Connection) sambungan.koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[3];
                obj[0] = res.getString("id_menu");
                obj[1] = res.getString("nama_menu");
                obj[2] = res.getString("harga_menu");
                model.addRow(obj);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public pnlDaftarmenu() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI pnl = (BasicInternalFrameUI)this.getUI();
        pnl.setNorthPane(null);
        
        Font font = new Font("Arial", Font.PLAIN, 16);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke!");
        
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        
        jTable1.setModel(model);
        
        model.addColumn("Id Menu");
        model.addColumn("Nama Menu");
        model.addColumn("Harga Menu");
        
        jTable1.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
        int totalWidth = jTable1.getWidth();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.06)); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.45));
        jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
        }
    });
        
        loadData();
        autonumber();
        btn_hapus.setEnabled(false);
        btn_segar.setEnabled(true);
        txtidmenu.setEnabled(false);
        btn_simpan.setVisible(true);
        btn_update.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pembelian = new javax.swing.JPanel();
        panelcustom1 = new custom.panelcustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtidmenu = new javax.swing.JTextField();
        txtnamamenu = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        btn_segar = new javax.swing.JButton();
        panelcustom4 = new custom.panelcustom();
        btn_hapus = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        panelcustom2 = new custom.panelcustom();
        txtcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pembelian.setPreferredSize(new java.awt.Dimension(1540, 870));
        pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pembelianMouseClicked(evt);
            }
        });
        pembelian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("id_menu");
        panelcustom1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, 80, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("harga menu");
        panelcustom1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("nama menu");
        panelcustom1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, 100, -1));

        txtidmenu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtidmenu.setPreferredSize(new java.awt.Dimension(270, 30));
        panelcustom1.add(txtidmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 25, 270, 30));

        txtnamamenu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtnamamenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamamenuActionPerformed(evt);
            }
        });
        panelcustom1.add(txtnamamenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 73, 270, 30));

        txtharga.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });
        txtharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthargaKeyReleased(evt);
            }
        });
        panelcustom1.add(txtharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 270, 30));

        pembelian.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 160, 405, 177));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(37, 150, 190));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        pembelian.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 160, 990, 600));

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA MENU");

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

        pembelian.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        btn_segar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_segar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btn_segar.setText("REFRESH");
        btn_segar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_segar.setFocusable(false);
        btn_segar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_segarActionPerformed(evt);
            }
        });
        pembelian.add(btn_segar, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, 43));

        panelcustom4.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom4.setRoundBottomLeft(12);
        panelcustom4.setRoundTopLeft(12);
        panelcustom4.setRoundTopRight(12);
        panelcustom4.setRoundottomRight(12);
        panelcustom4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_hapus.setBackground(new java.awt.Color(217, 24, 40));
        btn_hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash 30px.png"))); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        panelcustom4.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 43));

        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        panelcustom4.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 130, 43));

        btn_simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        panelcustom4.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 130, 43));

        pembelian.add(panelcustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 355, 405, 60));

        panelcustom2.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom2.setRoundBottomLeft(5);
        panelcustom2.setRoundTopLeft(5);
        panelcustom2.setRoundTopRight(5);
        panelcustom2.setRoundottomRight(5);
        panelcustom2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcari.setBorder(null);
        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcariKeyTyped(evt);
            }
        });
        panelcustom2.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 200, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-25.png"))); // NOI18N
        panelcustom2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, 30, 30));

        pembelian.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 246, -1));

        getContentPane().add(pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cariData();
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        cariData();
    }//GEN-LAST:event_txtcariKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btn_simpan.setVisible(false);
        btn_update.setVisible(true);
        btn_hapus.setEnabled(true);
        btn_segar.setEnabled(true);
        txtidmenu.setEnabled(true);

        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        String nama = (String) model.getValueAt(i, 1);
        String harga = (String) model.getValueAt(i, 2);

        txtidmenu.setText(id);
        txtnamamenu.setText(nama);
        txtharga.setText(harga);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String id = txtidmenu.getText();
        String nama = txtnamamenu.getText();
        String harga = txtharga.getText();

        if (nama.equals("") && harga.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap isi semua form");
        }else if (harga.equals("")){
            JOptionPane.showMessageDialog(null, "Harap isi harga menu");
        }else {

        try {
            Connection c = koneksi.getKoneksi();
            String sql = "insert into menu values (?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, harga);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            btn_simpan.setVisible(true);
            btn_update.setVisible(false);
            autonumber();
            clear();
            loadData();
        }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        String nama = txtnamamenu.getText();
        String harga = txtharga.getText();

        try {
            Connection c = koneksi.getKoneksi();
            String sql = "update menu set nama_menu = ?, harga_menu = ? where id_menu = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, harga);
            p.setString(3, id);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
            this.btn_update.setVisible(false);
            clear();
        } catch (Exception e) {
            System.out.println("update error");
        }finally{
            btn_simpan.setVisible(true);
            btn_update.setVisible(false);
            btn_hapus.setEnabled(false);
            txtidmenu.setEnabled(false);
            loadData();
            autonumber();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }

        String id = (String) model.getValueAt(i, 0);

        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "delete from menu where id_menu = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan");
            }finally{
                btn_simpan.setVisible(true);
                btn_update.setVisible(false);
                btn_hapus.setEnabled(false);
                btn_segar.setEnabled(true);
                txtidmenu.setEnabled(false);
                loadData();
                autonumber();
                clear();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {

        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_segarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_segarActionPerformed
        clear();
        loadData();
        btn_simpan.setVisible(true);
        btn_update.setVisible(false);
        btn_hapus.setEnabled(false);
        btn_segar.setEnabled(true);
        txtidmenu.setEnabled(false);
        txtcari.setText("");
        autonumber();
    }//GEN-LAST:event_btn_segarActionPerformed

    private void pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pembelianMouseClicked
        jTable1.clearSelection();
    }//GEN-LAST:event_pembelianMouseClicked

    private void txthargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthargaKeyReleased
        String nohp = txtharga.getText();
        if (nohp.matches("[0-9]*")) {
            txtharga.setBackground(Color.white);
        } else {
            txtharga.setBackground(new Color(254, 161, 161));
        }
    }//GEN-LAST:event_txthargaKeyReleased

    private void txtnamamenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamamenuActionPerformed
        txtharga.requestFocus(true);
    }//GEN-LAST:event_txtnamamenuActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        String id = txtidmenu.getText();
        String nama = txtnamamenu.getText();
        String harga = txtharga.getText();

        if (nama.equals("") && harga.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap isi semua form");
        }else if (harga.equals("")){
            JOptionPane.showMessageDialog(null, "Harap isi harga menu");
        }else {

        try {
            Connection c = koneksi.getKoneksi();
            String sql = "insert into menu values (?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, harga);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            btn_simpan.setVisible(true);
            btn_update.setVisible(false);
            autonumber();
            clear();
            loadData();
        }
        }
    }//GEN-LAST:event_txthargaActionPerformed
    public void run() {
                new pnlDaftarmenu().setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_hapus;
    public javax.swing.JButton btn_segar;
    public javax.swing.JButton btn_simpan;
    public javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom panelcustom4;
    private javax.swing.JPanel pembelian;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    public javax.swing.JTextField txtidmenu;
    private javax.swing.JTextField txtnamamenu;
    // End of variables declaration//GEN-END:variables
}
