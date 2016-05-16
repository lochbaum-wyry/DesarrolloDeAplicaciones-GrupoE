'use strict';
angular.module('desappGrupoeFrontendApp')
	.factory('UserService',function ($http,$log){
		var url = 'http://localhost:8080/domain/servicesRest/user';

		var UserService = {};

		UserService.signUp = function(data){
			function onSuccessSignUp(response){
				return response.data;
			}

			function onFailureSignUp(error){
				$log.error('Ocurrio un error: ' + error.data);
				return 'Ocurrio un error';
			}
			return $http.post(url + '/signUp',data).then(onSuccessSignUp).catch(onFailureSignUp);
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

