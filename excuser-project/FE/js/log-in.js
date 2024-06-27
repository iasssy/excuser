
$(document).ready(function() {
    $('#log-in-form').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission
        
       
        let user_email = $('#email-input').val();
        let user_password = $('#password-input').val();

        // Validate email format
        if (!validateEmail(user_email)) {
            $('#modal-popup .modal-body p').text("Invalid email format.");
            $('#modal-popup').modal('show');
            return;
        }

        let loginData = {
            user_email: user_email,
            user_password: user_password
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

    
    // Function to validate email format
    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
});
