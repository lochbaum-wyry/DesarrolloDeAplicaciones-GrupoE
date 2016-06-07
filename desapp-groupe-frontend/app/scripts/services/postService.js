(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('PostService', PostService);

function PostService($http,$log) {
    
  var url = 'http://localhost:8080/domain/servicesRest/posts';

  var postService = {
    getPosts: getPosts,
    sendPost: sendPost
  };

  function getPosts(user_id) {

    return $http.get(url + '/posts/' + user_id)
          .then(onSuccessgetPosts)
          .catch(onFailuregetPosts);
    
        function onSuccessgetPosts(response){
            return response.data;
        }

        function onFailuregetPosts(error){
            $log.error('Ocurrio un error: ' + error.data);
            return 'Ocurrio un error';
        }

  };

    function sendPost(post) {

    return $http.post(url + '/post',post)
          .then(onSuccesssendPost)
          .catch(onFailuresendPost);
    
        function onSuccesssendPost(response){
            return response.data;
        }

        function onFailuresendPost(error){
            $log.error('Ocurrio un error: ' + error.data);
            return 'Ocurrio un error';
        }

  };

  return postService; 

}

})();