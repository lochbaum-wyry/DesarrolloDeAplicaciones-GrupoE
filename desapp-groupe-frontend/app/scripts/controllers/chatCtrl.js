(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ChatCtrl', chatCtrl );

/* @ngInject */
function chatCtrl(SessionService,UserService,ChatService) {
  var vm = this; 

  /* String */ vm.ERROR_MSG = null;
  vm.search = '';
  vm.user = SessionService.user();
  vm.users = [];
  vm.chatSelected = {};
  vm.text = "";


  vm.getUsers = function(){

        function onSuccess(result){
            vm.users = result;
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        UserService.getUsers() 
              .then(onSuccess)
              .catch(onFailure);
    };

    vm.getChatByUser = function(userId){

        function onSuccess(result){
            vm.chatSelected = result;
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        ChatService.getChatByUser(vm.user.id,userId) 
              .then(onSuccess)
              .catch(onFailure);
    };

    vm.sendMessage = function(){

        function onSuccess(result){
            vm.chatSelected.messages.push({'sender': vm.user,'content':vm.text});
            vm.text = "";
        };

        function onFailure(error){
            vm.ERROR_MSG = error;
        };

        ChatService.sendMessage(vm.chatSelected.id,vm.user.id,vm.text) 
              .then(onSuccess)
              .catch(onFailure);
    };

  vm.getUsers();
  }

})()