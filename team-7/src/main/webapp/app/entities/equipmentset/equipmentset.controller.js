(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentsetController', EquipmentsetController);

    EquipmentsetController.$inject = ['$scope', '$state', 'Equipmentset', 'EquipmentsetSearch'];

    function EquipmentsetController ($scope, $state, Equipmentset, EquipmentsetSearch) {
        var vm = this;
        
        vm.equipmentsets = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Equipmentset.query(function(result) {
                vm.equipmentsets = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            EquipmentsetSearch.query({query: vm.searchQuery}, function(result) {
                vm.equipmentsets = result;
            });
        }    }
})();
