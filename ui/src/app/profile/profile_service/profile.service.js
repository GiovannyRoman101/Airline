
export default class ProfileService {
  /* @ngInject */
  constructor ($http, apiUrl) {
    var service = this
    service.$http = $http

    service.getUser = function (email) {
      return service.$http.get(apiUrl + '/users/' + email).then(result => result.data)
    }
    service.getFlights = function () {
      return service.$http.get(apiUrl + '/flights').then(result => result.data)
    }
    service.getFlightsfromOriginToDestin = function (origin, destination) {
      return service.$http.get(apiUrl + '/flights/' + origin + '/' + destination).then(result => result.data)
    }
    service.save = function (email, itineraries) {
      return service.$http.put(apiUrl + '/users/' + email + '/Itineraries', itineraries).then(result => result.data)
    }
    service.getLocations = function () {
      return service.$http.get(apiUrl + '/locations').then(result => result.data)
    }

    service.getMarkerByCityName = function (name) {
      return service.$http.get(apiUrl + '/locations/' + `name`, { params: { name } }).then(result => result.data)
    }
  }
}
