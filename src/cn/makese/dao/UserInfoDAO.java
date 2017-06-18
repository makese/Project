package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.swing.internal.plaf.metal.resources.metal;

import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.UserInfo;

public class UserInfoDAO extends BasicDAO<UserInfo> {
	public boolean LoginUser(UserInfo user) throws Exception {
		boolean t = false;
		ResultSet rs = FindByEqual(user);
		if(rs.next()) {
			t = true;
		}
		releaseSource();
		return t;
	}
	public UserInfo findUserInfoBySno(String sno) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserID(sno);
		ResultSet rs;
		try {
			rs = FindByEqual(userInfo);
			if(rs.next()) {
				userInfo.setUserPsw(rs.getString("userpsw"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return userInfo;
	}
	public ArrayList<UserInfo> findAllUserInfo() {
		ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
		MyTableConvert myTableConvert = new MyTableConvert();
		ResultSet rs;
		try {
			rs = FindAll(new UserInfo());
			userInfos = (ArrayList<UserInfo>) myTableConvert.convertToList(rs, new UserInfo().getClass());
		} catch (Exception e) {
			System.out.println("查找所有用户错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return userInfos;
	}
}
