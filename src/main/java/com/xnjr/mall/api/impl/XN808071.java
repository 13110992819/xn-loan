package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IOrderAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.domain.Order;
import com.xnjr.mall.dto.req.XN808071Req;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 列表查询订单
 * @author: xieyj 
 * @since: 2016年5月23日 上午9:04:12 
 * @history:
 */
public class XN808071 extends AProcessor {

    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN808071Req req = null;

    /** 
     * @see com.xnjr.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Order condition = new Order();
        condition.setCodeForQuery(req.getCode());
        condition.setMobile(req.getMobile());
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        return orderAO.queryOrderList(condition);
    }

    /** 
     * @see com.xnjr.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808071Req.class);
    }
}