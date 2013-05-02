package it.geek.file.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);

	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public static File creaFile(String path) {
		log.info("creaFile::path = "+path);
		
		File file = null;
		
		try {
			file = new File(path);
		    
			if (file.exists())
				log.debug("Il file " + path + " esiste");
			else if (file.createNewFile())
				log.debug("Il file " + path + " è stato creato");
			else
				log.debug("Il file " + path + " non può essere creato");
		}
		catch (IOException e) {
			log.error("errore in creaFile! "+e);
		}
		
		return file;
	}
	
	  public static String leggiFile(File file) {
		  log.info("scriviFile::path = "+(file!=null?file.getAbsolutePath():""));
		  
		  String fileAsString = null;
		  
		  StringBuilder contenuto = null;
		  char[] in = new char[50];
		  int size = 0;
		  try {
		      FileReader fr = new FileReader(file);
		      size = fr.read(in);

		      log.debug("Caratteri presenti: " + size + "\n");

		      log.info("Il contenuto del file è il seguente:\n");

		      contenuto = new StringBuilder();
		      for(int i=0; i<size; i++)
		    	  contenuto.append(in[i]);
		    	  
		      fr.close();
		      
		      log.info(contenuto);
		      fileAsString = contenuto.toString();
		   }
		   catch(IOException e) { 
			   log.error("errore in leggiFile! "+e);
		   }
		  
		  return fileAsString;
	  }	
	
	public static void scriviFile(File file, String cosa) {
		log.info("scriviFile::path = "+(file!=null?file.getAbsolutePath():""));
		
	    try {
	    	FileWriter fw = new FileWriter(file);
	    	BufferedWriter bw = new BufferedWriter(fw);
		    bw.write(cosa);
		    bw.flush();
		    bw.close();
	    }
	    catch(IOException e) {
	    	log.error("errore in scriviFile! "+e);
	    }
	  }	

	  public static void copiaFile(String sourcePath, String destPath){
		  log.info("copiaFile::start");
		  File sourceFile = creaFile(sourcePath);
		  File destFile = creaFile(destPath);
		  
		  String sourceFileAsString = leggiFile(sourceFile);
		  scriviFile(destFile, sourceFileAsString);
		  log.info("copiaFile::end");
	  }
}
