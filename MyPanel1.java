//PROGRAM UNTUK KONVERSI STRING KE KODE MORSE & AUDIO
//RESPONSI 2 Praktikum Pemrograman Berorientasi Objek
//BINTANG PRADANA ERLANGGA PUTRA
//M0518010

//MENERAPKAN 5 KONSEP
//1. Collections (Hash Map)
//2. String Operation (Concatenate)
//3. Regular Expression
//4. Exception Handling
//5. IO Operation

//package tempat class berada
package responsi2;

//mengimport class dan package yang dibutuhkan
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//deklarasi class MyPanel yang merupakan turunan JPanel
public class MyPanel extends JPanel {
    //deklarasi komponen GUI
    private JLabel label1;
    private JLabel label2;
    private JTextArea outputBox;
    private JButton openButton;
    private JButton toMorseButton;
    private JTextArea inputBox;
    private JButton playButton;
    private JFileChooser fileChooser;
    private JScrollPane scrollInput;
    private JScrollPane scrollOutput;
    private morse m;

    //constructor class MyPanel
    public MyPanel() {
        //construct components
        label1 = new JLabel ("Input Text");
        label2 = new JLabel ("Morse Code");
        outputBox = new JTextArea (5, 5);
        outputBox.setEditable(false);
        openButton = new JButton ("Open");
        toMorseButton = new JButton("Encode");
        inputBox = new JTextArea (5, 5);
        inputBox.setEditable(true);
        playButton = new JButton ("Play");
        fileChooser = new JFileChooser();
        scrollInput = new JScrollPane(inputBox);
        scrollOutput = new JScrollPane(outputBox);
        m = new morse();

        //deklarasi ukuran window
        setPreferredSize (new Dimension (400, 375));
        setLayout (null);

        //menambahkan komponen
        add (label1);
        add (label2);
        add (scrollOutput);
        add (openButton);
        add (toMorseButton);
        add (scrollInput);
        add (playButton);

        //men-set posisi dan ukuran komponen
        label1.setBounds (5, 5, 100, 25);
        label2.setBounds (5, 190, 100, 25);
        scrollOutput.setBounds (5, 215, 300, 150);
        openButton.setBounds (315, 30, 80, 30);
        toMorseButton.setBounds (315, 65, 80, 30);
        scrollInput.setBounds (5, 30, 300, 150);
        playButton.setBounds (315, 215, 80, 30);

        //jika tombol open diklik maka akan menjalankan method BrowseFileActionPerformed
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BrowseFileActionPerformed(evt);
            }
        });

        //jika tombol encode diklik maka akan mengubah text input menjadi kode morse
        toMorseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String data = m.prePorcess(inputBox.getText().toString().toLowerCase());
                outputBox.setText(m.toMorse(data));
            }
        });
        
        //jika tombol play diklik maka akan mengubah kode morse menjadi suara
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                m.toSound(outputBox.getText());
            }
        });
    }
    
    //method untuk membuka file browser
    private void BrowseFileActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BrowseFileActionPerformed
        if (fileChooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            FileReader file;
            //mencoba untuk membuka file yang dipilih pengguna
        	try {
                //implementasi konsep 5.IO Operation
        		file = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
        		inputBox.read(file, null);
        		file.close();
                String data = m.prePorcess(inputBox.getText().toString().toLowerCase());
                outputBox.setText(m.toMorse(data));
            }
            //mencetak 'file not found!' jika file tidak ditemukan
            //implementasi konsep 4.Exception Handling
            catch (FileNotFoundException ex) {
        		System.out.println("File not found!");
            }
            //mencetak 'IOException!' jika terjadi IO Exception
            //implementasi konsep 4.Exception Handling
            catch (IOException ex) {
        		System.out.println("IOException!");
        	}
        }
    }

    //fungsi utama program
    public static void main (String[] args) {
        //membuat window dengan nama 'text to morse code'
        JFrame frame = new JFrame ("Text to Morse Code");
        //keluar dari program jika tombol silang diklik
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        //menambah MyPanel pada window frame
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        //menampilkan window beserta isinya
        frame.setVisible (true);
    }
}
