package halamanAdmin;
import static halamanAdmin.mainAdmin.judulMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
import org.codehaus.groovy.ast.stmt.TryCatchStatement;

public class dataSupplier extends javax.swing.JInternalFrame {
    koneksi koneksi = new koneksi();
    private DefaultTableModel model;
    
    /**
     * Creates new form dataSupplier
     */
    private void autonumber(){
       try {
        Connection c = sambungan.koneksi.getKoneksi();
        Statement s = c.createStatement();
        String sql = "select * from supplier order by id_supplier desc";
        ResultSet r = s.executeQuery(sql);
        if (r.next()) {
            String idlvl = r.getString("id_supplier");
            String lv = "" + (Integer.parseInt(idlvl) + 1);

            txt_idsupp.setText(lv);
        } else {
            txt_idsupp.setText("1");
        }
        r.close();
        s.close();
    } catch (Exception e) {
        System.out.println("autonumber error");
    }
}
    
    public void clear(){
        txt_namasupp.setText("");
        txt_alamatsupp.setText("");
        txt_nohpsupp.setText("");
        txt_cari.setText("");
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from supplier";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[4];
                o [0] = r.getString("id_supplier");
                o [1] = r.getString("nama");
                o [2] = r.getString("alamat");
                o [3] = r.getString("telp");
                
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
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
        model.addColumn("Id Supplier");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No.Hp");

        model.setRowCount(0); // Mengganti model.getDataVector().removeAllElements()
        String cari = txt_cari.getText();
        try {
            String sql = "select * from supplier "
                    + "where id_supplier like '%" + cari + "%' or nama like '%" + cari + "%' or alamat like '%" + cari + "%' or telp like '%" + cari + "%'";
            java.sql.Connection conn = (Connection) sambungan.koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[4];
                obj[0] = res.getString("id_supplier");
                obj[1] = res.getString("nama");
                obj[2] = res.getString("alamat");
                obj[3] = res.getString("telp");
                model.addRow(obj);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    public dataSupplier() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI pnl = (BasicInternalFrameUI)this.getUI();
        pnl.setNorthPane(null);
        
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Siap!");
        
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        
        jTable1.setModel(model);
        
        model.addColumn("Id Supplier");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No.Hp");
        
        jTable1.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
        int totalWidth = jTable1.getWidth();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15));
        jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.35)); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15)); 
    }
});
        
        loadData();
        autonumber();
        btndelete.setEnabled(false);
        btnbatal.setEnabled(true);
        txt_idsupp.setEnabled(false);
        btn_pilih_pembelian.setVisible(false);
        btn_batalpilih.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlsupplier = new javax.swing.JPanel();
        pnl_biodata = new custom.panelcustom();
        txt_idsupp = new javax.swing.JTextField();
        txt_alamatsupp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_namasupp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nohpsupp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        panelcustom2 = new custom.panelcustom();
        btnsimpan = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_pilih_pembelian = new javax.swing.JButton();
        btn_batalpilih = new javax.swing.JToggleButton();
        btnbatal = new javax.swing.JButton();
        panelcustom1 = new custom.panelcustom();
        txt_cari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlsupplier.setPreferredSize(new java.awt.Dimension(1540, 870));
        pnlsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlsupplierMouseClicked(evt);
            }
        });
        pnlsupplier.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_biodata.setBackground(new java.awt.Color(255, 255, 255));
        pnl_biodata.setRoundBottomLeft(12);
        pnl_biodata.setRoundTopLeft(12);
        pnl_biodata.setRoundTopRight(12);
        pnl_biodata.setRoundottomRight(12);

        txt_idsupp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_alamatsupp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_alamatsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatsuppActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Nama");

        txt_namasupp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_namasupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namasuppActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("No_Hp");

        txt_nohpsupp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_nohpsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpsuppActionPerformed(evt);
            }
        });
        txt_nohpsupp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nohpsuppKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("ID Suplllier");

        javax.swing.GroupLayout pnl_biodataLayout = new javax.swing.GroupLayout(pnl_biodata);
        pnl_biodata.setLayout(pnl_biodataLayout);
        pnl_biodataLayout.setHorizontalGroup(
            pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_biodataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_namasupp, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txt_idsupp, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txt_alamatsupp, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(txt_nohpsupp, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_biodataLayout.setVerticalGroup(
            pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_biodataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_idsupp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_namasupp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_biodataLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_alamatsupp, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnl_biodataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_nohpsupp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlsupplier.add(pnl_biodata, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 390, 250));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user black 20px.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlsupplier.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 13, 33, 33));

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA SUPPLIER");

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

        pnlsupplier.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        panelcustom2.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom2.setRoundBottomLeft(12);
        panelcustom2.setRoundTopLeft(12);
        panelcustom2.setRoundTopRight(12);
        panelcustom2.setRoundottomRight(12);
        panelcustom2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        panelcustom2.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 130, 43));

        btndelete.setBackground(new java.awt.Color(217, 24, 40));
        btndelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndelete.setForeground(new java.awt.Color(255, 255, 255));
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash 30px.png"))); // NOI18N
        btndelete.setText("HAPUS");
        btndelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        panelcustom2.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 43));

        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        panelcustom2.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 130, 43));

        pnlsupplier.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 390, 60));

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

        pnlsupplier.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 1040, 390));

        btn_pilih_pembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ceklist 30px.png"))); // NOI18N
        btn_pilih_pembelian.setText("Pilih >> pembelian");
        btn_pilih_pembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pilih_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilih_pembelianActionPerformed(evt);
            }
        });
        pnlsupplier.add(btn_pilih_pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 560, -1, -1));

        btn_batalpilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back 30px.png"))); // NOI18N
        btn_batalpilih.setText("Batal");
        btn_batalpilih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_batalpilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalpilihActionPerformed(evt);
            }
        });
        pnlsupplier.add(btn_batalpilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, 33));

        btnbatal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btnbatal.setText("REFRESH");
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        pnlsupplier.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, 43));

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setRoundBottomLeft(5);
        panelcustom1.setRoundTopLeft(5);
        panelcustom1.setRoundTopRight(5);
        panelcustom1.setRoundottomRight(5);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cari.setBorder(null);
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cariKeyTyped(evt);
            }
        });
        panelcustom1.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 200, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-25.png"))); // NOI18N
        panelcustom1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, 30, 30));

        pnlsupplier.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 246, 30));

        getContentPane().add(pnlsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        cariData();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
        cariData();
    }//GEN-LAST:event_txt_cariKeyTyped

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }

        String idusr = (String) model.getValueAt(i, 0);

        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = sambungan.koneksi.getKoneksi();
                String sql = "delete from supplier where id_supplier = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idusr);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }finally{
                btnsimpan.setVisible(true);
                btnbatal.setEnabled(true);
                btn_update.setVisible(false);
                btndelete.setEnabled(false);
                txt_idsupp.setEnabled(false);
                loadData();
                autonumber();
                clear();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        clear();
        loadData();
        btnsimpan.setVisible(true);
        btn_update.setVisible(false);
        btndelete.setEnabled(false);
        btnbatal.setEnabled(true);
        txt_idsupp.setEnabled(false);
        txt_idsupp.setEnabled(false);
        autonumber();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        String idsup = txt_idsupp.getText();
        String name = txt_namasupp.getText();
        String almt = txt_alamatsupp.getText();
        String telp = txt_nohpsupp.getText();
        
        if (idsup.equals("") || name.equals("") || almt.equals("") || telp.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap Isi semua form");
        }else {

        try {
            Connection c = sambungan.koneksi.getKoneksi();
            String sql = "insert into supplier values (?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idsup);
            p.setString(2, name);
            p.setString(3, almt);
            p.setString(4, telp);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Error Min");
        }finally{
            txt_namasupp.requestFocus(true);
            autonumber();
            clear();
            loadData();
        }}
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btnsimpan.setVisible(false);
        btn_update.setVisible(true);
        btndelete.setEnabled(true);
        btnbatal.setEnabled(true);
        txt_idsupp.setEnabled(true);

        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idsup = (String) model.getValueAt(i, 0);
        String nama = (String) model.getValueAt(i, 1);
        String almt = (String) model.getValueAt(i, 2);
        String telp = (String) model.getValueAt(i, 3);

        txt_idsupp.setText(idsup);
        txt_namasupp.setText(nama);
        txt_alamatsupp.setText(almt);
        txt_nohpsupp.setText(telp);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        pnlPengguna user = new pnlPengguna();
        halamanAdmin.mainAdmin.jDesktopPane1.add(user).setVisible(true);
        halamanAdmin.mainAdmin.judulMenu.setText("/ Pengguna");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_pilih_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilih_pembelianActionPerformed
        int i = jTable1.getSelectedRow();
        String id = jTable1.getValueAt(i, 0).toString();
        
        halamanAdmin.pnlPembelian.txt_idsupplier.setText(id);
        dispose();
    }//GEN-LAST:event_btn_pilih_pembelianActionPerformed

    private void btn_batalpilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalpilihActionPerformed
        dispose();
    }//GEN-LAST:event_btn_batalpilihActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idsup = (String) model.getValueAt(i, 0);
        String name = txt_namasupp.getText();
        String almt = txt_alamatsupp.getText();
        String telp = txt_nohpsupp.getText();

        try {
            Connection c = sambungan.koneksi.getKoneksi();
            String sql = "update supplier set id_supplier = ?, nama = ?, alamat = ?, telp = ? where id_supplier = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idsup);
            p.setString(2, name);
            p.setString(3, almt);
            p.setString(4, telp);
            p.setString(5, idsup);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
            btnsimpan.setEnabled(true);
            btndelete.setEnabled(false);
            btnbatal.setEnabled(false);
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Update Gagal");
        } finally {
            loadData();
            autonumber();
            autonumber();
            btnbatal.setEnabled(true);
            btn_update.setVisible(false);
            btnsimpan.setVisible(true);
            txt_idsupp.setEnabled(false);
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void pnlsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlsupplierMouseClicked
        jTable1.clearSelection();
        clear();
        loadData();
        btnsimpan.setVisible(true);
        btn_update.setVisible(false);
        btndelete.setEnabled(false);
        btnbatal.setEnabled(true);
        txt_idsupp.setEnabled(false);
        txt_idsupp.setEnabled(false);
        autonumber();
    }//GEN-LAST:event_pnlsupplierMouseClicked

    private void txt_nohpsuppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nohpsuppKeyReleased
        String nohp = txt_nohpsupp.getText();
        if (nohp.matches("[0-9+]*") && nohp.length() >= 7 && nohp.length() <= 14) {
            txt_nohpsupp.setBackground(Color.white);
        }else if("".equals(nohp)){
            txt_nohpsupp.setBackground(Color.white);
        } 
        else {
            txt_nohpsupp.setBackground(new Color(254, 161, 161));
        }
    }//GEN-LAST:event_txt_nohpsuppKeyReleased

    private void txt_namasuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namasuppActionPerformed
        txt_alamatsupp.requestFocus(true);
    }//GEN-LAST:event_txt_namasuppActionPerformed

    private void txt_alamatsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatsuppActionPerformed
        txt_nohpsupp.requestFocus(true);
    }//GEN-LAST:event_txt_alamatsuppActionPerformed

    private void txt_nohpsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpsuppActionPerformed
        String idsup = txt_idsupp.getText();
        String name = txt_namasupp.getText();
        String almt = txt_alamatsupp.getText();
        String telp = txt_nohpsupp.getText();
        
        if (idsup.equals("") || name.equals("") || almt.equals("") || telp.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap Isi semua form");
        }else {

        try {
            Connection c = sambungan.koneksi.getKoneksi();
            String sql = "insert into supplier values (?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idsup);
            p.setString(2, name);
            p.setString(3, almt);
            p.setString(4, telp);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Error Min");
        }finally{
            autonumber();
            clear();
            loadData();
        }}
    }//GEN-LAST:event_txt_nohpsuppActionPerformed
        public void run() {
            new dataSupplier().setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JToggleButton btn_batalpilih;
    public static javax.swing.JButton btn_pilih_pembelian;
    public javax.swing.JButton btn_update;
    public javax.swing.JButton btnbatal;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnsimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom pnl_biodata;
    private javax.swing.JPanel pnlsupplier;
    private javax.swing.JTextField txt_alamatsupp;
    public javax.swing.JTextField txt_cari;
    public javax.swing.JTextField txt_idsupp;
    private javax.swing.JTextField txt_namasupp;
    private javax.swing.JTextField txt_nohpsupp;
    // End of variables declaration//GEN-END:variables
}
