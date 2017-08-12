package com.viewt.rest.entity.restdata;

import java.util.ArrayList;
import java.util.List;

public class DpShopsBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DpShopsBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameIsNull() {
            addCriterion("sourceFileName is null");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameIsNotNull() {
            addCriterion("sourceFileName is not null");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameEqualTo(String value) {
            addCriterion("sourceFileName =", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameNotEqualTo(String value) {
            addCriterion("sourceFileName <>", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameGreaterThan(String value) {
            addCriterion("sourceFileName >", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameGreaterThanOrEqualTo(String value) {
            addCriterion("sourceFileName >=", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameLessThan(String value) {
            addCriterion("sourceFileName <", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameLessThanOrEqualTo(String value) {
            addCriterion("sourceFileName <=", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameLike(String value) {
            addCriterion("sourceFileName like", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameNotLike(String value) {
            addCriterion("sourceFileName not like", value, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameIn(List<String> values) {
            addCriterion("sourceFileName in", values, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameNotIn(List<String> values) {
            addCriterion("sourceFileName not in", values, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameBetween(String value1, String value2) {
            addCriterion("sourceFileName between", value1, value2, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSourcefilenameNotBetween(String value1, String value2) {
            addCriterion("sourceFileName not between", value1, value2, "sourcefilename");
            return (Criteria) this;
        }

        public Criteria andSummarysIsNull() {
            addCriterion("summarys is null");
            return (Criteria) this;
        }

        public Criteria andSummarysIsNotNull() {
            addCriterion("summarys is not null");
            return (Criteria) this;
        }

        public Criteria andSummarysEqualTo(String value) {
            addCriterion("summarys =", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysNotEqualTo(String value) {
            addCriterion("summarys <>", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysGreaterThan(String value) {
            addCriterion("summarys >", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysGreaterThanOrEqualTo(String value) {
            addCriterion("summarys >=", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysLessThan(String value) {
            addCriterion("summarys <", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysLessThanOrEqualTo(String value) {
            addCriterion("summarys <=", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysLike(String value) {
            addCriterion("summarys like", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysNotLike(String value) {
            addCriterion("summarys not like", value, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysIn(List<String> values) {
            addCriterion("summarys in", values, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysNotIn(List<String> values) {
            addCriterion("summarys not in", values, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysBetween(String value1, String value2) {
            addCriterion("summarys between", value1, value2, "summarys");
            return (Criteria) this;
        }

        public Criteria andSummarysNotBetween(String value1, String value2) {
            addCriterion("summarys not between", value1, value2, "summarys");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIsNull() {
            addCriterion("avgPrice is null");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIsNotNull() {
            addCriterion("avgPrice is not null");
            return (Criteria) this;
        }

        public Criteria andAvgpriceEqualTo(Double value) {
            addCriterion("avgPrice =", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotEqualTo(Double value) {
            addCriterion("avgPrice <>", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceGreaterThan(Double value) {
            addCriterion("avgPrice >", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("avgPrice >=", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceLessThan(Double value) {
            addCriterion("avgPrice <", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceLessThanOrEqualTo(Double value) {
            addCriterion("avgPrice <=", value, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceIn(List<Double> values) {
            addCriterion("avgPrice in", values, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotIn(List<Double> values) {
            addCriterion("avgPrice not in", values, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceBetween(Double value1, Double value2) {
            addCriterion("avgPrice between", value1, value2, "avgprice");
            return (Criteria) this;
        }

        public Criteria andAvgpriceNotBetween(Double value1, Double value2) {
            addCriterion("avgPrice not between", value1, value2, "avgprice");
            return (Criteria) this;
        }

        public Criteria andShopcityidIsNull() {
            addCriterion("shopCityId is null");
            return (Criteria) this;
        }

        public Criteria andShopcityidIsNotNull() {
            addCriterion("shopCityId is not null");
            return (Criteria) this;
        }

        public Criteria andShopcityidEqualTo(Integer value) {
            addCriterion("shopCityId =", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidNotEqualTo(Integer value) {
            addCriterion("shopCityId <>", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidGreaterThan(Integer value) {
            addCriterion("shopCityId >", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopCityId >=", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidLessThan(Integer value) {
            addCriterion("shopCityId <", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidLessThanOrEqualTo(Integer value) {
            addCriterion("shopCityId <=", value, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidIn(List<Integer> values) {
            addCriterion("shopCityId in", values, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidNotIn(List<Integer> values) {
            addCriterion("shopCityId not in", values, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidBetween(Integer value1, Integer value2) {
            addCriterion("shopCityId between", value1, value2, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andShopcityidNotBetween(Integer value1, Integer value2) {
            addCriterion("shopCityId not between", value1, value2, "shopcityid");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeIsNull() {
            addCriterion("lastReviewTime is null");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeIsNotNull() {
            addCriterion("lastReviewTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeEqualTo(Long value) {
            addCriterion("lastReviewTime =", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeNotEqualTo(Long value) {
            addCriterion("lastReviewTime <>", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeGreaterThan(Long value) {
            addCriterion("lastReviewTime >", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("lastReviewTime >=", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeLessThan(Long value) {
            addCriterion("lastReviewTime <", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeLessThanOrEqualTo(Long value) {
            addCriterion("lastReviewTime <=", value, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeIn(List<Long> values) {
            addCriterion("lastReviewTime in", values, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeNotIn(List<Long> values) {
            addCriterion("lastReviewTime not in", values, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeBetween(Long value1, Long value2) {
            addCriterion("lastReviewTime between", value1, value2, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andLastreviewtimeNotBetween(Long value1, Long value2) {
            addCriterion("lastReviewTime not between", value1, value2, "lastreviewtime");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("cityId is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("cityId is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(Integer value) {
            addCriterion("cityId =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(Integer value) {
            addCriterion("cityId <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(Integer value) {
            addCriterion("cityId >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityId >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(Integer value) {
            addCriterion("cityId <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(Integer value) {
            addCriterion("cityId <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<Integer> values) {
            addCriterion("cityId in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<Integer> values) {
            addCriterion("cityId not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(Integer value1, Integer value2) {
            addCriterion("cityId between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("cityId not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andShopglatIsNull() {
            addCriterion("shopGlat is null");
            return (Criteria) this;
        }

        public Criteria andShopglatIsNotNull() {
            addCriterion("shopGlat is not null");
            return (Criteria) this;
        }

        public Criteria andShopglatEqualTo(String value) {
            addCriterion("shopGlat =", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatNotEqualTo(String value) {
            addCriterion("shopGlat <>", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatGreaterThan(String value) {
            addCriterion("shopGlat >", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatGreaterThanOrEqualTo(String value) {
            addCriterion("shopGlat >=", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatLessThan(String value) {
            addCriterion("shopGlat <", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatLessThanOrEqualTo(String value) {
            addCriterion("shopGlat <=", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatLike(String value) {
            addCriterion("shopGlat like", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatNotLike(String value) {
            addCriterion("shopGlat not like", value, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatIn(List<String> values) {
            addCriterion("shopGlat in", values, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatNotIn(List<String> values) {
            addCriterion("shopGlat not in", values, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatBetween(String value1, String value2) {
            addCriterion("shopGlat between", value1, value2, "shopglat");
            return (Criteria) this;
        }

        public Criteria andShopglatNotBetween(String value1, String value2) {
            addCriterion("shopGlat not between", value1, value2, "shopglat");
            return (Criteria) this;
        }

        public Criteria andSmellIsNull() {
            addCriterion("smell is null");
            return (Criteria) this;
        }

        public Criteria andSmellIsNotNull() {
            addCriterion("smell is not null");
            return (Criteria) this;
        }

        public Criteria andSmellEqualTo(Double value) {
            addCriterion("smell =", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellNotEqualTo(Double value) {
            addCriterion("smell <>", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellGreaterThan(Double value) {
            addCriterion("smell >", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellGreaterThanOrEqualTo(Double value) {
            addCriterion("smell >=", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellLessThan(Double value) {
            addCriterion("smell <", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellLessThanOrEqualTo(Double value) {
            addCriterion("smell <=", value, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellIn(List<Double> values) {
            addCriterion("smell in", values, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellNotIn(List<Double> values) {
            addCriterion("smell not in", values, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellBetween(Double value1, Double value2) {
            addCriterion("smell between", value1, value2, "smell");
            return (Criteria) this;
        }

        public Criteria andSmellNotBetween(Double value1, Double value2) {
            addCriterion("smell not between", value1, value2, "smell");
            return (Criteria) this;
        }

        public Criteria andMainregionidIsNull() {
            addCriterion("mainRegionId is null");
            return (Criteria) this;
        }

        public Criteria andMainregionidIsNotNull() {
            addCriterion("mainRegionId is not null");
            return (Criteria) this;
        }

        public Criteria andMainregionidEqualTo(Integer value) {
            addCriterion("mainRegionId =", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidNotEqualTo(Integer value) {
            addCriterion("mainRegionId <>", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidGreaterThan(Integer value) {
            addCriterion("mainRegionId >", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mainRegionId >=", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidLessThan(Integer value) {
            addCriterion("mainRegionId <", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidLessThanOrEqualTo(Integer value) {
            addCriterion("mainRegionId <=", value, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidIn(List<Integer> values) {
            addCriterion("mainRegionId in", values, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidNotIn(List<Integer> values) {
            addCriterion("mainRegionId not in", values, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidBetween(Integer value1, Integer value2) {
            addCriterion("mainRegionId between", value1, value2, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andMainregionidNotBetween(Integer value1, Integer value2) {
            addCriterion("mainRegionId not between", value1, value2, "mainregionid");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2IsNull() {
            addCriterion("reviewCountStar2 is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2IsNotNull() {
            addCriterion("reviewCountStar2 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2EqualTo(Integer value) {
            addCriterion("reviewCountStar2 =", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2NotEqualTo(Integer value) {
            addCriterion("reviewCountStar2 <>", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2GreaterThan(Integer value) {
            addCriterion("reviewCountStar2 >", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2GreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar2 >=", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2LessThan(Integer value) {
            addCriterion("reviewCountStar2 <", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2LessThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar2 <=", value, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2In(List<Integer> values) {
            addCriterion("reviewCountStar2 in", values, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2NotIn(List<Integer> values) {
            addCriterion("reviewCountStar2 not in", values, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2Between(Integer value1, Integer value2) {
            addCriterion("reviewCountStar2 between", value1, value2, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar2NotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCountStar2 not between", value1, value2, "reviewcountstar2");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1IsNull() {
            addCriterion("reviewCountStar1 is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1IsNotNull() {
            addCriterion("reviewCountStar1 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1EqualTo(Integer value) {
            addCriterion("reviewCountStar1 =", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1NotEqualTo(Integer value) {
            addCriterion("reviewCountStar1 <>", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1GreaterThan(Integer value) {
            addCriterion("reviewCountStar1 >", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1GreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar1 >=", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1LessThan(Integer value) {
            addCriterion("reviewCountStar1 <", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1LessThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar1 <=", value, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1In(List<Integer> values) {
            addCriterion("reviewCountStar1 in", values, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1NotIn(List<Integer> values) {
            addCriterion("reviewCountStar1 not in", values, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1Between(Integer value1, Integer value2) {
            addCriterion("reviewCountStar1 between", value1, value2, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar1NotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCountStar1 not between", value1, value2, "reviewcountstar1");
            return (Criteria) this;
        }

        public Criteria andShopglngIsNull() {
            addCriterion("shopGlng is null");
            return (Criteria) this;
        }

        public Criteria andShopglngIsNotNull() {
            addCriterion("shopGlng is not null");
            return (Criteria) this;
        }

        public Criteria andShopglngEqualTo(String value) {
            addCriterion("shopGlng =", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngNotEqualTo(String value) {
            addCriterion("shopGlng <>", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngGreaterThan(String value) {
            addCriterion("shopGlng >", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngGreaterThanOrEqualTo(String value) {
            addCriterion("shopGlng >=", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngLessThan(String value) {
            addCriterion("shopGlng <", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngLessThanOrEqualTo(String value) {
            addCriterion("shopGlng <=", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngLike(String value) {
            addCriterion("shopGlng like", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngNotLike(String value) {
            addCriterion("shopGlng not like", value, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngIn(List<String> values) {
            addCriterion("shopGlng in", values, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngNotIn(List<String> values) {
            addCriterion("shopGlng not in", values, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngBetween(String value1, String value2) {
            addCriterion("shopGlng between", value1, value2, "shopglng");
            return (Criteria) this;
        }

        public Criteria andShopglngNotBetween(String value1, String value2) {
            addCriterion("shopGlng not between", value1, value2, "shopglng");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4IsNull() {
            addCriterion("reviewCountStar4 is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4IsNotNull() {
            addCriterion("reviewCountStar4 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4EqualTo(Integer value) {
            addCriterion("reviewCountStar4 =", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4NotEqualTo(Integer value) {
            addCriterion("reviewCountStar4 <>", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4GreaterThan(Integer value) {
            addCriterion("reviewCountStar4 >", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4GreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar4 >=", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4LessThan(Integer value) {
            addCriterion("reviewCountStar4 <", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4LessThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar4 <=", value, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4In(List<Integer> values) {
            addCriterion("reviewCountStar4 in", values, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4NotIn(List<Integer> values) {
            addCriterion("reviewCountStar4 not in", values, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4Between(Integer value1, Integer value2) {
            addCriterion("reviewCountStar4 between", value1, value2, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar4NotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCountStar4 not between", value1, value2, "reviewcountstar4");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3IsNull() {
            addCriterion("reviewCountStar3 is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3IsNotNull() {
            addCriterion("reviewCountStar3 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3EqualTo(Integer value) {
            addCriterion("reviewCountStar3 =", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3NotEqualTo(Integer value) {
            addCriterion("reviewCountStar3 <>", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3GreaterThan(Integer value) {
            addCriterion("reviewCountStar3 >", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3GreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar3 >=", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3LessThan(Integer value) {
            addCriterion("reviewCountStar3 <", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3LessThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar3 <=", value, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3In(List<Integer> values) {
            addCriterion("reviewCountStar3 in", values, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3NotIn(List<Integer> values) {
            addCriterion("reviewCountStar3 not in", values, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3Between(Integer value1, Integer value2) {
            addCriterion("reviewCountStar3 between", value1, value2, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar3NotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCountStar3 not between", value1, value2, "reviewcountstar3");
            return (Criteria) this;
        }

        public Criteria andCitynameIsNull() {
            addCriterion("cityName is null");
            return (Criteria) this;
        }

        public Criteria andCitynameIsNotNull() {
            addCriterion("cityName is not null");
            return (Criteria) this;
        }

        public Criteria andCitynameEqualTo(String value) {
            addCriterion("cityName =", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotEqualTo(String value) {
            addCriterion("cityName <>", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameGreaterThan(String value) {
            addCriterion("cityName >", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameGreaterThanOrEqualTo(String value) {
            addCriterion("cityName >=", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLessThan(String value) {
            addCriterion("cityName <", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLessThanOrEqualTo(String value) {
            addCriterion("cityName <=", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameLike(String value) {
            addCriterion("cityName like", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotLike(String value) {
            addCriterion("cityName not like", value, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameIn(List<String> values) {
            addCriterion("cityName in", values, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotIn(List<String> values) {
            addCriterion("cityName not in", values, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameBetween(String value1, String value2) {
            addCriterion("cityName between", value1, value2, "cityname");
            return (Criteria) this;
        }

        public Criteria andCitynameNotBetween(String value1, String value2) {
            addCriterion("cityName not between", value1, value2, "cityname");
            return (Criteria) this;
        }

        public Criteria andReviewcountIsNull() {
            addCriterion("reviewCount is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountIsNotNull() {
            addCriterion("reviewCount is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountEqualTo(Integer value) {
            addCriterion("reviewCount =", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountNotEqualTo(Integer value) {
            addCriterion("reviewCount <>", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountGreaterThan(Integer value) {
            addCriterion("reviewCount >", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCount >=", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountLessThan(Integer value) {
            addCriterion("reviewCount <", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountLessThanOrEqualTo(Integer value) {
            addCriterion("reviewCount <=", value, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountIn(List<Integer> values) {
            addCriterion("reviewCount in", values, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountNotIn(List<Integer> values) {
            addCriterion("reviewCount not in", values, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountBetween(Integer value1, Integer value2) {
            addCriterion("reviewCount between", value1, value2, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountNotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCount not between", value1, value2, "reviewcount");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5IsNull() {
            addCriterion("reviewCountStar5 is null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5IsNotNull() {
            addCriterion("reviewCountStar5 is not null");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5EqualTo(Integer value) {
            addCriterion("reviewCountStar5 =", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5NotEqualTo(Integer value) {
            addCriterion("reviewCountStar5 <>", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5GreaterThan(Integer value) {
            addCriterion("reviewCountStar5 >", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5GreaterThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar5 >=", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5LessThan(Integer value) {
            addCriterion("reviewCountStar5 <", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5LessThanOrEqualTo(Integer value) {
            addCriterion("reviewCountStar5 <=", value, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5In(List<Integer> values) {
            addCriterion("reviewCountStar5 in", values, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5NotIn(List<Integer> values) {
            addCriterion("reviewCountStar5 not in", values, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5Between(Integer value1, Integer value2) {
            addCriterion("reviewCountStar5 between", value1, value2, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andReviewcountstar5NotBetween(Integer value1, Integer value2) {
            addCriterion("reviewCountStar5 not between", value1, value2, "reviewcountstar5");
            return (Criteria) this;
        }

        public Criteria andCityglngIsNull() {
            addCriterion("cityGlng is null");
            return (Criteria) this;
        }

        public Criteria andCityglngIsNotNull() {
            addCriterion("cityGlng is not null");
            return (Criteria) this;
        }

        public Criteria andCityglngEqualTo(String value) {
            addCriterion("cityGlng =", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngNotEqualTo(String value) {
            addCriterion("cityGlng <>", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngGreaterThan(String value) {
            addCriterion("cityGlng >", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngGreaterThanOrEqualTo(String value) {
            addCriterion("cityGlng >=", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngLessThan(String value) {
            addCriterion("cityGlng <", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngLessThanOrEqualTo(String value) {
            addCriterion("cityGlng <=", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngLike(String value) {
            addCriterion("cityGlng like", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngNotLike(String value) {
            addCriterion("cityGlng not like", value, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngIn(List<String> values) {
            addCriterion("cityGlng in", values, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngNotIn(List<String> values) {
            addCriterion("cityGlng not in", values, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngBetween(String value1, String value2) {
            addCriterion("cityGlng between", value1, value2, "cityglng");
            return (Criteria) this;
        }

        public Criteria andCityglngNotBetween(String value1, String value2) {
            addCriterion("cityGlng not between", value1, value2, "cityglng");
            return (Criteria) this;
        }

        public Criteria andTuanIsNull() {
            addCriterion("tuan is null");
            return (Criteria) this;
        }

        public Criteria andTuanIsNotNull() {
            addCriterion("tuan is not null");
            return (Criteria) this;
        }

        public Criteria andTuanEqualTo(String value) {
            addCriterion("tuan =", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanNotEqualTo(String value) {
            addCriterion("tuan <>", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanGreaterThan(String value) {
            addCriterion("tuan >", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanGreaterThanOrEqualTo(String value) {
            addCriterion("tuan >=", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanLessThan(String value) {
            addCriterion("tuan <", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanLessThanOrEqualTo(String value) {
            addCriterion("tuan <=", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanLike(String value) {
            addCriterion("tuan like", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanNotLike(String value) {
            addCriterion("tuan not like", value, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanIn(List<String> values) {
            addCriterion("tuan in", values, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanNotIn(List<String> values) {
            addCriterion("tuan not in", values, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanBetween(String value1, String value2) {
            addCriterion("tuan between", value1, value2, "tuan");
            return (Criteria) this;
        }

        public Criteria andTuanNotBetween(String value1, String value2) {
            addCriterion("tuan not between", value1, value2, "tuan");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andCityennameIsNull() {
            addCriterion("cityEnName is null");
            return (Criteria) this;
        }

        public Criteria andCityennameIsNotNull() {
            addCriterion("cityEnName is not null");
            return (Criteria) this;
        }

        public Criteria andCityennameEqualTo(String value) {
            addCriterion("cityEnName =", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameNotEqualTo(String value) {
            addCriterion("cityEnName <>", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameGreaterThan(String value) {
            addCriterion("cityEnName >", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameGreaterThanOrEqualTo(String value) {
            addCriterion("cityEnName >=", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameLessThan(String value) {
            addCriterion("cityEnName <", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameLessThanOrEqualTo(String value) {
            addCriterion("cityEnName <=", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameLike(String value) {
            addCriterion("cityEnName like", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameNotLike(String value) {
            addCriterion("cityEnName not like", value, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameIn(List<String> values) {
            addCriterion("cityEnName in", values, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameNotIn(List<String> values) {
            addCriterion("cityEnName not in", values, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameBetween(String value1, String value2) {
            addCriterion("cityEnName between", value1, value2, "cityenname");
            return (Criteria) this;
        }

        public Criteria andCityennameNotBetween(String value1, String value2) {
            addCriterion("cityEnName not between", value1, value2, "cityenname");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(Integer value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(Integer value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(Integer value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(Integer value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(Integer value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<Integer> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<Integer> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(Integer value1, Integer value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andAlldishesIsNull() {
            addCriterion("allDishes is null");
            return (Criteria) this;
        }

        public Criteria andAlldishesIsNotNull() {
            addCriterion("allDishes is not null");
            return (Criteria) this;
        }

        public Criteria andAlldishesEqualTo(String value) {
            addCriterion("allDishes =", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesNotEqualTo(String value) {
            addCriterion("allDishes <>", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesGreaterThan(String value) {
            addCriterion("allDishes >", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesGreaterThanOrEqualTo(String value) {
            addCriterion("allDishes >=", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesLessThan(String value) {
            addCriterion("allDishes <", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesLessThanOrEqualTo(String value) {
            addCriterion("allDishes <=", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesLike(String value) {
            addCriterion("allDishes like", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesNotLike(String value) {
            addCriterion("allDishes not like", value, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesIn(List<String> values) {
            addCriterion("allDishes in", values, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesNotIn(List<String> values) {
            addCriterion("allDishes not in", values, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesBetween(String value1, String value2) {
            addCriterion("allDishes between", value1, value2, "alldishes");
            return (Criteria) this;
        }

        public Criteria andAlldishesNotBetween(String value1, String value2) {
            addCriterion("allDishes not between", value1, value2, "alldishes");
            return (Criteria) this;
        }

        public Criteria andCityglatIsNull() {
            addCriterion("cityGlat is null");
            return (Criteria) this;
        }

        public Criteria andCityglatIsNotNull() {
            addCriterion("cityGlat is not null");
            return (Criteria) this;
        }

        public Criteria andCityglatEqualTo(String value) {
            addCriterion("cityGlat =", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatNotEqualTo(String value) {
            addCriterion("cityGlat <>", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatGreaterThan(String value) {
            addCriterion("cityGlat >", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatGreaterThanOrEqualTo(String value) {
            addCriterion("cityGlat >=", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatLessThan(String value) {
            addCriterion("cityGlat <", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatLessThanOrEqualTo(String value) {
            addCriterion("cityGlat <=", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatLike(String value) {
            addCriterion("cityGlat like", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatNotLike(String value) {
            addCriterion("cityGlat not like", value, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatIn(List<String> values) {
            addCriterion("cityGlat in", values, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatNotIn(List<String> values) {
            addCriterion("cityGlat not in", values, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatBetween(String value1, String value2) {
            addCriterion("cityGlat between", value1, value2, "cityglat");
            return (Criteria) this;
        }

        public Criteria andCityglatNotBetween(String value1, String value2) {
            addCriterion("cityGlat not between", value1, value2, "cityglat");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidIsNull() {
            addCriterion("mainCategoryId is null");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidIsNotNull() {
            addCriterion("mainCategoryId is not null");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidEqualTo(Integer value) {
            addCriterion("mainCategoryId =", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidNotEqualTo(Integer value) {
            addCriterion("mainCategoryId <>", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidGreaterThan(Integer value) {
            addCriterion("mainCategoryId >", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mainCategoryId >=", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidLessThan(Integer value) {
            addCriterion("mainCategoryId <", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidLessThanOrEqualTo(Integer value) {
            addCriterion("mainCategoryId <=", value, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidIn(List<Integer> values) {
            addCriterion("mainCategoryId in", values, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidNotIn(List<Integer> values) {
            addCriterion("mainCategoryId not in", values, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidBetween(Integer value1, Integer value2) {
            addCriterion("mainCategoryId between", value1, value2, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andMaincategoryidNotBetween(Integer value1, Integer value2) {
            addCriterion("mainCategoryId not between", value1, value2, "maincategoryid");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNull() {
            addCriterion("fullName is null");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNotNull() {
            addCriterion("fullName is not null");
            return (Criteria) this;
        }

        public Criteria andFullnameEqualTo(String value) {
            addCriterion("fullName =", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotEqualTo(String value) {
            addCriterion("fullName <>", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThan(String value) {
            addCriterion("fullName >", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("fullName >=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThan(String value) {
            addCriterion("fullName <", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThanOrEqualTo(String value) {
            addCriterion("fullName <=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLike(String value) {
            addCriterion("fullName like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotLike(String value) {
            addCriterion("fullName not like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameIn(List<String> values) {
            addCriterion("fullName in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotIn(List<String> values) {
            addCriterion("fullName not in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameBetween(String value1, String value2) {
            addCriterion("fullName between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotBetween(String value1, String value2) {
            addCriterion("fullName not between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andShoppowerIsNull() {
            addCriterion("shopPower is null");
            return (Criteria) this;
        }

        public Criteria andShoppowerIsNotNull() {
            addCriterion("shopPower is not null");
            return (Criteria) this;
        }

        public Criteria andShoppowerEqualTo(Integer value) {
            addCriterion("shopPower =", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerNotEqualTo(Integer value) {
            addCriterion("shopPower <>", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerGreaterThan(Integer value) {
            addCriterion("shopPower >", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopPower >=", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerLessThan(Integer value) {
            addCriterion("shopPower <", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerLessThanOrEqualTo(Integer value) {
            addCriterion("shopPower <=", value, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerIn(List<Integer> values) {
            addCriterion("shopPower in", values, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerNotIn(List<Integer> values) {
            addCriterion("shopPower not in", values, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerBetween(Integer value1, Integer value2) {
            addCriterion("shopPower between", value1, value2, "shoppower");
            return (Criteria) this;
        }

        public Criteria andShoppowerNotBetween(Integer value1, Integer value2) {
            addCriterion("shopPower not between", value1, value2, "shoppower");
            return (Criteria) this;
        }

        public Criteria andEnvIsNull() {
            addCriterion("env is null");
            return (Criteria) this;
        }

        public Criteria andEnvIsNotNull() {
            addCriterion("env is not null");
            return (Criteria) this;
        }

        public Criteria andEnvEqualTo(Double value) {
            addCriterion("env =", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotEqualTo(Double value) {
            addCriterion("env <>", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThan(Double value) {
            addCriterion("env >", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvGreaterThanOrEqualTo(Double value) {
            addCriterion("env >=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThan(Double value) {
            addCriterion("env <", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvLessThanOrEqualTo(Double value) {
            addCriterion("env <=", value, "env");
            return (Criteria) this;
        }

        public Criteria andEnvIn(List<Double> values) {
            addCriterion("env in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotIn(List<Double> values) {
            addCriterion("env not in", values, "env");
            return (Criteria) this;
        }

        public Criteria andEnvBetween(Double value1, Double value2) {
            addCriterion("env between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andEnvNotBetween(Double value1, Double value2) {
            addCriterion("env not between", value1, value2, "env");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameIsNull() {
            addCriterion("mainCategoryName is null");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameIsNotNull() {
            addCriterion("mainCategoryName is not null");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameEqualTo(String value) {
            addCriterion("mainCategoryName =", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameNotEqualTo(String value) {
            addCriterion("mainCategoryName <>", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameGreaterThan(String value) {
            addCriterion("mainCategoryName >", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameGreaterThanOrEqualTo(String value) {
            addCriterion("mainCategoryName >=", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameLessThan(String value) {
            addCriterion("mainCategoryName <", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameLessThanOrEqualTo(String value) {
            addCriterion("mainCategoryName <=", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameLike(String value) {
            addCriterion("mainCategoryName like", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameNotLike(String value) {
            addCriterion("mainCategoryName not like", value, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameIn(List<String> values) {
            addCriterion("mainCategoryName in", values, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameNotIn(List<String> values) {
            addCriterion("mainCategoryName not in", values, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameBetween(String value1, String value2) {
            addCriterion("mainCategoryName between", value1, value2, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andMaincategorynameNotBetween(String value1, String value2) {
            addCriterion("mainCategoryName not between", value1, value2, "maincategoryname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameIsNull() {
            addCriterion("categoryURLName is null");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameIsNotNull() {
            addCriterion("categoryURLName is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameEqualTo(String value) {
            addCriterion("categoryURLName =", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameNotEqualTo(String value) {
            addCriterion("categoryURLName <>", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameGreaterThan(String value) {
            addCriterion("categoryURLName >", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameGreaterThanOrEqualTo(String value) {
            addCriterion("categoryURLName >=", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameLessThan(String value) {
            addCriterion("categoryURLName <", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameLessThanOrEqualTo(String value) {
            addCriterion("categoryURLName <=", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameLike(String value) {
            addCriterion("categoryURLName like", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameNotLike(String value) {
            addCriterion("categoryURLName not like", value, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameIn(List<String> values) {
            addCriterion("categoryURLName in", values, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameNotIn(List<String> values) {
            addCriterion("categoryURLName not in", values, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameBetween(String value1, String value2) {
            addCriterion("categoryURLName between", value1, value2, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andCategoryurlnameNotBetween(String value1, String value2) {
            addCriterion("categoryURLName not between", value1, value2, "categoryurlname");
            return (Criteria) this;
        }

        public Criteria andServiceIsNull() {
            addCriterion("service is null");
            return (Criteria) this;
        }

        public Criteria andServiceIsNotNull() {
            addCriterion("service is not null");
            return (Criteria) this;
        }

        public Criteria andServiceEqualTo(Double value) {
            addCriterion("service =", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceNotEqualTo(Double value) {
            addCriterion("service <>", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceGreaterThan(Double value) {
            addCriterion("service >", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceGreaterThanOrEqualTo(Double value) {
            addCriterion("service >=", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceLessThan(Double value) {
            addCriterion("service <", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceLessThanOrEqualTo(Double value) {
            addCriterion("service <=", value, "service");
            return (Criteria) this;
        }

        public Criteria andServiceIn(List<Double> values) {
            addCriterion("service in", values, "service");
            return (Criteria) this;
        }

        public Criteria andServiceNotIn(List<Double> values) {
            addCriterion("service not in", values, "service");
            return (Criteria) this;
        }

        public Criteria andServiceBetween(Double value1, Double value2) {
            addCriterion("service between", value1, value2, "service");
            return (Criteria) this;
        }

        public Criteria andServiceNotBetween(Double value1, Double value2) {
            addCriterion("service not between", value1, value2, "service");
            return (Criteria) this;
        }

        public Criteria andBranchqtyIsNull() {
            addCriterion("branchQty is null");
            return (Criteria) this;
        }

        public Criteria andBranchqtyIsNotNull() {
            addCriterion("branchQty is not null");
            return (Criteria) this;
        }

        public Criteria andBranchqtyEqualTo(Integer value) {
            addCriterion("branchQty =", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyNotEqualTo(Integer value) {
            addCriterion("branchQty <>", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyGreaterThan(Integer value) {
            addCriterion("branchQty >", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("branchQty >=", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyLessThan(Integer value) {
            addCriterion("branchQty <", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyLessThanOrEqualTo(Integer value) {
            addCriterion("branchQty <=", value, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyIn(List<Integer> values) {
            addCriterion("branchQty in", values, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyNotIn(List<Integer> values) {
            addCriterion("branchQty not in", values, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyBetween(Integer value1, Integer value2) {
            addCriterion("branchQty between", value1, value2, "branchqty");
            return (Criteria) this;
        }

        public Criteria andBranchqtyNotBetween(Integer value1, Integer value2) {
            addCriterion("branchQty not between", value1, value2, "branchqty");
            return (Criteria) this;
        }

        public Criteria andShoptypeIsNull() {
            addCriterion("shopType is null");
            return (Criteria) this;
        }

        public Criteria andShoptypeIsNotNull() {
            addCriterion("shopType is not null");
            return (Criteria) this;
        }

        public Criteria andShoptypeEqualTo(Integer value) {
            addCriterion("shopType =", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeNotEqualTo(Integer value) {
            addCriterion("shopType <>", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeGreaterThan(Integer value) {
            addCriterion("shopType >", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopType >=", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeLessThan(Integer value) {
            addCriterion("shopType <", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeLessThanOrEqualTo(Integer value) {
            addCriterion("shopType <=", value, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeIn(List<Integer> values) {
            addCriterion("shopType in", values, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeNotIn(List<Integer> values) {
            addCriterion("shopType not in", values, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeBetween(Integer value1, Integer value2) {
            addCriterion("shopType between", value1, value2, "shoptype");
            return (Criteria) this;
        }

        public Criteria andShoptypeNotBetween(Integer value1, Integer value2) {
            addCriterion("shopType not between", value1, value2, "shoptype");
            return (Criteria) this;
        }

        public Criteria andHuiIsNull() {
            addCriterion("hui is null");
            return (Criteria) this;
        }

        public Criteria andHuiIsNotNull() {
            addCriterion("hui is not null");
            return (Criteria) this;
        }

        public Criteria andHuiEqualTo(String value) {
            addCriterion("hui =", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiNotEqualTo(String value) {
            addCriterion("hui <>", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiGreaterThan(String value) {
            addCriterion("hui >", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiGreaterThanOrEqualTo(String value) {
            addCriterion("hui >=", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiLessThan(String value) {
            addCriterion("hui <", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiLessThanOrEqualTo(String value) {
            addCriterion("hui <=", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiLike(String value) {
            addCriterion("hui like", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiNotLike(String value) {
            addCriterion("hui not like", value, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiIn(List<String> values) {
            addCriterion("hui in", values, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiNotIn(List<String> values) {
            addCriterion("hui not in", values, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiBetween(String value1, String value2) {
            addCriterion("hui between", value1, value2, "hui");
            return (Criteria) this;
        }

        public Criteria andHuiNotBetween(String value1, String value2) {
            addCriterion("hui not between", value1, value2, "hui");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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