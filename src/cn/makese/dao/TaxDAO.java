package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Tax;

public class TaxDAO extends BasicDAO<Tax> {
	public int netSalary(int salary) {
		ArrayList<Tax> taxs = new ArrayList<Tax>();
		MyTableConvert myTableConvert = new MyTableConvert();
		ResultSet rs;
		try {
			rs = FindAll(new Tax());
			taxs = (ArrayList<Tax>) myTableConvert.convertToList(rs, new Tax().getClass());
		} catch (Exception e) {
			System.out.println("¼ÆËã¹¤×Ê´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		Collections.sort(taxs, new Comparator<Tax>(){
			public int compare(Tax o1, Tax o2) {
				if (o1.getHLimit() > o2.getHLimit()) {
					return 1;
				}
				else {
					return 0;
				}
			}	
		});
		int num = 0;
		for (Tax tax : taxs) {
			if(tax.getHLimit() < salary) {
				num += (tax.getHLimit() - tax.getLLimit()) * (1 - tax.getTax()/100.0);
			}
			else {
				num += (salary-tax.getLLimit()) * (1 - tax.getTax()/100.0);
				break;
			}
		}
		return num;
	}
}
