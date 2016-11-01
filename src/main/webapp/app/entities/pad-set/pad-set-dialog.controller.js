(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadSetDialogController', PadSetDialogController);

    PadSetDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PadSet', 'EquipmentSet'];

    function PadSetDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PadSet, EquipmentSet) {
        var vm = this;

        vm.padSet = entity;
        vm.clear = clear;
        vm.save = save;
        vm.equipmentsets = EquipmentSet.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.padSet.id !== null) {
                PadSet.update(vm.padSet, onSaveSuccess, onSaveError);
            } else {
                PadSet.save(vm.padSet, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:padSetUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
