// 빈값 여부 판단
isEmpty = function (str) {
  return (str === undefined || str === null || str === 'null' || str === '' || str.length === 0);
}

// 빈값X 여부 판단
isNotEmpty = function (str) {
  return (str !== undefined || str !== null || str !== 'null' || str !== '' || str.length > 0);
}

// 문자 동일 여부 판단
String.prototype.isEquals = function(str) {
  return this == str;
}

// 문자 다름 여부 판단
String.prototype.isNotEquals = function(str) {
  return this != str;
}

class MsgType {
  static SUCCESS = "success"
  static ERROR = "error"
  static WARNING = "warning"
  static INFO = "info"
  static QUESTION = "question"
}

class Message {
  constructor(text, icon) {
    this.text = text;
    this.icon = icon
    this.color = '#ffffff';
    this.background = '#212529';
    this.confirmButtonColor = '#e53637';
  }

  static success = (text) => {
    let msg = new Message(text, MsgType.SUCCESS);
    Swal.fire(msg);
  }

  static error = (text) => {
    let msg = new Message(text, MsgType.ERROR);
    Swal.fire(msg);
  }

  static warning = (text) => {
    let msg = new Message(text, MsgType.WARNING);
    Swal.fire(msg);
  }

  static info = (text) => {
    let msg = new Message(text, MsgType.INFO);
    Swal.fire(msg);
  }

  static question = (text) => {
    let msg = new Message(text, MsgType.QUESTION);
    Swal.fire(msg);
  }
}