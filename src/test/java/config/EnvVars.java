package config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EnvVars {

	public static Path tdPath = Paths.get("src/test/resources/Testdata/JsonData");
//	    System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\JsonData\\";
	
	public static Path resultPath = Paths.get("Result"); 
//			System.getProperty("user.dir") + "\\Result\\";

//	public static String prodCatalogTD = tdPath + "ProductCatalog\\";

	public static final String excelExt = ".xlsx";
	public static final String propExt = ".properties";
	public static final String pngExt = ".png";

//Script hard waits
	public static final int quickwait1 = 1;
	public static final int quickwait2 = 2;
	public static final int quickwait3 = 3;
	public static final int shortwait = 4;
	public static final int mediumwait = 8;
	public static final int longwait = 12;

//WebDriver Waits
	public static final int shortww = 5;
	public static final int normalww = 30;

}
