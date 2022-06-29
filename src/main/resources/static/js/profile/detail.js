$(document).ready(function () {
  const comment = $('#comment');
  const commentBtn = $("#comment-button");

  comment.on('keyup', function () {

    if ($(this).val().length > 0) buttonActive(commentBtn);

    if ($(this).val().length <= 0) buttonDisabled(commentBtn);

    if ($(this).val().length > 300) {
      alert("300자를 초과하였습니다.");
      $(this).val($(this).val().substring(0, 300));
    }
  })

  if (comment.val().isEmpty()) buttonDisabled(commentBtn);

})

const buttonActive = function (commentBtn) {
  commentBtn.css("background", "#e53637");
  commentBtn.attr('disabled', false);
}

const buttonDisabled = function (commentBtn) {
  commentBtn.attr('disabled', true);
  commentBtn.css("background", "#dee2e6");
}