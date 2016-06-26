  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('AddRouteCtrl', AddRouteCtrl);

function AddRouteCtrl(UserService,$localStorage, SessionService, GoogleMapsService, GMRouteService, GmSubiConv) {

  var vm = this;
  
  /* String */        vm.ERROR_MSG;

  /* User */          vm.user = SessionService.user();
  /* Route[] */       vm.userRoutes = vm.user.routes ; 
  /* RoutePoint[] */  vm.routePoints = [] ;
  /* Schedule[] */    vm.schedules   = [] ;
  /* Float */         vm.distanceInKms = 7.8; // datos de ejemplo 

  /* Float */         vm.fixedCosts; // datos por default/sugeridos
  /* int */           vm.scheduleDay = moment().weekday();
  /* DateTime */      vm.scheduleDepartureTime = moment();

   

  //-------------------
  //-- Scope functions
  //-------------------
  vm.addPoint = addPoint;
  vm.addSchedule = addSchedule; 
  vm.addRoute = addRoute; 

  //-----------------------------
  //-- Controller initialization
  //-----------------------------


  addPoint(-34.702742,-58.2937437); // datos de ejemplo 
  addPoint(-34.6753329,-58.342759); // datos de ejemplo 

  setDefaultSchedule();
  GoogleMapsService.instanceMap('mapAddRoute');
  GMRouteService.createRouteWithMarkers();

  //-------------------
  //-- Functions
  //-------------------

  function setDefaultSchedule()
  {
    vm.scheduleDay = moment().weekday() + ""; // lo convierto a string porque parece no ser tan mágico
    vm.scheduleDepartureTime = moment();
  }


  //----------------------------------
  //-- Scope functions implementation
  //----------------------------------

  function addPoint(latitude, longitude) {
    var latLng = {
      latitude:  parseFloat(latitude),
      longitude: parseFloat(longitude)
    };

    vm.routePoints.push(latLng);
  }

  function addSchedule() {
    var dia = parseInt(vm.scheduleDay);
    var diaStr = moment().weekday(vm.scheduleDay);
    var schedule = { 
      day:            dia,
      dayStr:         diaStr.format("dddd"),
      departureTime:  vm.scheduleDepartureTime
    };

    vm.schedules.push(schedule);
    setDefaultSchedule();
  }
  
  function addRoute() {
    var directions = GMRouteService.endRouteWithMarkers(); 
    var route = GmSubiConv.routeFromDirections(directions);

    var schedules = vm.schedules.map(convertToSchedule);

    UserService.addRoute(vm.user.id,route.routePoints,route.distanceInKms,vm.fixedCosts, schedules) 
              .then(onSuccess)
              .catch(onFailure);

    function convertToSchedule(schedule) {
      return {
        day: schedule['day'], 
        departureTime: toJodaLocalTime(schedule['departureTime'])
      };
    }

    /** 
     * Formato que espera el desserializador de jodatime para el tipo LocalTime
     */
    function toJodaLocalTime(datetime) {
      return [
        datetime.hour(), 
        datetime.minutes(),
        datetime.seconds(),
        datetime.milliseconds()
      ];
    }
  }

  
  function onSuccess(result) {
    vm.routePoint = [];
    SessionService.reloadUser();
    vm.user = SessionService.user();
    vm.userRoutes = SessionService.user().routes;
  }

  function onFailure(error){
    vm.ERROR_MSG = error;
  }

} // addRouteCtrl



})()