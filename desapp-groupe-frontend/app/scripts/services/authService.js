'use strict';

angular.module('desappGrupoeFrontendApp')
  .factory('AuthService', function($localStorage) {
    return {

      getTokenKey: function() {
        return 'accessToken';
      },

      getToken: function() {
        return $localStorage.accessToken;
      },

      isLoggedIn: function() {
        return !!this.getToken();
      },

      login: function(token) {
        $localStorage.accessToken = token;
      },

      logout: function() {
        return $localStorage.accessToken = 0;
      }
    };
  });