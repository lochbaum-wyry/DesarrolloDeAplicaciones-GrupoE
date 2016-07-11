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
    'snapscroll',
    'ngRoute',
    'ngMessages',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap.datetimepicker',
    'ngFlash',
    'angularUtils.directives.dirPagination',
    'ui.dateTimeInput'
  ])
  .config(function ($routeProvider,$httpProvider,$translateProvider,FlashProvider) {
    $routeProvider
      .when('/about', {
        templateUrl: 'views2_0/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/addRoute', {
        templateUrl: '/views2_0/addRoute.html',
        controller: 'AddRouteCtrl',
        controllerAs: 'addrf'
      })
      .when('/home', {
        templateUrl: '/views2_0/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      })
      .when('/webshop', {
        templateUrl: '/views2_0/webshop.html',
        controller: 'WebShopCtrl',
        controllerAs: 'ws'
      })
      .when('/user_data', {
        templateUrl: '/views2_0/user_data.html',
        controller: 'UserDataCtrl',
        controllerAs: 'ud'
      })
      .when('/chats', {
        templateUrl: '/views2_0/chats.html',
        controller: 'ChatCtrl',
        controllerAs: 'ct'
      })
      .when('/routeFind', {
        templateUrl: '/views2_0/routeFind.html',
        controller: 'RouteFindCtrl',
        controllerAs: 'rf'
      })
      .when('/profile/:id', {
        templateUrl: '/views2_0/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'p'
      })
      .when('/rankings', {
        templateUrl: '/views2_0/rankings.html',
        controller: 'RankingCtrl',
        controllerAs: 'rnk'
      })
      .when('/requestedRides', {
        templateUrl: '/views2_0/requestedRides.html',
        controller: 'RequestedRidesCtrl',
        controllerAs: 'rrc'
      })
      .when('/rateRide', {
        templateUrl: '/views2_0/rateRide.html',
        controller: 'RateRideCtrl',
        controllerAs: 'rrc'
      })
      .when('/rideRequests', {
        templateUrl: '/views2_0/rideRequests.html',
        controller: 'RideRequestsCtrl',
        controllerAs: 'rrc'
      })
      .otherwise({
        templateUrl: '/views2_0/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      });
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];

      $translateProvider.translations('es', getTranslations('es'));
      $translateProvider.translations('en', getTranslations('en'));

      var language = (navigator.language || navigator.browserLanguage).split('-')[0];
      $translateProvider.preferredLanguage(language);

      FlashProvider.setTimeout(10);
      FlashProvider.setShowClose(true);
      
  });
