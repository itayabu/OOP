import java.util.*;
public class CollectionFacadeSet implements SimpleSet {
	
	protected Collection<String> collection;
	int size;
	public CollectionFacadeSet(Collection<String> collection){
		this.collection = collection;
		size=0;
	}
	@Override
	public boolean add(String newValue) {
		if ( !this.contains(newValue)){
			collection.add(newValue);
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String searchVal) {
		if ((collection != null) && (collection.contains(searchVal))){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		if (this.contains(toDelete)){
			this.collection.remove(toDelete);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}
