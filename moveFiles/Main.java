package moveFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start project!");
		
		// 1. A폴더, B폴더가 있는지 확인
		
		// 파일 객체 생성
		File fileA = new File("A");
		File fileB = new File("B");
		
		if(fileA.isDirectory() && fileB.isDirectory()) {
			// 2. A폴더에 파일이 있는지 확인 후
			File[] filesA =  fileA.listFiles();
			
			for(File oldFile : filesA){
				
				if(oldFile.isFile()){
					System.out.println("file name : "+ oldFile.getName());
					
					// 3. B폴더로 이동할 것이다.
					InputStream fin = null;
					OutputStream fout = null;
					
					byte[] b = new byte[1024];
					
					String dest = fileB.getPath() + "/" + oldFile.getName();
					System.out.println("destination : " + dest);
					
					File newFile = new File(dest);
					
					try {
						fin = new FileInputStream(oldFile);
						fout = new FileOutputStream(newFile);
						
						int read = 0;
						while((read = fin.read(b, 0, b.length)) != -1){
							fout.write(b, 0, read);
						}
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							fin.close();
							fout.close();
							
							System.out.println("old file exists? : " + oldFile.exists());
							System.out.println("delete success? : " + oldFile.delete());

						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					
					
				}
			}
		}
		
		System.out.println("Done!");
	}
}
