(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentSetDetailController', EquipmentSetDetailController);

    EquipmentSetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EquipmentSet', 'Metric', 'PadSet'];

    function EquipmentSetDetailController($scope, $rootScope, $stateParams, previousState, entity, EquipmentSet, Metric, PadSet) {
        var vm = this;

        vm.equipmentSet = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:equipmentSetUpdate', function(event, result) {
            vm.equipmentSet = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
