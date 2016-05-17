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
    'ngStorage',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider,$httpProvider) {
    $routeProvider
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/home', {
        templateUrl: '/views/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      })
      .when('/routeFind', {
        templateUrl: '/views/routeFind.html',
        controller: 'routeFindCtrl',
        controllerAs: 'rf'
      })
      .otherwise({
        templateUrl: 'views/home.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      });
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];
  });
