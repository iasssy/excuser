
const HOST = "http://localhost:8080";

$(document).ready(function() {
    $('#generate-excuse').on('click', function(event) {
        event.preventDefault();
        $.ajax({
          method: "get",
          url: `${HOST}/excuse/`
        })
          .done((response) => {
            $('#excuse-output').val(response.excuse);
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
});
