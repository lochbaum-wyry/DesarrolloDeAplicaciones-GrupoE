'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('UserService',function ($http,$log){
		var url = 'http://localhost:8080/domain/servicesRest/user';

		var UserService = {};

		UserService.singUp = function(data){
			function onSuccessSingUp(response){
				return response.data;
			}

			function onFailureSingUp(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';
			}
			return $http.post(url + '/singUp',data).then(onSuccessSingUp).catch(onFailureSingUp);
		};

		UserService.getUser =  function(id){

			function onSuccessGetUser(response){
				return response.data;
			}

			function onFailureGetUser(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';	
			}
			return $http.get(url + '/user/' + id).then(onSuccessGetUser).catch(onFailureGetUser);
		};

		return UserService;
	});

