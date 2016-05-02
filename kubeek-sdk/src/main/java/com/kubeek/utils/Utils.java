package com.kubeek.utils;



import java.awt.*;
import java.io.*;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class Utils {

    //private static final Logger log = LogManager.getLogger("Utils");
    private static String OS = null;
    private static String CPU = null;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy - HH:mm:ss.SSS");

    public void test() {
        System.out.println("test");
    }

    public static UUID getUniqueID(){
        return UUID.randomUUID();
    }

    public static String horodatage() {
        Date d = new Date();
        String dt = formatter.format(d);
        return dt;
    }

    public static void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String toAscii(byte[] b) {

        StringBuffer s = new StringBuffer();
        if (b == null) {
            return "";
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] != 0) {
                char in = (char) b[i];
                s.append(in);
            }
        }
        return s.toString();
    }

    public static String byteToString(byte[] b) {

        StringBuffer s = new StringBuffer();
        if (b == null) {
            return "";
        }
        for (int i = 0; i < b.length; i++) {
            s.append(b[i] + ",");
        }
        return s.toString();
    }

    public static byte[] stringToByte(String mot)
            throws UnsupportedEncodingException {

        if (mot.equals(null)) {
            return null;
        }

        return mot.getBytes();
    }

    public static byte[] charToByte(char[] tab) {
        byte[] result = new byte[tab.length];
        for (int i = 0; i < tab.length; i++) {
            result[i] = (byte) tab[i];
        }
        return result;
    }

    public static byte charToByte(char tab) {
        byte result = (byte) tab;

        return result;
    }

    //http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
    public static String getOS() {
        if (OS == null) {
            OS = System.getProperty("os.name").toLowerCase();
        }
        String retour="";

        if(isWindows()){
            retour="windows";
        }else if(isMac()){
            retour="mac";
        }else if(isLinux()){
            retour="linux";
        }

        return retour;
    }

    public static String getCPU(){
        if(CPU == null){
            CPU =System.getProperty("os.arch").toLowerCase();
        }

        String retour="";

        if(is64()){
            retour="x64";
        }else if(isArm()){
            retour="arm";
        }else if(isx86()){
            retour="x86";
        }

        return retour;
    }

    public static boolean isArm() {

        return (CPU.indexOf("arm") >= 0);

    }
    public static boolean isx86() {

        return (CPU.indexOf("86") >= 0);

    }
    public static boolean is64() {

        return (CPU.indexOf("64") >= 0);

    }

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isLinux() {

        return (OS.indexOf("linux") >= 0);

    }

    public static int javaVersion() {
        int version = Integer.parseInt(System.getProperty("java.version").split("\\.")[1]);
        return version;
    }

    public static String path() throws IOException {
        return new java.io.File(".").getCanonicalPath();
    }

    public static Color hex2Rgb(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 0, 2 ), 16 ),
                Integer.valueOf( colorStr.substring( 2, 4 ), 16 ),
                Integer.valueOf( colorStr.substring( 4, 6 ), 16 ) );
    }


    /**
     * Method to print all files/Directory in a Path
     *
     * @param path
     * @param level
     */
    public static void getExplorerDirectoryPrintln(String path, int level) {

        File repertoire = new File(path);
        String[] children = repertoire.list();
        for (int i = 0; i <= children.length - 1; i++) {


                if (new File(path + File.separator + children[i]).isDirectory()) {
                    for (int j = 1; j <= level; j++) {
                        System.out.print(" ");
                    }
                    System.out.println("+" + children[i]);
                    getExplorerDirectoryPrintln(path + File.separator + children[i], level + 1);

                }
                if (new File(path + File.separator + children[i]).isFile()) {
                    for (int j = 1; j <= level; j++) {
                        System.out.print(" ");
                    }
                    System.out.println("  " + children[i]);

                }


        }
    }

    public static ArrayList getFilesList(String path, String extension) {
        ArrayList listing = new ArrayList();
        File repertoire = new File(path);
        String[] children = repertoire.list();
        for (int i = 0; i <= children.length - 1; i++) {
            if (new File(path + File.separator + children[i]).isFile() && children[i].endsWith(extension)) {
                listing.add(children[i].substring(0, children[i].length() - 4));
            }
        }

        return listing;

    }

    public static void removeFile(String pathName) {
        // A File object to represent the filename
        File f = new File(pathName);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            throw new IllegalArgumentException(
                    "Delete: no such file or directory: " + pathName);
        }

        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: "
                    + pathName);
        }

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new IllegalArgumentException(
                        "Delete: directory not empty: " + pathName);
            }
        }

        // Attempt to delete it
        boolean success = f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }

    public static void copyfile(File f1, File f2) {
        try {
            //File f1 = new File(srFile);

            InputStream in = new FileInputStream(f1);

            // For Append the file.
            // OutputStream out = new FileOutputStream(f2,true);

            // For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            //log.debug("File copied.");
        } catch (FileNotFoundException ex) {
            //log.error(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            //log.error(e.getMessage());
        }
    }

    public static String sansAccent(String s) {

       String string = Normalizer.normalize(s, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }
}