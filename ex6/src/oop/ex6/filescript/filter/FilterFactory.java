package oop.ex6.filescript.filter;

import oop.ex6.filescript.filter.exceptions.BadBooleanParameterException;
import oop.ex6.filescript.filter.exceptions.BadFilterException;
import oop.ex6.filescript.filter.exceptions.BadFilterNameException;
import oop.ex6.filescript.filter.exceptions.BadNOTArgumentException;

/**
 * manage creation of all filters
 * @author Itay
 *
 */
public class FilterFactory {

	/**
	 * 
	 * @param filterStr - filter name and parameters
	 * @param filterLine - line in ordersFile that filter was called from
	 * @return
	 */
	public static Filter createFilter (String[] filterStr, int filterLine){
		int i=0;
		Filter filter=null;
		try {
			if (filterStr[i].equals("greater_than")){
				filter = new GreaterThanFilter
						(Double.parseDouble(filterStr[++i]));
			} 

			else if (filterStr[i].equals("smaller_than")){
				filter = new SmallerThanFilter
						(Double.parseDouble(filterStr[++i]));
			}

			else if (filterStr[i].equals("between")){
				filter = new BetweenFilter (Double.parseDouble(filterStr[++i]),
						Double.parseDouble(filterStr[++i])); 
			}

			else if(filterStr[i].equals ("contains")){
				filter = new ContainFilter(filterStr[++i]);
			}

			else if(filterStr[i].equals ("file")){
				filter = new FileNameFilter(filterStr[++i]);
			}

			else if(filterStr[i].equals ("prefix")){
				filter = new PrefixFilter(filterStr[++i]);
			}

			else if(filterStr[i].equals ("suffix")){
				filter = new SuffixFilter(filterStr[++i]);
			}

			else if(filterStr[i].equals ("writable")){
				if (filterStr[++i].equals("YES")){
					filter = new WritableFilter(true);	
				}
				else if (filterStr[i].equals("NO")){
					filter = new WritableFilter(false);	
				}
				else{
					throw new BadBooleanParameterException();
				}
			}

			else if(filterStr[i].equals ("executable")){
				if (filterStr[++i].equals("YES")){
					filter = new ExecuteFilter(true);	
				}
				else if (filterStr[i].equals("NO")){
					filter = new ExecuteFilter(false);	
				}
				else{
					throw new BadBooleanParameterException();
				}
			}

			else if(filterStr[i].equals ("hidden")){
				if (filterStr[++i].equals("YES")){
					filter = new HiddenFilter(true);	
				}
				else if (filterStr[i].equals("NO")){
					filter = new HiddenFilter(false);	
				}
				else{
					throw new BadBooleanParameterException();
				}
			}

			else if(filterStr[i].equals ("all")){
				filter = new AllFilter();
			}

			// if not equal to any name, throw exception
			else {
				throw new BadFilterNameException();
			}

			// manage the NOT filter
			if (++i < filterStr.length){
				if (filterStr[i].equals("NOT")){
					filter = new NotFilter(filter);
				}
				else{
					throw new BadNOTArgumentException();
				}
			}
		}
		// if there was any problem, print warning and initiate AllFilter
		catch (BadFilterException | NumberFormatException e){
			System.out.println("Warning in line " + filterLine);
			filter = new AllFilter();
		}
		return filter;
	}
}
