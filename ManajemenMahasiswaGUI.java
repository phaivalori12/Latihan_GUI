import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManajemenMahasiswaGUI extends JFrame implements ActionListener {

    // Atomic Components
    private JTextField txtNim, txtNama;
    private JComboBox<String> cbProdi;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgGender;
    private JButton btnTambah, btnReset, btnHapus;
    private JTable tabelMahasiswa;
    private DefaultTableModel tableModel;

    public ManajemenMahasiswaGUI() {
        // 1. Konfigurasi Top-Level Container (JFrame)
        setTitle("Tugas PBO - Eksplorasi Java Swing GUI");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Jendela muncul di tengah layar

        // Mengambil Content Pane dan mengatur Layout Utama (BorderLayout)
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));

        // --- BAGIAN ATAS (NORTH): Header ---
        JLabel lblHeader = new JLabel("FORM DATA MAHASISWA INFORMATIKA", SwingConstants.CENTER);
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        contentPane.add(lblHeader, BorderLayout.NORTH);

        // --- BAGIAN TENGAH (CENTER): Intermediate Container (JPanel) + GridLayout ---
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Input Biodata"));

        // Baris 1: NIM
        panelForm.add(new JLabel("NIM:"));
        txtNim = new JTextField();
        // Contoh Event Adapter Class (KeyAdapter) untuk membatasi input NIM hanya huruf/angka tertentu
        txtNim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtNim.getText().length() >= 11) {
                    e.consume(); // Batasi maksimal 11 karakter (contoh: F1D02410091)
                }
            }
        });
        panelForm.add(txtNim);

        // Baris 2: Nama Lengkap
        panelForm.add(new JLabel("Nama Lengkap:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        // Baris 3: Program Studi (JComboBox)
        panelForm.add(new JLabel("Program Studi:"));
        String[] daftarProdi = {"Teknik Informatika", "Teknik Elektro", "Teknik Sipil", "Teknik Mesin"};
        cbProdi = new JComboBox<>(daftarProdi);
        panelForm.add(cbProdi);

        // Baris 4: Jenis Kelamin (Intermediate Container JPanel + FlowLayout + JRadioButton)
        panelForm.add(new JLabel("Jenis Kelamin:"));
        JPanel panelGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rbLaki = new JRadioButton("Laki-laki", true);
        rbPerempuan = new JRadioButton("Perempuan");
        bgGender = new ButtonGroup();
        bgGender.add(rbLaki);
        bgGender.add(rbPerempuan);
        panelGender.add(rbLaki);
        panelGender.add(rbPerempuan);
        panelForm.add(panelGender);

        // --- BAGIAN BAWAH FORM: Intermediate Container (JPanel) + FlowLayout untuk Tombol ---
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnTambah = new JButton("Tambah Data");
        btnReset = new JButton("Reset Form");
        btnHapus = new JButton("Hapus Baris Terpilih");

        // Mendaftarkan Event Listener (ActionListener) ke setiap tombol
        btnTambah.addActionListener(this);
        btnReset.addActionListener(this);
        btnHapus.addActionListener(this);

        panelTombol.add(btnTambah);
        panelTombol.add(btnReset);
        panelTombol.add(btnHapus);

        // Menggabungkan panelForm dan panelTombol ke dalam satu panel tengah
        JPanel panelTengah = new JPanel(new BorderLayout(5, 5));
        panelTengah.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        panelTengah.add(panelForm, BorderLayout.CENTER);
        panelTengah.add(panelTombol, BorderLayout.SOUTH);

        contentPane.add(panelTengah, BorderLayout.CENTER);

        // --- BAGIAN BAWAH (SOUTH): Intermediate Container (JScrollPane) + JTable ---
        String[] kolom = {"NIM", "Nama Lengkap", "Program Studi", "Jenis Kelamin"};
        tableModel = new DefaultTableModel(kolom, 0);
        tabelMahasiswa = new JTable(tableModel);

        // Contoh MouseAdapter (Event Adapter Class) saat baris tabel diklik
        tabelMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int baris = tabelMahasiswa.getSelectedRow();
                if (baris != -1) {
                    txtNim.setText(tableModel.getValueAt(baris, 0).toString());
                    txtNama.setText(tableModel.getValueAt(baris, 1).toString());
                    cbProdi.setSelectedItem(tableModel.getValueAt(baris, 2).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabelMahasiswa);
        scrollPane.setPreferredSize(new Dimension(600, 150));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));
        contentPane.add(scrollPane, BorderLayout.SOUTH);
    }

    // Override method dari interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTambah) {
            tambahData();
        } else if (e.getSource() == btnReset) {
            resetForm();
        } else if (e.getSource() == btnHapus) {
            hapusData();
        }
    }

    private void tambahData() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = cbProdi.getSelectedItem().toString();
        String gender = rbLaki.isSelected() ? "Laki-laki" : "Perempuan";

        if (nim.isEmpty() || nama.isEmpty()) {
            // Menggunakan Top-level Dialog (JOptionPane) untuk validasi
            JOptionPane.showMessageDialog(this, "NIM dan Nama tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{nim, nama, prodi, gender});
        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        resetForm();
    }

    private void resetForm() {
        txtNim.setText("");
        txtNama.setText("");
        cbProdi.setSelectedIndex(0);
        rbLaki.setSelected(true);
        txtNim.requestFocus();
    }

    private void hapusData() {
        int selectedRow = tabelMahasiswa.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris pada tabel yang ingin dihapus terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            resetForm();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenMahasiswaGUI().setVisible(true);
        });
    }
}