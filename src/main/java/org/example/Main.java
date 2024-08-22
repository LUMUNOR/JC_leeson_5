package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println(Backup());// Задание 1
        FreeByteMaker();// Задание 2
    }
    //1. Написать функцию, создающую резервную копию всех файлов в директории
    //   (без поддиректорий) во вновь созданную папку ./backup
    public static String Backup(){
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of("."))) {
            Files.createDirectory(Path.of("./backup"));
            for (Path file : dir) {
                if (Files.isDirectory(file)) continue;
                Files.copy(file, Path.of("./backup/" + file.toString()));
            }
        } catch (IOException e){
            return "Бекап не удался(";
        }
        return "Бекап удался!";
    }

    //2. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон
    //[0, 3], и представляют собой, например, состояния ячеек поля для игры в крестики-
    //нолики, где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,
    //3 – резервное значение. Такое предположение позволит хранить в одном числе
    //типа int всё поле 3х3. Записать в файл 9 значений так, чтобы они заняли три
    // байта.
    public static void FreeByteMaker() {
        int[] ar2 = {0, 1, 2, 3, 0, 1, 2, 3, 0};
        try (FileOutputStream fos = new FileOutputStream("save1.out")) {
            for (int b = 0; b < 3; b++) { // запись 3-х байт
                byte wr = 0;
                for (int v = 0; v < 3; v++) { // заполнение байта по 2 бита за итерцию двоичным представлением чисел[0:3]
                    wr += (byte) (ar2[3 * b + v] << (v * 2));
                }
                fos.write(wr);
            }
            fos.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}