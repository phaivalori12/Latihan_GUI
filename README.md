# Latihan_GUI
# Eksplorasi Java Swing GUI - Praktikum PBO

Aplikasi desktop sederhana berbasis Java Swing untuk manajemen input data mahasiswa yang menerapkan 5 komponen utama arsitektur GUI Java serta penanganan event (*Event Handling*).

## 5 Komponen Utama GUI yang Digunakan

### 1. Top-Level Container Class
* **`javax.swing.JFrame`**: Digunakan sebagai jendela utama aplikasi (`ManajemenMahasiswaGUI extends JFrame`) dengan operasi penutupan `EXIT_ON_CLOSE`.
* **`javax.swing.JOptionPane`**: Digunakan untuk menampilkan dialog pop-up informasi (`showMessageDialog`), peringatan validasi input kosong, dan dialog konfirmasi hapus data (`showConfirmDialog`).

### 2. Intermediate Container Class
* **`javax.swing.JPanel`**: Digunakan untuk mengelompokkan komponen secara terstruktur (`panelForm`, `panelGender`, `panelTombol`, dan `panelTengah`).
* **`javax.swing.JScrollPane`**: Digunakan sebagai wadah pembungkus komponen tabel (`JTable`) agar mendukung *scrolling* ketika baris data bertambah banyak.

### 3. Atomic Component Class
* **`javax.swing.JLabel`**: Menampilkan judul aplikasi dan label keterangan pada setiap input form.
* **`javax.swing.JTextField`**: Menerima input teks berupa NIM dan Nama Lengkap mahasiswa.
* **`javax.swing.JComboBox`**: Menyediakan pilihan *dropdown* untuk Program Studi.
* **`javax.swing.JRadioButton` & `javax.swing.ButtonGroup`**: Menyediakan pilihan opsi tunggal untuk Jenis Kelamin.
* **`javax.swing.JButton`**: Tombol eksekusi aksi (`Tambah Data`, `Reset Form`, dan `Hapus Baris Terpilih`).
* **`javax.swing.JTable`**: Menampilkan daftar data mahasiswa yang telah diinput dalam bentuk baris dan kolom.

### 4. Layout Classes (`java.awt`)
Program ini menggabungkan 3 jenis *Layout Manager*:
* **`java.awt.BorderLayout`**: Digunakan sebagai layout utama pada `ContentPane` (`NORTH` untuk judul, `CENTER` untuk area form & tombol, dan `SOUTH` untuk area tabel) serta pada `panelTengah`.
* **`java.awt.GridLayout`**: Digunakan pada `panelForm` (`GridLayout(4, 2, 10, 10)`) untuk menyusun label dan kolom input secara rapi dalam 4 baris dan 2 kolom.
* **`java.awt.FlowLayout`**: Digunakan pada `panelGender` dan `panelTombol` untuk menyusun radio button dan tombol aksi secara sejajar horizontal.

### 5. Event Listener & Event Adapter Classes (`java.awt.event`)
* **`java.awt.event.ActionListener` (Interface)**: Diimplementasikan langsung oleh kelas utama dengan meng-override method `actionPerformed(ActionEvent e)` untuk menangani klik pada tombol `Tambah Data`, `Reset Form`, dan `Hapus Baris Terpilih`.
* **`java.awt.event.KeyAdapter` (Adapter Class)**: Digunakan pada `txtNim` dengan meng-override method `keyTyped(KeyEvent e)` untuk membatasi panjang maksimal input NIM sebanyak 11 karakter.
* **`java.awt.event.MouseAdapter` (Adapter Class)**: Digunakan pada `tabelMahasiswa` dengan meng-override method `mouseClicked(MouseEvent e)` untuk menyalin data baris yang diklik kembali ke dalam form input.

---

## Screenshot Hasil Program

![Screenshot Aplikasi GUI](screenshot.png)