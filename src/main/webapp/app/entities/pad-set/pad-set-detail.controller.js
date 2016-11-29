(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadSetDetailController', PadSetDetailController);

    PadSetDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PadSet', 'EquipmentSet'];

    function PadSetDetailController($scope, $rootScope, $stateParams, previousState, entity, PadSet, EquipmentSet) {
        var vm = this;

        vm.padSet = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:padSetUpdate', function(event, result) {
            vm.padSet = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
