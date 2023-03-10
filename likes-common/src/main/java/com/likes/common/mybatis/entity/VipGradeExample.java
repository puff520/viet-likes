package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VipGradeExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected Integer limit;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public VipGradeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
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
     * This method corresponds to the database table vip_grade
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
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
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
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public Integer getLimit() {
        return this.limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_grade
     *
     * @mbggenerated
     */
    public VipGradeExample bound(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vip_grade
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andValidBetIsNull() {
            addCriterion("valid_bet is null");
            return (Criteria) this;
        }

        public Criteria andValidBetIsNotNull() {
            addCriterion("valid_bet is not null");
            return (Criteria) this;
        }

        public Criteria andValidBetEqualTo(BigDecimal value) {
            addCriterion("valid_bet =", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetNotEqualTo(BigDecimal value) {
            addCriterion("valid_bet <>", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetGreaterThan(BigDecimal value) {
            addCriterion("valid_bet >", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("valid_bet >=", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetLessThan(BigDecimal value) {
            addCriterion("valid_bet <", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("valid_bet <=", value, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetIn(List<BigDecimal> values) {
            addCriterion("valid_bet in", values, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetNotIn(List<BigDecimal> values) {
            addCriterion("valid_bet not in", values, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("valid_bet between", value1, value2, "validBet");
            return (Criteria) this;
        }

        public Criteria andValidBetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("valid_bet not between", value1, value2, "validBet");
            return (Criteria) this;
        }

        public Criteria andHandselIsNull() {
            addCriterion("handsel is null");
            return (Criteria) this;
        }

        public Criteria andHandselIsNotNull() {
            addCriterion("handsel is not null");
            return (Criteria) this;
        }

        public Criteria andHandselEqualTo(BigDecimal value) {
            addCriterion("handsel =", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselNotEqualTo(BigDecimal value) {
            addCriterion("handsel <>", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselGreaterThan(BigDecimal value) {
            addCriterion("handsel >", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("handsel >=", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselLessThan(BigDecimal value) {
            addCriterion("handsel <", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselLessThanOrEqualTo(BigDecimal value) {
            addCriterion("handsel <=", value, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselIn(List<BigDecimal> values) {
            addCriterion("handsel in", values, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselNotIn(List<BigDecimal> values) {
            addCriterion("handsel not in", values, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("handsel between", value1, value2, "handsel");
            return (Criteria) this;
        }

        public Criteria andHandselNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("handsel not between", value1, value2, "handsel");
            return (Criteria) this;
        }

        public Criteria andBackwaterIsNull() {
            addCriterion("backwater is null");
            return (Criteria) this;
        }

        public Criteria andBackwaterIsNotNull() {
            addCriterion("backwater is not null");
            return (Criteria) this;
        }

        public Criteria andBackwaterEqualTo(Integer value) {
            addCriterion("backwater =", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterNotEqualTo(Integer value) {
            addCriterion("backwater <>", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterGreaterThan(Integer value) {
            addCriterion("backwater >", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("backwater >=", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterLessThan(Integer value) {
            addCriterion("backwater <", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterLessThanOrEqualTo(Integer value) {
            addCriterion("backwater <=", value, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterIn(List<Integer> values) {
            addCriterion("backwater in", values, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterNotIn(List<Integer> values) {
            addCriterion("backwater not in", values, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterBetween(Integer value1, Integer value2) {
            addCriterion("backwater between", value1, value2, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterNotBetween(Integer value1, Integer value2) {
            addCriterion("backwater not between", value1, value2, "backwater");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorIsNull() {
            addCriterion("backwater_denominator is null");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorIsNotNull() {
            addCriterion("backwater_denominator is not null");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorEqualTo(Integer value) {
            addCriterion("backwater_denominator =", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorNotEqualTo(Integer value) {
            addCriterion("backwater_denominator <>", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorGreaterThan(Integer value) {
            addCriterion("backwater_denominator >", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("backwater_denominator >=", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorLessThan(Integer value) {
            addCriterion("backwater_denominator <", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorLessThanOrEqualTo(Integer value) {
            addCriterion("backwater_denominator <=", value, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorIn(List<Integer> values) {
            addCriterion("backwater_denominator in", values, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorNotIn(List<Integer> values) {
            addCriterion("backwater_denominator not in", values, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorBetween(Integer value1, Integer value2) {
            addCriterion("backwater_denominator between", value1, value2, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andBackwaterDenominatorNotBetween(Integer value1, Integer value2) {
            addCriterion("backwater_denominator not between", value1, value2, "backwaterDenominator");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNull() {
            addCriterion("privilege is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNotNull() {
            addCriterion("privilege is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeEqualTo(String value) {
            addCriterion("privilege =", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotEqualTo(String value) {
            addCriterion("privilege <>", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThan(String value) {
            addCriterion("privilege >", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThanOrEqualTo(String value) {
            addCriterion("privilege >=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThan(String value) {
            addCriterion("privilege <", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThanOrEqualTo(String value) {
            addCriterion("privilege <=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLike(String value) {
            addCriterion("privilege like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotLike(String value) {
            addCriterion("privilege not like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIn(List<String> values) {
            addCriterion("privilege in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotIn(List<String> values) {
            addCriterion("privilege not in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeBetween(String value1, String value2) {
            addCriterion("privilege between", value1, value2, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotBetween(String value1, String value2) {
            addCriterion("privilege not between", value1, value2, "privilege");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Integer value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Integer value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Integer value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Integer value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Integer> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Integer> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Integer value1, Integer value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andWaterRatioIsNull() {
            addCriterion("water_ratio is null");
            return (Criteria) this;
        }

        public Criteria andWaterRatioIsNotNull() {
            addCriterion("water_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andWaterRatioEqualTo(Integer value) {
            addCriterion("water_ratio =", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioNotEqualTo(Integer value) {
            addCriterion("water_ratio <>", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioGreaterThan(Integer value) {
            addCriterion("water_ratio >", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_ratio >=", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioLessThan(Integer value) {
            addCriterion("water_ratio <", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioLessThanOrEqualTo(Integer value) {
            addCriterion("water_ratio <=", value, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioIn(List<Integer> values) {
            addCriterion("water_ratio in", values, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioNotIn(List<Integer> values) {
            addCriterion("water_ratio not in", values, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioBetween(Integer value1, Integer value2) {
            addCriterion("water_ratio between", value1, value2, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andWaterRatioNotBetween(Integer value1, Integer value2) {
            addCriterion("water_ratio not between", value1, value2, "waterRatio");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountIsNull() {
            addCriterion("recharge_amount is null");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountIsNotNull() {
            addCriterion("recharge_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountEqualTo(BigDecimal value) {
            addCriterion("recharge_amount =", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountNotEqualTo(BigDecimal value) {
            addCriterion("recharge_amount <>", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountGreaterThan(BigDecimal value) {
            addCriterion("recharge_amount >", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_amount >=", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountLessThan(BigDecimal value) {
            addCriterion("recharge_amount <", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("recharge_amount <=", value, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountIn(List<BigDecimal> values) {
            addCriterion("recharge_amount in", values, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountNotIn(List<BigDecimal> values) {
            addCriterion("recharge_amount not in", values, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_amount between", value1, value2, "rechargeAmount");
            return (Criteria) this;
        }

        public Criteria andRechargeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("recharge_amount not between", value1, value2, "rechargeAmount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table vip_grade
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
     * This class corresponds to the database table vip_grade
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
