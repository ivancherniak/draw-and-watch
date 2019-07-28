$(function () {
    $("form[name='regForm']").validate({
        rules: {
            name: "required",
            login: "required",
            password: "required",
            repeatPassword: {
                required: true,
                equalTo: "#password"
            }
        },
        messages: {
            name: "Please enter your name",
            login: "Please enter your login",
            password: "Please enter a password",
            repeatPassword: {
                required: "Please repeat a password",
                equalTo: "Passwords should be equal"
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});