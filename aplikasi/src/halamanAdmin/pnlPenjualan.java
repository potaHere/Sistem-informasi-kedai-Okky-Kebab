package halamanAdmin;
import static halamanKasir.Pesananmenu.cekAll;
import static halamanKasir.Pesananmenu.cekLate;
import static halamanKasir.Pesananmenu.cekSelesai;
import static halamanKasir.Pesananmenu.cekThen;
import static halamanKasir.Pesananmenu.jTable3;
import halamanKasir.bayarmuka;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import sambungan.koneksi;

public class pnlPenjualan extends javax.swing.JInternalFrame {
    PreparedStatement ps;
    ResultSet rs;
    private DefaultTableModel model;
    
    public void tampilPesanan(){
        title_tabel.setText("Semua penjualan");
        try{
            String tampil = "select id_pesanan, date_format(tanggal, '%d-%m-%Y %H-%i-%s') as tanggal, id_pelanggan, totalbeli, bayar, kembalian from pesanan;";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            rs = ps.executeQuery();
            
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            
            model.addColumn("ID Pesanan");
            model.addColumn("ID Pelanggan");
            model.addColumn("totalbeli");
            model.addColumn("bayar");
            model.addColumn("kembalian");
            model.addColumn("tanggal");
            
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            
            while (rs.next()){
                Object jual[] = {
                  rs.getString("id_pesanan"),
                  rs.getString("id_pelanggan"),
                  rs.getString("totalbeli"),
                  rs.getString("bayar"),
                  rs.getString("kembalian"),
                  rs.getString("tanggal"),
                };

                model.addRow(jual);
                jTable1.setModel(model);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error bosss");
        }
        txtDate1.setText("Tanggal");
        txtDate2.setText("Tanggal");
    }
    
    public void pesananhariini(){
        title_tabel.setText("Penjualan hari ini");
        try {
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String tampil = "select id_pesanan, date_format(tanggal, '%d-%m-%Y %H-%i-%s') as tanggal, id_pelanggan, totalbeli, bayar, kembalian from pesanan where date(tanggal) = ?";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, nowString);
            rs = ps.executeQuery();
            
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            model.addColumn("ID Pesanan");
            model.addColumn("ID Pelanggan");
            model.addColumn("Total Beli");
            model.addColumn("Bayar");
            model.addColumn("Kembalian");
            model.addColumn("Tanggal");
            jTable1.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable1.getWidth();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
            jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
            jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.25)); 
            }
        });
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                    rs.getString("tanggal"),
                };

                model.addRow(jual);
            }
            jTable1.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
    }
    
    public void menularis(){
        try {
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String laris = "select pd.namamenu, sum(pd.jumlah) as total_pembelian from pesanandetail pd inner join pesanan p on pd.id_pesanan = p.id_pesanan where date(p.tanggal) = ? group by pd.namamenu order by total_pembelian desc limit 1";
            Connection connection = koneksi.getKoneksi();

            PreparedStatement ps = connection.prepareStatement(laris);
            ps.setString(1, nowString);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String menuname = rs.getString("namamenu");
                int jumlahbl = rs.getInt("total_pembelian");
                txtMenularisnow.setText(menuname);
                txtJumlahlarisNow.setText(""+jumlahbl);
            } else {
                txtMenularisnow.setText("Empty");
                txtJumlahlarisNow.setText("Empty");
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari menu terlaris");
        }
    }
    
    public void pdphariini(){
        try {
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String hitung = "select sum(totalbeli) as pendapatan from pesanan where date(tanggal) = ?";
            Connection connection = koneksi.getKoneksi();

            PreparedStatement ps = connection.prepareStatement(hitung);
            ps.setString(1, nowString);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalPendapatan = rs.getInt("pendapatan");
                txt_omsettoday.setText("Rp" + Integer.toString(totalPendapatan));
            } else {
                txt_omsettoday.setText("Rp0");
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung pendapatan");
        }
    }
    
    public void caritanggal(){
        try{
            String startDate = txtDate1.getText();
            String endDate = txtDate2.getText();
            String caritgl = "select id_pesanan, date_format(tanggal, '%d-%m-%Y %H-%i-%s') as tanggal, id_pelanggan, totalbeli, bayar, kembalian from pesanan where date(tanggal) >= ? and date(tanggal) <= ?";
            ps = koneksi.getKoneksi().prepareStatement(caritgl);
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            rs = ps.executeQuery();
            
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            model.addColumn("ID Pesanan");
            model.addColumn("ID Pelanggan");
            model.addColumn("totalbeli");
            model.addColumn("bayar");
            model.addColumn("kembalian");
            model.addColumn("tanggal");
            jTable1.setModel(model);
            
            while (rs.next()){
               model.addRow(new Object[]  {
                  rs.getString("id_pesanan"),
                  rs.getString("id_pelanggan"),
                  rs.getString("totalbeli"),
                  rs.getString("bayar"),
                  rs.getString("kembalian"),
                  rs.getString("tanggal"),
                });
            }
        }catch(Exception e){
        System.out.println("Cari tanggal error");
        }
    }
    
    public void clearSelect(){
        cekBayarNanti.setEnabled(true);
        cekBayarNanti.setSelected(false);
        cekBesar.setEnabled(true);
        cekBesar.setSelected(false);
    }
    
    public pnlPenjualan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI pnl = (BasicInternalFrameUI)this.getUI();
        pnl.setNorthPane(null);
        
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
            jTable1.setModel(model);
            
//            model.addColumn("ID Pesanan");
//            model.addColumn("ID Pelanggan");
//            model.addColumn("totalbeli");
//            model.addColumn("bayar");
//            model.addColumn("kembalian");
//            model.addColumn("tanggal");
            
            jTable1.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable1.getWidth();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
            jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
            jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.25)); 
            }
        });
        
        pesananhariini();
        menularis();
        pdphariini();
        txtDate1.setVisible(false);
        txtDate2.setVisible(false);
        lbl_sampai.setVisible(false);
        btnCari.setVisible(false);
        btn_allsale.setVisible(true);
        btn_saleNow.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        penjualan = new javax.swing.JPanel();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtDate1 = new javax.swing.JTextField();
        lbl_sampai = new javax.swing.JLabel();
        txtDate2 = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        title_tabel = new javax.swing.JLabel();
        btn_allsale = new javax.swing.JButton();
        btn_saleNow = new javax.swing.JButton();
        panelcustom2 = new custom.panelcustom();
        txtBarcode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panelcustom4 = new custom.panelcustom();
        jLabel2 = new javax.swing.JLabel();
        lblPenjualan = new javax.swing.JLabel();
        txt_omsettoday = new javax.swing.JTextField();
        panelcustom5 = new custom.panelcustom();
        jLabel3 = new javax.swing.JLabel();
        lblMenuFav = new javax.swing.JLabel();
        txtMenularisnow = new javax.swing.JTextField();
        panelcustom6 = new custom.panelcustom();
        jLabel4 = new javax.swing.JLabel();
        lblJumlahLaris = new javax.swing.JLabel();
        txtJumlahlarisNow = new javax.swing.JTextField();
        panelcustom1 = new custom.panelcustom();
        cekBesar = new javax.swing.JCheckBox();
        cekBayarNanti = new javax.swing.JCheckBox();
        pnlPaginationShow = new custom.panelcustom();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        cmbJumlahBaris = new javax.swing.JComboBox<>();

        dateChooser1.setTextRefernce(txtDate1);

        dateChooser2.setTextRefernce(txtDate2);

        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        penjualan.setPreferredSize(new java.awt.Dimension(1540, 870));
        penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penjualanMouseClicked(evt);
            }
        });
        penjualan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA PENJUALAN");

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

        penjualan.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

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
        )

    );
    jTable1.setFocusable(false);
    jTable1.setRowHeight(30);
    jTable1.setSelectionBackground(new java.awt.Color(37, 150, 190));
    jScrollPane1.setViewportView(jTable1);

    penjualan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 950, 530));

    txtDate1.setEditable(false);
    txtDate1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
    txtDate1.setFocusable(false);
    penjualan.add(txtDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 140, 95, 29));
    txtDate1.getAccessibleContext().setAccessibleParent(this);

    lbl_sampai.setText("Sampai");
    penjualan.add(lbl_sampai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 145, -1, 20));

    txtDate2.setEditable(false);
    txtDate2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
    txtDate2.setFocusable(false);
    penjualan.add(txtDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 140, 95, 29));
    txtDate2.getAccessibleContext().setAccessibleParent(this);

    btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find_date 20px.png"))); // NOI18N
    btnCari.setText("Cari");
    btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnCari.setFocusable(false);
    btnCari.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCariActionPerformed(evt);
        }
    });
    penjualan.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 140, -1, 31));

    btnReload.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
    btnReload.setText("REFRESH");
    btnReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnReload.setFocusable(false);
    btnReload.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnReloadActionPerformed(evt);
        }
    });
    penjualan.add(btnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, 43));

    title_tabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    title_tabel.setText("text");
    penjualan.add(title_tabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

    btn_allsale.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
    btn_allsale.setText("Semua Penjualan");
    btn_allsale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btn_allsale.setFocusable(false);
    btn_allsale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btn_allsale.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_allsaleActionPerformed(evt);
        }
    });
    penjualan.add(btn_allsale, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 180, 30));

    btn_saleNow.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
    btn_saleNow.setText("Penjualan Hari Ini");
    btn_saleNow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btn_saleNow.setFocusable(false);
    btn_saleNow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btn_saleNow.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_saleNowActionPerformed(evt);
        }
    });
    penjualan.add(btn_saleNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 180, 30));

    panelcustom2.setBackground(new java.awt.Color(255, 255, 255));
    panelcustom2.setRoundBottomLeft(5);
    panelcustom2.setRoundTopLeft(5);
    panelcustom2.setRoundTopRight(5);
    panelcustom2.setRoundottomRight(5);
    panelcustom2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    txtBarcode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    txtBarcode.setBorder(null);
    txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            txtBarcodeKeyPressed(evt);
        }
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtBarcodeKeyTyped(evt);
        }
    });
    panelcustom2.add(txtBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 30));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-barcode-30.png"))); // NOI18N
    panelcustom2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 30, 30));

    penjualan.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 135, 246, 30));

    panelcustom4.setBackground(new java.awt.Color(255, 255, 255));
    panelcustom4.setRoundBottomLeft(12);
    panelcustom4.setRoundTopLeft(12);
    panelcustom4.setRoundTopRight(12);
    panelcustom4.setRoundottomRight(12);

    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-income-64.png"))); // NOI18N

    lblPenjualan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lblPenjualan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    lblPenjualan.setText("Pendapatan");

    txt_omsettoday.setEditable(false);
    txt_omsettoday.setBackground(new java.awt.Color(255, 255, 255));
    txt_omsettoday.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    txt_omsettoday.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    txt_omsettoday.setBorder(null);
    txt_omsettoday.setFocusable(false);

    javax.swing.GroupLayout panelcustom4Layout = new javax.swing.GroupLayout(panelcustom4);
    panelcustom4.setLayout(panelcustom4Layout);
    panelcustom4Layout.setHorizontalGroup(
        panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelcustom4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelcustom4Layout.createSequentialGroup()
                    .addComponent(txt_omsettoday, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    panelcustom4Layout.setVerticalGroup(
        panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(panelcustom4Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(lblPenjualan)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txt_omsettoday, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    penjualan.add(panelcustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 270, 90));

    panelcustom5.setBackground(new java.awt.Color(255, 255, 255));
    panelcustom5.setRoundBottomLeft(12);
    panelcustom5.setRoundTopLeft(12);
    panelcustom5.setRoundTopRight(12);
    panelcustom5.setRoundottomRight(12);

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-best-product-64.png"))); // NOI18N

    lblMenuFav.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lblMenuFav.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    lblMenuFav.setText("Menu Laris");

    txtMenularisnow.setEditable(false);
    txtMenularisnow.setBackground(new java.awt.Color(255, 255, 255));
    txtMenularisnow.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    txtMenularisnow.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    txtMenularisnow.setBorder(null);
    txtMenularisnow.setFocusable(false);

    javax.swing.GroupLayout panelcustom5Layout = new javax.swing.GroupLayout(panelcustom5);
    panelcustom5.setLayout(panelcustom5Layout);
    panelcustom5Layout.setHorizontalGroup(
        panelcustom5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelcustom5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelcustom5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMenuFav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelcustom5Layout.createSequentialGroup()
                    .addComponent(txtMenularisnow, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    panelcustom5Layout.setVerticalGroup(
        panelcustom5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(panelcustom5Layout.createSequentialGroup()
            .addGap(0, 6, Short.MAX_VALUE)
            .addComponent(lblMenuFav)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtMenularisnow, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    penjualan.add(panelcustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 270, 90));

    panelcustom6.setBackground(new java.awt.Color(255, 255, 255));
    panelcustom6.setRoundBottomLeft(12);
    panelcustom6.setRoundTopLeft(12);
    panelcustom6.setRoundTopRight(12);
    panelcustom6.setRoundottomRight(12);

    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-favorite-64.png"))); // NOI18N

    lblJumlahLaris.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lblJumlahLaris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    lblJumlahLaris.setText("Jumlah Menu Laris");

    txtJumlahlarisNow.setEditable(false);
    txtJumlahlarisNow.setBackground(new java.awt.Color(255, 255, 255));
    txtJumlahlarisNow.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
    txtJumlahlarisNow.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    txtJumlahlarisNow.setBorder(null);
    txtJumlahlarisNow.setFocusable(false);

    javax.swing.GroupLayout panelcustom6Layout = new javax.swing.GroupLayout(panelcustom6);
    panelcustom6.setLayout(panelcustom6Layout);
    panelcustom6Layout.setHorizontalGroup(
        panelcustom6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelcustom6Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelcustom6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblJumlahLaris, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelcustom6Layout.createSequentialGroup()
                    .addComponent(txtJumlahlarisNow, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    panelcustom6Layout.setVerticalGroup(
        panelcustom6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(panelcustom6Layout.createSequentialGroup()
            .addGap(0, 6, Short.MAX_VALUE)
            .addComponent(lblJumlahLaris)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtJumlahlarisNow, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    penjualan.add(panelcustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 270, 90));

    panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
    panelcustom1.setRoundBottomLeft(12);
    panelcustom1.setRoundTopLeft(12);
    panelcustom1.setRoundTopRight(12);
    panelcustom1.setRoundottomRight(12);

    cekBesar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    cekBesar.setText("Prabayar");
    cekBesar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cekBesarActionPerformed(evt);
        }
    });

    cekBayarNanti.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    cekBayarNanti.setText("Belum bayar");
    cekBayarNanti.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cekBayarNantiActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
    panelcustom1.setLayout(panelcustom1Layout);
    panelcustom1Layout.setHorizontalGroup(
        panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cekBayarNanti)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
            .addComponent(cekBesar)
            .addContainerGap())
    );
    panelcustom1Layout.setVerticalGroup(
        panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelcustom1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cekBesar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cekBayarNanti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    penjualan.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 730, 300, 50));

    pnlPaginationShow.setBackground(new java.awt.Color(255, 255, 255));
    pnlPaginationShow.setRoundBottomLeft(12);
    pnlPaginationShow.setRoundTopLeft(12);
    pnlPaginationShow.setRoundTopRight(12);
    pnlPaginationShow.setRoundottomRight(12);
    pnlPaginationShow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-left-35.png"))); // NOI18N
    btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnPrevActionPerformed(evt);
        }
    });
    pnlPaginationShow.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 9, 35, 35));

    btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-right-35.png"))); // NOI18N
    btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnNextActionPerformed(evt);
        }
    });
    pnlPaginationShow.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 9, 35, 35));

    btnLast.setText("Last");
    pnlPaginationShow.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 9, -1, 35));

    btnFirst.setText("First");
    pnlPaginationShow.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 9, -1, 35));

    cmbJumlahBaris.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    cmbJumlahBaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "25", "50", "100", "200", "500" }));
    pnlPaginationShow.add(cmbJumlahBaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 9, 70, 35));

    penjualan.add(pnlPaginationShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 730, 320, 50));

    getContentPane().add(penjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        caritanggal();
        btn_allsale.setVisible(true);
        btn_saleNow.setVisible(false);
        try {
            String tglawal = txtDate1.getText();
            String tglakhir = txtDate2.getText();
            String hitung = "select sum(totalbeli) as pendapatan from pesanan where date(tanggal) >=? and date(tanggal) <=?";
            ps = koneksi.getKoneksi().prepareStatement(hitung);
            ps.setString(1, tglawal);
            ps.setString(2, tglakhir);
            rs = ps.executeQuery();
            title_tabel.setText("Data penjualan " + tglawal + " sampai " + tglakhir);
            if (rs.next()) {
                int totalPendapatan = rs.getInt("pendapatan");
                txt_omsettoday.setText("Rp" + Integer.toString(totalPendapatan));
            }
            else {
                txt_omsettoday.setText("Maaf Terjadi Error");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hitung Pendapatan Error");
        }
        
        try {
            String tglawal = txtDate1.getText();
            String tglakhir = txtDate2.getText();
            String laris = "select pd.namamenu, sum(pd.jumlah) as total_pembelian from pesanandetail pd inner join pesanan p on pd.id_pesanan = p.id_pesanan where date(p.tanggal) between ? and ? group by pd.namamenu order by total_pembelian desc limit 1";
            ps = koneksi.getKoneksi().prepareStatement(laris);
            ps.setString(1, tglawal);
            ps.setString(2, tglakhir);
            rs = ps.executeQuery();

            if (rs.next()) {
                String menuname = rs.getString("namamenu");
                int jumlahbl = rs.getInt("total_pembelian");
                txtMenularisnow.setText(menuname);
                txtJumlahlarisNow.setText(""+ jumlahbl);
            } else {
                txtMenularisnow.setText("Empty");
                txtJumlahlarisNow.setText("Empty");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencari menu terlaris");
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        txtDate1.setVisible(false);
        txtDate2.setVisible(false);
        lbl_sampai.setVisible(false);
        btnCari.setVisible(false);
        btn_allsale.setVisible(true);
        pesananhariini();
        menularis();
        pdphariini();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btn_allsaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_allsaleActionPerformed
        tampilPesanan();
        clearSelect();
        txtDate1.setVisible(true);
        txtDate2.setVisible(true);
        lbl_sampai.setVisible(true);
        btnCari.setVisible(true);
        btn_saleNow.setVisible(true);
        btn_allsale.setVisible(false);
        
        try {
            String hitung = "select sum(totalbeli) as pendapatan from pesanan";
            Connection connection = koneksi.getKoneksi();

            PreparedStatement ps = connection.prepareStatement(hitung);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalPendapatan = rs.getInt("pendapatan");
                txt_omsettoday.setText("Rp" + Integer.toString(totalPendapatan));
            } else {
                txt_omsettoday.setText("Rp0");
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghitung pendapatan");
        }
        
        try{
            String laris = "select pd.namamenu, sum(pd.jumlah) as total_pembelian from pesanandetail pd group by pd.namamenu order by total_pembelian desc limit 1;";
            ps = koneksi.getKoneksi().prepareStatement(laris);
            rs = ps.executeQuery();
            if (rs.next()){
                 String menuname = rs.getString("namamenu");
                 int jumlahbl = rs.getInt("total_pembelian");
                txtMenularisnow.setText(menuname);
                txtJumlahlarisNow.setText(""+jumlahbl);
            }
            rs.close();
            ps.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_allsaleActionPerformed

    private void btn_saleNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saleNowActionPerformed
        txtDate1.setVisible(false);
        txtDate2.setVisible(false);
        lbl_sampai.setVisible(false);
        btnCari.setVisible(false);
        btn_saleNow.setVisible(false);
        btn_allsale.setVisible(true);
        clearSelect();
        pesananhariini();
        menularis();
        pdphariini();
    }//GEN-LAST:event_btn_saleNowActionPerformed

    private void penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penjualanMouseClicked
        jTable1.clearSelection();
    }//GEN-LAST:event_penjualanMouseClicked

    private void txtBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             try{
                String brcd = txtBarcode.getText();
                String tampil = "SELECT id_pesanan, DATE_FORMAT(tanggal, '%d-%m-%Y %H:%i:%s') AS tanggal, id_pelanggan, totalbeli, bayar, kembalian FROM pesanan WHERE id_pesanan = '" + brcd + "'";
                ps = koneksi.getKoneksi().prepareStatement(tampil);
                rs = ps.executeQuery();

                model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                return false;
                    }
                };

                model.addColumn("ID Pesanan");
                model.addColumn("ID Pelanggan");
                model.addColumn("totalbeli");
                model.addColumn("bayar");
                model.addColumn("kembalian");
                model.addColumn("tanggal");

                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                model.setRowCount(0);

                while (rs.next()){
                    Object jual[] = {
                      rs.getString("id_pesanan"),
                      rs.getString("id_pelanggan"),
                      rs.getString("totalbeli"),
                      rs.getString("bayar"),
                      rs.getString("kembalian"),
                      rs.getString("tanggal"),
                    };

                    model.addRow(jual);
                }
                    jTable1.setModel(model);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "error Min");
            }
        txtDate1.setText("Tanggal");
        txtDate2.setText("Tanggal");
    }
    }//GEN-LAST:event_txtBarcodeKeyPressed

    private void txtBarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyTyped
        if("".equals(txtBarcode.getText())){
            pesananhariini();
        }
    }//GEN-LAST:event_txtBarcodeKeyTyped

    private void cekBesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekBesarActionPerformed
        if(cekBesar.isSelected()){
            cekBesar.setEnabled(false);
            cekBayarNanti.setEnabled(true);
            cekBayarNanti.setSelected(false);
            
            bayarmuka muka = new bayarmuka();
            muka.setVisible(true);
            muka.btnFormat.setVisible(false);
            muka.btnRefresh.setVisible(true);
            try {
            PreparedStatement ps;
            ResultSet rs;

            String tampil = "select id_pesanan, date_format(tanggal, '%d-%m-%Y %H-%i-%s') as tanggal, id_pelanggan,  totalbeli, bayar, kembalian from pesanan where kembalian < 0";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            rs = ps.executeQuery();
            
            DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            model.addColumn("ID Pesanan");
            model.addColumn("ID Pelanggan");
            model.addColumn("totalbeli");
            model.addColumn("bayar");
            model.addColumn("kembalian");
            model.addColumn("tanggal");
            jTable1.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable1.getWidth();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                    rs.getString("tanggal"),
                };

                model.addRow(jual);
            }
            jTable1.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
            
        }else{
            return;
        }
    }//GEN-LAST:event_cekBesarActionPerformed

    private void cekBayarNantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekBayarNantiActionPerformed
        if(cekBayarNanti.isSelected()){
            cekBayarNanti.setEnabled(false);
            cekBesar.setSelected(false);
            cekBesar.setEnabled(true);
            try {
            PreparedStatement ps;
            ResultSet rs;
            String tampil = "select id_pesanan, date_format(tanggal, '%d-%m-%Y %H-%i-%s') as tanggal, id_pelanggan,  totalbeli, bayar, kembalian from pesanan where bayar IS NULL and kembalian IS NULL";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            rs = ps.executeQuery();
            
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            model.addColumn("ID Pesanan");
            model.addColumn("ID Pelanggan");
            model.addColumn("totalbeli");
            model.addColumn("bayar");
            model.addColumn("kembalian");
            model.addColumn("tanggal");
            jTable1.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable1.getWidth();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                    rs.getString("tanggal"),
                };

                model.addRow(jual);
            }
            jTable1.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
            
        }else{
            return;
        }
    }//GEN-LAST:event_cekBayarNantiActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btn_allsale;
    private javax.swing.JButton btn_saleNow;
    private javax.swing.JCheckBox cekBayarNanti;
    private javax.swing.JCheckBox cekBesar;
    private javax.swing.JComboBox<String> cmbJumlahBaris;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblJumlahLaris;
    private javax.swing.JLabel lblMenuFav;
    private javax.swing.JLabel lblPenjualan;
    private javax.swing.JLabel lbl_sampai;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom panelcustom4;
    private custom.panelcustom panelcustom5;
    private custom.panelcustom panelcustom6;
    private javax.swing.JPanel penjualan;
    private custom.panelcustom pnlPaginationShow;
    private javax.swing.JLabel title_tabel;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtDate1;
    private javax.swing.JTextField txtDate2;
    private javax.swing.JTextField txtJumlahlarisNow;
    private javax.swing.JTextField txtMenularisnow;
    private javax.swing.JTextField txt_omsettoday;
    // End of variables declaration//GEN-END:variables
}