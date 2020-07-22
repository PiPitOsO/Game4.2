package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

        ArrayList<String> listSave = new ArrayList<>();
        listSave.add("savegames/save1.data");
        listSave.add("savegames/save2.data");
        listSave.add("savegames/save3.data");

        saveGame(way1, save1);
        saveGame(way2, save2);
        saveGame(way3, save3);
        zipFiles(listSave);
        delGame(way1);
        delGame(way2);
        delGame(way3);

    }

    public static void saveGame(String way, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(save);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(List<String> files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("savegames/zip.zip"))) {
            for (String file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.substring(file.lastIndexOf('s')));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void delGame(String way) {
        File del = new File(way);
        if (del.delete()) {
            System.out.println(way + "Удалён");
        } else
            System.out.println(way + "Файл не был найден");
    }
}