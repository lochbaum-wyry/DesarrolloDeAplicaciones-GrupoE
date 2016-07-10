(function () { 
'use strict';
angular.module('desappGrupoeFrontendApp').factory('AlertService', AlertService);

function AlertService(Flash) {
    
  var alertService = {
    successAlert: successAlert,
    failureAlert: failureAlert
  };

  function successAlert(msg) {
        var message = '<strong></strong>' + msg;
        var id = Flash.create('success', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };

  function failureAlert(error) {
        var message = '<strong></strong> ' + error;
        var id = Flash.create('danger', message, 2000, {class: 'custom-class', id: 'custom-id'}, true);
  };


  return alertService; 

}

})();