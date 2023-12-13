package login;
import halamanKasir.Pesananmenu;
import halamanAdmin.mainAdmin;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import sambungan.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
public class Login extends javax.swing.JFrame {
    
    private PreparedStatement stat;
    private ResultSet rs;
    koneksi k = new koneksi();
    int xx, xy;
    private Timer timer;
    private Timer waktulokal;
    
    public Login() {
        initComponents();
        k.getKoneksi();
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
                txtTanggalLogin.setText(s.format(date));
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
    }
    class user{
        String username, password, level;
        public user() {
            this.username = txtusername.getText();
            this.password = txtpassword.getText();
            this.level  = "";
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        toppanel = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        txtHariID = new javax.swing.JLabel();
        txtTanggalID = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTanggalLogin = new javax.swing.JLabel();
        panelcustom1 = new custom.panelcustom();
        logintext = new javax.swing.JLabel();
        usertxt = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        pw = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        disable = new javax.swing.JLabel();
        usericon = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        show = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        txtJamID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("formlogin");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/ikon project kedai_okky.png")).getImage());
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bg.setPreferredSize(new java.awt.Dimension(1140, 650));

        toppanel.setBackground(new java.awt.Color(217, 24, 40));
        toppanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logookkybitmap.png"))); // NOI18N
        toppanel.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 108));

        txtHariID.setFont(new java.awt.Font("STHupo", 1, 79)); // NOI18N
        txtHariID.setForeground(new java.awt.Color(255, 255, 255));
        toppanel.add(txtHariID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 360, 80));

        txtTanggalID.setFont(new java.awt.Font("STHupo", 1, 36)); // NOI18N
        txtTanggalID.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggalID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toppanel.add(txtTanggalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 280, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kbb_2.png"))); // NOI18N
        toppanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        txtTanggalLogin.setFont(new java.awt.Font("Tahoma", 0, 7)); // NOI18N
        txtTanggalLogin.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggalLogin.setText("jLabel2");
        toppanel.add(txtTanggalLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, -1, -1));

        panelcustom1.setBackground(new java.awt.Color(141, 16, 26));
        panelcustom1.setPreferredSize(new java.awt.Dimension(333, 205));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);
        panelcustom1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logintext.setFont(new java.awt.Font("Swis721 Hv BT", 1, 12)); // NOI18N
        logintext.setForeground(new java.awt.Color(255, 255, 255));
        logintext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logintext.setText("LOGIN TERLEBIH DAHULU!");
        panelcustom1.add(logintext, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 10, 320, -1));

        usertxt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        usertxt.setForeground(new java.awt.Color(255, 255, 255));
        usertxt.setText("username");
        panelcustom1.add(usertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });
        panelcustom1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 270, 30));

        pw.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        pw.setForeground(new java.awt.Color(255, 255, 255));
        pw.setText("password");
        panelcustom1.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txtpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtpassword.setPreferredSize(new java.awt.Dimension(66, 22));
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        panelcustom1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 270, 30));

        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/oraketok.png"))); // NOI18N
        disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        panelcustom1.add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, 30));

        usericon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        panelcustom1.add(usericon, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, 30));

        btn_login.setBackground(new java.awt.Color(163, 203, 56));
        btn_login.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_login.setText("masuk");
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        panelcustom1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 110, -1));

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ketok.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        panelcustom1.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, 30));

        jPanel2.setBackground(new java.awt.Color(141, 16, 26));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel2.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 30, 30));

        minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/minimize_putih.png"))); // NOI18N
        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jPanel2.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

        txtJamID.setFont(new java.awt.Font("STHupo", 1, 21)); // NOI18N
        txtJamID.setForeground(new java.awt.Color(255, 255, 255));
        txtJamID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(txtJamID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 380, 30));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(toppanel, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelcustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(panelcustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(toppanel, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1140, 650));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        txtpassword.setEchoChar((char)0);
        disable.setVisible(false);
        disable.setEnabled(false);
        show.setEnabled(true);
        show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        txtpassword.setEchoChar((char)8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        show.setEnabled(false);
        show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin akan keluar?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        if (pernyataan == JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_closeMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setExtendedState(Login.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        user u = new user();
        String namauser = txtusername.getText();
        String tgllogin = txtTanggalLogin.getText();
        String logout = txtTanggalLogin.getText();
        LocalDate now = LocalDate.now();
        String nowString = now.toString();
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Oke");
        try {
            this.stat = k.getKoneksi().prepareStatement("select * from user where "
                    + "username='"+u.username+"' and password='"+u.password+"';");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {                
                u.level = rs.getString("level");
            }
            if (u.username.equals("")) {
                JOptionPane.showMessageDialog(null, "Harap Isi form untuk Log-In");
                txtusername.requestFocus(true);
            }
            else if(txtpassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Harap masukkan password");
                txtpassword.requestFocus(true);
            }
            else if (u.level == "") {
                JOptionPane.showMessageDialog(null, "Akun Tidak Ditemukan");
                txtusername.requestFocus(true);
            }
            else{
                switch(u.level){
                    case "admin":
                        mainAdmin adm = new mainAdmin();
                        adm.setVisible(true);
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Anda Login Sebagai Admin");
                        try {
                            Connection c = koneksi.getKoneksi();
                            String sql = "select count(*) from riwayat_login where username = ? and date(waktulogin) = ?";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, namauser);
                            p.setString(2, nowString);

                            ResultSet r = p.executeQuery();
                            r.next();
                                int count = r.getInt(1);

                            if (count > 0) {
                                // Jika nama / tanggal sama, lakukan operasi update 
                                sql = "update riwayat_login set waktulogin = ?, waktu_logout = ? where username = ? and date(waktulogin) = ?";
                                p = c.prepareStatement(sql);
                                p.setString(1, tgllogin);
                                p.setString(2, logout);
                                p.setString(3, namauser);
                                p.setString(4, nowString);
                                p.executeUpdate();
                                p.close();
                            } else {
                                // Jika nama belum ada, lakukan operasi insert 
                                sql = "insert into riwayat_login values (?, ?, ?)";
                                p = c.prepareStatement(sql);
                                p.setString(1, namauser);
                                p.setString(2, tgllogin);
                                p.setString(3, logout);
                                p.executeUpdate();
                                p.close();
                            }
                        } 
                        catch (SQLException e) {
                            System.out.println(e.getMessage());
                    }
                        finally {
                            mainAdmin.namaUser2.setText(namauser);
                            mainAdmin.namaUser2.setVisible(true);
                            mainAdmin.btnLogout.setVisible(true);
                        }
                        break;
                    case "pegawai":
                        Pesananmenu kasir = new Pesananmenu();
                        kasir.setVisible(true);
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Anda Login Sebagai Pegawai");
                        try {
                            Connection c = koneksi.getKoneksi();
                            String sql = "select count(*) from riwayat_login where username = ? and date(waktulogin) = ?";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, namauser);
                            p.setString(2, nowString);

                            ResultSet r = p.executeQuery();
                            r.next();
                                int count = r.getInt(1);

                            if (count > 0) {
                                // Jika nama / tanggal sama, lakukan operasi update 
                                sql = "update riwayat_login set waktulogin = ?, waktu_logout = ? where username = ? and date(waktulogin) = ?";
                                p = c.prepareStatement(sql);
                                p.setString(1, tgllogin);
                                p.setString(2, logout);
                                p.setString(3, namauser);
                                p.setString(4, nowString);
                                p.executeUpdate();
                                p.close();
                            } else {
                                // Jika nama belum ada, lakukan operasi insert
                                sql = "insert into riwayat_login values (?, ?, ?)";
                                p = c.prepareStatement(sql);
                                p.setString(1, namauser);
                                p.setString(2, tgllogin);
                                p.setString(3, logout);
                                p.executeUpdate();
                                p.close();
                                System.out.println("riwayat ambil stok disimpan");
                            }
                        } 
                        catch (SQLException e) {
                            System.out.println(e.getMessage());
                    }
                        finally {
                            Pesananmenu.namaUser.setText(namauser);
                            Pesananmenu.namaUser.setVisible(true);
                            Pesananmenu.logoutpsn.setVisible(true);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        txtpassword.requestFocus(true);
    }//GEN-LAST:event_txtusernameActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        user u = new user();
        String namauser = txtusername.getText();
        String tgllogin = txtTanggalLogin.getText();
        String logout = txtTanggalLogin.getText();
        LocalDate now = LocalDate.now();
        String nowString = now.toString();
        Font font = new Font("Arial", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.okButtonText", "Siap!");
        try {
            this.stat = k.getKoneksi().prepareStatement("select * from user where "
                    + "username='"+u.username+"' and password='"+u.password+"';");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {                
                u.level = rs.getString("level");
            }
            if (u.username.equals("")) {
                JOptionPane.showMessageDialog(null, "Harap Isi form untuk Log-In");
                txtusername.requestFocus(true);
            }
            else if(txtpassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Harap masukkan password");
                txtpassword.requestFocus(true);
            }
            else if (u.level == "") {
                JOptionPane.showMessageDialog(null, "Akun Tidak Ditemukan");
                txtusername.requestFocus(true);
            }
            else{
                switch(u.level){
                    case "admin":
                        mainAdmin adm = new mainAdmin();
                        adm.setVisible(true);
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Anda Login Sebagai Admin");
                        try {
                            Connection c = koneksi.getKoneksi();
                            String sql = "select count(*) from riwayat_login where username = ? and date(waktulogin) = ?";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, namauser);
                            p.setString(2, nowString);

                            ResultSet r = p.executeQuery();
                            r.next();
                                int count = r.getInt(1);

                            if (count > 0) {
                                // Jika nama / tanggal sama, lakukan operasi update 
                                sql = "update riwayat_login set waktulogin = ?, waktu_logout = ? where username = ? and date(waktulogin) = ?";
                                p = c.prepareStatement(sql);
                                p.setString(1, tgllogin);
                                p.setString(2, logout);
                                p.setString(3, namauser);
                                p.setString(4, nowString);
                                p.executeUpdate();
                                p.close();
                            } else {
                                // Jika nama belum ada, lakukan operasi insert 
                                sql = "insert into riwayat_login values (?, ?, ?)";
                                p = c.prepareStatement(sql);
                                p.setString(1, namauser);
                                p.setString(2, tgllogin);
                                p.setString(3, logout);
                                p.executeUpdate();
                                p.close();
                            }
                        } 
                        catch (SQLException e) {
                            System.out.println(e.getMessage());
                    }
                        finally {
                            mainAdmin.namaUser2.setText(namauser);
                            mainAdmin.namaUser2.setVisible(true);
                            mainAdmin.btnLogout.setVisible(true);
                        }
                        break;
                    case "pegawai":
                        Pesananmenu kasir = new Pesananmenu();
                        kasir.setVisible(true);
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Anda Login Sebagai Pegawai");
                        try {
                            Connection c = koneksi.getKoneksi();
                            String sql = "select count(*) from riwayat_login where username = ? and date(waktulogin) = ?";
                            PreparedStatement p = c.prepareStatement(sql);
                            p.setString(1, namauser);
                            p.setString(2, nowString);

                            ResultSet r = p.executeQuery();
                            r.next();
                                int count = r.getInt(1);

                            if (count > 0) {
                                // Jika nama / tanggal sama, lakukan operasi update 
                                sql = "update riwayat_login set waktulogin = ?, waktu_logout = ? where username = ? and date(waktulogin) = ?";
                                p = c.prepareStatement(sql);
                                p.setString(1, tgllogin);
                                p.setString(2, logout);
                                p.setString(3, namauser);
                                p.setString(4, nowString);
                                p.executeUpdate();
                                p.close();

                            } else {
                                // Jika nama belum ada, lakukan operasi insert
                                sql = "insert into riwayat_login values (?, ?, ?)";
                                p = c.prepareStatement(sql);
                                p.setString(1, namauser);
                                p.setString(2, tgllogin);
                                p.setString(3, logout);
                                p.executeUpdate();
                                p.close();
                            }
                        } 
                        catch (SQLException e) {
                            System.out.println(e.getMessage());
                    }
                        finally {
                            Pesananmenu.namaUser.setText(namauser);
                            Pesananmenu.namaUser.setVisible(true);
                            Pesananmenu.logoutpsn.setVisible(true);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx,y - xy);
    }//GEN-LAST:event_jPanel2MouseDragged

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btn_login;
    private javax.swing.JLabel close;
    private javax.swing.JLabel disable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logintext;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel minimize;
    private custom.panelcustom panelcustom1;
    private javax.swing.JLabel pw;
    private javax.swing.JLabel show;
    private javax.swing.JPanel toppanel;
    private javax.swing.JLabel txtHariID;
    private javax.swing.JLabel txtJamID;
    private javax.swing.JLabel txtTanggalID;
    private javax.swing.JLabel txtTanggalLogin;
    private javax.swing.JPasswordField txtpassword;
    public static javax.swing.JTextField txtusername;
    private javax.swing.JLabel usericon;
    private javax.swing.JLabel usertxt;
    // End of variables declaration//GEN-END:variables
}