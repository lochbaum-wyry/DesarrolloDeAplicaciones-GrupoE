(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('RouteFindCtrl', RouteFindCtrl);

/* @ngInject */
function RouteFindCtrl(RouteService,RideService,SessionService,GoogleMapsService, GmSubiConv) {
  /* RouteFindCtrl */ var vm = this;
  /* String */ vm.ERROR_MSG = null;

  vm.markers = [] ; 

  //------------
  // Scope data
  //------------

  //-- for getRideProposals
  /* LatLng */ vm.departureInput; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.arrivalInput;
  /* DateTime */ vm.selDate; // fecha seleccionada por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selDeparturePoint; // puntos seleccionados por el usuario para la búsqueda de rutas
  /* LatLng */ vm.selArrivalPoint;
  /* RideProposal[] */ vm.rideProposals = [];

  //-- for submitRideRequest
  /* int */ vm.selRideProposalIdx ; // Indice de propuesta seleccionada por el usuario para solicitar unirse

  //------------------------------
  // Scope functions declarations
  //------------------------------
  vm.getRideProposals = getRideProposals;
  vm.requestRide = requestRide; 
  vm.selectRideProposal = selectRideProposal;
  vm.parseTimestamp = parseTimestamp;
  vm.setPointFromAddress = setPointFromAddress;
  //---------------------------
  // Controller initialization

  setDefaultValues() ;
  GoogleMapsService.clearMap();
  GoogleMapsService.instanceMap('mapRouteFind');
  GoogleMapsService.onMapDblClick( addFindMarkers ) ; 

  var origin_autocomplete = new google.maps.places.Autocomplete((document.getElementById('subeEn')),{types: ['geocode']});

//   var destination_autocomplete = new google.maps.places.Autocomplete((document.getElementById('bajaEn')), {'types': ['geocode']});


  origin_autocomplete.addListener('place_changed', function() {
    var place = origin_autocomplete.getPlace();
    console.log(place);
    // if (!place.geometry) {
    //   window.alert("Autocomplete's returned place contains no geometry");
    //   return;
    // }

  });



  //--------------------------------
  // Scope functions implementation
  //--------------------------------

  function setPointFromAddress(whichPoint) {
    // switch (whichPoint) { 
    //   case 
    // }
  }

  function parseTimestamp(timestamp) {
    var ts = moment(timestamp);
    return ts;
  }

  function getRideProposals() {
    /* String */ var departurePoint = latLngToString(vm.selDeparturePoint);
    /* String */ var arrivalPoint = latLngToString(vm.selArrivalPoint);
    var routePromise = RouteService.getRideProposals(vm.selDate.valueOf(), departurePoint, arrivalPoint)
              .then(onSuccess)
              .catch(onFailure);

    vm.selRideProposalIdx= null;

    function onSuccess(response) { 
      vm.rideProposals = response ;
      if (vm.rideProposals.length > 0) {
        selectRideProposal(0);
      }
    }

    function onFailure(error) {
      infoModal("failed_getting_proposals", 'error');
     }
  }

  function selectRideProposal(index)
  {
    vm.selRideProposalIdx = index;
    // GoogleMapsService.clearMap();
    renderRideProposal(index);
  }

  function requestRide() {
    var rideProposal = vm.rideProposals[vm.selRideProposalIdx];
    var rideRequest = rideProposalToRideRequest(rideProposal, SessionService.user());
    RideService.requestRide(rideRequest).then(onReqSucc).catch(onReqFail);

    function onReqSucc(data) {
      infoModal("successfull_ride_request_msg");
      setDefaultValues();
      vm.rideProposals = [];
    }

    function onReqFail(error) {
      infoModal("failed_requesting_ride", 'error');
      console.log(error);
    }
  }

  //-----------
  // Functions
  //-----------

  function setDefaultValues() {
    vm.selDate = moment("2016-07-08 9:20", "YYYY-MM-DD HH:mm"); 
    vm.selDeparturePoint = GoogleMapsService.latLng(-34.702742,-58.2937437); 
    vm.selArrivalPoint = GoogleMapsService.latLng(-34.6753329,-58.342759);
    vm.selRideProposalIdx= null;
  }

  function rideProposalToRideRequest(/* RideProposal */ rideProposal, /* User */ requester) {
    return {
      driverId: rideProposal['driver']['id'],
      requesterId: requester['id'],
      routeId: rideProposal['route']['id'],
      date: rideProposal['departureDateTime'],
      boardingAtId: rideProposal['boardingPoint']['id'],
      getOffAtId: rideProposal['getOffPoint']['id']
    };
  }

  function latLngToString( /* LatLng */ latLng) {
    return "" + latLng.lat + "," + latLng.lng;
  }

  function infoModal(msg, type) {
    if (type && type=='error') {
      msg = "Error: " + msg; 
    }
    alert(msg);
  }

  function renderRideProposal(rideProposalIdx) { 
    /* RideProposal */ var rideProposal = vm.rideProposals[rideProposalIdx];
    renderRouteOnMap(
      rideProposal['route']['routePoints'], 
      rideProposal['boardingPoint'],  // punto cercano al que el usuario solicito subirse 
      rideProposal['getOffPoint']  // punto cercano al que el usuairo solicita bajarse 
    );
  } 

  /**
   * RoutePoint[] points: lista de todos los puntos que componen la ruta:
   * RoutePoint boardingPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para subirse
   * RoutePoint getOffPoint: Punto mas cercano dentro de la ruta al que el usuario seleccionó para bajarse
   */ 
  function renderRouteOnMap(points, boardingPoint, getOffPoint) {
      var markersInfo = [
        { index: boardingPoint['indexInRoute'], content: "Boarding here" },
        { index: getOffPoint['indexInRoute'], content: "Getting off here" }
      ];

      var latLngPoints = points.map(GmSubiConv.latLngFromRoutePoint); 
      GoogleMapsService.renderRoute(latLngPoints, markersInfo); 
  }

  function addFindMarkers(e) {
    var msg ; 
    var pos = GoogleMapsService.latLng(e.latLng.lat() , e.latLng.lng() );
    var markerCallback ; 

    if (vm.markers.length == 2) {
      GoogleMapsService.clearMap();
      vm.markers = []; 
      vm.selDeparturePoint = null;
      vm.selArrivalPoint = null ; 
    }

    if (vm.markers.length == 0) {
      msg = 'boarding_here';
      vm.selDeparturePoint = pos;
      markerCallback = modifyFindMarkerPoint.bind('boarding');

    } else if (vm.markers.length == 1) {
      msg = 'getting_off_here';
      vm.selArrivalPoint = pos;
      markerCallback = modifyFindMarkerPoint.bind('getOff');
    }
    
    var marker = GoogleMapsService.addMarker(pos, true, msg);
    GoogleMapsService.addListener(marker,'dragend', markerCallback);
    vm.markers.push(marker); 

    function modifyFindMarkerPoint(evt) {
      var whichPoint = this;
      var pos = GoogleMapsService.latLng(evt.latLng.lat() , evt.latLng.lng() )
      switch(whichPoint) { 
        case 'boarding': 
          vm.selDeparturePoint = pos ; 
          break; 
        case 'getOff': 
          vm.selArrivalPoint = pos ; 
          break; 
      }
    }
  }

} // RouteFindCtrl


})()


