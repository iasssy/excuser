
const HOST = "http://localhost:8080";
let excuse_id;
let excuse_content;
let category_name;
let session_user_id = sessionStorage.getItem('session_user_id');

console.log("session_user_id: " + session_user_id);
$(document).ready(function() {
    updateLoginState(); // checking if there is session_user_id

    $('#generate-excuse').on('click', function(event) {
        event.preventDefault();
        let category = $('#excuse-select').val();
        let apiUrl = category ? `${HOST}/excuse/category/${category}` : `${HOST}/excuse/`;
        $.ajax({
          method: "get",
          url: apiUrl
        })
          .done((response) => {
            $('#excuse-output').val(response.excuse);
            excuse_id=response.id;
            excuse_content=response.excuse;
            category_name=response.category;
            let category_chosen=response.category;
            console.log("excuse_id: " + excuse_id);
            console.log("category: " + category_chosen);
            // Show the spinner
            $('#gif-container').html('<div class="spinner-border" role="status"><span class="sr-only"></span></div>');

            // a random GIF from yesno API
            $.ajax({
              url: `${HOST}/yesno/`,
              method: 'get',
              dataType: 'json'
            })
            .done((data) => {
                $('#gif-container').html(`<img src="${data.image}" class="img-fluid" alt="Excuse GIF" />`);
            })
            .fail((xhrObj) => alert(xhrObj));
            })
            .fail((xhrObj) => alert(xhrObj));
    });


    $('#btn-save').on('click', function() {
      if (session_user_id == null){
          $('#modal-popup .modal-body p').text("Please log in!");
          $('#modal-popup').modal('show');
        
      } else {
        if (!excuse_id) {
            //alert('Please generate an excuse first!');
            $('#modal-popup .modal-body p').text("Please generate an excuse at first!");
            $('#modal-popup').modal('show');
            return;
        }
      $.ajax({
          method: 'post',
          url: `${HOST}/history/save`,
          data: {
              session_user_id: session_user_id,
              excuse_id: excuse_id,
              excuse_content: excuse_content,
              category_name: category_name
          },
          success: function(response) {            
                $('#modal-popup .modal-body p').text("Excuse saved successfully.");
                $('#modal-popup').modal('show');
                console.log('Excuse saved successfully with ID: ' + response);
          },
          error: function(xhr, status, error) {
              alert('Error saving excuse: ' + xhr.responseText);
          }
      });
    }
  });

   // Function to update button "Log in" or "Quit" based on session_user_id
   function updateLoginState() {
    session_user_id = sessionStorage.getItem('session_user_id');
    let buttonGroup = $('#button-group');
    buttonGroup.empty(); // Clear existing buttons
     //  append other buttons
     buttonGroup.append(`
        <a id="btn-save" href="#" class="btn btn-primary">SAVE</a>
        <a id="btn-history" href="#" class="btn btn-primary">HISTORY</a>
        <a id="btn-comments" href="#" class="btn btn-primary">COMMENTS</a>`
      );

    if (session_user_id == null) {
        // append Log in button
        buttonGroup.append(`<a id="btn-login" href="log-in.html" class="btn btn-primary">LOG IN</a>`);
    } else {
        buttonGroup.append(`<a id="btn-quit" href="#" class="btn btn-primary">QUIT</a>`);
        // handle quit button click
       
    }
}

 $('#btn-quit').on('click', function(event) {
    event.preventDefault();
    // Clear session_user_id
    sessionStorage.removeItem('session_user_id');
    updateLoginState(); // Update UI
    window.location.href = 'index.html'; // Redirect to index or desired page
  });

  
 $('#btn-history').on('click', function(event) {      
  event.preventDefault();
  if (session_user_id == null) {
    $('#modal-popup .modal-body p').text("Please log in!");
    $('#modal-popup').modal('show');
  } else {
    window.location.href = 'history.html';
  }
 }); 

 $('#btn-comments').on('click', function(event) {      
  event.preventDefault();
  if (session_user_id == null) {
    $('#modal-popup .modal-body p').text("Please log in!");
    $('#modal-popup').modal('show');
  } else {
    window.location.href = 'comment.html';
  }
 });

});