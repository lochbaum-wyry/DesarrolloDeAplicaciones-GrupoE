(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('ChatService', ChatService);

function ChatService($http,$log,SessionService) {
    
  var url = 'http://localhost:8080/domain/servicesRest/chat';

  var chatService = {
    getChatByUser: getChatByUser,
    sendMessage: sendMessage
  };

  function getChatByUser(userId,userChatId) {

    return $http.get(url + '/getChatByUser/' + userId + '/' + userChatId)
          .then(onSuccessgetChatByUser)
          .catch(onFailuregetChatByUser);
    
        function onSuccessgetChatByUser(response){
            return response.data;
        }

        function onFailuregetChatByUser(error){
            $log.error('Ocurrio un error: ' + error.data);
            return 'Ocurrio un error';
        }

  };

  function sendMessage(chatId,userId,content) {

    return $http.get(url + '/sendMessage/' + chatId + '/' + userId + '/' + content)
          .then(onSuccesssendMessage)
          .catch(onFailuresendMessage);
    
        function onSuccesssendMessage(response){
            return response.data;
        }

        function onFailuresendMessage(error){
            $log.error('Ocurrio un error: ' + error.data);
            return 'Ocurrio un error';
        }

  };


  return chatService; 

}

})();