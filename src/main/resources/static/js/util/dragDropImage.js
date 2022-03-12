let uploadFiles = [];
let $drop = $("#drop");
console.log($drop);

// $drop.on("dragenter", function (e) {    // 드래그 요소가 들어왔을 때
//   console.log("dragenter");
//   $(this).addClass("drag-over");
//
// }).on("dragleave", function (e) {   // 드래그 요소가 나갔을 때
//   console.log("dragover");
//   $(this).removeClass("drag-over");
//
// }).on("dragover", function (e) {
//   console.log("dragover");
//   e.stopPropagation();
//   e.preventDefault();
//
// }).on("drop", function (e) {    // 드래그한 항목을 떨어뜨렸을 때
//   console.log("drop");
//   e.preventDefault();
//   $(this).removeClass("drag-over");
//
//   let files = e.originalEvent.dataTransfer.files;    // 드래그&드랍 항목
//   console.table(files);
//   for (let i = 0; files.length; i++) {
//     let file = files[i];
//     let size = uploadFiles.push(file);    // 업로드 목록에 추가
//     preview(file, size - 1);
//   }
// });

function preview(file, idx) {
  var reader = new FileReader();
  reader.onload = (function(f, idx) {
    return function(e) {
      let div = '<div class="thumb"> \
                  <div class="close" data-idx="' + idx + '">X</div> \
                  <img src="' + e.target.result + '" title="' + encodeURIComponent(f.name) + '"/> \
                 </div>';
      $("#thumbnails").append(div);
    };
  })(file, idx);
  reader.readAsDataURL(file);
}

$("#thumbnails").on("click", ".close", function(e) {
  var $target = $(e.target);
  var idx = $target.attr('data-idx');
  uploadFiles[idx].upload = 'disable'; //삭제된 항목은 업로드하지 않기 위해 플래그 생성
  $target.parent().remove(); //프리뷰 삭제
});

// Check for the various File API support.
if (window.File && window.FileReader && window.FileList && window.Blob) {
  function showFile() {
    var demoImage = document.querySelector('img');
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.onload = function (event) {
      demoImage.src = reader.result;
    }
    reader.readAsDataURL(file);
    console.log(file)
  }
} else {
  alert("Your browser is too old to support HTML5 File API");
}