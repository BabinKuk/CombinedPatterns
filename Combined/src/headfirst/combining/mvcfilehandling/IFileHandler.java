package headfirst.combining.mvcfilehandling;

import javax.swing.JFrame;

public interface IFileHandler {
	public void saveFile(String bpm);
	public void moveFile();
	public void zipFile(String bpm);
	public void unzipFile();
}
