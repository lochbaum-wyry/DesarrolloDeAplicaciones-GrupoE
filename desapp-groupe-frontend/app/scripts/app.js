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
      .when('/profile/:id', {
        templateUrl: '/views/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'p'
      })
      .when('/rankings', {
        templateUrl: '/views/rankings.html',
        controller: 'RankingCtrl',
        controllerAs: 'rnk'
      })
      .when('/requestedRides', {
        templateUrl: '/views/requestedRides.html',
        controller: 'RequestedRidesCtrl',
        controllerAs: 'rrc'
      })
      .when('/rideRequests', {
        templateUrl: '/views/rideRequests.html',
        controller: 'RideRequestsCtrl',
        controllerAs: 'rrc'
      })
      .otherwise({
        templateUrl: 'views/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      });
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];

      $translateProvider.translations('es', getTranslations('es'));
      $translateProvider.translations('en', getTranslations('en'));

      var language = (navigator.language || navigator.browserLanguage).split('-')[0];
      $translateProvider.preferredLanguage(language);
  });
