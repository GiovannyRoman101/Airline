import loginApp from './login.component.js'
import loginService from './login_service/login.service.js'

export default
  angular
    .module('loginApp', [])
    .component('loginApp', loginApp)
    .service('loginService', loginService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise('/')
      $stateProvider
        .state('login', {
          url: '/login',
          component: 'loginApp'
        })
    }])
    .name
