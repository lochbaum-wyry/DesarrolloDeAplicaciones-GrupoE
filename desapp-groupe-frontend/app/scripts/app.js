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
    'ngTouch',
    'ui.bootstrap.datetimepicker'
  ])
  .config(function ($routeProvider,$httpProvider) {
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
        controller: 'chatCtrl',
        controllerAs: 'chat'
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
  });
