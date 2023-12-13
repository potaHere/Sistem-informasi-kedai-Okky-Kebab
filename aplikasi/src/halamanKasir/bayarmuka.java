package halamanKasir;
import static halamanKasir.Pesananmenu.jTable3;
import static halamanKasir.Pesananmenu.radioPembelianDP;
import static halamanKasir.Pesananmenu.txt_harga;
import static halamanKasir.Pesananmenu.txt_idmenu;
import static halamanKasir.Pesananmenu.txt_jumlah;
import static halamanKasir.Pesananmenu.txt_namamenu;
import static halamanKasir.Pesananmenu.txt_tampil;
import static halamanKasir.Pesananmenu.txt_totalbayar;
import java.awt.Color;
import java.awt.Font;
import sambungan.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class bayarmuka extends javax.swing.JFrame {
    private DefaultTableModel modelmuka;

    public void tampiljualdp() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "select * from prepayment";
            ResultSet r = s.executeQuery(sql);

            modelmuka = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modelmuka.addColumn("ID Pesanan");
            modelmuka.addColumn("ID Pelanggan");
            modelmuka.addColumn("Nama");
            modelmuka.addColumn("No.Hp");
            modelmuka.addColumn("Alamat");
            modelmuka.addColumn("Total Beli");
            modelmuka.addColumn("Bayar");
            modelmuka.addColumn("Kembalian");
            modelmuka.addColumn("Tanggal");

            while (r.next()) {
                Object[] o = new Object[9];
                o[0] = r.getString("id_pesanan");
                o[1] = r.getString("id_pelanggan");
                o[2] = r.getString("namaPelanggan");
                o[3] = r.getString("noHp");
                o[4] = r.getString("alamat");
                o[5] = r.getInt("totalbeli");
                o[6] = r.getInt("bayar");
                o[7] = r.getInt("kembalian");
                o[8] = r.getString("tanggal");

                modelmuka.addRow(o);
            }
            jTable1.setModel(modelmuka);

            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void prabayardone() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "select * from prabayar where kembalian >= 0";
            ResultSet r = s.executeQuery(sql);

            modelmuka = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            modelmuka.addColumn("ID Pesanan");
            modelmuka.addColumn("ID Pelanggan");
            modelmuka.addColumn("Nama");
            modelmuka.addColumn("No.Hp");
            modelmuka.addColumn("Alamat");
            modelmuka.addColumn("Total Beli");
            modelmuka.addColumn("Bayar");
            modelmuka.addColumn("Kembalian");
            modelmuka.addColumn("Tanggal");

            while (r.next()) {
                Object[] o = new Object[9];
                o[0] = r.getString("id_pesanan");
                o[1] = r.getString("id_pelanggan");
                o[2] = r.getString("namaPelanggan");
                o[3] = r.getString("noHp");
                o[4] = r.getString("alamat");
                o[5] = r.getInt("totalbeli");
                o[6] = r.getInt("bayar");
                o[7] = r.getInt("kembalian");
                o[8] = r.getString("tanggal");

                modelmuka.addRow(o);
            }
            jTable1.setModel(modelmuka);

            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void bersih(){
        txtIdPesananDP.setText("");
        txtIdPelangganDP.setText("");
        txtNamaPelangganDP.setText("");
        txtNoHpPelangganDP.setText("");
        txtAlamatPelangganDP.setText("");
        txtTotalBayarDP.setText("");
        txtTunaiDP.setText("");
        txtKembalianDP.setText("");
        jTable1.clearSelection();
    }
    
    public void buylater(){
        try {
            int i = jTable1.getSelectedRow();
            String idPesanan = jTable1.getValueAt(i, 0).toString();
            PreparedStatement ps;
            ResultSet rs;

            String tampil = "select * from pesanandetail where id_pesanan = ?";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, idPesanan);
            rs = ps.executeQuery();
            
            Pesananmenu.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            Pesananmenu.model.addColumn("ID Pesanan");
            Pesananmenu.model.addColumn("ID Menu");
            Pesananmenu.model.addColumn("Nama Menu");
            Pesananmenu.model.addColumn("Jumlah");
            Pesananmenu.model.addColumn("Harga");
            Pesananmenu.model.addColumn("Total");

            Pesananmenu.model.getDataVector().removeAllElements();
            Pesananmenu.model.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_menu"),
                    rs.getString("namamenu"),
                    rs.getString("jumlah"),
                    rs.getString("harga"),
                    rs.getString("total"),
                };

                Pesananmenu.model.addRow(jual);
            }
            Pesananmenu.jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = Pesananmenu.jTable1.getWidth();
            Pesananmenu.jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            Pesananmenu.jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
            Pesananmenu.jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.25)); 
            Pesananmenu.jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.01)); 
            Pesananmenu.jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
            Pesananmenu.jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.15)); 
            }
        });
            Pesananmenu.jTable1.setModel(Pesananmenu.model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
    }
    
    public void cariData(){
//        DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        model.addColumn("ID Pesanan");
        model.addColumn("ID Pelanggan");
        model.addColumn("Nama");
        model.addColumn("No.Hp");
        model.addColumn("Alamat");
        model.addColumn("Total Beli");
        model.addColumn("Bayar");
        model.addColumn("Kembalian");
        model.addColumn("Tanggal");

        model.setRowCount(0); // Mengganti model.getDataVector().removeAllElements()
        String cari = txtCarimenu.getText();
        try {
            String sql = "select * from prabayar where id_pesanan like '%" + cari + "%' or id_pelanggan like '%" + cari + "%' or namaPelanggan like '%" + cari + "%' or noHP like '%" + cari + "%' or alamat like '%" + cari + "%' or totalbeli like '%" + cari + "%' or bayar like '%" + cari + "%' or kembalian like '%" + cari + "%' or tanggal like '%" + cari +  "%'";

            java.sql.Connection conn = (Connection) koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[9];
                obj[0] = res.getString("id_pesanan");
                obj[1] = res.getString("id_pelanggan");
                obj[2] = res.getString("namaPelanggan");
                obj[3] = res.getString("noHp");
                obj[4] = res.getString("Alamat");
                obj[5] = res.getString("totalbeli");
                obj[6] = res.getString("bayar");
                obj[7] = res.getString("kembalian");
                obj[8] = res.getString("tanggal");
                model.addRow(obj);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void totalBiaya(){
        int jumlahBaris = Pesananmenu.jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(Pesananmenu.jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(Pesananmenu.jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        Pesananmenu.txt_totalbayar.setText(String.valueOf(totalBiaya));
        Pesananmenu.txt_tampil.setText("Rp"+ totalBiaya);
    }
    
    public void clear2(){
        Pesananmenu.txt_idmenu.setText("");
        Pesananmenu.txt_namamenu.setText("");
        Pesananmenu.txt_harga.setText("");
        Pesananmenu.txt_jumlah.setText("");
    }

    public bayarmuka() {
        initComponents();
        tampiljualdp();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelcustom1 = new custom.panelcustom();
        jLabel3 = new javax.swing.JLabel();
        txtIdPelangganDP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNamaPelangganDP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtAlamatPelangganDP = new javax.swing.JTextField();
        txtNoHpPelangganDP = new javax.swing.JTextField();
        txtIdPesananDP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTotalBayarDP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTunaiDP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKembalianDP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnSimpanPrabayar = new javax.swing.JButton();
        panelcustom5 = new custom.panelcustom();
        txtCarimenu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cekDoneElse = new javax.swing.JCheckBox();
        btnFormat = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("ID Pelanggan");
        panelcustom1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtIdPelangganDP.setEditable(false);
        txtIdPelangganDP.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPelangganDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIdPelangganDP.setFocusable(false);
        panelcustom1.add(txtIdPelangganDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 230, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Nama");
        panelcustom1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtNamaPelangganDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNamaPelangganDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganDPActionPerformed(evt);
            }
        });
        panelcustom1.add(txtNamaPelangganDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 230, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Nomor HP");
        panelcustom1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Alamat");
        panelcustom1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txtAlamatPelangganDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        panelcustom1.add(txtAlamatPelangganDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 230, 90));

        txtNoHpPelangganDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNoHpPelangganDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpPelangganDPActionPerformed(evt);
            }
        });
        txtNoHpPelangganDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoHpPelangganDPKeyReleased(evt);
            }
        });
        panelcustom1.add(txtNoHpPelangganDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 230, -1));

        txtIdPesananDP.setEditable(false);
        txtIdPesananDP.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPesananDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIdPesananDP.setFocusable(false);
        panelcustom1.add(txtIdPesananDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 230, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("ID Pesanan");
        panelcustom1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtTotalBayarDP.setEditable(false);
        txtTotalBayarDP.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalBayarDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTotalBayarDP.setFocusable(false);
        panelcustom1.add(txtTotalBayarDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 230, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Total Bayar");
        panelcustom1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        txtTunaiDP.setEditable(false);
        txtTunaiDP.setBackground(new java.awt.Color(255, 255, 255));
        txtTunaiDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTunaiDP.setFocusable(false);
        panelcustom1.add(txtTunaiDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 230, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Tunai");
        panelcustom1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Kembalian/(-)");
        panelcustom1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        txtKembalianDP.setEditable(false);
        txtKembalianDP.setBackground(new java.awt.Color(255, 255, 255));
        txtKembalianDP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtKembalianDP.setFocusable(false);
        panelcustom1.add(txtKembalianDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 230, -1));

        jPanel1.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 370, 390));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("PEMBELIAN PRA BAYAR");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 30, 1610, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 1460, 420));

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-left-35.png"))); // NOI18N
        btnBack.setText("KEMBALI");
        btnBack.setFocusable(false);
        btnBack.setPreferredSize(new java.awt.Dimension(135, 40));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 140, -1));

        btnSimpanPrabayar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpanPrabayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btnSimpanPrabayar.setText("SIMPAN");
        btnSimpanPrabayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPrabayarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpanPrabayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 565, 117, 39));

        panelcustom5.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom5.setRoundBottomLeft(5);
        panelcustom5.setRoundTopLeft(5);
        panelcustom5.setRoundTopRight(5);
        panelcustom5.setRoundottomRight(5);
        panelcustom5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCarimenu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCarimenu.setBorder(null);
        txtCarimenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarimenuActionPerformed(evt);
            }
        });
        txtCarimenu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarimenuKeyTyped(evt);
            }
        });
        panelcustom5.add(txtCarimenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 223, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-30.png"))); // NOI18N
        panelcustom5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 40));

        jPanel1.add(panelcustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 300, 40));

        cekDoneElse.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cekDoneElse.setText("Transaksi Selesai");
        cekDoneElse.setFocusable(false);
        cekDoneElse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekDoneElseActionPerformed(evt);
            }
        });
        jPanel1.add(cekDoneElse, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 600, -1, -1));

        btnFormat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFormat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btnFormat.setText("BATAL");
        btnFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormatActionPerformed(evt);
            }
        });
        jPanel1.add(btnFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 117, 39));

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 117, 39));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 565, 117, 39));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanPrabayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPrabayarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String idpesanan = txtIdPesananDP.getText();
        String idpelang = txtIdPelangganDP.getText();
        String nama = txtNamaPelangganDP.getText();
        String noHp = txtNoHpPelangganDP.getText();
        String alamat = txtAlamatPelangganDP.getText();
        String total = txtTotalBayarDP.getText();
        String tunai = txtTunaiDP.getText();
        String kembalian = txtKembalianDP.getText();
        String tanggal = Pesananmenu.txtDatetime.getText();
        
        
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke!");
        if (txtNamaPelangganDP.getText().isEmpty() || txtNoHpPelangganDP.getText().isEmpty() || txtAlamatPelangganDP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Isi data dengan lengkap");
            return;
            }
        else if(!noHp.matches("[0-9+]*") || noHp.length() < 7 || noHp.length() > 14){
            JOptionPane.showMessageDialog(null, "Masukkan Nomor Hp dengan benar");
            txtNoHpPelangganDP.requestFocus(true);
            return;
        }
        else {
                
        try {
            int bone = Integer.parseInt(txtKembalianDP.getText());
            
            int baris = jTable1.getRowCount();
            Connection c = koneksi.getKoneksi();
            
            String sql3 = "insert into pelanggan values (?, ?, ?, ?)";
            PreparedStatement q = c.prepareStatement(sql3);
            q.setString(1, idpelang);
            q.setString(2, nama);
            q.setString(3, noHp);
            q.setString(4, alamat);
            q.executeUpdate();
            q.close();
            
            Pesananmenu.jScrollPane1.setVisible(false);
            Pesananmenu.jScrollPane3.setVisible(true);
            Pesananmenu.cekAll.setVisible(true);
            Pesananmenu.cekThen.setVisible(true);
            Pesananmenu.cekLate.setVisible(true);
            Pesananmenu.cekLate.setSelected(true);
            Pesananmenu.cekLate.setEnabled(false);
            Pesananmenu.btnMenu.setVisible(true);
            Pesananmenu.btnPesanan.setVisible(false);
//            Pesananmenu.cekLateActionPerformed(evt);
            Font pont = new Font("Arial", Font.PLAIN, 23);
            UIManager.put("OptionPane.messageFont", pont);
            UIManager.put("OptionPane.buttonFont", pont);

            JOptionPane.showMessageDialog(null, "Berhasil");
            
//        Pesananmenu.pesananhariini();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tampiljualdp();
        bersih();
//        clear();
//        utama();
//        autonumber();
//        kosong();
        Pesananmenu.radioPembelianDP.setSelected(false);
        Pesananmenu.txt_tampil.setText("Rp0");
//        jScrollPane3.setVisible(false);
//        jScrollPane1.setVisible(true);
        }
    }//GEN-LAST:event_btnSimpanPrabayarActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
//        Pesananmenu.cekSelesai.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtNoHpPelangganDPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoHpPelangganDPKeyReleased
        String nohp = txtNoHpPelangganDP.getText();
        if (nohp.matches("[0-9+]*") && nohp.length() >= 7 && nohp.length() <= 14) {
            txtNoHpPelangganDP.setBackground(Color.white);
        }else if("".equals(nohp)){
            txtNoHpPelangganDP.setBackground(Color.white);
        } 
        else {
            txtNoHpPelangganDP.setBackground(new Color(254, 161, 161));
        }

    }//GEN-LAST:event_txtNoHpPelangganDPKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
            int klik = jTable1.getSelectedRow();
            String idpesan = jTable1.getValueAt(klik, 0).toString();
            String idpelang = jTable1.getValueAt(klik, 1).toString();
            String nama = jTable1.getValueAt(klik, 2).toString();
            String noHp = jTable1.getValueAt(klik, 3).toString();
            String alamat = jTable1.getValueAt(klik, 4).toString();
            String totbay = jTable1.getValueAt(klik, 5).toString();
            String tunai = jTable1.getValueAt(klik, 6).toString();
            String kembalian = jTable1.getValueAt(klik, 7).toString();

            if (evt.getClickCount() == 2 && !cekDoneElse.isSelected()) {
                Pesananmenu.btnBayarNanti.setVisible(false);
                Pesananmenu.btnUpdate.setVisible(true);
                Pesananmenu.btn_simpan.setVisible(false);
                Pesananmenu.radioPembelianDP.setVisible(false);
                Pesananmenu.cekSelesai.setVisible(true);
                // Kode yang akan dijalankan jika double click dan checkbox tidak tercentang
            buylater();
            totalBiaya();
            Pesananmenu.txt_bayar.requestFocus(true);

            int totalBeli = Integer.parseInt(totbay);
            int bayarbone = Integer.parseInt(tunai);
            int utang = Integer.parseInt(kembalian);

            if (utang < 0) {
                int newTotalBeli = totalBeli - bayarbone;
                Pesananmenu.txt_totalbayar.setText(String.valueOf(newTotalBeli));
                Pesananmenu.lblTotalBayar.setText("TOTAL SISA BAYAR");
            } else {
                return;
            }

            Pesananmenu.btnUpdate.setVisible(true);
            Pesananmenu.btn_simpan.setVisible(false);
            Pesananmenu.btnPraBayar.setVisible(false);
            this.setVisible(false);

            for (int row = 0; row < Pesananmenu.jTable3.getRowCount(); row++) {
                String id = Pesananmenu.jTable3.getValueAt(row, 0).toString();
                if (id.equals(idpesan)) {
                    Pesananmenu.jTable3.setRowSelectionInterval(row, row);
                    break;
                }
            }
            } else if (evt.getClickCount() == 1 && !cekDoneElse.isSelected()) {
                txtIdPesananDP.setText(idpesan);
                txtIdPelangganDP.setText(idpelang);
                txtNamaPelangganDP.setText(nama);
                txtNoHpPelangganDP.setText(noHp);
                txtAlamatPelangganDP.setText(alamat);
                txtTotalBayarDP.setText(totbay);
                txtTunaiDP.setText(tunai);
                txtKembalianDP.setText(kembalian);
                btnUpdate.setVisible(true);
                btnSimpanPrabayar.setVisible(false);
            } else {
                return;
            }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cekDoneElseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekDoneElseActionPerformed
        if(cekDoneElse.isSelected()){
            prabayardone();
        }else{
            tampiljualdp();
        }
    }//GEN-LAST:event_cekDoneElseActionPerformed

    private void btnFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormatActionPerformed
        String idpesanan = txtIdPesananDP.getText();
        DefaultTableModel model = (DefaultTableModel) Pesananmenu.jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
        Pesananmenu.txt_totalbayar.setText("0");
        Pesananmenu.txt_tampil.setText("Rp0");
        Pesananmenu.btnUpdate.setVisible(false);
        Pesananmenu.btn_simpan.setVisible(true);
        Pesananmenu.jTable3.clearSelection();
        Pesananmenu.radioPembelianDP.setVisible(true);
        Pesananmenu.btnBayarNanti.setVisible(false);
        try {
                Connection c = koneksi.getKoneksi();
                String sql = "delete from pesanan where id_pesanan = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idpesanan);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Transaksi Dibatalkan");
                this.dispose();
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan");
            }
        
    }//GEN-LAST:event_btnFormatActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        bersih();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int klik = jTable1.getSelectedRow();
        if (klik == -1) {
            return;
        }
        String id = (String) model.getValueAt(klik, 1);
        String nama = txtNamaPelangganDP.getText();
        String noHp = txtNoHpPelangganDP.getText();
        String alamat = txtAlamatPelangganDP.getText();

        try {
            Connection c = koneksi.getKoneksi();
            String sql = "update pelanggan set namaPelanggan= ?, noHp = ?, alamat = ? where id_pelanggan = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, noHp);
            p.setString(3, alamat);
            p.setString(4, id);

            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
            this.btnUpdate.setVisible(false);
        } catch (Exception e) {
            System.out.println("update error");
        }finally{
            tampiljualdp();
            bersih();
            btnSimpanPrabayar.setVisible(true);
            btnUpdate.setVisible(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtNamaPelangganDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganDPActionPerformed
        txtNoHpPelangganDP.requestFocus(true);
    }//GEN-LAST:event_txtNamaPelangganDPActionPerformed

    private void txtNoHpPelangganDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpPelangganDPActionPerformed
        txtAlamatPelangganDP.requestFocus(true);
    }//GEN-LAST:event_txtNoHpPelangganDPActionPerformed

    private void txtCarimenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarimenuActionPerformed
        cariData();
    }//GEN-LAST:event_txtCarimenuActionPerformed

    private void txtCarimenuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarimenuKeyTyped
        cariData();
    }//GEN-LAST:event_txtCarimenuKeyTyped

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
            java.util.logging.Logger.getLogger(bayarmuka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bayarmuka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bayarmuka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bayarmuka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bayarmuka().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    public static javax.swing.JButton btnFormat;
    public static javax.swing.JButton btnRefresh;
    public static javax.swing.JButton btnSimpanPrabayar;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox cekDoneElse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom5;
    public static javax.swing.JTextField txtAlamatPelangganDP;
    private javax.swing.JTextField txtCarimenu;
    public static javax.swing.JTextField txtIdPelangganDP;
    public static javax.swing.JTextField txtIdPesananDP;
    public static javax.swing.JTextField txtKembalianDP;
    public static javax.swing.JTextField txtNamaPelangganDP;
    public static javax.swing.JTextField txtNoHpPelangganDP;
    public static javax.swing.JTextField txtTotalBayarDP;
    public static javax.swing.JTextField txtTunaiDP;
    // End of variables declaration//GEN-END:variables
}