// Promise 객체
const HEADER = { 'Content-Type': 'application/json' }
const METHOD = {
  GET : 'GET',
  POST : 'POST',
  PUT : 'PUT',
  PATCH : 'PATCH',
  DEL : 'DEL'
}

class API {

  static async GetData(url) {
    return await fetch(url);
  }

  static async PostData(url, data) {
    return await fetch(
        url,
        {
          method: METHOD.POST,
          headers: HEADER,
          body: JSON.stringify(data)
        });
  }

  static async PutData(url, data) {
    return await fetch(
        url,
        {
          method: METHOD.PUT,
          headers: HEADER,
          body: JSON.stringify(data)
        });
  }

  static async PatchData(url, data) {
    return await fetch(
        url,
        {
          method: METHOD.PATCH,
          headers: HEADER,
          body: JSON.stringify(data)
        });
  }

  static async DelData(url, data) {
    return await fetch(
        url,
        {
          method: METHOD.DEL,
          headers: HEADER,
          body: JSON.stringify(data)
        });
  }

}