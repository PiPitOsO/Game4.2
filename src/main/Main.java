package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 300, 2, 10.1);
        GameProgress save2 = new GameProgress(77, 234, 4, 25.4);
        GameProgress save3 = new GameProgress(21, 53, 9, 123.4);

        String way1 = "savegames/save1.data";
        String way2 = "savegames/save2.data";
        String way3 = "savegames/save3.data";
        String wayZ = "savegames";
        String[] arrWayZ = {"savegames/save1.data", "savegames/save2.data", "savegames/save3.data"};

        saveGame(way1, save1);
        saveGame(way2, save2);
        saveGame(way3, save3);
        zipFiles(way1);
        zipFiles(way2);
        zipFiles(way3);

    }

    public static void saveGame(String way, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String way) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("savegames/zip.zip"));
            FileInputStream fis = new FileInputStream(way)) {
//            for(int i = 0; i < way.length; i++) {
//                String b = String.valueOf(i);
                ZipEntry entry1 = new ZipEntry("save1.data");
                zout.putNextEntry(entry1);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
//            }
            zout.closeEntry();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}