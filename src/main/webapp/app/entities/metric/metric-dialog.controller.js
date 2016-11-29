(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('MetricDialogController', MetricDialogController);

    MetricDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Metric', 'EquipmentSet', 'Threshold'];

    function MetricDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Metric, EquipmentSet, Threshold) {
        var vm = this;

        vm.metric = entity;
        vm.clear = clear;
        vm.save = save;
        vm.equipmentsets = EquipmentSet.query();
        vm.thresholds = Threshold.query({filter: 'metric-is-null'});
        $q.all([vm.metric.$promise, vm.thresholds.$promise]).then(function() {
            if (!vm.metric.threshold || !vm.metric.threshold.id) {
                return $q.reject();
            }
            return Threshold.get({id : vm.metric.threshold.id}).$promise;
        }).then(function(threshold) {
            vm.thresholds.push(threshold);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.metric.id !== null) {
                Metric.update(vm.metric, onSaveSuccess, onSaveError);
            } else {
                Metric.save(vm.metric, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('team7App:metricUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
