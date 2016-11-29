(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('MetricDetailController', MetricDetailController);

    MetricDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Metric', 'EquipmentSet', 'Threshold'];

    function MetricDetailController($scope, $rootScope, $stateParams, previousState, entity, Metric, EquipmentSet, Threshold) {
        var vm = this;

        vm.metric = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:metricUpdate', function(event, result) {
            vm.metric = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
