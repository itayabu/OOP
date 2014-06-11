package oop.ex7.Sjavac;

public enum Type {
INT("int"), DOUBLE("double"), STRING("String"), BOOLEAN("boolean"), CHAR("char"), VOID("void");

private String typeName;

Type (String kind){
	typeName = kind;
}

public String getType(){
	return typeName;
}
}
