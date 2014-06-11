package oop.ex7.Sjavac.instance;

import oop.ex7.Sjavac.Type;
import oop.ex7.Sjavac.validations.ValidateType;

public class InstanceFactory {

	
	public Instance createInstance(String[] splittedLine){
		Type currentType = ValidateType.makeType(splittedLine[0]);
	}

	
}
