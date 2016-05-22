(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ChatCtrl', chatCtrl );

/* @ngInject */
function chatCtrl($localStorage) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;

  //vm.chats = $localStorage.user.chats;
  vm.chats = [{'name':'Dan'},{'name':'bernardo'},{'name':'carlos'},{'name':'sarasa'}]

  vm.chatSelected = {'name':'Dan'};
  }

})()