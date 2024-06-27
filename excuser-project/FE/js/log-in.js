
$(document).ready(function() {
    $('#log-in-form').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        let loginData = {
            user_email: $('#email-input').val(),
            user_password: $('#password-input').val()
        };

        $.ajax({
            method: 'get',
            url: `${HOST}/user/login`,
            data: loginData,
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
                if (xhr.status === 401) {
                    $('#modal-popup .modal-body p').text("Password or email are incorrect");
                    $('#modal-popup').modal('show');
                } else {
                    let errorMessage = xhr.responseText || 'An error occurred during login.';
                    $('#error-message').text(errorMessage).show();
                }
            }
        });
    });
});
