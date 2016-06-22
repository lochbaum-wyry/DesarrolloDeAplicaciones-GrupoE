'use strict'
var map ; 
var directionsService;
var directionsDisplay;

function initMap() {
  directionsService = new google.maps.DirectionsService;
  directionsDisplay = new google.maps.DirectionsRenderer;
}
