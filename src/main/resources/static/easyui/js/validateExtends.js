/**
 * 扩展easyui表单的验证
 */

$.extend($.fn.validatebox.defaults.rules, {
    // 人名验证
    name: {
        validator: function (value) {
            var reg = /^[\u4e00-\u9fa50-9]{2,12}$/;
            return reg.test(value);
        },
        message: '姓名2~12位, 只能包含汉字和数字'
    },
    // 班级名称验证
    clazzName: {
        validator: function (value) {
            var reg = /^[\u4e00-\u9fa5\d]{2,16}$/;
            return reg.test(value);
        },
        message: '班级名称2~16位, 只能包含汉字和数字'
    },
    // 竞赛名称验证
    competitionName: {
        validator: function (value) {
            var reg = /^[\u4e00-\u9fa5a-zA-Z0-9]{2,30}$/;
            return reg.test(value);
        },
        message: '竞赛名称2~30位, 只能包含汉字、字母和数字'
    },
    // 邮箱地址验证
    email: {
        validator: function (value) {
            var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
            return reg.test(value);
        },
        message: '邮箱地址格式错误'
    },
    // 手机号码验证
    number: {
        validator: function (value) {
            var reg = /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1589]))\d{8}$/;
            return reg.test(value);
        },
        message: '手机号码格式错误'
    },
    // 账号验证
    account: {
        validator: function (value) {
            var reg = /^[a-zA-Z0-9]{6,16}$/;
            return reg.test(value);
        },
        message: '账号6~16位, 只能包含字母、数字'
    },
    // 密码验证
    password: {
        validator: function (value) {
            var reg = /^[a-zA-Z0-9_]{6,16}$/;
            return reg.test(value);
        },
        message: '密码6~16位, 只能包含字母、数字和_'
    },
    // 日期验证
    date: {
        validator: function (value) {
            var reg = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$/;
            return reg.test(value);
        },
        message: '日期格式为YYYY-MM-DD'
    }
})
