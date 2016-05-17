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
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider,$httpProvider) {
    $routeProvider
      .when('/signUp', {
        templateUrl: 'views/signUp.html',
        controller: 'UserCtrl',
        controllerAs: 'SignUp'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/home2', {
        templateUrl: '/views/home2.html',
        controller: 'UserCtrl',
        controllerAs: 'home'
      })
      .otherwise({
        templateUrl: 'views/signUp.html',
        controller: 'UserCtrl',
        controllerAs: 'SignUp'
      });
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];
  });
