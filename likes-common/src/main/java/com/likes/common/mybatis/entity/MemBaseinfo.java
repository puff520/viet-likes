package com.likes.common.mybatis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "`mem_baseinfo`")
public class MemBaseinfo {

    /**
     * 用户id
     */
    @Id
    @Column(name = "`memid`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memid;

    /**
     * 会员ID
     */
    @Column(name = "`unique_id`")
    private String uniqueId;

    /**
     * 会员标识号
     */
    @Column(name = "`accno`")
    private String accno;

    @Column(name = "`superior_id`")
    private String superiorId;

    /**
     * 呢称
     */
    @Column(name = "`nickname`")
    private String nickname;

    /**
     * 真实姓名
     */
    @Column(name = "`memname`")
    private String memname;

    /**
     * 手机号码
     */
    @Column(name = "`email`")
    private String email;

    @Column(name = "`mobileno`")
    private String mobileno;

    /**
     * 性别 1男 2女 4保密
     */
    @Column(name = "`sex`")
    private Integer sex;

    @Column(name = "`level`")
    private Integer level;

    /**
     * 证件类型DDINFO
     */
    @Column(name = "`idcardtype`")
    private String idcardtype;

    /**
     * 证件号码
     */
    @Column(name = "`idcardno`")
    private String idcardno;

    /**
     * 证件照片正面
     */
    @Column(name = "`idcardfront`")
    private String idcardfront;

    /**
     * 证件照片反面
     */
    @Column(name = "`idcardback`")
    private String idcardback;

    /**
     * 出生日期
     */
    @Column(name = "`birthday`")
    private Date birthday;

    /**
     * 国籍DDINFO
     */
    @Column(name = "`nationality`")
    private String nationality;

    /**
     * 头像
     */
    @Column(name = "`headimg`")
    private String headimg;

    /**
     * 注册日期
     */
    @Column(name = "`registerdate`")
    private Date registerdate;

    /**
     * 推荐码
     */
    @Column(name = "`recomcode`")
    private String recomcode;

    /**
     * 描述
     */
    @Column(name = "`describes`")
    private String describes;

    /**
     * 登录地址城市
     */
    @Column(name = "`tag`")
    private String tag;

    /**
     * 登录ip地址
     */
    @Column(name = "`clintipadd`")
    private String clintipadd;

    /**
     * 注册ip
     */
    @Column(name = "`register_ip`")
    private String registerIp;

    /**
     * 注册设备
     */
    @Column(name = "`register_dev`")
    private String registerDev;

    /**
     * 登录设备
     */
    @Column(name = "`last_login_dev`")
    private String lastLoginDev;

    /**
     * 登录国家
     */
    @Column(name = "`logincountry`")
    private String logincountry;

    /**
     * 会员特征(兴趣)
     */
    @Column(name = "`memfeatures`")
    private String memfeatures;

    /**
     * 来源 recommend推荐  regist注册  operator运营人员
     */
    @Column(name = "`memorgin`")
    private String memorgin;

    /**
     * 粉丝数量
     */
    @Column(name = "`fansnum`")
    private Long fansnum;

    /**
     * 用户乐币数量
     */
    @Column(name = "`goldnum`")
    private BigDecimal goldnum;

    /**
     * 待开奖金额（元）
     */
    @Column(name = "`wait_amount`")
    private BigDecimal waitAmount;

    /**
     * 可提现余额
     */
    @Column(name = "`withdrawal_remainder`")
    private BigDecimal withdrawalRemainder;

    /**
     * 累计投注  (元)
     */
    @Column(name = "`bet_amount`")
    private BigDecimal betAmount;

    /**
     * 累计充值（元）
     */
    @Column(name = "`pay_amount`")
    private BigDecimal payAmount;

    /**
     * 最大充值金额
     */
    @Column(name = "`pay_max`")
    private BigDecimal payMax;

    /**
     * 首次充值金额
     */
    @Column(name = "`pay_first`")
    private BigDecimal payFirst;

    /**
     * 充值总次数
     */
    @Column(name = "`pay_num`")
    private Integer payNum;

    /**
     * 累计提现（元)
     */
    @Column(name = "`withdrawal_amount`")
    private BigDecimal withdrawalAmount;

    /**
     * 最大提现金额
     */
    @Column(name = "`withdrawal_max`")
    private BigDecimal withdrawalMax;

    /**
     * 首次提现金额
     */
    @Column(name = "`withdrawal_first`")
    private BigDecimal withdrawalFirst;

    /**
     * 提现总次数
     */
    @Column(name = "`withdrawal_num`")
    private Integer withdrawalNum;

    /**
     * 总消费(打码量)
     */
    @Column(name = "`consume_amount`")
    private BigDecimal consumeAmount;

    /**
     * 不可提现金额(元)
     */
    @Column(name = "`no_withdrawal_amount`")
    private BigDecimal noWithdrawalAmount;

    /**
     * 聊天状态: 0,不允许;1,允许
     */
    @Column(name = "`chat_status`")
    private Integer chatStatus;

    /**
     * 冻结状态: 0,不冻结;1,冻结
     */
    @Column(name = "`freeze_status`")
    private Integer freezeStatus;

    /**
     * 投注状态: 0,不允许;1,允许
     */
    @Column(name = "`bet_status`")
    private Integer betStatus;

    /**
     * 返水状态: 0,不允许;1,允许
     */
    @Column(name = "`backwater_status`")
    private Integer backwaterStatus;

    /**
     * 晒单状态(圈子使用): 0,不允许;1,允许
     */
    @Column(name = "`share_order_status`")
    private Integer shareOrderStatus;

    /**
     * 账户类型  普通会员1      主播2   家族長3   运营后台管理员8    第三方登录7   服務註冊中心管理員9  聚合站点后台管理员10
     */
    @Column(name = "`logintype`")
    private Integer logintype;

    /**
     * 第三方登录时返回的唯一标识
     */
    @Column(name = "`openId`")
    private String openid;

    /**
     * 所在地(省市区)12位區域編碼code
     */
    @Column(name = "`sitearea`")
    private String sitearea;

    /**
     * 微信号
     */
    @Column(name = "`wechat`")
    private String wechat;

    /**
     * 微信昵称
     */
    @Column(name = "`chatnickname`")
    private String chatnickname;

    /**
     * 是否删除
     */
    @Column(name = "`is_delete`")
    private Boolean isDelete;

    /**
     * 创建人
     */
    @Column(name = "`create_user`")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 最后修改人
     */
    @Column(name = "`update_user`")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;


    /**
     * 备注
     */
    @Column(name = "`cg_nickname`")
    private Boolean cgNickname;


    /**
     * 备注
     */
    @Column(name = "`proxy_url`")
    private String proxyUrl;

    /**
     * 获取用户id
     *
     * @return memid - 用户id
     */
    public Long getMemid() {
        return memid;
    }

    /**
     * 设置用户id
     *
     * @param memid 用户id
     */
    public void setMemid(Long memid) {
        this.memid = memid;
    }

    /**
     * 获取会员ID
     *
     * @return unique_id - 会员ID
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置会员ID
     *
     * @param uniqueId 会员ID
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取会员标识号
     *
     * @return accno - 会员标识号
     */
    public String getAccno() {
        return accno;
    }

    /**
     * 设置会员标识号
     *
     * @param accno 会员标识号
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * 获取呢称
     *
     * @return nickname - 呢称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置呢称
     *
     * @param nickname 呢称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取真实姓名
     *
     * @return memname - 真实姓名
     */
    public String getMemname() {
        return memname;
    }

    /**
     * 设置真实姓名
     *
     * @param memname 真实姓名
     */
    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别 1男 2女 4保密
     *
     * @return sex - 性别 1男 2女 4保密
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 1男 2女 4保密
     *
     * @param sex 性别 1男 2女 4保密
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取证件类型DDINFO
     *
     * @return idcardtype - 证件类型DDINFO
     */
    public String getIdcardtype() {
        return idcardtype;
    }

    /**
     * 设置证件类型DDINFO
     *
     * @param idcardtype 证件类型DDINFO
     */
    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype;
    }

    /**
     * 获取证件号码
     *
     * @return idcardno - 证件号码
     */
    public String getIdcardno() {
        return idcardno;
    }

    /**
     * 设置证件号码
     *
     * @param idcardno 证件号码
     */
    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    /**
     * 获取证件照片正面
     *
     * @return idcardfront - 证件照片正面
     */
    public String getIdcardfront() {
        return idcardfront;
    }

    /**
     * 设置证件照片正面
     *
     * @param idcardfront 证件照片正面
     */
    public void setIdcardfront(String idcardfront) {
        this.idcardfront = idcardfront;
    }

    /**
     * 获取证件照片反面
     *
     * @return idcardback - 证件照片反面
     */
    public String getIdcardback() {
        return idcardback;
    }

    /**
     * 设置证件照片反面
     *
     * @param idcardback 证件照片反面
     */
    public void setIdcardback(String idcardback) {
        this.idcardback = idcardback;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取国籍DDINFO
     *
     * @return nationality - 国籍DDINFO
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 设置国籍DDINFO
     *
     * @param nationality 国籍DDINFO
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取头像
     *
     * @return headimg - 头像
     */
    public String getHeadimg() {
        return headimg;
    }

    /**
     * 设置头像
     *
     * @param headimg 头像
     */
    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    /**
     * 获取注册日期
     *
     * @return registerdate - 注册日期
     */
    public Date getRegisterdate() {
        return registerdate;
    }

    /**
     * 设置注册日期
     *
     * @param registerdate 注册日期
     */
    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    /**
     * 获取推荐码
     *
     * @return recomcode - 推荐码
     */
    public String getRecomcode() {
        return recomcode;
    }

    /**
     * 设置推荐码
     *
     * @param recomcode 推荐码
     */
    public void setRecomcode(String recomcode) {
        this.recomcode = recomcode;
    }

    /**
     * 获取描述
     *
     * @return describes - 描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * 设置描述
     *
     * @param describes 描述
     */
    public void setDescribes(String describes) {
        this.describes = describes;
    }

    /**
     * 获取登录地址城市
     *
     * @return tag - 登录地址城市
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置登录地址城市
     *
     * @param tag 登录地址城市
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取登录ip地址
     *
     * @return clintipadd - 登录ip地址
     */
    public String getClintipadd() {
        return clintipadd;
    }

    /**
     * 设置登录ip地址
     *
     * @param clintipadd 登录ip地址
     */
    public void setClintipadd(String clintipadd) {
        this.clintipadd = clintipadd;
    }

    /**
     * 获取注册ip
     *
     * @return register_ip - 注册ip
     */
    public String getRegisterIp() {
        return registerIp;
    }

    /**
     * 设置注册ip
     *
     * @param registerIp 注册ip
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * 获取注册设备
     *
     * @return register_dev - 注册设备
     */
    public String getRegisterDev() {
        return registerDev;
    }

    /**
     * 设置注册设备
     *
     * @param registerDev 注册设备
     */
    public void setRegisterDev(String registerDev) {
        this.registerDev = registerDev;
    }

    /**
     * 获取登录设备
     *
     * @return last_login_dev - 登录设备
     */
    public String getLastLoginDev() {
        return lastLoginDev;
    }

    /**
     * 设置登录设备
     *
     * @param lastLoginDev 登录设备
     */
    public void setLastLoginDev(String lastLoginDev) {
        this.lastLoginDev = lastLoginDev;
    }

    /**
     * 获取登录国家
     *
     * @return logincountry - 登录国家
     */
    public String getLogincountry() {
        return logincountry;
    }

    /**
     * 设置登录国家
     *
     * @param logincountry 登录国家
     */
    public void setLogincountry(String logincountry) {
        this.logincountry = logincountry;
    }

    /**
     * 获取会员特征(兴趣)
     *
     * @return memfeatures - 会员特征(兴趣)
     */
    public String getMemfeatures() {
        return memfeatures;
    }

    /**
     * 设置会员特征(兴趣)
     *
     * @param memfeatures 会员特征(兴趣)
     */
    public void setMemfeatures(String memfeatures) {
        this.memfeatures = memfeatures;
    }

    /**
     * 获取来源 recommend推荐  regist注册  operator运营人员
     *
     * @return memorgin - 来源 recommend推荐  regist注册  operator运营人员
     */
    public String getMemorgin() {
        return memorgin;
    }

    /**
     * 设置来源 recommend推荐  regist注册  operator运营人员
     *
     * @param memorgin 来源 recommend推荐  regist注册  operator运营人员
     */
    public void setMemorgin(String memorgin) {
        this.memorgin = memorgin;
    }

    /**
     * 获取粉丝数量
     *
     * @return fansnum - 粉丝数量
     */
    public Long getFansnum() {
        return fansnum;
    }

    /**
     * 设置粉丝数量
     *
     * @param fansnum 粉丝数量
     */
    public void setFansnum(Long fansnum) {
        this.fansnum = fansnum;
    }

    /**
     * 获取用户乐币数量
     *
     * @return goldnum - 用户乐币数量
     */
    public BigDecimal getGoldnum() {
        return goldnum;
    }

    /**
     * 设置用户乐币数量
     *
     * @param goldnum 用户乐币数量
     */
    public void setGoldnum(BigDecimal goldnum) {
        this.goldnum = goldnum;
    }

    /**
     * 获取待开奖金额（元）
     *
     * @return wait_amount - 待开奖金额（元）
     */
    public BigDecimal getWaitAmount() {
        return waitAmount;
    }

    /**
     * 设置待开奖金额（元）
     *
     * @param waitAmount 待开奖金额（元）
     */
    public void setWaitAmount(BigDecimal waitAmount) {
        this.waitAmount = waitAmount;
    }

    /**
     * 获取可提现余额
     *
     * @return withdrawal_remainder - 可提现余额
     */
    public BigDecimal getWithdrawalRemainder() {
        return withdrawalRemainder;
    }

    /**
     * 设置可提现余额
     *
     * @param withdrawalRemainder 可提现余额
     */
    public void setWithdrawalRemainder(BigDecimal withdrawalRemainder) {
        this.withdrawalRemainder = withdrawalRemainder;
    }

    /**
     * 获取累计投注  (元)
     *
     * @return bet_amount - 累计投注  (元)
     */
    public BigDecimal getBetAmount() {
        return betAmount;
    }

    /**
     * 设置累计投注  (元)
     *
     * @param betAmount 累计投注  (元)
     */
    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    /**
     * 获取累计充值（元）
     *
     * @return pay_amount - 累计充值（元）
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置累计充值（元）
     *
     * @param payAmount 累计充值（元）
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取最大充值金额
     *
     * @return pay_max - 最大充值金额
     */
    public BigDecimal getPayMax() {
        return payMax;
    }

    /**
     * 设置最大充值金额
     *
     * @param payMax 最大充值金额
     */
    public void setPayMax(BigDecimal payMax) {
        this.payMax = payMax;
    }

    /**
     * 获取首次充值金额
     *
     * @return pay_first - 首次充值金额
     */
    public BigDecimal getPayFirst() {
        return payFirst;
    }

    /**
     * 设置首次充值金额
     *
     * @param payFirst 首次充值金额
     */
    public void setPayFirst(BigDecimal payFirst) {
        this.payFirst = payFirst;
    }

    /**
     * 获取充值总次数
     *
     * @return pay_num - 充值总次数
     */
    public Integer getPayNum() {
        return payNum;
    }

    /**
     * 设置充值总次数
     *
     * @param payNum 充值总次数
     */
    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    /**
     * 获取累计提现（元)
     *
     * @return withdrawal_amount - 累计提现（元)
     */
    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    /**
     * 设置累计提现（元)
     *
     * @param withdrawalAmount 累计提现（元)
     */
    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    /**
     * 获取最大提现金额
     *
     * @return withdrawal_max - 最大提现金额
     */
    public BigDecimal getWithdrawalMax() {
        return withdrawalMax;
    }

    /**
     * 设置最大提现金额
     *
     * @param withdrawalMax 最大提现金额
     */
    public void setWithdrawalMax(BigDecimal withdrawalMax) {
        this.withdrawalMax = withdrawalMax;
    }

    /**
     * 获取首次提现金额
     *
     * @return withdrawal_first - 首次提现金额
     */
    public BigDecimal getWithdrawalFirst() {
        return withdrawalFirst;
    }

    /**
     * 设置首次提现金额
     *
     * @param withdrawalFirst 首次提现金额
     */
    public void setWithdrawalFirst(BigDecimal withdrawalFirst) {
        this.withdrawalFirst = withdrawalFirst;
    }

    /**
     * 获取提现总次数
     *
     * @return withdrawal_num - 提现总次数
     */
    public Integer getWithdrawalNum() {
        return withdrawalNum;
    }

    /**
     * 设置提现总次数
     *
     * @param withdrawalNum 提现总次数
     */
    public void setWithdrawalNum(Integer withdrawalNum) {
        this.withdrawalNum = withdrawalNum;
    }

    /**
     * 获取总消费(打码量)
     *
     * @return consume_amount - 总消费(打码量)
     */
    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    /**
     * 设置总消费(打码量)
     *
     * @param consumeAmount 总消费(打码量)
     */
    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    /**
     * 获取不可提现金额(元)
     *
     * @return no_withdrawal_amount - 不可提现金额(元)
     */
    public BigDecimal getNoWithdrawalAmount() {
        return noWithdrawalAmount;
    }

    /**
     * 设置不可提现金额(元)
     *
     * @param noWithdrawalAmount 不可提现金额(元)
     */
    public void setNoWithdrawalAmount(BigDecimal noWithdrawalAmount) {
        this.noWithdrawalAmount = noWithdrawalAmount;
    }

    /**
     * 获取聊天状态: 0,不允许;1,允许
     *
     * @return chat_status - 聊天状态: 0,不允许;1,允许
     */
    public Integer getChatStatus() {
        return chatStatus;
    }

    /**
     * 设置聊天状态: 0,不允许;1,允许
     *
     * @param chatStatus 聊天状态: 0,不允许;1,允许
     */
    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }

    /**
     * 获取冻结状态: 0,不冻结;1,冻结
     *
     * @return freeze_status - 冻结状态: 0,不冻结;1,冻结
     */
    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    /**
     * 设置冻结状态: 0,不冻结;1,冻结
     *
     * @param freezeStatus 冻结状态: 0,不冻结;1,冻结
     */
    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    /**
     * 获取投注状态: 0,不允许;1,允许
     *
     * @return bet_status - 投注状态: 0,不允许;1,允许
     */
    public Integer getBetStatus() {
        return betStatus;
    }

    /**
     * 设置投注状态: 0,不允许;1,允许
     *
     * @param betStatus 投注状态: 0,不允许;1,允许
     */
    public void setBetStatus(Integer betStatus) {
        this.betStatus = betStatus;
    }

    /**
     * 获取返水状态: 0,不允许;1,允许
     *
     * @return backwater_status - 返水状态: 0,不允许;1,允许
     */
    public Integer getBackwaterStatus() {
        return backwaterStatus;
    }

    /**
     * 设置返水状态: 0,不允许;1,允许
     *
     * @param backwaterStatus 返水状态: 0,不允许;1,允许
     */
    public void setBackwaterStatus(Integer backwaterStatus) {
        this.backwaterStatus = backwaterStatus;
    }

    /**
     * 获取晒单状态(圈子使用): 0,不允许;1,允许
     *
     * @return share_order_status - 晒单状态(圈子使用): 0,不允许;1,允许
     */
    public Integer getShareOrderStatus() {
        return shareOrderStatus;
    }

    /**
     * 设置晒单状态(圈子使用): 0,不允许;1,允许
     *
     * @param shareOrderStatus 晒单状态(圈子使用): 0,不允许;1,允许
     */
    public void setShareOrderStatus(Integer shareOrderStatus) {
        this.shareOrderStatus = shareOrderStatus;
    }

    /**
     * 获取账户类型  普通会员1      主播2   家族長3   运营后台管理员8    第三方登录7   服務註冊中心管理員9  聚合站点后台管理员10
     *
     * @return logintype - 账户类型  普通会员1      主播2   家族長3   运营后台管理员8    第三方登录7   服務註冊中心管理員9  聚合站点后台管理员10
     */
    public Integer getLogintype() {
        return logintype;
    }

    /**
     * 设置账户类型  普通会员1      主播2   家族長3   运营后台管理员8    第三方登录7   服務註冊中心管理員9  聚合站点后台管理员10
     *
     * @param logintype 账户类型  普通会员1      主播2   家族長3   运营后台管理员8    第三方登录7   服務註冊中心管理員9  聚合站点后台管理员10
     */
    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }

    /**
     * 获取第三方登录时返回的唯一标识
     *
     * @return openId - 第三方登录时返回的唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置第三方登录时返回的唯一标识
     *
     * @param openid 第三方登录时返回的唯一标识
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取所在地(省市区)12位區域編碼code
     *
     * @return sitearea - 所在地(省市区)12位區域編碼code
     */
    public String getSitearea() {
        return sitearea;
    }

    /**
     * 设置所在地(省市区)12位區域編碼code
     *
     * @param sitearea 所在地(省市区)12位區域編碼code
     */
    public void setSitearea(String sitearea) {
        this.sitearea = sitearea;
    }

    /**
     * 获取微信号
     *
     * @return wechat - 微信号
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信号
     *
     * @param wechat 微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取微信昵称
     *
     * @return chatnickname - 微信昵称
     */
    public String getChatnickname() {
        return chatnickname;
    }

    /**
     * 设置微信昵称
     *
     * @param chatnickname 微信昵称
     */
    public void setChatnickname(String chatnickname) {
        this.chatnickname = chatnickname;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改人
     *
     * @return update_user - 最后修改人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置最后修改人
     *
     * @param updateUser 最后修改人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getCgNickname() {
        return cgNickname;
    }

    public void setCgNickname(Boolean cgNickname) {
        this.cgNickname = cgNickname;
    }

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
