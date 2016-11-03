(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadsetDeleteController',PadsetDeleteController);

    PadsetDeleteController.$inject = ['$uibModalInstance', 'entity', 'Padset'];

    function PadsetDeleteController($uibModalInstance, entity, Padset) {
        var vm = this;

        vm.padset = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Padset.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
