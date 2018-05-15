package bas.jeda.core;

public class Contexts {

    public static final String INVEST_GROUP = "InvestGroup";
    public static final String ERRORINFO = "Error";
    public static final String TRADE_INF = "TradeInf";
    public static final String INVEST_GROUPINF = "InvestGroupInf";
    public static final String BOND_GROUPINF = "BondGroupInf";
    public static final String TABLE = "table";
    public static final String TGACCT_INF = "TGAcctInf";
    public static final String TGACCT = "TGAcct";
    public static final String QSACCT_INF = "QSAcctInf";
    public static final String QSACCT = "QSAcct";
    public static final String INTERESTS_INF = "InterestsInf";
    public static final String OPTION_INF = "OptionInf";

    public static final String TASK_BEGIN = "0";
    public static final String TASK_ERROR = "-1";
    public static final String TASK_FINISH = "1";

    public static final String ZHG = "0"; //正回购
    public static final String NHG = "1"; //逆回购

    public static final String XQMR = "XQMR";//现券买入
    public static final String XQMC = "XQMC";//现券卖出
    public static final String ZQCX = "ZQCX";//承销
    public static final String FXMR = "FXMR";//分销买入
    public static final String FXMC = "FXMC";//分销卖出
    public static final String YHMR = "YHMR";//央行现券买断
    public static final String YHMC = "YHMC";//央行现券卖断
    	public static final String ZYHG="ZYHG";//债券质押回购
//	public static final String ZYZG="ZYZG";//质押式正回购
//	public static final String ZYNG="ZYNG";//质押式逆回购
//	public static final String MDZG="MDZG";//买断式正回购
//	public static final String MDNG="MDNG";//买断式逆回购
//	public static final String YHZH="YHZH";//央行质押式正回购
//	public static final String YHNH="YHNH";//央行质押式逆回购
//	public static final String GKXJ="GKXJ";//国库现金管理商业银行定期存款
    public static final String ZYDH = "ZYDH";//质押券调换
    public static final String ZYZG = "ZYZG";//质押式正回购首期
    public static final String ZYNG = "ZYNG";//质押式逆回购首期
    public static final String MDZG = "MDZG";//买断式正回购首期
    public static final String MDNG = "MDNG";//买断式逆回购首期
    public static final String YHZH = "YHZH";//央行质押式正回购首期
    public static final String YHNH = "YHNH";//央行质押式逆回购首期
    public static final String GKXJ = "GKXJ";//国库现金管理商业银行定期存款首期
    public static final String ZZDQ = "ZZDQ";//质押式正回购到期
    public static final String ZNDQ = "ZNDQ";//质押式逆回购到期
    public static final String MZDQ = "MZDQ";//买断式正回购到期
    public static final String MNDQ = "MNDQ";//买断式逆回购到期
    public static final String YHZD = "YHZD";//央行质押式正回购到期
    public static final String YHND = "YHND";//央行质押式逆回购到期
    public static final String GKDQ = "GKDQ";//国库现金管理商业银行定期存款到期
    public static final String ZQGZ = "ZQGZ";//公允价值调整
    public static final String ZQSX = "ZQSX";//收息
    public static final String ZQDQ = "ZQDQ";//兑付
    public static final String TZHS = "TZHS";//投资人回售
    public static final String FQHB = "FQHB";//分期还本
    public static final String JZZB = "JZZB";//减值准备
    public static final String JTLX = "JTLX";//计提
    public static final String ZQTX = "ZQTX";//摊销
    public static final String NZJZ = "NZJZ";//年终结转
    public static final String SGJZ = "SGJZ";//手工记账
    public static final String CXFL = "CXFL";//重分类

    /**
     * 日终处理
     */
    public static final String sysDate = "sysDate"; //系统日期
    public static final String RZDZ = "finalCheck";//日终对账
    public static final String JTLX1 = "calInterests";//计提利息
    public static final String JSTX = "calTanxiao";//计算摊销
    public static final String JYRTZFH = "tradeDayAccountCheck";//交易日台账复核是否完成
    public static final String KJRTZFH = "kuaijiDayAccountCheck";//会计日台账复核是否完成
    public static final String ZBRTZFH = "assetDayAccountCheck";//资本日台账复核是否完成
    public static final String FXRTZFH = "riskDayAccountCheck";//风险日台账复核是否完成
    public static final String CZBFH = "chizhaibiaoCheck";//持债表复核是否完成
    public static final String ZCHZBFH = "zichanhuizongCheck";//资产汇总表复核是否完成
    public static final String HGSMZQFH = "huigouCheck";//回购生命周期复核是否完成
    public static final String TZFH = "accountCheck";//台账审核是否完成
    public static final String RQ = "sysDayChange";//日切是否完成
    
    public static final String systemStatus = "systemstatus";

}
