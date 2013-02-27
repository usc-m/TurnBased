package inf1.oop.turnbased;

import java.util.HashMap;

public class ServiceProvider {
	private HashMap<Class, Object> services;
	
	public <T> T get(Class<T> cls) { 
		if(!services.containsKey(cls)) throw new ArrayIndexOutOfBoundsException(); //TODO: Use neater exception type
		
		return (T)services.get(cls);
	}
	
	public <T> void set(T obj, Class<T> cls) {
		if(services.containsKey(cls)) throw new IllegalArgumentException(); // TODO: Use neater exception type
		
		services.put(cls, obj);
	}
	
	public ServiceProvider(){
		services = new HashMap<Class, Object>();
	}
}
