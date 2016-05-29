(function () {
    'use strict';

    angular.module('app', [
        'app.core',
        'app.widgets',
        'app.dashboard',
        'app.layout'
    ])
    .config(function ($httpProvider) {
    	$httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });

})();
