'use strict'

export default class LoginService {
  /* @ngInject */
  constructor ($http, apiUrl) {
    var service = this
    service.$http = $http
    service.apiUrl = apiUrl
  }

  login = function (email) {
    return this.$http.get(this.apiUrl + '/users/' + email).then(result => result.data)
  }
}
