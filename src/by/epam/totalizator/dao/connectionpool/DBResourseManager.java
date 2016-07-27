package by.epam.totalizator.dao.connectionpool;

import java.util.ResourceBundle;

/**
 * This class implements a pattern singleton. 
 * This class derives from the configuration file settings for the database.
 */

public class DBResourseManager {

	private static final String PATH = "db";
	private ResourceBundle bundle = ResourceBundle.getBundle(PATH);
	
	private DBResourseManager() {
		
	}
	
	/**
	 * The inner class for implementation singleton. 
	 * It holds DBResourseManager instance.
	 */
	private static class Holder {
		private static final DBResourseManager INSTANCE = new DBResourseManager();
	}
	
	/**
	 * The method gives DBResourseManager singleton instance.
	 */
	public static DBResourseManager getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * The method gives extract key parameters.
	 * 
	 * @param String key
	 * @return String
	 */
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
