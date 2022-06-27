$(document).ready(function () {
  $('#comment').on('keyup', function () {
    if ($(this).val().length > 300) {
      alert("300자를 초과하였습니다.");
      $(this).val($(this).val().substring(0, 300));
    }
  })

  $('#comment-button').click(function () {
    if ($(this).val().isEmpty()) alert("내용을 작성해 주세요.");
  })
})