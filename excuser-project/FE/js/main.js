
const HOST = "http://localhost:8080";
let excuse_id;
let excuse_content;
let category_name;
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
            excuse_content=response.excuse;
            category_name=response.category;
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
              session_user_id: session_user_id,
              excuse_id: excuse_id,
              excuse_content: excuse_content,
              category_name: category_name
          },
          success: function(response) {
              alert('Excuse saved successfully with ID: ' + response);
          },
          error: function(xhr, status, error) {
              alert('Error saving excuse: ' + xhr.responseText);
          }
      });
  });


  function loadHistory() {
    $.ajax({
        method: 'get',
        url: `${HOST}/history/`,
        data: {
            user_id: session_user_id
        },
        success: function(response) {
            let historyList = $('#history-list');
            historyList.empty();
            response.forEach(item => {
                let listItem = `<li class="list-group-item d-flex">
                                <div class="d-flex align-items-start">
                                  <h5><span class="badge text-bg-warning rounded-pill">${item.category_name}</span></h5>
                                  <div class="excuse-content text-start">${item.excuse_content}</div>
                                </div>
                                <div class="delete-icon ms-auto px-4 py-2" id="delete-${item.history_id}">
                                  <i class="bi bi-trash3-fill"></i>
                                </div>
                                </li>`;
                historyList.append(listItem);
            });

            // click to delete history_id
            $('.delete-icon').on('click', function() {
              let historyIdAttr = $(this).attr('id');
              // extract everything after "history-"
              let historyId = historyIdAttr.substring(historyIdAttr.indexOf('-') + 1);               
              deleteHistory(historyId);
          });
        },
        error: function(xhr, status, error) {
            alert('Error loading history: ' + xhr.responseText);
        }
    });    
}

// Function to delete history entry
function deleteHistory(historyId) {
  $.ajax({
      method: 'delete',
      url: `${HOST}/history/delete/`+historyId,
      success: function(response) {
          alert('History entry deleted successfully');
          // Reload history after deletion
          loadHistory();
      },
      error: function(xhr, status, error) {
          alert('Error deleting history: ' + xhr.responseText);
      }
  });
  }
  loadHistory();



  function loadComments() {
    $.ajax({
        method: 'get',
        url: `${HOST}/comments/`,
        data: {
            user_id: session_user_id
        },
        success: function(response) {
            let commentsList = $('#comments-list');
            commentsList.empty();
            response.forEach(item => {
                let listItem = `<li class="list-group-item text-start">
                                  <div class="row">
                                      <div class="col-sm-2 col-12">
                                          <h5><span class="badge text-bg-warning rounded-pill">${item.category_name}</span></h5>
                                      </div>
                                      <div class="col-sm-8 col-12">
                                          <h5>Excuse:</h5>
                                          <small>${item.excuse_content}</small>
                                          <h2 class="mt-2">Comment:</h2>
                                          <div class="bg-info">${item.comment_content}</div>
                                      </div>
                                      <div class="col-sm-2 col-12 text-center mt-2">
                                          <div class="delete-icon" id="delete-${item.comment_id}">
                                              <i class="bi bi-trash3-fill"></i>
                                          </div>
                                      </div>
                                  </div>
                              </li>`;
                commentsList.append(listItem);
            });
          },
          error: function(xhr, status, error) {
              alert('Error loading history: ' + xhr.responseText);
          }
    });    
  }
  loadComments();

});