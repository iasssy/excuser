
$(document).ready(function() {
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