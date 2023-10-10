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

/**
 * TODO
 * - 댓글 가져오는 로직 수정.
 * - 댓글 가져오는 API 수정.
 * - 댓글 HTML 그리는 로직 수정.
 * - fetch API 사용 로직 공통화.
 */
const writeComment = function (id) {
  const comment = $('#comment');
  const commentBtn = $("#comment-button");
  const content = comment.val();
  let data = {
    memberId: id,
    contents: content
  };

  fetch(
      "/api/profile/comment",
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      })
      .then(
          () => makeCommentHtml()
      );

  comment.val('');
  buttonDisabled(commentBtn);
}

const makeCommentHtml = function () {
  fetch('/api/profile/detail?id=1')
      .then(res => res.json())
      .then(data => {
        let html = "";
        html += '<div class="section-title">';
        html +=   '<h5>Comment</h5>';
        html += '</div>';

        const arr = data.comments;
        arr.forEach((comment, idx) => {
          let recentComment = idx === 0;
          html += '<div class="anime__review__item">';
          (recentComment) ? html +=   '<div class="anime__review__item__pic last__comment">' : html +=   '<div class="anime__review__item__pic">';
          html +=     '<img src="/images/profile/basic-profile-img.png" alt="">';
          html +=   '</div>';
          (recentComment) ? html += '<div class="anime__review__item__text" style="background-color: dimgray;">' : html += '<div class="anime__review__item__text">';
          html +=     '<h6><span style="color: #ffffff"></span>' + comment.iflandNickName + '<span> - ' + comment.dayAgo + '</span></h6>';
          html +=     '<p>' + comment.contents + '</p>';
          html +=   '</div>';
          html += '</div>';
        });

        $('#commentDiv').html(html);

      });
}