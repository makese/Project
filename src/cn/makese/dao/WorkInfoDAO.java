package cn.makese.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.sun.jndi.url.ldaps.ldapsURLContextFactory;

import cn.makese.dbmanager.DataAccessFactory;
import cn.makese.dbmanager.IDataAccess;
import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Position;
import cn.makese.model.WorkInfo;


public class WorkInfoDAO extends BasicDAO<WorkInfo> {
	public ArrayList<WorkInfo> findAllWorkInfoBySno(String sno) {
		WorkInfo workInfo = new WorkInfo();
		MyTableConvert myTableConvert = new MyTableConvert();
		ArrayList<WorkInfo> workInfos = new ArrayList<WorkInfo>();
		workInfo.setSno(sno);
		ResultSet rs;
		try {
			rs = FindByEqual(workInfo);
			workInfos = (ArrayList<WorkInfo>) myTableConvert.convertToList(rs, workInfo.getClass());
		} catch (Exception e) {
			System.out.println("���ҿ�����Ϣ����");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return workInfos;
	}
	public ArrayList<WorkInfo> findAllWorkInfo() {
		MyTableConvert myTableConvert = new MyTableConvert();
		ArrayList<WorkInfo> workinfos = new ArrayList<WorkInfo>();
		ResultSet rs;
		try {
			rs = FindAll(new WorkInfo());
			workinfos = (ArrayList<WorkInfo>) myTableConvert.convertToList(rs, new WorkInfo().getClass());
		} catch (Exception e) {
			System.out.println("�������й�����Ϣ����");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return workinfos;
	}
	
	public boolean isWork(String sno) {
		boolean b = false;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String time = sdf.format(date);
		WorkInfo workInfo = new WorkInfo();
		workInfo.setSno(sno);
		workInfo.setDate(time);
		workInfo.setWork("�ϰ�");
		ResultSet rs;
		try {
			rs = FindByEqual(workInfo);
			if(rs.next()) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("��֤�Ƿ���ǩ������");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return b;
	}
	public boolean isOverWork(String sno) {
		boolean b = false;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String time = sdf.format(date);
		WorkInfo workInfo = new WorkInfo();
		workInfo.setSno(sno);
		workInfo.setDate(time);
		workInfo.setWork("�Ӱ�");
		ResultSet rs;
		try {
			rs = FindByEqual(workInfo);
			if(rs.next()) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println("��֤�Ƿ�Ӱ����");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return b;
	}
	public void deleteAll() {
		String sql = "delete from workinfo";
		IDataAccess dataAccess;
		try {
			dataAccess = DataAccessFactory.CreateDataAccess(databaseType);
			dataAccess.executeUpdate(sql, new ArrayList<Object>());
		} catch (Exception e) {
			System.out.println("ɾ��������ټ�¼����");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
	}
}
