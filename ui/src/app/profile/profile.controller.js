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
    ctrl.origin
    ctrl.destination
    ctrl.itineraries
    ctrl.col = 0

    ctrl.getUser = function () {
      profileService.getUser($stateParams.email).then(function (result) {
        ctrl.user = result
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
    // gets all the flights currently available
    ctrl.getFlights = function () {
      profileService.getFlights().then(function (flights) {
        ctrl.flights = flights
      })
    }

    // place markers on the map for all locations
    $scope.placeMarkers = function () {
      profileService.getLocations().then(function (result) {
        ctrl.locations = result
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

    // saves an entire itinerary
    ctrl.saveItinerary = function (itinerary) {
      profileService.save($stateParams.email, itinerary).then(result => {
        ctrl.getUser()
      })
    }

    ctrl.allPaths = function () {
      profileService.getFlightsfromOriginToDestin(ctrl.origin.city, ctrl.destination.city).then(function (result) {
        ctrl.itineraries = result
      })
    }

    // displays path on the map
    ctrl.mapRoute = function (itinerary) {
      // resets the path so different paths are not displayed
      ctrl.paths = []
      for (let i = 0; i < itinerary.flights.length; i++) {
        let flight = itinerary.flights[i]
        ctrl.getflightPath(flight.origin, flight.destination)
      }
      ctrl.col = 0
    }

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
    }, 5000)
    $scope.refresh()
    $scope.placeMarkers()
  }
}
