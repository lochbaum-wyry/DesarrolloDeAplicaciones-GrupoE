'use strict'
var map ; 
var directionsService;
var directionsDisplay;
var geocoder ; 
function initMap() {
  directionsService = new google.maps.DirectionsService;
  directionsDisplay = new google.maps.DirectionsRenderer;
  geocoder = new google.maps.Geocoder;

}
