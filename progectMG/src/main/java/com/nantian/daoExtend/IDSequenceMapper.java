package com.nantian.daoExtend;

/**
 * Created by NT on 2017/4/6.
 */
public interface IDSequenceMapper {
    //客户编号
    int querySEQCUSTOMER();
    //项目编号
    int querySEQPROJECT();
    //中介机构编号
    int querySEQINTERMEDIARY();
    //主合同编号
    int querySEQCONTRACT();
    //不良资产债权、投资 合同编号
    int querySEQCONTRACT2();
    //保证合同编号
    int querySEQGUARANTEE();
    //股权资产编号
    int querySEQSTOCK();
    //实物资产编号
    int querySEQPHYSICAL();
    //导入批次
    int querySEQIMPORT();
    //单据编号
    int querySEQRECEIPTS();
    //交易流水号
    int querySEQTRANSACTION();
    //处置编号
    int querySEQDISPOSITION();
    //--------------------------↓：不加前缀编号
    //押品编号
    int querySEQCOLLATERAL();
    //递增主键
    int querySEQWYID();
    //交易对手编号
    int querySEQTRANSFEROR();
    //共同债务人编号
    int querySEQDEBTOR();
    //协议编号
    int querySEQAGREEMENT();
    //投放风险预警
    int querySEQRISK();
    //资产分类
    int querySEQCLASSIFICATION();
}
