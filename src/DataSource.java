import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author Stamatis Pitsios
 * 
 * Every instance of this class will be used for reading data records from files.
 */
public class DataSource 
{
	/**
	 * The object for reading from file.
	 */
	private BufferedReader reader;
	
	/**
	 * The relative path of the file.
	 */
	private String theFile;
	
	
	/**
	 * Creates a new DataSource instance.
	 * 
	 * @param file The path of file with the item.
	 */
	public DataSource(String file) 
	{
		theFile = file;		
	}
	
	
	/**
	 * Reads the item with identified by the given key.
	 * 
	 * @param key The key to identify the item.
	 * 
	 * @return The value of the item in String format.
	 * 
	 * @throws IOException if something "bad" happens while parsing the text file.
	 */
	public String readItem(String key) throws IOException
	{
		reader = new BufferedReader(new FileReader(theFile));
		String line = null;
		String data = null;
		
		while((line = reader.readLine())!= null)
		{
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			String firstToken = tokenizer.nextToken();
			
			if(key.equals(firstToken))
			{
				data = tokenizer.nextToken();
			}
		}
		
		reader.close();
		return data;
	}	
}