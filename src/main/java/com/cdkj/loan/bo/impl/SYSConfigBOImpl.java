package com.cdkj.loan.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.dao.ISYSConfigDAO;
import com.cdkj.loan.domain.SYSConfig;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月17日 下午7:56:03 
 * @history:
 */

@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig> implements
        ISYSConfigBO {
    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Override
    public boolean isSYSConfigExist(Long Id) {
        SYSConfig sysConfig = new SYSConfig();
        sysConfig.setId(Id);
        if (sysConfigDAO.selectTotalCount(sysConfig) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int saveSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            data.setId(data.getId());
            count = sysConfigDAO.insert(data);
        }
        return count;
    }

    @Override
    public int refreshSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            count = sysConfigDAO.updateValue(data);
        }
        return count;
    }

    @Override
    public SYSConfig getConfig(Long id) {
        SYSConfig sysConfig = null;
        if (id != null) {
            SYSConfig condition = new SYSConfig();
            condition.setId(id);
            sysConfig = sysConfigDAO.select(condition);
        }
        return sysConfig;
    }

    /** 
     * @see com.cdkj.loan.bo.ISYSConfigBO#getConfigValue(java.lang.String)
     */
    @Override
    public String getConfigValue(String companyCode, String ckey) {
        String result = null;
        SYSConfig sysConfig = null;
        if (ckey != null) {
            SYSConfig condition = new SYSConfig();
            condition.setCompanyCode(companyCode);
            condition.setCkey(ckey);
            sysConfig = sysConfigDAO.select(condition);
            if (sysConfig == null) {
                condition.setBelong(0L);
                condition.setCompanyCode(null);
                sysConfig = sysConfigDAO.select(condition);
            }
        }
        if (sysConfig != null) {
            result = sysConfig.getCvalue();
        }
        return result;
    }

    @Override
    public SYSConfig getConfigValue(String ckey) {
        SYSConfig result = null;
        if (ckey != null) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(ckey);
            result = sysConfigDAO.select(condition);
        }
        return result;
    }
}
