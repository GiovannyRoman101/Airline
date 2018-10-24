'use strict'

export default class MainController {
  zoom = 7
  center = [35.5175, -86.5804]
  /* @ngInject */
  constructor (mainpageService, $scope, $interval) {
    var ctrl = this
    ctrl.flights
    ctrl.user

    $scope.refresh = function () {
      mainpageService.getFlights().then(function (flights) {
        ctrl.flights = flights
      })
    }

    $scope.intervalPromise = $interval(function () {
      $scope.refresh()
    }, 1000)
    $scope.refresh()
  }
}
