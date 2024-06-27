$(document).ready(function() {
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
                let listItem = `<li class="list-group-item text-start">
                                    <div class="row">
                                        <div class="col-sm-2 col-12 d-flex align-items-center">
                                            <h5><span class="badge text-bg-warning rounded-pill">${item.category_name}</span></h5>
                                        </div>
                                        <div class="col-sm-7 col-12">
                                            ${item.excuse_content}
                                        </div>
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <a type="button" class="add-comment btn btn-info btn-sm rounded" id="new-${item.history_id}">Add comment</a>
                                        </div>
                                        <div class="col-sm-1 col-12 text-center mt-2">
                                            <div class="delete-icon" id="delete-${item.history_id}">
                                                <i class="bi bi-trash3-fill"></i>
                                            </div>
                                        </div>
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

          $('.add-comment').on('click', function() {
              let historyIdAttrComment = $(this).attr('id');
              // extract everything after "new-"
              let historyCommentId = historyIdAttrComment.substring(historyIdAttrComment.indexOf('-') + 1);  
              console.log("history_id " + historyCommentId);
            $('#modal-add-comment').modal('show');

            $('#save-comment').on('click', function() {
                let commentContent = $('#comment-content').val();
                if (!commentContent.trim()) {
                    // Add red border to textarea
                    $('#comment-content').addClass('border border-danger');
                    // Focus on textarea
                    $('#comment-content').focus();
                    return;
                }
                let commentData = {
                    user_id: session_user_id,  
                    history_id: historyCommentId,
                    comment_content: commentContent
                };
                $.ajax({
                    method: 'post',
                    url: `${HOST}/comments/save/`,
                    contentType: 'application/json',
                    data: JSON.stringify(commentData),
                    success: function(response) {
                        //alert('Comment added successfully.');                        
                        $('#modal-add-comment .modal-body p').text("Saved successfully. Please close the window.");
                        $('#comment-content').val('');
                        //$('#modal-add-comment').modal('hide');
                    },
                    error: function(xhr, status, error) {
                        //console.log('Error adding comment: ' + xhr.responseText);
                        $('#modal-popup .modal-body p').text("Error saving comment");
                        $('#modal-popup').modal('show');
                    }
                });
            });

            // Remove red border from textarea when user starts typing
            $('#comment-content').on('input', function() {
                if ($(this).val().trim()) {
                    $(this).removeClass('border border-danger');
                }
            });
          });
        },
        error: function(xhr, status, error) {
            //alert('Error loading history: ' + xhr.responseText);            
        }
    });    
}

// Function to delete history entry
function deleteHistory(historyId) {
  $.ajax({
      method: 'delete',
      url: `${HOST}/history/delete/`+historyId,
      success: function(response) {
          //console.log('History entry deleted successfully');                  
          $('#modal-popup .modal-body p').text("History entry deleted successfully");
          $('#modal-popup').modal('show');
          // reload history after deletion
          loadHistory();
      },
      error: function(xhr, status, error) {
          alert('Error deleting history: ' + xhr.responseText);
      }
  });
  } 


  loadHistory();

});