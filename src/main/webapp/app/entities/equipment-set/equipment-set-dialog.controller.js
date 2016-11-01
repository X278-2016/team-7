(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentSetDialogController', EquipmentSetDialogController);

    EquipmentSetDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'EquipmentSet', 'Metric', 'PadSet'];

    function EquipmentSetDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, EquipmentSet, Metric, PadSet) {
        var vm = this;

        vm.equipmentSet = entity;
        vm.clear = clear;
        vm.save = save;
        vm.metrics = Metric.query();
        vm.padsets = PadSet.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.equipmentSet.id !== null) {
                EquipmentSet.update(vm.equipmentSet, onSaveSuccess, onSaveError);
            } else {
                EquipmentSet.save(vm.equipmentSet, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:equipmentSetUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
