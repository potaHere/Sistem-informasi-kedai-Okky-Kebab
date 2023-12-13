package halamanAdmin;

public class panelAmbilStok extends javax.swing.JPanel {

    public panelAmbilStok() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spinBalikStok = new javax.swing.JSpinner();
        lblBalikStok = new javax.swing.JLabel();
        btnBalikStok = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spinBalikStok.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        spinBalikStok.setPreferredSize(new java.awt.Dimension(80, 40));
        add(spinBalikStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 13, -1, -1));

        lblBalikStok.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblBalikStok.setText("Kembalikan stok sebanyak?");
        add(lblBalikStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 20, -1, -1));

        btnBalikStok.setBackground(new java.awt.Color(255, 255, 255));
        btnBalikStok.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBalikStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh 30px.png"))); // NOI18N
        btnBalikStok.setText("KEMBALIKAN");
        btnBalikStok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(btnBalikStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 160, 43));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBalikStok;
    private javax.swing.JLabel lblBalikStok;
    private javax.swing.JSpinner spinBalikStok;
    // End of variables declaration//GEN-END:variables
}