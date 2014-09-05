import java.io.IOException;


/** 
 * @author Stamatis Pitsios
 *
 * This class provides the main function for testing the performance of our cache.
 */
public class TestCacheSpeed 
{
	public static void main(String[] args) throws IOException 
	{
		CacheImpl<String, String> cache = new CacheImpl<String,String>(500);
		String requestFile = "datasets/dataset-50000/workload-500000.dat";
		String dataFile = "datasets/dataset-50000/data-50000.dat";

		DataSource dataReader = new DataSource(dataFile);
		WorkloadReader wreader = new WorkloadReader(requestFile);
		
		String key = null;		
		
		/*
		 * Start speed test.
		 */
		long startTime = System.currentTimeMillis();
		
		while ((key = wreader.nextRequest()) != null) 
		{
			String data = cache.lookUp(key);
			
			if (data == null) 
			{
				data = dataReader.readItem(key);
				
				if (data == null) 
				{
					System.out.println("DID NOT FIND KEY " + key);
				}
				
				cache.store(key, data);
			}
		}

		/*
		 * Speed test finished
		 */
		double duration = (double)(System.currentTimeMillis() - startTime)/(double)1000;
		
		System.out.println("Lookups : " + cache.getNumberOfLookUps());
		System.out.println("Hits : " + cache.getHits());
		System.out.println("Misses : " + cache.getMisses());
		System.out.println("Hitratio: " + cache.getHitRatio() * 100 + "%");
		System.out.println("Duration time : " + duration + " seconds");
                
		wreader.close();      
	}
}