package it.geek.file.test;

public class FileTest {

	public static void main(String[] args) {
		
		String sourcePath = "/copie/fileDiTest.txt";
		String destPath = "/copie/fileDiTest_copia.txt";
		
		FileUtil.copiaFile(sourcePath, destPath);
		
	}

}
