package halamanKasir;
import java.awt.Font;
import sambungan.koneksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class Pesananmenu extends javax.swing.JFrame {
    
    int xx, xy;
    String Tanggal;
    public static DefaultTableModel model;
    private DefaultTableModel modelmenu;
    private DefaultTableModel modelpesannow;
    private DefaultTableModel tabel;
    private Timer timer;
    private Timer waktulokal;
    
    
    public void tampilpesan(){
        
       try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from menu";
            ResultSet r = s.executeQuery(sql);
            
            modelmenu.getDataVector().removeAllElements();
            modelmenu.fireTableDataChanged();
            modelmenu.setRowCount(0);
            
            while (r.next()) {                
                Object[] o = new Object[3];
                o [0] = r.getString("id_menu");
                o [1] = r.getString("nama_menu");
                o [2] = r.getString("harga_menu");
                
                modelmenu.addRow(o);
                jTable2.setModel(modelmenu);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void pesananselesai(){
        try {
            PreparedStatement ps;
            ResultSet rs;
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String tampil = "SELECT id_pesanan, id_pelanggan, totalbeli, bayar, kembalian FROM pesanan WHERE kembalian >= 0 and kembalian IS NOT NULL AND DATE(tanggal) = ?";

            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, nowString);
            rs = ps.executeQuery();
            
            modelpesannow = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            modelpesannow.addColumn("ID Pesanan");
            modelpesannow.addColumn("ID Pelanggan");
            modelpesannow.addColumn("Total Beli");
            modelpesannow.addColumn("Bayar");
            modelpesannow.addColumn("Kembalian");
            jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable3.getWidth();
            jTable3.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable3.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        modelpesannow.getDataVector().removeAllElements();
        modelpesannow.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                };

                modelpesannow.addRow(jual);
            }
            jTable3.setModel(modelpesannow);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
    }
    
    public void pesananhariini(){
        try {
            PreparedStatement ps;
            ResultSet rs;
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String tampil = "SELECT id_pesanan, id_pelanggan, totalbeli, bayar, kembalian FROM pesanan WHERE kembalian <= 0 or kembalian IS NULL or DATE(tanggal) = ?";

            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, nowString);
            rs = ps.executeQuery();
            
            modelpesannow = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            modelpesannow.addColumn("ID Pesanan");
            modelpesannow.addColumn("ID Pelanggan");
            modelpesannow.addColumn("Total Beli");
            modelpesannow.addColumn("Bayar");
            modelpesannow.addColumn("Kembalian");
            jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable3.getWidth();
            jTable3.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable3.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        modelpesannow.getDataVector().removeAllElements();
        modelpesannow.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                };

                modelpesannow.addRow(jual);
            }
            jTable3.setModel(modelpesannow);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
    }
    
    public void buylater(){
        try {
            int i = jTable3.getSelectedRow();
            String idPesanan = jTable3.getValueAt(i, 0).toString();
            PreparedStatement ps;
            ResultSet rs;

            String tampil = "select * from pesanandetail where id_pesanan = ?";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, idPesanan);
            rs = ps.executeQuery();
            
            model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
            model.addColumn("ID Pesanan");
            model.addColumn("ID Menu");
            model.addColumn("Nama Menu");
            model.addColumn("Jumlah");
            model.addColumn("Harga");
            model.addColumn("Total");

            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_menu"),
                    rs.getString("namamenu"),
                    rs.getString("jumlah"),
                    rs.getString("harga"),
                    rs.getString("total"),
                };

                model.addRow(jual);
            }
            jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable1.getWidth();
            jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
            jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.25)); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.01)); 
            jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
            jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.15)); 
            }
        });
            jTable1.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
    }
    
        public void cari(){
        tabel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
        
        tabel.addColumn("ID Menu");
        tabel.addColumn("Nama Menu");
        tabel.addColumn("Harga ");
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "select * from menu "
            + "where id_menu like '%" + txtCarimenu.getText() + "%' or nama_menu like '%" + txtCarimenu.getText() + "%' or harga_menu like '%" + txtCarimenu.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                });
            }
            jTable2.setModel(tabel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        public void caridata(){
        modelpesannow = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };
        
        modelpesannow.addColumn("ID Pesanan");
        modelpesannow.addColumn("ID Pelanggan");
        modelpesannow.addColumn("Total Beli");
        modelpesannow.addColumn("Bayar");
        modelpesannow.addColumn("Kembalian");
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "select id_pesanan, id_pelanggan, totalbeli, bayar, kembalian from pesanan where id_pesanan like '%" + test.getText() + "%' or id_pelanggan like '%" + test.getText() + "%' or totalbeli like '%" + test.getText() + "%' or bayar like '%" + test.getText() + "%' or kembalian like '%" + test.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
                modelpesannow.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                });
            }
            jTable3.setModel(modelpesannow);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void clearm(){
        txt_idmenu.setText("");
        txt_namamenu.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
        btn_delete.setEnabled(false);
        txtCarimenu.setText("");
    }
    
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txt_totalbayar.setText(String.valueOf(totalBiaya));
        txt_tampil.setText("Rp"+ totalBiaya);
    }
    
    private void autonumber(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "select * from pesanan order by id_pesanan desc";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String idpesanan = r.getString("id_pesanan").substring(2);
                String TR = "" +(Integer.parseInt(idpesanan)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txt_idpesanan.setText("TR" + Nol + TR);
                txt_idpelanggan.setText("PL" + Nol + TR);
            } else {
                txt_idpesanan.setText("TR0001");
                txt_idpelanggan.setText("PL0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void loadData(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{
            txt_idpesanan.getText(),
            txt_idmenu.getText(),
            txt_namamenu.getText(),
            txt_jumlah.getText(),
            txt_harga.getText(),
            txt_totalbayar.getText(),
            txt_bayar.getText(),
            txt_kembalian.getText()
        });
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txt_idpesanan.setText("");
        txt_idmenu.setText("");
        txt_namamenu.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
        autonumber();
    }
    
    public void clear(){
//        txt_idpelanggan.setText("");
        txt_totalbayar.setText("0");
        txt_bayar.setText("");
        txt_kembalian.setText("");
        txt_tampil.setText("Rp0");
    }
    
    public void clear2(){
        txt_idmenu.setText("");
        txt_namamenu.setText("");
        txt_harga.setText("");
        txt_jumlah.setText("");
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txt_jumlah.getText());
        harga = Integer.valueOf(txt_harga.getText());
        total = jumlah * harga;
        
        txt_totalbayar.setText(String.valueOf(total));
        
        loadData();
        totalBiaya();
        clear2();
        txt_idmenu.requestFocus();
    }

    public Pesananmenu() {
        initComponents();
        cekAll.setVisible(false);
        cekSelesai.setVisible(false);
        cekThen.setVisible(false);
        cekLate.setVisible(false);
        btnUpdate.setVisible(false);
        btnPraBayar.setVisible(false);
        panelcustom6.setVisible(false);
        btnRefresh.setVisible(false);
        logoutpsn.setVisible(false);
        namaUser.setVisible(false);
        txt_idmenu.setEditable(false);
        txt_idmenu.requestFocus(false);
        model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        modelmenu = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
            }
        };
        
        btn_delete.setEnabled(false);
        txt_tampil.setEditable(false);
        txt_kembalian.setEditable(false);
        
        jTable1.setModel(model);
        
        model.addColumn("ID Pesanan");
        model.addColumn("ID Menu");
        model.addColumn("Nama Menu");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");

        int totalWidth = jTable1.getWidth();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.1));
        jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.25)); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.01)); 
        jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
        jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.15)); 

        utama();
        txt_totalbayar.setText("0");
        txt_bayar.setText("");
        txt_tampil.setText("Rp0");
        txt_kembalian.setText("");
        txt_idpelanggan.requestFocus();
        
        jTable2.setModel(modelmenu);
        
        modelmenu.addColumn("ID Menu");
        modelmenu.addColumn("Nama Menu");
        modelmenu.addColumn("Harga ");
        
        tampilpesan();
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
                txtDatetime.setText(s.format(date));
            }
        });
        timer.start();
        
        waktulokal = new Timer(1000, new ActionListener() {
            Locale locale = new Locale("id", "ID");
            @Override
            public void actionPerformed(ActionEvent e) {
                Date tglnow = new Date();
                SimpleDateFormat hari = new SimpleDateFormat("EEEE, " ,locale);
                SimpleDateFormat tgl = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss");
                txtHariID.setText(hari.format(tglnow));
                txtTanggalID.setText(tgl.format(tglnow));
                txtJamID.setText(jam.format(tglnow));
            }
        });
        waktulokal.start();
        btnPesanan.setVisible(true);
        btnMenu.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgkasirpsn = new javax.swing.JPanel();
        topbarkasipsn = new custom.panelcustom();
        logopsn = new javax.swing.JLabel();
        titletoko = new javax.swing.JLabel();
        txtHariID = new javax.swing.JLabel();
        txtTanggalID = new javax.swing.JLabel();
        txtJamID = new javax.swing.JLabel();
        xypress = new javax.swing.JPanel();
        btnExits = new javax.swing.JLabel();
        btnMax = new javax.swing.JLabel();
        btnMinim = new javax.swing.JLabel();
        namaUser = new javax.swing.JLabel();
        txtDatetime = new javax.swing.JLabel();
        panelcustom1 = new custom.panelcustom();
        jLabel1 = new javax.swing.JLabel();
        txt_idpesanan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_idpelanggan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_tampil = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        panelcustom3 = new custom.panelcustom();
        jLabel11 = new javax.swing.JLabel();
        txt_totalbayar = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        lblTotalBayar = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelcustom4 = new custom.panelcustom();
        jLabel4 = new javax.swing.JLabel();
        txt_idmenu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namamenu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnbatal = new javax.swing.JButton();
        panelcustom2 = new custom.panelcustom();
        logoutpsn = new javax.swing.JLabel();
        panelcustom5 = new custom.panelcustom();
        txtCarimenu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnPesanan = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnBayarNanti = new javax.swing.JButton();
        radioPembelianDP = new javax.swing.JRadioButton();
        btnUpdate = new javax.swing.JButton();
        panelcustom6 = new custom.panelcustom();
        test = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        cekLate = new javax.swing.JCheckBox();
        cekAll = new javax.swing.JCheckBox();
        cekThen = new javax.swing.JCheckBox();
        btnPraBayar = new javax.swing.JButton();
        cekSelesai = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/ikon project kedai_okky.png")).getImage());
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgkasirpsn.setPreferredSize(new java.awt.Dimension(1920, 1080));
        bgkasirpsn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgkasirpsnMouseClicked(evt);
            }
        });
        bgkasirpsn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topbarkasipsn.setBackground(new java.awt.Color(217, 24, 40));
        topbarkasipsn.setPreferredSize(new java.awt.Dimension(1880, 100));
        topbarkasipsn.setRoundBottomLeft(12);
        topbarkasipsn.setRoundTopLeft(12);
        topbarkasipsn.setRoundTopRight(12);
        topbarkasipsn.setRoundottomRight(12);

        logopsn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo revisi  height 88.png"))); // NOI18N

        titletoko.setFont(new java.awt.Font("STHupo", 0, 36)); // NOI18N
        titletoko.setForeground(new java.awt.Color(255, 255, 255));
        titletoko.setText("OKKY HOME");

        txtHariID.setFont(new java.awt.Font("STHupo", 1, 36)); // NOI18N
        txtHariID.setForeground(new java.awt.Color(255, 255, 255));
        txtHariID.setText("Hari");

        txtTanggalID.setFont(new java.awt.Font("STHupo", 1, 36)); // NOI18N
        txtTanggalID.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggalID.setText("Tanggal");

        txtJamID.setFont(new java.awt.Font("STHupo", 1, 21)); // NOI18N
        txtJamID.setForeground(new java.awt.Color(255, 255, 255));
        txtJamID.setText("Waktu");

        javax.swing.GroupLayout topbarkasipsnLayout = new javax.swing.GroupLayout(topbarkasipsn);
        topbarkasipsn.setLayout(topbarkasipsnLayout);
        topbarkasipsnLayout.setHorizontalGroup(
            topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarkasipsnLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(logopsn)
                .addGap(18, 18, 18)
                .addComponent(titletoko)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1283, Short.MAX_VALUE)
                .addGroup(topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topbarkasipsnLayout.createSequentialGroup()
                        .addComponent(txtHariID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTanggalID))
                    .addComponent(txtJamID, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        topbarkasipsnLayout.setVerticalGroup(
            topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logopsn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topbarkasipsnLayout.createSequentialGroup()
                .addGroup(topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topbarkasipsnLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHariID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTanggalID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJamID))
                    .addGroup(topbarkasipsnLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(titletoko, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bgkasirpsn.add(topbarkasipsn, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 42, 1870, 100));

        xypress.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                xypressMouseDragged(evt);
            }
        });
        xypress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xypressMousePressed(evt);
            }
        });

        btnExits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/closeWindows.png"))); // NOI18N
        btnExits.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitsMouseClicked(evt);
            }
        });

        btnMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/maximizeWindows.png"))); // NOI18N
        btnMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMaxMouseClicked(evt);
            }
        });

        btnMinim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MinimizeWindows.png"))); // NOI18N
        btnMinim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimMouseClicked(evt);
            }
        });

        namaUser.setFont(new java.awt.Font("Segoe UI", 0, 7)); // NOI18N
        namaUser.setText("user");

        txtDatetime.setFont(new java.awt.Font("Segoe UI", 0, 7)); // NOI18N
        txtDatetime.setText("jLabel2");

        javax.swing.GroupLayout xypressLayout = new javax.swing.GroupLayout(xypress);
        xypress.setLayout(xypressLayout);
        xypressLayout.setHorizontalGroup(
            xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, xypressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDatetime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1750, Short.MAX_VALUE)
                .addComponent(btnMinim)
                .addGap(18, 18, 18)
                .addComponent(btnMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExits)
                .addContainerGap())
        );
        xypressLayout.setVerticalGroup(
            xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(xypressLayout.createSequentialGroup()
                .addGroup(xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinim)
                    .addComponent(btnMax)
                    .addComponent(btnExits)
                    .addGroup(xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(namaUser)
                        .addComponent(txtDatetime)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bgkasirpsn.add(xypress, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 28));

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setPreferredSize(new java.awt.Dimension(340, 90));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel1.setText("ID_PESANAN");
        panelcustom1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 18, -1, -1));

        txt_idpesanan.setEnabled(false);
        panelcustom1.add(txt_idpesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 15, 172, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setText("ID_PELANGGAN");
        panelcustom1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 58, -1, -1));

        txt_idpelanggan.setEnabled(false);
        panelcustom1.add(txt_idpelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 55, 172, -1));

        bgkasirpsn.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 340, 90));

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
        jScrollPane2.setViewportView(jTable1);

        bgkasirpsn.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(665, 402, 1150, 361));

        txt_tampil.setEditable(false);
        txt_tampil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_tampil.setFocusable(false);
        bgkasirpsn.add(txt_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 790, 212, 46));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("TOTAL");
        bgkasirpsn.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 800, -1, -1));

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("PEMESANAN MAKANAN");

        javax.swing.GroupLayout panelcustom3Layout = new javax.swing.GroupLayout(panelcustom3);
        panelcustom3.setLayout(panelcustom3Layout);
        panelcustom3Layout.setHorizontalGroup(
            panelcustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelcustom3Layout.setVerticalGroup(
            panelcustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18))
        );

        bgkasirpsn.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, -1, -1));

        txt_totalbayar.setEditable(false);
        txt_totalbayar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_totalbayar.setFocusable(false);
        bgkasirpsn.add(txt_totalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 790, 220, 40));

        txt_bayar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        bgkasirpsn.add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 850, 220, 40));

        txt_kembalian.setEditable(false);
        txt_kembalian.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_kembalian.setFocusable(false);
        bgkasirpsn.add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 910, 220, 40));

        lblTotalBayar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lblTotalBayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalBayar.setText("TOTAL BAYAR");
        bgkasirpsn.add(lblTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1361, 800, 200, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel13.setText("TUNAI");
        bgkasirpsn.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 860, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel14.setText("KEMBALIAN");
        bgkasirpsn.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 920, -1, -1));

        panelcustom4.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom4.setPreferredSize(new java.awt.Dimension(535, 90));
        panelcustom4.setRoundBottomLeft(12);
        panelcustom4.setRoundTopLeft(12);
        panelcustom4.setRoundTopRight(12);
        panelcustom4.setRoundottomRight(12);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ID_MENU");

        txt_idmenu.setEditable(false);
        txt_idmenu.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NAMA MENU");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HARGA");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("JUMLAH");

        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcustom4Layout = new javax.swing.GroupLayout(panelcustom4);
        panelcustom4.setLayout(panelcustom4Layout);
        panelcustom4Layout.setHorizontalGroup(
            panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_idmenu)
                    .addGroup(panelcustom4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txt_namamenu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelcustom4Layout.setVerticalGroup(
            panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_idmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelcustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_namamenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bgkasirpsn.add(panelcustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 290, -1, 90));

        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-40.png"))); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 990, -1, 70));

        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add 24px.png"))); // NOI18N
        btn_tambah.setText("TAMBAH");
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 290, -1, 90));

        btn_delete.setBackground(new java.awt.Color(217, 24, 40));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ikon_sampah24px.png"))); // NOI18N
        btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1760, 340, 40, 40));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.setFocusable(false);
        jTable2.setRowHeight(30);
        jTable2.setSelectionBackground(new java.awt.Color(37, 150, 190));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        bgkasirpsn.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 358, 525, 450));

        btnbatal.setBackground(new java.awt.Color(255, 255, 255));
        btnbatal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 20px.png"))); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbatal.setFocusable(false);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 40));

        panelcustom2.setBackground(new java.awt.Color(217, 24, 40));
        panelcustom2.setRoundBottomLeft(12);
        panelcustom2.setRoundTopLeft(12);
        panelcustom2.setRoundTopRight(12);
        panelcustom2.setRoundottomRight(12);

        logoutpsn.setFont(new java.awt.Font("STHupo", 0, 18)); // NOI18N
        logoutpsn.setForeground(new java.awt.Color(255, 255, 255));
        logoutpsn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout-46.png"))); // NOI18N
        logoutpsn.setText("LOG OUT");
        logoutpsn.setAlignmentY(0.0F);
        logoutpsn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutpsn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutpsnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelcustom2Layout = new javax.swing.GroupLayout(panelcustom2);
        panelcustom2.setLayout(panelcustom2Layout);
        panelcustom2Layout.setHorizontalGroup(
            panelcustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutpsn, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        panelcustom2Layout.setVerticalGroup(
            panelcustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoutpsn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        bgkasirpsn.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1005, 140, 50));

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-30.png"))); // NOI18N
        panelcustom5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 40));

        bgkasirpsn.add(panelcustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 300, 40));

        jTable3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.setFocusable(false);
        jTable3.setRowHeight(30);
        jTable3.setSelectionBackground(new java.awt.Color(37, 150, 190));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        bgkasirpsn.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 358, 525, 450));

        btnPesanan.setBackground(new java.awt.Color(255, 255, 255));
        btnPesanan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-cart-30.png"))); // NOI18N
        btnPesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPesanan.setFocusable(false);
        btnPesanan.setPreferredSize(new java.awt.Dimension(42, 42));
        btnPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesananActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnPesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 820, -1, -1));

        btnMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-hamburger-30.png"))); // NOI18N
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setFocusable(false);
        btnMenu.setPreferredSize(new java.awt.Dimension(42, 42));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 820, -1, -1));

        btnBayarNanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-procurement-30.png"))); // NOI18N
        btnBayarNanti.setPreferredSize(new java.awt.Dimension(46, 46));
        btnBayarNanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarNantiActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnBayarNanti, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 790, -1, -1));

        radioPembelianDP.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        radioPembelianDP.setText("Pembelian PraBayar");
        radioPembelianDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPembelianDPActionPerformed(evt);
            }
        });
        bgkasirpsn.add(radioPembelianDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 960, -1, -1));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-40.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1610, 990, -1, 70));

        panelcustom6.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom6.setRoundBottomLeft(5);
        panelcustom6.setRoundTopLeft(5);
        panelcustom6.setRoundTopRight(5);
        panelcustom6.setRoundottomRight(5);
        panelcustom6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        test.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        test.setBorder(null);
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });
        test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                testKeyTyped(evt);
            }
        });
        panelcustom6.add(test, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 223, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-30.png"))); // NOI18N
        panelcustom6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 40));

        bgkasirpsn.add(panelcustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 300, 40));

        btnRefresh.setBackground(new java.awt.Color(255, 255, 255));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 20px.png"))); // NOI18N
        btnRefresh.setText("REFRESH");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setFocusable(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 40));

        cekLate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cekLate.setText("prabayar");
        cekLate.setRequestFocusEnabled(false);
        cekLate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekLateActionPerformed(evt);
            }
        });
        bgkasirpsn.add(cekLate, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 820, -1, -1));

        cekAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cekAll.setText("semua");
        cekAll.setRequestFocusEnabled(false);
        cekAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekAllActionPerformed(evt);
            }
        });
        bgkasirpsn.add(cekAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 820, -1, -1));

        cekThen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cekThen.setText("bayar nanti");
        cekThen.setRequestFocusEnabled(false);
        cekThen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekThenActionPerformed(evt);
            }
        });
        bgkasirpsn.add(cekThen, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 820, -1, -1));

        btnPraBayar.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btnPraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-40.png"))); // NOI18N
        btnPraBayar.setText("PRABAYAR");
        btnPraBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPraBayarActionPerformed(evt);
            }
        });
        bgkasirpsn.add(btnPraBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 990, -1, 70));

        cekSelesai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cekSelesai.setText("bayar selesai");
        cekSelesai.setRequestFocusEnabled(false);
        cekSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSelesaiActionPerformed(evt);
            }
        });
        bgkasirpsn.add(cekSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 820, -1, -1));

        getContentPane().add(bgkasirpsn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitsMouseClicked
        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin akan keluar?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        if (pernyataan == JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_btnExitsMouseClicked

    private void btnMinimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimMouseClicked
        this.setExtendedState(Pesananmenu.ICONIFIED);
    }//GEN-LAST:event_btnMinimMouseClicked

    private void btnMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaxMouseClicked
         if(this.getExtendedState()!=Pesananmenu.MAXIMIZED_BOTH){
            this.setExtendedState(Pesananmenu.MAXIMIZED_BOTH);
        }else{
            this.setExtendedState(Pesananmenu.NORMAL);
    }//GEN-LAST:event_btnMaxMouseClicked
    }
    private void xypressMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xypressMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_xypressMousePressed

    private void xypressMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xypressMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx,y - xy);
    }//GEN-LAST:event_xypressMouseDragged

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        String textjumlah = txt_jumlah.getText();
        if (textjumlah.equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah terlebih dahulu!");
        }else {
        try {
            int jumlahint = Integer.parseInt(textjumlah);

            if (jumlahint < 1) {
                JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0");
                txt_jumlah.setText("");
                txt_jumlah.requestFocus(true);
            } else {
                tambahTransaksi();
                txt_bayar.requestFocus(true);
            }
        }   
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.");
            txt_jumlah.setText("");
    }
        }
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String idMenu = txt_idmenu.getText();
        String namaMenu = txt_namamenu.getText();
        String hargaMenu = txt_harga.getText();
        String textjumlah = txt_jumlah.getText();
        Font font = new Font("Arial", Font.BOLD, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke");

        if (idMenu.isEmpty() || namaMenu.isEmpty() || hargaMenu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih menu terlebih dahulu!");
        } else if (textjumlah.equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah terlebih dahulu!");
        } else {
            try {
                int jumlahint = Integer.parseInt(textjumlah);

                if (jumlahint < 1) {
                    JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0");
                    txt_jumlah.setText("");
                    txt_jumlah.requestFocus(true);
                } else {
                    tambahTransaksi();
                    txt_bayar.requestFocus(true);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.");
                txt_jumlah.setText("");
            }
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        
        model.removeRow(row);
        totalBiaya();
        txt_bayar.setText("");
        txt_kembalian.setText("");
        btn_delete.setEnabled(false);
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String idpesanan = txt_idpesanan.getText();
        String tanggal = txtDatetime.getText();
        String idpelanggan = txt_idpelanggan.getText();
        String total = txt_totalbayar.getText();
        String bayar = txt_bayar.getText();
        String kembalian = txt_kembalian.getText();
        
        
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke!");
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,"Belum ada pesanan");
            txt_bayar.requestFocus(true);
            return;
            }
        else if(txt_bayar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Masukkan jumlah pembayaran");
            txt_bayar.requestFocus(true);
            return;
        }
        else if(txt_kembalian.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter total bayar untuk melihat kembalian");
            txt_bayar.requestFocus(true);
            return;
        }
        else {
                
        try {
            int bone = Integer.parseInt(txt_kembalian.getText());
        if(bone < 0){
            JOptionPane.showMessageDialog(null, "Masuk ke bayar(DP)");
            
            int baris = jTable1.getRowCount();
            Connection c = koneksi.getKoneksi();
            
            String sql = "insert into pesanan values (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idpesanan);
            p.setString(2, tanggal);
            p.setString(3, idpelanggan);
            p.setString(4, total);
            p.setString(5, bayar);
            p.setString(6, kembalian);
            p.executeUpdate();
            p.close();
            for (int i = 0; i < baris; i++) {
                String sql2 = "insert into pesanandetail(id_pesanan, id_menu, namamenu, jumlah, harga, total) VALUES('"
                        + jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2) 
                        +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4) +"','"+ jTable1.getValueAt(i, 5) 
                        +"')";
                PreparedStatement z = c.prepareStatement(sql2);
                z.executeUpdate();
                z.close();
            cekLateActionPerformed(evt);
            }
            jScrollPane1.setVisible(false);
            jScrollPane3.setVisible(true);
            cekAll.setVisible(true);
            cekThen.setVisible(true);
            cekLate.setVisible(true);
            cekLate.setSelected(true);
            cekLate.setEnabled(false);
            btnMenu.setVisible(true);
            btnPesanan.setVisible(false);
        }else {
            Font pont = new Font("Arial", Font.PLAIN, 23);
            UIManager.put("OptionPane.messageFont", pont);
            UIManager.put("OptionPane.buttonFont", pont);

            Object[] options = { "Simpan", "Cetak Struk" };

            int option = JOptionPane.showOptionDialog(null, "Pembelian Berhasil", "Konfirmasi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (option == JOptionPane.CLOSED_OPTION) {
                return; 
            }
            int baris = jTable1.getRowCount();
            Connection c = koneksi.getKoneksi();
            
            String sql = "insert into pesanan values (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idpesanan);
            p.setString(2, tanggal);
            p.setString(3, idpelanggan);
            p.setString(4, total);
            p.setString(5, bayar);
            p.setString(6, kembalian);
            p.executeUpdate();
            p.close();
            for (int i = 0; i < baris; i++) {
                String sql2 = "insert into pesanandetail(id_pesanan, id_menu, namamenu, jumlah, harga, total) VALUES('"
                        + jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2) 
                        +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4) +"','"+ jTable1.getValueAt(i, 5) 
                        +"')";
                PreparedStatement z = c.prepareStatement(sql2);
                z.executeUpdate();
                z.close();
            }
            
            if (option == 1) {
                // Logika untuk mencetak struk
                try {
                String report = "C:\\Users\\A S U S\\Documents\\NetBeansProjects\\project_s2\\src\\halamankasir\\report1.jrxml";
                HashMap param = new HashMap();
                param.put("invo", txt_idpesanan.getText());

                JasperReport JRpt = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(JRpt, param, sambungan.koneksi.getKoneksi());
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                 System.out.println(e.getMessage());
            }
    }
        }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
//        pesananhariini();
        clear();
        utama();
        autonumber();
        kosong();
        radioPembelianDP.setSelected(false);
        txt_tampil.setText("Rp0");
//        jScrollPane3.setVisible(false);
//        jScrollPane1.setVisible(true);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void logoutpsnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutpsnMouseClicked
        LocalDate now = LocalDate.now();
        String nowString = now.toString();
        int pernyataan = JOptionPane.showConfirmDialog(null, "Anda yakin akan logout?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(pernyataan == JOptionPane.OK_OPTION){
            try {
                String user = namaUser.getText();
                String tgluser = txtDatetime.getText();
                
                Connection c = koneksi.getKoneksi();
                String sql = "update riwayat_login set waktu_logout = ? where username = ? and date(waktulogin) = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, tgluser);
                p.setString(2, user);
                p.setString(3, nowString);
                
                p.executeUpdate();
                p.close();
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally {
                login.Login out = new login.Login();
                out.setVisible(true);
                this.setVisible(false);
            }
        }
        if (pernyataan == JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_logoutpsnMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        btn_delete.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        int total, bayar, kembalian;
        total = Integer.valueOf(txt_totalbayar.getText());

        String bayarText = txt_bayar.getText();
        if (bayarText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Masukkan jumlah pembayaran");
        } else {
            try {
                bayar = Integer.valueOf(bayarText);
                if(radioPembelianDP.isSelected()){
                    kembalian = bayar - total;
                    txt_kembalian.setText(String.valueOf(kembalian));
                }
                else if (total > bayar) {
                    JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
                } else {
                    kembalian = bayar - total;
                    txt_kembalian.setText(String.valueOf(kembalian));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Jumlah pembayaran tidak valid");
            }
        }
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void bgkasirpsnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgkasirpsnMouseClicked
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
    }//GEN-LAST:event_bgkasirpsnMouseClicked

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        clearm();
        tampilpesan();
        btnPesanan.setVisible(true);
        btnMenu.setVisible(false);
        jScrollPane1.setVisible(true);
        jScrollPane3.setVisible(false);
    }//GEN-LAST:event_btnbatalActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int i = jTable2.getSelectedRow();

        String id = jTable2.getValueAt(i, 0).toString();
        String nama = jTable2.getValueAt(i, 1).toString();
        String harga = jTable2.getValueAt(i, 2).toString();

        txt_idmenu.setText(id);
        txt_namamenu.setText(nama);
        txt_harga.setText(harga);
        txt_jumlah.requestFocus(true);
        btnUpdate.setVisible(false);
        btn_simpan.setVisible(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtCarimenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarimenuActionPerformed
        cari();
    }//GEN-LAST:event_txtCarimenuActionPerformed

    private void txtCarimenuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarimenuKeyTyped
        cari();
    }//GEN-LAST:event_txtCarimenuKeyTyped

    private void btnPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesananActionPerformed
        jScrollPane1.setVisible(false);
        jScrollPane3.setVisible(true);
        pesananhariini();
        btnRefresh.setVisible(true);
        btnbatal.setVisible(false);
        panelcustom6.setVisible(true);
        panelcustom5.setVisible(false);
        test.requestFocus(true);
        btnPesanan.setVisible(false);
        btnMenu.setVisible(true);
        btnBayarNanti.setVisible(false);
        cekAll.setVisible(true);
        cekAll.setSelected(true);
        cekLate.setSelected(false);
        cekThen.setSelected(false);
        cekAll.setEnabled(false);
        cekThen.setEnabled(true);
        cekLate.setEnabled(true);
        cekThen.setVisible(true);
        cekLate.setVisible(true);
        cekSelesai.setVisible(true);
        cekSelesai.setEnabled(true);
        cekSelesai.setSelected(false);
    }//GEN-LAST:event_btnPesananActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        radioPembelianDP.setVisible(true);
        jScrollPane3.setVisible(false);
        jScrollPane1.setVisible(true);
        tampilpesan();
        btnRefresh.setVisible(false);
        btnbatal.setVisible(true);
        btnUpdate.setVisible(false);
        btn_simpan.setVisible(true);
        panelcustom6.setVisible(false);
        panelcustom5.setVisible(true);
        txtCarimenu.requestFocus(true);
        btnMenu.setVisible(false);
        btnPesanan.setVisible(true);
        btnBayarNanti.setVisible(true);
        cekAll.setVisible(false);
        cekThen.setVisible(false);
        cekLate.setVisible(false);
        cekSelesai.setVisible(false);
    }//GEN-LAST:event_btnMenuActionPerformed
    
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
    if (evt.getClickCount() == 2) { 
    int klik = jTable3.getSelectedRow();
    String id = jTable3.getValueAt(klik, 0).toString();
    Object valueCol3 = jTable3.getValueAt(klik, 3);
    Object valueCol4 = jTable3.getValueAt(klik, 4);
    int kembali = 0;

    if (valueCol3 != null && valueCol4 != null) {
        kembali = Integer.parseInt(valueCol4.toString());
    }
    if (valueCol3 == null || valueCol4 == null || (valueCol3 != null && valueCol4 != null && kembali < 0)) {
        JOptionPane.showMessageDialog(null, "Pesanan belum selesai.");
    } else {
        try {
            String report = "C:\\Users\\A S U S\\Documents\\NetBeansProjects\\project_s2\\src\\halamankasir\\report1.jrxml";
            HashMap param = new HashMap();
            param.put("invo", id);

            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(JRpt, param, sambungan.koneksi.getKoneksi());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
        }
        else if (evt.getClickCount() == 1) {
            btnBayarNanti.setVisible(false);
            btnUpdate.setVisible(true);
            btn_simpan.setVisible(false);
            radioPembelianDP.setVisible(false);
            int klik = jTable3.getSelectedRow();
            String id = jTable3.getValueAt(klik, 0).toString();
            Object valueCol3 = jTable3.getValueAt(klik, 3);
            Object valueCol4 = jTable3.getValueAt(klik, 4);

        if (valueCol3 == null || valueCol4 == null) {
            buylater();
            totalBiaya();
            lblTotalBayar.setText("TOTAL BAYAR");
            txt_bayar.requestFocus(true);
        } 
        else {
            buylater();
            totalBiaya();
            txt_bayar.requestFocus(true);

            int totalBeli = Integer.parseInt(jTable3.getValueAt(klik, 2).toString());
            int bayarbone = Integer.parseInt(jTable3.getValueAt(klik, 3).toString());
            int utang = Integer.parseInt(jTable3.getValueAt(klik, 4).toString());

            if (utang < 0) {
                int newTotalBeli = totalBeli - bayarbone;
                txt_totalbayar.setText(String.valueOf(newTotalBeli));
                lblTotalBayar.setText("TOTAL SISA BAYAR");
            }
        }clear2();
    }
    }//GEN-LAST:event_jTable3MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtCarimenu.requestFocus(true);
    }//GEN-LAST:event_formWindowOpened

    private void btnBayarNantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarNantiActionPerformed
        cekAll.setVisible(true);
        cekThen.setVisible(true);
        cekThen.setSelected(true);
        cekThen.setEnabled(false);
        cekLate.setVisible(true);
        cekSelesai.setVisible(true);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        String idpesanan = txt_idpesanan.getText();
        String tanggal = txtDatetime.getText();
        String idpelanggan = txt_idpelanggan.getText();
        String total = txt_totalbayar.getText();
        Integer bayar = null;
        String kembalian = null;

        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke!");

        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Belum ada pesanan");
            txt_bayar.requestFocus(true);
            return;
        } else {
            try {
                jScrollPane3.setVisible(true);
                jScrollPane1.setVisible(false);
                btnMenu.setVisible(true);
                btnPesanan.setVisible(false);
                btn_simpan.setVisible(true);
                btnUpdate.setVisible(false);
                Font pont = new Font("Arial", Font.PLAIN, 23);
                UIManager.put("OptionPane.messageFont", pont);
                UIManager.put("OptionPane.buttonFont", pont);

                Object[] options = { "Simpan" };

                int option = JOptionPane.showOptionDialog(null, "Masuk bayar nanti", "Konfirmasi",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                int baris = jTable1.getRowCount();
                Connection c = koneksi.getKoneksi();

                String sql = "insert into pesanan values (?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idpesanan);
                p.setString(2, tanggal);
                p.setString(3, idpelanggan);
                p.setString(4, total);
                if (bayar == null) {
                    p.setNull(5, java.sql.Types.INTEGER);
                } else {
                    p.setInt(5, bayar);
                }
                p.setString(6, kembalian);
                p.executeUpdate();
                p.close();
                for (int i = 0; i < baris; i++) {
                String sql2 = "insert into pesanandetail(id_pesanan, id_menu, namamenu, jumlah, harga, total) VALUES('"
                        + jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2) 
                        +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4) +"','"+ jTable1.getValueAt(i, 5) 
                        +"')";
                PreparedStatement z = c.prepareStatement(sql2);
                z.executeUpdate();
                z.close();
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
//                pesananhariini();
                cekThenActionPerformed(evt);
                clear();
                utama();
                autonumber();
                kosong();
                txt_tampil.setText("Rp0");
        }
    }//GEN-LAST:event_btnBayarNantiActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int tabelklik = jTable3.getSelectedRow();
        String id = jTable3.getValueAt(tabelklik, 0).toString();
        Object valueCol3 = jTable3.getValueAt(tabelklik, 3);
        Object valueCol4 = jTable3.getValueAt(tabelklik, 4);
        String bayar = txt_bayar.getText();
        String kembali = txt_kembalian.getText();

        if (valueCol3 == null || valueCol4 == null) {
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "UPDATE pesanan SET bayar = ?, kembalian = ? WHERE id_pesanan = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, bayar);
                p.setString(2, kembali);
                p.setString(3, id);
                p.executeUpdate();
                p.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                JOptionPane.showMessageDialog(null, "Berhasil!");
                cekAll.setVisible(true);
                cekAll.setSelected(true);
                cekAll.setEnabled(false);
                cekThen.setVisible(true);
                cekThen.setSelected(false);
                cekThen.setEnabled(true);
                cekLate.setVisible(true);
                cekLate.setSelected(false);
                cekLate.setEnabled(true);
                clear();
                pesananhariini();
                kosong();
            }
        } else {
            int totalBeli = Integer.parseInt(valueCol3.toString());
            int totalBeli2 = Integer.parseInt(valueCol4.toString());
            int bayarValue = Integer.parseInt(bayar);
            int kembaliValue = Integer.parseInt(kembali);
            int newKembalian = totalBeli - bayarValue;

            if (totalBeli2 < 0) {
                try {
                    Connection c = koneksi.getKoneksi();
                    String sql = "UPDATE pesanan SET bayar = ?, kembalian = ? WHERE id_pesanan = ?";
                    PreparedStatement p = c.prepareStatement(sql);
                    p.setString(1, String.valueOf(totalBeli + bayarValue));
                    p.setString(2, String.valueOf(kembaliValue));
                    p.setString(3, id);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(null, "Berhasil!");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else {
                try {
                    Connection c = koneksi.getKoneksi();
                    String sql = "UPDATE pesanan SET bayar = ?, kembalian = ? WHERE id_pesanan = ?";
                    PreparedStatement p = c.prepareStatement(sql);
                    p.setString(1, bayar);
                    p.setString(2, kembali);
                    p.setString(3, id);
                    p.executeUpdate();
                    p.close();
                    JOptionPane.showMessageDialog(null, "Berhasil!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                cekAll.setVisible(true);
                cekAll.setSelected(true);
                cekAll.setEnabled(false);
                cekThen.setVisible(true);
                cekThen.setSelected(false);
                cekThen.setEnabled(true);
                cekLate.setVisible(true);
                cekLate.setSelected(false);
                cekLate.setEnabled(true);
                clear();
                pesananhariini();
                kosong();
                btnUpdate.setVisible(false);
                btn_simpan.setVisible(true);
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testActionPerformed
        caridata();
    }//GEN-LAST:event_testActionPerformed

    private void testKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testKeyTyped
        caridata();
    }//GEN-LAST:event_testKeyTyped

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        kosong();
        txt_totalbayar.setText("0");
        txt_tampil.setText("Rp0");
        btnUpdate.setVisible(false);
        btn_simpan.setVisible(true);
        jTable3.clearSelection();
        radioPembelianDP.setVisible(true);
        btnBayarNanti.setVisible(false);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cekAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekAllActionPerformed
        if(cekAll.isSelected()){
            cekAll.setEnabled(false);
            cekThen.setSelected(false);
            cekLate.setSelected(false);
            cekThen.setEnabled(true);
            cekLate.setEnabled(true);
            cekSelesai.setSelected(false);
            cekSelesai.setEnabled(true);
            try {
            PreparedStatement ps;
            ResultSet rs;
            LocalDate now = LocalDate.now();
            String nowString = now.toString();

            String tampil = "select id_pesanan, id_pelanggan, totalbeli, bayar, kembalian from pesanan where kembalian < 0 or date(tanggal) = ?";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            ps.setString(1, nowString);
            rs = ps.executeQuery();
            
            modelpesannow = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            modelpesannow.addColumn("ID Pesanan");
            modelpesannow.addColumn("ID Pelanggan");
            modelpesannow.addColumn("Total Beli");
            modelpesannow.addColumn("Bayar");
            modelpesannow.addColumn("Kembalian");
            jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable3.getWidth();
            jTable3.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable3.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        modelpesannow.getDataVector().removeAllElements();
        modelpesannow.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                };

                modelpesannow.addRow(jual);
            }
            jTable3.setModel(modelpesannow);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
        }else{
            return;
        }
    }//GEN-LAST:event_cekAllActionPerformed

    private void cekLateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekLateActionPerformed
        if(cekLate.isSelected()){
            cekLate.setEnabled(false);
            cekAll.setSelected(false);
            cekThen.setSelected(false);
            cekAll.setEnabled(true);
            cekThen.setEnabled(true);
            cekSelesai.setSelected(false);
            cekSelesai.setEnabled(true);
            cekSelesai.setVisible(true);
            bayarmuka muka = new bayarmuka();
            muka.setVisible(true);
            muka.btnFormat.setVisible(false);
            muka.btnRefresh.setVisible(true);
            try {
            PreparedStatement ps;
            ResultSet rs;

            String tampil = "select id_pesanan, id_pelanggan, totalbeli, bayar, kembalian from pesanan where kembalian < 0";
            ps = koneksi.getKoneksi().prepareStatement(tampil);
            rs = ps.executeQuery();
            
            modelpesannow = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }
            };

            modelpesannow.addColumn("ID Pesanan");
            modelpesannow.addColumn("ID Pelanggan");
            modelpesannow.addColumn("Total Beli");
            modelpesannow.addColumn("Bayar");
            modelpesannow.addColumn("Kembalian");
            jTable3.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int totalWidth = jTable3.getWidth();
            jTable3.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
            jTable3.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
            jTable3.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
            }
        });
        modelpesannow.getDataVector().removeAllElements();
        modelpesannow.fireTableDataChanged();
            while (rs.next()) {
                Object jual[] = {
                    rs.getString("id_pesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("totalbeli"),
                    rs.getString("bayar"),
                    rs.getString("kembalian"),
                };

                modelpesannow.addRow(jual);
            }
            jTable3.setModel(modelpesannow);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
            e.printStackTrace();
        }
            
        }else{
            return;
        }
    }//GEN-LAST:event_cekLateActionPerformed

    private void radioPembelianDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPembelianDPActionPerformed
        if (radioPembelianDP.isSelected() && jTable1.getRowCount() == 0) {
            return;
        }else if (radioPembelianDP.isSelected() && jTable1.getRowCount() > 0){
            txt_bayar.requestFocus(true);
            btnPraBayar.setVisible(true);
            btnUpdate.setVisible(false);
            btn_simpan.setVisible(false);
        }else if(!radioPembelianDP.isSelected() && jTable1.getRowCount() > 0){
            JOptionPane.showMessageDialog(null, "Pembelian Prabayar batal");
            txt_bayar.setText("");
            txt_kembalian.setText("");
            btnPraBayar.setVisible(false);
            btnUpdate.setVisible(false);
            btn_simpan.setVisible(true);
        }
        else{
            btnPraBayar.setVisible(false);
            btnUpdate.setVisible(false);
            btn_simpan.setVisible(true);
            }
       
    }//GEN-LAST:event_radioPembelianDPActionPerformed

    private void btnPraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPraBayarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String idpesanan = txt_idpesanan.getText();
        String tanggal = txtDatetime.getText();
        String idpelanggan = txt_idpelanggan.getText();
        String total = txt_totalbayar.getText();
        String bayar = txt_bayar.getText();
        String kembalian = txt_kembalian.getText();
        
        
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke!");
        
        if(txt_bayar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Masukkan jumlah pembayaran");
            txt_bayar.requestFocus(true);
            return;
        }
        else if(txt_kembalian.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter total bayar untuk melihat kembalian");
            txt_bayar.requestFocus(true);
            return;
        }
        else {
                
        try {
            int bone = Integer.parseInt(txt_kembalian.getText());
            
            int baris = jTable1.getRowCount();
            Connection c = koneksi.getKoneksi();
            
            String sql = "insert into pesanan values (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, idpesanan);
            p.setString(2, tanggal);
            p.setString(3, idpelanggan);
            p.setString(4, total);
            p.setString(5, bayar);
            p.setString(6, kembalian);
            p.executeUpdate();
            p.close();
            for (int i = 0; i < baris; i++) {
                String sql2 = "insert into pesanandetail(id_pesanan, id_menu, namamenu, jumlah, harga, total) VALUES('"
                        + jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2) 
                        +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4) +"','"+ jTable1.getValueAt(i, 5) 
                        +"')";
                PreparedStatement z = c.prepareStatement(sql2);
                z.executeUpdate();
                z.close();
            }
//            jScrollPane1.setVisible(false);
//            jScrollPane3.setVisible(true);
//            cekAll.setVisible(true);
//            cekThen.setVisible(true);
//            cekLate.setVisible(true);
//            cekLate.setSelected(true);
//            cekLate.setEnabled(false);
//            btnMenu.setVisible(true);
//            btnPesanan.setVisible(false);
//            cekLateActionPerformed(evt);
            
        pesananhariini();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        bayarmuka muka = new bayarmuka();
        muka.setVisible(true);
        muka.btnRefresh.setVisible(false);
        muka.btnFormat.setVisible(false);
        
        bayarmuka.txtIdPesananDP.setText(idpesanan);
        bayarmuka.txtIdPelangganDP.setText(idpelanggan);
        bayarmuka.txtTotalBayarDP.setText(total);
        bayarmuka.txtTunaiDP.setText(bayar);
        bayarmuka.txtKembalianDP.setText(kembalian);
        bayarmuka.btnFormat.setVisible(true);
        clear();
        utama();
        autonumber();
        kosong();
        radioPembelianDP.setSelected(false);
        txt_tampil.setText("Rp0");
//        jScrollPane3.setVisible(false);
//        jScrollPane1.setVisible(true);
        }
        
    }//GEN-LAST:event_btnPraBayarActionPerformed

    private void cekThenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekThenActionPerformed
        if(cekThen.isSelected()){
            cekThen.setEnabled(false);
            cekAll.setSelected(false);
            cekLate.setSelected(false);
            cekAll.setEnabled(true);
            cekLate.setEnabled(true);
            cekSelesai.setSelected(false);
            cekSelesai.setEnabled(true);
            try {
                PreparedStatement ps;
                ResultSet rs;
                LocalDate now = LocalDate.now();
                String nowString = now.toString();

                String tampil = "select id_pesanan, id_pelanggan, totalbeli, bayar, kembalian from pesanan where bayar IS NULL and kembalian IS NULL and date(tanggal) = ?";
                ps = koneksi.getKoneksi().prepareStatement(tampil);
                ps.setString(1, nowString);
                rs = ps.executeQuery();

                modelpesannow = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                modelpesannow.addColumn("ID Pesanan");
                modelpesannow.addColumn("ID Pelanggan");
                modelpesannow.addColumn("Total Beli");
                modelpesannow.addColumn("Bayar");
                modelpesannow.addColumn("Kembalian");
                jTable3.addComponentListener(new ComponentAdapter() {
                    public void componentResized(ComponentEvent e) {
                        int totalWidth = jTable3.getWidth();
                        jTable3.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1));
                        jTable3.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15));
                        jTable3.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15));
                        jTable3.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15));
                    }
                });
                modelpesannow.getDataVector().removeAllElements();
                modelpesannow.fireTableDataChanged();
                while (rs.next()) {
                    Object jual[] = {
                        rs.getString("id_pesanan"),
                        rs.getString("id_pelanggan"),
                        rs.getString("totalbeli"),
                        rs.getString("bayar"),
                        rs.getString("kembalian"),
                    };

                    modelpesannow.addRow(jual);
                }
                jTable3.setModel(modelpesannow);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menampilkan pesanan");
                e.printStackTrace();
            }

        }else{
            return;
        }
    }//GEN-LAST:event_cekThenActionPerformed

    private void cekSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSelesaiActionPerformed
        if (cekSelesai.isSelected()){
        pesananselesai();
        cekSelesai.setEnabled(false);
        cekAll.setSelected(false);
        cekAll.setEnabled(true);
        cekThen.setSelected(false);
        cekThen.setEnabled(true);
        cekLate.setSelected(false);
        cekLate.setEnabled(true);
        }else{
            return;
        }
    }//GEN-LAST:event_cekSelesaiActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pesananmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pesananmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pesananmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pesananmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pesananmenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgkasirpsn;
    public static javax.swing.JButton btnBayarNanti;
    private javax.swing.JLabel btnExits;
    private javax.swing.JLabel btnMax;
    public static javax.swing.JButton btnMenu;
    private javax.swing.JLabel btnMinim;
    public static javax.swing.JButton btnPesanan;
    public static javax.swing.JButton btnPraBayar;
    private javax.swing.JButton btnRefresh;
    public static javax.swing.JButton btnUpdate;
    public static javax.swing.JButton btn_delete;
    public static javax.swing.JButton btn_simpan;
    public javax.swing.JButton btn_tambah;
    private javax.swing.JButton btnbatal;
    public static javax.swing.JCheckBox cekAll;
    public static javax.swing.JCheckBox cekLate;
    public static javax.swing.JCheckBox cekSelesai;
    public static javax.swing.JCheckBox cekThen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    public static javax.swing.JLabel lblTotalBayar;
    private javax.swing.JLabel logopsn;
    public static javax.swing.JLabel logoutpsn;
    public static javax.swing.JLabel namaUser;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom panelcustom4;
    private custom.panelcustom panelcustom5;
    private custom.panelcustom panelcustom6;
    public static javax.swing.JRadioButton radioPembelianDP;
    private javax.swing.JTextField test;
    private javax.swing.JLabel titletoko;
    private custom.panelcustom topbarkasipsn;
    private javax.swing.JTextField txtCarimenu;
    public static javax.swing.JLabel txtDatetime;
    private javax.swing.JLabel txtHariID;
    private javax.swing.JLabel txtJamID;
    private javax.swing.JLabel txtTanggalID;
    public static javax.swing.JTextField txt_bayar;
    public static javax.swing.JTextField txt_harga;
    public static javax.swing.JTextField txt_idmenu;
    public static javax.swing.JTextField txt_idpelanggan;
    public static javax.swing.JTextField txt_idpesanan;
    public static javax.swing.JTextField txt_jumlah;
    public static javax.swing.JTextField txt_kembalian;
    public static javax.swing.JTextField txt_namamenu;
    public static javax.swing.JTextField txt_tampil;
    public static javax.swing.JTextField txt_totalbayar;
    private javax.swing.JPanel xypress;
    // End of variables declaration//GEN-END:variables
}