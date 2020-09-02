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

//mengimport class java yang dibutuhkan
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Map;
import java.util.HashMap;

//deklarasi class morse
public class morse{
    //hashmap untuk menyimpan nilai kode morse dari suatu karakter
    //implementasi konsep 1.Collections
    private Map<Character,String> morseCode = new HashMap<>(){{
        put('a',".- ");
        put('b',"-... ");
        put('c',"-.-. ");
        put('d',"-.. ");
        put('e',". ");
        put('f',"..-. ");
        put('g',"--. ");
        put('h',".... ");
        put('i',".. ");
        put('j',".--- ");
        put('k',"-.- ");
        put('l',".-.. ");
        put('m',"-- ");
        put('n',"-. ");
        put('o',"--- ");
        put('p',".--. ");
        put('q',"--.- ");
        put('r',".-. ");
        put('s',"... ");
        put('t',"- ");
        put('u',"..- ");
        put('v',"...- ");
        put('w',".-- ");
        put('x',"-..- ");
        put('y',"-.-- ");
        put('z',"--.. ");
        put('0',"----- ");
        put('1',".---- ");
        put('2',"..--- ");
        put('3',"...-- ");
        put('4',"....- ");
        put('5',"..... ");
        put('6',"-.... ");
        put('7',"--... ");
        put('8',"---.. ");
        put('9',"----. ");
        put(' ',"/ ");
    }};
    
    //digunakan untuk menyimpan alamat dari file pada harddrive
    private URL t1 = getClass().getResource("sound/1t.wav");
    private URL t3 = getClass().getResource("sound/3t.wav");
    private URL s1 = getClass().getResource("sound/1s.wav");
    private URL s3 = getClass().getResource("sound/3s.wav");
    
    //method untuk mengubah string menjadi kode morse
    public String toMorse(String input){
        String output = "";
        
        for(int i=0;i<input.length();i++){
            //menggabungkan kode morse
            //implementasi konsep 2.String Operation
            output = output.concat(morseCode.get(input.charAt(i)));
        }

        return output;
    }

    //method untuk menghapus semua karakter kecuali a-z, A-Z, 0-9, dan spasi
    //implementasi konsep 3.Regular Expression
    public String prePorcess(String input){
        return input.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
    }
    
    //method untuk memutar file audio berdasarkan alamat file
    public void play(URL url){
        //mencoba untuk memutar audio
        try{
            //mengambil file audio
            //implementasi konsep 5.IO Operation
            File sound = new File(url.getPath());
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        //jika terjadi exception maka akan dicetak
        //implementasi konsep 4.Exception Handling
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //method untuk mengubah kode morse menjadi audio
    public void toSound(String input){
        for(int i=0;i<input.length();i++){
            Character c = input.charAt(i);
            if(c == '.') play(t1);
            else if(c == '-') play(t3);
            else if(c == ' ') play(s1);
            else if(c == '/') play(s3);
        }
    }
}