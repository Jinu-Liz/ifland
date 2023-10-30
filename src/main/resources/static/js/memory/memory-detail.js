/**
 * TODO 추후 로그인한 Member의 ID를 가져와 작성하도록 수정해야함
 */
const writeComment = function (id) {
  const comment = $('#comment');
  const commentBtn = $("#comment-button");
  const content = comment.val();
  let data = {
    memberId: 11,
    contentId: id,
    contents: content
  };

  let commentData =
      API.PostData("/api/memorize/comment", data)
          .then(() => makeCommentHtml())
          .catch(() => Message.error("댓글 작성 중 문제가 발생하였습니다. 다시 시도해주세요."));

  comment.val('');
  buttonDisabled(commentBtn);
}

const makeCommentHtml = function () {
  const memoryId = $('#memoryId').val();
  API.GetData('/api/memorize/detail/' + memoryId)
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