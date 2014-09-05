import java.util.Comparator;

/**
 * @author Stamatis Pitsios
 *
 * A comparator implementation for comparing the keys in our data structures.
 */
final class DefaultComparator implements Comparator<Object> 
{
    @SuppressWarnings("unchecked")
	@Override
	public int compare(Object a, Object b) 
    {
		return ((Comparable<Object>)a).compareTo(b);
	}
}