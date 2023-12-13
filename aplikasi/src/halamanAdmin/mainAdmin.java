package halamanAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import sambungan.koneksi;

public class mainAdmin extends javax.swing.JFrame {

    int xx, xy;
    Color warnaAsli, warnaKlik, warnaHover;
    private Timer timer;
    private Timer waktulokal;
    private boolean menu1Clicked = false, menu2Clicked = false, menu3Clicked = false, menu4Clicked = false, menu5Clicked = false;
    private boolean menu1Hovered = false, menu2Hovered = false, menu3Hovered = false, menu4Hovered = false, menu5Hovered = false;
    
    public mainAdmin() {
        initComponents();
        namaUser2.setVisible(false);
        btnLogout.setVisible(false);
        warnaAsli =  new Color(242,196,21);
        warnaKlik = new Color(217,24,40);
        warnaHover = new Color(217,176,19);
        
        menu1.setBackground(warnaKlik);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = true;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = false;
        
         timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
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
        
        pnlPengguna menuPengguna = new pnlPengguna();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        judulMenu.setText("/ Pengguna");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMainAdmin = new javax.swing.JPanel();
        bgSelectedMenu = new custom.panelcustom();
        menu1 = new custom.panelcustom();
        jLabel1 = new javax.swing.JLabel();
        menu2 = new custom.panelcustom();
        jLabel2 = new javax.swing.JLabel();
        menu3 = new custom.panelcustom();
        jLabel3 = new javax.swing.JLabel();
        menu4 = new custom.panelcustom();
        jLabel4 = new javax.swing.JLabel();
        menu5 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        topbarkasipsn = new custom.panelcustom();
        logopsn = new javax.swing.JLabel();
        titletoko = new javax.swing.JLabel();
        judulMenu = new javax.swing.JLabel();
        txtHariID = new javax.swing.JLabel();
        txtTanggalID = new javax.swing.JLabel();
        txtJamID = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        xypress = new javax.swing.JPanel();
        btnExits = new javax.swing.JLabel();
        btnMax = new javax.swing.JLabel();
        btnMinim = new javax.swing.JLabel();
        namaUser2 = new javax.swing.JLabel();
        txtDatetime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/ikon project kedai_okky.png")).getImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgMainAdmin.setPreferredSize(new java.awt.Dimension(1920, 1080));
        bgMainAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bgSelectedMenu.setBackground(new java.awt.Color(153, 153, 153));
        bgSelectedMenu.setPreferredSize(new java.awt.Dimension(222, 500));
        bgSelectedMenu.setRoundBottomLeft(12);
        bgSelectedMenu.setRoundTopLeft(12);
        bgSelectedMenu.setRoundTopRight(12);
        bgSelectedMenu.setRoundottomRight(12);
        bgSelectedMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu1.setBackground(new java.awt.Color(242, 196, 21));
        menu1.setRoundBottomLeft(12);
        menu1.setRoundTopLeft(12);
        menu1.setRoundTopRight(12);
        menu1.setRoundottomRight(12);
        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-35.png"))); // NOI18N
        jLabel1.setText(" Pengguna");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        bgSelectedMenu.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, 70));

        menu2.setBackground(new java.awt.Color(242, 196, 21));
        menu2.setRoundBottomLeft(12);
        menu2.setRoundTopLeft(12);
        menu2.setRoundTopRight(12);
        menu2.setRoundottomRight(12);
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu2MousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-burger-35.png"))); // NOI18N
        jLabel2.setText(" Daftar Menu");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        bgSelectedMenu.add(menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, 70));

        menu3.setBackground(new java.awt.Color(242, 196, 21));
        menu3.setPreferredSize(new java.awt.Dimension(233, 70));
        menu3.setRoundBottomLeft(12);
        menu3.setRoundTopLeft(12);
        menu3.setRoundTopRight(12);
        menu3.setRoundottomRight(12);
        menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu3MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-dollar-35.png"))); // NOI18N
        jLabel3.setText(" Penjualan");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu3Layout.setVerticalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        bgSelectedMenu.add(menu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 230, -1));

        menu4.setBackground(new java.awt.Color(242, 196, 21));
        menu4.setPreferredSize(new java.awt.Dimension(233, 70));
        menu4.setRoundBottomLeft(12);
        menu4.setRoundTopLeft(12);
        menu4.setRoundTopRight(12);
        menu4.setRoundottomRight(12);
        menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu4MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-box-35.png"))); // NOI18N
        jLabel4.setText(" Pembelian");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        bgSelectedMenu.add(menu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 230, -1));

        menu5.setBackground(new java.awt.Color(242, 196, 21));
        menu5.setPreferredSize(new java.awt.Dimension(233, 70));
        menu5.setRoundBottomLeft(12);
        menu5.setRoundTopLeft(12);
        menu5.setRoundTopRight(12);
        menu5.setRoundottomRight(12);
        menu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu5MousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Swis721 BlkCn BT", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-stock-35.png"))); // NOI18N
        jLabel5.setText("persediaan");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout menu5Layout = new javax.swing.GroupLayout(menu5);
        menu5.setLayout(menu5Layout);
        menu5Layout.setHorizontalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        menu5Layout.setVerticalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        bgSelectedMenu.add(menu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 230, -1));

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout-46.png"))); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.setAlignmentY(0.0F);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });
        bgSelectedMenu.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 790, 210, 87));

        bgMainAdmin.add(bgSelectedMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 250, 890));

        topbarkasipsn.setBackground(new java.awt.Color(217, 24, 40));
        topbarkasipsn.setRoundBottomLeft(12);
        topbarkasipsn.setRoundTopLeft(12);
        topbarkasipsn.setRoundTopRight(12);
        topbarkasipsn.setRoundottomRight(12);

        logopsn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo revisi  height 88.png"))); // NOI18N

        titletoko.setFont(new java.awt.Font("STHupo", 0, 36)); // NOI18N
        titletoko.setForeground(new java.awt.Color(255, 255, 255));
        titletoko.setText("OKKY HOME");

        judulMenu.setFont(new java.awt.Font("STHupo", 0, 24)); // NOI18N
        judulMenu.setForeground(new java.awt.Color(255, 255, 255));
        judulMenu.setText("/  ");

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
                .addGap(39, 39, 39)
                .addComponent(logopsn)
                .addGap(30, 30, 30)
                .addComponent(titletoko)
                .addGap(18, 18, 18)
                .addComponent(judulMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1060, Short.MAX_VALUE)
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
            .addGroup(topbarkasipsnLayout.createSequentialGroup()
                .addGroup(topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHariID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTanggalID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJamID)
                .addContainerGap())
            .addComponent(logopsn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addGroup(topbarkasipsnLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(topbarkasipsnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titletoko)
                    .addComponent(judulMenu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bgMainAdmin.add(topbarkasipsn, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 33, 1880, 100));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1280, 720));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1540, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );

        bgMainAdmin.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 160, 1540, 870));

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

        namaUser2.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        namaUser2.setText("user");

        txtDatetime.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        txtDatetime.setText("jLabel6");

        javax.swing.GroupLayout xypressLayout = new javax.swing.GroupLayout(xypress);
        xypress.setLayout(xypressLayout);
        xypressLayout.setHorizontalGroup(
            xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, xypressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaUser2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1665, Short.MAX_VALUE)
                .addComponent(btnMinim)
                .addGap(18, 18, 18)
                .addComponent(btnMax)
                .addGap(14, 14, 14)
                .addComponent(btnExits)
                .addContainerGap())
        );
        xypressLayout.setVerticalGroup(
            xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addGroup(xypressLayout.createSequentialGroup()
                .addGroup(xypressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaUser2)
                    .addComponent(txtDatetime))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bgMainAdmin.add(xypress, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, -1));

        getContentPane().add(bgMainAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

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

    private void btnMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaxMouseClicked
        if(this.getExtendedState()!=mainAdmin.MAXIMIZED_BOTH){
            this.setExtendedState(mainAdmin.MAXIMIZED_BOTH);
        }else{
            this.setExtendedState(mainAdmin.NORMAL);
    }//GEN-LAST:event_btnMaxMouseClicked
    }
    private void btnMinimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimMouseClicked
        this.setExtendedState(mainAdmin.ICONIFIED);
    }//GEN-LAST:event_btnMinimMouseClicked

    private void xypressMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xypressMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx,y - xy);
    }//GEN-LAST:event_xypressMouseDragged

    private void xypressMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xypressMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_xypressMousePressed

    private void menu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MousePressed
        menu1.setBackground(warnaKlik);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = true;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = false;
        judulMenu.setText("/ Pengguna");
    }//GEN-LAST:event_menu1MousePressed

    private void menu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MousePressed
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaKlik);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = true;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = false;
        
        judulMenu.setText("/ Daftar Menu");
    }//GEN-LAST:event_menu2MousePressed

    private void menu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MousePressed
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaKlik);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = true;
        menu4Clicked = false;
        menu5Clicked = false;
        
        judulMenu.setText("/ Penjualan");
    }//GEN-LAST:event_menu3MousePressed

    private void menu4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MousePressed
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaKlik);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = true;
        menu5Clicked = false;
        
        judulMenu.setText("/ Pembelian");
    }//GEN-LAST:event_menu4MousePressed

    private void menu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MousePressed
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaKlik);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = true;
        
        judulMenu.setText("/ Stok");
    }//GEN-LAST:event_menu5MousePressed

    private void menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseClicked
        pnlPengguna menuPengguna = new pnlPengguna();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        menu1.setBackground(warnaKlik);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = true;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = false;
        judulMenu.setText("/ Pengguna");
    }//GEN-LAST:event_menu1MouseClicked

    private void menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseClicked
        pnlDaftarmenu menuPengguna = new pnlDaftarmenu();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaKlik);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = true;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = false;
        
        judulMenu.setText("/ Daftar Menu");
    }//GEN-LAST:event_menu2MouseClicked

    private void menu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseClicked
        pnlPenjualan menuPengguna = new pnlPenjualan();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaKlik);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = true;
        menu4Clicked = false;
        menu5Clicked = false;
        
        judulMenu.setText("/ Penjualan");
    }//GEN-LAST:event_menu3MouseClicked

    private void menu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseClicked
        pnlPembelian menuPengguna = new pnlPembelian();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaKlik);
        menu5.setBackground(warnaAsli);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = true;
        menu5Clicked = false;
        
        judulMenu.setText("/ Pembelian");
    }//GEN-LAST:event_menu4MouseClicked

    private void menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseClicked
        pnlStok menuPengguna = new pnlStok();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(menuPengguna).setVisible(true);
        menu1.setBackground(warnaAsli);
        menu2.setBackground(warnaAsli);
        menu3.setBackground(warnaAsli);
        menu4.setBackground(warnaAsli);
        menu5.setBackground(warnaKlik);
        menu1Clicked = false;
        menu2Clicked = false;
        menu3Clicked = false;
        menu4Clicked = false;
        menu5Clicked = true;
        
        judulMenu.setText("/ Stok");
    }//GEN-LAST:event_menu5MouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        LocalDate now = LocalDate.now();
        String nowString = now.toString();
        int pernyataan = JOptionPane.showConfirmDialog(null, "Anda yakin akan logout?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(pernyataan == JOptionPane.OK_OPTION){
            try {
                String user = namaUser2.getText();
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
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void menu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseEntered
        if (!menu1Clicked) {
        menu1.setBackground(warnaHover);
        menu1Hovered = true;
    }
    }//GEN-LAST:event_menu1MouseEntered

    private void menu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseExited
        if (!menu1Clicked) {
        menu1.setBackground(warnaAsli);
        menu1Hovered = false;
    }
    }//GEN-LAST:event_menu1MouseExited

    private void menu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseEntered
        if (!menu2Clicked) {
        menu2.setBackground(warnaHover);
        menu2Hovered = true;
    }
    }//GEN-LAST:event_menu2MouseEntered

    private void menu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseExited
        if (!menu2Clicked) {
        menu2.setBackground(warnaAsli);
        menu2Hovered = false;
    }
    }//GEN-LAST:event_menu2MouseExited

    private void menu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseEntered
        if (!menu3Clicked) {
        menu3.setBackground(warnaHover);
        menu3Hovered = true;
    }
    }//GEN-LAST:event_menu3MouseEntered

    private void menu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseExited
        if (!menu3Clicked) {
        menu3.setBackground(warnaAsli);
        menu3Hovered = false;
    }
    }//GEN-LAST:event_menu3MouseExited

    private void menu4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseEntered
        if (!menu4Clicked) {
        menu4.setBackground(warnaHover);
        menu4Hovered = true;
    }
    }//GEN-LAST:event_menu4MouseEntered

    private void menu4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseExited
        if (!menu4Clicked) {
        menu4.setBackground(warnaAsli);
        menu4Hovered = false;
    }
    }//GEN-LAST:event_menu4MouseExited

    private void menu5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseEntered
        if (!menu5Clicked) {
        menu5.setBackground(warnaHover);
        menu5Hovered = true;
    }
    }//GEN-LAST:event_menu5MouseEntered

    private void menu5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseExited
        if (!menu5Clicked) {
        menu5.setBackground(warnaAsli);
        menu5Hovered = false;
    }
    }//GEN-LAST:event_menu5MouseExited

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(mainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainAdmin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgMainAdmin;
    private custom.panelcustom bgSelectedMenu;
    private javax.swing.JLabel btnExits;
    public static javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnMax;
    private javax.swing.JLabel btnMinim;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel judulMenu;
    private javax.swing.JLabel logopsn;
    private custom.panelcustom menu1;
    private custom.panelcustom menu2;
    private custom.panelcustom menu3;
    private custom.panelcustom menu4;
    private custom.panelcustom menu5;
    public static javax.swing.JLabel namaUser2;
    private javax.swing.JLabel titletoko;
    private custom.panelcustom topbarkasipsn;
    public static javax.swing.JLabel txtDatetime;
    private javax.swing.JLabel txtHariID;
    private javax.swing.JLabel txtJamID;
    private javax.swing.JLabel txtTanggalID;
    private javax.swing.JPanel xypress;
    // End of variables declaration//GEN-END:variables
}