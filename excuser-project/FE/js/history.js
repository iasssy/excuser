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
          //alert('History entry deleted successfully');
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