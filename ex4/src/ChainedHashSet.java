import java.lang.String;
import java.util.LinkedList;
public class ChainedHashSet extends SimpleHashSet {

	private final int divFactor=2, doubleFactor=2;
	MyLinkedListFadaceA [] hashTable;
	public ChainedHashSet(){
		super ();
		buildHashSet();
	}

	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor){
		super (upperLoadFactor, lowerLoadFactor);
		buildHashSet();
	}
	public ChainedHashSet(String[] data){
		super ();
		buildHashSet();
		for (int i=0; i<data.length; i++){
			add(data[i]);
		}
	}
	
	private void buildHashSet(){
		hashTable = new MyLinkedListFadaceA[capacity];
		nullify(hashTable, capacity);
	}

	@Override
	public boolean add(String newValue) {
		if ( ! this.contains(newValue)){
			hashTable[hash(newValue)].add(newValue);
			size++;
			if(toExpand()){
				expand();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String searchVal) {
		if (hashTable[hash(searchVal)].contains(searchVal)){
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		if (this.contains(toDelete)){
			hashTable[hash(toDelete)].delete(toDelete);
			size--;
			if (toShrink()){
				shrink();
			}
			return true;
		}
//		System.out.println("oh bummer, something happend");
		return false;
	}
	private int hash(String value){
		return value.hashCode()&(capacity()-1);
	}

	private void nullify( MyLinkedListFadaceA[] Table, int sizeOfTable){
		for (int i = 0; i < sizeOfTable; i++){
			Table[i]=new MyLinkedListFadaceA();
		}
	}
	private void shrink(){
//		System.out.println("-------------shrinking");
		MyLinkedListFadaceA[] oldTable= hashTable;
		int oldCap = capacity;
		size=0;
		capacity = (capacity/ divFactor);
		hashTable = new MyLinkedListFadaceA[capacity];
		nullify(hashTable, capacity);
		for (int i = 0; i < oldCap; i++){
			reHashList(oldTable[i]);
		}
	}
	private void expand(){
//		System.out.println("------expanding");
		MyLinkedListFadaceA [] oldTable= hashTable;
		int oldCap = capacity();
		size=0;
		setCapacity (capacity() * doubleFactor);
		hashTable = new MyLinkedListFadaceA[capacity];
		nullify(hashTable, capacity);
		for (int i = 0; i < oldCap; i++){
			reHashList(oldTable[i]);
		}
	}
	private void reHashList(MyLinkedListFadaceA list){
		while (! list.isEmpty()){
			add(list.pollString());
		}
	}
	public void printAllLeft(){
		for (int i=0; i<capacity; i++){
			if (!hashTable[i].isEmpty()){
//				while(!hashTable[i].isEmpty())
//					System.out.println("a"  +hashTable[i].pollString());
			}
		}
	}

	private class MyLinkedListFadaceA{
		private LinkedList<String> list;
		
		private MyLinkedListFadaceA() {
			list = new LinkedList<String>();
		}

		private boolean add(String newValue) {
			if ( !this.contains(newValue)){
				list.add(newValue);
				return true;
			}
//			System.out.println("didnt add " + newValue);
			return false;
		}

		private boolean contains(String searchVal) {
			if ((!list.isEmpty()) && (list.contains(searchVal))){
				return true;
			}
			return false;
		}

		private boolean delete(String toDelete) {
			if (this.contains(toDelete)){
				this.list.remove(toDelete);
				return true;
			}
			return false;
		}
		private String pollString(){
			return list.pollFirst();
		}

		private boolean isEmpty(){
			return list.isEmpty();
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}
}
