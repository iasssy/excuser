$(document).ready(function() {
    $('#sign-up-form').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        user_name = $('#username-input').val();
        user_email = $('#email-input').val();
        user_password = $('#password-input').val();

        $.ajax({
            method: 'post',
            url: `${HOST}/user/save`,
            data: JSON.stringify({
                'user_name': user_name,
                'user_email': user_email,
                'user_password': user_password
            }),
            contentType: "application/json",
            success: function(response) {
                // Handle successful login
                //alert('Login successful!');
                session_user_id = response.user_id;
                sessionStorage.setItem('session_user_id', session_user_id);
                console.log("session_user_id: " + session_user_id);
                window.location.href = 'index.html';
            },
            error: function(xhr, status, error) {
                // Handle error response
                let errorMessage = xhr.responseText || 'An error occurred during login.';
                console.log(errorMessage);
            }
        });
    });
});
