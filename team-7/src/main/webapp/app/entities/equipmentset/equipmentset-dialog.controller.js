(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentsetDialogController', EquipmentsetDialogController);

    EquipmentsetDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Equipmentset'];

    function EquipmentsetDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Equipmentset) {
        var vm = this;

        vm.equipmentset = entity;
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
            if (vm.equipmentset.id !== null) {
                Equipmentset.update(vm.equipmentset, onSaveSuccess, onSaveError);
            } else {
                Equipmentset.save(vm.equipmentset, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:equipmentsetUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
