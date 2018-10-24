'use strict'
import colors from './routeColors.js'

export default class ProfileController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []
  colors = colors
  /* @ngInject */
  constructor (profileService, $scope, $interval, $stateParams) {
    var ctrl = this
    ctrl.flights
    ctrl.user
    ctrl.locations

    ctrl.col = 0

    ctrl.getUser = function () {
      profileService.getUser($stateParams.email).then(function (result) {
        ctrl.user = result
        console.log(ctrl.user)
      })
    }

    // this method is only to save one flight
    ctrl.save = function (flight) {
      const itineraries = {}
      itineraries.origin = flight.origin
      itineraries.destination = flight.destination
      itineraries.flightTime = flight.flightTime
      itineraries.layovertime = 0
      itineraries.flights = []
      itineraries.flights[0] = flight
      profileService.save($stateParams.email, itineraries).then(function (result) {
        ctrl.getUser()
      })
    }
    ctrl.getFlights = function () {
      profileService.getFlights().then(function (flights) {
        ctrl.flights = flights
      })
    }

    // need test
    ctrl.mapRoute = function (itinerary) {
      // resets the path so different paths are not displayed
      ctrl.paths = []
      for (let i = 0; i < itinerary.flights.length; i++) {
        let flight = itinerary.flights[i]
        ctrl.getflightPath(flight.origin, flight.destination)
      }
      ctrl.col = 0
    }
    $scope.placeMarkers = function () {
      profileService.getLocations().then(function (result) {
        ctrl.locations = result
        console.log(ctrl.locations)

        ctrl.locations.forEach(function (marker) {
          ctrl.addMarker(marker.latitude, marker.longitude)
        })
      })
    }

    // adds markers on the map
    ctrl.addMarker = function (latitude, longitude) {
      ctrl.markers.push({
        position: `[${latitude}, ${longitude}]`
      })
    }

    // adds path between two points on the map
    ctrl.addPath = function (a, b, color) {
      ctrl.paths.push({
        path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
        strokeColor: color,
        strokeOpacity: 1.0,
        strokeWeight: 3,
        geodesic: true
      })
    }

    // gets all possible routes (need test)
    ctrl.allPaths = function () {
      profileService.findFlightRoutes(ctrl.origin, ctrl.destination).then(function (result) {
        ctrl.flightplans = result
      })
    }

    // need test
    ctrl.getflightPath = function (origin, destination) {
      profileService.getMarkerByCityName(origin).then(function (start) {
        origin = start
        profileService.getMarkerByCityName(destination).then(function (end) {
          destination = end
          let color = ctrl.colors[ctrl.col]
          ctrl.addPath(origin, destination, color.value)
          ctrl.col++
        })
      })
    }

    $scope.refresh = function () {
      ctrl.getFlights()
      ctrl.getUser()
    }

    $scope.intervalPromise = $interval(function () {
      $scope.refresh()
    }, 1000)
    $scope.refresh()
    $scope.placeMarkers()
  }
}
