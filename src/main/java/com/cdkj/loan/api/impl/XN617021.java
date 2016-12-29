package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICreditOrderAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.dto.req.XN617021Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

public class XN617021 extends AProcessor {
    private ICreditOrderAO creditOrderAO = SpringContextHolder
        .getBean(ICreditOrderAO.class);

    private XN617021Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        creditOrderAO
            .editReceiptPdf(req.getCode(),
                StringValidater.toLong(req.getReceiptAmount()),
                req.getReceiptPdf());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN617021Req.class);
        StringValidater.validateBlank(req.getCode(), req.getReceiptPdf(),
            req.getReceiptAmount());
    }
}