package com.likes.common.mybatis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfPushExample implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected Integer offset;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected Integer limit;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public InfPushExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
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
     * This method corresponds to the database table inf_push
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
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
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
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public Integer getOffset() {
        return this.offset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public Integer getLimit() {
        return this.limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inf_push
     *
     * @mbggenerated
     */
    public InfPushExample bound(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inf_push
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

        public Criteria andBdpushidIsNull() {
            addCriterion("bdpushid is null");
            return (Criteria) this;
        }

        public Criteria andBdpushidIsNotNull() {
            addCriterion("bdpushid is not null");
            return (Criteria) this;
        }

        public Criteria andBdpushidEqualTo(Long value) {
            addCriterion("bdpushid =", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidNotEqualTo(Long value) {
            addCriterion("bdpushid <>", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidGreaterThan(Long value) {
            addCriterion("bdpushid >", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidGreaterThanOrEqualTo(Long value) {
            addCriterion("bdpushid >=", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidLessThan(Long value) {
            addCriterion("bdpushid <", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidLessThanOrEqualTo(Long value) {
            addCriterion("bdpushid <=", value, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidIn(List<Long> values) {
            addCriterion("bdpushid in", values, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidNotIn(List<Long> values) {
            addCriterion("bdpushid not in", values, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidBetween(Long value1, Long value2) {
            addCriterion("bdpushid between", value1, value2, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andBdpushidNotBetween(Long value1, Long value2) {
            addCriterion("bdpushid not between", value1, value2, "bdpushid");
            return (Criteria) this;
        }

        public Criteria andPushtypeIsNull() {
            addCriterion("pushtype is null");
            return (Criteria) this;
        }

        public Criteria andPushtypeIsNotNull() {
            addCriterion("pushtype is not null");
            return (Criteria) this;
        }

        public Criteria andPushtypeEqualTo(String value) {
            addCriterion("pushtype =", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotEqualTo(String value) {
            addCriterion("pushtype <>", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeGreaterThan(String value) {
            addCriterion("pushtype >", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeGreaterThanOrEqualTo(String value) {
            addCriterion("pushtype >=", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLessThan(String value) {
            addCriterion("pushtype <", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLessThanOrEqualTo(String value) {
            addCriterion("pushtype <=", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeLike(String value) {
            addCriterion("pushtype like", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotLike(String value) {
            addCriterion("pushtype not like", value, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeIn(List<String> values) {
            addCriterion("pushtype in", values, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotIn(List<String> values) {
            addCriterion("pushtype not in", values, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeBetween(String value1, String value2) {
            addCriterion("pushtype between", value1, value2, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtypeNotBetween(String value1, String value2) {
            addCriterion("pushtype not between", value1, value2, "pushtype");
            return (Criteria) this;
        }

        public Criteria andPushtitleIsNull() {
            addCriterion("pushtitle is null");
            return (Criteria) this;
        }

        public Criteria andPushtitleIsNotNull() {
            addCriterion("pushtitle is not null");
            return (Criteria) this;
        }

        public Criteria andPushtitleEqualTo(String value) {
            addCriterion("pushtitle =", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleNotEqualTo(String value) {
            addCriterion("pushtitle <>", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleGreaterThan(String value) {
            addCriterion("pushtitle >", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleGreaterThanOrEqualTo(String value) {
            addCriterion("pushtitle >=", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleLessThan(String value) {
            addCriterion("pushtitle <", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleLessThanOrEqualTo(String value) {
            addCriterion("pushtitle <=", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleLike(String value) {
            addCriterion("pushtitle like", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleNotLike(String value) {
            addCriterion("pushtitle not like", value, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleIn(List<String> values) {
            addCriterion("pushtitle in", values, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleNotIn(List<String> values) {
            addCriterion("pushtitle not in", values, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleBetween(String value1, String value2) {
            addCriterion("pushtitle between", value1, value2, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushtitleNotBetween(String value1, String value2) {
            addCriterion("pushtitle not between", value1, value2, "pushtitle");
            return (Criteria) this;
        }

        public Criteria andPushbodyIsNull() {
            addCriterion("pushbody is null");
            return (Criteria) this;
        }

        public Criteria andPushbodyIsNotNull() {
            addCriterion("pushbody is not null");
            return (Criteria) this;
        }

        public Criteria andPushbodyEqualTo(String value) {
            addCriterion("pushbody =", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyNotEqualTo(String value) {
            addCriterion("pushbody <>", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyGreaterThan(String value) {
            addCriterion("pushbody >", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyGreaterThanOrEqualTo(String value) {
            addCriterion("pushbody >=", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyLessThan(String value) {
            addCriterion("pushbody <", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyLessThanOrEqualTo(String value) {
            addCriterion("pushbody <=", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyLike(String value) {
            addCriterion("pushbody like", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyNotLike(String value) {
            addCriterion("pushbody not like", value, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyIn(List<String> values) {
            addCriterion("pushbody in", values, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyNotIn(List<String> values) {
            addCriterion("pushbody not in", values, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyBetween(String value1, String value2) {
            addCriterion("pushbody between", value1, value2, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushbodyNotBetween(String value1, String value2) {
            addCriterion("pushbody not between", value1, value2, "pushbody");
            return (Criteria) this;
        }

        public Criteria andPushdateIsNull() {
            addCriterion("pushdate is null");
            return (Criteria) this;
        }

        public Criteria andPushdateIsNotNull() {
            addCriterion("pushdate is not null");
            return (Criteria) this;
        }

        public Criteria andPushdateEqualTo(Date value) {
            addCriterion("pushdate =", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotEqualTo(Date value) {
            addCriterion("pushdate <>", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateGreaterThan(Date value) {
            addCriterion("pushdate >", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateGreaterThanOrEqualTo(Date value) {
            addCriterion("pushdate >=", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateLessThan(Date value) {
            addCriterion("pushdate <", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateLessThanOrEqualTo(Date value) {
            addCriterion("pushdate <=", value, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateIn(List<Date> values) {
            addCriterion("pushdate in", values, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotIn(List<Date> values) {
            addCriterion("pushdate not in", values, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateBetween(Date value1, Date value2) {
            addCriterion("pushdate between", value1, value2, "pushdate");
            return (Criteria) this;
        }

        public Criteria andPushdateNotBetween(Date value1, Date value2) {
            addCriterion("pushdate not between", value1, value2, "pushdate");
            return (Criteria) this;
        }

        public Criteria andSortbyIsNull() {
            addCriterion("sortby is null");
            return (Criteria) this;
        }

        public Criteria andSortbyIsNotNull() {
            addCriterion("sortby is not null");
            return (Criteria) this;
        }

        public Criteria andSortbyEqualTo(Integer value) {
            addCriterion("sortby =", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyNotEqualTo(Integer value) {
            addCriterion("sortby <>", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyGreaterThan(Integer value) {
            addCriterion("sortby >", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortby >=", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyLessThan(Integer value) {
            addCriterion("sortby <", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyLessThanOrEqualTo(Integer value) {
            addCriterion("sortby <=", value, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyIn(List<Integer> values) {
            addCriterion("sortby in", values, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyNotIn(List<Integer> values) {
            addCriterion("sortby not in", values, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyBetween(Integer value1, Integer value2) {
            addCriterion("sortby between", value1, value2, "sortby");
            return (Criteria) this;
        }

        public Criteria andSortbyNotBetween(Integer value1, Integer value2) {
            addCriterion("sortby not between", value1, value2, "sortby");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table inf_push
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
     * This class corresponds to the database table inf_push
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