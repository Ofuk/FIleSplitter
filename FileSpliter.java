package CODING;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSpliter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file (beserta ekstensi): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per potongan: ");
        int linesPerPart = scanner.nextInt();
        scanner.nextLine();

        Queue<String> fileParts = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder currentPart = new StringBuilder();
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                currentPart.append(line).append("\n");
                lineCount++;

                if (lineCount == linesPerPart) {
                    fileParts.add(currentPart.toString());
                    currentPart = new StringBuilder();
                    lineCount = 0;
                }
            }

            if (currentPart.length() > 0) {
                fileParts.add(currentPart.toString());
            }
            
            int partNumber = 1;
            while (!fileParts.isEmpty()) {
                System.out.println("Potongan " + partNumber + ":");
                System.out.println(fileParts.poll());
                partNumber++;
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}
