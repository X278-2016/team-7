(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('ThresholdDialogController', ThresholdDialogController);

    ThresholdDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Threshold'];

    function ThresholdDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Threshold) {
        var vm = this;

        vm.threshold = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.threshold.id !== null) {
                Threshold.update(vm.threshold, onSaveSuccess, onSaveError);
            } else {
                Threshold.save(vm.threshold, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:thresholdUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
