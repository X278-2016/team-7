(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentsetDeleteController',EquipmentsetDeleteController);

    EquipmentsetDeleteController.$inject = ['$uibModalInstance', 'entity', 'Equipmentset'];

    function EquipmentsetDeleteController($uibModalInstance, entity, Equipmentset) {
        var vm = this;

        vm.equipmentset = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Equipmentset.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
