package halamanAdmin;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import sambungan.koneksi;

public class pnlStok extends javax.swing.JInternalFrame {
    koneksi koneksi = new koneksi();
    private DefaultTableModel model;
    private DefaultTableModel model2;
    /**
     * Creates new form pnlStok
     */
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from bahan_baku";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[5];
                o [0] = r.getString("id_bahanbaku");
                o [1] = r.getString("id_supplier");
                o [2] = r.getString("nama");
                o [3] = r.getString("harga");
                o [4] = r.getString("stock");
                
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan");
        }
    }
    
    public void loadriwayatstok(){
        model2.getDataVector().removeAllElements();
        model2.fireTableDataChanged();
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from riwayat_ambilstok";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[3];
                o [0] = r.getString("nama");
                o [1] = r.getString("jumlah");
                o [2] = r.getString("tanggal_ambil");
                
                model2.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public pnlStok() {
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
        
        model.addColumn("ID Bahan");
        model.addColumn("ID Supplier");
        model.addColumn("Nama");
        model.addColumn("Harga");
        model.addColumn("Stok");
        loadData();
        
        model2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        tbl_ambilstok.setModel(model2);
        
        model2.addColumn("nama");
        model2.addColumn("jumlah");
        model2.addColumn("tanggal");
        loadriwayatstok();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stok = new javax.swing.JPanel();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_ambilstok = new javax.swing.JButton();
        spin_ambilstok = new javax.swing.JSpinner();
        label_stok = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ambilstok = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_restore = new javax.swing.JButton();
        btnHapusStok = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stok.setPreferredSize(new java.awt.Dimension(1540, 870));
        stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stokMouseClicked(evt);
            }
        });
        stok.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PERSEDIAAN");

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

        stok.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        stok.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 880, 540));

        btn_ambilstok.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_ambilstok.setText("ambil stok");
        btn_ambilstok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ambilstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ambilstokActionPerformed(evt);
            }
        });
        stok.add(btn_ambilstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 740, 120, 40));

        spin_ambilstok.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        spin_ambilstok.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spin_ambilstok.setPreferredSize(new java.awt.Dimension(80, 40));
        stok.add(spin_ambilstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 740, 80, 40));

        label_stok.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_stok.setText("Stok Bahan Baku");
        stok.add(label_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        tbl_ambilstok.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_ambilstok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_ambilstok.setFocusable(false);
        tbl_ambilstok.setRowHeight(30);
        tbl_ambilstok.setSelectionBackground(new java.awt.Color(37, 150, 190));
        jScrollPane2.setViewportView(tbl_ambilstok);

        stok.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 180, 480, 530));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tabel Ambil Stok");
        stok.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 140, -1, -1));

        btn_restore.setBackground(new java.awt.Color(217, 24, 40));
        btn_restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ikon_sampah24px.png"))); // NOI18N
        btn_restore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restore.setPreferredSize(new java.awt.Dimension(33, 33));
        btn_restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restoreActionPerformed(evt);
            }
        });
        stok.add(btn_restore, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 180, 33, 33));

        btnHapusStok.setBackground(new java.awt.Color(217, 24, 40));
        btnHapusStok.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHapusStok.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ikon_sampah24px.png"))); // NOI18N
        btnHapusStok.setText("Hapus Stok");
        btnHapusStok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusStok.setPreferredSize(new java.awt.Dimension(33, 33));
        btnHapusStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusStokActionPerformed(evt);
            }
        });
        stok.add(btnHapusStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 740, 140, 33));

        getContentPane().add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String namabahan = (String) model.getValueAt(i, 2);
        String jumlahst = (String) model.getValueAt(i, 4);
        int stok = Integer.parseInt(jumlahst);
            if (stok == 0) {
                label_stok.setText("stok " + namabahan + " habis");
                JOptionPane.showMessageDialog(null, "stok " + namabahan + " habis");
                spin_ambilstok.setEnabled(false);
                btn_ambilstok.setEnabled(false);
            }
            else if (stok < 5){
                label_stok.setText("Ambil " + namabahan + " sebanyak?  stok hampir habis yaitu: " + jumlahst);
                spin_ambilstok.setEnabled(true);
                btn_ambilstok.setEnabled(true);
            }
            else {
                label_stok.setText("Ambil " + namabahan + " sebanyak?  stok saat ini: " + jumlahst);
                spin_ambilstok.setEnabled(true);
                btn_ambilstok.setEnabled(true);
            }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_ambilstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ambilstokActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Pilih stok terlebih dahulu!");
            return;
        }
        
        LocalDate now = LocalDate.now();
        String nowString = now.toString();
        
        String tglstok = (String) mainAdmin.txtDatetime.getText();
        String idbahan = (String) model.getValueAt(i, 0);
        String stok = (String) model.getValueAt(i, 4);
        String nama = (String) model.getValueAt(i, 2);
        int nilaiambil = (int) spin_ambilstok.getValue();
        
        int stokbr = Integer.parseInt(stok);
        int sisastok = stokbr - nilaiambil;
        
        if (nilaiambil > stokbr) {
        JOptionPane.showMessageDialog(null, "Jumlah stok yang akan diambil melebihi stok yang tersedia!");
        return;
        }else if(nilaiambil == 0){
            JOptionPane.showMessageDialog(null, "Masukkan jumlah dengan benar");
            return;
        }
        
        int option = JOptionPane.showOptionDialog(null, "Anda yakin ingin mengambil stok " +nama+" ?\nJumlah stok yang akan diambil: " + nilaiambil +"\n"+nama+" sekarang tersisa: "+ sisastok, "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Lanjut", "Batal"}, "Lanjut");
        
        if (option == JOptionPane.YES_OPTION) {
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "update bahan_baku set stock = stock - ? where id_bahanBaku = ?;";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, nilaiambil);
            p.setString(2, idbahan);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "stok " + nama + " telah diambil sebanyak: " + nilaiambil + "\nstok " + nama + " sekarang tersisa: " + sisastok);
            label_stok.setText("Ambil Stok");
            spin_ambilstok.setValue(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "select count(*) from riwayat_ambilstok where nama = ? and date(tanggal_ambil) = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, nowString);
            
            ResultSet r = p.executeQuery();
            r.next();
                int count = r.getInt(1);
        
            if (count > 0) {
                // Jika nama / tanggal sama, lakukan operasi update pada riwayat_ambilstok
                sql = "update riwayat_ambilstok set jumlah = jumlah + ? where nama = ? and date(tanggal_ambil) = ?";
                p = c.prepareStatement(sql);
                p.setInt(1, nilaiambil);
                p.setString(2, nama);
                p.setString(3, nowString);
                p.executeUpdate();
                p.close();
                System.out.println("Data riwayat ambil stok diupdate.");
                loadriwayatstok();

            } else {
                // Jika nama belum ada, lakukan operasi insert pada riwayat_ambilstok
                sql = "insert into riwayat_ambilstok values (?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, nama);
                p.setInt(2, nilaiambil);
                p.setString(3, tglstok);
                p.executeUpdate();
                p.close();
                System.out.println("riwayat ambil stok disimpan");
                loadriwayatstok();
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
    }
        finally{
            loadData();
            loadriwayatstok();
        }
    }
    }//GEN-LAST:event_btn_ambilstokActionPerformed

    private void stokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stokMouseClicked
        jTable1.clearSelection();
        tbl_ambilstok.clearSelection();
        label_stok.setText("Stok Bahan Baku");
        spin_ambilstok.setValue(0);
    }//GEN-LAST:event_stokMouseClicked

    private void btn_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restoreActionPerformed
    DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
int selectedRow = tbl_ambilstok.getSelectedRow();

String namaBahanBaku = tbl_ambilstok.getValueAt(selectedRow, 0).toString();
int jumlahAmbil = Integer.parseInt(tbl_ambilstok.getValueAt(selectedRow, 1).toString());
String tanggalAmbil = tbl_ambilstok.getValueAt(selectedRow, 2).toString();

String[] options = {"Hapus Semua", "Ambil Beberapa"};
int option = JOptionPane.showOptionDialog(null, "Pilih opsi:", "Konfirmasi",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

if (option == 0) {
    try {
        Connection c = koneksi.getKoneksi();

        // Perbarui stok di tabel "bahan_baku"
        String sqlUpdateStok = "UPDATE bahan_baku SET stock = stock + ? WHERE nama = ?";
        PreparedStatement pUpdateStok = c.prepareStatement(sqlUpdateStok);
        pUpdateStok.setInt(1, jumlahAmbil);
        pUpdateStok.setString(2, namaBahanBaku);
        pUpdateStok.executeUpdate();
        pUpdateStok.close();

        // Hapus baris dari tabel "riwayat_ambilstok"
        model.removeRow(selectedRow);

        // Hapus data dari tabel "riwayat_ambilstok"
        String sqlDeleteRiwayat = "DELETE FROM riwayat_ambilstok WHERE nama = ? AND tanggal_ambil = ?";
        PreparedStatement pDeleteRiwayat = c.prepareStatement(sqlDeleteRiwayat);
        pDeleteRiwayat.setString(1, namaBahanBaku);
        pDeleteRiwayat.setString(2, tanggalAmbil);
        pDeleteRiwayat.executeUpdate();
        pDeleteRiwayat.close();

        System.out.println("Stok dikembalikan dan riwayat dihapus.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
} else if (option == 1) {
    String jumlahAmbilStr = JOptionPane.showInputDialog(null, "Masukkan jumlah yang ingin diambil:", "Ambil Beberapa",
            JOptionPane.QUESTION_MESSAGE);
    if (jumlahAmbilStr != null) {
        int jumlahAmbilBaru = Integer.parseInt(jumlahAmbilStr);
        if (jumlahAmbilBaru >= jumlahAmbil) {
            // Menghapus semua data karena jumlah yang diambil sama atau lebih besar dari jumlah aslinya
            try {
                Connection c = koneksi.getKoneksi();

                // Perbarui stok di tabel "bahan_baku"
                String sqlUpdateStok = "UPDATE bahan_baku SET stock = stock + ? WHERE nama = ?";
                PreparedStatement pUpdateStok = c.prepareStatement(sqlUpdateStok);
                pUpdateStok.setInt(1, jumlahAmbil);
                pUpdateStok.setString(2, namaBahanBaku);
                pUpdateStok.executeUpdate();
                pUpdateStok.close();

                // Hapus baris dari tabel "riwayat_ambilstok"
                model.removeRow(selectedRow);

                // Hapus data dari tabel "riwayat_ambilstok"
                String sqlDeleteRiwayat = "DELETE FROM riwayat_ambilstok WHERE nama = ? AND tanggal_ambil = ?";
                PreparedStatement pDeleteRiwayat = c.prepareStatement(sqlDeleteRiwayat);
                pDeleteRiwayat.setString(1, namaBahanBaku);
                pDeleteRiwayat.setString(2, tanggalAmbil);
                pDeleteRiwayat.executeUpdate();
                pDeleteRiwayat.close();

                System.out.println("Stok dikembalikan dan riwayat dihapus.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Mengurangi jumlah yang diambil dan memperbarui jumlah di riwayat ambil stok
            try {
                Connection c = koneksi.getKoneksi();

                // Perbarui stok di tabel "bahan_baku"
                String sqlUpdateStok = "UPDATE bahan_baku SET stock = stock + ? WHERE nama = ?";
                PreparedStatement pUpdateStok = c.prepareStatement(sqlUpdateStok);
                pUpdateStok.setInt(1, jumlahAmbilBaru);
                pUpdateStok.setString(2, namaBahanBaku);
                pUpdateStok.executeUpdate();
                pUpdateStok.close();

                // Kurangi jumlah di riwayat ambil stok
                String sqlUpdateRiwayat = "UPDATE riwayat_ambilstok SET jumlah = jumlah- ? WHERE nama = ? AND tanggal_ambil = ?";
                PreparedStatement pUpdateRiwayat = c.prepareStatement(sqlUpdateRiwayat);
                pUpdateRiwayat.setInt(1, jumlahAmbilBaru);
                pUpdateRiwayat.setString(2, namaBahanBaku);
                pUpdateRiwayat.setString(3, tanggalAmbil);
                pUpdateRiwayat.executeUpdate();
                pUpdateRiwayat.close();

                System.out.println("Jumlah diubah dan stok dikembalikan.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    }
    // Refresh tampilan tabel dan riwayat stok
    loadData();
    loadriwayatstok();
    }//GEN-LAST:event_btn_restoreActionPerformed

    private void btnHapusStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusStokActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Tidak ada stok yang dipilih!");
            return;
        }

        String idBahan = (String) model.getValueAt(i, 0);
        
        int pernyataan = JOptionPane.showOptionDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Hapus", "Batal"}, "");
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "delete from bahan_baku where id_bahanBaku = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idBahan);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan");
            }finally {
                loadData();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {
    }
    }//GEN-LAST:event_btnHapusStokActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapusStok;
    private javax.swing.JButton btn_ambilstok;
    private javax.swing.JButton btn_restore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_stok;
    private custom.panelcustom panelcustom3;
    private javax.swing.JSpinner spin_ambilstok;
    private javax.swing.JPanel stok;
    private javax.swing.JTable tbl_ambilstok;
    // End of variables declaration//GEN-END:variables
}