//package com.likes.common.mybatis.entity;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class CirclePushUserRecordExample implements Serializable {
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected String orderByClause;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected boolean distinct;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected List<Criteria> oredCriteria;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected Integer offset;
//
//    /**
//     * This field was generated by MyBatis Generator.
//     * This field corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected Integer limit;
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public CirclePushUserRecordExample() {
//        oredCriteria = new ArrayList<Criteria>();
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void setOrderByClause(String orderByClause) {
//        this.orderByClause = orderByClause;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public String getOrderByClause() {
//        return orderByClause;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void setDistinct(boolean distinct) {
//        this.distinct = distinct;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public boolean isDistinct() {
//        return distinct;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public List<Criteria> getOredCriteria() {
//        return oredCriteria;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void or(Criteria criteria) {
//        oredCriteria.add(criteria);
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public Criteria or() {
//        Criteria criteria = createCriteriaInternal();
//        oredCriteria.add(criteria);
//        return criteria;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public Criteria createCriteria() {
//        Criteria criteria = createCriteriaInternal();
//        if (oredCriteria.size() == 0) {
//            oredCriteria.add(criteria);
//        }
//        return criteria;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected Criteria createCriteriaInternal() {
//        Criteria criteria = new Criteria();
//        return criteria;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void clear() {
//        oredCriteria.clear();
//        orderByClause = null;
//        distinct = false;
//        offset = null;
//        limit = null;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void setOffset(Integer offset) {
//        this.offset = offset;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public Integer getOffset() {
//        return this.offset;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public void setLimit(Integer limit) {
//        this.limit = limit;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public Integer getLimit() {
//        return this.limit;
//    }
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public CirclePushUserRecordExample bound(Integer offset, Integer limit) {
//        this.offset = offset;
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * This class was generated by MyBatis Generator.
//     * This class corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    protected abstract static class GeneratedCriteria implements Serializable {
//        protected List<Criterion> criteria;
//
//        protected GeneratedCriteria() {
//            super();
//            criteria = new ArrayList<Criterion>();
//        }
//
//        public boolean isValid() {
//            return criteria.size() > 0;
//        }
//
//        public List<Criterion> getAllCriteria() {
//            return criteria;
//        }
//
//        public List<Criterion> getCriteria() {
//            return criteria;
//        }
//
//        protected void addCriterion(String condition) {
//            if (condition == null) {
//                throw new RuntimeException("Value for condition cannot be null");
//            }
//            criteria.add(new Criterion(condition));
//        }
//
//        protected void addCriterion(String condition, Object value, String property) {
//            if (value == null) {
//                throw new RuntimeException("Value for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value));
//        }
//
//        protected void addCriterion(String condition, Object value1, Object value2, String property) {
//            if (value1 == null || value2 == null) {
//                throw new RuntimeException("Between values for " + property + " cannot be null");
//            }
//            criteria.add(new Criterion(condition, value1, value2));
//        }
//
//        public Criteria andIdIsNull() {
//            addCriterion("id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIsNotNull() {
//            addCriterion("id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdEqualTo(Integer value) {
//            addCriterion("id =", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotEqualTo(Integer value) {
//            addCriterion("id <>", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThan(Integer value) {
//            addCriterion("id >", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("id >=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThan(Integer value) {
//            addCriterion("id <", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdLessThanOrEqualTo(Integer value) {
//            addCriterion("id <=", value, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdIn(List<Integer> values) {
//            addCriterion("id in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotIn(List<Integer> values) {
//            addCriterion("id not in", values, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdBetween(Integer value1, Integer value2) {
//            addCriterion("id between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("id not between", value1, value2, "id");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdIsNull() {
//            addCriterion("push_id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdIsNotNull() {
//            addCriterion("push_id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdEqualTo(Integer value) {
//            addCriterion("push_id =", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdNotEqualTo(Integer value) {
//            addCriterion("push_id <>", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdGreaterThan(Integer value) {
//            addCriterion("push_id >", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("push_id >=", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdLessThan(Integer value) {
//            addCriterion("push_id <", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdLessThanOrEqualTo(Integer value) {
//            addCriterion("push_id <=", value, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdIn(List<Integer> values) {
//            addCriterion("push_id in", values, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdNotIn(List<Integer> values) {
//            addCriterion("push_id not in", values, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdBetween(Integer value1, Integer value2) {
//            addCriterion("push_id between", value1, value2, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andPushIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("push_id not between", value1, value2, "pushId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdIsNull() {
//            addCriterion("user_id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdIsNotNull() {
//            addCriterion("user_id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdEqualTo(Integer value) {
//            addCriterion("user_id =", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdNotEqualTo(Integer value) {
//            addCriterion("user_id <>", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdGreaterThan(Integer value) {
//            addCriterion("user_id >", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("user_id >=", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdLessThan(Integer value) {
//            addCriterion("user_id <", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
//            addCriterion("user_id <=", value, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdIn(List<Integer> values) {
//            addCriterion("user_id in", values, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdNotIn(List<Integer> values) {
//            addCriterion("user_id not in", values, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdBetween(Integer value1, Integer value2) {
//            addCriterion("user_id between", value1, value2, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("user_id not between", value1, value2, "userId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdIsNull() {
//            addCriterion("order_bet_id is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdIsNotNull() {
//            addCriterion("order_bet_id is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdEqualTo(Integer value) {
//            addCriterion("order_bet_id =", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdNotEqualTo(Integer value) {
//            addCriterion("order_bet_id <>", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdGreaterThan(Integer value) {
//            addCriterion("order_bet_id >", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdGreaterThanOrEqualTo(Integer value) {
//            addCriterion("order_bet_id >=", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdLessThan(Integer value) {
//            addCriterion("order_bet_id <", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdLessThanOrEqualTo(Integer value) {
//            addCriterion("order_bet_id <=", value, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdIn(List<Integer> values) {
//            addCriterion("order_bet_id in", values, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdNotIn(List<Integer> values) {
//            addCriterion("order_bet_id not in", values, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdBetween(Integer value1, Integer value2) {
//            addCriterion("order_bet_id between", value1, value2, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderBetIdNotBetween(Integer value1, Integer value2) {
//            addCriterion("order_bet_id not between", value1, value2, "orderBetId");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnIsNull() {
//            addCriterion("order_sn is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnIsNotNull() {
//            addCriterion("order_sn is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnEqualTo(String value) {
//            addCriterion("order_sn =", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnNotEqualTo(String value) {
//            addCriterion("order_sn <>", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnGreaterThan(String value) {
//            addCriterion("order_sn >", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
//            addCriterion("order_sn >=", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnLessThan(String value) {
//            addCriterion("order_sn <", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnLessThanOrEqualTo(String value) {
//            addCriterion("order_sn <=", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnLike(String value) {
//            addCriterion("order_sn like", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnNotLike(String value) {
//            addCriterion("order_sn not like", value, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnIn(List<String> values) {
//            addCriterion("order_sn in", values, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnNotIn(List<String> values) {
//            addCriterion("order_sn not in", values, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnBetween(String value1, String value2) {
//            addCriterion("order_sn between", value1, value2, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andOrderSnNotBetween(String value1, String value2) {
//            addCriterion("order_sn not between", value1, value2, "orderSn");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueIsNull() {
//            addCriterion("issue is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueIsNotNull() {
//            addCriterion("issue is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueEqualTo(String value) {
//            addCriterion("issue =", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueNotEqualTo(String value) {
//            addCriterion("issue <>", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueGreaterThan(String value) {
//            addCriterion("issue >", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueGreaterThanOrEqualTo(String value) {
//            addCriterion("issue >=", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueLessThan(String value) {
//            addCriterion("issue <", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueLessThanOrEqualTo(String value) {
//            addCriterion("issue <=", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueLike(String value) {
//            addCriterion("issue like", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueNotLike(String value) {
//            addCriterion("issue not like", value, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueIn(List<String> values) {
//            addCriterion("issue in", values, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueNotIn(List<String> values) {
//            addCriterion("issue not in", values, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueBetween(String value1, String value2) {
//            addCriterion("issue between", value1, value2, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andIssueNotBetween(String value1, String value2) {
//            addCriterion("issue not between", value1, value2, "issue");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidIsNull() {
//            addCriterion("gid is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidIsNotNull() {
//            addCriterion("gid is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidEqualTo(Integer value) {
//            addCriterion("gid =", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidNotEqualTo(Integer value) {
//            addCriterion("gid <>", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidGreaterThan(Integer value) {
//            addCriterion("gid >", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidGreaterThanOrEqualTo(Integer value) {
//            addCriterion("gid >=", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidLessThan(Integer value) {
//            addCriterion("gid <", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidLessThanOrEqualTo(Integer value) {
//            addCriterion("gid <=", value, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidIn(List<Integer> values) {
//            addCriterion("gid in", values, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidNotIn(List<Integer> values) {
//            addCriterion("gid not in", values, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidBetween(Integer value1, Integer value2) {
//            addCriterion("gid between", value1, value2, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andGidNotBetween(Integer value1, Integer value2) {
//            addCriterion("gid not between", value1, value2, "gid");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIsNull() {
//            addCriterion("create_time is null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIsNotNull() {
//            addCriterion("create_time is not null");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeEqualTo(Date value) {
//            addCriterion("create_time =", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotEqualTo(Date value) {
//            addCriterion("create_time <>", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeGreaterThan(Date value) {
//            addCriterion("create_time >", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
//            addCriterion("create_time >=", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeLessThan(Date value) {
//            addCriterion("create_time <", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
//            addCriterion("create_time <=", value, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeIn(List<Date> values) {
//            addCriterion("create_time in", values, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotIn(List<Date> values) {
//            addCriterion("create_time not in", values, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeBetween(Date value1, Date value2) {
//            addCriterion("create_time between", value1, value2, "createTime");
//            return (Criteria) this;
//        }
//
//        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
//            addCriterion("create_time not between", value1, value2, "createTime");
//            return (Criteria) this;
//        }
//    }
//
//    /**
//     * This class was generated by MyBatis Generator.
//     * This class corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated do_not_delete_during_merge
//     */
//    public static class Criteria extends GeneratedCriteria implements Serializable {
//
//        protected Criteria() {
//            super();
//        }
//    }
//
//    /**
//     * This class was generated by MyBatis Generator.
//     * This class corresponds to the database table circle_push_user_record
//     *
//     * @mbggenerated
//     */
//    public static class Criterion implements Serializable {
//        private String condition;
//
//        private Object value;
//
//        private Object secondValue;
//
//        private boolean noValue;
//
//        private boolean singleValue;
//
//        private boolean betweenValue;
//
//        private boolean listValue;
//
//        private String typeHandler;
//
//        public String getCondition() {
//            return condition;
//        }
//
//        public Object getValue() {
//            return value;
//        }
//
//        public Object getSecondValue() {
//            return secondValue;
//        }
//
//        public boolean isNoValue() {
//            return noValue;
//        }
//
//        public boolean isSingleValue() {
//            return singleValue;
//        }
//
//        public boolean isBetweenValue() {
//            return betweenValue;
//        }
//
//        public boolean isListValue() {
//            return listValue;
//        }
//
//        public String getTypeHandler() {
//            return typeHandler;
//        }
//
//        protected Criterion(String condition) {
//            super();
//            this.condition = condition;
//            this.typeHandler = null;
//            this.noValue = true;
//        }
//
//        protected Criterion(String condition, Object value, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.typeHandler = typeHandler;
//            if (value instanceof List<?>) {
//                this.listValue = true;
//            } else {
//                this.singleValue = true;
//            }
//        }
//
//        protected Criterion(String condition, Object value) {
//            this(condition, value, null);
//        }
//
//        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
//            super();
//            this.condition = condition;
//            this.value = value;
//            this.secondValue = secondValue;
//            this.typeHandler = typeHandler;
//            this.betweenValue = true;
//        }
//
//        protected Criterion(String condition, Object value, Object secondValue) {
//            this(condition, value, secondValue, null);
//        }
//    }
//}