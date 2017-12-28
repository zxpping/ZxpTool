package com.zxp.java.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxp on 2017/12/26.
 * desc:
 */
public class MysqlUtil {

    public static Map<String, Object> getMapObj(Object obj) {
        try {
            HashMap<String, Object> mapObj = new HashMap();
            Field[] aF = (Field[])null;
            aF = obj.getClass().getDeclaredFields();
            if (aF != null) {
                for(int i = 0; i < aF.length; ++i) {
                    if (!"serialVersionUID".equalsIgnoreCase(aF[i].getName())) {
                        if (aF[i].getModifiers() != 1) {
                            try {
                                aF[i].setAccessible(true);
                            } catch (SecurityException var5) {

                            }
                        }

                        mapObj.put(aF[i].getName(), aF[i].get(obj));
                    }
                }
            }

            return mapObj;
        } catch (Exception e) {
            System.out.println("根据Bean对象得到转换后的Map对象："+e);
            return null;
        }
    }

    /**
     * 获取查询总条数
     * @param sSql
     * @return
     */
    public static String getCountSql(String sSql) {
        if (sSql != null && !"".equals(sSql)) {
            String sCountSql = "select count(*) from (" + sSql + ") temp ";
            return sCountSql;
        } else {
            return null;
        }
    }

    /**
     * 获取分页sql
     * @param sSql
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static String getPagingSql(String sSql,int pageNum,int pageSize) {
        if (sSql != null && !"".equals(sSql)) {
            StringBuffer sbPagingSql = new StringBuffer();
            sbPagingSql.append(sSql);
            sbPagingSql.append(" limit "+((pageNum - 1) * pageSize)+", "+pageSize+"");
            return sbPagingSql.toString();
        } else {
            return null;
        }
    }
}
