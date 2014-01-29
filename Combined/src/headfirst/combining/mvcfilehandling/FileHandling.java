package headfirst.combining.mvcfilehandling;

import java.awt.event.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.zip.*;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import sun.dc.pr.PathStroker;

public class FileHandling implements IFileHandler {

	private static final int  BUFFER_SIZE = 4096;

	public static String fileType = ".txt";
	String zipFileName;
	File fileName;
	File zipSource;
	File zipDest;
	File sourceFile;
	File destFile;
	JFileChooser fileSave = new JFileChooser();
	JFileChooser fileSource = new JFileChooser();
	JFileChooser fileDest = new JFileChooser();
	
	public FileHandling() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveFile(String bpm) {
		System.out.println("FileHandling.saveFile() " + bpm);
		//triggered when user hits 'Save'
		//bring up a file dialog box
		//let the user name and save the set
		//bring up dialog box and wait until user chooses 'Save'
		//all file navigation and selection is done by FileChooser
		fileSave.showSaveDialog(null);
		
		//iterate through the list of cards, and write each one out to a txt file
		//in a parseable way (with clear separations between parts)
		try {
			//chain BufferedWriter and FileWriter for efficient saving
			fileName = fileSave.getSelectedFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + fileType));
			
			//write systime and bpm value into file separated by '/'
			//newline character '\n'
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			writer.write(timestamp + "/");
			writer.write(bpm + "\n");
				
			zipFileName = fileName.getName() + fileType;
			System.out.println("zipFileName: " + zipFileName);
			writer.close();

		} catch (IOException e) {
			System.out.println ("Failed to save the file...");
			e.printStackTrace();
		}
	}

	@Override
	public void moveFile() {
		System.out.println("FileHandling.moveFile()");
		
		fileSource.showOpenDialog(null);
		System.out.println("fileA.getSelectedFile() " + fileSource.getSelectedFile());
		
		try {
			sourceFile = fileSource.getSelectedFile();
			System.out.println("sourceFile: " + sourceFile);
			
			fileDest.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileDest.showOpenDialog(null);
			System.out.println("fileDest " + fileDest.getSelectedFile());
			destFile = fileDest.getSelectedFile();
			System.out.println("destFile " + destFile + "\n" + (destFile.toString() + System.getProperty("file.separator") + sourceFile.getName()));
			
			if(sourceFile.renameTo(new File(destFile.toString() + System.getProperty("file.separator") + sourceFile.getName()))) {
				System.out.println("File is moved successfully!");
			} else {
				System.out.println("File is failed to move!");
			}
	 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void zipFile(String bpm) {
		System.out.println("FileHandling.zipFile() " + bpm);
		saveFile(bpm);
		
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			FileOutputStream fos = new FileOutputStream(fileSave.getSelectedFile() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(zipFileName);
    		zos.putNextEntry(ze);
    		FileInputStream in = new FileInputStream(fileName + fileType);
    		
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			System.out.println("in while");
    			zos.write(buffer, 0, len);
    		}
 
    		in.close();
    		zos.closeEntry();
 
    		//close it
    		zos.close();
 
    		System.out.println("Done");
		
		} catch(IOException ex){
	    	   ex.printStackTrace();
    	}
	}

	@Override
	public void unzipFile() {
		System.out.println("FileHandling.unzipFile()");
		
		fileSource.showOpenDialog(null);
		zipSource = fileSource.getSelectedFile();
		System.out.println("zipSource " + zipSource);
		
		fileDest.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileDest.showOpenDialog(null);
		zipDest = fileDest.getSelectedFile();
		System.out.println("zipDest " + zipDest);
		//extract 
		extract(zipSource, zipDest);
	}
	
    private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException {
		System.out.println("FileHandling.extractFile()\nin: " + in + "\noutdir: " + outdir + "\nname: " + name);
    	byte[] buffer = new byte[BUFFER_SIZE];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir,name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1) {
			out.write(buffer, 0, count);
		}
		out.close();
	}

	private static void mkdirs(File outdir,String path) {
		System.out.println("FileHandling.mkdirs()\noutdir: " + outdir + "\npath: " + path);
		File d = new File(outdir, path);
		System.out.println("d: " + d);
		//check if dir exists
		if(!d.exists()) {
			System.out.println("FileHandling.mkdirs() d.mkdirs()");
			//create dir
			d.mkdirs();
		}
	}

	private static String dirpart(String name) {
		System.out.println("FileHandling.dirpart() " + name);
		int s = name.lastIndexOf(File.separatorChar);
		return s == -1 ? null : name.substring(0, s);
	}

	/***
     * Extract zipfile to outdir with complete directory structure
     * @param zipfile Input .zip file
     * @param outdir Output directory
     */
	public static void extract(File zipfile, File outdir) {
		System.out.println("FileHandling.extract() \ninpdir: " + zipfile + "\noutdir: " + outdir);
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
			ZipEntry entry;
			String name, dir;
			while ((entry = zin.getNextEntry()) != null) {
				name = entry.getName();
				System.out.println("name: " + name);
				if(entry.isDirectory()) {
					System.out.println("entry.isDirectory() " + entry);
					mkdirs(outdir,name);
					continue;
				}
				/* this part is necessary because file entry can come before
				 * directory entry where is file located
				 * i.e.:
				 * /foo/foo.txt
				 * /foo/
				 */
				dir = dirpart(name);
				System.out.println("dir " + dir);
				if(dir != null) {
					System.out.println("dir != null");
					mkdirs(outdir,dir);
				}
				extractFile(zin, outdir, name);
			}
			zin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}