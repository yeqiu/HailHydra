package com.yeqiu.android_tools.net.bean.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @project：android_tools
 * @author：小卷子
 * @date 2018/4/16 下午3:24
 * @describe：
 * @fix：
 */
public class TestData implements Serializable{


    /**
     * code : 0
     * message :
     * data : {"audit_amount":10000,"zm_certify":false,"alipay_account":false,"has_credit":0,
     * "has_pay_pass":false,"has_paying_order":0,"has_paying_order_txt":"您有X笔打款中的订单，暂时不能提现",
     * "has_places_num":998,"has_places_text":"今日借款名额已用完，请客官明日早点来","has_seal":false,
     * "has_face":false,"has_bind_bankcard":false,"zm_notify_url":"",
     * "has_seal_txt":"您的借款技能已被封印，请联系客服解除封印！","credit_config":[{"start":500,"end":10000,
     * "step":100,"type":[{"value":"week","first_deadline":"","period":[{"name":"1周","value":"1",
     * "interest":"8.0","is_default":0},{"name":"3周","value":"3","interest":"8.0",
     * "is_default":"1"}]}]}],"purpose_title":"请按实际用途选择，熊猫分期保留核实的权利","purpose_list":[{"id":1,
     * "name":"网上购物","cancel_at":"0000-00-00 00:00:00","created_at":"2017-12-02 11:22:35",
     * "updated_at":"2017-12-02 11:23:24"},{"id":2,"name":"实体店购物","cancel_at":null,
     * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:24:23"},{"id":3,
     * "name":"教育培训","cancel_at":null,"created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02
     * 11:25:47"},{"id":4,"name":"出国留学","cancel_at":null,"created_at":"2017-12-02 11:22:35",
     * "updated_at":"2017-12-02 11:25:48"},{"id":5,"name":"婚庆装修","cancel_at":null,
     * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:25:49"},{"id":6,
     * "name":"餐饮娱乐","cancel_at":null,"created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02
     * 11:25:51"},{"id":7,"name":"医疗美容","cancel_at":null,"created_at":"2017-12-02 11:22:35",
     * "updated_at":"2017-12-02 11:25:54"},{"id":8,"name":"旅游出行","cancel_at":null,
     * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:26:52"},{"id":9,
     * "name":"生活开销","cancel_at":null,"created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02
     * 11:26:53"},{"id":10,"name":"其他用途","cancel_at":null,"created_at":"2017-12-02 11:22:35",
     * "updated_at":"2017-12-02 11:26:54"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * audit_amount : 10000
         * zm_certify : false
         * alipay_account : false
         * has_credit : 0
         * has_pay_pass : false
         * has_paying_order : 0
         * has_paying_order_txt : 您有X笔打款中的订单，暂时不能提现
         * has_places_num : 998
         * has_places_text : 今日借款名额已用完，请客官明日早点来
         * has_seal : false
         * has_face : false
         * has_bind_bankcard : false
         * zm_notify_url :
         * has_seal_txt : 您的借款技能已被封印，请联系客服解除封印！
         * credit_config : [{"start":500,"end":10000,"step":100,"type":[{"value":"week",
         * "first_deadline":"","period":[{"name":"1周","value":"1","interest":"8.0",
         * "is_default":0},{"name":"3周","value":"3","interest":"8.0","is_default":"1"}]}]}]
         * purpose_title : 请按实际用途选择，熊猫分期保留核实的权利
         * purpose_list : [{"id":1,"name":"网上购物","cancel_at":"0000-00-00 00:00:00",
         * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:23:24"},{"id":2,
         * "name":"实体店购物","cancel_at":null,"created_at":"2017-12-02 11:22:35",
         * "updated_at":"2017-12-02 11:24:23"},{"id":3,"name":"教育培训","cancel_at":null,
         * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:25:47"},{"id":4,
         * "name":"出国留学","cancel_at":null,"created_at":"2017-12-02 11:22:35",
         * "updated_at":"2017-12-02 11:25:48"},{"id":5,"name":"婚庆装修","cancel_at":null,
         * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:25:49"},{"id":6,
         * "name":"餐饮娱乐","cancel_at":null,"created_at":"2017-12-02 11:22:35",
         * "updated_at":"2017-12-02 11:25:51"},{"id":7,"name":"医疗美容","cancel_at":null,
         * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:25:54"},{"id":8,
         * "name":"旅游出行","cancel_at":null,"created_at":"2017-12-02 11:22:35",
         * "updated_at":"2017-12-02 11:26:52"},{"id":9,"name":"生活开销","cancel_at":null,
         * "created_at":"2017-12-02 11:22:35","updated_at":"2017-12-02 11:26:53"},{"id":10,
         * "name":"其他用途","cancel_at":null,"created_at":"2017-12-02 11:22:35",
         * "updated_at":"2017-12-02 11:26:54"}]
         */

        private int audit_amount;
        private boolean zm_certify;
        private boolean alipay_account;
        private int has_credit;
        private boolean has_pay_pass;
        private int has_paying_order;
        private String has_paying_order_txt;
        private int has_places_num;
        private String has_places_text;
        private boolean has_seal;
        private boolean has_face;
        private boolean has_bind_bankcard;
        private String zm_notify_url;
        private String has_seal_txt;
        private String purpose_title;
        private List<CreditConfigBean> credit_config;
        private List<PurposeListBean> purpose_list;

        public int getAudit_amount() {
            return audit_amount;
        }

        public void setAudit_amount(int audit_amount) {
            this.audit_amount = audit_amount;
        }

        public boolean isZm_certify() {
            return zm_certify;
        }

        public void setZm_certify(boolean zm_certify) {
            this.zm_certify = zm_certify;
        }

        public boolean isAlipay_account() {
            return alipay_account;
        }

        public void setAlipay_account(boolean alipay_account) {
            this.alipay_account = alipay_account;
        }

        public int getHas_credit() {
            return has_credit;
        }

        public void setHas_credit(int has_credit) {
            this.has_credit = has_credit;
        }

        public boolean isHas_pay_pass() {
            return has_pay_pass;
        }

        public void setHas_pay_pass(boolean has_pay_pass) {
            this.has_pay_pass = has_pay_pass;
        }

        public int getHas_paying_order() {
            return has_paying_order;
        }

        public void setHas_paying_order(int has_paying_order) {
            this.has_paying_order = has_paying_order;
        }

        public String getHas_paying_order_txt() {
            return has_paying_order_txt;
        }

        public void setHas_paying_order_txt(String has_paying_order_txt) {
            this.has_paying_order_txt = has_paying_order_txt;
        }

        public int getHas_places_num() {
            return has_places_num;
        }

        public void setHas_places_num(int has_places_num) {
            this.has_places_num = has_places_num;
        }

        public String getHas_places_text() {
            return has_places_text;
        }

        public void setHas_places_text(String has_places_text) {
            this.has_places_text = has_places_text;
        }

        public boolean isHas_seal() {
            return has_seal;
        }

        public void setHas_seal(boolean has_seal) {
            this.has_seal = has_seal;
        }

        public boolean isHas_face() {
            return has_face;
        }

        public void setHas_face(boolean has_face) {
            this.has_face = has_face;
        }

        public boolean isHas_bind_bankcard() {
            return has_bind_bankcard;
        }

        public void setHas_bind_bankcard(boolean has_bind_bankcard) {
            this.has_bind_bankcard = has_bind_bankcard;
        }

        public String getZm_notify_url() {
            return zm_notify_url;
        }

        public void setZm_notify_url(String zm_notify_url) {
            this.zm_notify_url = zm_notify_url;
        }

        public String getHas_seal_txt() {
            return has_seal_txt;
        }

        public void setHas_seal_txt(String has_seal_txt) {
            this.has_seal_txt = has_seal_txt;
        }

        public String getPurpose_title() {
            return purpose_title;
        }

        public void setPurpose_title(String purpose_title) {
            this.purpose_title = purpose_title;
        }

        public List<CreditConfigBean> getCredit_config() {
            return credit_config;
        }

        public void setCredit_config(List<CreditConfigBean> credit_config) {
            this.credit_config = credit_config;
        }

        public List<PurposeListBean> getPurpose_list() {
            return purpose_list;
        }

        public void setPurpose_list(List<PurposeListBean> purpose_list) {
            this.purpose_list = purpose_list;
        }

        public static class CreditConfigBean {
            /**
             * start : 500
             * end : 10000
             * step : 100
             * type : [{"value":"week","first_deadline":"","period":[{"name":"1周","value":"1",
             * "interest":"8.0","is_default":0},{"name":"3周","value":"3","interest":"8.0",
             * "is_default":"1"}]}]
             */

            private int start;
            private int end;
            private int step;
            private List<TypeBean> type;

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getStep() {
                return step;
            }

            public void setStep(int step) {
                this.step = step;
            }

            public List<TypeBean> getType() {
                return type;
            }

            public void setType(List<TypeBean> type) {
                this.type = type;
            }

            public static class TypeBean {
                /**
                 * value : week
                 * first_deadline :
                 * period : [{"name":"1周","value":"1","interest":"8.0","is_default":0},
                 * {"name":"3周","value":"3","interest":"8.0","is_default":"1"}]
                 */

                private String value;
                private String first_deadline;
                private List<PeriodBean> period;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getFirst_deadline() {
                    return first_deadline;
                }

                public void setFirst_deadline(String first_deadline) {
                    this.first_deadline = first_deadline;
                }

                public List<PeriodBean> getPeriod() {
                    return period;
                }

                public void setPeriod(List<PeriodBean> period) {
                    this.period = period;
                }

                public static class PeriodBean {
                    /**
                     * name : 1周
                     * value : 1
                     * interest : 8.0
                     * is_default : 0
                     */

                    private String name;
                    private String value;
                    private String interest;
                    private int is_default;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }

                    public String getInterest() {
                        return interest;
                    }

                    public void setInterest(String interest) {
                        this.interest = interest;
                    }

                    public int getIs_default() {
                        return is_default;
                    }

                    public void setIs_default(int is_default) {
                        this.is_default = is_default;
                    }
                }
            }
        }

        public static class PurposeListBean {
            /**
             * id : 1
             * name : 网上购物
             * cancel_at : 0000-00-00 00:00:00
             * created_at : 2017-12-02 11:22:35
             * updated_at : 2017-12-02 11:23:24
             */

            private int id;
            private String name;
            private String cancel_at;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCancel_at() {
                return cancel_at;
            }

            public void setCancel_at(String cancel_at) {
                this.cancel_at = cancel_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }
    }
}
