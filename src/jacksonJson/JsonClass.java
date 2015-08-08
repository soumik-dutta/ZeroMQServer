package jacksonJson;

public class JsonClass {
	private String classType;

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	/**
	 * <p>The method will get the string as a parameter and return the class</p>
	 * @param classString
	 * @return child class of Request class
	 */
	public Object getJsonClass(String classString){
		switch(classString){
			case "Auth":{
				return new User();
				
			}
			default:{
				return null;
			}
		}
	}	
}
