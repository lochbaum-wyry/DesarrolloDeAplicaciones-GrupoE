'use strict';

describe('Controller: UserCtrl', function () {

  // load the controller's module
  beforeEach(module('desappGrupoeFrontendApp'));

  var UserCtrl,
    scope;

  var UserService = {

  };

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserCtrl = $controller('UserCtrl', {
      $scope: scope,
      $UserService: UserService
      // place here mocked dependencies
    });
  }));

  it('test de singUpData ', function () {
    var json = {'name':'asd4','lastName':'asd3','userName':'asd2','email':'asd'};
    UserCtrl.singUpData(json);
    expect(UserCtrl.ERROR_MSG).toBe('el usuario se creo correctamente');
  });

  it('test de getUserData ', function () {
    var json = {'name':'asd4','lastName':'asd3','userName':'asd2','email':'asd'};
    UserCtrl.singUpData(json);
    UserCtrl.getUserData(0);
    expect(UserCtrl.getUserData).toBe(json);
  });
});
