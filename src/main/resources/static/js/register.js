$(() => {
    $("#registerForm").on("submit", function() {

        // 檢查帳號
        var account = $("#account").val();
        if(account == "") {
            alert("請輸入帳號");
            $("#account").focus();
            return false;
        }

        if(!isEmail(account)) {

            return false;
        }

        // 檢查密碼
        var password = $("#password").val();
        if(password == "") {
            alert("請輸入密碼");
            $("#password").focus();
            return false;
        }

        if(!isPasswordFormat(password)) {

            return false;
        }

        // 檢查確認密碼
        var checkPassword = $("#checkPassword").val();
        if(checkPassword == "") {
            alert("請輸入確認密碼");
            $("#checkPassword").focus();
            return false;
        }

        if(password != checkPassword) {
            alert("確認密碼與密碼不相符");
            $("#checkPassword").focus();
            return false;
        }

        $.ajax({
            url : "/register",
            type : "POST",
            data : {
                "account" : account,
                "password" : password,
                "checkPassword" : checkPassword
            },
            dataType : "TEXT",
            success : function(res) {
                if(res != "") {
					alert(res);
				}
				else {
					alert("註冊成功");
					$(location).attr("href", "/login")
				}
            },
            error : function(xhr) {
				alert(xhr.responseText);
            }
        });
		
        return false;
    });
})