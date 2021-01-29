package com.ocr.thainoi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.sourceforge.tess4j.Tesseract;

@SpringBootApplication
public class OcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrApplication.class, args);

		Path root_traindata_path = Paths.get("tesseract_traindata");
		Path input_file_Path = Paths.get("uploads");
		try {
			Tesseract tesse = new Tesseract();
			tesse.setDatapath(root_traindata_path.getFileName().toString()); // set ที่อยู่ traindata
			tesse.setLanguage("thainoi"); // OCR ด้วยภาษาไทย
			// tesse.setOcrEngineMode(1); // set OCR Engine mode เป็น LSTM
			String fullText = tesse.doOCR(new File(input_file_Path.getFileName().toString() + "/t8.png")); // ทำการแปลงจาก
			// รูป เป็น
			// text
			System.out.println(fullText);
			System.out.println(writeFile(fullText));
		} catch (Exception e) {
			System.out.println(e);
		}
		///
	}

	public static String writeFile(String text) {
		Path output_file_path = Paths.get("output");
		try {
			Files.write(Paths.get(output_file_path.getFileName().toString() + "/o.txt"), text.getBytes()); // เขียนไฟล์
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "writed to folder.";
	}
}
