(function() {
    'use strict';

    angular
        .module('team7App')
        .controller('EquipmentSetController', EquipmentSetController);

    EquipmentSetController.$inject = ['$scope', '$state', 'EquipmentSet', 'EquipmentSetSearch'];

    function EquipmentSetController ($scope, $state, EquipmentSet, EquipmentSetSearch) {
        var vm = this;
        
        vm.equipmentSets = [];
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            EquipmentSet.query(function(result) {
                vm.equipmentSets = result;
            });
        }

        function search () {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            EquipmentSetSearch.query({query: vm.searchQuery}, function(result) {
                vm.equipmentSets = result;
            });
        }    }
})();
