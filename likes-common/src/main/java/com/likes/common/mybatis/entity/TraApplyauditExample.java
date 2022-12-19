package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TraApplyauditExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected Integer limit;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public TraApplyauditExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        offset = null;
        limit = null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public Integer getLimit() {
        return this.limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public TraApplyauditExample bound(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andApauditidIsNull() {
            addCriterion("apauditid is null");
            return (Criteria) this;
        }

        public Criteria andApauditidIsNotNull() {
            addCriterion("apauditid is not null");
            return (Criteria) this;
        }

        public Criteria andApauditidEqualTo(Long value) {
            addCriterion("apauditid =", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidNotEqualTo(Long value) {
            addCriterion("apauditid <>", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidGreaterThan(Long value) {
            addCriterion("apauditid >", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidGreaterThanOrEqualTo(Long value) {
            addCriterion("apauditid >=", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidLessThan(Long value) {
            addCriterion("apauditid <", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidLessThanOrEqualTo(Long value) {
            addCriterion("apauditid <=", value, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidIn(List<Long> values) {
            addCriterion("apauditid in", values, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidNotIn(List<Long> values) {
            addCriterion("apauditid not in", values, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidBetween(Long value1, Long value2) {
            addCriterion("apauditid between", value1, value2, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApauditidNotBetween(Long value1, Long value2) {
            addCriterion("apauditid not between", value1, value2, "apauditid");
            return (Criteria) this;
        }

        public Criteria andApycidIsNull() {
            addCriterion("apycid is null");
            return (Criteria) this;
        }

        public Criteria andApycidIsNotNull() {
            addCriterion("apycid is not null");
            return (Criteria) this;
        }

        public Criteria andApycidEqualTo(Long value) {
            addCriterion("apycid =", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidNotEqualTo(Long value) {
            addCriterion("apycid <>", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidGreaterThan(Long value) {
            addCriterion("apycid >", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidGreaterThanOrEqualTo(Long value) {
            addCriterion("apycid >=", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidLessThan(Long value) {
            addCriterion("apycid <", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidLessThanOrEqualTo(Long value) {
            addCriterion("apycid <=", value, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidIn(List<Long> values) {
            addCriterion("apycid in", values, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidNotIn(List<Long> values) {
            addCriterion("apycid not in", values, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidBetween(Long value1, Long value2) {
            addCriterion("apycid between", value1, value2, "apycid");
            return (Criteria) this;
        }

        public Criteria andApycidNotBetween(Long value1, Long value2) {
            addCriterion("apycid not between", value1, value2, "apycid");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andPaysetidIsNull() {
            addCriterion("paysetid is null");
            return (Criteria) this;
        }

        public Criteria andPaysetidIsNotNull() {
            addCriterion("paysetid is not null");
            return (Criteria) this;
        }

        public Criteria andPaysetidEqualTo(Long value) {
            addCriterion("paysetid =", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidNotEqualTo(Long value) {
            addCriterion("paysetid <>", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidGreaterThan(Long value) {
            addCriterion("paysetid >", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidGreaterThanOrEqualTo(Long value) {
            addCriterion("paysetid >=", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidLessThan(Long value) {
            addCriterion("paysetid <", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidLessThanOrEqualTo(Long value) {
            addCriterion("paysetid <=", value, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidIn(List<Long> values) {
            addCriterion("paysetid in", values, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidNotIn(List<Long> values) {
            addCriterion("paysetid not in", values, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidBetween(Long value1, Long value2) {
            addCriterion("paysetid between", value1, value2, "paysetid");
            return (Criteria) this;
        }

        public Criteria andPaysetidNotBetween(Long value1, Long value2) {
            addCriterion("paysetid not between", value1, value2, "paysetid");
            return (Criteria) this;
        }

        public Criteria andAuditamtIsNull() {
            addCriterion("auditamt is null");
            return (Criteria) this;
        }

        public Criteria andAuditamtIsNotNull() {
            addCriterion("auditamt is not null");
            return (Criteria) this;
        }

        public Criteria andAuditamtEqualTo(BigDecimal value) {
            addCriterion("auditamt =", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtNotEqualTo(BigDecimal value) {
            addCriterion("auditamt <>", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtGreaterThan(BigDecimal value) {
            addCriterion("auditamt >", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("auditamt >=", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtLessThan(BigDecimal value) {
            addCriterion("auditamt <", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("auditamt <=", value, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtIn(List<BigDecimal> values) {
            addCriterion("auditamt in", values, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtNotIn(List<BigDecimal> values) {
            addCriterion("auditamt not in", values, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("auditamt between", value1, value2, "auditamt");
            return (Criteria) this;
        }

        public Criteria andAuditamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("auditamt not between", value1, value2, "auditamt");
            return (Criteria) this;
        }

        public Criteria andCodesizeIsNull() {
            addCriterion("codesize is null");
            return (Criteria) this;
        }

        public Criteria andCodesizeIsNotNull() {
            addCriterion("codesize is not null");
            return (Criteria) this;
        }

        public Criteria andCodesizeEqualTo(BigDecimal value) {
            addCriterion("codesize =", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeNotEqualTo(BigDecimal value) {
            addCriterion("codesize <>", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeGreaterThan(BigDecimal value) {
            addCriterion("codesize >", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("codesize >=", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeLessThan(BigDecimal value) {
            addCriterion("codesize <", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("codesize <=", value, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeIn(List<BigDecimal> values) {
            addCriterion("codesize in", values, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeNotIn(List<BigDecimal> values) {
            addCriterion("codesize not in", values, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("codesize between", value1, value2, "codesize");
            return (Criteria) this;
        }

        public Criteria andCodesizeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("codesize not between", value1, value2, "codesize");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("`source` is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("`source` is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("`source` =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("`source` <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("`source` >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("`source` >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("`source` <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("`source` <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("`source` in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("`source` not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("`source` between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("`source` not between", value1, value2, "source");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tra_applyaudit
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tra_applyaudit
     *
     * @mbggenerated
     */
    public static class Criterion implements Serializable {
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}