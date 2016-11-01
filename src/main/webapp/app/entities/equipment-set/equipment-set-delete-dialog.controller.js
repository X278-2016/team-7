(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentSetDeleteController',EquipmentSetDeleteController);

    EquipmentSetDeleteController.$inject = ['$uibModalInstance', 'entity', 'EquipmentSet'];

    function EquipmentSetDeleteController($uibModalInstance, entity, EquipmentSet) {
        var vm = this;

        vm.equipmentSet = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EquipmentSet.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
