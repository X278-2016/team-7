(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadsetDetailController', PadsetDetailController);

    PadsetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Padset'];

    function PadsetDetailController($scope, $rootScope, $stateParams, previousState, entity, Padset) {
        var vm = this;

        vm.padset = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:padsetUpdate', function(event, result) {
            vm.padset = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
