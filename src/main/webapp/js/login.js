$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

function loginRegisterSwitch(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}

$(document).ready(function() {
    $("button.register").click(function() {
        var email = $("form.register-form input.email").val();
        var firstName = $("form.register-form input.firstName").val();
        var lastName = $("form.register-form input.lastName").val();
        var password = $("form.register-form input.password").val();
        if (email == '' || firstName == '' || lastName == '' || password == '') {
            alert("Please fill all fields...!!!!!!");
        } else if ((password.length) < 8) {
            alert("Password should atleast 8 character in length...!!!!!!");
        } else {
            var userRegistration = {
                email: email,
                firstName: firstName,
                lastName: lastName,
                password: password
            };
            $.post("RegistrationServlet", userRegistration, function(data) {
                if (data == 'You have Successfully Registered.....') {
                    $("form")[0].reset();
                    loginRegisterSwitch();
                }
                alert(data);
            });
        }
    });
});

$("button.login").click(function() {
    var email = $("form.login-form input.email").val();
    var password = $("form.login-form input.password").val();
    if (email == '' || password == '') {
        alert("Please fill all fields...!!!!!!");
    } else {
        var userLogin = {
            email: email,
            password: password
        };
        $.post("LoginServlet", userLogin, function(data) {

            var customUrl = ""
            var urlContent = window.location.href.split('/')
            for (let i = 0; i < urlContent.length-1; i++) {
                customUrl+= urlContent[i]+'/'
            }
            customUrl+=data.destinationURL
            window.location = customUrl
        });
    }
});

