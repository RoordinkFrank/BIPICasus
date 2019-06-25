package fileManagment.fileManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Can only be used for text related filetypes. For example .java, .txt.
 * This class cann't write or read images etc (not text related).
 * 
 */
public class FileManager{
	private static final String THE_SPECIFIED_FILE_IS_A_DIRECTORY_OR_CAN_NOT_BE_CREATED_THE_SPECIFIED_MAP_DOES_NOT_EXIST_OR_OPENED_FOR_ANY_OTHER_REASON = "The specified file is a directory, or can not be created, the specified map does not exist or opened for any other reason";
	private static final String THE_FILE_READER_IS_ALREADY_CLOSED = "The fileReader is already closed";
	private static final String THE_FILE_WRITER_IS_ALREADY_CLOSED = "The fileWriter is already closed";
	private static final String A_FILE_WRITER_CONNECTION_DOES_NOT_SEEM_TO_BE_OPEN = "A fileWriter connection does not seem to be open";
	private static final String AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED = "An IOException seems to have occured";
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;
	
	/**
	   * Opens a write connection with a file.
	   * @param fileType filetype without the dot (example .exe)
	   * @param fileName name of the file
	   * @exception FileManagerException
	   * 	if specified file is a directory or some unknown error has occured
	   */
	public void openFileWriter(String fileName, String fileType) throws FileManagerException{
		openFileWriter("", fileName, fileType);
	}
	
	/**
	 * @param fileLocation example "C:\Users\Frank\Documents\HU\OwnProjects\UMLParser"
	 * @see #openFileWriter(fileName, fileType)
	 */
	
	public void openFileWriter(String fileLocation , String fileName, String fileType) throws FileManagerException{
		String filePath = "";
		if (!fileLocation.equals("")){
			filePath+=fileLocation+"/";
		}
		filePath+=fileName+"."+fileType;
		
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e) {
			throw new FileManagerException(filePath+THE_SPECIFIED_FILE_IS_A_DIRECTORY_OR_CAN_NOT_BE_CREATED_THE_SPECIFIED_MAP_DOES_NOT_EXIST_OR_OPENED_FOR_ANY_OTHER_REASON);
		}
	}
	
	/**
	 * 
	 * @param fileName name of the file
	 * @param fileType filetype, (example java, so not .java)
	 * @throws FileManagerException
	 */
	
	public void openFileReader(String fileName, String fileType) throws FileManagerException{
		openFileReader("", fileName, fileType);
	}
	
	/**
	   * Opens a write connection with a file.
	   * @param filePath / at the end is not needed, "" if there is no patch needed.
	   * @exception FileManagerException
	   * 	if specified file is a directory, does not exist or some unknown error has occured
	   * @see #openFileReader(fileName, fileType)
	   */
	public void openFileReader(String filePath, String fileName, String fileType) throws FileManagerException{
		   try {
			if ("".equals(filePath)){
				bufferedReader = new BufferedReader(new FileReader(fileName+"."+fileType));
			}
			else {
				FileReader f = new FileReader(filePath+"/"+fileName+"."+fileType);
				bufferedReader = new BufferedReader(f);
			}
			} catch (FileNotFoundException e) {
			throw new FileManagerException(THE_SPECIFIED_FILE_IS_A_DIRECTORY_OR_CAN_NOT_BE_CREATED_THE_SPECIFIED_MAP_DOES_NOT_EXIST_OR_OPENED_FOR_ANY_OTHER_REASON+", Message:" + e.getMessage());
		}
	}
	
	/**
	   * writes text to a file.
	   */
	public void write(String text) throws FileManagerException{
		try {
			bufferedWriter.write(text);
		} catch (IOException e) {
			throw new FileManagerException(AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED);
		} catch (NullPointerException e){
			throw new FileManagerException(A_FILE_WRITER_CONNECTION_DOES_NOT_SEEM_TO_BE_OPEN);
		}
	}
	
	/**
	   * writes text to a file and the writer does an enter
	   * won't work if the connection is not closed afterwards.
	   * @exception FileManagerException
	   *   possible can be if a fileWriter is not open
	   */
	public void writeLine(String text) throws FileManagerException{
		try {
			bufferedWriter.write(text);
	        bufferedWriter.newLine();
		} catch (IOException e) {
			throw new FileManagerException(AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED);
		} catch (NullPointerException e){
			throw new FileManagerException(A_FILE_WRITER_CONNECTION_DOES_NOT_SEEM_TO_BE_OPEN);
		}
	}
	
	/**
	   * Closes a write connection with a file.
	   * @exception FileManagerException
	   *   if the connection was not open to begin with
	   */
	public void closeFileWriter() throws FileManagerException{
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			throw new FileManagerException(AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED);
		} catch (NullPointerException e){
			throw new FileManagerException(THE_FILE_WRITER_IS_ALREADY_CLOSED);
		}
	}
	
	/**
	   * reads text from an file.
	   * @return a line of text found in the specified document
	   * @exception FileManagerException
	   *   possible can be if a fileWriter is not open
	   */
	public String readLine() throws FileManagerException{
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			throw new FileManagerException(AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED);
		} catch (NullPointerException e){
			throw new FileManagerException(A_FILE_WRITER_CONNECTION_DOES_NOT_SEEM_TO_BE_OPEN);
		}
	}
	
	/**
	   * Closes a read connection with a file.
	   * @exception FileManagerException
	   *   if the connection was not open to begin with
	   */
	public void closeFileReader() throws FileManagerException{
		try {
			bufferedReader.close();
		} catch (IOException e) {
			throw new FileManagerException(AN_IO_EXCEPTION_SEEMS_TO_HAVE_OCCURED);
		} catch (NullPointerException e){
			throw new FileManagerException(THE_FILE_READER_IS_ALREADY_CLOSED);
		}
	}
	
	/**
	   * finalize is called if the object is destroyed. The garbargecollector is however unreliable with
	   * destroying objects. You never know when it happens. Do not rely on this method to close your connections.
	   * It is just a backup in case it happens as there is a good chance but no guarantee that the object will be
	   * destroyed and thus this method called
	   * only the garbagecollector is supposed to use this method
	   */

	protected void finalize(){
		//can not been thrown as there will be a memory leak if that happens
		try{
			closeFileReader();
		}
		catch(Exception e){}//nullpointer and filemanager included
		try{
			closeFileWriter();
		}
		catch(Exception e){}//nullpointer and filemanager included
	}
}
