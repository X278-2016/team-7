(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadsetDialogController', PadsetDialogController);

    PadsetDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Padset'];

    function PadsetDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Padset) {
        var vm = this;

        vm.padset = entity;
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
            if (vm.padset.id !== null) {
                Padset.update(vm.padset, onSaveSuccess, onSaveError);
            } else {
                Padset.save(vm.padset, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:padsetUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
