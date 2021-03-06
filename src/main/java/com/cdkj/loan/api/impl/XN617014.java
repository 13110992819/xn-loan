package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICreditOrderAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.CreditOrder;
import com.cdkj.loan.dto.req.XN617014Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

public class XN617014 extends AProcessor {
    private ICreditOrderAO creditOrderAO = SpringContextHolder
        .getBean(ICreditOrderAO.class);

    private XN617014Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CreditOrder data = new CreditOrder();
        data.setCode(req.getCode());
        data.setSkAmount(StringValidater.toLong(req.getAmount()));
        data.setSkPdf(req.getPdf());
        data.setUpdater(req.getUpdater());
        data.setYhDatetime(DateUtil.strToDate(req.getYhDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setRemark(req.getRemark());
        creditOrderAO.editReceiptPdf(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN617014Req.class);
        StringValidater.validateBlank(req.getCode(), req.getPdf(),
            req.getYhDatetime(), req.getAmount(), req.getUpdater());
    }
}
