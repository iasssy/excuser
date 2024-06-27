let commentsList = $('#comments-list');

$(document).ready(function() {
    function loadComments() {        
     if (session_user_id == null) {
        commentsList.append('<h3 class="mt-5">Please log in</h3>');
        
     } else {
      $.ajax({
        method: 'get',
        url: `${HOST}/comments/`,
        success: function(response) {
          commentsList.empty();
  
          response.forEach(commentResponse => {
            $.ajax({
              method: 'get',
              url: `${HOST}/history/id/` + commentResponse.history_id,
              success: function(historyResponse) {
                let listItem = `<li class="list-group-item text-start">
                  <div class="row">
                    <div class="col-sm-2 col-12 d-flex align-items-center">
                      <h5><span class="badge text-bg-warning rounded-pill">${historyResponse.category_name}</span></h5>
                    </div>
                    <div class="col-sm-8 col-12">
                      <h6>Excuse:</h6>
                      <small>${historyResponse.excuse_content}</small>
                      <h5 class="mt-3">Comment:</h5>
                      <div class="comment-background p-2">${commentResponse.comment_content}</div>
                    </div>
                    <div class="col-sm-1 col-6 text-center mt-2">
                      <div class="update-icon" id="update-${commentResponse.comment_id}">
                        <i class="bi bi-pencil-fill"></i>
                      </div>
                    </div>
                    <div class="col-sm-1 col-6 text-center mt-2">
                      <div class="delete-icon" id="delete-${commentResponse.comment_id}">
                        <i class="bi bi-trash3-fill"></i>
                      </div>
                    </div>
                  </div>
                </li>`;
                commentsList.append(listItem);
              },
              error: function(xhr, status, error) {
                console.log('Error retrieving history: ' + xhr.responseText);
              }
            });
          });
  
          // Bind event listeners after all comments are appended
          bindEventListeners();
        },
        error: function(xhr, status, error) {
          console.log('Error loading comments: ' + xhr.responseText);
          $('#modal-popup .modal-body p').text("Error loading comments");
          $('#modal-popup').modal('show');
        }
      });
    }
    }
  
    // Function to bind event listeners for dynamically loaded elements
    function bindEventListeners() {
      // Unbind previous event handlers to prevent multiple executions
      $('#comments-list').off('click', '.delete-icon').on('click', '.delete-icon', function() {
        let commentIdAttr = $(this).attr('id');
        let commentId = commentIdAttr.substring(commentIdAttr.indexOf('-') + 1);
        deleteComment(commentId);
      });
  
      // Unbind previous event handlers to prevent multiple executions
      $('#comments-list').off('click', '.update-icon').on('click', '.update-icon', function() {
        let commentIdAttr = $(this).attr('id');
        let commentId = commentIdAttr.substring(commentIdAttr.indexOf('-') + 1);
        updateComment(commentId);
      });
    }
  
    // Function to delete comment
    function deleteComment(commentId) {
      $.ajax({
        method: 'delete',
        url: `${HOST}/comments/delete/` + commentId,
        success: function(response) {
          $('#modal-popup .modal-body p').text("Comment deleted successfully.");
          $('#modal-popup').modal('show');
          loadComments(); // Reload comments after deletion
        },
        error: function(xhr, status, error) {
          $('#modal-popup .modal-body p').text("Error deleting the comment.");
          $('#modal-popup').modal('show');
        }
      });
    }
  
    // Function to update comment
    function updateComment(commentId) {
      $.ajax({
        method: 'get',
        url: `${HOST}/comments/id/` + commentId,
        success: function(responseByID) {
          $('#modal-update-comment #comment-content').val(responseByID.comment_content); // Use .val() to set input field value
          $('#modal-update-comment').modal('show');
  
          // Unbind previous click handlers before binding a new one
          $('#save-comment').off('click').on('click', function() {
            let updatedContent = $('#modal-update-comment #comment-content').val();
            $.ajax({
              method: 'put',
              url: `${HOST}/comments/update/` + commentId,
              data: { 'comment_content': updatedContent },
              success: function(response) {
                $('#modal-update-comment .modal-body p').text("Saved successfully. Please close the window.");
                loadComments(); // Reload comments after update
              },
              error: function(xhr, status, error) {
                console.log('Error updating comment: ' + xhr.responseText);
              }
            });
          });
  
        },
        error: function(xhr, status, error) {
          console.log('Error retrieving comment: ' + xhr.responseText);
        }
      });
    }
  
    // Initial load of comments
    loadComments();
  });
  