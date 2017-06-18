package cn.makese.dbmanager;


public class DataAccessFactory {
	
	public static IDataAccess CreateDataAccess(EnumDatabaseType dbType) {
		switch (dbType)
        {
            case Sqlserver :return new SqlServer();
            case mysql:return new MySql();
            default: {
               System.out.println("��֧�ֵ����ݿ�����");
               return null;
            }
          
        }
   }
}
