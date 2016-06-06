(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ChatCtrl', chatCtrl );

/* @ngInject */
function chatCtrl($localStorage) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  vm.chats = $localStorage.user.chats;
  }

})()