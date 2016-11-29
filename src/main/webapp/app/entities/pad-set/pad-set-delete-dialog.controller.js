(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('PadSetDeleteController',PadSetDeleteController);

    PadSetDeleteController.$inject = ['$uibModalInstance', 'entity', 'PadSet'];

    function PadSetDeleteController($uibModalInstance, entity, PadSet) {
        var vm = this;

        vm.padSet = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PadSet.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
