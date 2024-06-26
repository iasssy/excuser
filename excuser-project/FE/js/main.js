
const HOST = "http://localhost:8080";
let excuse_id;
const session_user_id = 1; 

$(document).ready(function() {
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
            let category_chosen=response.category;
            console.log("excuse_id: " + excuse_id);
            console.log("category: " + category_chosen);
            // Show the spinner
            $('#gif-container').html('<div class="spinner-border" role="status"><span class="sr-only"></span></div>');

            // a random GIF from yesno API
            $.ajax({
              url: 'https://yesno.wtf/api',
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
      if (!excuse_id) {
          alert('Please generate an excuse first!');
          return;
      }
      $.ajax({
          method: 'post',
          url: `${HOST}/history/save`,
          data: {
              session_user_id: BigInt(session_user_id),
              excuse_id: excuse_id
          },
          success: function(response) {
              alert('Excuse saved successfully with ID: ' + response);
          },
          error: function(xhr, status, error) {
              alert('Error saving excuse: ' + xhr.responseText);
          }
      });
  });

});
