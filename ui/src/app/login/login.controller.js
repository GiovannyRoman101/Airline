'use strict'
var bcrypt = require('bcryptjs')

export default class LoginController {
  /* @ngInject */
  constructor (loginService, $state) {
    var ctrl = this
    ctrl.user
    ctrl.login = function (user) {
      loginService.login(user.email).then(function (data) {
        bcrypt.compare(user.password, data.password, function (err, res) {
          if (res === true) {
            $state.go('profile', {email: data.email})
          }
        })
      })
    }
  }

}
