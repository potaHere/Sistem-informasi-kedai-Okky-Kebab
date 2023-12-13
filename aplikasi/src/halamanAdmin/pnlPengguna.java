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
import login.riwayatLogin;

public class pnlPengguna extends javax.swing.JInternalFrame {

    koneksi koneksi = new koneksi();
    private DefaultTableModel model;
    

    private void autonumber(){
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "select * from user order by id_user desc";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String iduser = r.getString("id_user").substring(3);
                String USR = "" +(Integer.parseInt(iduser)+1);
                String No1 = "";
                
                if (USR.length()==1) 
                    {No1 = "00";}
                else if(USR.length()==2)
                    {No1 = "0";}
                else if(USR.length()==3)
                    {No1 = "";}
                
                txt_iduser.setText("USR" + No1 + USR);  
            }else{
                txt_iduser.setText("USR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void clear(){
        txt_username.setText("");
        txtcari.setText("");
        txt_password.setText("");
        txt_namauser.setText("");
        txt_nohp.setText("");
        txt_alamat.setText("");
        txt_iduser.setEnabled(false);
        combo_idlevel.setSelectedItem("pilih level");
        btnsimpan.setVisible(true);
        btnUpdate.setVisible(false);
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "select * from user";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[7];
                o [0] = r.getString("id_user");
                o [1] = r.getString("username");
                o [2] = r.getString("password");
                o [3] = r.getString("nama_user");
                o [4] = r.getString("no_hp");
                o [5] = r.getString("alamat_user");
                o [6] = r.getString("level");
                
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
        model.addColumn("id_user");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Nama");
        model.addColumn("No_hp");
        model.addColumn("Alamat");
        model.addColumn("level");
        
        model.setRowCount(0);
        String cari = txtcari.getText();
        try {
            String sql = "select * from user "
                    + "where id_user like '%" + cari + "%' or username like '%" + cari + "%' or password like '%" + cari + "%' or nama_user like '%" + cari + "%' or no_hp like '%" + cari + "%' or alamat_user like '%" + cari + "%' or level like '%" + cari + "%'";
            java.sql.Connection conn = (Connection) sambungan.koneksi.getKoneksi();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                Object[] obj = new Object[7];
                obj[0] = res.getString("id_user");
                obj[1] = res.getString("username");
                obj[2] = res.getString("password");
                obj[3] = res.getString("nama_user");
                obj[4] = res.getString("no_hp");
                obj[5] = res.getString("alamat_user");
                obj[6] = res.getString("level");
                model.addRow(obj);
            }
            jTable1.setModel(model);
            
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public pnlPengguna() {
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
        
        model.addColumn("id_user");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Nama");
        model.addColumn("No_hp");
        model.addColumn("Alamat");
        model.addColumn("level");
        
        jTable1.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
        int totalWidth = jTable1.getWidth();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth((int) (totalWidth * 0.1)); 
        jTable1.getColumnModel().getColumn(1).setPreferredWidth((int) (totalWidth * 0.15));
        jTable1.getColumnModel().getColumn(2).setPreferredWidth((int) (totalWidth * 0.15)); 
        jTable1.getColumnModel().getColumn(3).setPreferredWidth((int) (totalWidth * 0.15)); 
        jTable1.getColumnModel().getColumn(4).setPreferredWidth((int) (totalWidth * 0.15));
        jTable1.getColumnModel().getColumn(5).setPreferredWidth((int) (totalWidth * 0.25)); 
        jTable1.getColumnModel().getColumn(6).setPreferredWidth((int) (totalWidth * 0.1));
        }
        });

        
        loadData();
        autonumber();
        btnUpdate.setVisible(false);
        btnsimpan.setVisible(true);
        btndelete.setEnabled(false);
        btn_refresh.setEnabled(true);
        txt_iduser.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pengguna = new javax.swing.JPanel();
        panelcustom3 = new custom.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        panelcustom1 = new custom.panelcustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_iduser = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_namauser = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_nohp = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        combo_idlevel = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelcustom2 = new custom.panelcustom();
        btnsimpan = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btn_supplier = new javax.swing.JButton();
        btn_history = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        panelcustom4 = new custom.panelcustom();
        txtcari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1540, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pengguna.setPreferredSize(new java.awt.Dimension(1540, 870));
        pengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penggunaMouseClicked(evt);
            }
        });
        pengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom3.setBackground(new java.awt.Color(242, 196, 21));
        panelcustom3.setRoundBottomLeft(12);
        panelcustom3.setRoundTopLeft(12);
        panelcustom3.setRoundTopRight(12);
        panelcustom3.setRoundottomRight(12);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA PENGGUNA");

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

        pengguna.add(panelcustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        panelcustom1.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom1.setRoundBottomLeft(12);
        panelcustom1.setRoundTopLeft(12);
        panelcustom1.setRoundTopRight(12);
        panelcustom1.setRoundottomRight(12);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("id_user");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("password");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("username");

        txt_iduser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        txt_username.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        txt_password.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("nama");

        txt_namauser.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_namauser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namauserActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("no.hp");

        txt_nohp.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nohp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nohpActionPerformed(evt);
            }
        });
        txt_nohp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nohpKeyReleased(evt);
            }
        });

        txt_alamat.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_alamatActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("alamat");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("id_level");

        combo_idlevel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        combo_idlevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih level", "admin", "pegawai" }));
        combo_idlevel.setToolTipText("");

        javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
        panelcustom1.setLayout(panelcustom1Layout);
        panelcustom1Layout.setHorizontalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(29, 29, 29)
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelcustom1Layout.createSequentialGroup()
                                .addComponent(combo_idlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 181, Short.MAX_VALUE))
                            .addComponent(txt_nohp, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txt_alamat)))
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txt_username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txt_iduser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(txt_namauser, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelcustom1Layout.setVerticalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_iduser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_namauser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_nohp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(combo_idlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pengguna.add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

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

        pengguna.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 1010, 590));

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
        panelcustom2.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 130, 43));

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
        panelcustom2.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 43));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save 30px.png"))); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        panelcustom2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 130, 43));

        pengguna.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 416, 60));

        btn_supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/supplier 20px.png"))); // NOI18N
        btn_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_supplier.setPreferredSize(new java.awt.Dimension(25, 25));
        btn_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supplierActionPerformed(evt);
            }
        });
        pengguna.add(btn_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 13, 33, 33));

        btn_history.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/history_Login_35px.png"))); // NOI18N
        btn_history.setText("Riwayat Login");
        btn_history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historyActionPerformed(evt);
            }
        });
        pengguna.add(btn_history, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 750, 179, 52));

        btn_refresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btn_refresh.setText("REFRESH");
        btn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        pengguna.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, -1, 43));

        panelcustom4.setBackground(new java.awt.Color(255, 255, 255));
        panelcustom4.setPreferredSize(new java.awt.Dimension(263, 38));
        panelcustom4.setRoundBottomLeft(5);
        panelcustom4.setRoundTopLeft(5);
        panelcustom4.setRoundTopRight(5);
        panelcustom4.setRoundottomRight(5);
        panelcustom4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcari.setBorder(null);
        txtcari.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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
        panelcustom4.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 2, 190, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-25.png"))); // NOI18N
        panelcustom4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 0, 30, 30));

        pengguna.add(panelcustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 246, 30));

        getContentPane().add(pengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        clear();
        loadData();
        btndelete.setEnabled(false);
        btn_refresh.setEnabled(true);
        txt_iduser.setEnabled(false);
        autonumber();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cariData();
        btn_refresh.setEnabled(true);
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyTyped
        cariData();
        btn_refresh.setEnabled(true);
    }//GEN-LAST:event_txtcariKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idusr = (String) model.getValueAt(i, 0);
        String usernm = (String) model.getValueAt(i, 1);
        String pw = (String) model.getValueAt(i, 2);
        String name = (String) model.getValueAt(i, 3);
        String hp = (String) model.getValueAt(i, 4);
        String almt = (String) model.getValueAt(i, 5);
        String lvl = (String) model.getValueAt(i, 6);

        txt_iduser.setText(idusr);
        txt_username.setText(usernm);
        txt_password.setText(pw);
        txt_namauser.setText(name);
        txt_nohp.setText(hp);
        txt_alamat.setText(almt);
        combo_idlevel.setSelectedItem(lvl);
        
        btnsimpan.setVisible(false);
        btnUpdate.setVisible(true);
        btndelete.setEnabled(true);
        btn_refresh.setEnabled(true);
        txt_iduser.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        String idusr = txt_iduser.getText();
        String usernm = txt_username.getText();
        String pw = txt_password.getText();
        String name = txt_namauser.getText();
        String hp = txt_nohp.getText();
        String almt = txt_alamat.getText();
        String lvl = combo_idlevel.getSelectedItem().toString();
        String tgllog = mainAdmin.txtDatetime.getText();
        if (idusr.equals("") || usernm.equals("") || pw.equals("") || name.equals("") || hp.equals("") || almt.equals("") || tgllog.equals("")) {
            JOptionPane.showMessageDialog(null, "Harap Isi semua form");
        }
        else if (lvl.equals("pilih level")) {
            JOptionPane.showMessageDialog(null, "Pilih level dengan benar(admin / pegawai)");
            btnsimpan.setVisible(true);
            btnUpdate.setVisible(false);
        }
        else {
        try {
            Connection c = sambungan.koneksi.getKoneksi();
            // Mengecek apakah username sudah ada apa belom
            String checkUsernameQuery = "select count(*) from user where username = ?";
            PreparedStatement checkUsernameStatement = c.prepareStatement(checkUsernameQuery);
            checkUsernameStatement.setString(1, usernm);
            ResultSet checkUsernameResult = checkUsernameStatement.executeQuery();
            checkUsernameResult.next();
            int usernameCount = checkUsernameResult.getInt(1);

            if (usernameCount > 0) {
                JOptionPane.showMessageDialog(null, "Username sudah ada, harap gunakan username lain");
                txt_username.requestFocus(true);
            } else {
                String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idusr);
                p.setString(2, usernm);
                p.setString(3, pw);
                p.setString(4, name);
                p.setString(5, hp);
                p.setString(6, almt);
                p.setString(7, lvl);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Tersimpan");
                autonumber();
                clear();
                loadData();
            }
            checkUsernameResult.close();
            checkUsernameStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Error Min");
        }
    }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }

        String idusr = (String) model.getValueAt(i, 0);
        String usrname = (String) model.getValueAt(i, 1);

        int pernyataan = JOptionPane.showOptionDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Hapus", "Batal"}, "");
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "delete from user where id_user = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, idusr);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan");
            }
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "delete from riwayat_login where username = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, usrname);
                p.executeUpdate();
                p.close();
                System.out.println("Data Riwayat Login Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan Hapus Riwayat Login");
            }finally{
                btndelete.setEnabled(false);
                btn_refresh.setEnabled(true);
                loadData();
                autonumber();
                clear();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btn_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supplierActionPerformed
        this.setVisible(false);
        dataSupplier sup = new dataSupplier();
        halamanAdmin.mainAdmin.jDesktopPane1.add(sup).setVisible(true);
        halamanAdmin.mainAdmin.judulMenu.setText("/ Supplier");
    }//GEN-LAST:event_btn_supplierActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    int i = jTable1.getSelectedRow();
    if (i == -1) {
        return;
    }
    String idusr = (String) model.getValueAt(i, 0);
    String usernm = txt_username.getText();
    String pw = txt_password.getText();
    String name = txt_namauser.getText();
    String hp = txt_nohp.getText();
    String almt = txt_alamat.getText();
    String userlvl = combo_idlevel.getSelectedItem().toString();

    try {
        Connection c = koneksi.getKoneksi();
        String sql = "update user set id_user = ?, username = ?, password = ?, nama_user = ?, no_hp = ?, alamat_user = ?, level = ? WHERE id_user = ?";
        PreparedStatement p = c.prepareStatement(sql);
        p.setString(1, idusr);
        p.setString(2, usernm);
        p.setString(3, pw);
        p.setString(4, name);
        p.setString(5, hp);
        p.setString(6, almt);
        p.setString(7, userlvl);
        p.setString(8, idusr);

        p.executeUpdate();
        p.close();
        JOptionPane.showMessageDialog(null, "Data Terubah");
        btnsimpan.setEnabled(true);
        btnUpdate.setEnabled(false);
        btndelete.setEnabled(false);
        btn_refresh.setEnabled(false);
        clear();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Update Gagal");
    } finally {
        loadData();
        autonumber();
        btn_refresh.setEnabled(true);
        btnsimpan.setVisible(true);
        btnUpdate.setVisible(false);
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void penggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaMouseClicked
        jTable1.clearSelection();
        clear();
        loadData();
        btndelete.setEnabled(false);
        btn_refresh.setEnabled(true);
        txt_iduser.setEnabled(false);
        autonumber();
    }//GEN-LAST:event_penggunaMouseClicked

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historyActionPerformed
        this.setVisible(false);
        login.riwayatLogin history = new riwayatLogin();
        halamanAdmin.mainAdmin.jDesktopPane1.add(history).setVisible(true);
        halamanAdmin.mainAdmin.judulMenu.setText("/ Riwayat Login");
    }//GEN-LAST:event_btn_historyActionPerformed

    private void txt_nohpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nohpKeyReleased
        String nohp = txt_nohp.getText();
        if (nohp.matches("[0-9+]*") && nohp.length() >= 7 && nohp.length() <= 14) {
            txt_nohp.setBackground(Color.white);
        }else if("".equals(nohp)){
            txt_nohp.setBackground(Color.white);
        } 
        else {
            txt_nohp.setBackground(new Color(254, 161, 161));
        }
    }//GEN-LAST:event_txt_nohpKeyReleased

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        txt_password.requestFocus(true);
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        txt_namauser.requestFocus(true);
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_namauserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namauserActionPerformed
        txt_nohp.requestFocus(true);
    }//GEN-LAST:event_txt_namauserActionPerformed

    private void txt_nohpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nohpActionPerformed
        txt_alamat.requestFocus(true);
    }//GEN-LAST:event_txt_nohpActionPerformed

    private void txt_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_alamatActionPerformed
        combo_idlevel.requestFocus(true);
    }//GEN-LAST:event_txt_alamatActionPerformed
        public void run() {
            new pnlPengguna().setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnUpdate;
    private javax.swing.JButton btn_history;
    public javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_supplier;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> combo_idlevel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private custom.panelcustom panelcustom1;
    private custom.panelcustom panelcustom2;
    private custom.panelcustom panelcustom3;
    private custom.panelcustom panelcustom4;
    private javax.swing.JPanel pengguna;
    private javax.swing.JTextField txt_alamat;
    public javax.swing.JTextField txt_iduser;
    private javax.swing.JTextField txt_namauser;
    private javax.swing.JTextField txt_nohp;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_username;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
