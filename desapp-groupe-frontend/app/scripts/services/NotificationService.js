(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('NotificationService', NotificationService);

/* @ngInject */
function NotificationService($http,$log,$localStorage,UserService) {
    
  var notificationService = {
    Type: { RideRequested: 0, RideAccepted: 1, RideRejected: 2, WallPost: 3, IncomingMessage:4 },
    notificationsFor: notificationsFor

  };

  return notificationService; 


  var url = 'http://localhost:8080/domain/servicesRest/notification';

  function notificationsFor(userId) {
      return $http.get(url + '/notificationsFor/' + userId).then(onSuccess).catch(onFail);
  }

}


function onSuccess(response){
    return response.data;
}

function onFail(error){
    $log.error('Ocurrio un error: ' + error.data);
    return 'Ocurrio un error';
}

})();