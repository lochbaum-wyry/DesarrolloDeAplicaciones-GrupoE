'use strict';

/**
 * @ngdoc overview
 * @name desappGrupoeFrontendApp
 * @description
 * # desappGrupoeFrontendApp
 *
 * Main module of the application.
 */
angular
  .module('desappGrupoeFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'pascalprecht.translate',
    'ngStorage',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap.datetimepicker',
    'angularUtils.directives.dirPagination'
  ])
  .config(function ($routeProvider,$httpProvider,$translateProvider) {
    $routeProvider
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/addRoute', {
        templateUrl: '/views/addRoute.html',
        controller: 'AddRouteCtrl',
        controllerAs: 'addrf'
      })
      .when('/home', {
        templateUrl: '/views/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      })
      .when('/webshop', {
        templateUrl: '/views/webshop.html',
        controller: 'WebShopCtrl',
        controllerAs: 'ws'
      })
      .when('/chats', {
        templateUrl: '/views/chats.html',
        controller: 'ChatCtrl',
        controllerAs: 'ct'
      })
      .when('/routeFind', {
        templateUrl: '/views/routeFind.html',
        controller: 'RouteFindCtrl',
        controllerAs: 'rf'
      })
      .when('/rankings', {
        templateUrl: '/views/rankings.html',
        controller: 'RankingCtrl',
        controllerAs: 'rnk'
      })
      .otherwise({
        templateUrl: 'views/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      });
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];

      $translateProvider.translations('es',{
        'home': 'Home',
        'viajes':'Viajes',
        'confirmados':'Confirmados',
        'solicitudes':'Solicitudes',
        'solicitados':'Solicitados',
        'calificar viaje':'Calificar Viaje',
        'recorridos':'Recorridos',
        'mis recorridos':'Mis Viajes',
        'agregar recorrido':'Agregar Recorrido',
        'chats':'Chats',
        'webShop':'WebShop',
        'buscar recorrido':'Buscar Recorrido',
        'rankings':'Rankings',
        'datos personales':'Datos Personales',
        'proximos viajes':'Proximos Viajes',
        'votos buenos':'Votos Buenos',
        'votos malos':'Votos Malos',
        'total de votos':'Total de Votos',
        'dia':'Dia',
        'hora':'Hora',
        'origen':'Origen',
        'destino':'Destino',
        'intencion':'Intencion',
        'costo':'Costo',
        'comentarios':'Comentarios',
        'latitud':'Latitud',
        'longitud':'Longitud',
        'agregar punto':'Agregar Punto',
        'agregar ruta':'Agregar Ruta',
        'mis rutas':'Mis Rutas',
        'distancia':'Distancia',
        'chats':'Chats',
        'filtro':'Filtro',
        'nombre':'Nombre',
        'productos':'Productos',
        'mis puntos':'Mis Puntos',
        'producto':'Producto',
        'stock':'Stock',
        'sube en':'Sube en',
        'baja en':'Baja en',
        'un lugar':'Un lugar',
        'fecha y hora':'Fecha y Hora',
        'buscar':'Buscar',
        'mes':'Mes',
        'año':'Año',
        'conductores':'Conductores',
        'pasajeros':'Pasajeros',
        'vehiculos':'Vehiculos',
        'ranking':'Ranking',
        'usuario':'Usuario',
        'puntos':'Puntos',
        'salir':'Salir',
        'hora_partida': 'Hora'
      });

      $translateProvider.translations('en',{
        'home': 'Home',
        'viajes':'Rides',
        'confirmados':'Confirmed',
        'solicitudes':'Request',
        'solicitados':'Requested',
        'calificar viaje':'Ride Rating',
        'recorridos':'Routes',
        'mis recorridos':'My Rides',
        'agregar recorrido':'Add Route',
        'chats':'Chats',
        'webShop':'WebShop',
        'buscar recorrido':'RouteFind',
        'rankings':'Rankings',
        'datos personales':'Settings',
        'proximos viajes':'Upcoming Rides',
        'votos buenos':'GoodRateCount',
        'votos malos':'BadRateCount',
        'total de votos':'TotalRateCount',
        'dia':'Day',
        'hora':'Hour',
        'origen':'Origin',
        'destino':'Destination',
        'intencion':'Intention',
        'costo':'Cost',
        'comentarios':'Comments',
        'latitud':'Latitude',
        'longitud':'Longitude',
        'agregar punto':'Add Point',
        'agregar ruta':'Add Route',
        'mis rutas':'My Routes',
        'distancia':'Distance',
        'chats':'Chats',
        'filtro':'Filter',
        'nombre':'Name',
        'productos':'Products',
        'mis puntos':'My Points',
        'producto':'Product',
        'stock':'Stock',
        'sube en':'Board at',
        'baja en':'Get off at',
        'un lugar':'A place',
        'fecha y hora':'Date and Hour',
        'buscar':'Search',
        'mes':'Month',
        'año':'Year',
        'conductores':'Drivers',
        'pasajeros':'Passenger',
        'vehiculos':'Vehicle',
        'ranking':'Ranking',
        'usuario':'User',
        'puntos':'Points',
        'salir':'Log out',
        'hora_partida': 'Time'
      });

      var language = (navigator.language || navigator.browserLanguage).split('-')[0];
      $translateProvider.preferredLanguage(language);
  });
