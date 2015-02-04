package misc;

import java.io.File;
import java.util.Calendar;

public class ModifyFileAttr {

	public static void main(String[] args) {

		File f = new File(
				"C:\\Users\\Destiny\\Desktop\\intern\\intern\\Worksap\\docs\\12PEK015_JiangRuixiang_Catalog_2012.xls");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.add(Calendar.HOUR_OF_DAY, -8);

		f.setLastModified(cal.getTimeInMillis());

	}

}
