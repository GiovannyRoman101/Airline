import profileApp from './profile.component.js'
import profileService from './profile_service/profile.service.js'

export default
  angular
    .module('profileApp', ['ngMap'])
    .component('profileApp', profileApp)
    .service('profileService', profileService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise('/')
      $stateProvider
        .state('profile', {
          url: '/profile/:email',
          component: 'profileApp'
        })
    }])
    .name
