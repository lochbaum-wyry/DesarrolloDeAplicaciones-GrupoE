  (function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('ProfileCtrl', ProfileCtrl);

function ProfileCtrl($localStorage,PostService) {

  var vm = this; 
  vm.user = $localStorage.userProfile;
  vm.posts = [];
  vm.text = "Escribe algo :)"

  /* String */ vm.ERROR_MSG;

  vm.getPosts = function() {

    function onSuccess(result){
        vm.posts = result;
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };
    
    PostService.getPosts(vm.user.id) 
            .then(onSuccess)
            .catch(onFailure);
  };

  vm.sendPost = function() {

    function onSuccess(result){
        vm.getPosts();
        return result
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };
    
    var post = {'publisher':$localStorage.user,'date':0,'content':vm.text,'wallOwner':vm.user};

    PostService.sendPost(post) 
            .then(onSuccess)
            .catch(onFailure);
  };

  vm.getPosts();

}

})()