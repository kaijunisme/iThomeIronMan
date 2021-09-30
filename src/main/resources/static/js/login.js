$(() => {
    $("#loginForm").on("submit", function() {
        // 檢查帳號
        var account = $("#account").val();
        if(account == "") {
            alert("請輸入帳號");
            $("#account").focus();
            return false;
        }

        // 檢查密碼
        var password = $("#password").val();
        if(password == "") {
            alert("請輸入密碼");
            $("#password").focus();
            return false;
        }
    });
})