String.prototype.isEmpty = function () {
  let str = this.toString();
  return str.length === 0 || str === '' || str === undefined || str === null || str === 'null';
}

String.prototype.isNotEmpty = function () {
  let str = this.toString();
  return str.length > 0 || str !== '' || str !== undefined || str !== 'null';
}