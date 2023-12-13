package halamanAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import sambungan.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class pnlPembelian extends javax.swing.JInternalFrame {
    /**
     * Creates new form pnlPembelian
     */
    koneksi koneksi = new koneksi();
    private DefaultTableModel model;
    
    private void autonumber(){
       try {
        Connection c = sambungan.koneksi.getKoneksi();
        Statement s = c.createStatement();
        String sql = "select * from pembelian_bahan order by id_pembelianbahan desc";
        ResultSet r = s.executeQuery(sql);
        if (r.next()) {
            String id = r.getString("id_pembelianbahan");
            String bhn = "" + (Integer.parseInt(id) + 1);

            txt_idpembelian.setText(bhn);
        } else {
            txt_idpembelian.setText("1");
        }
        r.close();
        s.close();
    } catch (Exception e) {
        System.out.println("autonumber error");
    }
       try {
        Connection c = sambungan.koneksi.getKoneksi();
        Statement s = c.createStatement();
        String sql = "select * from bahan_baku order by id_bahanbaku desc";
        ResultSet r = s.executeQuery(sql);
        if (r.next()) {
            String id = r.getString("id_bahanbaku");
            String bhn = "" + (Integer.parseInt(id) + 1);

            txt_idbarang.setText(bhn);
        } else {
            txt_idbarang.setText("1");
        }
        r.close();
        s.close();
    } catch (Exception e) {
        System.out.println("autonumber error");
    }
}
    
    public void clear(){
        txt_idsupplier.setText("");
        txt_namabrg.setText("");
        txt_hargabrg.setText("");
        txt_jumlahbrg.setText("");
        txt_hargatotal.setText("");
        btn_update.setVisible(false);
        btnsimpan.setVisible(true);
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        txt_namabrg.setText("");
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from viewpembelian_null";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[8];
                o [0] = r.getString("id_pembelianbahan");
                o [1] = r.getObject("id_supplier");
                o [2] = r.getObject("id_bahanbaku");
                o [3] = r.getObject("nama");
                o [4] = r.getObject("harga");
                o [5] = r.getString("jumlah");
                o [6] = r.getString("total_harga");
                o [7] = r.getString("tanggal");
                
                model.addRow(o);
                jTable1.setModel(model);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan cuy!");
        }
    }
    
    public void loadCombo(){
        DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<>();
         try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select nama from bahan_baku";
            ResultSet r = s.executeQuery(sql);
            
            combo = new DefaultComboBoxModel<>();
            combo_namabahan.setModel(combo);
            
            combo.addElement("-");
            while (r.next()) {
                combo_namabahan.addItem(r.getString("nama"));
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void PembelianHariIni() {
    try {
        Connection c = koneksi.getKoneksi();
        
        // Ambil tanggal hari ini
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayString = today.format(formatter);

        // Query untuk menghitung total pembelian pada hari ini
        String sql = "SELECT SUM(total_harga) AS total_pembelian FROM pembelian_bahan WHERE DATE(tanggal) = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, todayString);
        ResultSet r = p.executeQuery();
        
        if (r.next()) {
            int totalPembelian = r.getInt("total_pembelian");
            lblTotalBeli.setText("Pengeluaran Hari Ini:");
            txtTotalBeliNow.setText(String.valueOf("Rp" + totalPembelian));
        } else {
            // Tidak ada pembelian pada hari ini
            txtTotalBeliNow.setText("Rp0");
        }

        r.close();
        p.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
    private void PembelianTanggal() {
    try {
        Connection c = koneksi.getKoneksi();
        
        // Ambil tanggal 
        String todayString = txtDate.getText();

        // Query untuk menghitung total pembelian pada hari ini
        String sql = "SELECT SUM(total_harga) AS total_pembelian FROM pembelian_bahan WHERE DATE(tanggal) = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, todayString);
        ResultSet r = p.executeQuery();
        
        if (r.next()) {
            int totalPembelian = r.getInt("total_pembelian");
            lblTotalBeli.setText("Pengeluaran Tanggal " + todayString + " :");
            txtTotalBeliNow.setText(String.valueOf("Rp" + totalPembelian));
        } else {
            // Tidak ada pembelian pada hari ini
            txtTotalBeliNow.setText("Rp0");
        }

        r.close();
        p.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
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
        model.addColumn("ID Pembelian");
        model.addColumn("ID Supplier");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Brg");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");

        model.setRowCount(0); // Mengganti model.getDataVector().removeAllElements()
        String cari = txt_cari.getText();
        try {
            String sql = "select * from viewpembelian_null where id_pembelianbahan like '%" + cari + "%' or id_supplier like '%" + cari + "%' or id_bahanbaku like '%" + cari + "%' or nama like '%" + cari + "%' or harga like '%" + cari + "%' or jumlah like '%" + cari + "%' or total_harga like '%" + cari + "%'";

            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[7];
                obj[0] = res.getString("id_pembelianbahan");
                obj[1] = res.getString("id_supplier");
                obj[2] = res.getString("id_bahanbaku");
                obj[3] = res.getString("nama");
                obj[4] = res.getString("harga");
                obj[5] = res.getString("jumlah");
                obj[6] = res.getString("total_harga");
                model.addRow(obj);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void cariTanggal(){
        try{
            PreparedStatement ps;
            ResultSet rs;
            String cartang = txtDate.getText();
            String caritanggal = "select * from viewpembelian_null where date(tanggal) = ?";
            ps = koneksi.getKoneksi().prepareStatement(caritanggal);
            ps.setString(1, cartang);
            rs = ps.executeQuery();
            
            // menampilkan data sebelumnya pada tabel
//            DefaultTableModel model = new DefaultTableModel();
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            model.addColumn("ID Pembelian");
            model.addColumn("ID Supplier");
            model.addColumn("ID Barang");
            model.addColumn("Nama Barang");
            model.addColumn("Harga Brg");
            model.addColumn("Jumlah");
            model.addColumn("Total Harga");
            model.addColumn("Tanggal");
            jTable1.setModel(model);

            while (rs.next()){
               model.addRow(new Object[]  {
                  rs.getString("id_pembelianbahan"),
                  rs.getString("id_supplier"),
                  rs.getString("id_bahanbaku"),
                  rs.getString("nama"),
                  rs.getString("harga"),
                  rs.getString("jumlah"),
                  rs.getString("total_harga"),
                  rs.getString("tanggal"),
                });
            }
        }catch(Exception e){
        System.out.println("Cari tanggal error");
        }
    }

    public pnlPembelian() {
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
        
        model.addColumn("ID Pembelian");
        model.addColumn("ID Supplier");
        model.addColumn("ID Bahan");
        model.addColumn("Nama Bahan");
        model.addColumn("Harga Bhn");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        
        jTable1.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
        int totalWidth = jTable1.getWidth();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
        jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.25)); 
        jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
        jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(6).setPreferredWidth((int) (totalWidth * 0.11));
        jTable1.getColumnModel().getColumn(7).setPreferredWidth((int) (totalWidth * 0.19));
    }
});
        
        loadData();
        loadCombo();
        PembelianHariIni();
        autonumber();
        
        btndelete.setEnabled(false);
        btnbatal.setEnabled(true);
        txt_idpembelian.setEnabled(false);
        txtDate.setText("Cari Tanggal");
        txt_namabrg.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        pembelian = new javax.swing.JPanel();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelcustom1 = new custom.panelcustom();
        jLabel1 = new javax.swing.JLabel();
        txt_idpembelian = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_namabrg = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_jumlahbrg = new javax.swing.JTextField();
        txt_hargatotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_idsupplier = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_supplierview = new javax.swing.JButton();
        txt_hargabrg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_idbarang = new javax.swing.JTextField();
        combo_namabahan = new javax.swing.JComboBox<>();
        panelcustom2 = new custom.panelcustom();
        btndelete = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        txtDate = new javax.swing.JTextField();
        btnFindDate = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        panelcustom4 = new custom.panelcustom();
        jLabel10 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        panelcustom5 = new custom.panelcustom();
        jLabel4 = new javax.swing.JLabel();
        lblTotalBeli = new javax.swing.JLabel();
        txtTotalBeliNow = new javax.swing.JTextField();

        dateChooser1.setTextRefernce(txtDate);
        dateChooser1.getAccessibleContext().setAccessibleParent(txtDate);

        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pembelian.setPreferredSize(new java.awt.Dimension(1540, 870));
        pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pembelianMouseClicked(evt);
            }
        });
        pembelian.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA PEMBELIAN");

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

        pembelian.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 980, 560));

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("ID Pembelian");
        panelcustom1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 16, -1, -1));

        txt_idpembelian.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        panelcustom1.add(txt_idpembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 13, 270, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Nama Barang");
        panelcustom1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 160, -1, -1));

        txt_namabrg.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_namabrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namabrgActionPerformed(evt);
            }
        });
        panelcustom1.add(txt_namabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 157, 270, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Jumlah brg");
        panelcustom1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 256, -1, -1));

        txt_jumlahbrg.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_jumlahbrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlahbrgKeyReleased(evt);
            }
        });
        panelcustom1.add(txt_jumlahbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 253, 270, 30));

        txt_hargatotal.setEditable(false);
        txt_hargatotal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_hargatotal.setFocusable(false);
        panelcustom1.add(txt_hargatotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 301, 270, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Harga Total");
        panelcustom1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 304, -1, -1));

        txt_idsupplier.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        panelcustom1.add(txt_idsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 61, 233, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("ID Supplier");
        panelcustom1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 64, -1, -1));

        btn_supplierview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/supplier 20px.png"))); // NOI18N
        btn_supplierview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_supplierview.setPreferredSize(new java.awt.Dimension(28, 28));
        btn_supplierview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supplierviewActionPerformed(evt);
            }
        });
        panelcustom1.add(btn_supplierview, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 61, 30, 30));

        txt_hargabrg.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_hargabrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargabrgActionPerformed(evt);
            }
        });
        txt_hargabrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hargabrgKeyReleased(evt);
            }
        });
        panelcustom1.add(txt_hargabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 205, 270, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Harga Barang");
        panelcustom1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 208, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("ID Barang");
        panelcustom1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 112, -1, -1));

        txt_idbarang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        panelcustom1.add(txt_idbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 109, 59, 30));

        combo_namabahan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        combo_namabahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_namabahanActionPerformed(evt);
            }
        });
        panelcustom1.add(combo_namabahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 109, 193, 30));

        pembelian.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 440, 340));

        panelcustom2.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom2.setRoundBottomLeft(12);
        panelcustom2.setRoundTopLeft(12);
        panelcustom2.setRoundTopRight(12);
        panelcustom2.setRoundottomRight(12);
        panelcustom2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        panelcustom2.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 43));

        btnsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        panelcustom2.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 43));

        btn_update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        panelcustom2.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 43));

        pembelian.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 440, 60));

        txtDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDate.setFocusable(false);
        pembelian.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 120, 118, 30));
        txtDate.getAccessibleContext().setAccessibleParent(this);

        btnFindDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_date 20px.png"))); // NOI18N
        btnFindDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFindDate.setPreferredSize(new java.awt.Dimension(30, 30));
        btnFindDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindDateActionPerformed(evt);
            }
        });
        pembelian.add(btnFindDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 120, -1, -1));

        btnbatal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btnbatal.setText("REFRESH");
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbatal.setFocusable(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        pembelian.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, 43));

        panelcustom4.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom4.setRoundBottomLeft(5);
        panelcustom4.setRoundTopLeft(5);
        panelcustom4.setRoundTopRight(5);
        panelcustom4.setRoundottomRight(5);
        panelcustom4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-25.png"))); // NOI18N
        panelcustom4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, 30, 30));

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
        panelcustom4.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 30));

        pembelian.add(panelcustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 246, 30));

        panelcustom5.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom5.setRoundBottomLeft(12);
        panelcustom5.setRoundTopLeft(12);
        panelcustom5.setRoundTopRight(12);
        panelcustom5.setRoundottomRight(12);
        panelcustom5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-cost-45.png"))); // NOI18N
        panelcustom5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 60));

        lblTotalBeli.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotalBeli.setText("Total Pembelian");
        panelcustom5.add(lblTotalBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 2, -1, -1));

        txtTotalBeliNow.setEditable(false);
        txtTotalBeliNow.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalBeliNow.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTotalBeliNow.setBorder(null);
        txtTotalBeliNow.setFocusable(false);
        panelcustom5.add(txtTotalBeliNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 22, 250, 30));

        pembelian.add(panelcustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 740, 320, 60));

        getContentPane().add(pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        String idbahan = txt_idbarang.getText();
        String idsup = txt_idsupplier.getText();
        String name = txt_namabrg.getText();
        String bijian = txt_hargabrg.getText();
        String stok = txt_jumlahbrg.getText();
        
        String id = txt_idpembelian.getText();
        String baku = txt_idbarang.getText();
        String jumlah = txt_jumlahbrg.getText();
        String harga = txt_hargatotal.getText();
        String tanggal = halamanAdmin.mainAdmin.txtDatetime.getText();
        
        if (name.equals("") || bijian.equals("") || stok.equals("") || harga.equals("") || idsup.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap Isi semua form");
        }else {
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "select count(*) from bahan_baku where nama = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, name);
            
            ResultSet r = p.executeQuery();
            r.next();
                int count = r.getInt(1);
        
            if (count > 0) {
                // Jika nama barang sudah ada, lakukan operasi update pada stok
                sql = "update bahan_baku set harga = ?, stock = stock + ? where nama = ?;";
                p = c.prepareStatement(sql);
                p.setString(1, bijian);
                p.setString(2, stok);
                p.setString(3, name);
                p.executeUpdate();
                p.close();
                System.out.println("Data berhasil diupdate.");
                loadData();

                sql = "insert into pembelian_bahan values (?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, id);
                p.setString(2, baku);
                p.setString(3, jumlah);
                p.setString(4, harga);
                p.setString(5, tanggal);
                p.executeUpdate();
                p.close();
                System.out.println("Pemebelian Bahan Berhasil");
                loadData();
            } else {
                // Jika nama barang belum ada, lakukan operasi insert pada bahan_baku dan pembelian bahan
                sql = "insert into bahan_baku values (?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, idbahan);
                p.setString(2, idsup);
                p.setString(3, name);
                p.setString(4, bijian);
                p.setString(5, stok);
                p.executeUpdate();
                p.close();
                System.out.println("Bahan Baku disimpan");
                loadData();
        
                sql = "insert into pembelian_bahan values (?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, id);
                p.setString(2, baku);
                p.setString(3, jumlah);
                p.setString(4, harga);
                p.setString(5, tanggal);
                p.executeUpdate();
                p.close();
                System.out.println("Pemebelian Bahan Berhasil");
                loadData();
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
    }
        finally{
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            autonumber();
            clear();
            loadData();
            PembelianHariIni();
        }
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }

        String idbuy = (String) model.getValueAt(i, 0);
//        String idbhn = (String) model.getValueAt(i, 2);

        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = sambungan.koneksi.getKoneksi();
                String sql = "delete from pembelian_bahan where id_pembelianbahan = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idbuy);
                p.executeUpdate();
                p.close();
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan hapus pada pembelian_bahan");
            }
        
//            try {
//                Connection c = sambungan.koneksi.getKoneksi();
//                String sql = "delete from bahan_baku where id_bahanbaku = ?";
//                PreparedStatement p = c.prepareStatement(sql);
//                p.setString(1, idbhn);
//                p.executeUpdate();
//                p.close();
//            } catch (Exception e) {
//                System.out.println("Terjadi Kesalahan hapus pada bahan_baku");
//            }
            
            finally{
                JOptionPane.showMessageDialog(null, "Data Terhapus");
                btndelete.setEnabled(false);
                btnbatal.setEnabled(true);
                loadData();
                PembelianHariIni();
                autonumber();
                clear();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {

        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        btndelete.setEnabled(false);
        btnbatal.setEnabled(true);
        txt_idpembelian.setEnabled(false);
        txt_cari.setText("");
        txtDate.setText("Cari Tanggal");
        autonumber();
        loadData();
        PembelianHariIni();
        clear();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btn_supplierviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierviewActionPerformed
        dataSupplier tampilsupli = new dataSupplier();
        halamanAdmin.mainAdmin.jDesktopPane1.add(tampilsupli).setVisible(true);
        halamanAdmin.dataSupplier.btn_pilih_pembelian.setVisible(true);
        halamanAdmin.dataSupplier.btn_batalpilih.setVisible(true);
    }//GEN-LAST:event_btn_supplierviewActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btnsimpan.setVisible(false);
        btn_update.setVisible(true);
        btndelete.setEnabled(true);
        btnbatal.setEnabled(true);
        txt_idpembelian.setEnabled(true);
        combo_namabahan.setSelectedItem("-");

        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idbuy = model.getValueAt(i, 0).toString();
        String idsup = model.getValueAt(i, 1).toString();
        String idbhn = model.getValueAt(i, 2).toString();
        String name =  model.getValueAt(i, 3).toString();
        String bijian =  model.getValueAt(i, 4).toString();
        String jumlah =  model.getValueAt(i, 5).toString();
        String grand =  model.getValueAt(i, 6).toString();

        txt_idpembelian.setText(idbuy);
        txt_idsupplier.setText(idsup);
        txt_idbarang.setText(idbhn);
        txt_namabrg.setText(name);
        txt_hargabrg.setText(bijian);
        txt_jumlahbrg.setText(jumlah);
        txt_hargatotal.setText(grand);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        cariData();
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyTyped
        cariData();
    }//GEN-LAST:event_txt_cariKeyTyped

    private void btnFindDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindDateActionPerformed
        cariTanggal();
        PembelianTanggal();
    }//GEN-LAST:event_btnFindDateActionPerformed

    private void combo_namabahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_namabahanActionPerformed
        String bahanpilih = combo_namabahan.getSelectedItem().toString();
       try {
        Connection c = koneksi.getKoneksi();
        String sql = "select id_bahanbaku, id_supplier, nama, harga from bahan_baku where nama = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, bahanpilih);
        ResultSet r = p.executeQuery();

        if (r.next()) {
            String idBahan = r.getString("id_bahanbaku");
            String idsupplier = r.getString("id_supplier");
            String namaBahan = r.getString("nama");
            String hargaBahan = r.getString("harga");
            txt_idbarang.setText(idBahan);
            txt_idsupplier.setText(idsupplier);
            txt_namabrg.setText(namaBahan);
            txt_hargabrg.setText(hargaBahan);
            txt_jumlahbrg.setText("");
            txt_hargatotal.setText("");
            
        }else {
            autonumber();
            txt_idsupplier.setText("");
            txt_namabrg.setText("");
            txt_hargabrg.setText("");
            txt_jumlahbrg.setText("");
            txt_hargatotal.setText("");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }//GEN-LAST:event_combo_namabahanActionPerformed

    private void txt_jumlahbrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahbrgKeyReleased
        int total, bayar, grand;
        
        bayar = Integer.valueOf(txt_hargabrg.getText());
        total = Integer.valueOf(txt_jumlahbrg.getText());
        
        if (total == 0 || bayar == 0) {
            JOptionPane.showMessageDialog(null, "harap masukkan dengan benar");
            txt_jumlahbrg.setText("");
            txt_hargabrg.setText("");
        } 
        else {
            grand = bayar * total;
            txt_hargatotal.setText(String.valueOf(grand));
        }
        
        txt_hargatotal.setEditable(false);
    }//GEN-LAST:event_txt_jumlahbrgKeyReleased

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idbuy =  model.getValueAt(i, 0).toString();
        String idbhn = model.getValueAt(i, 2).toString();
        String idbahan = txt_idbarang.getText();
        String jumlah = txt_jumlahbrg.getText();
        String grand = txt_hargatotal.getText();
//        String tanggal = halamanAdmin.mainAdmin.txtDatetime.getText();
        String idsup = txt_idsupplier.getText();
        String name = txt_namabrg.getText();
        String bijian = txt_hargabrg.getText();
        String stok = txt_jumlahbrg.getText();

            try {
                try {
                       Connection c = koneksi.getKoneksi();
                       String sql = "update pembelian_bahan set id_pembelianbahan = ?, id_bahanbaku = ?, jumlah = ?, total_harga = ? where id_pembelianbahan = ?";
                       PreparedStatement p = c.prepareStatement(sql);
                       p.setString(1, idbuy);
                       p.setString(2, idbahan);
                       p.setString(3, jumlah);
                       p.setString(4, grand);
           //            p.setString(5, tanggal);
                       p.setString(5, idbuy);

                       p.executeUpdate();
                       p.close();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

            Font pont = new Font("Arial", Font.PLAIN, 16);
            UIManager.put("OptionPane.messageFont", pont);
            UIManager.put("OptionPane.buttonFont", pont);

            Object[] options = { "jangan update", "update juga bahan baku" };

            int dialogResult = JOptionPane.showOptionDialog(null, "Apakah anda ingin mengupdate bahan baku juga?", "Konfirmasi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (dialogResult == 1) {
                    Connection cBahanBaku = koneksi.getKoneksi();
                    String sqlBahanBaku = "update bahan_baku set id_bahanbaku = ?, id_supplier = ?, nama = ?, harga = ?, stock = ? where id_bahanbaku = ?";
                    PreparedStatement pBahanBaku = cBahanBaku.prepareStatement(sqlBahanBaku);
                    pBahanBaku.setString(1, idbahan);
                    pBahanBaku.setString(2, idsup);
                    pBahanBaku.setString(3, name);
                    pBahanBaku.setString(4, bijian);
                    pBahanBaku.setString(5, stok);
                    pBahanBaku.setString(6, idbhn);

                    pBahanBaku.executeUpdate();
                    pBahanBaku.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        finally{
            JOptionPane.showMessageDialog(null, "Data Terubah");
            btndelete.setEnabled(false);
            btnbatal.setEnabled(true);
            clear();
            loadData();
            autonumber();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pembelianMouseClicked
        jTable1.clearSelection();
    }//GEN-LAST:event_pembelianMouseClicked

    private void txt_hargabrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hargabrgKeyReleased
        String nohp = txt_hargabrg.getText();
        if (nohp.matches("[0-9]*")) {
            txt_hargabrg.setBackground(Color.white);
        } else {
            txt_hargabrg.setBackground(new Color(254, 161, 161));
        }
    }//GEN-LAST:event_txt_hargabrgKeyReleased

    private void txt_hargabrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargabrgActionPerformed
        txt_jumlahbrg.requestFocus(true);
    }//GEN-LAST:event_txt_hargabrgActionPerformed

    private void txt_namabrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namabrgActionPerformed
        txt_hargabrg.requestFocus(true);
    }//GEN-LAST:event_txt_namabrgActionPerformed
    public void run() {
        new pnlPembelian().setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindDate;
    private javax.swing.JButton btn_supplierview;
    public javax.swing.JButton btn_update;
    public javax.swing.JButton btnbatal;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> combo_namabahan;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTotalBeli;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom panelcustom4;
    private custom.panelcustom panelcustom5;
    private javax.swing.JPanel pembelian;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTotalBeliNow;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_hargabrg;
    private javax.swing.JTextField txt_hargatotal;
    private javax.swing.JTextField txt_idbarang;
    private javax.swing.JTextField txt_idpembelian;
    public static javax.swing.JTextField txt_idsupplier;
    private javax.swing.JTextField txt_jumlahbrg;
    private javax.swing.JTextField txt_namabrg;
    // End of variables declaration//GEN-END:variables
}
