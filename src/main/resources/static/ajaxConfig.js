$.ajaxSetup({
    //timeout: 5000,
    cache: false,
    //contentType: "application/json",
    processData: true,
    error: function(jqXHR) {
        if (jqXHR.status === 409) {
            // 处理字段冲突异常
            $.messager.alert("消息提醒", jqXHR.responseText, "warning");
        } else {
            // 处理其他错误
            alert('服务器发生错误: ' + jqXHR.statusText);
        }
    }
});