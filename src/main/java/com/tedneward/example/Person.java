package main.java.com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
	
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int instanceCount; 
  private static ArrayList<Person> newardFamily = makeFamily(); 
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    this.name = n;
    this.age = a;
    this.salary = s;
    this.ssn = ""; 
    instanceCount++; 
  }

  
  public int getAge() {
    return age;
  }
  
  public void setAge(int a){
	  if(a < 0){
		  throw new IllegalArgumentException(); 
	  }
	  age = a; 
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String n){
	  if(n != null){
		  name = n; 
	  } else {
		  throw new IllegalArgumentException(); 
	  }
	  
  }
  
  public double getSalary() {
    return salary;
  }
  
  public void setSalary(double s){
	  if(salary >= 0) {
		  salary = s;
	  } else {
		  throw new IllegalArgumentException(); 
	  }
	   
  }
  
  public String getSSN() {
    return ssn;
  }
  
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public int count() {
	  return instanceCount; 
  }
  
  @Override
  public boolean equals(Object other) { 
	  if(other instanceof Person) {
		  return (this.name.equals(((Person)other).name) && this.age == ((Person)other).age);
	  } else {
		  return false;
	  }  
  }

  @Override
  public String toString() {
    return String.format("[Person name:%s age:%d salary:%.2f]", name, age, salary); 
  }
  
  @Override
  public int compareTo(Person other){
	  if(other != null) {
		  if(salary < other.salary){
			  return 1; 
		  } else if (salary > other.salary){
			  return -1; 
		  } else {
			  return 0; 
		  }
	  } else {
		  throw new NullPointerException(); 
	  }
  }
  
  public static ArrayList<Person> getNewardFamily(){ 
	  return newardFamily; 
  }
  
  private static ArrayList<Person> makeFamily(){
	  ArrayList<Person> neward = new ArrayList<Person>(); 
	  Person ted = new Person("Ted", 41, 250000); 
	  Person charlotte = new Person("Charlotte", 43, 150000); 
	  Person mike = new Person("Michael", 22, 10000);
	  Person matt = new Person("Matthew", 15, 0);
	  neward.add(ted);
	  neward.add(charlotte);
	  neward.add(mike);
	  neward.add(matt);
	  return neward;
  }
 

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
  
  public static class AgeComparator implements Comparator<Person> {
	  
	    public AgeComparator(){}
	    
		@Override
	    public int compare(Person t, Person o){
				return t.age - o.age; 
		}
   }
	  
}
