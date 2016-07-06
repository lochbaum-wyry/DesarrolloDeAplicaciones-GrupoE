  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ProfileCtrl', ProfileCtrl);

function ProfileCtrl(PostService,SessionService,UserService, $routeParams,Flash) {

  var vm = this; 
  vm.profileUserId = $routeParams.id;
  vm.user = {}; 
  vm.posts = [];
  vm.text = "Escribe algo :)"

  /* String */ vm.ERROR_MSG;

  loadProfile(vm.profileUserId);
  
  function loadProfile(id) {
    var userPromise = UserService.getUser(id);
    userPromise.then(onSuccUser);
    userPromise.catch(onFailUser);

    function onSuccUser(result){
        vm.user = result;
    }

    function onFailUser(error){
        vm.ERROR_MSG = error;
    }
  }

  vm.getPosts = function() {

    function onSuccess(result){
        vm.posts = result;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };
    
    PostService.getPosts(vm.profileUserId) 
            .then(onSuccess)
            .catch(onFailure);
  };

  vm.sendPost = function() {

    function onSuccess(result){
        vm.getPosts();
        vm.successAlert()
        return result
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
        vm.failureAlert(error)
    };
    
    var post = {'publisher':SessionService.user(),'date':0,'content':vm.text,'wallOwner':vm.user};

    PostService.sendPost(post) 
            .then(onSuccess)
            .catch(onFailure);
  };

  vm.successAlert = function () {
        var message = '<strong>Bien!</strong> El post se ha realizado con Exito';
        var id = Flash.create('success', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };

  vm.failureAlert = function (error) {
        var message = '<strong>Mal! </strong> ' + error;
        var id = Flash.create('danger', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };

  vm.getPosts();

}

})()