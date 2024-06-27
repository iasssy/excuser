
$(document).ready(function() {
  function loadComments() {
    $.ajax({
        method: 'get',
        url: `${HOST}/comments/`,
        success: function(response) {
            let commentsList = $('#comments-list');
            commentsList.empty();
            response.forEach(commentResponse => {
                
                $.ajax({
                    method: 'get',
                    url: `${HOST}/history/id/`+commentResponse.history_id,
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
                                      <div class="col-sm-2 col-12 text-center mt-2">
                                          <div class="delete-icon" id="delete-${commentResponse.comment_id}">
                                              <i class="bi bi-trash3-fill"></i>
                                          </div>
                                      </div>
                                  </div>
                              </li>`;
                        commentsList.append(listItem);
                        
                        // click to delete comment_id
                        $('.delete-icon').on('click', function() {
                            let commentIdAttr = $(this).attr('id');
                            // extract everything after "history-"
                            let commentId = commentIdAttr.substring(commentIdAttr.indexOf('-') + 1);               
                            deleteHistory(commentId);
                        });


                    },
                    error: function(xhr, status, error) {
                        console.log('Error adding comment: ' + xhr.responseText);
                    }
                });

                

            });
          },
          error: function(xhr, status, error) {
              console.log('Error loading history: ' + xhr.responseText);                      
              $('#modal-popup .modal-body p').text("Error loading history");
              $('#modal-popup').modal('show');
          }
    });    
  }

    
    // Function to delete history entry
    function deleteHistory(commentId) {
        $.ajax({
            method: 'delete',
            url: `${HOST}/comments/delete/`+commentId,
            success: function(response) {
                //console.log('Comment deleted successfully');                  
                $('#modal-popup .modal-body p').text("Comment deleted successfully.");
                $('#modal-popup').modal('show');
                // reload comments after deletion       
                loadComments();        
            },
            error: function(xhr, status, error) {
                //alert('Error deleting history: ' + xhr.responseText);                               
                $('#modal-popup .modal-body p').text("Error deleting the comment.");
                $('#modal-popup').modal('show');
            }
        });
    }

  loadComments();



});