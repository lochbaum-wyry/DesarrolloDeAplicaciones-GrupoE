  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ProfileCtrl', ProfileCtrl);

function ProfileCtrl(PostService,SessionService,UserService, $routeParams,AlertService) {

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
        AlertService.successAlert('El post se ha realizado con Exito');
        return result
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
        AlertService.failureAlert(error);
    };
    
    var post = {'publisher':SessionService.user(),'date':0,'content':vm.text,'wallOwner':vm.user};

    PostService.sendPost(post) 
            .then(onSuccess)
            .catch(onFailure);
  };

  vm.getPosts();

}

})()