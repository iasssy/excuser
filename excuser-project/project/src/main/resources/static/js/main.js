const HOST = "http://localhost:8080";

$(document).ready(function() {
    $('#btnGetCategoryByID').on('click', function(event) {
        event.preventDefault();
        $.ajax({
          method: "get",
          url: `${HOST}/category_tbl/2`
        })
          .done((response) => {
            document.getElementById("test").innerHTML = response.categoryName;
          })
          .fail((xhrObj) => alert(xhrObj));
    });
});

/*
function getCategoryByID() {
    alert("clicked!");
  $.ajax({
    method: "get",
    url: `${HOST}/category_tbl/2`,
  })
    .done((response) => {
      document.getElementById("test").innerHTML = response.categoryName;
    })
    .fail((xhrObj) => alert(xhrObj));
}
*/