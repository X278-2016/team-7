(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentsetDetailController', EquipmentsetDetailController);

    EquipmentsetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Equipmentset'];

    function EquipmentsetDetailController($scope, $rootScope, $stateParams, previousState, entity, Equipmentset) {
        var vm = this;

        vm.equipmentset = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:equipmentsetUpdate', function(event, result) {
            vm.equipmentset = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
