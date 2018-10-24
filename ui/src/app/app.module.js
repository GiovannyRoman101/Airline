import apiUrl from './api.url.js'
import mainpage from './mainpage/mainpage.module.js'
import register from './register/register.module.js'
import login from './login/login.module.js'
import profile from './profile/profile.module.js'

export default
  angular
    .module('airline', ['ngAria',
      'ngAnimate',
      'ngMessages', 'ui.router',
      mainpage, register, login,
      profile
    ])
    .constant('apiUrl', apiUrl)
    .name
