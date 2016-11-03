(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('ThresholdDeleteController',ThresholdDeleteController);

    ThresholdDeleteController.$inject = ['$uibModalInstance', 'entity', 'Threshold'];

    function ThresholdDeleteController($uibModalInstance, entity, Threshold) {
        var vm = this;

        vm.threshold = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Threshold.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
