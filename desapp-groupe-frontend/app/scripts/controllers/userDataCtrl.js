(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('UserDataCtrl', userDataCtrl );

/* @ngInject */
function userDataCtrl(SessionService,UserService) {
  var vm = this; 


  /* String */ vm.ERROR_MSG = null;
  vm.user = SessionService.user();
  vm.name = "";
  vm.lastName = "";
  vm.userName = "";
  vm.image = "";
  vm.capacity = "";
  vm.oilWastePerKmInLts = "";
  vm.showVehicle = false ; 
  vm.toggleShowVehicle = toggleShowVehicle;
  vm.back = back;
  vm.save = save;

  function toggleShowVehicle(){
  	vm.showVehicle = ! vm.showVehicle ;
  }


  function back(){
  	vm.name = vm.user.name ;
  	vm.lastName = vm.user.lastName;
  	vm.userName = vm.user.userName;
  	vm.image = vm.user.image;
  	if(vm.user.vehicle != null){
  		vm.showVehicle = true;
  		vm.capacity = vm.user.vehicle.capacity;
  		vm.oilWastePerKmInLts = vm.user.vehicle.oilWastePerKmInLts;
  	}
  }

  function save() {
  	vm.user.name = vm.name;
  	vm.user.lastName = vm.lastName;
  	vm.user.userName = vm.userName;
  	vm.user.image = vm.image;

  	if(vm.showVehicle) {
  		var vehicle = {
        capacity: vm.capacity,
        oilWastePerKmInLts: vm.oilWastePerKmInLts
      };
  	}

  	UserService.updateDataUser(vm.user,vehicle)
			.then(onSuccess)
      .catch(onFailure);

    function onSuccess(result){
    	return result
    };

    function onFailure(error){
        vm.ERROR_MSG = error;
    };

    SessionService.reloadUser();
  };

  vm.back();

}

})()