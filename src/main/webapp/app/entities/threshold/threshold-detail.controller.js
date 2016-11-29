(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('ThresholdDetailController', ThresholdDetailController);

    ThresholdDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Threshold'];

    function ThresholdDetailController($scope, $rootScope, $stateParams, previousState, entity, Threshold) {
        var vm = this;

        vm.threshold = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('team7App:thresholdUpdate', function(event, result) {
            vm.threshold = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
