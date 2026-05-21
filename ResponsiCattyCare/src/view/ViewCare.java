/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Cats;
import controller.CareController;        
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ViewCare extends JFrame {
    private JTextField txtPemilik, txtKucing, txtNomor, txtLama, txtBiaya;
    private JButton btnTambah, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;
    private CareController controller;

    public ViewCare() {
        controller = new CareController();
        setTitle("Perpustakaan Digital"); 
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

     
        JLabel lblPemilik = new JLabel("Nama Pemilik"); lblPemilik.setBounds(550, 20, 100, 25); add(lblPemilik); 
        txtPemilik = new JTextField(); txtPemilik.setBounds(650, 20, 120, 25); add(txtPemilik);

        JLabel lblKucing = new JLabel("Nama Kucing"); lblKucing.setBounds(550, 60, 100, 25); add(lblKucing); 
        txtKucing = new JTextField(); txtKucing.setBounds(650, 60, 120, 25); add(txtKucing);

        JLabel lblNomor = new JLabel("Nomor Telepon"); lblNomor.setBounds(550, 100, 100, 25); add(lblNomor); 
        txtNomor = new JTextField(); txtNomor.setBounds(650, 100, 120, 25); add(txtNomor);

        JLabel lblLama = new JLabel("Lama Penitipan"); lblLama.setBounds(550, 140, 100, 25); add(lblLama); 
        txtLama = new JTextField(); txtLama.setBounds(650, 140, 120, 25); add(txtLama);

        JLabel lblBiaya = new JLabel("Biaya"); lblBiaya.setBounds(550, 180, 100, 25); add(lblBiaya); 
        txtBiaya = new JTextField(); txtBiaya.setBounds(650, 180, 120, 25); add(txtBiaya);

        // Buttons
        btnTambah = new JButton("Tambah"); btnTambah.setBounds(550, 280, 100, 30); add(btnTambah); 
        btnUpdate = new JButton("Update"); btnUpdate.setBounds(670, 280, 100, 30); add(btnUpdate); 
        btnDelete = new JButton("Delete"); btnDelete.setBounds(550, 320, 100, 30); add(btnDelete); 
        btnClear = new JButton("Clear"); btnClear.setBounds(670, 320, 100, 30); add(btnClear); 

  
        String[] columns = {"Nama Pemilik", "Nama Kucing", "Nomor Telepon", "Lama Penitipan", "Biaya"}; 
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 500, 400);
        add(sp);

        loadData();

  
        btnTambah.addActionListener(e -> {
            try {
                Cats b = new Cats(
                    txtPemilik.getText(), txtKucing.getText(), Integer.parseInt(txtNomor.getText()),
                    Integer.parseInt(txtLama.getText()), Integer.parseInt(txtBiaya.getText())
                );
                controller.createCats(b);
                JOptionPane.showMessageDialog(this, "Data Penitipan Berhasil Ditambahkan", "Message", JOptionPane.INFORMATION_MESSAGE); 
                loadData(); clearFields();
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Pastikan format angka untuk hari benar."); 
            }
        });

        btnUpdate.addActionListener(e -> {
            Cats b = new Cats(
                txtPemilik.getText(), txtKucing.getText(), Integer.parseInt(txtNomor.getText()),
                    Integer.parseInt(txtLama.getText()), Integer.parseInt(txtBiaya.getText())
            );
            controller.updateCats(b);
            loadData(); clearFields();
        });

        btnDelete.addActionListener(e -> {
            controller.deleteCats(txtPemilik.getText());
            loadData(); clearFields();
        });

        btnClear.addActionListener(e -> clearFields());

        // Mengambil data saat baris tabel diklik
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtPemilik.setText(tableModel.getValueAt(row, 0).toString());
                txtKucing.setText(tableModel.getValueAt(row, 1).toString());
                txtNomor.setText(tableModel.getValueAt(row, 2).toString());
                txtLama.setText(tableModel.getValueAt(row, 3).toString());
                txtBiaya.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Cats> cats = controller.readCats();
        for (Cats b : cats) {
            tableModel.addRow(new Object[]{
                b.getOwner(), b.getName(), b.getNumber(),
                b.getDays(), b.getFee()
            });
        }
    }

    private void clearFields() {
        txtPemilik.setText(""); txtKucing.setText(""); txtNomor.setText("");
        txtLama.setText(""); txtBiaya.setText("");
    }
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewCare().setVisible(true));
    }
}
