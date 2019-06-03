
public class Person {
	int mAge;
	String mName;
	Person mChild;

	//PART 1
    	//Complete the constructor with the following inputs: mAge, mName, mChild
	public Person(String name, int age, Person child){
		mName = name;
        	mAge = age;
        	mChild = child;
	}

	//PART 2
	//Instantiate a new Person object, including a child for the person
	public static void main(String args[]){
		Person person = new Person("John",30,new Person("Jane",8,null));
	}

	//PART 3
	//Complete the getter and setter methods for the mAge property
	public int getAge(){
		return mAge;
	}

	public void setAge(int age){
		mAge = age;
	}
}
