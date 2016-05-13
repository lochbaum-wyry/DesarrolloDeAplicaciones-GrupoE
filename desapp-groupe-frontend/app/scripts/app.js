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
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/singUp.html',
        controller: 'UserCtrl',
        controllerAs: 'singUp'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
