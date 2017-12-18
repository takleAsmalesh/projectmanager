package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropsHelper {

	private static Properties props = null;
	private static String FILE_NAME = "c:/temp/app.properties";
	
	public static String get(String name){
		if(props == null){
			init();
		}
		return props.getProperty(name);
	}
	
	public static void set(String name, String val){
		if(props == null){
			init();
		}
		props.setProperty(name, val);
		save();
	}
	
	@SuppressWarnings("deprecation")
	private static void save(){
		if(props != null){
			try{
				FileOutputStream out = new FileOutputStream(new File(FILE_NAME));
				props.save(out, "");
				System.out.println(out+"");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void init(){
		try{
			props = new Properties();
        	FileInputStream in = new FileInputStream(FILE_NAME);
        	props.load(in);
	        in.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
	}
}
